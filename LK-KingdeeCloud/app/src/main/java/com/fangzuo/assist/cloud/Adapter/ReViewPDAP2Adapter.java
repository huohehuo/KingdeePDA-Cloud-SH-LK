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
import com.fangzuo.assist.cloud.Utils.MathUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NB on 2017/8/3.
 */

public class ReViewPDAP2Adapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    List<T_Detail> detail;
    private ViewHolder viewHolder;
    InnerClickListener mListener;
    List<Boolean> isCheck;

    public ReViewPDAP2Adapter(Context context, List<T_Detail> detail, List<Boolean> isCheck) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_review_pd_p2_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        DaoSession daoSession = GreenDaoManager.getmInstance(context).getDaoSession();
//        T_mainDao t_mainDao = daoSession.getT_mainDao();
//        List<T_main> mains = t_mainDao.queryBuilder().where(
//                T_mainDao.Properties.FOrderId.eq(detail.get(i).FOrderId),
//                T_mainDao.Properties.Activity.eq(detail.get(i).activity)).build().list();


//            if(t_mains.get(0).activity== 1){
//                viewHolder.llWavehosue.setVisibility(View.VISIBLE);
//                viewHolder.productname.setText("单据编号:" + t_mains.get(0).orderId);
//                viewHolder.productId.setText("物料编码:" + detail.get(i).FProductCode);
//                viewHolder.productxh.setText("调入仓库:" + detail.get(i).FStorage);
//                viewHolder.num.setText("数量:" + detail.get(i).FQuantity+detail.get(i).FUnit);
//                viewHolder.price.setText("价格:" + detail.get(i).FTaxUnitPrice);
//                viewHolder.storage.setText("调出仓库:" + detail.get(i).FoutStoragename);
//                viewHolder.date.setText("物料名:" + detail.get(i).FProductName);

        viewHolder.productname.setText("物料名称:" + detail.get(i).FProductName);
        viewHolder.tvPihao.setText("批号:" + detail.get(i).FBatch);
        viewHolder.tvLv.setText("等级:" + detail.get(i).FLevel);
        viewHolder.tvLenght.setText("长度:" + detail.get(i).FYmLenght+"   ft");
        viewHolder.tvDia.setText("直径:" + detail.get(i).FYmDiameter+"    in");
        viewHolder.tvVol.setText("体积:" + MathUtil.Cut0((MathUtil.toD(detail.get(i).FVolume)*200)+"")+ "   bf");
//        if (mains.size()>0){
//            viewHolder.date.setText("入库日期:" + mains.get(0).FDate);
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
        @BindView(R.id.tv_vol)
        TextView tvVol;
        @BindView(R.id.cb_ischeck)
        CheckBox cbIscheck;
        List<T_Detail> detail;
        @BindView(R.id.productname)
        TextView productname;
        @BindView(R.id.tv_pihao)
        TextView tvPihao;
        @BindView(R.id.tv_lv)
        TextView tvLv;
        @BindView(R.id.tv_lenght)
        TextView tvLenght;
        @BindView(R.id.tv_dia)
        TextView tvDia;
//        @BindView(R.id.date)
//        TextView date;
        @BindView(R.id.delete)
        Button delete;
        @BindView(R.id.fix)
        Button fix;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
