package com.fangzuo.assist.cloud.Activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.AdapterView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.ProductRyAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.ProductTreeBeanList;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.databinding.ActivityProductCheck4P2Binding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class ProductCheck4P2Activity extends BaseActivity implements ProductRyAdapter.OnItemClickListener {
    ActivityProductCheck4P2Binding binding;
    private String searchOrg;
    private int activity;
    private String searchString;
    private SearchBean.S2Product s2Product;//用于数据查找...
    private ProductRyAdapter productRyAdapter;
    private ArrayList<Product> products;
    private ArrayList<Product> productsForSearch;
    private ProductTreeBeanList.ProductTreeBean type1,type2,treeType,lv,ply,ht,wt;
    private String[] split;
    private final static String Sc_type1="Sc_type1";
    private final static String Sc_type2="Sc_type2";
    private final static String Sc_treeType="Sc_treeType";
    private final static String Sc_lv="Sc_lv";
    private final static String Sc_ply="Sc_ply";
    private final static String Sc_ht="Sc_ht";
    private final static String Sc_wt="Sc_wt";
    private boolean isFirstCheck=true;//用于检测是否是第一次进入，
    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_check4_p2);
        binding.toolbar.tvTitle.setText("物料选择");
        Intent in = getIntent();
        Bundle b = in.getExtras();
        searchString = b.getString("search", "");
        searchOrg = b.getString("org", "");
        activity = b.getInt("activity");
        s2Product = new SearchBean.S2Product();
        s2Product.FOrg = searchOrg;
        toFilter(activity);
    }

    @Override
    protected void initData() {
        products = new ArrayList<>();
        productsForSearch = new ArrayList<>();
        productRyAdapter = new ProductRyAdapter(this,products);
        binding.ryProductSearchList.setAdapter(productRyAdapter);
        binding.ryProductSearchList.setItemAnimator(new DefaultItemAnimator());
        binding.ryProductSearchList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        productRyAdapter.setOnItemClickListener(this);
        binding.spType1.setAutoSelection("0", Hawk.get(Sc_type1,""));
//        LoadingUtil.showDialog(mContext,"正在处理数据...");
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                checkSearch();
//            }
//        }, 500);


    }

    @Override
    protected void initListener() {
        //分类1
        binding.spType1.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                type1 = (ProductTreeBeanList.ProductTreeBean) binding.spType1.getAdapter().getItem(i);
                if ("".equals(type1.FNumber))clearOther();
                Hawk.put(Sc_type1,type1.FNumber);
                Lg.e("分类1",type1);
                binding.spType2.setAutoSelection(type1.FID,Hawk.get(Sc_type2,""));
//                if (!isFirstCheck)getProduct(type1.FNumber);
            }
        });
        //分类2
        binding.spType2.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                type2 = (ProductTreeBeanList.ProductTreeBean) binding.spType2.getAdapter().getItem(i);
                Hawk.put(Sc_type2,type2.FNumber);
                binding.spTreeType.clear();treeType=null;
                binding.spLv.clear();lv=null;
                binding.spPly.clear();ply=null;
                binding.spHt.clear();ht=null;
//                binding.spWt.clear();wt=null;
                binding.spTreeType.setAutoSelection(type2.FID,Hawk.get(Sc_treeType,""));
//                if (!isFirstCheck)getProduct(type2.FNumber);
            }
        });
        //树种
        binding.spTreeType.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                treeType = (ProductTreeBeanList.ProductTreeBean) binding.spTreeType.getAdapter().getItem(i);
                Hawk.put(Sc_treeType,treeType.FNumber);
                binding.spLv.clear();lv=null;
                binding.spPly.clear();ply=null;
                binding.spHt.clear();ht=null;
//                binding.spWt.clear();wt=null;
                binding.spLv.setAutoSelection(treeType.FID,Hawk.get(Sc_lv,""));
