package com.siseth.roi.dto.roi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadRoiSourceObjectReqDTO {

    private Object roi;

    private LocalDate from;

    private LocalDate to;

    private Long sourceId;
    private Boolean toAnalysis;

    public Boolean getToAnalysis() {
        return Optional.ofNullable(this.toAnalysis).orElse(false);
    }

}
