package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.PushDownSubListAdapter;
import com.fangzuo.assist.cloud.Beans.BackData;
import com.fangzuo.assist.cloud.Beans.CodeCheckBackDataBean;
import com.fangzuo.assist.cloud.Beans.CodeCheckBean;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Dao.BarCode;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.PushDownMain;
import com.fangzuo.assist.cloud.Dao.PushDownSub;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.T_Detail;
import com.fangzuo.assist.cloud.Dao.T_main;
import com.fangzuo.assist.cloud.Dao.Unit;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Service.DataService;
import com.fangzuo.assist.cloud.Utils.Asynchttp;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DataModel;
import com.fangzuo.assist.cloud.Utils.DoubleUtil;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.LocDataUtil;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.MediaPlayer;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.UpLoadModel;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.databinding.ActivityPdSaleOrder2SaleOutBinding;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.widget.SpinnerUnit;
import com.fangzuo.assist.cloud.widget.SpinnerWaveHouse;
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.fangzuo.greendao.gen.BarCodeDao;
import com.fangzuo.greendao.gen.ProductDao;
import com.fangzuo.greendao.gen.PushDownMainDao;
import com.fangzuo.greendao.gen.PushDownSubDao;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.loopj.android.http.AsyncHttpClient;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PdSaleOrder2SaleOutActivity extends BaseActivity {
    ActivityPdSaleOrder2SaleOutBinding binding;
    private int activity = Config.PdSaleOrder2SaleOutActivity;
    private int tag = 2;
    private long ordercode;
    private Product product;
    private Storage storage;
//    private WaveHouse waveHouse;
    private Unit unit;
    private PushDownSub pushDownSub;
    private List<PushDownSub> container;
    private PushDownMainDao pushDownMainDao;
    private PushDownSubDao pushDownSubDao;
    private ArrayList<String> fidcontainer;
    private PushDownSubListAdapter pushDownSubListAdapter;
    private String default_unitID;
    private ArrayList<String> fidc;
    private Org org;
    private CodeCheckBackDataBean codeCheckBackDataBean;
    protected boolean isOpenBatch=false;
    private List<String> listOrder;
    protected PushDownMain pushDownMain;
    private SearchBean.S2Product s2Product;//用于数据查找...
    private String autoAuxSing = "";
    private String autoActualModel = "";
    private String autoStorage = "";

    private String mainSaleDept = "";//表头带出
    private String mainSaleMan = "";//表头带出
    private String mainSaleOrg = "";//表头带出

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }
    @Override
    protected boolean isScan() {
        return true;
    }
    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.ScanResult:
                BarcodeResult res = (BarcodeResult) event.postEvent;
//                if (binding.cbScaning.isChecked()){
//                }else{
                    mCaptureManager.onPause();
                    binding.zxingBarcodeScanner.setVisibility(View.GONE);
