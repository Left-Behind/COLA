package com.alibaba.craftsman.command;

import com.azhu.cola.dto.Response;
import com.alibaba.craftsman.convertor.UserProfileConvertor;
import com.alibaba.craftsman.user.UserProfile;
import com.alibaba.craftsman.dto.UserProfileUpdateCmd;
import com.alibaba.craftsman.gateway.UserProfileGateway;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserProfileUpdateCmdExe{

    @Resource
    private UserProfileGateway userProfileGateway;

    public Response execute(UserProfileUpdateCmd cmd) {
        UserProfile userProfile = UserProfileConvertor.toEntity(cmd.getUserProfileCO());
        userProfileGateway.update(userProfile);
        return Response.buildSuccess();
    }
}
