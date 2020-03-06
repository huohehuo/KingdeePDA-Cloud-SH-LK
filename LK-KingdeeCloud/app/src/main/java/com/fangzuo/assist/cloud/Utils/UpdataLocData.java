package com.fangzuo.assist.cloud.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.InStoreNumBean;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.greendao.gen.BuyerDao;
import com.fangzuo.greendao.gen.ClientDao;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.DepartmentDao;
import com.fangzuo.greendao.gen.EmployeeDao;
import com.fangzuo.greendao.gen.InStorageNumDao;
import com.fangzuo.greendao.gen.OrgDao;
import com.fangzuo.greendao.gen.ProductDao;
import com.fangzuo.greendao.gen.SaleManDao;
import com.fangzuo.greendao.gen.StorageDao;
import com.fangzuo.greendao.gen.StoreManDao;
import com.fangzuo.greendao.gen.SuppliersDao;
import com.fangzuo.greendao.gen.UnitDao;
import com.fangzuo.greendao.gen.UserDao;
import com.fangzuo.greendao.gen.WaveHouseDao;
import com.google.gson.Gson;

import org.greenrobot.greendao.async.AsyncSession;

import java.util.ArrayList;

/**
 * Created by 王璐阳 on 2018/1/2.
 */

public class UpdataLocData {
//    private  ProgressDialog pg;
    private  BasicShareUtil share;
    private Context mContext;
    private Handler handler;
    private DaoSession session;
    private View container;
    private long nowTime;
    private int size;

    public UpdataLocData(Context mContext) {
        this.mContext = mContext;
        share = BasicShareUtil.getInstance(mContext);
        session = GreenDaoManager.getmInstance(mContext).getDaoSession();
    }

    public static UpdataLocData getInstance(Context context){
        return new UpdataLocData(context);
    }
    public  void alertToChoose() {
        AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
        ab.setTitle("请选择要下载的内容");
        View v = LayoutInflater.from(mContext).inflate(R.layout.selectdownload, null);
        final ArrayList<CheckBox> cbList = new ArrayList<>();
        cbList.add((CheckBox)v.findViewById(R.id.cb1));
        cbList.add((CheckBox)v.findViewById(R.id.cb2));
        cbList.add((CheckBox)v.findViewById(R.id.cb3));
        cbList.add((CheckBox)v.findViewById(R.id.cb4));
        cbList.add((CheckBox)v.findViewById(R.id.cb5));
        cbList.add((CheckBox)v.findViewById(R.id.cb6));
        cbList.add((CheckBox)v.findViewById(R.id.cb7));
        cbList.add((CheckBox)v.findViewById(R.id.cb8));
        cbList.add((CheckBox)v.findViewById(R.id.cb9));
        cbList.add((CheckBox)v.findViewById(R.id.cb10));
        cbList.add((CheckBox)v.findViewById(R.id.cb11));
        cbList.add((CheckBox)v.findViewById(R.id.cb12));
        cbList.add((CheckBox)v.findViewById(R.id.cb13));
        cbList.add((CheckBox)v.findViewById(R.id.cb14));
//        cbList.add((CheckBox)v.findViewById(R.id.cb15));
//        cbList.add((CheckBox)v.findViewById(R.id.cb16));
//        cbList.add((CheckBox)v.findViewById(R.id.cb17));
//        cbList.add((CheckBox)v.findViewById(R.id.cb18));
//        cbList.add((CheckBox)v.findViewById(R.id.cb19));
        ab.setView(v);
        ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList<Integer> choose = new ArrayList<>();
                for (CheckBox checkbox : cbList) {
                    if (checkbox.isChecked()) {
                        Log.e("选择的是", checkbox.getText().toString());
                        switch (checkbox.getText().toString()) {
                            case "采购员表":
                                choose.add(1);
                                break;
                            case "部门表":
                                choose.add(2);
                                break;
                            case "职员表":
                                choose.add(3);
                                break;
                            case "仓位表":
                                choose.add(4);
                                break;
                            case "库存表":
                                choose.add(5);
                                break;
                            case "仓库表":
                                choose.add(6);
                                break;
                            case "单位表":
                                choose.add(7);
                                break;
                            case "仓管员表":
                                choose.add(8);
                                break;
                            case "供应商表":
                                choose.add(9);
                                break;
                            case "销售员表":
                                choose.add(10);
                                break;
                            case "商品资料表":
                                choose.add(11);
                                break;
                            case "用户信息表":
                                choose.add(12);
                                break;
                            case "客户信息表":
                                choose.add(13);
                                break;
                            case "组织表":
                                choose.add(14);
                                break;

//                            case "销售/采购方式表":
//                                choose.add(15);
//                                break;
//                            case "源单类型":
//                                choose.add(16);
//                                break;
//                            case "往来科目":
//                                choose.add(17);
//                                break;
//                            case "价格政策":
//                                choose.add(18);
//                                break;
//                            case "入库类型":
//                                choose.add(19);
//                                break;
                        }
                    }
                }
                downloadData(choose);
            }
        });
        ab.setNeutralButton("下载全部", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList<Integer> chooseAll = new ArrayList<>();
                for (int j = 1; j <= cbList.size() ; j++) {
                    chooseAll.add(j);
                }
                downloadData(chooseAll);
            }
        });
        ab.setNegativeButton("取消", null);
        ab.create().show();
    }
    private void downloadData(final ArrayList<Integer> choose) {
        LoadingUtil.show(mContext,"正在下载...");
        String json = JsonCreater.DownLoadData(share.getDatabaseIp(),
                share.getDatabasePort(),
                share.getDataBaseUser(),
                share.getDataBasePass(),
                share.getDataBase()
                , share.getVersion(), choose);
        nowTime = System.currentTimeMillis();
        App.getRService().downloadData(json, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                insertBefore(dBean);
            }

            @Override
            public void onError(Throwable e) {
                LoadingUtil.dismiss();
                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Updata_Error,e.toString()));
            }
        });
    }
    //库存表数据获取例外，先下载库存表数据
    private int storeNum = 0;
    private void insertBefore(final DownloadReturnBean dBean){
        final InStoreNumBean storageNum = new InStoreNumBean();
        storageNum.FType="0";
        App.getRService().doIOAction("GetStoreNum4sql", new Gson().toJson(storageNum), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state)return;
                DownloadReturnBean dBeanTemp = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (dBeanTemp.InstorageNum != null && dBeanTemp.InstorageNum.size() > 0) {
                    InStorageNumDao storageNumDao = session.getInStorageNumDao();
                    storageNumDao.deleteAll();
                    storageNumDao.insertOrReplaceInTx(dBeanTemp.InstorageNum);
                    storageNumDao.detachAll();
                    storeNum = dBeanTemp.InstorageNum.size();
//                    Toast.showText(mContext,"库存表下载完毕"+dBean.InstorageNum.size());
                }
                insert(dBean);
            }

            @Override
            public void onError(Throwable e) {
//                super.onError(e);
                Toast.showText(mContext,"库存表下载失败"+e.toString());
                insert(dBean);
            }
        });
    }
    private void insert(final DownloadReturnBean dBean) {
        AsyncSession asyncSession = session.startAsyncSession();
//        AsyncSession asyncSession2 = session.startAsyncSession();
        asyncSession.runInTx(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                boolean b = insertLocalSQLite(dBean);
                Log.e("result", b + "");
                if (b) {
                    Lg.e("数量1",dBean.size);
                    Lg.e("数量2",storeNum);
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Updata_OK,nowTime+"",(dBean.size+storeNum)+"",""));
                }
            }
        });
