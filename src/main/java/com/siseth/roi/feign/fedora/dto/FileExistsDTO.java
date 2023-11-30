package com.siseth.roi.feign.fedora.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileExistsDTO {

    private Long fileId;

    private Boolean isExist;

}
