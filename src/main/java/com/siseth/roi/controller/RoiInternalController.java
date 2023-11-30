package com.siseth.roi.controller;

import com.siseth.roi.dto.roi.request.UploadRoiInternalReqDTO;
import com.siseth.roi.dto.roi.request.UploadRoiSourceObjectReqDTO;
import com.siseth.roi.service.RoiService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/internal/image-processing/roi")
@RequiredArgsConstructor
public class RoiInternalController {

    private final RoiService service;

    //pobierz roi dla zdjęcia
    @GetMapping
    public ResponseEntity<?> getRoiToSource(@RequestParam Long sourceId,
                                            @RequestParam String date,
                                            @RequestParam String realm,
                                            @RequestParam String userId){
        return ResponseEntity.ok(service.getRoiToSourceInternal(sourceId, date, realm, userId));

    }

    @GetMapping("/all")
    public ResponseEntity<?> getRoiToSourceAllInternal(@RequestParam Long sourceId,
                                                        @RequestParam LocalDateTime dateTime,
                                                        @RequestParam String realm,
                                                       @RequestParam String userId){
        return ResponseEntity.ok(service.getRoiToSourceAllInternal(sourceId, dateTime, realm, userId));

    }

    @PostMapping("/results")
    public ResponseEntity<?> addRoi(@RequestBody UploadRoiInternalReqDTO api,
                                    @RequestParam(required = false)  String id,
                                    @RequestParam String realm) {
        return ResponseEntity.ok(service.addRoi(api, id, realm));
    }






}
