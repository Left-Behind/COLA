package com.azhu.apocalypse.web;

import com.azhu.cola.dto.MultiResponse;
import com.azhu.cola.dto.Response;
import com.azhu.apocalypse.api.MetricsServiceI;
import com.azhu.apocalypse.dto.ATAMetricAddCmd;
import com.azhu.apocalypse.dto.ATAMetricQry;
import com.azhu.apocalypse.dto.clientobject.ATAMetricCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MetricsController {

    @Autowired
    private MetricsServiceI metricsService;

    @GetMapping(value = "/metrics/ata")
    public MultiResponse<ATAMetricCO> listATAMetrics(@RequestParam String ownerId){
        ATAMetricQry ataMetricQry = new ATAMetricQry();
        ataMetricQry.setOwnerId(ownerId);
        return metricsService.listATAMetrics(ataMetricQry);
    }

    @PostMapping(value = "/metrics/ata")
    public Response addATAMetric(@RequestBody ATAMetricAddCmd ataMetricAddCmd){
        return metricsService.addATAMetric(ataMetricAddCmd);
    }
}
