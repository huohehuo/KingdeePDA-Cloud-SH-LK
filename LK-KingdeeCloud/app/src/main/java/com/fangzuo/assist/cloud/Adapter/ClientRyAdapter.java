package com.fangzuo.assist.cloud.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Beans.PrintHistory;
import com.fangzuo.assist.cloud.Dao.Client;
import com.fangzuo.assist.cloud.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class ClientRyAdapter extends RecyclerArrayAdapter<Client> {
    public ClientRyAdapter(Context context) {
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
//    @Override
//    public int getViewType(int position) {
//        return Integer.valueOf(getAllData().get(position).getType());
//
//    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MarkHolder(parent);
//        if (viewType==2){
//            Log.e("holder","返回图文布局");
//            return new MarkHolder(parent);
//        }else{
//            Log.e("holder","返回--文字--布局");
//            return new MainHolderForTxt(parent);
//        }

    }


    class MarkHolder extends BaseViewHolder<Client> {

        private TextView huoquan;
        private TextView batch;
        private TextView name;
        private TextView model;
        private TextView num;
        private TextView num2;
        private TextView note;
        private TextView wavehouse;
        private TextView date;

        public MarkHolder(ViewGroup parent) {
            super(parent, R.layout.item_client_dlg);
            huoquan =   $(R.id.fID);
            batch =     $(R.id.fname);
//            name =      $(R.id.tv_name);
//            model =     $(R.id.tv_model);
//            num =       $(R.id.tv_num);
//            num2 =      $(R.id.tv_num2);
//            note =      $(R.id.tv_note);
//            wavehouse = $(R.id.tv_wavehouse);
//            date =      $(R.id.tv_date);

        }

        @Override
        public void setData(Client data) {
            super.setData(data);
             huoquan.setText(data.getFNumber());
             batch.setText(data.getFName());
//             name.setText(data.getFName());
//             model.setText(data.getFModel());
//             num.setText(data.getFNum()+"  "+data.getFUnit());//库存数量
//             if (null==data.getFNum2()){
//                 num2.setVisibility(View.GONE);
//             }else{
//                 num2.setVisibility(View.VISIBLE);
//                 num2.setText(data.getFNum2()+"  "+data.getFUnitAux());//基本数量
//             }
//             note.setText(data.getFNot());
//             wavehouse.setText(data.getFWaveHouse());
//             date.setText(data.getFDate());
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