//                if (!isFirstCheck)getProduct(treeType.FNumber);
            }
        });
        //等级
        binding.spLv.setOnItemSelectedListener(new ItemListener() {
            @Override
            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
                lv = (ProductTreeBeanList.ProductTreeBean) binding.spLv.getAdapter().getItem(i);
                Hawk.put(Sc_lv,lv.FNumber);
                binding.spPly.clear();ply=null;
                binding.spHt.clear();ht=null;
                getProduct(lv.FNumber);//不再去获得厚度宽度和长度，直接取等级这一级的number进行获取物料并且默认获取第一个物料
//                binding.spWt.clear();wt=null;
//                binding.spPly.setAutoSelection(lv.FID,Hawk.get(Sc_ply,""),"");
//                if (!isFirstCheck)getProduct(lv.FNumber);
            }
        });
//        //厚度
//        binding.spPly.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                ply = (ProductTreeBeanList.ProductTreeBean) binding.spPly.getAdapter().getItem(i);
//                Hawk.put(Sc_ply,ply.FNumber);
//                binding.spHt.clear();ht=null;
////                binding.spWt.clear();wt=null;
//                binding.spHt.setAutoSelection(ply.FID,Hawk.get(Sc_ht,""),"");
////                if (!isFirstCheck)getProduct(ply.FNumber);
//            }
//        });
//        //宽度
//        binding.spHt.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                ht = (ProductTreeBeanList.ProductTreeBean) binding.spHt.getAdapter().getItem(i);
//                Hawk.put(Sc_ht,ht.FNumber);
//                binding.spWt.clear();wt=null;
//                Lg.e("选中ht",wt);
//                binding.spWt.setAutoSelection(ht.FID,Hawk.get(Sc_wt,""),"");
////                if (!isFirstCheck)
//                getProduct(ht.FNumber);
//            }
//        });
//        //规格
//        binding.spWt.setOnItemSelectedListener(new ItemListener() {
//            @Override
//            protected void ItemSelected(AdapterView<?> parent, View view, int i, long id) {
//                wt = (ProductTreeBeanList.ProductTreeBean) binding.spWt.getAdapter().getItem(i);
//                Hawk.put(Sc_wt,wt.FNumber);
//                Lg.e("选中wt",wt);
//                dealProduct(wt.FName);
//            }
//        });

        binding.btnCheck.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (products!=null&&products.size()>0){
                    Lg.e("products:"+products.size(),products.get(0));
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Product,products.get(0)));
                    finish();
                }else{
                    Toast.showText(mContext,"物料信息不完整，请重新选择");
                }
