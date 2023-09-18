package com.azhu.apocalypse.command;

import com.azhu.cola.dto.Response;
import com.azhu.apocalypse.convertor.UserProfileConvertor;
import com.azhu.apocalypse.user.UserProfile;
import com.azhu.apocalypse.dto.UserProfileAddCmd;
import com.azhu.apocalypse.gateway.UserProfileGateway;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * UserProfileAddCmdExe
 *
 * @author Frank Zhang
 * @date 2019-02-28 6:25 PM
 */
@Component
public class UserProfileAddCmdExe{

    @Resource
    private UserProfileGateway userProfileGateway;

    public Response execute(UserProfileAddCmd cmd) {
        UserProfile userProfile = UserProfileConvertor.toEntity(cmd.getUserProfileCO());
        userProfileGateway.create(userProfile);
        return Response.buildSuccess();
    }
}
