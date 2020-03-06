package com.fangzuo.assist.cloud.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.RegisterBean;
import com.fangzuo.assist.cloud.Beans.UseTimeBean;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.RegisterUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.assist.cloud.widget.LoadingUtil;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.fangzuo.assist.cloud.Utils.CommonUtil.dealTime;


public class IpPortActivity extends BaseActivity {


    @BindView(R.id.ed_ip)
    EditText edIp;
    @BindView(R.id.ed_port)
    EditText edPort;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.sp_pda)
    Spinner spPda;
    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_url)
    EditText edUrl;
    @BindView(R.id.ed_type)
    EditText edType;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    @BindView(R.id.tv_pda)
    TextView tvPda;
    @BindView(R.id.ed_printnum)
    EditText edPrintnum;
    private BasicShareUtil share;
    ArrayAdapter<String> adapter;
    private String string;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ip_port);
        mContext = this;
        ButterKnife.bind(this);
        tvTitle.setText(getResources().getString(R.string.server_set));
        share = BasicShareUtil.getInstance(mContext);
        if (!share.getIP().equals("")) {
            edIp.setText(share.getIP());
            edPort.setText(share.getPort());
//            edUrl.setText(Hawk.get(Config.Cloud_Url, "http://120.77.206.67/K3Cloud/"));
        }
//        else {
            if (App.DataBaseSetting.equals("K3DBConfiger201910115049165")) {
                edUrl.setText(Hawk.get(Config.Cloud_Url, "http://sanger.gnway.cc:8090/K3Cloud/"));
//                edIp.setText("120.77.206.67");
//                edPort.setText("8080");
            } else {
                edUrl.setText(Hawk.get(Config.Cloud_Url, "http://sanger.gnway.cc:8090/K3Cloud/"));
//                edIp.setText("192.168.0.136");
//                edPort.setText("8082");
            }
//        }
        edPrintnum.setText(Hawk.get(Config.PrintNum, "2"));
    }
    String pdaMsg;
    @Override
    protected void initData() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Config.PDA_Type);
        spPda.setAdapter(adapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                spPda.setSelection(App.PDA_Choose);
            }
        }, 100);

        if (null != Hawk.get(Config.SaveTime, null)) {
            UseTimeBean bean = Hawk.get(Config.SaveTime);
            pdaMsg = "有效期：" + dealTime(bean.endTime) + "   \n用户码：" + Hawk.get(Config.PDA_IMIE, "获取失败")+"  \n注册码："+Hawk.get(Config.PDA_RegisterCode,"获取失败");
//            tvEndtime.setText("有效期：" + dealTime(bean.endTime) + "   用户码：" + Hawk.get(Config.PDA_IMIE, "获取失败")+"  注册码："+Hawk.get(Config.PDA_RegisterCode,"获取失败"));
        } else {
            pdaMsg ="获取时间失效" + "   \n用户码：" + Hawk.get(Config.PDA_IMIE, "获取失败")+"  \n注册码："+Hawk.get(Config.PDA_RegisterCode,"获取失败");
//            tvEndtime.setText("获取时间失效" + "   用户码：" + Hawk.get(Config.PDA_IMIE, "获取失败")+"  注册码："+Hawk.get(Config.PDA_RegisterCode,"获取失败"));
        }
        edType.setText(Hawk.get(Config.PDA_Project_Type,"1"));
    }

    @Override
    protected void initListener() {
        spPda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                string = adapter.getItem(i);
//                private String[] arr={"G02A设备","8000设备","5000设备"手机端};
                if ("G02A设备".equals(string)) {
                    Hawk.put(Config.PDA, 1);
                    Toast.showText(mContext, "选择了G02A设备" + App.PDA_Choose);
                } else if ("8000设备".equals(string)) {
                    Hawk.put(Config.PDA, 2);
                    Toast.showText(mContext, "选择了8000设备" + App.PDA_Choose);
                } else if ("5000设备".equals(string)) {
                    Hawk.put(Config.PDA, 3);
                    Toast.showText(mContext, "选择了5000设备" + App.PDA_Choose);
                } else if ("手机端".equals(string)) {
                    Hawk.put(Config.PDA, 4);
                    Toast.showText(mContext, "选择了手机端" + App.PDA_Choose);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnSave.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (App.DataBaseSetting.equals("K3DBConfiger201910115049165")){
                    Hawk.put(Config.DataBase,"K3DBConfiger201910115049165");
                    App.DataBaseSetting = "K3DBConfiger201910115049165";
                }else{
                    Hawk.put(Config.DataBase,"K3DBConfigerRY");
                    App.DataBaseSetting = "K3DBConfigerRY";
                }
                Toast.showText(mContext,"已切换到服务器地址："+App.DataBaseSetting);
                return true;
            }
        });

        tvPda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("PDA信息")
                        .setMessage(pdaMsg)
                        .setPositiveButton("确认", null)
                        .create().show();
            }
        });
    }

    @Override
    protected void OnReceive(String code) {
        Toast.showText(mContext, code);
    }


    @Override
    protected void onDestroy() {
//        App.PDA_Choose = Hawk.get(Config.PDA, 2);
        if (!edUrl.getText().toString().equals("")) {
            Hawk.put(Config.Cloud_Url, edUrl.getText().toString());
        }
        if (!edPrintnum.getText().toString().equals("")) {
            Hawk.put(Config.PrintNum, edPrintnum.getText().toString());
        }
        if (!edType.getText().toString().equals("")) {
            Hawk.put(Config.PDA_Project_Type, edType.getText().toString());
        }
        super.onDestroy();
    }

    @OnClick({R.id.btn_save, R.id.btn_back, R.id.tv_title, R.id.btn_loginout, R.id.iv_check})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.btn_loginout:
                AlertDialog.Builder ab = new AlertDialog.Builder(mContext);
                ab.setTitle("是否登出");
                ab.setMessage("登出后，需重新注册才能使用");
                ab.setPositiveButton("登出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getRService().doIOAction(WebApi.RegisterDelete, Hawk.get(Config.PDA_IMIE,""), new MySubscribe<CommonResponse>() {
                            @Override
                            public void onNext(CommonResponse commonResponse) {
                                super.onNext(commonResponse);
                                if (!commonResponse.state)return;
                                Toast.showText(mContext,"用户已登出");
                            }

                            @Override
                            public void onError(Throwable e) {
//                        super.onError(e);
                                Toast.showText(mContext,"用户登出失败");
                            }
                        });
                    }
                });
                ab.setNegativeButton("取消", null);
                final AlertDialog alertDialog = ab.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
                break;
            case R.id.btn_save:
                if (!edPort.getText().toString().equals("") && !edIp.getText().toString().equals("")) {
                    share.setIP(edIp.getText().toString());
                    share.setPort(edPort.getText().toString());
                    finish();
                }
                break;
            case R.id.iv_check:
                if (!edPort.getText().toString().equals("") && !edIp.getText().toString().equals("")) {
                    share.setIP(edIp.getText().toString());
                    share.setPort(edPort.getText().toString());
//                    finish();
                }
                LoadingUtil.showDialog(mContext, "正在检测服务端是否连通...");
                App.getRService().doIOAction("TestServlet", "检测服务端是否连通", new MySubscribe<CommonResponse>() {
                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        super.onNext(commonResponse);
                        LoadingUtil.dismiss();
                        LoadingUtil.showAlter(mContext, "连接成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        LoadingUtil.dismiss();
                        LoadingUtil.showAlter(mContext, "连接失败");
                    }
                });
                break;
        }
    }

}
