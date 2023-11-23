package com.salton123.soulove;

/**
 * Author: Thomas.
 * <br/>Date: 2019/9/18 13:58
 * <br/>Email: 1071931588@qq.com
 * <br/>Description:App常量
 */
public interface Constants {
    interface Router {

        interface Live {
            String LIVE_ROOM = "/live/aty/live_room";
        }

        interface Profile {
            String EDIT = "/profile/aty/edit";
            String CONTACT_US = "/profile/aty/contact_us";
            String ACCOUNT_SETTING = "/profile/aty/account_setting";
        }
    }

    interface Provider {
        String LIVE_ROOM = "/provider/liveroom";
    }
}
