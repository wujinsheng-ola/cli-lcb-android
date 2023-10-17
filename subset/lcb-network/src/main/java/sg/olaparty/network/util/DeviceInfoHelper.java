package sg.olaparty.network.util;

import android.os.Build;
import android.text.TextUtils;

import com.airbnb.lottie.BuildConfig;
import com.salton123.app.BaseApplication;

import java.util.UUID;

/**
 * User: newSalton@outlook.com
 * Date: 2019/10/16 11:25
 * ModifyTime: 11:25
 * Description:
 */
public class DeviceInfoHelper {
    private static DeviceInfoHelper instance;
    private static final String SEPARATOR = "/";

    private DeviceInfoHelper() {

    }

    public static synchronized DeviceInfoHelper getInstance() {
        if (instance == null) {
            instance = new DeviceInfoHelper();
        }
        return instance;
    }

    /**
     * App版本
     */
    private String versionName;
    /**
     * 系统平台
     */
    public static final int SYSTEM_PLATFORM = 17;
    /**
     * 系统版本
     */
    private String systemVersion;
    /**
     * 手机型号
     */
    private String phoneModel;
    /**
     * wifi mac地址
     */
    private String macAddress;
    /**
     * 渠道号
     */
    private String channelId;
    /**
     * 子渠道号
     */
    private String subChannelId;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 屏幕高度
     */
    private int screenHeightPixels;
    /**
     * 屏幕宽度
     */
    private int screenWidthPixels;

    public String getVersionName() {
        if (TextUtils.isEmpty(versionName)) {
            versionName = BuildConfig.VERSION_NAME;
        }
        return versionName;
    }

    public int getSystemPlatform() {
        return SYSTEM_PLATFORM;
    }

    public String getSystemVersion() {
        if (TextUtils.isEmpty(systemVersion)) {
            systemVersion = Build.VERSION.RELEASE;
        }
        return systemVersion;
    }

    public String getPhoneModel() {
        if (TextUtils.isEmpty(phoneModel)) {
            phoneModel = Build.BRAND + "_" + Build.MODEL;
        }
        return phoneModel;
    }

    public String getMacAddress() {
        if (TextUtils.isEmpty(macAddress)) {
            macAddress = DeviceUtils.getLocalMacAddress(BaseApplication.sInstance);
        }
        return macAddress;
    }

    public String getPackageName() {
        if (TextUtils.isEmpty(packageName)) {
            packageName = BaseApplication.sInstance.getPackageName();
        }
        return packageName;
    }

    public int getScreenHeightPixels() {
        if (screenHeightPixels == 0) {
            screenHeightPixels = DeviceUtils.getScreenDisplayMetrics(BaseApplication.sInstance).heightPixels;
        }
        return screenHeightPixels;
    }

    public int getScreenWidthPixels() {
        if (screenWidthPixels == 0) {
            screenWidthPixels = DeviceUtils.getScreenDisplayMetrics(BaseApplication.sInstance).widthPixels;
        }
        return screenWidthPixels;
    }

    public String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    public int getNetworkType() {
        return DeviceUtils.getNetworkType(BaseApplication.sInstance);
    }

    /**
     * 获取ua
     *
     * @return ua
     */
    public String getUAInfo() {
        StringBuilder sb = new StringBuilder();
        String randomUUID = getRandomUUID();
        sb.append(getVersionName()).append(SEPARATOR)
                .append(getSystemPlatform()).append(SEPARATOR)
                .append(getSystemVersion()).append(SEPARATOR)
                .append(getPhoneModel()).append(SEPARATOR)
                .append(getMacAddress()).append(SEPARATOR)
                .append(getPackageName()).append(SEPARATOR)
                .append(getScreenHeightPixels()).append(SEPARATOR)
                .append(getScreenWidthPixels()).append(SEPARATOR)
                .append(randomUUID).append(SEPARATOR)
                .append(getNetworkType()).append(SEPARATOR);
        return sb.toString();
    }
}
