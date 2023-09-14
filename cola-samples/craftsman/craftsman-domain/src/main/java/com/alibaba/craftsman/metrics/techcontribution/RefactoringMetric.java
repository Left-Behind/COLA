package com.alibaba.craftsman.metrics.techcontribution;

import com.alibaba.craftsman.metrics.MainMetric;
import com.alibaba.craftsman.metrics.SubMetric;
import com.alibaba.craftsman.metrics.SubMetricType;

/**
 * 重构指标
 * @author xueliang.sxl, alisa.hsh, xiangning.lxn
 */
public class RefactoringMetric extends SubMetric {

    public RefactoringMetric(){
        this.subMetricType = SubMetricType.Refactoring;
    }

    public RefactoringMetric(MainMetric parent) {
        this.parent = parent;
        parent.addSubMetric(this);
        this.subMetricType = SubMetricType.Refactoring;
    }

    @Override
    public double getWeight() {
        return  metricOwner.getWeight().getUnanimousWeight();
    }
}
