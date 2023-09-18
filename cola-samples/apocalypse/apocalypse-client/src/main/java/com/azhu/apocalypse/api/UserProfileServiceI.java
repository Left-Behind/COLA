package com.azhu.apocalypse.api;

import com.azhu.cola.dto.MultiResponse;
import com.azhu.cola.dto.Response;
import com.azhu.cola.dto.SingleResponse;
import com.azhu.apocalypse.dto.*;
import com.azhu.apocalypse.dto.clientobject.UserProfileCO;


/**
 * UserProfileServiceI
 *
 * @author Frank Zhang
 * @date 2019-02-28 6:15 PM
 */
public interface UserProfileServiceI {
    Response addUserProfile(UserProfileAddCmd cmd);
    Response updateUserProfile(UserProfileUpdateCmd cmd);
    Response refreshScore(RefreshScoreCmd cmd);
    SingleResponse<UserProfileCO> getUserProfileBy(UserProfileGetQry qry);
    MultiResponse<UserProfileCO>  listUserProfileBy(UserProfileListQry qry);
}