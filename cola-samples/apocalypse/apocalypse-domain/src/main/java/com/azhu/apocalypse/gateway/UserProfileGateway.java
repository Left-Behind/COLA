package com.azhu.apocalypse.gateway;

import com.azhu.apocalypse.user.UserProfile;

/**
 * UserProfileGateway
 *
 * @author Frank Zhang
 * @date 2020-07-02 12:16 PM
 */
public interface UserProfileGateway {
    void create(UserProfile userProfile);
    void update(UserProfile userProfile);
    UserProfile getByUserId(String userId);
}