//                }
                OnReceive(res.getResult().getText());
                Toast.showText(mContext, "扫描结果：" + res.getResult().getText());
                break;
            case EventBusInfoCode.Product:
                product = (Product) event.postEvent;
                Lg.e("获得物料信息：" + product.toString());
                binding.setProduct(product);
                dealProduct();
                break;
            case EventBusInfoCode.Upload_OK://回单成功
                BackData backData = (BackData)event.postEvent;
                if (backData.getResult().getResponseStatus().getIsSuccess()){
                    //获取生成的单号数据
                    for (int i = 0; i < backData.getResult().getResponseStatus().getSuccessEntitys().size(); i++) {
                        listOrder.add(backData.getResult().getResponseStatus().getSuccessEntitys().get(i).getNumber());
                    }
                    final List<T_main> mains = t_mainDao.queryBuilder().where(T_mainDao.Properties.Activity.eq(activity)).build().list();
                    for (int i = 0; i < mains.size(); i++) {
                        final int pos = i;
                        String reString = mains.get(i).FBillerID+"|"+listOrder.get(i)+"|"+mains.get(i).FOrderId+"|"+mains.get(i).IMIE;
                        App.getRService().doIOAction(WebApi.SaleOutUpload, reString, new MySubscribe<CommonResponse>() {
                            @Override
                            public void onNext(CommonResponse commonResponse) {
                                super.onNext(commonResponse);
                                if (!commonResponse.state)return;
                                t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
                                        T_DetailDao.Properties.Activity.eq(activity),
                                        T_DetailDao.Properties.FOrderId.eq(mains.get(pos).FOrderId)
                                ).build().list());
                                for (int i = 0; i < mains.size(); i++) {
                                    List<PushDownSub> pushDownSubs = pushDownSubDao.queryBuilder().where(
                                            PushDownSubDao.Properties.FBillNo.eq(mains.get(i).FBillNo)).build().list();
                                    pushDownSubDao.deleteInTx(pushDownSubs);
                                    List<PushDownMain> pushDownMains = pushDownMainDao.queryBuilder().where(
                                            PushDownMainDao.Properties.FBillNo.eq(mains.get(i).FBillNo)).build().list();
                                    pushDownMainDao.deleteInTx(pushDownMains);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }
                        });
                    }

//                    t_detailDao.deleteInTx(t_detailDao.queryBuilder().where(
//                            T_DetailDao.Properties.Activity.eq(activity)
//                    ).build().list());
//                    List<T_main> list = t_mainDao.queryBuilder().where(
//                            T_mainDao.Properties.Activity.eq(activity)
//                    ).build().list();
//                    for (int i = 0; i < list.size(); i++) {
//                        List<PushDownSub> pushDownSubs = pushDownSubDao.queryBuilder().where(
//                                PushDownSubDao.Properties.FBillNo.eq(list.get(i).FBillNo)).build().list();
//                        pushDownSubDao.deleteInTx(pushDownSubs);
//                        List<PushDownMain> pushDownMains = pushDownMainDao.queryBuilder().where(
//                                PushDownMainDao.Properties.FBillNo.eq(list.get(i).FBillNo)).build().list();
//                        pushDownMainDao.deleteInTx(pushDownMains);
//                    }
//                    t_mainDao.deleteInTx(list);
//            btnBackorder.setClickable(true);
                    LoadingUtil.dismiss();
                    Toast.showText(mContext, "上传成功");
                    MediaPlayer.getInstance(mContext).ok();
                    Bundle b = new Bundle();
                    b.putInt("123", tag);
                    startNewActivity(PushDownPagerActivity.class, 0, 0, true, b);
                }else{
                    LoadingUtil.dismiss();
                    List<BackData.ResultBean.ResponseStatusBean.ErrorsBean> errorsBeans = backData.getResult().getResponseStatus().getErrors();
                    StringBuilder builder =new StringBuilder();
                    for (BackData.ResultBean.ResponseStatusBean.ErrorsBean error :errorsBeans) {
                        builder.append(error.getFieldName()+"\n");
                        builder.append(error.getMessage() + "\n");
                    }
                    AlertDialog.Builder delete = new AlertDialog.Builder(mContext);
                    delete.setTitle("回单错误");
                    delete.setMessage(builder.toString());
                    delete.setPositiveButton("确定", null);
                    delete.create().show();
                }
                break;
            case EventBusInfoCode.Upload_Error://回单失败
                String error = (String) event.postEvent;
                Toast.showText(mContext, error);
