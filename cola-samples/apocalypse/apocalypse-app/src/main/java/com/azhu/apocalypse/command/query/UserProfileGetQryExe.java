package com.azhu.apocalypse.command.query;

import com.azhu.cola.dto.SingleResponse;
import com.azhu.apocalypse.dto.UserProfileGetQry;
import com.azhu.apocalypse.dto.clientobject.UserProfileCO;
import com.azhu.apocalypse.gatewayimpl.database.UserProfileMapper;
import com.azhu.apocalypse.gatewayimpl.database.dataobject.UserProfileDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserProfileGetQryExe {

    @Resource
    private UserProfileMapper userProfileMapper;

    public SingleResponse<UserProfileCO> execute(UserProfileGetQry qry) {
        UserProfileDO userProfileDO = userProfileMapper.getByUserId(qry.getUserId());
        UserProfileCO userProfileCO = new UserProfileCO();
        BeanUtils.copyProperties(userProfileDO, userProfileCO);
        return SingleResponse.of(userProfileCO);
    }

}
