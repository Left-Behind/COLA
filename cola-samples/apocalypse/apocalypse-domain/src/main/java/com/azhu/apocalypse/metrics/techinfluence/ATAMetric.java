package com.azhu.apocalypse.metrics.techinfluence;


import com.azhu.apocalypse.metrics.MainMetric;
import com.azhu.apocalypse.metrics.SubMetric;
import com.azhu.apocalypse.metrics.SubMetricType;

/**
 * ATAMetric
 * ATA文章指标
 * @author Frank Zhang
 * @date 2018-07-04 1:24 PM
 */
public class ATAMetric extends SubMetric {

    public ATAMetric(){
        this.subMetricType = SubMetricType.ATA;
    }

    public ATAMetric(MainMetric parent) {
        this.parent = parent;
        parent.addSubMetric(this);
        this.subMetricType = SubMetricType.ATA;
    }

    @Override
    public double getWeight() {
        return  parent.getMetricOwner().getWeight().getUnanimousWeight();
    }
}
