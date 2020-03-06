package com.fangzuo.assist.cloud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Dao.WaveHouse;
import com.fangzuo.assist.cloud.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NB on 2017/8/1.
 */

public class SearchWaveHouseAdapter extends BaseAdapter {
    Context context;
    List<WaveHouse> items;

    public SearchWaveHouseAdapter(Context context, List<WaveHouse> items) {
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
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.autolayout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.fID.setText(items.get(i).FNumber);
        viewHolder.fname.setText(items.get(i).FName);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.fID)
        TextView fID;
        @BindView(R.id.fname)
        TextView fname;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
