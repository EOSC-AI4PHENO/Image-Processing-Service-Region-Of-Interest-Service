package com.siseth.roi.dto.roi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadRoiInternalReqDTO {

    private Object roi;

    private Long sourceId;

}
