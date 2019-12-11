package com.fangzuo.assist.cloud.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.DoubleUtil;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class PrintHistoryAdapter extends RecyclerArrayAdapter<PrintHistory> {
    public PrintHistoryAdapter(Context context) {
        super(context);
    }
    //
//    public MarkAdapter(Context context, MarkBean[] objects) {
//        super(context, objects);
//    }
//
//    public MarkAdapter(Context context, List<MarkBean> objects) {
//        super(context, objects);
//    }

    @Override
    public int getViewType(int position) {
        if (null==(getAllData().get(position).getFYmLenght())){//当原木长为空时，为一期项目布局
            return 1;
        }else{
            if ("33".equals(getAllData().get(position).getF_TypeID())){
                return 3;
            }else{
                return 2;
            }
        }
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==2){
            Lg.e("二期布局");
            return new MarkHolder4P2(parent);
        }else if (viewType == 3){
            Lg.e("二期布局");
            return new MarkHolder4P2_33(parent);
        }else{
            Lg.e("一期布局");
            return new MarkHolder(parent);
        }

    }

    /*一期布局*/
    class MarkHolder extends BaseViewHolder<PrintHistory> {

        private TextView huoquan;
        private TextView batch;
        private TextView name;
        private TextView model;
        private TextView num;
        private TextView num2;
        private TextView note;
        private TextView storage;
        private TextView wavehouse;
        private TextView date;

        public MarkHolder(ViewGroup parent) {
            super(parent, R.layout.item_print_history);
            huoquan =   $(R.id.tv_huoquan);
            batch =     $(R.id.tv_batch);
            name =      $(R.id.tv_name);
            model =     $(R.id.tv_model);
            num =       $(R.id.tv_num);
            num2 =      $(R.id.tv_num2);
            note =      $(R.id.tv_note);
            wavehouse = $(R.id.tv_wavehouse);
            date =      $(R.id.tv_date);
            storage =   $(R.id.tv_storage);

        }

        @Override
        public void setData(PrintHistory data) {
            super.setData(data);
             huoquan.setText(LocDataUtil.getRemark(data.getFHuoquan(),"number").FNote);
             batch.setText(data.getFBatch());
             name.setText(data.getFName());
             model.setText(data.getFModel());
             num.setText(data.getFNum()+"  "+data.getFUnit());//库存数量
             if (null==data.getFNum2()){
                 num2.setVisibility(View.INVISIBLE);
             }else{
                 num2.setVisibility(View.VISIBLE);
                 num2.setText(data.getFNum2()+"  "+data.getFUnitAux());//基本数量
             }
             note.setText(data.getFAuxSign());
             wavehouse.setText(data.getFWaveHouse());
             date.setText(data.getFDate());
             storage.setText(data.getFStorage());

        }
    }
    /*二期布局*/
    class MarkHolder4P2 extends BaseViewHolder<PrintHistory> {

        private TextView huoquan;
        private TextView batch;
        private TextView name;
        private TextView model;
        private TextView num;
        private TextView num2;
        private TextView note;
        private TextView wavehouse;
        private TextView date;
        private TextView tvLeftLenght;
        private TextView tvLeftDia;

        public MarkHolder4P2(ViewGroup parent) {
            super(parent, R.layout.item_print_history_p2);
            huoquan =   $(R.id.tv_huoquan);
            batch =     $(R.id.tv_batch);
            name =      $(R.id.tv_name);
            model =     $(R.id.tv_model);
            num =       $(R.id.tv_num);
            num2 =      $(R.id.tv_num2);
            note =      $(R.id.tv_note);
            wavehouse = $(R.id.tv_wavehouse);
            date =      $(R.id.tv_date);
            tvLeftDia =      $(R.id.tv_left_dia);
            tvLeftLenght =      $(R.id.tv_left_leng);

        }

        @Override
        public void setData(PrintHistory data) {
            super.setData(data);
            huoquan.setText(LocDataUtil.getRemark(data.getFHuoquan(),"number").FNote);
            batch.setText(data.getFBatch());
            name.setText(data.getFName());
            if ("0".equals(data.F_TypeID)){//----------------水版时
                tvLeftLenght.setText("测量宽:");
                tvLeftDia.setText("层数:");
                model.setText(MathUtil.Cut0(data.getFYmLenght())+"*"+MathUtil.Cut0(data.getFBWide())+"*"+MathUtil.Cut0(data.getFBThick()));
//            num.setText(data.getFNum()+"  "+data.getFUnit());//库存数量
                num.setText(data.getFWidth());//库存数量
                num2.setText(data.getFCeng());//库存数量
                note.setText(data.getFVolume());
                wavehouse.setText(data.getFWaveHouse());
                date.setText(data.getFDate());
            }else{//--------------------原木布局
//                if ("1".equals(data.F_TypeID)){//立方米时(基本不用)
//                    model.setText(data.getFModel());
////            num.setText(data.getFNum()+"  "+data.getFUnit());//库存数量
//                    num.setText(data.getFYmLenght()+"  "+data.getFUnit());//库存数量
//                    if (null==data.getFNum2()){
//                        num2.setVisibility(View.INVISIBLE);
//                    }else{
//                        num2.setVisibility(View.VISIBLE);
////                num2.setText(data.getFNum2()+"  "+data.getFUnitAux());//基本数量
//                        num2.setText(data.getFYmDiameter()+"  厘米(cm)");//基本数量
//                    }
//                    note.setText(data.getFVolume());
//                    wavehouse.setText(data.getFWaveHouse());
//                    date.setText(data.getFDate());
//                }else{//----------------------------英尺时
                    model.setText(data.getFModel());
//            num.setText(data.getFNum()+"  "+data.getFUnit());//库存数量
                    num.setText(MathUtil.Cut0(data.getFYmLenght())+"  ft");//库存数量
                    num2.setText(MathUtil.Cut0(data.getFYmDiameter())+"  in");//基本数量
//                    if (null==data.getFNum2()){
//                        num2.setVisibility(View.INVISIBLE);
//                    }else{
//                        num2.setVisibility(View.VISIBLE);
////                num2.setText(data.getFNum2()+"  "+data.getFUnitAux());//基本数量
//                        num2.setText(data.getFYmDiameter()+"  厘米(cm)");//基本数量
//                    }
                    String val =DoubleUtil.mul(MathUtil.toD(data.getFVolume()),200)+"";
                    note.setText(val.substring(0,val.indexOf("."))+"  bf");
                    wavehouse.setText(data.getFWaveHouse());
                    date.setText(data.getFDate());
//                }

            }






//            name.setTextColor(App.getInstance().getColor(R.color.black));
//        //3、为集合添加值
//            isClicks = new ArrayList<>();
//            for(int i = 0;i<PrintHistory.size();i++){
//                isClicks.add(false);
//            }
//            name.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    name.setTextColor(App.getInstance().getColor(R.color.cpb_blue));
//                }
//            });

//            num.setText(data.getFavour().get__op());

//            favour.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(App.getContext(), "喜欢+1", Toast.LENGTH_SHORT).show();
//                }
//            });

//            Glide.with(getContext())
//                    .load(data.getBg_pic())
//                    .placeholder(R.drawable.dog)
//                    .centerCrop()
//                    .into(img_bg);

        }
    }
    //二期单据33
    class MarkHolder4P2_33 extends BaseViewHolder<PrintHistory> {

        private TextView huoquan;
        private TextView batch;
        private TextView name;
        private TextView model;
        private TextView num;

        public MarkHolder4P2_33(ViewGroup parent) {
            super(parent, R.layout.item_print_history_p2_33);
            huoquan =   $(R.id.tv_huoquan);
            batch =     $(R.id.tv_batch);
            name =      $(R.id.tv_name);
            model =     $(R.id.tv_model);
            num =       $(R.id.tv_num);

        }

        @Override
        public void setData(PrintHistory data) {
            super.setData(data);
            huoquan.setText(LocDataUtil.getRemark(data.getFHuoquan(),"number").FNote);
            batch.setText(data.getFBatch());
            name.setText(data.getFName());
            model.setText(data.getFModel());
            num.setText(data.getFNum());



//            name.setTextColor(App.getInstance().getColor(R.color.black));
//        //3、为集合添加值
//            isClicks = new ArrayList<>();
//            for(int i = 0;i<PrintHistory.size();i++){
//                isClicks.add(false);
//            }
//            name.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    name.setTextColor(App.getInstance().getColor(R.color.cpb_blue));
//                }
//            });

//            num.setText(data.getFavour().get__op());

//            favour.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(App.getContext(), "喜欢+1", Toast.LENGTH_SHORT).show();
//                }
//            });

//            Glide.with(getContext())
//                    .load(data.getBg_pic())
//                    .placeholder(R.drawable.dog)
//                    .centerCrop()
//                    .into(img_bg);

        }
    }

//    //纯文字布局
//    class MainHolderForTxt extends BaseViewHolder<PlanBean> {
//
//        private TextView time;
//        private TextView eesay;
//        private ImageView favour;
//        private TextView num;
//        public MainHolderForTxt(ViewGroup parent) {
//            super(parent, R.layout.item_plan_for_txt);
//            time = $(R.id.tv_time);
//            eesay = $(R.id.tv_main_essay);
//            num = $(R.id.tv_favour);
//            favour = $(R.id.iv_favour);
//        }
//
//        @Override
//        public void setData(PlanBean data) {
//            super.setData(data);
//            eesay.setText(data.getEssay());
//            time.setText(data.getCreatedAt());
////            num.setText(data.getFavour().get__op());
//
//            favour.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(App.getContext(), "喜欢+1", Toast.LENGTH_SHORT).show();
//                }
//            });
//
////            Glide.with(getContext())
////                    .load(data.getPic())
////                    .placeholder(R.mipmap.ic_launcher)
////                    .centerCrop()
////                    .into(imageView);
//
//        }
//    }
}
