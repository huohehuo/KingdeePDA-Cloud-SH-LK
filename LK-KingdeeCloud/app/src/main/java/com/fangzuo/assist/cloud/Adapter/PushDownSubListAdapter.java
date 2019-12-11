package com.fangzuo.assist.cloud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Dao.PushDownSub;
import com.fangzuo.assist.cloud.Dao.Unit;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.DoubleUtil;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.Utils.MathUtil;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NB on 2017/8/25.
 */

public class PushDownSubListAdapter extends BaseAdapter {
    Context context;
    List<PushDownSub> items;

    public PushDownSubListAdapter(Context context, List<PushDownSub> items) {
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
            view = LayoutInflater.from(context).inflate(R.layout.pushdown_sublist, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        DaoSession daoSession = GreenDaoManager.getmInstance(context).getDaoSession();
//        ProductDao productDao = daoSession.getProductDao();

        Unit unit = LocDataUtil.getUnit(items.get(i).FUnitID);

//        List<Product> list = productDao.queryBuilder().where(ProductDao.Properties.FMaterialid.eq(items.get(i).FMaterialID)).build().list();
//        String productName;
//        if(list.size()>0){
//            productName = list.get(0).FName;
//        }else{
//            productName = items.get(i).FMaterialID;
//        }
        if((int) (Double.parseDouble(items.get(i).FQtying)/Double.parseDouble(items.get(i).FQty)*100)==100){
            view.setBackgroundColor(viewHolder.blue);
        }else{
            view.setBackgroundColor(viewHolder.white);
        }

        viewHolder.billNo.setText("订单号:"+items.get(i).FBillNo);
        viewHolder.numyanshou.setText("订单数量:"+items.get(i).FQty);
        viewHolder.productId.setText("物料名称:"+ items.get(i).FName);
        viewHolder.tvModel.setText("规格型号:"+ items.get(i).FModel);
        viewHolder.numyanshouing.setText("已验数量:"+items.get(i).FQtying);
        viewHolder.tvLastnum.setText("未验数量:"+ DoubleUtil.Cut4((MathUtil.toD(items.get(i).FQty)-MathUtil.toD(items.get(i).FQtying))+""));
        viewHolder.unit.setText("单位:"+unit.FName);

        viewHolder.pg.setProgress((int) (Double.parseDouble(items.get(i).FQtying)/Double.parseDouble(items.get(i).FQty)*100));
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.billNo)
        TextView billNo;
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
        @BindView(R.id.unit)
        TextView unit;
        @BindView(R.id.progress)
        ProgressBar pg;
        @BindColor(R.color.cpb_blue)int blue;
        @BindColor(R.color.white)int white;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
