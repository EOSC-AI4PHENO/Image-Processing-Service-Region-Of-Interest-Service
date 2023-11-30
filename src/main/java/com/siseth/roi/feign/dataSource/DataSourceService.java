package com.siseth.roi.feign.dataSource;

import com.siseth.roi.feign.dataSource.dto.ImageSourceShortResDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "source-adapter-service")
public interface DataSourceService {

    @GetMapping("/api/internal/digital/source-adapter/{id}/get")
    ImageSourceShortResDTO getSource(@PathVariable Long id);


}
