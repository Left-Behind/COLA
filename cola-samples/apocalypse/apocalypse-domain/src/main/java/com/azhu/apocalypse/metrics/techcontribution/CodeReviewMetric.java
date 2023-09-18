package com.azhu.apocalypse.metrics.techcontribution;


import com.azhu.apocalypse.metrics.MainMetric;
import com.azhu.apocalypse.metrics.SubMetric;
import com.azhu.apocalypse.metrics.SubMetricType;

/**
 * CodeReview指标
 * @author xueliang.sxl, alisa.hsh, xiangning.lxn
 */
public class CodeReviewMetric extends SubMetric {

    public CodeReviewMetric(){
        this.subMetricType = SubMetricType.CodeReview;
    }

    public CodeReviewMetric(MainMetric parent) {
        this.parent = parent;
        parent.addSubMetric(this);
        this.subMetricType = SubMetricType.CodeReview;
    }

    @Override
    public double getWeight() {
        return metricOwner.getWeight().getUnanimousWeight();
    }
}
