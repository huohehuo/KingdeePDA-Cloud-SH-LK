package com.fangzuo.assist.cloud.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Beans.ProductTreeBeanList;
import com.fangzuo.assist.cloud.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王璐阳 on 2018/3/27.
 */
//代码，名称，规格，总库存，单位
public class ProductGettingRyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    public ArrayList<ProductTreeBeanList.ProductTreeBean> items;
    private RvInnerClickListener mListener;
    //1、定义一个集合，用来记录选中
    Context context;
    private OnItemClickListener onItemClickListener;
    public ProductGettingRyAdapter(Context context, ArrayList<ProductTreeBeanList.ProductTreeBean> items) {
        this.items = items;
        this.context = context;

    }

    public void addAll(List<ProductTreeBeanList.ProductTreeBean> itemss) {
        items.clear();
        items.addAll(itemss);
        notifyDataSetChanged();
    }
    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }
    public ArrayList<ProductTreeBeanList.ProductTreeBean> getAllData(){
        return items;
    }

//    @Override
//    public int getItemViewType(int position) {
//        return Integer.valueOf(items.get(position).getBackDateType());
//
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder holder = null;
//        if(1 == viewType){
            View v = mInflater.inflate(R.layout.item_product_getting,parent,false);
            holder = new MarkHolder(v);
//        }else if (2 == viewType){
//            View v = mInflater.inflate(R.layout.item_account_check_b,parent,false);
//            holder = new MainHolderTwo(v);
//        }else{
//            View v = mInflater.inflate(R.layout.item_account_check_c,parent,false);
//            holder = new MainHolderThree(v);
//        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int pos;
            ((MarkHolder) holder).name.setText(   items.get(position).FName);

//            if (Double.parseDouble(items.get(position).getFLastMoney())>300000.00){
//                ((MainHolderThree) holder).FLastMoney.setTextColor(Color.RED);
//            }else{
//                ((MainHolderThree) holder).FLastMoney.setTextColor(Color.BLACK);
//            }
//        ((MarkHolder) holder).cb_ischeck.setChecked(false);
            ((MarkHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = ((MarkHolder) holder).getLayoutPosition();
//                    if (pos==position){
//                        ((MarkHolder) holder).name.setTextColor(Color.BLUE);
//                        ((MarkHolder) holder).cb_ischeck.setChecked(true);
//
//                    }else{
//                        ((MarkHolder) holder).name.setTextColor(Color.BLACK);
//                        ((MarkHolder) holder).cb_ischeck.setChecked(false);
//                    }
                    onItemClickListener.onItemClick(((MarkHolder) holder).itemView, pos);
                }
            });

    }

    public ProductTreeBeanList.ProductTreeBean getItems(int position) {
        return items.get(position);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public void onClick(View view) {
        mListener.InnerItemOnClick(view);
    }

    public interface RvInnerClickListener {
        void InnerItemOnClick(View v);
    }

    public void setInnerClickListener(RvInnerClickListener mListener) {
        this.mListener = mListener;
    }

    public void addNewItem(ProductTreeBeanList.ProductTreeBean temp) {
        if (items != null) {
            items.add(temp);
            notifyItemInserted(items.size() + 1);
        }
    }

    public void deleteItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(ProductGettingRyAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    class MarkHolder  extends RecyclerView.ViewHolder {
        private TextView name;


        public MarkHolder(View itemView) {
            super(itemView);
            name        = (TextView) itemView.findViewById(R.id.tv_name);

        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

//        void onItemLongClick(View view, int position);
    }
}
