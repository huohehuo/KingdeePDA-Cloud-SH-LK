package com.fangzuo.assist.cloud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.Config;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NB on 2017/8/3.
 */

public class ReViewP14BoxAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    List<T_Detail> detail;
    private ViewHolder viewHolder;
    InnerClickListener mListener;
    List<Boolean> isCheck;

    public ReViewP14BoxAdapter(Context context, List<T_Detail> detail, List<Boolean> isCheck) {
        this.context = context;
        this.detail = detail;
        this.isCheck = isCheck;
    }

    @Override
    public int getCount() {
        return detail.size();
    }

    @Override
    public Object getItem(int i) {
        return detail.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_review_list_p1_4box, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvPihao.setText("物料名称:" + detail.get(i).FProductName);
        viewHolder.productname.setText("规格："+detail.get(i).model);
//        viewHolder.tvBoxcode.setText("箱码:" + (detail.get(i).FCfBoxCode==null?"":detail.get(i).FCfBoxCode));
//        viewHolder.tvSum.setText("PCS:" + detail.get(i).FCfQtySum);
//        viewHolder.tvM2.setText("M2:" + detail.get(i).FCfM2Sum);
        if (detail.get(i).activity == Config.BoxReBoxP1Activity){
            viewHolder.tvLenght.setText("库存数量:" + detail.get(i).FRealQty);
        }else{
            viewHolder.tvLenght.setText("已验数量:" + detail.get(i).FRealQty);
        }
//        viewHolder.tvThick.setText("厚:" + detail.get(i).FCfThick+"  MM");
        if (null==detail.get(i).FVolume){
            viewHolder.tvWide.setVisibility(View.GONE);
        }else{
            viewHolder.tvWide.setVisibility(View.VISIBLE);
            viewHolder.tvWide.setText("已验体积:" + detail.get(i).FVolume);
        }
//        if (detail.get(i).FIsInBox==1){
//            viewHolder.tvIsBox.setText("已装箱");
//            viewHolder.tvIsBox.setTextColor(viewHolder.red);
//        }else{
//            viewHolder.tvIsBox.setText("未装箱");
//            viewHolder.tvIsBox.setTextColor(viewHolder.black);
//        }

//            }

        viewHolder.delete.setOnClickListener(this);
        viewHolder.delete.setTag(i);
        viewHolder.fix.setOnClickListener(this);
        viewHolder.fix.setTag(i);
        viewHolder.cbIscheck.setChecked(isCheck.get(i));

        return view;
    }

    @Override
    public void onClick(View view) {
        mListener.InOnClick(view);
    }

    public void setInnerListener(InnerClickListener mListener) {
        this.mListener = mListener;
    }

    public interface InnerClickListener {
        void InOnClick(View v);
    }


    class ViewHolder {
        @BindView(R.id.cb_ischeck)
        CheckBox cbIscheck;
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.tv_pihao)
        TextView tvPihao;
        @BindView(R.id.tv_sum)
        TextView tvSum;
        @BindView(R.id.tv_m2)
        TextView tvM2;
        @BindView(R.id.tv_boxcode)
        TextView tvBoxcode;
        @BindView(R.id.tv_model)
        TextView tvLenght;
        @BindView(R.id.tv_model2)
        TextView tvWide;
        @BindView(R.id.tv_model3)
        TextView tvThick;
        @BindView(R.id.tv_isbox)
        TextView tvIsBox;

        @BindView(R.id.delete)
        Button delete;
        @BindView(R.id.fix)
        Button fix;
        @BindColor(R.color.red)int red;
        @BindColor(R.color.black)int black;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
