package com.fangzuo.assist.cloud.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.ClientRyAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Dao.Client;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.WebApi;
import com.fangzuo.greendao.gen.DaoSession;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;

public class SpinnerClientDlg extends RelativeLayout {

    private ImageView ivFind;
    private EditText editText;
    private boolean showEd = false;
    //    private SpinnerAdapter adapter;
    private DaoSession daoSession;
    private ArrayList<String> autoList;
    private BasicShareUtil share;
    private ArrayList<Client> container;
    private ArrayList<Client> containerTemp;
    private ClientRyAdapter adapter;
    private String autoString="";//用于联网时，再次去自动设置值
    private String saveKeyString="";//用于保存数据的key
    private String employeeId="";
    private String employeeName="";
    private String employeeNumber="";
    private String T="客户：";     //3
    private SearchBean.S2Product s2Product;//用于数据查找...

    public SpinnerClientDlg(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.view_client_dlg_spinner, this);
        daoSession = GreenDaoManager.getmInstance(context).getDaoSession();

        autoList = new ArrayList<>();
        share = BasicShareUtil.getInstance(context);
        container = new ArrayList<>();
        containerTemp = new ArrayList<>();
        // 获取控件
        editText=findViewById(R.id.et_search);
        ivFind=findViewById(R.id.iv_find);
        TypedArray attrArray = context.obtainStyledAttributes(attributeSet, R.styleable.ClientSpinner);
        int count = attrArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attrName = attrArray.getIndex(i);
            switch (attrName) {
//                case R.styleable.ClientSpinner_clientspinner_name:
//                    mTitleTv.setText(attrArray.getString(R.styleable.ClientSpinner_clientspinner_name));
//                    break;
//                case R.styleable.ClientSpinner_clientspinner_size:
//                    mTitleTv.setText(attrArray.getString(R.styleable.ClientSpinner_clientspinner_name));
//                    mTitleTv.setTextSize(attrArray.getDimension(R.styleable.ClientSpinner_clientspinner_size,10));
//                    break;
                case R.styleable.ClientSpinner_clientspinner_hint:
                    editText.setHint(attrArray.getString(R.styleable.ClientSpinner_clientspinner_hint));
                    break;
            }
        }
        attrArray.recycle();

        adapter = new ClientRyAdapter(context);

//        mSp.setAdapter(adapter);
//        ClientDao inStoreTypeDao = daoSession.getClientDao();
//        List<Client> inStoreTypes = inStoreTypeDao.loadAll();
//        container.add(new Client("","","",""));
//        container.addAll(inStoreTypes);
//        adapter.notifyDataSetChanged();
//
////        if (share.getIsOL()) {
//            ArrayList<Integer> choose = new ArrayList<>();
//            choose.add(13);
//            String json = JsonCreater.DownLoadData(
//                    share.getDatabaseIp(),
//                    share.getDatabasePort(),
//                    share.getDataBaseUser(),
//                    share.getDataBasePass(),
//                    share.getDataBase(),
//                    share.getVersion(),
//                    choose
//            );
//            App.getRService().downloadData(json, new MySubscribe<CommonResponse>() {
//                @Override
//                public void onNext(CommonResponse commonResponse) {
////                    Lg.e("得到Client："+commonResponse.returnJson);
//                    DownloadReturnBean dBean = JsonCreater.gson.fromJson(commonResponse.returnJson, DownloadReturnBean.class);
//                    Lg.e("得到Client：",dBean.clients.size());
//                    if (dBean.clients.size() > 0 && container.size()<=1){
//                        ClientDao payTypeDao = daoSession.getClientDao();
//                        payTypeDao.deleteAll();
//                        payTypeDao.insertOrReplaceInTx(dBean.clients);
//                        payTypeDao.detachAll();
//                        container.add(new Client("","","",""));
//                        container.addAll(dBean.clients);
//                        adapter.notifyDataSetChanged();
////                        setAutoSelection(saveKeyString,autoString);
//                    }
//                }
//
//                @Override
//                public void onError(Throwable e) {
////                    LoadingUtil.dismiss();
////                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Updata_Error,e.toString()));
//                }
//            });
////        }


