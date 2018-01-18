package my1508d.myyuekao.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangguojian on 2017/12/13.
 */

public class Mapp extends Application{
    public static Ijiekou ijiekou;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.svipmovie.com/front/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
       ijiekou = retrofit.create(Ijiekou.class);
    }
}
