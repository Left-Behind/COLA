package com.azhu.apocalypse.metrics.appquality;

import com.azhu.apocalypse.metrics.MainMetric;
import com.azhu.apocalypse.metrics.MainMetricType;
import com.azhu.apocalypse.user.UserProfile;

public class AppQualityMetric extends MainMetric {

    private AppMetric appMetric;

    public AppQualityMetric(UserProfile metricOwner){
        this.metricOwner = metricOwner;
        metricOwner.setAppQualityMetric(this);
        this.metricMainType = MainMetricType.APP_QUALITY;
    }

    @Override
    public double getWeight() {
        return metricOwner.getWeight().getAppQualityWeight();
    }
}
