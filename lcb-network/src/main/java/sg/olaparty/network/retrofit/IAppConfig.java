package sg.olaparty.network.retrofit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;
/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/10 15:43
 * Time: 15:43
 * Description:
 */
public interface IAppConfig {
  String getApiBaseUrl();
  SSLSocketFactory getSSLSocketFactory();
  HostnameVerifier getHostnameVerifier();
  Interceptor[] getInterceptors();
}
