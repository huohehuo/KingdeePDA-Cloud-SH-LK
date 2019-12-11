package com.fangzuo.assist.cloud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.MathUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NB on 2017/8/3.
 */

public class ReViewP2Adapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    List<T_Detail> detail;
    private ViewHolder viewHolder;
    InnerClickListener mListener;
    List<Boolean> isCheck;

    public ReViewP2Adapter(Context context, List<T_Detail> detail, List<Boolean> isCheck) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_review_list_p2, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (detail.get(i).activity==Config.DB4P2Activity ||
                detail.get(i).activity==Config.ShuiBanGetActivity||
                detail.get(i).activity==Config.OutKilnGetActivity||
                detail.get(i).activity==Config.ShuiBanGet2Activity ||
                detail.get(i).activity==Config.ShuiBanDB4P2Activity ||
                detail.get(i).activity==Config.DBInKiln4P2Activity){
            viewHolder.llOne.setVisibility(View.GONE);
            viewHolder.llTwo.setVisibility(View.VISIBLE);
            viewHolder.productname.setText("物料名称:" + detail.get(i).FProductName);
            viewHolder.tvPihao.setText("批号:" + detail.get(i).FBatch);
            viewHolder.tvModel.setText("规格:" + detail.get(i).model);
            viewHolder.tvVolTwo.setText("M3:    " + detail.get(i).FRealQty);
        }else{
            viewHolder.llOne.setVisibility(View.VISIBLE);
            viewHolder.llTwo.setVisibility(View.GONE);
            viewHolder.productname.setText("物料名称:" + detail.get(i).FProductName);
            viewHolder.tvPihao.setText("批号:" + detail.get(i).FBatch);
            viewHolder.tvLv.setText("等级:" + detail.get(i).FLevel);
            viewHolder.tvLenght.setText("长度:" + detail.get(i).FYmLenght+"   ft");
            viewHolder.tvDia.setText("直径:" + detail.get(i).FYmDiameter+"    in");
            viewHolder.tvVol.setText("英尺:" + MathUtil.Cut0((MathUtil.toD(detail.get(i).FVolume)*200)+"")+ "    bf");
//            viewHolder.tvVol.setText("体积:" + detail.get(i).FVolume+ "    m3");
        }


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
        @BindView(R.id.ll_one)
        LinearLayout llOne;
        @BindView(R.id.ll_two)
        LinearLayout llTwo;
        @BindView(R.id.tv_vol_two)
        TextView tvVolTwo;
        @BindView(R.id.tv_model)
        TextView tvModel;
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
