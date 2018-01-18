package my1508d.myyuekao.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import my1508d.myyuekao.R;
import my1508d.myyuekao.adapter.MyFragmentPagerAdapter;
import my1508d.myyuekao.bean.Bean;
import my1508d.myyuekao.bean.MessageBean;
import my1508d.myyuekao.bean.Xiang;
import my1508d.myyuekao.presenter.PresenterMain;
import my1508d.myyuekao.view.IViewModel;

public class PlayActivity extends AppCompatActivity implements IViewModel{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TextView xinxi;
    PresenterMain t;
    String urlID ="";
    JZVideoPlayerStandard jzVideoPlayerStandard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_play);
        EventBus.getDefault().register(this);
        xinxi = findViewById(R.id.xinxi);
        t = new PresenterMain(this);
        t.getVideo(urlID);
        //tab与viewpager与fragment
        initviews();
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
    @Subscribe(sticky = true)
    public void onMessage(MessageBean bean){
        urlID = bean.getUrl();
        Toast.makeText(this, ""+bean.getTitlte()+"===="+bean.getUrl(), Toast.LENGTH_SHORT).show();
        String ss = bean.getTitlte();
        xinxi.setText(ss);
        Log.i("urlid", "onMessage: "+ss);

    }
    @Override
    public void onBean(Bean bean) {

    }

    @Override
    public void onVideo(Xiang video) {
        String hdurl = video.getRet().getSmoothURL();
        Toast.makeText(this, ""+hdurl, Toast.LENGTH_SHORT).show();
        jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
        jzVideoPlayerStandard.setUp(hdurl
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "海量资源，免费看电影");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
    private void initviews(){
        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);
        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
    }
}