//        asyncSession2.runInTx(new Runnable() {
//            @Override
//            public void run() {
//                Message message = new Message();
//                boolean b = insertLocalSQLite2(dBean);
//                Log.e("result", b + "");
//
//            }
//
//        });
        try{
            LoadingUtil.dismiss();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private boolean insertLocalSQLite2(DownloadReturnBean dBean) {
        if (dBean.orgs != null && dBean.orgs.size() > 0) {
            OrgDao orgDao = session.getOrgDao();
            orgDao.deleteAll();
            orgDao.insertOrReplaceInTx(dBean.orgs);
            orgDao.detachAll();
        }

        if (dBean.department != null && dBean.department.size() > 0) {
            DepartmentDao departmentDao = session.getDepartmentDao();
            departmentDao.deleteAll();
            departmentDao.insertOrReplaceInTx(dBean.department);
            departmentDao.detachAll();
        }
        if (dBean.employee != null && dBean.employee.size() > 0) {
            EmployeeDao employeeDao = session.getEmployeeDao();
            employeeDao.deleteAll();
            employeeDao.insertOrReplaceInTx(dBean.employee);
            employeeDao.detachAll();
        }
        if (dBean.wavehouse != null && dBean.wavehouse.size() > 0) {
            WaveHouseDao wavehouseDao = session.getWaveHouseDao();
            wavehouseDao.deleteAll();
            wavehouseDao.insertOrReplaceInTx(dBean.wavehouse);
            wavehouseDao.detachAll();
        }
        if (dBean.InstorageNum != null && dBean.InstorageNum.size() > 0) {
            InStorageNumDao storageNumDao = session.getInStorageNumDao();
            storageNumDao.deleteAll();
            storageNumDao.insertOrReplaceInTx(dBean.InstorageNum);
            storageNumDao.detachAll();
        }
        if (dBean.storage != null && dBean.storage.size() > 0) {
            StorageDao storageDao = session.getStorageDao();
            storageDao.deleteAll();
            storageDao.insertOrReplaceInTx(dBean.storage);
            storageDao.detachAll();
        }
        if (dBean.units != null && dBean.units.size() > 0) {
            UnitDao unitDao = session.getUnitDao();
            unitDao.deleteAll();
            unitDao.insertOrReplaceInTx(dBean.units);
            unitDao.detachAll();
        }

        if (dBean.suppliers != null && dBean.suppliers.size() > 0) {
            SuppliersDao suppliersDao = session.getSuppliersDao();
            suppliersDao.deleteAll();
            suppliersDao.insertOrReplaceInTx(dBean.suppliers);
            suppliersDao.detachAll();
        }

        if (dBean.products != null && dBean.products.size() > 0) {
            ProductDao productDao = session.getProductDao();
            productDao.deleteAll();
            productDao.insertOrReplaceInTx(dBean.products);
            productDao.detachAll();
        }
        return true;
    }

    private boolean insertLocalSQLite(DownloadReturnBean dBean) {
        if (dBean.buyers != null && dBean.buyers.size() > 0) {
            BuyerDao buyerDao = session.getBuyerDao();
            buyerDao.deleteAll();
            buyerDao.insertOrReplaceInTx(dBean.buyers);
            buyerDao.detachAll();
        }
        if (dBean.User != null && dBean.User.size() > 0) {
            UserDao userDao = session.getUserDao();
            userDao.deleteAll();
            userDao.insertOrReplaceInTx(dBean.User);
            userDao.detachAll();
        }
        if (dBean.clients != null && dBean.clients.size() > 0) {
            ClientDao clientDao = session.getClientDao();
            clientDao.deleteAll();
            clientDao.insertOrReplaceInTx(dBean.clients);
            clientDao.detachAll();
        }
        if (dBean.saleMans != null && dBean.saleMans.size() > 0) {
            SaleManDao saleManDao = session.getSaleManDao();
            saleManDao.deleteAll();
            saleManDao.insertOrReplaceInTx(dBean.saleMans);
            saleManDao.detachAll();
        }
        if (dBean.storeMans != null && dBean.storeMans.size() > 0) {
            StoreManDao storeManDao = session.getStoreManDao();
            storeManDao.deleteAll();
            storeManDao.insertOrReplaceInTx(dBean.storeMans);
            storeManDao.detachAll();
        }
        if (dBean.orgs != null && dBean.orgs.size() > 0) {
            OrgDao orgDao = session.getOrgDao();
            orgDao.deleteAll();
            orgDao.insertOrReplaceInTx(dBean.orgs);
            orgDao.detachAll();
        }

        if (dBean.department != null && dBean.department.size() > 0) {
            DepartmentDao departmentDao = session.getDepartmentDao();
            departmentDao.deleteAll();
            departmentDao.insertOrReplaceInTx(dBean.department);
            departmentDao.detachAll();
        }
        if (dBean.employee != null && dBean.employee.size() > 0) {
            EmployeeDao employeeDao = session.getEmployeeDao();
            employeeDao.deleteAll();
            employeeDao.insertOrReplaceInTx(dBean.employee);
            employeeDao.detachAll();
        }
        if (dBean.wavehouse != null && dBean.wavehouse.size() > 0) {
            WaveHouseDao wavehouseDao = session.getWaveHouseDao();
            wavehouseDao.deleteAll();
            wavehouseDao.insertOrReplaceInTx(dBean.wavehouse);
            wavehouseDao.detachAll();
        }
//        if (dBean.InstorageNum != null && dBean.InstorageNum.size() > 0) {
//            InStorageNumDao storageNumDao = session.getInStorageNumDao();
//            storageNumDao.deleteAll();
//            storageNumDao.insertOrReplaceInTx(dBean.InstorageNum);
//            storageNumDao.detachAll();
//        }
        if (dBean.storage != null && dBean.storage.size() > 0) {
            StorageDao storageDao = session.getStorageDao();
            storageDao.deleteAll();
            storageDao.insertOrReplaceInTx(dBean.storage);
            storageDao.detachAll();
        }
        if (dBean.units != null && dBean.units.size() > 0) {
            UnitDao unitDao = session.getUnitDao();
            unitDao.deleteAll();
            unitDao.insertOrReplaceInTx(dBean.units);
            unitDao.detachAll();
        }

        if (dBean.suppliers != null && dBean.suppliers.size() > 0) {
            SuppliersDao suppliersDao = session.getSuppliersDao();
            suppliersDao.deleteAll();
            suppliersDao.insertOrReplaceInTx(dBean.suppliers);
            suppliersDao.detachAll();
        }

        if (dBean.products != null && dBean.products.size() > 0) {
            ProductDao productDao = session.getProductDao();
            productDao.deleteAll();
            productDao.insertOrReplaceInTx(dBean.products);
            productDao.detachAll();
        }
        return true;
    }
}
