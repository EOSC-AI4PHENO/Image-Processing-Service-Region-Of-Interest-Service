package com.siseth.roi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siseth.roi.dto.roi.request.UploadRoiSourceFileReqDTO;
import com.siseth.roi.dto.roi.request.UploadRoiSourceObjectReqDTO;
import com.siseth.roi.dto.roi.response.RoiResDTO;
import com.siseth.roi.service.RoiService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/image-processing/roi")
@RequiredArgsConstructor
public class RoiController {

    private final RoiService roiService;

//    @PostMapping("/file")
//    @SecurityRequirement(name = "Bearer Authentication")
//    public ResponseEntity<?> addRoi(@ModelAttribute UploadRoiSourceFileReqDTO api,
//                                    @Parameter(hidden = true) @RequestHeader(required = false) String id,
//                                    @Parameter(hidden = true) @RequestHeader(required = false) String realm) {
//        return ResponseEntity.ok(roiService.uploadRoi(api, id, realm));
//    }

    @PostMapping("/json")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> addRoi(@RequestBody UploadRoiSourceObjectReqDTO api,
                                    @Parameter(hidden = true) @RequestHeader(required = false)  String id,
                                    @Parameter(hidden = true) @RequestHeader(required = false)  String realm) {
        return ResponseEntity.ok(roiService.uploadRoi(api, id, realm));
    }

    //GET ROI TO SOURCE

    @GetMapping("/{sourceId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<RoiResDTO>> getRoiToSource(@PathVariable Long sourceId,
                                                          @Parameter(hidden = true) @RequestHeader(required = false)  String id,
                                                          @Parameter(hidden = true) @RequestHeader(required = false)  String realm) {
        return ResponseEntity.ok(roiService.getRoiToSource(sourceId, id, realm));
    }

    @GetMapping("/owner")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<RoiResDTO>> getOwnerRoiToSource(@RequestParam Long sourceId,
                                                          @Parameter(hidden = true) @RequestHeader(required = false)  String id,
                                                          @Parameter(hidden = true) @RequestHeader(required = false)  String realm) {
        return ResponseEntity.ok(roiService.getOwnerRoiToSource(sourceId, realm));
    }

    @DeleteMapping("/source")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> deleteRoiToSource(@RequestParam Long sourceId,
                                                  @Parameter(hidden = true) @RequestHeader(required = false)  String id,
                                                  @Parameter(hidden = true) @RequestHeader(required = false)  String realm) {
        roiService.deleteRoiToSource(sourceId, id, realm);
        return ResponseEntity.ok().build();
    }
    //DELETE ROI
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> deleteRoi(@PathVariable Long id,
                                                     @Parameter(hidden = true) @RequestHeader(required = false)  String realm) {
        roiService.deleteRoi(id, realm);
        return ResponseEntity.ok().build();
    }


    @GetMapping("read")
    @SneakyThrows
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Map<String, Object>> read(@RequestParam Long fileId) {
        return ResponseEntity.ok(roiService.getRoi(fileId));
    }

}