//        setAutoSelection(saveKeyString,autoString);

//        mSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Client employee = (Client) adapter.getItem(i);
//                employeeId = employee.FItemID;
//                employeeName = employee.FName;
//                employeeNumber = employee.FNumber;
//                Lg.e("选中"+T,employee);
//                Hawk.put(saveKeyString,employee.FName);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        ivFind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDlg(context);
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Lg.e("变化前....");
                containerTemp.clear();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Lg.e("变化中....");
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getText().toString().equals("")){
                    employeeId = "";
                    employeeName = "";
                    employeeNumber = "";
                }
            }
        });

    }

    AlertDialog alertDialog;
    private void showDlg(Context context){
        s2Product = new SearchBean.S2Product();
        s2Product.likeOr = editText.getText().toString();
        s2Product.FOrg = "";
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setTitle("客户查找");
        View v = LayoutInflater.from(context).inflate(R.layout.show_client, null);
        final EasyRecyclerView listView     = v.findViewById(R.id.lv_client);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(context));
        App.getRService().doIOAction(WebApi.CLIENTSEARCHLIKE, new Gson().toJson(new SearchBean(SearchBean.product_for_like,new Gson().toJson(s2Product))), new MySubscribe<CommonResponse>() {
            @Override
            public void onNext(CommonResponse commonResponse) {
                super.onNext(commonResponse);
                if (!commonResponse.state)return;
                Lg.e("获得客户数据");
                DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                if (dBean.clients!=null && dBean.clients.size()>0){
                    Lg.e("获得客户数据",dBean.clients);
                    adapter.clear();
                    adapter.addAll(dBean.clients);
                    adapter.notifyDataSetChanged();
                }else{
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                    employeeId = "";
                    employeeName = "";
                    employeeNumber = "";
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                employeeId = "";
                employeeName = "";
                employeeNumber = "";

            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Client storage = adapter.getAllData().get(position);
                Lg.e("点击客户：",storage);
                employeeId = storage.FMASTERID;
                employeeName = storage.FName;
                employeeNumber = storage.FNumber;
                editText.setText(employeeName);
                alertDialog.dismiss();
            }
        });

        ab.setView(v);
        ab.setPositiveButton("返回", null);

        alertDialog = ab.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    // 为左侧返回按钮添加自定义点击事件
//    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
//        mSp.setOnItemSelectedListener(listener);
//    }
//    public void setAdapter(SpinnerAdapter adapter){
//        this.adapter = adapter;
//        mSp.setAdapter(adapter);
//    }
//    public void setSelection(int i){
//        mSp.setSelection(i);
//    }


    public String getDataId() {
        return employeeId == null ? "" : employeeId;
    }

    public String getDataName() {
        return employeeName == null ? "" : employeeName;
    }
    public String getDataNumber() {
        return employeeNumber == null ? "" : employeeNumber;
    }
//    /**
//     *
//     * @param saveKeyStr        用于保存的key
//     * @param string            自动设置的z值
//     * */
////    public void setAutoSelection(String saveKeyStr,String string) {
////        saveKeyString =saveKeyStr;
//        autoString = string;
//        if ("".equals(string)&&!"".equals(saveKeyStr)){
//            autoString = Hawk.get(saveKeyString,"");
//        }
//        for (int j = 0; j < adapter.getCount(); j++) {
//            if (((Client) adapter.getItem(j)).FNumber.equals(autoString)
//                    || ((Client) adapter.getItem(j)).FName.equals(autoString)) {
//                mSp.setSelection(j);
////                autoString = null;
//                break;
//            }
//        }
//    }

    public ClientRyAdapter getAdapter() {
        return adapter;
    }

    //清空
    private void clear() {
        container.clear();
    }
//     设置标题的方法
//    public void setTitleText(String title) {
//        mTitleTv.setText(title);
//    }
    public String getText(){
        return editText.getText().toString();
    }

}
