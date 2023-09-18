package com.azhu.apocalypse.gateway;

import com.azhu.apocalypse.metrics.MetricItem;
import com.azhu.apocalypse.metrics.SubMetric;
import com.azhu.apocalypse.metrics.appquality.AppMetric;
import com.azhu.apocalypse.metrics.devquality.BugMetric;

import java.util.List;

/**
 * MetricGateway
 *
 * @author Frank Zhang
 * @date 2020-07-02 12:16 PM
 */
public interface MetricGateway {
    void save(MetricItem metricItem);
    List<SubMetric> listByTechContribution(String userId);
    List<SubMetric> listByTechInfluence(String userId);
    BugMetric getBugMetric(String userId);
    AppMetric getAppMetric(String userId);
}
