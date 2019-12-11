package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.DataSearchRyAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.ConnectResponseBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Service.BaseUtilService;
import com.fangzuo.assist.cloud.Service.DataService;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.DataBaseAdapter;
import com.fangzuo.assist.cloud.Utils.DataModel;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.JsonCreater;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.UpdataLocData;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingActivity extends BaseActivity implements DataSearchRyAdapter.OnItemClickListener {

    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tvtiti)
    TextView tvTiTi;
    @BindView(R.id.ry_data_search)
    RecyclerView ryDataSearch;
    @BindView(R.id.btn_connect)
    Button btnConnect;
    @BindView(R.id.btn_prop)
    Button btnProp;
    @BindView(R.id.btn_download)
    Button btnDownload;
    @BindView(R.id.ed_serverip)
    EditText edServerip;
    @BindView(R.id.ed_port)
    EditText edPort;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_pass)
    EditText edPass;
    @BindView(R.id.lv_database)
    ListView lvDatabase;
    @BindView(R.id.tv_right)
    TextView tvRight;
    //    @BindView(R.id.container)
//    CoordinatorLayout containerView;
    private DataBaseAdapter adapter;
    private SettingActivity mContext;
    private ArrayList<ConnectResponseBean.DataBaseList> container;
    private BasicShareUtil share;
    private String chooseDatabase;
    private long nowTime;
    private DataSearchRyAdapter dataSearchRyAdapter;
    private ProgressDialog pDialog;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    long nowTime = (long) msg.obj;
                    int size = msg.arg1;
                    long endTime = System.currentTimeMillis();
                    AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                    ab.setTitle("下载完成");
                    ab.setMessage("耗时:" + (endTime - nowTime) + "ms" + ",共插入" + size + "条数据");
                    ab.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startNewActivity(LoginActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, true, null);
                        }
                    });
                    ab.create().show();
                    break;
            }
        }
    };
    private int size;
    private int flag = 1;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Connect_OK:
                CommonResponse commonResponse = (CommonResponse) event.postEvent;
                share.setDatabaseIp(edServerip.getText().toString());
                share.setDatabasePort(edPort.getText().toString());
                share.setDataBaseUser(edUsername.getText().toString());
                share.setDataBasePass(edPass.getText().toString());
                LoadingUtil.dismiss();
                ConnectResponseBean connectBean = gson.fromJson(commonResponse.returnJson, ConnectResponseBean.class);
                dataSearchRyAdapter.addAll(connectBean.DataBaseList);
                adapter = new DataBaseAdapter(mContext, container);
                lvDatabase.setAdapter(adapter);
                Toast.showText(mContext, "获取了" + connectBean.DataBaseList.size() + "条数据");
                break;
            case EventBusInfoCode.Connect_Error:
