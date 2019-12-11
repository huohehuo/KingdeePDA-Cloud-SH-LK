package com.fangzuo.assist.cloud.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.BackDataLogin;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.RegisterBean;
import com.fangzuo.assist.cloud.Beans.UseTimeBean;
import com.fangzuo.assist.cloud.Dao.User;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.RxSerivce.RService;
import com.fangzuo.assist.cloud.RxSerivce.ToSubscribe;
import com.fangzuo.assist.cloud.Service.BaseUtilService;
import com.fangzuo.assist.cloud.Service.DataService;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.CommonUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.RegisterUtil;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.UpgradeUtil.AppStatisticalUtil;
import com.fangzuo.assist.cloud.Utils.UpgradeUtil.AppVersionUtil;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.fangzuo.assist.cloud.widget.SpinnerUser;
import com.fangzuo.greendao.gen.UserDao;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static com.fangzuo.assist.cloud.Utils.CommonUtil.dealTime;


public class LoginActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {


    @BindView(R.id.isRemPass)
    CheckBox isRemPass;
    @BindView(R.id.ver)
    TextView mTvVersion;
    @BindView(R.id.ed_pass)
    EditText mEtPassword;
    //    @BindView(R.id.isOL)
//    CheckBox mCbisOL;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.tv_vers_tip)
    TextView tvVersTip;
    private Button mBtnLogin;
    private Button mBtnSetting;
    private LoginActivity mContext;
    @BindView(R.id.sp_login)
    SpinnerUser spinner;
    private User user;
//    private String userName = "";
//    private String userID = "";
    //    private List<User> users;
    private BasicShareUtil share;
    private boolean isOL;
//    private String userPass;
    private UserDao userDao;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    RService rService;

    @Override
    protected void receiveEvent(ClassEvent event) {
        switch (event.Msg) {
            case EventBusInfoCode.Register_Result://注册信息反馈
                String result = (String) event.postEvent;
                if ("OK".equals(result)) {
                    btnLogin.setClickable(true);
                    btnLogin.setText(getString(R.string.login_text));
                    Lg.e("成功注册");
//                    BasicShareUtil.getInstance(App.getContext()).setRegisterState(true);
//                    startNewActivity(LoginActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, true, null);
                } else {
//                    Lg.e("生成dlg000000");
                    btnLogin.setClickable(false);
                    btnLogin.setText(R.string.not_register);
                    AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                    ab.setTitle(R.string.tip);
                    ab.setMessage(result);
                    ab.setPositiveButton(R.string.register, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            RegisterUtil.doRegisterCheck(Hawk.get(Config.PDA_IMIE, ""));

                        }
                    });
                    ab.setNegativeButton(R.string.cancle, null);
                    ab.setNeutralButton(R.string.close_app, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    });
                    final AlertDialog alertDialog = ab.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
//                    LoadingUtil.showAlter(LoginActivity.this, "提示", result);
                }
                break;
        }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = this;
        initBar();
        share = BasicShareUtil.getInstance(mContext);
        userDao = daoSession.getUserDao();
        Hawk.put(Config.PDA_Project_Type, "1");//确定一期/二期项目;确定之后，将决定apk和版本号的下载地址，版本号
        getPermisssion();
//        mTvVersion.setText("Cloud Ver:" + CommonUtil.getVersionName());
        mTvVersion.setText("Cloud Ver:" + Info.getAppNo());
        Lg.e("PDA：" + App.PDA_Choose);
        isRemPass.setChecked(Hawk.get(Info.IsRemanber, false));
        rService = App.getRService();
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint({"HardwareIds", "MissingPermission"}) String deviceId = tm.getDeviceId();
        BasicShareUtil.getInstance(mContext).setIsOL(true);
        share.setIMIE(deviceId);
        Hawk.put(Config.PDA_MsgAndIMIE,deviceId);
