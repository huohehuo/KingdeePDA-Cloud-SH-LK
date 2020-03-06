package com.fangzuo.assist.cloud.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Adapter.SearchAdapter;
import com.fangzuo.assist.cloud.Adapter.SearchManAdapter;
import com.fangzuo.assist.cloud.Adapter.SearchStorageAdapter;
import com.fangzuo.assist.cloud.Adapter.SearchWaveHouseAdapter;
import com.fangzuo.assist.cloud.Beans.CommonResponse;
import com.fangzuo.assist.cloud.Beans.DownloadReturnBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Beans.SearchBean;
import com.fangzuo.assist.cloud.Dao.Client;
import com.fangzuo.assist.cloud.Dao.Employee;
import com.fangzuo.assist.cloud.Dao.GetGoodsDepartment;
import com.fangzuo.assist.cloud.Dao.InStorageNum;
import com.fangzuo.assist.cloud.Dao.Product;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.Suppliers;
import com.fangzuo.assist.cloud.Dao.WaveHouse;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.RxSerivce.MySubscribe;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.greendao.gen.EmployeeDao;
import com.fangzuo.greendao.gen.StorageDao;
import com.fangzuo.greendao.gen.WaveHouseDao;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchDataActivity extends BaseActivity {


    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.lv_result)
    ListView lvResult;
    @BindView(R.id.cancle)
    View cancle;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.model)
    TextView model;
    @BindView(R.id.name)
    TextView name;
    private String searchString;
    private String searchString4wavehouse;
    private String backBus;
    private SearchDataActivity mContext;
    private SearchAdapter ada;
    private SearchStorageAdapter searchStorageAdapter;
    private SearchWaveHouseAdapter searchWaveHouseAdapter;
    private List<Product> items1;
    private List<Product> items;
    private List<Employee> employeeList;
    private List<Product> itemAll;
    //    private int where;
    private List<Suppliers> itemAllSupplier;
    private List<Suppliers> suppliersList;
    private List<Storage> storageList;
    private List<WaveHouse> waveHouseList;
    private List<Client> itemAllClient;
    private List<Client> itemClient;
    private List<GetGoodsDepartment> goodsDepartmentList;
    private List<GetGoodsDepartment> goodsDepartmentAllList;

    @Override
    public void initView() {
        setContentView(R.layout.activity_search_data);
        ButterKnife.bind(this);
        mContext = this;

        //接收
        Intent intent = getIntent();
        if (intent != null) {
            searchString = intent.getStringExtra("search");
            searchString4wavehouse = intent.getStringExtra("search_storage");
            backBus = intent.getStringExtra("backBus");
//            where = intent.getIntExtra("where",0);
//            fidcontainer = intent.getStringArrayListExtra("fid");
//            Lg.e("Intent:"+fidcontainer.toString());
        }
//        if (where == Info.Search_Man) title.setText("查询结果(员工)");
//        if (where == Info.SEARCHPRODUCT) title.setText("查询结果(物料)");
//        if (where == Info.SEARCHSUPPLIER) title.setText("查询结果(供应商)");
//        if (where == Info.SEARCHCLIENT) title.setText("查询结果(客户)");
//        if (where == Info.SEARCHJH) title.setText("查询结果(交货单位)");
        if (EventBusInfoCode.Storage.equals(backBus)) title.setText("查询结果(仓库)");
        if (EventBusInfoCode.Storage2.equals(backBus)) title.setText("查询结果(调入仓库)");
        if (EventBusInfoCode.WaveHouse.equals(backBus)) title.setText("查询结果(仓位)");
        if (EventBusInfoCode.WaveHouse2.equals(backBus)) title.setText("查询结果(调入仓位)");
        if (EventBusInfoCode.Man.equals(backBus)) title.setText("查询结果(员工)");
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(ClassEvent event) {
        super.receiveEvent(event);
    }

    @Override
    public void initData() {
        if (EventBusInfoCode.Storage.equals(backBus) || EventBusInfoCode.Storage2.equals(backBus)) {
            model.setText("编码");
            name.setText("名称");
            if (BasicShareUtil.getInstance(mContext).getIsOL()) {
                App.getRService().doIOAction("SearchStorage", searchString, new MySubscribe<CommonResponse>() {
                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        super.onNext(commonResponse);
                        DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                        storageList = dBean.storage;
                        if (storageList.size() > 0) {
                            searchStorageAdapter = new SearchStorageAdapter(mContext, storageList);
                            lvResult.setAdapter(searchStorageAdapter);
                            searchStorageAdapter.notifyDataSetChanged();
                        } else {
                            Toast.showText(mContext, "无数据");
                            setResult(-9998, null);
                            onBackPressed();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Toast.showText(mContext, e.getMessage());
                    }
                });
            } else {
                StorageDao storageDao = daoSession.getStorageDao();
                List<Storage> storages = storageDao.queryBuilder().whereOr(
                        StorageDao.Properties.FItemID.eq(searchString),
                        StorageDao.Properties.FNumber.eq(searchString)
                ).build().list();
                storageList = storages;
                if (storageList.size() > 0) {
                    searchStorageAdapter = new SearchStorageAdapter(mContext, storageList);
                    lvResult.setAdapter(searchStorageAdapter);
                    searchStorageAdapter.notifyDataSetChanged();
                } else {
                    Toast.showText(mContext, "无数据");
                    setResult(-9998, null);
                    onBackPressed();
                }
            }
        } else if (EventBusInfoCode.WaveHouse.equals(backBus) || EventBusInfoCode.WaveHouse2.equals(backBus)) {
            Lg.e("仓位查询：",searchString+"--"+searchString4wavehouse);
            model.setText("编号");
            name.setText("名称");
            if (BasicShareUtil.getInstance(mContext).getIsOL()) {
                SearchBean searchBean = new SearchBean();
                searchBean.val1 = searchString;
                searchBean.val2 = searchString4wavehouse;
                App.getRService().doIOAction("SearchWaveHouse", gson.toJson(searchBean), new MySubscribe<CommonResponse>() {
                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        super.onNext(commonResponse);
                        DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                        waveHouseList = dBean.wavehouse;
                        if (waveHouseList.size() > 0) {
                            SearchWaveHouseAdapter ada1 = new SearchWaveHouseAdapter(mContext, waveHouseList);
                            lvResult.setAdapter(ada1);
                            ada1.notifyDataSetChanged();
                        } else {
                            Toast.showText(mContext, "无数据");
                            setResult(-9998, null);
                            onBackPressed();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Toast.showText(mContext, e.getMessage());
                    }
                });
            } else {
                waveHouseList = new ArrayList<>();
                String con="";
                con=con+" and FSTORAGE_ID='"+searchString4wavehouse+"' and (FNAME like '%"+searchString+"%' or FNUMBER like '%"+searchString+"%')";
                String SQL = "SELECT * FROM WAVE_HOUSE WHERE 1=1 "+con;
                Lg.e("仓位:"+SQL);
                Cursor cursor = daoSession.getDatabase().rawQuery(SQL, null);
                while (cursor.moveToNext()) {
                    WaveHouse f = new WaveHouse();
                    f.FSPID = cursor.getString(cursor.getColumnIndex("FSPID"));
                    f.FNumber = cursor.getString(cursor.getColumnIndex("FNUMBER"));
                    f.FName = cursor.getString(cursor.getColumnIndex("FNAME"));
                    f.FOrg = cursor.getString(cursor.getColumnIndex("FORG"));
                    f.FStorageID = cursor.getString(cursor.getColumnIndex("FSTORAGE_ID"));
                    f.FSEQ = cursor.getString(cursor.getColumnIndex("FSEQ"));
                    waveHouseList.add(f);
                }
                if (waveHouseList.size() > 0) {
                    SearchWaveHouseAdapter ada1 = new SearchWaveHouseAdapter(mContext, waveHouseList);
                    lvResult.setAdapter(ada1);
                    ada1.notifyDataSetChanged();
                } else {
                    Toast.showText(mContext, "无数据");
                    setResult(-9998, null);
                    onBackPressed();
                }

            }
        } else if (EventBusInfoCode.Man.equals(backBus) || EventBusInfoCode.Man2.equals(backBus) || EventBusInfoCode.Man3.equals(backBus) || EventBusInfoCode.Man4.equals(backBus)) {
            model.setText("编号");
            name.setText("名称");
            if (BasicShareUtil.getInstance(mContext).getIsOL()) {
                App.getRService().doIOAction("ManSearchLike", searchString, new MySubscribe<CommonResponse>() {
                    @Override
                    public void onNext(CommonResponse commonResponse) {
                        super.onNext(commonResponse);
                        DownloadReturnBean dBean = new Gson().fromJson(commonResponse.returnJson, DownloadReturnBean.class);
                        employeeList = dBean.employee;
                        if (employeeList.size() > 0) {
                            SearchManAdapter ada1 = new SearchManAdapter(mContext, employeeList);
                            lvResult.setAdapter(ada1);
                            ada1.notifyDataSetChanged();
                        } else {
                            Toast.showText(mContext, "无数据");
                            setResult(-9998, null);
                            onBackPressed();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Toast.showText(mContext, e.getMessage());
                    }
                });
            } else {
                EmployeeDao suppliersDao = GreenDaoManager.getmInstance(mContext).getDaoSession().getEmployeeDao();
                List<Employee> list = suppliersDao.queryBuilder().whereOr(
                        EmployeeDao.Properties.FName.like("%" + searchString + "%"),
                        EmployeeDao.Properties.FNumber.like("%" + searchString + "%")
                ).orderAsc(StorageDao.Properties.FItemID).limit(50).build().list();
                employeeList.addAll(list);
                if (employeeList.size() > 0) {
                    SearchManAdapter ada1 = new SearchManAdapter(mContext, employeeList);
                    lvResult.setAdapter(ada1);
                    ada1.notifyDataSetChanged();
                } else {
                    Toast.showText(mContext, "未查询到数据");
                    setResult(-9998, null);
                    onBackPressed();
                }
            }
        }

    }

    @Override
    public void initListener() {
        lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent mIntent = new Intent();
//                Bundle b = new Bundle();
                if (EventBusInfoCode.Storage.equals(backBus)) {
                    EventBusUtil.sendEvent(new ClassEvent(backBus, storageList.get(i)));
                    onBackPressed();
                } else if (EventBusInfoCode.WaveHouse.equals(backBus)) {
                    EventBusUtil.sendEvent(new ClassEvent(backBus, waveHouseList.get(i)));
                    onBackPressed();
                } else if (EventBusInfoCode.Man.equals(backBus) || EventBusInfoCode.Man2.equals(backBus) || EventBusInfoCode.Man3.equals(backBus) || EventBusInfoCode.Man4.equals(backBus)) {
                    EventBusUtil.sendEvent(new ClassEvent(backBus, employeeList.get(i)));
                    onBackPressed();
                }
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void OnReceive(String code) {

    }

    public static void start(Context context, String search, String backBus) {
        Intent starter = new Intent(context, SearchDataActivity.class);
        starter.putExtra("search", search);
        starter.putExtra("backBus", backBus);
//        starter.putStringArrayListExtra("fid", fid);
        context.startActivity(starter);
    }

    public static void start(Context context, String search, String search_storage, String backBus) {
        Intent starter = new Intent(context, SearchDataActivity.class);
        starter.putExtra("search", search);
        starter.putExtra("search_storage", search_storage);
        starter.putExtra("backBus", backBus);
//        starter.putStringArrayListExtra("fid", fid);
        context.startActivity(starter);
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
        this.overridePendingTransition(R.anim.bottom_end, 0);
    }

    @Override
    public void onBackPressed() {
        finish();
        this.overridePendingTransition(0, R.anim.bottom_end);
    }
}


