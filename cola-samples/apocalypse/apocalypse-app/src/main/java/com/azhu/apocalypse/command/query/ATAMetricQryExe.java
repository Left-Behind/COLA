package com.azhu.apocalypse.command.query;

import com.azhu.cola.dto.MultiResponse;
import com.azhu.apocalypse.metrics.SubMetricType;
import com.azhu.apocalypse.dto.ATAMetricQry;
import com.azhu.apocalypse.dto.clientobject.ATAMetricCO;
import com.azhu.apocalypse.gatewayimpl.database.MetricMapper;
import com.azhu.apocalypse.gatewayimpl.database.dataobject.MetricDO;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ATAMetricQryExe{

    @Resource
    private MetricMapper metricMapper;

    public MultiResponse<ATAMetricCO> execute(ATAMetricQry cmd) {
        List<MetricDO> metricDOList = metricMapper.listBySubMetric(cmd.getOwnerId(), SubMetricType.ATA.getMetricSubTypeCode());
        List<ATAMetricCO> ataMetricCOList = new ArrayList<>();
        metricDOList.forEach(metricDO -> {
            ATAMetricCO ataMetricCO = JSON.parseObject(metricDO.getMetricItem(), ATAMetricCO.class);
            ataMetricCO.setOwnerId(metricDO.getUserId());
            ataMetricCOList.add(ataMetricCO);
        });
        return MultiResponse.of(ataMetricCOList);
    }

}
