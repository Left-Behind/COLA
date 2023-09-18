package com.azhu.apocalypse.command;

import com.azhu.cola.dto.Response;
import com.azhu.apocalypse.convertor.UserProfileConvertor;
import com.azhu.apocalypse.user.UserProfile;
import com.azhu.apocalypse.dto.UserProfileUpdateCmd;
import com.azhu.apocalypse.gateway.UserProfileGateway;
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
