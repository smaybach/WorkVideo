package my1508d.myyuekao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import my1508d.myyuekao.R;
import my1508d.myyuekao.activity.PlayActivity;
import my1508d.myyuekao.adapter.MyAdapter;
import my1508d.myyuekao.bean.Bean;
import my1508d.myyuekao.bean.MessageBean;
import my1508d.myyuekao.bean.Xiang;
import my1508d.myyuekao.presenter.PresenterMain;
import my1508d.myyuekao.utils.Bann;
import my1508d.myyuekao.view.IViewModel;

/**
 * Created by wangguojian on 2017/12/14.
 */

public class Frag1 extends Fragment implements IViewModel{
    MyAdapter adapter;
    PresenterMain t;
    List<Bean.RetBean.ListBean.ChildListBean> listBeans = new ArrayList<Bean.RetBean.ListBean.ChildListBean>();
    Banner banner;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_frag1,null);
        banner = view.findViewById(R.id.banner);
        recyclerView = view.findViewById(R.id.recy);
        t = new PresenterMain(this);
        t.getData();

        return view;

    }


    @Override
    public void onBean(Bean bean) {

        for (int i = 0; i < bean.getRet().getList().size(); i++) {
            int len = bean.getRet().getList().get(i).getChildList().size();
            for (int j = 0; j < len; j++) {
                listBeans.add(bean.getRet().getList().get(i).getChildList().get(j));
            }
        }
        adapter = new MyAdapter(getActivity(),listBeans);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);
        final List<String> lists = new ArrayList<>();
        for (int i = 0; i < bean.getRet().getList().size(); i++) {
            lists.add(listBeans.get(i).getPic());
        }
        banner.setImageLoader(new Bann());
        banner.setImages(lists);
        banner.start();
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                EventBus.getDefault().postSticky(new MessageBean(listBeans.get(position).getTitle(),listBeans.get(position).getDataId()));
                startActivity(new Intent(getActivity(), PlayActivity.class));
            }
        });
    }

    @Override
    public void onVideo(Xiang video) {

    }


}
