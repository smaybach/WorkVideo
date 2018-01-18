package my1508d.myyuekao.base;

/**
 * Created by wangguojian on 2017/12/14.
 */

public class BasePresneter<V> {

    public V view;
    public void atacth(V view){
        this.view = view;
    }
    public void detach(){
        this.view = null;
    }
}
