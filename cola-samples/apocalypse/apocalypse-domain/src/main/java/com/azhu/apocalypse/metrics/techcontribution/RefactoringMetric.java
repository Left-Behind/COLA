package com.azhu.apocalypse.metrics.techcontribution;

import com.azhu.apocalypse.metrics.MainMetric;
import com.azhu.apocalypse.metrics.SubMetric;
import com.azhu.apocalypse.metrics.SubMetricType;

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
