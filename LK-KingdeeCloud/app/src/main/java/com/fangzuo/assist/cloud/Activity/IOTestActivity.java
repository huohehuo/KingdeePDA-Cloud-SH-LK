//package com.fangzuo.assist.cloud.Activity;
//
//import android.content.Context;
//import android.databinding.DataBindingUtil;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//
//import com.fangzuo.assist.cloud.Activity.Crash.App;
//import com.fangzuo.assist.cloud.Beans.BackData;
//import com.fangzuo.assist.cloud.R;
//import com.fangzuo.assist.cloud.RxSerivce.RService;
//import com.fangzuo.assist.cloud.RxSerivce.ToSubscribe;
//import com.fangzuo.assist.cloud.Utils.Config;
//import com.fangzuo.assist.cloud.Utils.Info;
//import com.fangzuo.assist.cloud.Utils.Lg;
//import com.fangzuo.assist.cloud.Utils.Toast;
//import com.fangzuo.assist.cloud.databinding.ActivityIotestBinding;
//import com.google.gson.Gson;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//
//import org.apache.http.Header;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//public class IOTestActivity extends AppCompatActivity {
//
//
//    ActivityIotestBinding binding;
//    Context mContext;
//    Gson gson;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_iotest);
//        mContext=this;
//        gson = new Gson();
//
//
//        binding.btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                new Thread(new Runnable() {
////                    @Override
////                    public void run() {
////                        try {
////                            if (InvokeHelper.Login("5beb8db650b4cf", "Administrator", "888888", 2052)) {
////                                System.out.println("login success");
////                                Lg.e("login success");
////                                InvokeHelper.View("STK_InStock","{\"Number\":\"CGRK00006\"}");
////                            }
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                        }
////                    }
////                }).start();
//
//                login();
////                post(mContext,"http://192.168.0.201/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc","");
//            }
//        });
//        binding.btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//        find();
//// check("http://192.168.0.201/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.View.common.kdsvc");
//            }
//        });
//        binding.btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                String data="{\"Creator\":\"\",\"NeedUpDateFields\":[],\"NeedReturnFields\":[],\"IsDeleteEntry\":\"True\",\"SubSystemId\":\"\",\"IsVerifyBaseDataField\":\"false\",\"IsEntryBatchFill\":\"True\",\"ValidateFlag\":\"True\",\"NumberSearch\":\"True\",\"InterationFlags\":\"\",\"Model\":{\"FACCEPTANCE\":{\"FNumber\":\"\"},\"FBillNo\":\"\",\"FBillTypeID\":{\"FNUMBER\":\"QTRKD01_SYS\"},\"FDEPTID\":{\"FNumber\":\"BM000001\"},\"FDate\":\"2018-12-26\",\"FEntity\":[{\"FKEEPERID\":{\"FNumber\":\"100\"},\"FKEEPERTYPEID\":\"BD_KeeperOrg\",\"FLot\":{\"FNumber\":\"2018122601\"},\"FMATERIALID\":{\"FNumber\":\"02.03.13.120.A.20.200\"},\"FOWNERID\":{\"FNumber\":\"100\"},\"FOWNERTYPEID\":\"BD_OwnerOrg\",\"FQty\":\"500\",\"FSTOCKSTATUSID\":{\"FNumber\":\"KCZT01_SYS\"},\"FStockId\":{\"FNumber\":\"CK004\"},\"FUnitID\":{\"FNumber\":\"Pcs\"}}],\"FOwnerIdHead\":{\"FNumber\":\"100\"},\"FOwnerTypeIdHead\":\"BD_OwnerOrg\",\"FSTOCKERID\":{\"FNAME\":\"\"},\"FSUPPLIERID\":{\"FNumber\":\"VEN00001\"},\"FStockDirect\":\"GENERAL\",\"FStockOrgId\":{\"FNumber\":\"100\"}}}";
////                String data="{\"Creator\":\"\",\"NeedUpDateFields\":[],\"NeedReturnFields\":[],\"IsDeleteEntry\":\"True\",\"SubSystemId\":\"\",\"IsVerifyBaseDataField\":\"false\",\"IsEntryBatchFill\":\"True\",\"ValidateFlag\":\"True\",\"NumberSearch\":\"True\",\"InterationFlags\":\"\",\"Model\":{\"FACCEPTANCE\":{\"FNumber\":\"\"},\"FBillNo\":\"\",\"FBillTypeID\":{\"FNUMBER\":\"QTRKD01_SYS\"},\"FDEPTID\":{\"FNumber\":\"BM000001\"},\"FDate\":\"2018-12-26\",\"FLOT\":{\"FNumber\":\"2018122601\"},\"FEntity\":[{\"FKEEPERID\":{\"FNumber\":\"100\"},\"FKEEPERTYPEID\":\"BD_KeeperOrg\",\"FLOT\":{\"FNumber\":\"2018122601\"},\"FMATERIALID\":{\"FNumber\":\"02.03.13.120.A.20.200\"},\"FOWNERID\":{\"FNumber\":\"100\"},\"FOWNERTYPEID\":\"BD_OwnerOrg\",\"FQty\":\"500\",\"FSTOCKSTATUSID\":{\"FNumber\":\"KCZT01_SYS\"},\"FStockId\":{\"FNumber\":\"CK004\"},\"FUnitID\":{\"FNumber\":\"Pcs\"}}],\"FOwnerIdHead\":{\"FNumber\":\"100\"},\"FOwnerTypeIdHead\":\"BD_OwnerOrg\",\"FSTOCKERID\":{\"FNAME\":\"\"},\"FSUPPLIERID\":{\"FNumber\":\"VEN00001\"},\"FStockDirect\":\"GENERAL\",\"FStockOrgId\":{\"FNumber\":\"100\"}}}";
////                String data="{\"Creator\":\"\",\"NeedUpDateFields\":[],\"NeedReturnFields\":[],\"IsDeleteEntry\":\"True\",\"SubSystemId\":\"\",\"IsVerifyBaseDataField\":\"false\",\"IsEntryBatchFill\":\"True\",\"ValidateFlag\":\"True\",\"NumberSearch\":\"True\",\"InterationFlags\":\"\",\"Model\":{\"FACCEPTANCE\":{\"FNumber\":\"\"},\"FBillNo\":\"\",\"FBillTypeID\":{\"FNUMBER\":\"QTRKD01_SYS\"},\"FDEPTID\":{\"FNumber\":\"BM000001\"},\"FDate\":\"2018-12-26\",\"FEntity\":[{\"FKEEPERID\":{\"FNumber\":\"100\"},\"FKEEPERTYPEID\":\"BD_KeeperOrg\",\"FLOT\":{\"FNUMBER\":\"20181226007\"},\"FMATERIALID\":{\"FNumber\":\"02.03.13.120.A.20.200\"},\"FOWNERID\":{\"FNumber\":\"100\"},\"FOWNERTYPEID\":\"BD_OwnerOrg\",\"FQty\":\"500\",\"FSTOCKSTATUSID\":{\"FNumber\":\"KCZT01_SYS\"},\"FStockId\":{\"FNumber\":\"CK004\"},\"FUnitID\":{\"FNumber\":\"Pcs\"}}],\"FOwnerIdHead\":{\"FNumber\":\"100\"},\"FOwnerTypeIdHead\":\"BD_OwnerOrg\",\"FSTOCKERID\":{\"FNAME\":\"\"},\"FSUPPLIERID\":{\"FNumber\":\"VEN00001\"},\"FStockDirect\":\"GENERAL\",\"FStockOrgId\":{\"FNumber\":\"100\"}}}";
////                String data="{\"Creator\":\"\",\"NeedUpDateFields\":[],\"NeedReturnFields\":[],\"IsDeleteEntry\":\"True\",\"SubSystemId\":\"\",\"IsVerifyBaseDataField\":\"True\",\"IsEntryBatchFill\":\"True\",\"ValidateFlag\":\"True\",\"NumberSearch\":\"True\",\"InterationFlags\":\"\",\"Model\":{\"FACCEPTANCE\":{\"FNumber\":\"\"},\"FBillNo\":\"\",\"FBillTypeID\":{\"FNUMBER\":\"QTRKD01_SYS\"},\"FDEPTID\":{\"FNumber\":\"BM000001\"},\"FDate\":\"2018-12-26\",\"FEntity\":[{\"FKEEPERID\":{\"FNumber\":\"100\"},\"FKEEPERTYPEID\":\"BD_KeeperOrg\",\"FLOT\":{\"FNumber\":\"\"},\"FMATERIALID\":{\"FNumber\":\"CH4421\"},\"FOWNERID\":{\"FNumber\":\"100\"},\"FOWNERTYPEID\":\"BD_OwnerOrg\",\"FQty\":\"500\",\"FSTOCKSTATUSID\":{\"FNumber\":\"KCZT01_SYS\"},\"FStockId\":{\"FNumber\":\"CK004\"},\"FUnitID\":{\"FNumber\":\"Pcs\"}}],\"FOwnerIdHead\":{\"FNumber\":\"100\"},\"FOwnerTypeIdHead\":\"BD_OwnerOrg\",\"FSTOCKERID\":{\"FNAME\":\"\"},\"FSUPPLIERID\":{\"FNumber\":\"VEN00001\"},\"FStockDirect\":\"GENERAL\",\"FStockOrgId\":{\"FNumber\":\"100\"}}}";
////                String data="{\"Creator\":\"\",\"NeedUpDateFields\":[],\"NeedReturnFields\":[],\"IsDeleteEntry\":\"True\",\"SubSystemId\":\"\",\"IsVerifyBaseDataField\":\"True\",\"IsEntryBatchFill\":\"True\",\"ValidateFlag\":\"True\",\"NumberSearch\":\"True\",\"InterationFlags\":\"\",\"Model\":{\"FACCEPTANCE\":{\"FNumber\":\"\"},\"FBillNo\":\"\",\"FBillTypeID\":{\"FNUMBER\":\"QTRKD01_SYS\"},\"FDEPTID\":{\"FNumber\":\"BM000001\"},\"FDate\":\"2018-12-26\",\"FEntity\":[{\"FKEEPERID\":{\"FNumber\":\"100\"},\"FKEEPERTYPEID\":\"BD_KeeperOrg\",\"FLOT\":{\"FNUMBER\":\"2018122601\",\"FLOTNAME\":\"2018122601\"},\"FMATERIALID\":{\"FNumber\":\"02.03.13.120.A.20.200\"},\"FOWNERID\":{\"FNumber\":\"100\"},\"FOWNERTYPEID\":\"BD_OwnerOrg\",\"FQty\":\"500\",\"FSTOCKSTATUSID\":{\"FNumber\":\"KCZT01_SYS\"},\"FStockId\":{\"FNumber\":\"CK004\"},\"FUnitID\":{\"FNumber\":\"Pcs\"}}],\"FOwnerIdHead\":{\"FNumber\":\"100\"},\"FOwnerTypeIdHead\":\"BD_OwnerOrg\",\"FSTOCKERID\":{\"FNAME\":\"\"},\"FSUPPLIERID\":{\"FNumber\":\"VEN00001\"},\"FStockDirect\":\"GENERAL\",\"FStockOrgId\":{\"FNumber\":\"100\"}}}";
////                String data="{\"Creator\":\"\",\"NeedUpDateFields\":[],\"NeedReturnFields\":[],\"IsDeleteEntry\":\"True\",\"SubSystemId\":\"\",\"IsVerifyBaseDataField\":\"True\",\"IsEntryBatchFill\":\"True\",\"ValidateFlag\":\"True\",\"NumberSearch\":\"True\",\"InterationFlags\":\"\",\"Model\":{\"FACCEPTANCE\":{\"FNumber\":\"\"},\"FBillNo\":\"\",\"FBillTypeID\":{\"FNUMBER\":\"QTRKD01_SYS\"},\"FDEPTID\":{\"FNumber\":\"BM000001\"},\"FDate\":\"2018-12-26\",\"FEntity\":[{\"FKEEPERID\":{\"FNumber\":\"100\"},\"FKEEPERTYPEID\":\"BD_KeeperOrg\",\"FLOT\":{\"FLOTID\":\"0\",\"FNUMBER\":\"2018122101\",\"FNAME\":\"2018122101\",\"FLOT_TEXT\":\"2018122101\"},\"FMATERIALID\":{\"FNumber\":\"CH4422\"},\"FOWNERID\":{\"FNumber\":\"100\"},\"FOWNERTYPEID\":\"BD_OwnerOrg\",\"FQty\":\"500\",\"FSTOCKSTATUSID\":{\"FNumber\":\"KCZT01_SYS\"},\"FStockId\":{\"FNumber\":\"CK001\"},\"FUnitID\":{\"FNumber\":\"Pcs\"}}],\"FOwnerIdHead\":{\"FNumber\":\"100\"},\"FOwnerTypeIdHead\":\"BD_OwnerOrg\",\"FSTOCKERID\":{\"FNAME\":\"\"},\"FSUPPLIERID\":{\"FNumber\":\"VEN00005\"},\"FStockDirect\":\"GENERAL\",\"FStockOrgId\":{\"FNumber\":\"100\"}}}";
////                String data="{\"IsDeleteEntry\":false,\"IsEntryBatchFill\":true,\"IsVerifyBaseDataField\":true,\"Model\":{\"FACCEPTANCE\":{\"FNumber\":\"\"},\"FBillNo\":\"\",\"FBillTypeID\":{\"FNUMBER\":\"QTRKD01_BSB\"},\"FDEPTID\":{\"FNumber\":\"BM000004\"},\"FDate\":\"2018-12-27\",\"FEntity\":[{\"FKEEPERID\":{\"FNumber\":\"100\"},\"FKEEPERTYPEID\":\"BD_KeeperOrg\",\"FOWNERID\":{\"FNumber\":\"100\"},\"FLOT\":{\"FNumber\":\"20181206\"},\"FMATERIALID\":{\"FNumber\":\"CH4422\"},\"FOWNERTYPEID\":\"BD_OwnerOrg\",\"FQty\":\"500\",\"FSTOCKSTATUSID\":{\"FNumber\":\"KCZT01_SYS\"},\"FStockId\":{\"FNumber\":\"CK001\"},\"FUnitID\":{\"FNumber\":\"Pcs\"}}],\"FOwnerIdHead\":{\"FNumber\":\"100\"},\"FOwnerTypeIdHead\":\"BD_OwnerOrg\",\"FSTOCKERID\":{\"FNAME\":\"\"},\"FSUPPLIERID\":{\"FNumber\":\"VEN00001\"},\"FStockDirect\":\"GENERAL\",\"FStockOrgId\":{\"FNumber\":\"100\"}}}";
//                String data="{\"IsDeleteEntry\":true,\"IsEntryBatchFill\":false,\"Model\":[{\"FACCEPTANCE\":{\"FNumber\":\"\"},\"FBillNo\":\"\",\"FBillTypeID\":{\"FNUMBER\":\"QTRKD01_BSB\"},\"FDEPTID\":{\"FNumber\":\"BM000004\"},\"FDate\":\"2018-12-27\",\"FEntity\":[{\"FMATERIALID\":{\"FNumber\":\"02.03.13.120.A.20.200\"},\"FKEEPERID\":{\"FNumber\":\"100\"},\"FKEEPERTYPEID\":\"BD_KeeperOrg\",\"FLOT\":{\"FNumber\":\"2018122702\"},\"FOWNERID\":{\"FNumber\":\"100\"},\"FOWNERTYPEID\":\"BD_OwnerOrg\",\"FQty\":\"500\",\"FSTOCKSTATUSID\":{\"FNumber\":\"KCZT01_SYS\"},\"FStockId\":{\"FNumber\":\"CK004\"},\"FUnitID\":{\"FNumber\":\"Pcs\"}}],\"FOwnerIdHead\":{\"FNumber\":\"100\"},\"FOwnerTypeIdHead\":\"BD_OwnerOrg\",\"FSTOCKERID\":{\"FNAME\":\"\"},\"FSUPPLIERID\":{\"FNumber\":\"VEN00001\"},\"FStockDirect\":\"GENERAL\",\"FStockOrgId\":{\"FNumber\":\"100\"}}],\"NumberSearch\":false,\"ValidateFlag\":false}";
//
//                upload(Config.C_BatcnSave, Info.getJson(Config.OtherInStoreActivity,data));
//            }
//        });
//
//
//
//
//
//    }
//
//    public void post(Context context, String url, String json){
//        Log.e("Asynchttp-提交数据：",json+" "+url);
//        final AsyncHttpClient client = new AsyncHttpClient();
//        client.setTimeout(10000);
//        //组装登录数据
//        JSONArray jParas = new JSONArray();
//        jParas.put("5beb8db650b4cf");// 帐套Id
//        jParas.put("Administrator");// 用户名
//        jParas.put("888888");// 密码
//        jParas.put(2052);// 语言T
////        BasicShareUtil share = BasicShareUtil.getInstance(context);
//        RequestParams params = new RequestParams();
//        params.add("format", "1");
//        params.add("useragent", "ApiClient");
//        params.add("rid", "1111");
//        params.add("parameters", RService.chinaToUnicode(jParas.toString()));
//        params.add("timestamp", new Date().toString());
//        client.post(url, params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int i, Header[] headers, byte[] bytes) {
//                if(!new String(bytes).equals("")&&new String(bytes)!=null){
////                    Log.e("Asynchttp--得到数据：",new String(bytes));
//                    String string = new String(bytes);
//                    Lg.e(string+client.toString());
//                    Toast.showText(mContext,"成功"+string);
//
//                }else{
//                    Lg.e("服务器未响应"+client.toString());
//                    Toast.showText(mContext,"服务器未响应"+client.toString());
//
////                    response.onFailed("服务器未响应",client);
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                Lg.e("网络错误"+client.toString());
////                response.onFailed("网络错误",client);
//            }
//        });
//
//    }
//    K3CloudApiClient client;
//
//
//
//    private void login(){
////        UUID uuid = UUID.randomUUID();
////        final int hashCode = uuid.toString().hashCode();
////        //组装登录数据
////        JSONArray jParas = new JSONArray();
////        jParas.put("5beb8db650b4cf");// 帐套Id
////        jParas.put("Administrator");// 用户名
////        jParas.put("888888");// 密码
////        jParas.put(2052);// 语言T
////        JSONObject jObj = new JSONObject();
////        try {
////            jObj.put("format", 1);
////            jObj.put("useragent", "ApiClient");
////            jObj.put("rid", UUID.randomUUID().toString().hashCode());
////            jObj.put("parameters", RService.chinaToUnicode(jParas.toString()));
////            jObj.put("timestamp", new Date().toString());
////            jObj.put("v", "1.0");
////            Lg.e("测试"+jObj.toString());
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
////
////        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jObj.toString());
//////        App.getRService().doIOActionLoginT(Config.C_Login, body,new ToSubscribe<BackDataLogin>() {
////        App.CloudService().doIOActionLogin(Config.C_Login, jParas.toString(),new ToSubscribe<BackDataLogin>() {
////            @Override
////            public void onNext(BackDataLogin backDataLogin) {
////                super.onNext(backDataLogin);
////                Lg.e(backDataLogin.toString());
////                Toast.showText(mContext,backDataLogin.toString());
////
////
////
////                List<Object> Parameters = new ArrayList<>();
////                //业务对象Id
////                String formid = "STK_InStock";//发票
////                Parameters.add(formid);
////                //Json字串
////                Map<String, Object> jOb = new HashMap<>();
//////                jOb.put("CreateOrgId","");
////                jOb.put("Number","CGRK00006");
//////                jOb.put("Ids","");
////                    String data = "{\"Number\":\"CGRK00006\"}";
//////                    Parameters.add(data);
////                JSONArray jsonArray = new JSONArray();
////                jsonArray.put(formid);
////                jsonArray.put(data);
////                JSONObject jObj2 = new JSONObject();
////                try {
////                    jObj2.put("format", 1);
////                    jObj2.put("useragent", "ApiClient");
////                    jObj2.put("rid", hashCode);
////                    jObj2.put("parameters", RService.chinaToUnicode(jsonArray.toString()));
////                    jObj2.put("timestamp", new Date().toString());
////                    jObj2.put("v", "1.0");
////                    Lg.e("测试"+jObj2.toString());
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////                RequestBody body2=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jObj2.toString());
////
//////                    String data = "[\"STK_InStock\",\"{\\\"Number\\\":\\\"CGRK00006\\\"}\"]\n";
////                App.getRService().doIOAction(Config.C_View, jsonArray.toString(),new ToSubscribe<BackData>() {
////                    @Override
////                    public void onNext(BackData backDataLogin) {
////                        super.onNext(backDataLogin);
////                        Lg.e(backDataLogin.toString());
////                        Toast.showText(mContext,backDataLogin.toString());
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        super.onError(e);
////                        Lg.e(e.toString());
////
////                    }
////                });
////
////
////
////
////            }
////
////            @Override
////            public void onError(Throwable e) {
////                super.onError(e);
////                Lg.e(e.toString());
////
////            }
////        });
//////                    client = new K3CloudApiClient("http://win-v52dshi1bh2/K3Cloud/");
//////                    boolean ret;
//////                    try {
//////                        ret = client.login(
//////                                "5beb8db650b4cf",
//////                                "Administrator",
//////                                "888888",2052);
//////                        if (ret){
//////                            android.widget.Toast.makeText(LoginActivity.this, "登录成功", android.widget.Toast.LENGTH_SHORT).show();
//////                        }else{
//////                            android.widget.Toast.makeText(LoginActivity.this, "登录失败", android.widget.Toast.LENGTH_SHORT).show();
//////                        }
//////                    } catch (Exception e) {
//////                        android.widget.Toast.makeText(LoginActivity.this, "错误："+e.toString(), android.widget.Toast.LENGTH_SHORT).show();
//////                        e.printStackTrace();
//////                    }
////
//////
//////                        useBean bean = new useBean();
//////                        bean.acctID="5beb8db650b4cf";
//////                        bean.username="Administrator";
//////                        bean.lcid="888888";
//////                        post(mContext,"http://win-v52dshi1bh2/K3Cloud/",gson.toJson(bean));
//
//    }
//
//    private void check(String url){
//
//
//
//        final AsyncHttpClient client = new AsyncHttpClient();
//        client.setTimeout(10000);
//        client.addHeader("Content-Type", "application/json;charset=UTF-8");
//        List<Object> Parameters = new ArrayList<>();
//        //业务对象Id
//        String formid = "STK_InStock";//发票
//        Parameters.add(formid);
//        //Json字串
//        Map<String, Object> jOb = new HashMap<>();
////        jOb.put("CreateOrgId","");
//        jOb.put("Number","CGRK00006");
////        jOb.put("Ids","");
//        String data = "{\"Number\":\"CGRK00006\"}";
////                    Parameters.add(data);
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.put(formid);
//        jsonArray.put(data);
//
////        JSONObject jObj2 = new JSONObject();
////        try {
////            jObj2.put("format", 1);
////            jObj2.put("useragent", "ApiClient");
////            jObj2.put("rid", "1111");
////            jObj2.put("parameters", RService.chinaToUnicode(jsonArray.toString()));
////            jObj2.put("timestamp", new Date().toString());
////            jObj2.put("v", "1.0");
////            Lg.e("测试"+jObj2.toString());
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//        UUID uuid = UUID.randomUUID();
//        int hashCode = uuid.toString().hashCode();
////        BasicShareUtil share = BasicShareUtil.getInstance(context);
//        RequestParams params = new RequestParams();
//        params.add("format", "1");
//        params.add("useragent", "ApiClient");
//        params.add("rid", hashCode+"");
//        params.add("parameters", RService.chinaToUnicode(jsonArray.toString()));
//        params.add("timestamp", new Date().toString());
//        Lg.e("回单数据："+params.toString());
//        client.post(url, params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int i, Header[] headers, byte[] bytes) {
//                if(!new String(bytes).equals("")&&new String(bytes)!=null){
////                    Log.e("Asynchttp--得到数据：",new String(bytes));
//                    String string = new String(bytes);
//                    Lg.e(string+client.toString());
//                    Toast.showText(mContext,"成功"+string);
//
//                }else{
//                    Lg.e("服务器未响应"+client.toString());
//                    Toast.showText(mContext,"服务器未响应"+client.toString());
//
////                    response.onFailed("服务器未响应",client);
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                Lg.e("网络错误"+client.toString());
////                response.onFailed("网络错误",client);
//            }
//        });
//    }
//
//    private void find(){
//        List<Object> Parameters = new ArrayList<>();
//        //业务对象Id
//        String formid = "STK_InStock";//发票
//        Parameters.add(formid);
//        //Json字串
//        Map<String, Object> jOb = new HashMap<>();
////                jOb.put("CreateOrgId","");
//        jOb.put("Number","CGRK00006");
////                jOb.put("Ids","");
//        String data = "{\"Number\":\"CGRK00006\"}";
////                    Parameters.add(data);
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.put(formid);
//        jsonArray.put(data);
//        JSONObject jObj2 = new JSONObject();
//        try {
//            jObj2.put("format", 1);
//            jObj2.put("useragent", "ApiClient");
//            jObj2.put("rid", UUID.randomUUID().toString().hashCode());
//            jObj2.put("parameters", RService.chinaToUnicode(jsonArray.toString()));
//            jObj2.put("timestamp", new Date().toString());
//            jObj2.put("v", "1.0");
//            Lg.e("测试"+jObj2.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
////        RequestBody body2=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jObj2.toString());
//
////                    String data = "[\"STK_InStock\",\"{\\\"Number\\\":\\\"CGRK00006\\\"}\"]\n";
//        App.CloudService().doIOAction(Config.C_View, jsonArray.toString(),new ToSubscribe<BackData>() {
//            @Override
//            public void onNext(BackData backDataLogin) {
//                super.onNext(backDataLogin);
//                Lg.e(backDataLogin.toString());
//                Toast.showText(mContext,backDataLogin.toString());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                Lg.e(e.toString());
//
//            }
//        });
//    }
//    //统一回单数据请求
//    public void upload(String io,String json){
//        Lg.e("回单数据："+json);
//        App.CloudService().doIOAction(io, json, new ToSubscribe<BackData>() {
//            @Override
//            public void onNext(BackData backData) {
//                super.onNext(backData);
//                if (backData.getResult().getResponseStatus().getIsSuccess()) {
//                    Lg.e("成功：",backData);
//
//                } else {
//                    Lg.e("失败：",backData);
//
//                }
//            }
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                Toast.showText(mContext,"错误："+e.toString());
//            }
//        });
//    }
//}
