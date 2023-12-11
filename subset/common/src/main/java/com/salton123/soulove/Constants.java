package com.salton123.soulove;

/**
 * Author: Thomas.
 * <br/>Date: 2019/9/18 13:58
 * <br/>Email: 1071931588@qq.com
 * <br/>Description:App常量
 */
public interface Constants {
    interface Router {
        interface App {
            String HOME = "/app/aty/home";
            String DEV = "/app/aty/dev";
        }

        interface Live {
            String LIVE_ROOM = "/live/aty/live_room";
            //主播 观众端分开
            String LIVE_HOST_ROOM = "/live/aty/live_host_room";

        }

        interface Profile {
            String EDIT = "/profile/aty/edit";
            String CONTACT_US = "/profile/aty/contact_us";
            String WEB = "/profile/aty/web";
            String ACCOUNT_SETTING = "/profile/aty/account_setting";
            String LOGIN = "/profile/aty/login";
        }
    }

    interface Provider {
        String LIVE_ROOM = "/provider/liveroom";
    }
}
