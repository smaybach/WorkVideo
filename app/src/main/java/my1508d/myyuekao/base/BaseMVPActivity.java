package my1508d.myyuekao.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseMVPActivity<V,T extends BasePresneter<V>> extends Fragment {
    public T t;
    public abstract T initpresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        t = initpresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        t.atacth((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        t.detach();
    }
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        t = initpresenter();
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        t.atacth((V) this);
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        t.detach();
//    }
}
