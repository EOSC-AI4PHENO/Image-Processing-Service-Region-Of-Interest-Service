package com.siseth.roi.dto.roi.response;

import com.siseth.roi.entity.Roi2Source;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoiShortResDTO {
    private Long id;

    private Long sourceId;

    private Long fileId;

    public RoiShortResDTO(Roi2Source roi2Source) {
        this.id = roi2Source.getId();
        this.sourceId = roi2Source.getSourceId();
        this.fileId = roi2Source.getFileId();
    }

}
