package my1508d.myyuekao.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import my1508d.myyuekao.R;
import my1508d.myyuekao.fragment.Frag1;
import my1508d.myyuekao.fragment.Frag2;
import my1508d.myyuekao.fragment.Frag3;
import my1508d.myyuekao.fragment.Frag4;

public class MainActivity extends FragmentActivity {


    @BindView(R.id.frag)
    FrameLayout frag;
    @BindView(R.id.rbt1)
    RadioButton rbt1;
    @BindView(R.id.rbt2)
    RadioButton rbt2;
    @BindView(R.id.rbt3)
    RadioButton rbt3;
    @BindView(R.id.rbt4)
    RadioButton rbt4;
    @BindView(R.id.radio)
    RadioGroup radio;
    Frag1 frag1;
    Frag2 frag2;
    Frag3 frag3;
    Frag4 frag4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();
        frag4 = new Frag4();
        radio.check(R.id.rbt1);
        showFrag(R.id.frag,frag1,"frag1");
        Intent intent = new Intent();

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbt1:
                        showFrag(R.id.frag,frag1,"frag1");
                        break;
                    case R.id.rbt2:
                        showFrag(R.id.frag,frag2,"frag2");
                        break;
                    case R.id.rbt3:
                        showFrag(R.id.frag,frag3,"frag3");
                        break;
                    case R.id.rbt4:
                        showFrag(R.id.frag,frag4,"frag4");
                        break;
                        default:break;
                }
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    public void showFrag(int id, Fragment frag, String tag) {
        FragmentTransaction transactio = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            transactio.add(id, frag, tag);
        }
        List<Fragment> list = getSupportFragmentManager().getFragments();
        for (Fragment f : list) {
            transactio.hide(f);
        }
        transactio.show(frag);
        transactio.commit();
    }

}
