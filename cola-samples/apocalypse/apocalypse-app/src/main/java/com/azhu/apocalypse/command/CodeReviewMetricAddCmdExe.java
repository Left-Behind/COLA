package com.azhu.apocalypse.command;

import com.azhu.cola.dto.Response;
import com.azhu.apocalypse.metrics.techcontribution.CodeReviewMetric;
import com.azhu.apocalypse.metrics.techcontribution.CodeReviewMetricItem;
import com.azhu.apocalypse.metrics.techcontribution.ContributionMetric;
import com.azhu.apocalypse.user.UserProfile;
import com.azhu.apocalypse.dto.CodeReviewMetricAddCmd;
import com.azhu.apocalypse.gateway.MetricGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CodeReviewMetricAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-03-04 11:14 AM
 */
@Component
public class CodeReviewMetricAddCmdExe{

    @Autowired
    private MetricGateway metricGateway;

    public Response execute(CodeReviewMetricAddCmd cmd) {
        CodeReviewMetricItem codeReviewMetricItem = new CodeReviewMetricItem();
        BeanUtils.copyProperties(cmd, codeReviewMetricItem);
        codeReviewMetricItem.setSubMetric(new CodeReviewMetric(new ContributionMetric(new UserProfile(cmd.getOwnerId()))));
        metricGateway.save(codeReviewMetricItem);
        return Response.buildSuccess();
    }
}
