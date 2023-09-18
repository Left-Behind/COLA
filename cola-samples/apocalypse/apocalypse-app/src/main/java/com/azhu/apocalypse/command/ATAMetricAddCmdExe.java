package com.azhu.apocalypse.command;

import com.azhu.cola.dto.Response;
import com.azhu.apocalypse.metrics.techinfluence.ATAMetric;
import com.azhu.apocalypse.metrics.techinfluence.ATAMetricItem;
import com.azhu.apocalypse.metrics.techinfluence.InfluenceMetric;
import com.azhu.apocalypse.user.UserProfile;
import com.azhu.apocalypse.dto.ATAMetricAddCmd;
import com.azhu.apocalypse.gateway.MetricGateway;
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
