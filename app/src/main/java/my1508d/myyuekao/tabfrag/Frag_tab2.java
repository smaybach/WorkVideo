package my1508d.myyuekao.tabfrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import my1508d.myyuekao.R;

/**
 * Created by wangguojian on 2017/12/20.
 */

public class Frag_tab2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.tab_layout2,null);
        return view;
    }
}
