package com.siseth.roi.feign.fedora;

import com.siseth.roi.feign.fedora.dto.FileExistsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

@FeignClient(name = "fedora-management-service")
public interface FedoraService {

    @PostMapping(value = "/api/internal/assets/fedora/roi", headers = "Content-Type=multipart/form-data")
    FileExistsDTO uploadRoi(@RequestPart("file") MultipartFile file,
                            @RequestParam String name,
                            @RequestParam String userId,
                            @RequestParam String realm,
                            @RequestParam String type,
                            @RequestParam Long sourceId);

    @GetMapping("/api/internal/assets/fedora/roi")
    Map<String, Object> getRoi(@RequestParam Long fileId);

    @GetMapping("/api/internal/assets/fedora/roi/byte")
    byte[] getRoiByte(@RequestParam Long fileId);

}
