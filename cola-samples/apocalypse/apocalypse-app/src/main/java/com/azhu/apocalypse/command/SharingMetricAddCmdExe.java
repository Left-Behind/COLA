package com.azhu.apocalypse.command;

import com.azhu.cola.dto.Response;
import com.azhu.apocalypse.metrics.techinfluence.InfluenceMetric;
import com.azhu.apocalypse.metrics.techinfluence.SharingMetric;
import com.azhu.apocalypse.metrics.techinfluence.SharingMetricItem;
import com.azhu.apocalypse.metrics.techinfluence.SharingScope;
import com.azhu.apocalypse.user.UserProfile;
import com.azhu.apocalypse.dto.SharingMetricAddCmd;
import com.azhu.apocalypse.gateway.MetricGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * SharingMetricAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-03-02 5:00 PM
 */
@Component
public class SharingMetricAddCmdExe{

    @Resource
    private MetricGateway metricGateway;

    public Response execute(SharingMetricAddCmd cmd) {
        SharingMetricItem sharingMetricItem = new SharingMetricItem();
        BeanUtils.copyProperties(cmd.getSharingMetricCO(), sharingMetricItem);
        sharingMetricItem.setSubMetric(new SharingMetric(new InfluenceMetric(new UserProfile(cmd.getSharingMetricCO().getOwnerId()))));
        sharingMetricItem.setSharingScope(SharingScope.valueOf(cmd.getSharingMetricCO().getSharingScope()));
        metricGateway.save(sharingMetricItem);
        return Response.buildSuccess();
    }
}
