package com.azhu.apocalypse.command;

import com.azhu.cola.dto.Response;
import com.azhu.apocalypse.metrics.techcontribution.ContributionMetric;
import com.azhu.apocalypse.metrics.techcontribution.MiscMetric;
import com.azhu.apocalypse.metrics.techcontribution.MiscMetricItem;
import com.azhu.apocalypse.user.UserProfile;
import com.azhu.apocalypse.dto.MiscMetricAddCmd;
import com.azhu.apocalypse.gateway.MetricGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * MiscMetricAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-03-04 11:15 AM
 */
@Component
public class MiscMetricAddCmdExe{

    @Resource
    private MetricGateway metricGateway;

    public Response execute(MiscMetricAddCmd cmd) {
        MiscMetricItem miscMetricItem = new MiscMetricItem();
        BeanUtils.copyProperties(cmd.getMiscMetricCO(), miscMetricItem);
        miscMetricItem.setSubMetric(new MiscMetric(new ContributionMetric(new UserProfile(cmd.getMiscMetricCO().getOwnerId()))));
        metricGateway.save(miscMetricItem);
        return Response.buildSuccess();
    }
}
