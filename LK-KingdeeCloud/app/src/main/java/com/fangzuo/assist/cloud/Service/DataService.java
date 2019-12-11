package com.fangzuo.assist.cloud.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.Asynchttp;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.greendao.gen.BuyerDao;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.DepartmentDao;
import com.fangzuo.greendao.gen.OrgDao;
import com.fangzuo.greendao.gen.RemarkDataDao;
import com.fangzuo.greendao.gen.SaleManDao;
import com.fangzuo.greendao.gen.StorageDao;
import com.fangzuo.greendao.gen.UnitDao;
import com.loopj.android.http.AsyncHttpClient;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class DataService extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.fangzuo.assist.Service.action.FOO";
    private static final String ACTION_BAZ = "com.fangzuo.assist.Service.action.BAZ";
    private static final String ACTION_BackJson = "com.fangzuo.assist.Service.action.ACTION_BackJson";
    private static final String UpdateTime = "com.fangzuo.assist.Service.action.UpdateTime";
    private static final String UpdateData = "com.fangzuo.assist.Service.action.UpdateData";
    private static final String UpdateRegisterMaxNum = "com.fangzuo.assist.Service.action.UpdateRegisterMaxNum";

    private static final String EXTRA_PARAM1 = "com.fangzuo.assist.Service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.fangzuo.assist.Service.extra.PARAM2";

    public DataService() {
        super("DataService");
    }

    private DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();
        session = GreenDaoManager.getmInstance(App.getContext()).getDaoSession();
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void deleteAll(Context context) {
        Intent intent = new Intent(context, DataService.class);
        intent.setAction(ACTION_FOO);
        context.startService(intent);
    }

    //更新服务器中的当前时间
    public static void updateTime(Context context) {
        Intent intent = new Intent(context, DataService.class);
        intent.setAction(UpdateTime);
        context.startService(intent);
    }

    //获取基础数据
    public static void UpdateData(Context context) {
        Intent intent = new Intent(context, DataService.class);
        intent.setAction(UpdateData);
        context.startService(intent);
    }
    ////获取服务器最大注册数
    public static void updateRegisterMaxNum(Context context) {
        Intent intent = new Intent(context, DataService.class);
        intent.setAction(UpdateRegisterMaxNum);
        context.startService(intent);
    }

    /**
     * 处理报错数据
     *
     * @param context
     * @param txtName
     * @param ex
     */
    public static void pushError(Context context, String txtName, Throwable ex) {
        Intent intent = new Intent(context, DataService.class);
        StackTraceElement[] stes = ex.getStackTrace();
        StringBuilder builder = new StringBuilder();
        builder.append(ex.toString() + "\n");
        for (int i = 0; i < stes.length; i++) {
            builder.append(i + "->" + stes[i].getClassName() + "-->" + stes[i].getMethodName() + "-->" + stes[i].getFileName() + "\n");
        }
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, txtName);
        intent.putExtra(EXTRA_PARAM2, builder.toString());
        context.startService(intent);
    }
    //反馈回执
    public static void pushBackJson(Context context, String txtName, String json) {
        Intent intent = new Intent(context, DataService.class);
        intent.setAction(ACTION_BackJson);
        intent.putExtra(EXTRA_PARAM1, "反馈回执："+txtName);
        intent.putExtra(EXTRA_PARAM2, json);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                handleActionFoo();
            } else if (ACTION_BAZ.equals(action)) {
                final String txtNa = intent.getStringExtra(EXTRA_PARAM1);
                final String err = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(txtNa, err);
            } else if (UpdateTime.equals(action)) {
                handleActionUpdateTime();
            } else if (UpdateData.equals(action)) {
                handleActionUpdateData();
            } else if (UpdateRegisterMaxNum.equals(action)) {
                handleActionUpdateRegisterMaxNum();
            } else if (ACTION_BackJson.equals(action)) {
                final String txtNa = intent.getStringExtra(EXTRA_PARAM1);
                final String err = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBackJson(txtNa,err);
            }

        }
    }

    //删除所有本地数据
    private void handleActionFoo() {
        session.getBibieDao().deleteAll();
        session.getBarCodeDao().deleteAll();
        session.getT_DetailDao().deleteAll();
        session.getT_mainDao().deleteAll();
        session.getClientDao().deleteAll();
        session.getDepartmentDao().deleteAll();
        session.getEmployeeDao().deleteAll();
        session.getGetGoodsDepartmentDao().deleteAll();
        session.getInStorageNumDao().deleteAll();
        session.getInStoreTypeDao().deleteAll();
        session.getPayTypeDao().deleteAll();
        session.getPDMainDao().deleteAll();
        session.getPDSubDao().deleteAll();
        session.getPriceMethodDao().deleteAll();
        session.getProductDao().deleteAll();
        session.getPurchaseMethodDao().deleteAll();
        session.getPushDownMainDao().deleteAll();
        session.getPushDownSubDao().deleteAll();
        session.getStorageDao().deleteAll();
        session.getSuppliersDao().deleteAll();
        session.getUnitDao().deleteAll();
        session.getUserDao().deleteAll();
        session.getWanglaikemuDao().deleteAll();
        session.getWaveHouseDao().deleteAll();
        session.getYuandanTypeDao().deleteAll();
    }

    //上传错误数据
    private void handleActionBaz(String txtN, String param1) {

        Asynchttp.post(App.getContext(), Config.Error_Url, Config.Company + "^" + txtN + "^" + param1, new Asynchttp.Response() {
            @Override
            public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {

            }

            @Override
            public void onFailed(String Msg, AsyncHttpClient client) {
            }
        });
    }
    //上传反馈数据
    private void handleActionBackJson(String txtN, String param1) {

        Asynchttp.post(App.getContext(), Config.Error_Url, Config.Company + "^" + txtN + "^" + param1, new Asynchttp.Response() {
            @Override
            public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {

            }

            @Override
            public void onFailed(String Msg, AsyncHttpClient client) {
            }
        });
    }
    //更新时间
    private void handleActionUpdateTime() {
        App.getRService().doIOAction(WebApi.SetUseTime, "更新时间", new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
            }

            @Override
            public void onError(Throwable e) {
//                super.onError(e);
            }
        });
    }

    //下载数据,只能小数据，数据多的会崩溃
    private void handleActionUpdateData() {
        ArrayList<Integer> choose = new ArrayList<>();
        choose.add(1);//单位
        choose.add(7);//单位
        choose.add(6);//仓库
        choose.add(10);//销售员
        choose.add(14);//组织
        choose.add(15);//简称表
        choose.add(2);//部门表
        String json = JsonCreater.DownLoadData(
                BasicShareUtil.getInstance(App.getContext()).getDatabaseIp(),
                BasicShareUtil.getInstance(App.getContext()).getDatabasePort(),
                BasicShareUtil.getInstance(App.getContext()).getDataBaseUser(),
                BasicShareUtil.getInstance(App.getContext()).getDataBasePass(),
                BasicShareUtil.getInstance(App.getContext()).getDataBase(),
                BasicShareUtil.getInstance(App.getContext()).getVersion(),
                choose
        );
        App.getRService().downloadData(json, new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                DownloadReturnBean dBean = JsonCreater.gson.fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (dBean.units != null && dBean.units.size() > 0) {
                    UnitDao unitDao = session.getUnitDao();
                    unitDao.deleteAll();
                    unitDao.insertOrReplaceInTx(dBean.units);
                    unitDao.detachAll();
                    Lg.e("OK单位"+dBean.units.size());
                }
                if (dBean.saleMans != null && dBean.saleMans.size() > 0) {
                    SaleManDao saleManDao = session.getSaleManDao();
                    saleManDao.deleteAll();
                    saleManDao.insertOrReplaceInTx(dBean.saleMans);
                    saleManDao.detachAll();
                    Lg.e("OK销售员"+dBean.saleMans.size());
                }
                if (dBean.buyers!= null && dBean.buyers.size() > 0) {
                    BuyerDao saleManDao = session.getBuyerDao();
                    saleManDao.deleteAll();
                    saleManDao.insertOrReplaceInTx(dBean.buyers);
                    saleManDao.detachAll();
                    Lg.e("OK采购员"+dBean.buyers.size());
                }
                if (dBean.orgs != null && dBean.orgs.size() > 0) {
                    OrgDao clientDao = session.getOrgDao();
                    clientDao.deleteAll();
                    clientDao.insertOrReplaceInTx(dBean.orgs);
                    clientDao.detachAll();
                    Lg.e("OK组织"+dBean.orgs.size());
                }
                if (dBean.remarkDatas != null && dBean.remarkDatas.size() > 0) {
                    RemarkDataDao clientDao = session.getRemarkDataDao();
                    clientDao.deleteAll();
                    clientDao.insertOrReplaceInTx(dBean.remarkDatas);
                    clientDao.detachAll();
                    Lg.e("OK简称表"+dBean.remarkDatas.size());
                }
                if (dBean.storage != null && dBean.storage.size() > 0) {
                    StorageDao yuandanTypeDao = session.getStorageDao();
                    yuandanTypeDao.deleteAll();
                    yuandanTypeDao.insertOrReplaceInTx(dBean.storage);
                    yuandanTypeDao.detachAll();
                    Lg.e("OK仓库"+dBean.storage.size());
                }
                if (dBean.department != null && dBean.department.size() > 0) {
                    DepartmentDao yuandanTypeDao = session.getDepartmentDao();
                    yuandanTypeDao.deleteAll();
                    yuandanTypeDao.insertOrReplaceInTx(dBean.department);
                    yuandanTypeDao.detachAll();
                    Lg.e("OK部门表"+dBean.department.size());
                }

            }

            @Override
            public void onError(Throwable e) {
//                    LoadingUtil.dismiss();
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Updata_Error,e.toString()));
            }
        });
    }

    //获取服务器最大注册数
    private void handleActionUpdateRegisterMaxNum(){
            App.getRService().doIOAction(WebApi.RegisterGetNum, "", new MySubscribe<CommonResponse>() {
                @Override
                public void onNext(CommonResponse commonResponse) {
                    super.onNext(commonResponse);
                    if (!commonResponse.state)return;
                    Lg.e("最大用户数Max:"+commonResponse.returnJson);
                    Hawk.put(Config.PDA_RegisterMaxNum,commonResponse.returnJson);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    Toast.showText(App.getContext(),App.getContext().getString(R.string.error_get_app_usernum));
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Register_Result,App.getContext().getString(R.string.error_get_app_usernum)));
                }
            });
    }
}
