package sg.olaparty.network.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.IntDef;


import com.blankj.utilcode.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * User: newSalton@outlook.com
 * Date: 2019/10/16 11:26
 * ModifyTime: 11:26
 * Description:
 */
public class DeviceUtils {
    /**
     * 保存mac地址
     */
    private static final String PHONE_ID_ADDRESS = "phone_id_address";

    /**
     * 获取CPU信息.
     *
     * @return "CPU核心个数 x CPU频率"
     */
    public static String getCpuInfo() {
        return getCpuCoreCount() + " x " + getCpuFrequency();
    }

    /**
     * 获取CPU核心个数.
     *
     * @return
     */
    private static int getCpuCoreCount() {
        int coreCount = 1;
        try {
            String cpuDiePath = "/sys/devices/system/cpu";
            File dir = new File(cpuDiePath);
            String[] cpuFiles = dir.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return Pattern.matches("cpu\\d{1}", name);
                }
            });
            if (cpuFiles != null && cpuFiles.length > 0) {
                coreCount = cpuFiles.length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coreCount;
    }

    /**
     * 获取CPU频率.
     *
     * @return
     */
    private static String getCpuFrequency() {
        String cpuFreq = "";
        BufferedReader bufferedReader = null;
        try {
            String[] args = {"/system/bin/cat",
                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};
            ProcessBuilder cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            bufferedReader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            cpuFreq = bufferedReader.readLine();
            // convert from Kb to Gb
            float tempFreq = Float.valueOf(cpuFreq.trim());
            cpuFreq = tempFreq / (1000 * 1000) + "Gb";
            return cpuFreq;
        } catch (Exception e) {
            return StringUtils.isEmpty(cpuFreq) ? "N/A" : cpuFreq + "Kb";
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获得系统总内存大小.
     *
     * @param context
     * @return
     */
    public static String getSystemTotalMemory(Context context) {
        // 系统内存信息文件
        String memInfoFilePath = "/proc/meminfo";
        String firstLine;
        String[] arrayOfString;
        long initialMemory = 0;
        BufferedReader localBufferedReader = null;
        try {
            FileReader localFileReader = new FileReader(memInfoFilePath);
            localBufferedReader = new BufferedReader(
                    localFileReader, 10240);
            // 读取meminfo第一行, 系统总内存大小
            firstLine = localBufferedReader.readLine();
            arrayOfString = firstLine.split("\\s+");
            // 获得系统总内存, 单位是KB, 乘以1024转换为Byte
            initialMemory = Long.valueOf(arrayOfString[1].trim()) * 1024;
        } catch (Exception e) {
            e.printStackTrace();
            // ignore.
        } finally {
            if (localBufferedReader != null) {
                try {
                    localBufferedReader.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

        // 内存大小规格化, Byte转换为KB或者MB
        return Formatter.formatFileSize(context, initialMemory);
    }

    /**
     * 获取系统当前可用内存.
     *
     * @param context
     * @return
     */
    public static String getSystemAvailMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context
                .ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        // 内存大小规格化, Byte转换为KB或者MB
        return Formatter.formatFileSize(context, memoryInfo.availMem);
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    /**
     * 返回屏幕DisplayMetrics
     *
     * @return 当前屏幕DisplayMetrics
     */
    public static DisplayMetrics getScreenDisplayMetrics(Context context) {
        WindowManager mWm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        // 有时候密度出错，所以通过Dpi来判断
        mWm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * 返回屏幕Display
     *
     * @return 当前屏幕Display
     */
    public static Display getScreenDisplay(Context context) {
        WindowManager mWm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return mWm.getDefaultDisplay();
    }

    /**
     * 返回屏幕 密度
     *
     * @return 当前屏幕密度
     */
    public static float getScreenDensity(Context context) {
        // 有时候密度出错，所以通过Dpi来判断
        return getScreenDisplayMetrics(context).density;
    }


    public static String getLocalMacAddress(Context context) {
        String mac = null;
        try {
            WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wm.getConnectionInfo();
            mac = wifiInfo.getMacAddress();
            if (!TextUtils.equals(mac, "02:00:00:00:00:00")
                    && !TextUtils.equals(mac, "00:00:00:00:00:00")) {
                return mac;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (interfaces != null && interfaces.hasMoreElements()) {
            NetworkInterface iF = interfaces.nextElement();

            byte[] address = new byte[0];
            try {
                address = iF.getHardwareAddress();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            if (address == null || address.length == 0) {
                continue;
            }

            StringBuilder buf = new StringBuilder();
            for (byte b : address) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            mac = buf.toString();
            if ("wlan0".equals(iF.getName())) {
                return mac;
            }
        }
        return mac;
    }

    public static String getSerialNumber() {
        return Build.SERIAL;
    }

    /**
     * 获取 ANDROID_ID
     *
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        try {
            String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            if (TextUtils.isEmpty(android_id) || TextUtils.equals(android_id, "9774d56d682e549c")) {
                return null;
            }
            return android_id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final int NETWORK_TYPE_UNKNOWN = -1;
    public static final int NETWORK_TYPE_2G = 0;
    public static final int NETWORK_TYPE_3G = 1;
    public static final int NETWORK_TYPE_4G = 2;
    public static final int NETWORK_TYPE_WIFI = 3;

    @IntDef(value = {NETWORK_TYPE_UNKNOWN, NETWORK_TYPE_2G, NETWORK_TYPE_3G, NETWORK_TYPE_4G, NETWORK_TYPE_WIFI})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NetworkType {
    }

    /**
     * 网络(0:2G;1:3G;2:4G;3:WIFI;-1:未知)
     *
     * @return
     */
    public static int getNetworkType(NetworkInfo networkInfo) {
        int strNetworkType = NETWORK_TYPE_UNKNOWN;
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = NETWORK_TYPE_WIFI;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();

                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = NETWORK_TYPE_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = NETWORK_TYPE_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = NETWORK_TYPE_4G;
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = NETWORK_TYPE_4G;
                        } else if (_strSubTypeName.equals("TDS_HSDPA")) {
                            strNetworkType = NETWORK_TYPE_3G;
                        } else {
                            strNetworkType = NETWORK_TYPE_UNKNOWN;
                        }

                        break;
                }
            }
        }
        return strNetworkType;
    }

    /**
     * 网络(0:2G;1:3G;2:4G;3:WIFI;-1:未知)
     *
     * @param context
     * @return
     */
    public static int getNetworkType(Context context) {
        NetworkInfo networkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return NETWORK_TYPE_UNKNOWN;
        }
        networkInfo = connectivityManager.getActiveNetworkInfo();
        return getNetworkType(networkInfo);
    }

    /**
     * 网络状态是否为wifi
     *
     * @param context
     * @return
     */
    public static boolean isNetworkTypeWifi(Context context) {
        return NETWORK_TYPE_WIFI == getNetworkType(context);
    }

    /**
     * 网络是否可用
     *
     * @param context context
     * @return 网络是否可用
     */
    public static boolean isNetworkConnected(Context context) {
        NetworkInfo networkInfo;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * 获取ip地址
     *
     * @return ip地址
     */
    public static String getIPAddress() {
        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            if (nis == null) {
                return null;
            }
            InetAddress ia;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (!(ia instanceof Inet4Address) || ia.isLoopbackAddress()) {
                        continue;
                    }
                    hostIp = ia.getHostAddress();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Log.i("getIPAddress()", "ip:" + hostIp);
        return hostIp;
    }

}