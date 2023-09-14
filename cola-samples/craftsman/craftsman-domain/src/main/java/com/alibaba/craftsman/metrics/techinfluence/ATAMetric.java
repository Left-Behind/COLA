package com.alibaba.craftsman.metrics.techinfluence;


import com.alibaba.craftsman.metrics.MainMetric;
import com.alibaba.craftsman.metrics.SubMetric;
import com.alibaba.craftsman.metrics.SubMetricType;

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
