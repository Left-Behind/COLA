package com.azhu.apocalypse.metrics.appquality;

import com.azhu.apocalypse.metrics.SubMetric;
import com.azhu.apocalypse.metrics.SubMetricType;

public class AppMetric extends SubMetric {

    public AppMetric(){
        this.subMetricType = SubMetricType.App;
    }

    @Override
    public double getWeight() {
        return metricOwner.getWeight().getUnanimousWeight();
    }

    @Override
    public double calculateScore() {
        int appCount = super.getMetricItemList().size();
        if (appCount == 0){
            return 0;
        }
        double sumScore = super.calculateScore();
        return sumScore/appCount;
    }
}
