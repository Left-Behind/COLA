package com.azhu.apocalypse.convertor;


import com.azhu.apocalypse.metrics.MetricItem;
import com.azhu.apocalypse.gatewayimpl.database.dataobject.MetricDO;

/**
 * @author frankzhang
 */
public class MetricConvertor{

    public static MetricDO toDataObject(MetricItem metricItem){
        MetricDO metricDO = new MetricDO();
        metricDO.setUserId(metricItem.getMetricOwner().getUserId());
        metricDO.setMainMetric(metricItem.getSubMetric().getParent().getCode());
        metricDO.setSubMetric(metricItem.getSubMetric().getCode());
        metricDO.setMetricItem(metricItem.toJsonString());
        metricDO.setCreator("test");
        metricDO.setModifier("test");
        return metricDO;
    }

}
