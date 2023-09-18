package com.azhu.apocalypse.command;

import com.azhu.cola.dto.Response;
import com.azhu.apocalypse.metrics.techcontribution.ContributionMetric;
import com.azhu.apocalypse.metrics.techcontribution.RefactoringLevel;
import com.azhu.apocalypse.metrics.techcontribution.RefactoringMetric;
import com.azhu.apocalypse.metrics.techcontribution.RefactoringMetricItem;
import com.azhu.apocalypse.user.UserProfile;
import com.azhu.apocalypse.dto.RefactoringMetricAddCmd;
import com.azhu.apocalypse.gateway.MetricGateway;
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
