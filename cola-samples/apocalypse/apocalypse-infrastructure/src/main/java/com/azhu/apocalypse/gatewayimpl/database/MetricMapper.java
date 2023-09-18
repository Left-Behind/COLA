package com.azhu.apocalypse.gatewayimpl.database;

import com.azhu.apocalypse.gatewayimpl.database.dataobject.MetricDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MetricMapper extends BaseMapper<MetricDO> {

    int create(MetricDO dataObject);

    List<MetricDO> listByUserId(@Param("userId") String userId);

    List<MetricDO> listByMainMetric(@Param("userId") String userId, @Param("mainMetric") String mainMetric);

    List<MetricDO> listBySubMetric(@Param("userId") String userId, @Param("subMetric") String subMetric);

    int delete(@Param("id") String id, @Param("modifier") String modifier);

    MetricDO getById(@Param("id") String id);
}
