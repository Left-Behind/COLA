package com.alibaba.craftsman.command;

import com.azhu.cola.dto.Response;
import com.alibaba.craftsman.metrics.techinfluence.ATAMetric;
import com.alibaba.craftsman.metrics.techinfluence.ATAMetricItem;
import com.alibaba.craftsman.metrics.techinfluence.InfluenceMetric;
import com.alibaba.craftsman.user.UserProfile;
import com.alibaba.craftsman.dto.ATAMetricAddCmd;
import com.alibaba.craftsman.gateway.MetricGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ATAMetricAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-03-01 11:42 AM
 */
@Component
public class ATAMetricAddCmdExe{

    @Autowired
    private MetricGateway metricGateway;

    public Response execute(ATAMetricAddCmd cmd) {
        ATAMetricItem ataMetricItem = new ATAMetricItem();
        BeanUtils.copyProperties(cmd.getAtaMetricCO(), ataMetricItem);
        ataMetricItem.setSubMetric(new ATAMetric(new InfluenceMetric(new UserProfile(cmd.getAtaMetricCO().getOwnerId()))));
        metricGateway.save(ataMetricItem);
        return Response.buildSuccess();
    }
}
