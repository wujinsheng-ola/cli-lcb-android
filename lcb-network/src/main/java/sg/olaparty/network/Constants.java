package sg.olaparty.network;

import android.os.Environment;

/**
 * User: newSalton@outlook.com
 * Date: 2019/8/12 17:13
 * ModifyTime: 17:13
 * Description:
 */
public class Constants {
    public static boolean isDevelop = true;
    public static final String TAG = "Constants";
    /**
     * DCIM路径
     */
    public static final String DCIM_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            .getAbsolutePath();
    /**
     * 相册保存路径
     */
    public static final String CAMERA_DIR = DCIM_DIR + "/Camera/";

    public static String getAppSecret() {
        if (isDevelop) {
            return "foobar";
        } else {
            return "049a9748867baa1d9a5db8cbff764db514eddf8a";
        }
    }

    public static String getBaseUrl() {
        if (isDevelop) {
            return "http://color-fever-api-beta.dragonplus.com";
        } else {
            return "https://anime-paint-api.dragonplus.com";
        }
    }
}
