package com.siseth.roi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siseth.roi.component.file.FileUtils;
import com.siseth.roi.dto.roi.request.UploadRoiInternalReqDTO;
import com.siseth.roi.dto.roi.request.UploadRoiSourceFileReqDTO;
import com.siseth.roi.dto.roi.request.UploadRoiSourceObjectReqDTO;
import com.siseth.roi.dto.roi.response.RoiResDTO;
import com.siseth.roi.dto.roi.response.RoiShortResDTO;
import com.siseth.roi.entity.Roi2Source;
import com.siseth.roi.feign.dataSource.DataSourceService;
import com.siseth.roi.feign.dataSource.dto.ImageSourceShortResDTO;
import com.siseth.roi.feign.fedora.FedoraService;
import com.siseth.roi.feign.fedora.dto.FileExistsDTO;
import com.siseth.roi.repository.Roi2SourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoiService {

    private final Roi2SourceRepository repository;

    private final FedoraService fedoraService;

    private final FileService fileService;

    private final DataSourceService dataSourceService;


//    public Object uploadRoi(UploadRoiSourceFileReqDTO api, String userId, String realm) {
//
//        FileExistsDTO response = fedoraService.uploadRoi(api.getRoi(), api.getRoi().getOriginalFilename(),
//                userId, realm,
//                "json_roi",
//                api.getSourceId());
//
//        return null;
//    }

    public RoiResDTO getRoiToSourceAllInternal(Long sourceId, LocalDateTime dateTime, String realm, String userId) {
        return repository.getRoiToSources(sourceId, dateTime, userId,  Roi2Source.RoiType.IMAGE, realm)
                .stream()
                .findFirst()
                .map(x -> new RoiResDTO(x, fileService.getRoi(x.getFileId())))
                .orElse(null);
    }
    public byte[] getRoiToSourceInternal(Long sourceId, String dateTime, String realm, String userId) {
        return repository.getRoiToSources(sourceId, LocalDateTime.parse(dateTime), userId, Roi2Source.RoiType.IMAGE, realm)
                .stream()
                .findFirst()
                .map(x -> fileService.getRoiByte(x.getFileId()))
                .orElseThrow(() -> new RuntimeException("Roi not found!"));
    }

    public RoiShortResDTO addRoi(UploadRoiInternalReqDTO api, String userId, String realm) {
        File file = fileService.createFile(api.getSourceId(), api.getRoi());
        MultipartFile multipartFile = FileUtils.convert(file);

        Roi2Source entity = new Roi2Source(api.getSourceId(), null, null, Roi2Source.RoiType.RESULT, userId, realm);

        FileExistsDTO response = fedoraService.uploadRoi(multipartFile, file.getName(),
                userId, realm,
                "json_result", api.getSourceId());

        entity.addFile(response.getFileId());

        return new RoiShortResDTO(repository.save(entity));
    }

    public Object uploadRoi(UploadRoiSourceObjectReqDTO api, String userId, String realm) {
        File file = fileService.createFile(api.getSourceId(), api.getRoi());
        MultipartFile multipartFile = FileUtils.convert(file);

        Roi2Source entity = new Roi2Source(api.getSourceId(),
                                                api.getFrom(), api.getTo(),
                                                api.getToAnalysis() ?
                                                        Roi2Source.RoiType.IMAGE_ANALYSIS :
                                                        Roi2Source.RoiType.IMAGE,
                                                userId, realm);

        FileExistsDTO response = fedoraService.uploadRoi(multipartFile, file.getName(),
                userId, realm,
                "json_roi",
                api.getSourceId());

        entity.addFile(response.getFileId());

        repository.save(entity);
        return entity.getFileId();
    }

    public void deleteRoiToSource(Long sourceId, String userId, String realm) {
        List<Roi2Source> roi2SourceList =
                repository.findAllBySourceIdAndUserIdAndRealmAndTypeOrderByCreatedAt(sourceId, userId, realm, Roi2Source.RoiType.IMAGE);
        roi2SourceList.forEach(Roi2Source::delete);
        repository.saveAll(roi2SourceList);
    }

    @SneakyThrows
    public List<RoiResDTO> getRoiToSource(Long sourceId, String userId, String realm) {
        return repository.findAllBySourceIdAndUserIdAndRealmAndTypeOrderByCreatedAt(sourceId, userId, realm, Roi2Source.RoiType.IMAGE)
                .stream()
                .map(x -> new RoiResDTO(x, fileService.getRoi(x.getFileId()) ))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public List<RoiResDTO> getOwnerRoiToSource(Long sourceId, String realm) {
        ImageSourceShortResDTO source = dataSourceService.getSource(sourceId);
        return repository.findAllBySourceIdAndUserIdAndRealmAndTypeOrderByCreatedAt(sourceId, source.getUserId(), realm, Roi2Source.RoiType.IMAGE)
                .stream()
                .map(x -> new RoiResDTO(x, fileService.getRoi(x.getFileId()) ))
                .collect(Collectors.toList());
    }


    public void deleteRoi(Long id, String realm) {
        repository.findById(id)
                .ifPresent(x -> {
                    x.delete();
                    repository.save(x);
                    System.out.println("Deleted roi by id " + id);
                });
    }

    @SneakyThrows
    public Map<String, Object> getRoi(Long fileId) {
        Map<String, Object> roi = fedoraService.getRoi(fileId);
        roi.put("file", roi.remove(roi.entrySet().iterator().next().getKey()));
        return roi;
    }

}
