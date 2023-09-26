package sg.olaparty.network.api;

import com.dragonplus.colorfever.api.TokenDataEncryptInterceptor;

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
public class TokenDefaultConfig implements IAppConfig {
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
        return new Interceptor[]{
                new TokenDataEncryptInterceptor()
        };
    }
}
