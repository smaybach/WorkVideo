package my1508d.myyuekao.tabfrag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import my1508d.myyuekao.R;
import my1508d.myyuekao.activity.JumpActivity;
import my1508d.myyuekao.adapter.MyAdapter2;
import my1508d.myyuekao.bean.Bean;
import my1508d.myyuekao.bean.MessageBean;
import my1508d.myyuekao.bean.Xiang;
import my1508d.myyuekao.presenter.PresenterMain;
import my1508d.myyuekao.view.IViewModel;

/**
 * Created by wangguojian on 2017/12/20.
 */

public class Frag_tab1 extends Fragment implements IViewModel{
    TextView tv1,tv2;
    RecyclerView recyclerView;
    View view;
    PresenterMain p;
    String uid = "";
    MyAdapter2 adapter2;
    List<Bean.RetBean.ListBean.ChildListBean> listBeans = new ArrayList<Bean.RetBean.ListBean.ChildListBean>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.tab_layout1,null);
        EventBus.getDefault().register(this);
        p = new PresenterMain(this);
        p.getVideo(uid);
        p.getData();
        tv1 =view.findViewById(R.id.daoyuan);
        tv2 =view.findViewById(R.id.zhuyan);
        recyclerView = view.findViewById(R.id.recylerview);
        return view;
    }
    @Subscribe(sticky = true)
    public void onMssds(MessageBean bean){
        uid = bean.getUrl();
    }
    @Override
    public void onBean(Bean bean) {
        for (int i = 0; i < bean.getRet().getList().size(); i++) {
            int len = bean.getRet().getList().get(i).getChildList().size();
            for (int j = 0; j < len; j++) {
                listBeans.add(bean.getRet().getList().get(i).getChildList().get(j));
            }
        }
        adapter2 = new MyAdapter2(getActivity(),listBeans);
        adapter2.setOnItemClickListener(new MyAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), JumpActivity.class));
            }
        });
        GridLayoutManager gl = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(gl);
        recyclerView.setAdapter(adapter2);
    }

    @Override
    public void onVideo(Xiang video) {
        Log.i("wafasdsadas", "onVideo: "+video.toString());
        tv1.setText("导演："+video.getRet().getDirector());
        tv2.setText("演员："+video.getRet().getActors());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