//        Resources res = mContext.getResources();
//        Configuration config = res.getConfiguration();
//        String phone_locale = config.locale.getCountry();
//        Lg.e("当前手机语言：", phone_locale);
//        changeAppLanguage();
//        App.getRService().doIOAction("StorageIO", "storage", new MySubscribe<CommonResponse>() {
//            @Override
//            public void onNext(CommonResponse commonResponse) {
//                super.onNext(commonResponse);
//                DownloadReturnBean dBean = JsonCreater.gson.fromJson(commonResponse.returnJson, DownloadReturnBean.class);
//                Lg.e("得到仓库",dBean.storage.size());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//            }
//        });

        AppStatisticalUtil.upDataStatis(mContext,"LoginActivity");

//
//        Lg.e("100/0.00236",MathUtil.div("100","0.00236"));
//        Lg.e("100/0.00236",(MathUtil.toD("42372.88136")*0.00236)+"");
//        Lg.e("42372.8813559322*0.00236计算1",MathUtil.D2save5(MathUtil.mul("42372.88136","0.00236")));
//        Lg.e("42372.8813559322*0.00236计算2",MathUtil.D2save5(42372.88136*0.00236));
//        Lg.e("42372.8813559322*0.00236计算3",MathUtil.D2save5((MathUtil.toD("42372.88136")*0.00236))+"");
//        Lg.e("42372.8813559322*0.00236计算4",MathUtil.D2save5_33("42372.88136"));
//        Lg.e("42372.8813559322*0.00236计算",MathUtil.D2save5(MathUtil.mul(beans.get(j).FRealQty,"0.00236")));

    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.e("Login___onResume");
        //获取用户数据，并且设置默认值
        spinner.setAutoSelection(Info.AutoLogin, Hawk.get(Info.AutoLogin, ""));
        mTvVersion.setText("Cloud Ver:" + Info.getAppNo());
        DataService.updateTime(mContext);
        DataService.updateRegisterMaxNum(mContext);
        DownLoadUseTime();
        AppVersionUtil.CheckVersion(mContext);
        //检查是否存在注册码
        RegisterUtil.checkHasRegister();
        //下载app版本号
//        RegisterUtil.downLoadVersion();
//        RegisterUtil.downLoadVersionExplain();
        //上传手机信息IMIE到注册表
        BaseUtilService.updateRegisterMsg(mContext,gson.toJson(
                new RegisterBean(
                        Hawk.get(Config.PDA_IMIE,""),
                        Build.MODEL+"-IMEI码:"+Hawk.get(Config.PDA_MsgAndIMIE,""),
                        Info.getAppNo(),getTimeLong(true))));
//        Lg.e("本地版本号：",Info.TestNo);
//        Lg.e("网络版本号：",Hawk.get(Config.Apk_Version, ""));
        //若网络版本比本地版本高，提示新版本
//        if (!"".equals(Hawk.get(Config.Apk_Version, ""))) {
//            if (Double.parseDouble(Hawk.get(Config.Apk_Version, "0")) > Double.parseDouble(Info.getAppNo())) {
//                tvVersTip.setVisibility(View.VISIBLE);
//                tvVersTip.setText("有新版本"+Hawk.get(Config.Apk_Version,"0")+"，点击这里进行更新");
//                //发送更新提示广播
//                Intent intent = new Intent("com.fangzuo.version");
//                intent.putExtra("version",Hawk.get(Config.Apk_Version,"0"));
//                sendBroadcast(intent);
//                new AlertDialog.Builder(mContext)
//                        .setTitle("更新版本")
//                        .setMessage("请确保本地数据已完全上传后,及时进行程序更新,避免影响做单")
//                        .setPositiveButton("下载并更新", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                DownLoad(Config.getApk_Url());
//                            }
//                        })
//                        .setNeutralButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.showText(mContext,"请及时更新程序，避免影响做单");
//                            }
//                        })
//                        .create().show();
//            }else{
//                tvVersTip.setVisibility(View.GONE);
//            }
//        }else{
//            Lg.e("kong");
//        }
//        Lg.e("加密",DesUtils.base64Encrypt("123"));
//        Lg.e("解密",DesUtils.base64Decode(DesUtils.base64Encrypt("123")));

