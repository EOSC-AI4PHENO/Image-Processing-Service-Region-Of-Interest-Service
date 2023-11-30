package com.siseth.roi.dto.roi.response;

import com.siseth.roi.entity.Roi2Source;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoiResDTO extends RoiShortResDTO{
    private LocalDate from;
    private LocalDate to;
    private Object roi;
    public RoiResDTO(Roi2Source roi2Source, Object roi) {
        super(roi2Source);
        this.from = roi2Source.getDateFrom();
        this.to = roi2Source.getDateTo();
        this.roi = roi;
    }

}
