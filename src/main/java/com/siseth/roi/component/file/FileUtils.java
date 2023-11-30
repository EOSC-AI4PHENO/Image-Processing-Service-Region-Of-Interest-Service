package com.siseth.roi.component.file;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public final class FileUtils {

    public static MultipartFile convert(File file) {
        try{
            return new MockMultipartFile(file.getName(), new FileInputStream(file));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}