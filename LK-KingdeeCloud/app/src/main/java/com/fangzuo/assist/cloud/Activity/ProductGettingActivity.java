//package com.fangzuo.assist.cloud.Activity;
//
//import android.content.Intent;
//import android.databinding.DataBindingUtil;
//import android.os.Bundle;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.DividerItemDecoration;
//import android.view.View;
//
//import com.fangzuo.assist.cloud.ABase.BaseActivity;
//import com.fangzuo.assist.cloud.Activity.Crash.App;
//import com.fangzuo.assist.cloud.Adapter.ProductGettingRyAdapter;
//import com.fangzuo.assist.cloud.Adapter.ProductRyAdapter;
//import com.fangzuo.assist.cloud.Beans.CommonResponse;
//import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
//import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
//import com.fangzuo.assist.cloud.Beans.ProductTreeBeanList;
//import com.fangzuo.assist.cloud.Beans.SearchBean;
//import com.fangzuo.assist.cloud.Dao.Product;
//import com.fangzuo.assist.cloud.R;
//import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
//import com.fangzuo.assist.cloud.Utils.Config;
//import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
//import com.fangzuo.assist.cloud.Utils.EventBusUtil;
//import com.fangzuo.assist.cloud.Utils.Lg;
//import com.fangzuo.assist.cloud.Utils.WebApi;
//import com.fangzuo.assist.cloud.databinding.ActivityProductGettingBinding;
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//
////弃用
//public class ProductGettingActivity extends BaseActivity implements ProductGettingRyAdapter.OnItemClickListener,ProductRyAdapter.OnItemClickListener {
//
//    private ActivityProductGettingBinding binding;
//    private ProductGettingRyAdapter productGettingRyAdapter;
//    private ProductRyAdapter productRyAdapter;
//    private ArrayList<Product> products;
//    private ArrayList<ProductTreeBeanList.ProductTreeBean> container;
//    private ArrayList<ClickBean> clickBeans;
//    private ArrayList<String> strings;
//    private SearchBean.S2Product s2Product;//用于数据查找...
//    private String searchString;
//    private String searchOrg;
//    private int activity;
//    @Override
//    protected boolean isRegisterEventBus() {
//        return true;
//    }
//    @Override
//    protected void initView() {
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_getting);
//        binding.toolbar.tvTitle.setText("物料选择");
//        Intent in = getIntent();
//        Bundle b = in.getExtras();
//        searchString = b.getString("search", "");
//        searchOrg = b.getString("org", "");
//        activity = b.getInt("activity");
//        s2Product = new SearchBean.S2Product();
//        s2Product.FOrg = searchOrg;
//        toFilter(activity);
//    }
//
//    @Override
//    protected void initData() {
//        container = new ArrayList<>();
//        products = new ArrayList<>();
//        clickBeans = new ArrayList<>();
//        strings = new ArrayList<>();
//        productGettingRyAdapter = new ProductGettingRyAdapter(this,container);
//        productRyAdapter = new ProductRyAdapter(this,products);
//        binding.ryProductSearch.setAdapter(productGettingRyAdapter);
//        binding.ryProductSearchList.setAdapter(productRyAdapter);
//        binding.ryProductSearch.setItemAnimator(new DefaultItemAnimator());
//        binding.ryProductSearchList.setItemAnimator(new DefaultItemAnimator());
//        binding.ryProductSearch.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        binding.ryProductSearchList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        productGettingRyAdapter.setOnItemClickListener(this);
//        productRyAdapter.setOnItemClickListener(this);
//        //首次时，0为最外层列表
//        getTreeList("0");
//    }
//
//    @Override
//    protected void initListener() {
//        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //删除最后一个的FPv
//                if (clickBeans.size()>0){
//                    clickBeans.remove(clickBeans.size()-1);
//                }
//                //选择最后一个数据的FPv，并且更新列表
//                if (clickBeans.size()>0){
//                    getTreeList(clickBeans.get(clickBeans.size()-1).FID);
//                    getProduct(clickBeans.get(clickBeans.size()-1).Number);
//                    strings.remove(strings.size()-1);
//                    binding.tvShow.setText(strings.toString());
//                }else{
//                    strings.clear();
//                    binding.tvShow.setText("");
//                    getTreeList("0");
//                }
//            }
//        });
//    }
//
//    private void getTreeList(String string){
//        App.getRService().doIOAction(WebApi.ProductTreeGet, string, new MySubscribe<CommonResponse>() {
//            @Override
//            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
//                if (!commonResponse.state)return;
//                ProductTreeBeanList treeList = gson.fromJson(commonResponse.returnJson, ProductTreeBeanList.class);
//                productGettingRyAdapter.addAll(treeList.treeBeans);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//            }
//        });
//        if ("0".equals(string)){
//            getProduct("0");
//        }
//    }
//    private void getProduct(String number){
//        binding.pg.setVisibility(View.VISIBLE);
//        s2Product.likeOr = number;
//        App.getRService().doIOAction(WebApi.ProductSearchForTree, gson.toJson(new SearchBean(SearchBean.product_for_like,gson.toJson(s2Product))), new MySubscribe<CommonResponse>() {
//            @Override
//            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
//                binding.pg.setVisibility(View.GONE);
//                if (!commonResponse.state)return;
//                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
//                if (dBean.products.size()>0){
//                    products.addAll(dBean.products);
//                    productRyAdapter.addAll(dBean.products);
//                }else{
//                    productRyAdapter.clear();
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                binding.pg.setVisibility(View.GONE);
//            }
//        });
//    }
//
//
//    @Override
//    public void onItemClick(View view, int position) {
//        ProductTreeBeanList.ProductTreeBean bean = container.get(position);
//        clickBeans.add(new ClickBean(bean.FName,bean.FNumber,bean.FID));
//        strings.add(bean.FName+"-");
//        binding.tvShow.setText(strings.toString());
//        Lg.e("点击：",bean);
//        getTreeList(bean.FID);
//        getProduct(bean.FNumber);
//    }
//
//    @Override
//    public void onProductItemClick(View view, int position) {
//        EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Product,products.get(position)));
//        finish();
//    }
//
//    //用于选择物料组时的bean保存
//    public class ClickBean{
//        public String Name;
//        public String Number;
//        public String FID;
//        public ClickBean(String Name,String Number,String FPv){
//            this.Name = Name;
//            this.Number = Number;
//            this.FID = FPv;
//        }
//    }
//
//
//
//
//
//    //根据activity过滤是否物料（是否允许生产，是否允许采购等)
//    private void toFilter(int activity){
//        switch (activity){
//            case Config.PurchaseInStoreActivity://采购入库
//                s2Product.FIsPurchase="1";
////                FIsPurchase="1";
//                break;
//            case Config.ProductInStoreActivity://产品入库
//            case Config.TbInActivity://产品入库
//            case Config.DgInActivity://产品入库
//            case Config.SimpleInActivity://产品入库
//                s2Product.FIsProduce="1";
////                FIsProduce="1";
//                break;
//            case Config.ProductGetActivity://生产领料
//                s2Product.FIsInventory="1";
////                FIsInventory="1";
//                break;
//            case Config.SaleOutActivity://销售出库
//                s2Product.FIsSale="1";
////                FIsSale="1";
//                break;
//            case Config.OtherInStoreActivity://其他入库
//                s2Product.FIsInventory="1";
////                FIsInventory="1";
//                break;
//            case Config.OtherOutStoreActivity://其他出库
//                s2Product.FIsInventory="1";
////                FIsInventory="1";
//                break;
//            case Config.SaleOrderActivity://销售订单
//                s2Product.FIsSale="1";
////                FIsSale="1";
//                break;
//            case Config.PurchaseOrderActivity://采购订单
//                s2Product.FIsPurchase="1";
////                FIsPurchase="1";
//                break;
//        }
//    }
//    @Override
//    protected void OnReceive(String code) {
//
//    }
//
//}
