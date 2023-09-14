package com.alibaba.craftsman.metrics.techinfluence;

import com.alibaba.craftsman.metrics.MainMetric;
import com.alibaba.craftsman.metrics.SubMetric;
import com.alibaba.craftsman.metrics.SubMetricType;

/**
 * SharingMetric
 * 线下技术分享指标
 * @author Frank Zhang
 * @date 2018-07-04 1:25 PM
 */
public class SharingMetric extends SubMetric {

    public SharingMetric(){
        this.subMetricType = SubMetricType.Sharing;
    }

    public SharingMetric(MainMetric parent) {
        this.parent = parent;
        parent.addSubMetric(this);
        this.subMetricType = SubMetricType.Sharing;
    }

    @Override
    public double getWeight() {
        return  parent.getMetricOwner().getWeight().getUnanimousWeight();
    }
}
