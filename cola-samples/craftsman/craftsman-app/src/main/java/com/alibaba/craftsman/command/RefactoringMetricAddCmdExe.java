package com.alibaba.craftsman.command;

import com.azhu.cola.dto.Response;
import com.alibaba.craftsman.metrics.techcontribution.ContributionMetric;
import com.alibaba.craftsman.metrics.techcontribution.RefactoringLevel;
import com.alibaba.craftsman.metrics.techcontribution.RefactoringMetric;
import com.alibaba.craftsman.metrics.techcontribution.RefactoringMetricItem;
import com.alibaba.craftsman.user.UserProfile;
import com.alibaba.craftsman.dto.RefactoringMetricAddCmd;
import com.alibaba.craftsman.gateway.MetricGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RefactoringMetricAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-03-04 11:15 AM
 */
@Component
public class RefactoringMetricAddCmdExe{

    @Autowired
    private MetricGateway metricGateway;

    public Response execute(RefactoringMetricAddCmd cmd) {
        RefactoringMetricItem refactoringMetricItem = new RefactoringMetricItem();
        BeanUtils.copyProperties(cmd.getRefactoringMetricCO(), refactoringMetricItem);
        refactoringMetricItem.setSubMetric(new RefactoringMetric(new ContributionMetric(new UserProfile(cmd.getRefactoringMetricCO().getOwnerId()))));
        refactoringMetricItem.setRefactoringLevel(RefactoringLevel.valueOf(cmd.getRefactoringMetricCO().getRefactoringLevel()));
        metricGateway.save(refactoringMetricItem);
        return Response.buildSuccess();
    }
}
