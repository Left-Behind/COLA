package com.azhu.apocalypse.metrics.devquality;

import com.azhu.apocalypse.metrics.MainMetric;
import com.azhu.apocalypse.metrics.MainMetricType;
import com.azhu.apocalypse.user.UserProfile;
import lombok.Data;

@Data
public class DevQualityMetric extends MainMetric {

    private BugMetric bugMetric;

    public DevQualityMetric(UserProfile metricOwner){
        this.metricOwner = metricOwner;
        metricOwner.setDevQualityMetric(this);
        this.metricMainType = MainMetricType.DEV_QUALITY;
    }

    @Override
    public double getWeight() {
        return metricOwner.getWeight().getDevQualityWeight();
    }
}