//Lg.e("精度",DoubleUtil.CutTo0("6.457"));
//Lg.e("精度",DoubleUtil.CutTo0("6.44"));
//Lg.e("精度",DoubleUtil.CutTo0("6.95"));



    }

    //获取配置文件中的时间数据
    private void DownLoadUseTime() {
        App.getRService().doIOAction(WebApi.GetUseTime, "获取时间", new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                LoadingUtil.dismiss();
                if (!commonResponse.state) return;
                UseTimeBean bean = gson.fromJson(commonResponse.returnJson, UseTimeBean.class);
                if (Integer.parseInt(getTime(false)) < MathUtil.toInt(bean.nowTime)) {
                    Toast.showText(mContext, getString(R.string.error_time_getting));
                    Hawk.put(Config.SaveTime, bean);
                    return;
                } else {
                    if (Integer.parseInt(getTime(false)) > MathUtil.toInt(dealTime(bean.endTime))) {
                        Toast.showText(mContext, getString(R.string.error_app_past_time));
                        Hawk.put(Config.SaveTime, bean);
                        return;
                    } else {
                        Lg.e("获取起止时间：" + commonResponse.returnJson);
                        Hawk.put(Config.SaveTime, bean);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LoadingUtil.dismiss();
//                Hawk.put(Config.SaveTime,null);
                Toast.showText(mContext, e.toString());
                Lg.e("错误：" + e.toString());
            }
        });


//        Lg.e("本地配置数据；",Hawk.get(Config.SettingData,new DownloadReturnBean().new SetFile()));
//
//        Asynchttp.post(mContext,Config.Setting_Url, "sdfa", new Asynchttp.Response() {
//            @Override
//            public void onSucceed(CommonResponse cBean, AsyncHttpClient client) {
//                Lg.e("配置数据：",cBean);
//                Lg.e("配置数据：",cBean.returnJson);
//                DownloadReturnBean dBean = JsonCreater.gson.fromJson(cBean.returnJson, DownloadReturnBean.class);
//                Lg.e("配置解析：",dBean);
//                Lg.e("配置解析：",dBean.serverTime);
//                for (int i = 0; i < dBean.setFiles.size(); i++) {
//                    if (getApplication().getPackageName().equals(dBean.setFiles.get(i).AppID)){
//                        Lg.e("存在App：",dBean.setFiles.get(i));
//                        Hawk.put(Config.SettingData,dBean.setFiles.get(i));
//                    }
//                }
//            }
//
//            @Override
//            public void onFailed(String Msg, AsyncHttpClient client) {
//                Lg.e("配置解析错误："+Msg);
//            }
//        });
    }

    //检测是否符合时间要求
    private boolean checkTime() {
        if (null == Hawk.get(Config.SaveTime, null)) {
            LoadingUtil.showDialog(mContext, getString(R.string.loading_getseting));
            DownLoadUseTime();
            return false;
        } else {
            UseTimeBean bean = Hawk.get(Config.SaveTime);
            if (Integer.parseInt(getTime(false)) < MathUtil.toInt(bean.nowTime)) {
                Toast.showText(mContext, getString(R.string.error_time_getting));
                return false;
            } else {
                if (Integer.parseInt(getTime(false)) > MathUtil.toInt(dealTime(bean.endTime))) {
                    Toast.showText(mContext, getString(R.string.error_app_past_time));
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    @Override
    public void initData() {
        //下载基础表：//单位//销售员//组织//简称表
        DataService.UpdateData(this);
    }

    @Override
    public void initListener() {
        tvVersTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("是否更新程序")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DownLoad(Config.getApk_Url());
                            }
                        })
                        .create().show();
            }
        });
        isRemPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Hawk.put(Info.IsRemanber, b);
            }
        });