//                String str = (String) event.postEvent;
//                LoadingUtil.dismiss();
//                Toast.showText(mContext, "连接错误:" + str);
                LoadingUtil.showAlter(mContext, getString(R.string.connect_fail), (String) event.postEvent);
                LoadingUtil.dismiss();
                break;
            case EventBusInfoCode.Prop_OK:
                CommonResponse prop = (CommonResponse) event.postEvent;
                final AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                if (prop.state) {
//                    if (isClearAll) {
//                        DataService.deleteAll(mContext);
//                        ShareUtil.getInstance(mContext).clear();
//                        App.getRService().doIOAction(WebApi.DetailTableDeleteAll, BasicShareUtil.getInstance(mContext).getIMIE(), new MySubscribe<CommonResponse>() {
//                            @Override
//                            public void onNext(CommonResponse commonResponse) {
//                                Lg.e("删除临时表成功1");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Lg.e("删除临时表失败");
//                            }
//                        });
//                    }
                    LoadingUtil.dismiss();
                    ab.setTitle(R.string.set_result);
                    ab.setMessage(R.string.set_successful);
                    ab.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startNewActivity(LoginActivity.class,
                                    R.anim.activity_fade_in, R.anim.activity_fade_out, true, null);
                        }
                    });
                    ab.create().show();
                    DataService.UpdateData(this);

                    share.setVersion(prop.returnJson);
                    share.setDataBase(chooseDatabase);
                } else {
                    LoadingUtil.dismiss();
                    ab.setTitle(R.string.set_result);
                    ab.setMessage(prop.returnJson);
                    ab.setPositiveButton(R.string.yes, null);
                    ab.setNegativeButton(R.string.retry, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            prop();
                        }
                    });
                    ab.create().show();
                }
                isClearAll = false;
                break;
            case EventBusInfoCode.Prop_Error:
                String str2 = (String) event.postEvent;
                final AlertDialog.Builder ab2 = new AlertDialog.Builder(mContext);
                LoadingUtil.dismiss();
                ab2.setTitle(R.string.set_result);
                ab2.setMessage(str2);
                ab2.setPositiveButton(R.string.yes, null);
                ab2.setNegativeButton(R.string.retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        prop();
                    }
                });
                ab2.create().show();
                break;
            case EventBusInfoCode.Updata_OK://回单成功
                String time = (String) event.Msg2;
                String size = (String) event.Msg3;
                long endTime = System.currentTimeMillis();
                AlertDialog.Builder ab4 = new AlertDialog.Builder(mContext);
                ab4.setTitle(R.string.download_successful);
                ab4.setMessage("耗时:" + (endTime - Long.parseLong(time)) + "ms" + ",共插入" + size + "条数据");
                ab4.setPositiveButton(R.string.yes, null);
                ab4.create().show();
                break;
            case EventBusInfoCode.Updata_Error://回单失败
                LoadingUtil.dismiss();
                LoadingUtil.showAlter(mContext, getString(R.string.down_fail), (String) event.postEvent);
