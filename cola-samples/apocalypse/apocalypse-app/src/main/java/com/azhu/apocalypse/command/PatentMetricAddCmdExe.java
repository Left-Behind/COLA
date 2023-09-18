package com.azhu.apocalypse.command;

import com.azhu.cola.dto.Response;
import com.azhu.apocalypse.metrics.techinfluence.AuthorType;
import com.azhu.apocalypse.metrics.techinfluence.InfluenceMetric;
import com.azhu.apocalypse.metrics.techinfluence.PatentMetric;
import com.azhu.apocalypse.metrics.techinfluence.PatentMetricItem;
import com.azhu.apocalypse.user.UserProfile;
import com.azhu.apocalypse.dto.PatentMetricAddCmd;
import com.azhu.apocalypse.gateway.MetricGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * PatentMetricAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-03-03 11:41 AM
 */
@Component
public class PatentMetricAddCmdExe{

    @Resource
    private MetricGateway metricGateway;

    public Response execute(PatentMetricAddCmd cmd) {
        PatentMetricItem patentMetricItem = new PatentMetricItem();
        BeanUtils.copyProperties(cmd.getPatentMetricCO(), patentMetricItem);
        patentMetricItem.setSubMetric(new PatentMetric(new InfluenceMetric(new UserProfile(cmd.getPatentMetricCO().getOwnerId()))));
        patentMetricItem.setAuthorType(AuthorType.valueOf(cmd.getPatentMetricCO().getAuthorType()));
        metricGateway.save(patentMetricItem);
        return Response.buildSuccess();
    }
}
