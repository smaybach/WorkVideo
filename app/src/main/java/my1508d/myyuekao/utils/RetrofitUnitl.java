package my1508d.myyuekao.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangguojian on 2017/12/10,0010.
 */

public class RetrofitUnitl {
    private Retrofit mRetrofit;
    private String baseUrl;
    OkHttpClient client;
    private static RetrofitUnitl mRetrofitManager;
    private RetrofitUnitl(String baseUrl,OkHttpClient client){
        this.baseUrl=baseUrl;
        this.client=client;
        initRetrofit();
    }
    public static synchronized RetrofitUnitl getInstance(String baseUrl, OkHttpClient client){
        if (mRetrofitManager == null){
            mRetrofitManager = new RetrofitUnitl(baseUrl,client);
        }
        return mRetrofitManager;
    }
    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }
    public <T> T setCreate(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }
}
