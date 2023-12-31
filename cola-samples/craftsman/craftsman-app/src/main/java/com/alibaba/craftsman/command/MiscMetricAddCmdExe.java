package com.alibaba.craftsman.command;

import com.azhu.cola.dto.Response;
import com.alibaba.craftsman.metrics.techcontribution.ContributionMetric;
import com.alibaba.craftsman.metrics.techcontribution.MiscMetric;
import com.alibaba.craftsman.metrics.techcontribution.MiscMetricItem;
import com.alibaba.craftsman.user.UserProfile;
import com.alibaba.craftsman.dto.MiscMetricAddCmd;
import com.alibaba.craftsman.gateway.MetricGateway;
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
