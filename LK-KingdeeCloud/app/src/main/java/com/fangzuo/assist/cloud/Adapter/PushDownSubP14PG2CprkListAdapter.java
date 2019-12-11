package com.fangzuo.assist.cloud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Dao.PushDownSub;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.DoubleUtil;
import com.fangzuo.assist.cloud.Utils.MathUtil;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NB on 2017/8/25.
 */

public class PushDownSubP14PG2CprkListAdapter extends BaseAdapter {
    Context context;
    List<PushDownSub> items;
    private int changeColor=-1;
    public PushDownSubP14PG2CprkListAdapter(Context context, List<PushDownSub> items) {
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
            view = LayoutInflater.from(context).inflate(R.layout.pushdown_sub_p1_pg2cprk_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        DaoSession daoSession = GreenDaoManager.getmInstance(context).getDaoSession();
//        ProductDao productDao = daoSession.getProductDao();

//        Unit unit = LocDataUtil.getUnit(items.get(i).FUnitID);

//        List<Product> list = productDao.queryBuilder().where(ProductDao.Properties.FMaterialid.eq(items.get(i).FMaterialID)).build().list();
//        String productName;
//        if(list.size()>0){
//            productName = list.get(0).FName;
//        }else{
//            productName = items.get(i).FMaterialID;
//        }
//        if ((int) (Double.parseDouble(items.get(i).FQtying) / Double.parseDouble(items.get(i).FQty) * 100) == 100) {
//            view.setBackgroundColor(viewHolder.blue);
//        } else {
//            view.setBackgroundColor(viewHolder.white);
//        }

        viewHolder.productId.setText("物料名称:" + items.get(i).FName);
        viewHolder.tvModel.setText("规格:" + items.get(i).FModel);
        viewHolder.tvPCS.setText("PCS:" + items.get(i).FPCS);
        viewHolder.tvM3.setText("M3:" + items.get(i).FM3);
        viewHolder.tvLv.setText("等级:" + items.get(i).FLevel);
        viewHolder.tvWide.setText("宽度:" + items.get(i).FWide);
        if ("1".equals(items.get(i).FIsPrint)){
            viewHolder.llPrinted.setVisibility(View.VISIBLE);
        }else{
            viewHolder.llPrinted.setVisibility(View.GONE);
        }

        if(i==changeColor){
            view.setBackgroundColor(viewHolder.blue);
        }else{
            view.setBackgroundColor(viewHolder.white);
        }

        viewHolder.numyanshouing.setText("已验数量:" + MathUtil.toD(items.get(i).FQtying));
        viewHolder.tvLastnum.setText("未验数量:" + DoubleUtil.Cut4((MathUtil.toD(items.get(i).FPCS) - MathUtil.toD(items.get(i).FQtying)) + ""));
//        viewHolder.unit.setText("单位:" + unit.FName);
//        viewHolder.numyanshou.setText("订单数量:" + items.get(i).FQty);
        viewHolder.pg.setProgress((int) (MathUtil.toD(items.get(i).FQtying) / MathUtil.toD(items.get(i).FPCS) * 100));
        return view;
    }
    public void setChangeColor(int checkPosition){
        this.changeColor = checkPosition;
        notifyDataSetChanged();
    }


    static class ViewHolder {
        @BindView(R.id.ll_printed)
        LinearLayout llPrinted;
        @BindView(R.id.tv_lv)
        TextView tvLv;
        @BindView(R.id.tv_wide)
        TextView tvWide;
        @BindView(R.id.tv_pcs)
        TextView tvPCS;
        @BindView(R.id.tv_m3)
        TextView tvM3;
        @BindView(R.id.productId)
        TextView productId;
        @BindView(R.id.numyanshou)
        TextView numyanshou;
        @BindView(R.id.tv_model)
        TextView tvModel;
        @BindView(R.id.tv_lastnum)
        TextView tvLastnum;
        @BindView(R.id.numyanshouing)
        TextView numyanshouing;
        @BindView(R.id.progress)
        ProgressBar pg;
        @BindColor(R.color.cpb_blue)
        int blue;
        @BindColor(R.color.white)
        int white;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
