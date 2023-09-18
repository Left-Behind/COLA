package com.azhu.apocalypse.dto;

import com.azhu.apocalypse.dto.clientobject.PaperMetricCO;
import com.azhu.apocalypse.dto.clientobject.PatentMetricCO;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * PaperMetricAddCmd
 *
 * @author Frank Zhang
 * @date 2019-03-03 11:38 AM
 */
@Data
public class PaperMetricAddCmd extends CommonCommand{
    @NotNull
    private PaperMetricCO paperMetricCO;
}