//                btnBackorder.setClickable(true);
                LoadingUtil.dismiss();
                MediaPlayer.getInstance(mContext).error();
                break;
            case EventBusInfoCode.Code_Check://条码检测
                LoadingUtil.dismiss();
                codeCheckBackDataBean = (CodeCheckBackDataBean)event.postEvent;
                if (codeCheckBackDataBean.FTip.equals("OK")){
                    binding.edBatchNo.setText(codeCheckBackDataBean.FBatchNo);
                    binding.edNum.setText(codeCheckBackDataBean.FQty);
                    autoAuxSing = codeCheckBackDataBean.FAuxsign;
                    autoStorage = codeCheckBackDataBean.FStockID;
                    LoadingUtil.showDialog(mContext,"正在查找物料信息");
                    DataModel.getProductForNumber(codeCheckBackDataBean.FItemID,org);
                }else{
//                    ReSetScan(binding.cbScaning);
                    Toast.showText(mContext,codeCheckBackDataBean.FTip);
                }
                break;
            case EventBusInfoCode.Code_Only_Insert://写入条码唯一临时表
                codeCheckBackDataBean = (CodeCheckBackDataBean)event.postEvent;
                if (codeCheckBackDataBean.FTip.equals("OK")){
                    Addorder();
                }else{
                    LoadingUtil.dismiss();
//                    ReSetScan(binding.cbScaning);
                    Toast.showText(mContext,codeCheckBackDataBean.FTip);
                }
                break;
        }
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pd_sale_order2_sale_out);
        ButterKnife.bind(this);
        setDrawerLeftEdgeSize(this, binding.drawerLayout, 0.2f);//设置抽屉滑动响应范围
        //摄像头初始化
        mCaptureManager = new ScanManager(this, binding.zxingBarcodeScanner);
        mCaptureManager.initializeFromIntent(getIntent(), savedInstanceState);
    }

    @Override
    protected void initData() {
        s2Product = new SearchBean.S2Product();
        ordercode = CommonUtil.createOrderCode(this);
        binding.tvDate.setText(getTime(true));
        //第一个参数用于保存上一个值，第二个为自动跳转到该默认值
        binding.spOrgSend.setAutoSelection(getString(R.string.spOrgSend_so_pd), Hawk.get(Info.user_org, ""));
        binding.spDepartmentSend.setAuto(getString(R.string.spDepartmentSend_so_pd), "",org,activity);
        binding.spStoreman.setAuto(getString(R.string.spStoreman_so_pd), "",org);
        binding.spSaleman.setAuto(getString(R.string.spSaleman_so_pd), "",org);
        binding.spStorage.setAuto("", org);
//        binding.spOrgSend.setEnable(false);
        binding.spUnit.setAuto(mContext, "", SpinnerUnit.Id);
        container = new ArrayList<>();
        fidcontainer = getIntent().getExtras().getStringArrayList("fid");
        getList();
        List<PushDownMain> pushDownMains = pushDownMainDao.queryBuilder().where(
                PushDownMainDao.Properties.FBillNo.eq(fidcontainer.get(0))).build().list();
        if (pushDownMains.size() > 0) {
            Lg.e("表头：",pushDownMains.get(0));
            pushDownMain = pushDownMains.get(0);
            mainSaleDept=LocDataUtil.getDept(pushDownMain.FSaleDeptID).FNumber;
            mainSaleMan=LocDataUtil.getSaleMan(pushDownMain.FSaleManID).FNumber;
            mainSaleOrg=LocDataUtil.getOrg(pushDownMain.FSaleDeptID,"id").FNumber;

//            fwanglaiUnit = list1.get(0).FSupplyID;
//            employeeId = list1.get(0).FEmpID;
//            departmentId = list1.get(0).FDeptID;
//            Log.e("employeeId", employeeId == null ? "" : employeeId);
//            Log.e("departmentId", departmentId == null ? "" : departmentId);
//            billNo = list1.get(0).FBillNo;
        }else{
            Toast.showText(mContext,"表头数据获取失败");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    private void getList() {
        container.clear();
        pushDownSubDao = daoSession.getPushDownSubDao();
        pushDownMainDao = daoSession.getPushDownMainDao();
        for (int i = 0; i < fidcontainer.size(); i++) {
            List<PushDownSub> list = pushDownSubDao.queryBuilder().where(
                    PushDownSubDao.Properties.FBillNo.eq(fidcontainer.get(i))).build().list();
            container.addAll(list);
        }
        if (container.size() > 0) {
            pushDownSubListAdapter = new PushDownSubListAdapter(mContext, container);
            binding.lvPushsub.setAdapter(pushDownSubListAdapter);
            pushDownSubListAdapter.notifyDataSetChanged();
        } else {
            Toast.showText(mContext, getString(R.string.find_nothing));
        }
    }

    @Override
    protected void initListener() {
        binding.spOrgSend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                org = (Org) binding.spOrgSend.getAdapter().getItem(i);
                Lg.e("组织：",org);
                storage=null;
                binding.spDepartmentSend.setAuto(getString(R.string.spDepartmentSend_so_pd), "",org,activity);
                binding.spStoreman.setAuto(getString(R.string.spStoreman_so_pd), "",org);
                binding.spSaleman.setAuto(getString(R.string.spSaleman_so_pd), "",org);
                binding.spStorage.setAuto("", org);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.spStorage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                storage = (Storage) binding.spStorage.getAdapter().getItem(i);
                Lg.e("选中仓库：" + storage.toString());
//                waveHouse = null;
                binding.spWavehouse.setAuto(mContext, storage, "", SpinnerWaveHouse.ID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.spWavehouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                waveHouse = (WaveHouse) binding.spWavehouse.getAdapter().getItem(i);
//                Lg.e("选中仓位：" + waveHouse.toString());
                Lg.e("仓位变化");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.spUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                unit = (Unit) binding.spUnit.getAdapter().getItem(i);
                Lg.e("选中单位：" + gson.toJson(unit));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.lvPushsub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pushDownSub = (PushDownSub) pushDownSubListAdapter.getItem(i);
                Lg.e("点击明细:",pushDownSub);
                if (BasicShareUtil.getInstance(mContext).getIsOL()) {
                    s2Product.likeOr = pushDownSub.FMaterialID;
                    s2Product.FOrg = org==null?"":org.FOrgID;
                    Asynchttp.post(mContext, getBaseUrl() + WebApi.S2Product, gson.toJson(new SearchBean(SearchBean.product_for_id,gson.toJson(s2Product))), new Asynchttp.Response() {
                        @Override
                        public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
                            final DownloadReturnBean dBean = new Gson().fromJson(cBean.returnJson, DownloadReturnBean.class);
                            Log.e("product.size", dBean.products.size() + "");
                            if (dBean.products.size() > 0) {
                                product = dBean.products.get(0);
                                Log.e("product.size", product + "");
                                dealProduct();
                            }else{
                                Toast.showText(mContext,"找不到物料数据");
                            }
                        }

                        @Override
                        public void onFailed(String Msg, AsyncHttpClient client) {
                            Toast.showText(mContext, "列表物料:" + Msg);
                        }
                    });
                } else {
                    ProductDao productDao = daoSession.getProductDao();
                    List<Product> products = productDao.queryBuilder().where(ProductDao.Properties.FMaterialid.eq(pushDownSub.FMaterialID)).build().list();
                    if (products.size() > 0) {
                        product = products.get(0);
                        dealProduct();
                    }else{
                        Toast.showText(mContext,"找不到物料数据");
                    }
                }

            }
        });
    }
    String barcode="";
    @Override
    protected void OnReceive(String code) {
        barcode = code;
        LoadingUtil.showDialog(mContext,"正在检测条码...");
        //查询条码唯一表
        CodeCheckBean bean = new CodeCheckBean(code);
        DataModel.codeCheck(WebApi.CodeCheckForOut,gson.toJson(bean));
//        ScanBarCode(code);
    }

    private void ScanBarCode(String barcode) {
        product = null;
        if (BasicShareUtil.getInstance(mContext).getIsOL()) {
            Asynchttp.post(mContext, getBaseUrl() + WebApi.S2Product, gson.toJson(new SearchBean(SearchBean.product_for_barcode,barcode)), new Asynchttp.Response() {
                @Override
                public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
                    final DownloadReturnBean dBean = new Gson().fromJson(cBean.returnJson, DownloadReturnBean.class);
                    Log.e("product.size", dBean.products.size() + "");
                    if (dBean.products.size() > 0) {
                        product = dBean.products.get(0);
                        Log.e("product.size", product + "");
                        default_unitID = dBean.products.get(0).FProduceUnitID;
                        setProduct(product);
                    }
                }

                @Override
                public void onFailed(String Msg, AsyncHttpClient client) {
                    Toast.showText(mContext, "物料：" + Msg);
                }
            });
        } else {
            ProductDao productDao = daoSession.getProductDao();
            BarCodeDao barCodeDao = daoSession.getBarCodeDao();
            final List<BarCode> barCodes = barCodeDao.queryBuilder().where(BarCodeDao.Properties.FBarCode.eq(barcode)).build().list();
            if (barCodes.size() > 0) {
                List<Product> products = productDao.queryBuilder().where(ProductDao.Properties.FProduceUnitID.eq(barCodes.get(0).FItemID)).build().list();
                if (products != null && products.size() > 0) {
                    product = products.get(0);
                    default_unitID = barCodes.get(0).FUnitID;
                    setProduct(product);
                }
            } else {
                Toast.showText(mContext, "条码不存在");
                MediaPlayer.getInstance(mContext).error();
            }
        }

    }

    private void setProduct(Product product) {
        if (product != null) {
            boolean flag = true;
            for (int j = 0; j < pushDownSubListAdapter.getCount(); j++) {
                PushDownSub pushDownSub1 = (PushDownSub) pushDownSubListAdapter.getItem(j);
                if (product.FMaterialid.equals(pushDownSub1.FMaterialID)) {
                    if (MathUtil.toD(pushDownSub1.FQty) == MathUtil.toD(pushDownSub1.FQtying)) {
                        flag = true;
                        continue;
                    } else {
                        if (!"".equals(default_unitID)) {
                            if (default_unitID.equals(pushDownSub1.FUnitID)) {
                                flag = false;
                                binding.lvPushsub.setSelection(j);
                                binding.lvPushsub.performItemClick(binding.lvPushsub.getChildAt(j), j, binding.lvPushsub.getItemIdAtPosition(j));
                                break;
                            }
                        } else {
                            flag = false;
                            binding.lvPushsub.setSelection(j);
                            binding.lvPushsub.performItemClick(binding.lvPushsub.getChildAt(j), j, binding.lvPushsub.getItemIdAtPosition(j));
                            break;
                        }
                    }

                }
            }

            if (flag) {
                Toast.showText(mContext, getString(R.string.product_nothing));
                MediaPlayer.getInstance(mContext).error();

            }
        } else {
            Toast.showText(mContext, "列表中不存在商品");
        }
    }

    //处理物料数据
    private void dealProduct() {
        if (product == null) {
            return;
        }
        binding.setProduct(product);
        Lg.e("物料数据：",product);
        //带出物料的默认值
        binding.spUnit.setAuto(mContext, product.FPurchaseUnitID, SpinnerUnit.Id);
        binding.spStorage.setAuto(product.FStockID, org);
        if (CommonUtil.isOpen(product.FIsBatchManage)) {
            isOpenBatch = true;
        } else {
            binding.edBatchNo.setText("");
            isOpenBatch = false;
        }
        DataModel.getStoreNum(product,storage,binding.edBatchNo.getText().toString().trim(),mContext,binding.tvKucun);

//        binding.spAuxsign.getData(product.FMASTERID, autoAuxSing);
//        binding.spActualmodel.getData(product.FMASTERID, autoActualModel);

//        if (binding.isAutoAdd.isChecked()){
//            binding.edNum.setText("1");
//            Addorder();
//        }
    }

    //添加前检测
    private boolean checkBeforeAdd() {
        if (product == null){
            Toast.showText(mContext, "物料数据为空");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (isOpenBatch && binding.edBatchNo.getText().toString().trim().equals("")) {
            Toast.showText(mContext, "请输入批号信息");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }//--------------------------------------------------
        if (binding.edNum.getText().toString().trim().equals("") || "0".equals(binding.edNum.getText().toString())) {
            Toast.showText(mContext, "请输入数量");
            MediaPlayer.getInstance(mContext).error();
            return false;
        }
        if (MathUtil.toD(pushDownSub.FQty) < ((MathUtil.toD(binding.edNum.getText().toString())) + MathUtil.toD(pushDownSub.FQtying))) {
            MediaPlayer.getInstance(mContext).error();
            Toast.showText(mContext, "大兄弟,您的数量超过我的想象");
            return false;
        }

        LoadingUtil.showDialog(mContext, "正在添加...");
        //插入条码唯一临时表
        CodeCheckBean bean = new CodeCheckBean(barcode, ordercode + "",binding.edNum.getText().toString(), BasicShareUtil.getInstance(mContext).getIMIE());
        DataModel.codeOnlyInsert(WebApi.CodeCheckInsertForOut, gson.toJson(bean));

        //--------------------------------------------------
//        if ("".equals(binding.edClient.getText().toString())){
//            binding.drawerLayout.openDrawer(Gravity.RIGHT);
//            Toast.showText(mContext,"请选择客户");
//            MediaPlayer.getInstance(mContext).error();
//            return false;
//        }else if (null==client && !"".equals(binding.edClient.getText().toString())){//当未选中供应商，但输入框有数据时
//            List<Client> list = GreenDaoManager.getmInstance(mContext).getDaoSession().getClientDao().queryBuilder().where(
//                    ClientDao.Properties.FName.eq(binding.edClient.getText().toString())
//            ).build().list();
//            if (list.size()>0){
//                client = list.get(0);
//            }else{
//                Toast.showText(mContext,"无法找到相对应的供应商信息，请重试");
//                MediaPlayer.getInstance(mContext).error();
//                return false;
//            }
//        }//--------------------------------------------------

        return true;
    }

    //添加数据
    private void Addorder() {
        try {
            if (!checkBeforeAdd()) {
                return;
            }
            String num = binding.edNum.getText().toString();
            if (binding.ishebing.isChecked()) {
                Lg.e("合并");
                List<T_Detail> detailhebing = t_detailDao.queryBuilder().where(
                        T_DetailDao.Properties.Activity.eq(activity),
                        T_DetailDao.Properties.FOrderId.eq(ordercode),
                        T_DetailDao.Properties.FMaterialId.eq(product.FNumber),
                        T_DetailDao.Properties.FUnitID.eq(unit.FNumber),
                        T_DetailDao.Properties.FStorageId.eq(storage.FNumber),
                        T_DetailDao.Properties.FWaveHouseId.eq(binding.spWavehouse.getWaveHouseNumber()),
                        T_DetailDao.Properties.FBatch.eq(binding.edBatchNo.getText().toString())
                ).build().list();
                if (detailhebing.size() > 0) {
                    Lg.e("合并：" + detailhebing.size() + "--" + detailhebing.get(0).toString());
                    for (int i = 0; i < detailhebing.size(); i++) {
                        num = (MathUtil.toD(num) + MathUtil.toD(detailhebing.get(i).FRealQty)) + "";
                        t_detailDao.delete(detailhebing.get(i));
                    }
                }
            }
            t_mainDao.deleteInTx(t_mainDao.queryBuilder().where(
                    T_mainDao.Properties.FOrderId.eq(ordercode)
            ).build().list());
            String timesecond = getTimesecond();
            T_main main = new T_main();//--------------------------------------表头-----------------
            main.activity = activity;
            main.FBillerID = Hawk.get(Info.user_id,"");
//            main.FBarcode = barcode;
            main.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            main.FOrderId = ordercode;
            main.FSoorDerno = pushDownMain.FBillNo;
            main.FIndex = timesecond;
            main.FBillNo = pushDownMain.FBillNo;
            main.setData(Info.getType(activity), mainSaleOrg,org.FNumber);
            main.FDepartmentNumber = binding.spDepartmentSend.getDataNumber();
            main.FPurchaseDeptId =mainSaleDept;
            main.FPurchaserId = mainSaleMan;
            main.FStockerNumber = binding.spStoreman.getDataNumber();
            main.FDate = binding.tvDate.getText().toString();
            main.FCustomerID = pushDownMain.FSupplyID;
            long insert1 = t_mainDao.insert(main);

            T_Detail detail = new T_Detail();//--------------------------------明细-----------------
            detail.activity = activity;
            detail.FBillerID = Hawk.get(Info.user_id,"");
//            detail.FBarcode = barcode;
            detail.IMIE = BasicShareUtil.getInstance(mContext).getIMIE();
            detail.FOrderId = ordercode;
            detail.FIndex = timesecond;
            detail.FEntryID = pushDownSub.FEntryID;
            detail.FID = pushDownSub.FID;
            detail.FSOEntryId = pushDownSub.FEntryID;
            detail.FRemainInStockQty = pushDownSub.FQty;
            detail.FRealQty = num;
            detail.FIsFree = false;
            detail.FBatch = binding.edBatchNo.getText().toString();
            detail.setProduct(product);
            detail.setStorage(storage);
            detail.setWaveHouse(binding.spWavehouse.getwaveHouseObject());
            detail.setUnit(unit);
            long insert2 = t_detailDao.insert(detail);

            if (insert1 > 0 && insert2 > 0) {
                pushDownSub.FQtying = DoubleUtil.sum(MathUtil.toD(pushDownSub.FQtying),
                        (MathUtil.toD(binding.edNum.getText().toString()) )) + "";
                pushDownSubDao.update(pushDownSub);
                pushDownSubListAdapter.notifyDataSetChanged();
                Lg.e("成功添加：" + main.toString());
                Lg.e("成功添加：" + detail.toString());
                MediaPlayer.getInstance(mContext).ok();
                Toast.showText(mContext, "添加成功");
                resetAll();
            } else {
                MediaPlayer.getInstance(mContext).error();
                Toast.showText(mContext, "添加失败，请重试");
            }

        } catch (Exception e) {
            DataService.pushError(mContext, this.getClass().getSimpleName(), e);
        }

    }


    private void resetAll() {
//        ReSetScan(binding.cbScaning);
        barcode="";
        listOrder.clear();
        binding.edBatchNo.setText("");
        binding.edNum.setText("");
        product = null;
        pushDownSub = null;
        binding.setProduct(new Product());

    }

    @OnClick({R.id.btn_add, R.id.btn_scan,R.id.tv_date, R.id.btn_backorder, R.id.btn_checkorder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                checkBeforeAdd();
                break;
            case R.id.btn_scan:
                if (binding.zxingBarcodeScanner.getVisibility()==View.VISIBLE){
                    binding.zxingBarcodeScanner.setVisibility(View.GONE);
                }else{
                    mCaptureManager.onResume();
                    binding.zxingBarcodeScanner.setVisibility(View.VISIBLE);
                    mCaptureManager.decode();
                }
                break;
            case R.id.btn_backorder:
                UpLoadModel.action(mContext,activity);
                break;
            case R.id.btn_checkorder:
                Bundle bundle = new Bundle();
                bundle.putInt("activity", activity);
                startNewActivity(ReViewPDActivity.class,
                        R.anim.activity_fade_in, R.anim.activity_fade_out, false, bundle);
                break;
            case R.id.tv_date:
                datePicker(binding.tvDate);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (binding.zxingBarcodeScanner.getVisibility()==View.VISIBLE){
                binding.zxingBarcodeScanner.setVisibility(View.GONE);
            }else{
                Bundle b = new Bundle();
                b.putInt("123", tag);
                startNewActivity(PushDownPagerActivity.class, 0, 0, true, b);
                super.onBackPressed();
            }

        }
    }
}