//        mCbisOL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                isOL = b;
//                BasicShareUtil.getInstance(mContext).setIsOL(b);
//                users = userDao.loadAll();
//                LoginSpAdapter ada = new LoginSpAdapter(mContext, users);
//                spinner.setAdapter(ada);
//                ada.notifyDataSetChanged();
//            }
//        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                user = (User) spinner.getAdapter().getItem(i);
                Lg.e("选中用户",user);
                ShareUtil.getInstance(mContext).setUserName(user.FNameERP);
//                ShareUtil.getInstance(mContext).setUserID(user.FUserID);
                //设置下次默认选择的用户
                Hawk.put(Info.AutoLogin, user.FName);
                //设置该用户密码
                mEtPassword.setText(Hawk.get(user.FName, ""));
                if ("Administrator".equals(user.FNameERP)){
                    Hawk.put(Config.User_Permit,"1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22-23-24-25-26-27-28-29-30-31-32-33-34-35-36-37-38-39-40-41-44-43-44-45-46-47-48-49-50-51-52-55-54-55-56-57-58-59-60-61-62-66-64-66-66-67-68-69-70-71-72-77-74-77-77-77-78-79-201-202-203-204-205-206-207-208-209-210-211-212-213-214-215-216-217");
                }else{
                    Hawk.put(Config.User_Permit,user.FPermit);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void OnReceive(String code) {
        Toast.showText(mContext, "扫码:" + code);
        Log.e("CODE", code + ":获得的code");
    }

    @OnClick({R.id.btn_login, R.id.btn_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Login();
                break;
            case R.id.btn_setting:
                Bundle b = new Bundle();
                b.putInt("flag", 0);
                startNewActivity(SettingMenuActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);
                break;
        }
    }


    //    String JsonStr="{"acctID":"5beb8db650b4cf","username":"Administrator","lcid":"888888"}";
    private void Login() {
        if (!checkTime()) {
            DownLoadUseTime();
            Toast.showText(mContext, getString(R.string.error_check_fail));
            return;
        }
        if (null==user){
            Toast.showText(mContext, "请选择登陆用户信息");
            return;
        }

        if (Hawk.get(Config.Cloud_ID, "").equals("")) {
            Toast.showText(mContext, getString(R.string.error_check_accout));
            return;
        }
        if ("".equals(user.FName)) {
            Toast.showText(mContext, getString(R.string.error_choose_user));
            return;
        }
        if ("".equals(user.FNameERP)) {
            Toast.showText(mContext, "当前用户对应的ERP登陆数据有误");
            return;
        }

        if (!mEtPassword.getText().toString().equals(user.FPassWord)) {
            Toast.showText(mContext, "登陆密码错误，请重试");
            return;
        }
        LoadingUtil.showDialog(mContext, getString(R.string.loading_checking));
        //组装登录数据
        JSONArray jParas = new JSONArray();
        jParas.put(Hawk.get(Config.Cloud_ID, ""));// 帐套Id
        jParas.put(user.FNameERP);// 用户名
        jParas.put(user.FPassWordERP);// 密码
        jParas.put(2052);// 语言T
        App.CloudService().doIOActionLogin(Config.C_Login, jParas.toString(), new ToSubscribe<BackDataLogin>() {
            @Override
            public void onNext(BackDataLogin bean) {
                try {
//                    BackDataLogin bean = JsonCreater.gson.fromJson(string, BackDataLogin.class);
                    if (bean.getLoginResultType() == 1 || bean.getLoginResultType() == -5) {
                        ShareUtil.getInstance(mContext).setUserName(user.FNameERP);
                        Hawk.put(Info.user_name, user.FNameERP);
                        Hawk.put(Info.user_pwd, user.FPassWordERP);
                        Hawk.put(Info.user_org, bean.getContext().getCurrentOrganizationInfo().getName());
                        Hawk.put(Info.user_id, bean.getContext().getUserId() + "");
                        Hawk.put(Info.user_data, bean.getContext().getDataCenterName() + "");
//                    ShareUtil.getInstance(mContext).setUserID(userID);
                        if (isRemPass.isChecked()) {
//                        保存该用户的密码
                            Hawk.put(user.FName, mEtPassword.getText().toString());
                        } else {
                            Hawk.put(user.FName, "");
                        }
                        if (Hawk.get(Config.PDA_Project_Type, "2").equals("1")){
                            startNewActivity(HomeActivity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);
                        }else{
                            startNewActivity(HomeP2Activity.class, R.anim.activity_slide_left_in, R.anim.activity_slide_left_out, false, null);
                        }
                        LoadingUtil.dismiss();
                    } else {
                        LoadingUtil.dismiss();
                        LoadingUtil.showAlter(mContext, getString(R.string.error_login_fail), bean.getMessage(), false);
//                        Toast.showText(App.getContext(), bean.getMessage());
                        Lg.e("登录错误2：" + bean.toString());
                    }
                } catch (Exception e) {
                    LoadingUtil.dismiss();
                    Lg.e("json转换错误：" + e.toString());
                }
            }

            @Override
            public void onError(Throwable e) {
                LoadingUtil.dismiss();
                Lg.e("登录错误：" + e.toString());
                super.onError(e);
            }
        });
    }

    private ProgressDialog pDialog;
    private void DownLoad(String downLoadURL) {
        LoadingUtil.dismiss();
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            pDialog = new ProgressDialog(mContext);
            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.setTitle("下载中,请耐心等待...");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.show();
            String target = Environment.getExternalStorageDirectory()
                    + "/NewApp"+getTimeLong(false)+".apk";
            HttpUtils utils = new HttpUtils();
            utils.download(downLoadURL, target, new RequestCallBack<File>() {
                @Override
                public void onLoading(long total, long current,
                                      boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                    System.out.println("下载进度:" + current + "/" + total);
                    pDialog.setProgress((int) (current*100/total));
                }

                @Override
                public void onSuccess(ResponseInfo<File> arg0) {
                    pDialog.dismiss();
                    Lg.e("下载的文件数据："+arg0.toString());
                    Lg.e("下载的文件数据："+arg0.result);
                    System.out.println("下载完成"+arg0.result);
                    try{
                        CommonUtil.installApk(mContext,arg0.result+"");
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.addCategory(Intent.CATEGORY_DEFAULT);
//                        intent.setDataAndType(Uri.fromFile(arg0.result),
//                                "application/vnd.android.package-archive");
//                        startActivityForResult(intent, 0);
                    }catch (Exception e){
                        try{
                            StringBuilder builder = new StringBuilder();
                            builder.append("请先退出本软件\n");
                            builder.append("进入PDA软件主页\n");
                            builder.append("选择文件管理器\n");
                            builder.append("找到文件NewApp\n");
                            builder.append("长按变色点击右下角重命名\n删去后面的数字\n");
                            builder.append("变成文件名：NewApp.apk\n");
                            builder.append("点击安装\n");
                            AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                            ab.setTitle("下载成功!\n请按操作重新安装APK");
                            ab.setMessage(builder.toString());
                            ab.setPositiveButton("确定", null);
                            ab.create().show();
                        }catch (Exception e1){

                        }
                    }

                }

                @Override
                public void onFailure(com.lidroid.xutils.exception.HttpException arg0, String arg1) {
                    pDialog.dismiss();
                    Toast.showText(mContext, "下载失败");
                }
            });
        } else {
            pDialog.dismiss();
            Toast.showText(mContext, "正在安装");

        }
    }

    //权限获取-------------------------------------------------------------
    private void getPermisssion() {
        String[] perm = {
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(mContext, perm)) {
            EasyPermissions.requestPermissions(this, getString(R.string.get_permission), 0, perm);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //把申请权限的回调交由EasyPermissions处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.i("permisssion", "获取成功的权限" + perms);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.i("permisssion", "获取失败的权限" + perms);
    }
}
