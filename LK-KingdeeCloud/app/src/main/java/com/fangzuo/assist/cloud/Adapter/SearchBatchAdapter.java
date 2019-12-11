package com.fangzuo.assist.cloud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Dao.BatchDataBean;
import com.fangzuo.assist.cloud.Dao.Client;
import com.fangzuo.assist.cloud.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NB on 2017/8/1.
 */

public class SearchBatchAdapter extends BaseAdapter {
    Context context;
    List<BatchDataBean> items;
    private ViewHolder viewHolder;

    public SearchBatchAdapter(Context context, List<BatchDataBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.autolayout_pihao, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvItem1.setText("名称:"+items.get(i).FName);
        viewHolder.tvItem2.setText("批次:"+items.get(i).FBatchNo);
        viewHolder.tvItem3.setText("长度:"+items.get(i).FLenght);
        viewHolder.tvItem4.setText("直径:"+items.get(i).FDiv);
//        viewHolder.tvItem5.setText("层数:"+items.get(i).FCeng);
        viewHolder.tvItem5.setText("数量:"+items.get(i).FQty);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_item1)
        TextView tvItem1;
        @BindView(R.id.tv_item2)
        TextView tvItem2;
        @BindView(R.id.tv_item3)
        TextView tvItem3;
        @BindView(R.id.tv_item4)
        TextView tvItem4;
        @BindView(R.id.tv_item5)
        TextView tvItem5;
        @BindView(R.id.tv_item6)
        TextView tvItem6;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