//                Toast.showText(mContext,(String)event.postEvent);
                break;
        }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        tvTitle.setText(getResources().getString(R.string.down_set));
        tvRight.setText("清空");
        if (!Hawk.get(Info.user_data, "").equals("")) {
            tvTiTi.setText("账套选择：当前登陆的账套中心：" + Hawk.get(Info.user_data, ""));
        } else {
            tvTiTi.setText("账套选择：");
        }
    }

    @Override
    public void initData() {
        mContext = this;
        container = new ArrayList<>();
        share = BasicShareUtil.getInstance(mContext);

        //为了测试
        if (!share.getDatabaseIp().equals("")) {
            edServerip.setText(share.getDatabaseIp());
            edPort.setText(share.getDatabasePort());
            edUsername.setText(share.getDataBaseUser());
            edPass.setText(share.getDataBasePass());
        } else {
            if (App.DataBaseSetting.equals("K3DBConfigerRY")) {
                edServerip.setText("172.18.120.186");
                edPort.setText("1433");
                edUsername.setText("sa");
                edPass.setText("rongyuan@888");
            } else {
                edServerip.setText("192.168.0.201");
                edPort.setText("1433");
                edUsername.setText("sa");
                edPass.setText("Abc123");
            }
        }

        dataSearchRyAdapter = new DataSearchRyAdapter(mContext, container);
        ryDataSearch.setAdapter(dataSearchRyAdapter);
        ryDataSearch.setItemAnimator(new DefaultItemAnimator());
        ryDataSearch.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        dataSearchRyAdapter.setOnItemClickListener(this);
        dataSearchRyAdapter.notifyDataSetChanged();

    }

    @Override
    public void initListener() {
        tvRight.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                AlertDialog.Builder ab4 = new AlertDialog.Builder(mContext);
                ab4.setTitle("是否清空本地数据及临时表");
                ab4.setPositiveButton("清空", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearData();
                    }
                });
                ab4.setNegativeButton("取消",null);
                ab4.create().show();
            }
        });
    }

    //清空临时表和贝蒂数据
    private void clearData(){
        LoadingUtil.showDialog(mContext,"正在清空...");
        DataService.deleteAll(mContext);
        ShareUtil.getInstance(mContext).clear();
        App.getRService().doIOAction(WebApi.DetailTableDeleteAll, BasicShareUtil.getInstance(mContext).getIMIE(), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                Lg.e("删除临时表成功1");
                Toast.showText(mContext,"清空临时表完毕");
                App.getRService().doIOAction(WebApi.DeleteBoxCodeAll, BasicShareUtil.getInstance(mContext).getIMIE(), new MySubscribe<CommonResponse>() {
                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        Lg.e("删除临时表成功1");
                        Toast.showText(mContext,"清空箱码临时表完毕完毕");
                        LoadingUtil.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LoadingUtil.dismiss();
                        Lg.e("删除临时表失败");
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Lg.e("删除临时表失败");
                LoadingUtil.dismiss();
            }
        });
    }

    @Override
    protected void OnReceive(String code) {
    }


    @Override
    public void onItemClick(View view, int position) {
        dataSearchRyAdapter.setIsCheck(position);
        chooseDatabase = container.get(position).dataBase;
//        Toast.showText(mContext, chooseDatabase);
        share.setDataBase(chooseDatabase);
        Hawk.put(Config.Cloud_ID, container.get(position).dataBaseID);
        Lg.e("设置账套id", Hawk.get(Config.Cloud_ID, ""));
        LoadingUtil.showAlter(mContext, "已选择数据中心：" + container.get(position).name);
        DataService.UpdateData(this);
        BaseUtilService.reLogin(mContext);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @OnClick({R.id.btn_back, R.id.btn_connect, R.id.btn_prop, R.id.btn_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_connect:
                connectToSQL();
                break;
            case R.id.btn_prop:
                prop();
                break;
            case R.id.btn_download:
                daoSession.clear();
                UpdataLocData.getInstance(mContext).alertToChoose();
//                DownLoadData.getInstance(mContext, containerView, handler).alertToChoose();
                break;
        }
    }

//    //清空的话，重启程序
//    public void restartApplication() {
//        if (isGotoLogin){
//            Lg.e("关闭？？？");
//            Intent intent = new Intent(this, SplashActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            this.startActivity(intent);
//            android.os.Process.killProcess(android.os.Process.myPid());
////            Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
////            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////            startActivity(intent);
//        }else{
//            finish();
//        }
//    }
//    @Override
//    public void onBackPressed() {
//        restartApplication();
//        super.onBackPressed();
//    }

    private void prop() {
        AlertDialog.Builder ab1 = new AlertDialog.Builder(mContext);
        ab1.setTitle(R.string.if_configuration);
//        ab1.setMessage(R.string.configuration_tips);
        ab1.setPositiveButton("配置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setprop(true);
            }
        });
//        ab1.setNeutralButton(R.string.unclear, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                setprop(false);
//            }
//        });
        ab1.setNegativeButton(R.string.cancle, null);
        ab1.create().show();
    }

    private boolean isClearAll = false;

    private void setprop(final boolean isClear) {
        if (null == chooseDatabase) {
            Toast.showText(mContext, getString(R.string.choose_accout));
            return;
        }
        isClearAll = isClear;
        LoadingUtil.show(mContext, getString(R.string.configurationing));
        DataModel.SetProp(JsonCreater.ConnectSQL(
                share.getDatabaseIp(),
                share.getDatabasePort(),
                share.getDataBaseUser(),
                share.getDataBasePass(),
                chooseDatabase));
    }

    private void connectToSQL() {
        LoadingUtil.showDialog(mContext, getString(R.string.connection_going));
        DataModel.SetConnectSQL(JsonCreater.ConnectSQL(
                edServerip.getText().toString(),
                edPort.getText().toString(),
                edUsername.getText().toString(),
                edPass.getText().toString(),
                App.DataBaseSetting));
    }


}
