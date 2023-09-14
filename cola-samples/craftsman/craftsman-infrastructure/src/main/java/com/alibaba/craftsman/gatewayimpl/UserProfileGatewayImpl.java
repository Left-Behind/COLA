package com.alibaba.craftsman.gatewayimpl;

import com.alibaba.craftsman.convertor.UserProfileConvertor;
import com.alibaba.craftsman.DomainFactory;
import com.alibaba.craftsman.gateway.UserProfileGateway;
import com.alibaba.craftsman.metrics.weight.WeightFactory;
import com.alibaba.craftsman.user.Role;
import com.alibaba.craftsman.user.UserProfile;
import com.alibaba.craftsman.gatewayimpl.database.UserProfileMapper;
import com.alibaba.craftsman.gatewayimpl.database.dataobject.UserProfileDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * UserProfileGatewayImpl
 *
 * @author Frank Zhang
 * @date 2020-07-02 12:32 PM
 */
@Component
@Slf4j
public class UserProfileGatewayImpl implements UserProfileGateway {

    @Resource
    private UserProfileMapper userProfileMapper;


    public void create(UserProfile userProfile) {
        userProfileMapper.create(UserProfileConvertor.toDataObjectForCreate(userProfile));
    }

    public void update(UserProfile userProfile) {
        userProfileMapper.update(UserProfileConvertor.toDataObjectForUpdate(userProfile));
    }

    public UserProfile getByUserId(String userId) {
        UserProfileDO userProfileDO = userProfileMapper.getByUserId(userId);
        if (userProfileDO == null) {
            log.warn("There is no UserProfile for : " + userId);
            return null;
        }
        UserProfile userProfile = DomainFactory.getUserProfile();
        BeanUtils.copyProperties(userProfileDO, userProfile);
        Role role = Role.valueOf(userProfileDO.getRole());
        userProfile.setRole(role);
        userProfile.setWeight(WeightFactory.get(role));
        return userProfile;
    }

}
