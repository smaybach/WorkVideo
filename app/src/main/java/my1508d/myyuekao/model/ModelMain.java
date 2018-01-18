package my1508d.myyuekao.model;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import my1508d.myyuekao.bean.Bean;
import my1508d.myyuekao.bean.Xiang;
import my1508d.myyuekao.utils.Mapp;

/**
 * Created by wangguojian on 2017/12/13.
 */

public class ModelMain {

    public void getData(final IModelMain modelMain){
        Observable<Bean> call = Mapp.ijiekou.gethome();
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bean>() {
                    @Override
                    public void accept(Bean bean) throws Exception {
                        modelMain.onBean(bean);
                    }
                });
    }
    public void getVideo(final IModelMain modelMain,String urlID){
        Observable<Xiang> call = Mapp.ijiekou.getvideo(urlID);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Xiang>() {
                    @Override
                    public void accept(Xiang video) throws Exception {
                        modelMain.onVideo(video);
                    }
                });
    }

}
