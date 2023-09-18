package com.azhu.apocalypse;

import com.azhu.apocalypse.user.UserProfile;

public class DomainFactory {

    public static UserProfile getUserProfile(){
        return new UserProfile();
    }

}
