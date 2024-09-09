package com.azhu.apocalypse.web;

import com.azhu.apocalypse.media.MediaMapper;
import com.azhu.apocalypse.media.MediaPO;
import com.azhu.cola.dto.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "测试接口")
@RestController
public class CustomerController {

    @Autowired
    private MediaMapper mediaMapper;

    @ApiOperation(value = "查询媒体文件")
    @GetMapping("/selectById")
    public SingleResponse<MediaPO> selectById(@RequestParam String id) throws Exception {
        return SingleResponse.of(/*mediaMapper.selectById(id)*/new MediaPO());
    }
}
