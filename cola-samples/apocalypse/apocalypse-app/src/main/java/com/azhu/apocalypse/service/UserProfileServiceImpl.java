package com.azhu.apocalypse.service;

import com.azhu.cola.catchlog.CatchAndLog;
import com.azhu.cola.dto.MultiResponse;
import com.azhu.cola.dto.Response;
import com.azhu.cola.dto.SingleResponse;
import com.azhu.apocalypse.api.UserProfileServiceI;
import com.azhu.apocalypse.command.RefreshScoreCmdExe;
import com.azhu.apocalypse.command.UserProfileAddCmdExe;
import com.azhu.apocalypse.command.UserProfileUpdateCmdExe;
import com.azhu.apocalypse.command.query.UserProfileGetQryExe;
import com.azhu.apocalypse.command.query.UserProfileListQryExe;
import com.azhu.apocalypse.dto.*;
import com.azhu.apocalypse.dto.clientobject.UserProfileCO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserProfileServiceImpl
 *
 * @author Frank Zhang
 * @date 2019-02-28 6:22 PM
 */
@Service
@CatchAndLog
public class UserProfileServiceImpl implements UserProfileServiceI{
    @Resource
    private UserProfileAddCmdExe userProfileAddCmdExe;
    @Resource
    private UserProfileUpdateCmdExe userProfileUpdateCmdExe;
    @Resource
    private RefreshScoreCmdExe refreshScoreCmdExe;
    @Resource
    private UserProfileGetQryExe userProfileGetQryExe;
    @Resource
    private UserProfileListQryExe userProfileListQryExe;


    @Override
    public Response addUserProfile(UserProfileAddCmd userProfileAddCmd) {
        return  userProfileAddCmdExe.execute(userProfileAddCmd);
    }

    @Override
    public Response updateUserProfile(UserProfileUpdateCmd cmd) {
        return userProfileUpdateCmdExe.execute(cmd);
    }

    @Override
    public Response refreshScore(RefreshScoreCmd cmd) {
        return refreshScoreCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<UserProfileCO> getUserProfileBy(UserProfileGetQry qry) {
        return userProfileGetQryExe.execute(qry);
    }

    @Override
    public MultiResponse<UserProfileCO> listUserProfileBy(UserProfileListQry qry) {
        return userProfileListQryExe.execute(qry);
    }
}
