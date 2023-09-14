package com.alibaba.craftsman;

import com.alibaba.craftsman.user.UserProfile;

public class DomainFactory {

    public static UserProfile getUserProfile(){
        return new UserProfile();
    }

}
