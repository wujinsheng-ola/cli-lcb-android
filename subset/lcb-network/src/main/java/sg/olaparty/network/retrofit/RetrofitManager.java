package sg.olaparty.network.retrofit;


import com.google.protobuf.ExtensionRegistryLite;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.protobuf.ProtoConverterFactory;
import sg.olaparty.network.api.DefaultConfig;
import sg.olaparty.network.api.HttpEventListener;

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/10 15:43
 * Time: 15:43
 * Description:
 */
public class RetrofitManager {
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private Retrofit.Builder mBuilder;
    private IAppConfig mConfig;

    private static class Holder {
        private static final RetrofitManager sInstance = new RetrofitManager();
    }

    private RetrofitManager() {
        init();
    }

    private void init() {
        initConfig();
        initOkHttpClient();
        initRetrofit();
    }

    private void initConfig() {
        mConfig = new DefaultConfig();
    }

    private void initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient
                .Builder();
        builder.eventListenerFactory(HttpEventListener.FACTORY);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        // builder.retryOnConnectionFailure(true);//错误重连
        //无条件信任所有证书
        builder.hostnameVerifier((s, sslSession) -> true);
        if (mConfig.getInterceptors() != null) {
            for (Interceptor interceptor : mConfig.getInterceptors()) {
                builder.addInterceptor(interceptor);
            }
        }
        if (mConfig.getHostnameVerifier() != null) {
            builder.hostnameVerifier(mConfig.getHostnameVerifier());
        }
        if (mConfig.getSSLSocketFactory() != null) {
            builder.sslSocketFactory(mConfig.getSSLSocketFactory());
        }
        mOkHttpClient = builder.build();
    }

    public static Retrofit self() {
        return Holder.sInstance.mRetrofit;
    }

    private void initRetrofit() {
        mBuilder = new Retrofit.Builder();
        mBuilder.callFactory(mOkHttpClient)
                .baseUrl(mConfig.getApiBaseUrl());
        mBuilder.addConverterFactory(ProtoConverterFactory.createWithRegistry(ExtensionRegistryLite.newInstance()));
        mBuilder.addConverterFactory(GsonConverterFactory.create());
        mBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        mRetrofit = mBuilder.build();
    }
}
