package com.siseth.roi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siseth.roi.feign.fedora.FedoraService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final String temporaryString = System.getProperty("java.io.tmpdir");

    private final FedoraService fedoraService;


    @SneakyThrows
    public File createFile(Long sourceId, Object json) {
        File file = new File(this.temporaryString, generateString(sourceId));
        file.deleteOnExit();
        new ObjectMapper().writeValue(file, convertFromString(json));
        return file;
    }

    @SneakyThrows
    public Object convertFromString(Object json) {
        if(json instanceof String) {
            return new ObjectMapper().readValue((String) json, Object.class);
        }
        return json;
    }

    public Object getRoi(Long fileId) {
        return Optional.ofNullable(fileId)
                                        .map(fedoraService::getRoi)
                                        .orElse(null);
    }

    public byte[] getRoiByte(Long fileId) {
        return Optional.ofNullable(fileId)
                                    .map(fedoraService::getRoiByte)
                                    .orElse(null);
    }

    private String generateString(Long sourceId) {
        return UUID.randomUUID() + ".json";
    }

}
