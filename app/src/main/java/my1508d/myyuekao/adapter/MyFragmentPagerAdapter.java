package my1508d.myyuekao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import my1508d.myyuekao.tabfrag.Frag_tab1;
import my1508d.myyuekao.tabfrag.Frag_tab2;

/**
 * Created by wangguojian on 2017/12/20.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"简历","评论"};
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==1){
            return new Frag_tab2();
        }
        return new Frag_tab1();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