//                productRyAdapter.clear();
//                products.clear();
//                productsForSearch.clear();
//                LoadingUtil.showDialog(mContext,"正在查找...");
//                getProduct(checkProduct());
            }
        });
    }

    //查找物料
    private void getProduct(String number){
        Lg.e("getProduct:"+number);
        if ("".equals(number)){
            LoadingUtil.dismiss();
            return;
        }
        binding.spWt.clear();
        productRyAdapter.clear();
        products.clear();
        productsForSearch.clear();
        s2Product.likeOr = number;
        App.getRService().doIOAction(WebApi.ProductSearchForTree, gson.toJson(new SearchBean(SearchBean.product_for_like,gson.toJson(s2Product))), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                binding.pg.setVisibility(View.GONE);
                LoadingUtil.dismiss();
                if (!commonResponse.state)return;
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (dBean.products.size()>0){
//                    if (dBean.products.size()==1){
//                        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Product,dBean.products.get(0)));
//                        finish();
//                    }else{
                    Lg.e("得到物料"+dBean.products.size(),dBean.products);
//                    getWtData(dBean.products);
                    products.addAll(dBean.products);
                    productsForSearch.addAll(dBean.products);
//                    }

                }else{
                    binding.spWt.clear();
                    productRyAdapter.clear();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                binding.pg.setVisibility(View.GONE);
            }
        });
    }
    private void dealProduct(String string){
        List<Product> productList = new ArrayList<>();
//        productList.addAll(products);
        Lg.e("dealProduct:"+products.size(),string);
//        Lg.e("1111:",productList);
        if (!"".equals(string)){
            for (int i = 0; i < productsForSearch.size(); i++) {
//                    Lg.e("1111",productsForSearch.get(i));
                if (productsForSearch.get(i).FModel.contains("*")){
                    String cutModel=productsForSearch.get(i).FModel.substring(0,productsForSearch.get(i).FModel.indexOf("*"));
                    if (cutModel.equals(string)){
                        Lg.e("添加：",productsForSearch.get(i));
                        productList.add(productsForSearch.get(i));
                    }
                }else{
                    Lg.e("添加：",productsForSearch.get(i));
                    productList.add(productsForSearch.get(i));
                }
//                else{
//                    productTreeBean.FName=bean.FModel;
//                }
//                if (productsForSearch.get(i).FModel.startsWith(string)){
//                    Lg.e("包含：",productsForSearch.get(i));
//                    productList.add(productsForSearch.get(i));
//                }
            }
//            binding.tvDataNum.setText(productList.size()+"");
            productRyAdapter.addAll(productList);
        }else{
//            binding.tvDataNum.setText(productsForSearch.size()+"");
            productRyAdapter.addAll(productsForSearch);
        }
        productRyAdapter.notifyDataSetChanged();
    }

    //添加搜索到的物料规格型号到长度栏
    private void getWtData(List<Product> products){
        binding.spWt.addItems(products);
    }

//    //获取FNumber用于物料查询
//    private void checkSearch(){
//        if (!"".equals(Hawk.get(Sc_ht,""))){
//            getProduct(Hawk.get(Sc_ht,""));
//            isFirstCheck=false;Lg.e("取宽度：");
//            return;
//        }
//        if (!"".equals(Hawk.get(Sc_ply,""))){
//            getProduct(Hawk.get(Sc_ply,""));
//            isFirstCheck=false;Lg.e("取厚度：");
//            return;
//        }
//        if (!"".equals(Hawk.get(Sc_lv,""))){
//            getProduct(Hawk.get(Sc_lv,""));
//            isFirstCheck=false;Lg.e("取等级：");
//            return;
//        }
//        if (!"".equals(Hawk.get(Sc_treeType,""))){
//            getProduct(Hawk.get(Sc_treeType,""));
//            isFirstCheck=false;Lg.e("取树种：");
//            return;
//        }
//        if (!"".equals(Hawk.get(Sc_type2,""))){
//            getProduct(Hawk.get(Sc_type2,""));
//            isFirstCheck=false;Lg.e("取分类2：");
//            return;
//        }
//        if (!"".equals(Hawk.get(Sc_type1,""))){
//            getProduct(Hawk.get(Sc_type1,""));
//            isFirstCheck=false;Lg.e("取分类1：");
//            return;
//        }
//        isFirstCheck = false;Lg.e("取空：");
//    }
    //获取FNumber用于物料查询
