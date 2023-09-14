package com.alibaba.craftsman.api;

import com.azhu.cola.dto.MultiResponse;
import com.azhu.cola.dto.Response;
import com.azhu.cola.dto.SingleResponse;
import com.alibaba.craftsman.dto.*;
import com.alibaba.craftsman.dto.clientobject.UserProfileCO;


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
