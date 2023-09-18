package com.azhu.apocalypse.command.query;

import com.azhu.cola.dto.MultiResponse;
import com.azhu.apocalypse.dto.UserProfileListQry;
import com.azhu.apocalypse.dto.clientobject.UserProfileCO;
import com.azhu.apocalypse.gatewayimpl.database.UserProfileMapper;
import com.azhu.apocalypse.gatewayimpl.database.dataobject.UserProfileDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserProfileListQryExe{

    @Resource
    private UserProfileMapper userProfileMapper;

    public MultiResponse<UserProfileCO> execute(UserProfileListQry qry) {
        List<UserProfileDO> userProfileDOList = userProfileMapper.listByDep(qry.getDep());
        List<UserProfileCO> userProfileCOList = new ArrayList<>();
        userProfileDOList.forEach(userDO -> {
            UserProfileCO userProfileCO = new UserProfileCO();
            BeanUtils.copyProperties(userDO, userProfileCO);
            userProfileCOList.add(userProfileCO);
        });
        return MultiResponse.of(userProfileCOList);
    }

}

