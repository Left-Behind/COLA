package com.alibaba.craftsman.command;

import com.azhu.cola.dto.Response;
import com.alibaba.craftsman.metrics.techcontribution.CodeReviewMetric;
import com.alibaba.craftsman.metrics.techcontribution.CodeReviewMetricItem;
import com.alibaba.craftsman.metrics.techcontribution.ContributionMetric;
import com.alibaba.craftsman.user.UserProfile;
import com.alibaba.craftsman.dto.CodeReviewMetricAddCmd;
import com.alibaba.craftsman.gateway.MetricGateway;
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
