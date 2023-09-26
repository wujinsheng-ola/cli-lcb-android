package sg.olaparty.network.api;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;
import sg.olaparty.network.Constants;
import sg.olaparty.network.retrofit.IAppConfig;

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/10 15:43
 * Time: 15:43
 * Description:
 */
public class DefaultConfig implements IAppConfig {
    @Override
    public String getApiBaseUrl() {
        return Constants.getBaseUrl();
    }

    @Override
    public SSLSocketFactory getSSLSocketFactory() {
        return null;
    }

    @Override
    public HostnameVerifier getHostnameVerifier() {
        return null;
    }

    @Override
    public Interceptor[] getInterceptors() {
        // HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new Interceptor[]{
                new DataEncryptInterceptor()
                // ,interceptor
        };
    }
}