//    private String checkProduct(){
////            if (wt!=null)return wt.FNumber;
//        if (ht!=null&&ply!=null&&!ply.FNumber.equals("")){
//            Lg.e("ht");
//            return ht.FNumber.equals("")?ply.FNumber:ht.FNumber;
//        }
//        if (ply!=null&&lv!=null&&!lv.FNumber.equals("")){
//            Lg.e("ply");
//            return ply.FNumber.equals("")?lv.FNumber:ply.FNumber;
//        }
//        if (lv!=null&&treeType!=null&&!treeType.FNumber.equals("")){
//            Lg.e("lv");
//            return lv.FNumber.equals("")?treeType.FNumber:lv.FNumber;
//        }
//        if (treeType!=null&&type2!=null&&!type2.FNumber.equals("")){
//            Lg.e("treeType");
//            return treeType.FNumber.equals("")?type2.FNumber:treeType.FNumber;
//        }
//        if (type2!=null&&type1!=null&&!type1.FNumber.equals("")){
//            Lg.e("type2");
//            return type2.FNumber.equals("")?type1.FNumber:type2.FNumber;
//        }
//        if (type1!=null){
//            Lg.e("type1");
//            return type1.FNumber;
//        }
//        return "";
//    }

    //清楚
    private void clearOther(){
        binding.spType2.clear();type2=null;
        binding.spTreeType.clear();treeType=null;
        binding.spLv.clear();lv=null;
        binding.spPly.clear();ply=null;
        binding.spHt.clear();ht=null;
        binding.spWt.clear();wt=null;
        Hawk.delete(Sc_wt);Hawk.delete(Sc_ht);Hawk.delete(Sc_ply);Hawk.delete(Sc_lv);Hawk.delete(Sc_treeType);Hawk.delete(Sc_type2);;Hawk.delete(Sc_type1);
    }

    //物料点击
    @Override
    public void onProductItemClick(View view, int position) {
        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Product,products.get(position)));
        finish();
    }

    private void getCodeCut(String code){
        split = code.split(".");
        Lg.e("编码截取："+code,split[0]);
    }

    //根据activity过滤是否物料（是否允许生产，是否允许采购等)
    private void toFilter(int activity){
        switch (activity){
            case Config.PdCgOrder2WgrkActivity://采购订单吓退外购入库
            case Config.PurchaseInStoreActivity://采购入库
                s2Product.FIsPurchase="1";
//                FIsPurchase="1";
                break;
            case Config.WorkOrgIn4P2Activity://产品入库
            case Config.ProductInStoreActivity://产品入库
            case Config.TbInActivity://挑板入库
            case Config.TbIn2Activity://挑板入库
            case Config.TbIn3Activity://挑板入库
            case Config.GbInActivity://改版入库
            case Config.DhInActivity://到货入库
            case Config.DhIn2Activity://到货入库
            case Config.SimpleInActivity://产品入库
                s2Product.FIsProduce="1";
//                FIsProduce="1";
                break;
            case Config.ShuiBanGet2Activity://生产领料
            case Config.WorkOrgGet4P2Activity://生产领料
            case Config.OutKilnGetActivity://生产领料
            case Config.ShuiBanGetActivity://生产领料
            case Config.ProductGet4P2Activity://生产领料
            case Config.ProductGet4P24PihaoActivity://生产领料
            case Config.ProductGetActivity://生产领料
            case Config.TbGetActivity://挑板领料
            case Config.TbGet2Activity://挑板领料
            case Config.TbGet3Activity://挑板领料
            case Config.GbGetActivity://改板领料
                s2Product.FIsInventory="1";
//                FIsInventory="1";
                break;
            case Config.SaleOutActivity://销售出库
                s2Product.FIsSale="1";
//                FIsSale="1";
                break;
            case Config.OtherInStoreActivity://其他入库
            case Config.HwIn3Activity://第三方货物入库
                s2Product.FIsInventory="1";
//                FIsInventory="1";
                break;
            case Config.SupplierGet4P2Activity://其他出库
            case Config.YbOut4P2Activity://其他出库
            case Config.OtherOutStoreActivity://其他出库
            case Config.YbOutActivity://样板出库
            case Config.HwOut3Activity://第三方货物出库
                s2Product.FIsInventory="1";
//                FIsInventory="1";
                break;
            case Config.SaleOrderActivity://销售订单
                s2Product.FIsSale="1";
//                FIsSale="1";
                break;
            case Config.PurchaseOrderActivity://采购订单
                s2Product.FIsPurchase="1";
//                FIsPurchase="1";
                break;
        }
    }

    @Override
    protected void OnReceive(String code) {

    }

    public static void start(Context context, int activity, String search, String org){
        Intent intent = new Intent(context,ProductCheck4P2Activity.class);
        intent.putExtra("search",search);
        intent.putExtra("org",org);
        intent.putExtra("activity",activity);
        context.startActivity(intent);
    }


}
