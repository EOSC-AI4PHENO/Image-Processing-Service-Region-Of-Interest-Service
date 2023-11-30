package com.siseth.roi.dto.roi.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadRoiSourceFileReqDTO {

    private MultipartFile roi;

    private String from;

    private String to;

    private Long sourceId;

}
