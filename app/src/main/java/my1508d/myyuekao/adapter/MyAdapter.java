package my1508d.myyuekao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import my1508d.myyuekao.R;
import my1508d.myyuekao.bean.Bean;

/**
 * Created by wangguojian on 2017/12/14.
 */

public class MyAdapter extends RecyclerView.Adapter implements View.OnClickListener{

    Context context;
    List<Bean.RetBean.ListBean.ChildListBean> listBeans;
    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    public MyAdapter(Context context, List<Bean.RetBean.ListBean.ChildListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.adapter_item, null);
        MyHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder holder1 = (MyHolder) holder;
        holder1.simp.setImageURI(listBeans.get(position).getPic());
        holder1.text.setText(listBeans.get(position).getTitle());
        holder1.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (listBeans != null) {
            return listBeans.size();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.simp)
        SimpleDraweeView simp;
        @BindView(R.id.text)
        TextView text;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
