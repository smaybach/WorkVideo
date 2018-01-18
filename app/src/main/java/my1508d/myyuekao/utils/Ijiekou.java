package my1508d.myyuekao.utils;

import io.reactivex.Observable;
import my1508d.myyuekao.bean.Bean;
import my1508d.myyuekao.bean.Xiang;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wangguojian on 2017/12/13.
 */
public interface Ijiekou {
//    http://api.svipmovie.com/front/homePageApi/homePage.do?mediaId=ff8080815a5f91db015a68a763b750d5
//    http://api.svipmovie.com/front/homePageApi/homePage.do
//    homePageApi
    @GET("homePageApi/homePage.do")
    Observable<Bean> gethome();
//    @POST("homePageApi/homePage.do")
//    Observable<Bean> getData();
    @GET("videoDetailApi/videoDetail.do")
    Observable<Xiang> getvideo(@Query("mediaId") String urlID);
}
