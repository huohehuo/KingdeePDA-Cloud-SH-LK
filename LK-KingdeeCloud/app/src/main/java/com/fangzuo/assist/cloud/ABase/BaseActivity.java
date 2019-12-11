package com.fangzuo.assist.cloud.ABase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Beans.BlueToothBean;
import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Service.BaseUtilService;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.MathUtil;
import com.fangzuo.assist.cloud.Utils.MediaPlayer;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;
import com.nineoldandroids.view.ViewHelper;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import zpSDK.zpSDK.zpBluetoothPrinter;

/**
 * Created by NB on 2017/8/1.
 */

public abstract class BaseActivity extends FragmentActivity {

    public Context mContext;
    public ShareUtil share;
//    private IntentFilter scanDataIntentFilter;
//    private String barcodeStr;
    public String TAG = getClass().getSimpleName();
    public Gson gson;
    public T_mainDao t_mainDao;
    public T_DetailDao t_detailDao;
    public DaoSession daoSession;
    public int year;
    public int month;
    public int day;
    public Bundle savedInstanceState;
    /**
     * 条形码扫描管理器
     */
    public ScanManager mCaptureManager;
//    //u8000
//    private ScanDevice sm;
//    private BroadcastReceiver mScanDataReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            byte[] barocode = intent.getByteArrayExtra("barocode");
//            int barocodelen = intent.getIntExtra("length", 0);
//            byte temp = intent.getByteExtra("barcodeType", (byte) 0);
//            Log.i("debug", "----codetype--" + temp);
//            barcodeStr = new String(barocode, 0, barocodelen);
//            OnReceive(barcodeStr);
//        }
//    };
//
//    //c5000
//    private BroadcastReceiver mScanDataReceiverFor5000 = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (action.equals("com.android.scanservice.scancontext")) {
//                String str = intent.getStringExtra("Scan_context");
//                OnReceive(str);
//            }
//        }
//    };
//    //G02A
//    private static final String ACTION_DISPLAY_SCAN_RESULT = "techain.intent.action.DISPLAY_SCAN_RESULT";
//    private BroadcastReceiver mScanDataReceiverForG02A = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (action.equals(ACTION_DISPLAY_SCAN_RESULT)) {
//                String str = intent.getStringExtra("decode_data");
//                OnReceive(str);
//            }
//        }
//    };
//    //  M60
//    private static final String ACTION_M60 = "com.mobilead.tools.action.scan_result";
//    private BroadcastReceiver mScanDataReceiverForM60 = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (action.equals(ACTION_M60)) {
//                String str = intent.getStringExtra("decode_rslt");
//                OnReceive(str);
//            }
//        }
//    };




    private String date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }

        share = ShareUtil.getInstance(mContext);
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        gson = new Gson();
        daoSession = GreenDaoManager.getmInstance(mContext).getDaoSession();
        t_mainDao = daoSession.getT_mainDao();
        t_detailDao = daoSession.getT_DetailDao();
        //UBX
//        initScan();
        this.savedInstanceState = savedInstanceState;
//        initTipView();
//        try{
//            //手机4：不需要注册
//            if (App.PDA_Choose == 1) {
//                //G02A
//                IntentFilter scanDataIntentFilter = new IntentFilter();
//                scanDataIntentFilter.addAction(ACTION_DISPLAY_SCAN_RESULT);
//                registerReceiver(mScanDataReceiverForG02A, scanDataIntentFilter);
//            } else if (App.PDA_Choose==2){
//                //u8000
//                sm = new ScanDevice();
//                IntentFilter filter = new IntentFilter();
//                filter.addAction("scan.rcv.message");
//                registerReceiver(mScanDataReceiver, filter);
//            }else if (App.PDA_Choose==3){
//                //5000
//                IntentFilter filter = new IntentFilter();
//                filter.addAction("scan.rcv.message");
//                filter.addAction("com.android.scanservice.scancontext");
//                registerReceiver(mScanDataReceiverFor5000, filter);
//            }else if (App.PDA_Choose==4){
//                //M60
//                IntentFilter scanDataIntentFilter = new IntentFilter();
//                scanDataIntentFilter.addAction(ACTION_M60);
//                registerReceiver(mScanDataReceiverForM60, scanDataIntentFilter);
//            }
//
//        }catch (Exception e){
//
//        }




        initView();
        initData();
        initListener();
    }
//    public BroadcastReceiver getBroadcastReceiver(){
//        return mScanDataReceiver;
//    }
    //弹窗初始化
    View mTipView;
    WindowManager mWindowManager;
    WindowManager.LayoutParams mLayoutParams;
    private void initTipView() {
        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 100,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH,
                PixelFormat.TRANSLUCENT);
        //使用非CENTER时，可以通过设置XY的值来改变View的位置
        mLayoutParams.gravity = Gravity.TOP;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
    }
    public void showTopView(String msg){
        if (mTipView==null ){
            Lg.e("m","等于空");
            mWindowManager.addView(setViewTop(msg),mLayoutParams);
        }else{
//            if (mTipView.getParent() != null) {
//                Lg.e("mTgetParent","不等于空");
                mWindowManager.removeView(mTipView);
//            }else{
//                Lg.e("mTgetParent","等于空");
//                mWindowManager.removeView(mTipView);
                mWindowManager.addView(setViewTop(msg), mLayoutParams);
//            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mTipView!=null){
                    mWindowManager.removeView(mTipView);
                    mTipView=null;
                }
            }
        }, 1700);

    }
    private View setViewTop(String msg){
        LayoutInflater inflater = getLayoutInflater();
        mTipView = inflater.inflate(R.layout.layout_msg_top, null); //提示View布局
        TextView textView = mTipView.findViewById(R.id.tv_msg);
        ImageButton imageView = mTipView.findViewById(R.id.iv_close);
        textView.setText(msg);
        imageView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Lg.e("按钮点击");
                mWindowManager.removeView(mTipView);
                mTipView =null;
            }
        });
        return mTipView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        try{
//            //        if (App.PDA_Choose!=4){
//            if (mScanDataReceiver != null || mScanDataReceiverForG02A != null|| mScanDataReceiverFor5000 != null) {
//                if (App.PDA_Choose == 1) {
//                    unregisterReceiver(mScanDataReceiverForG02A);
//                } else if (App.PDA_Choose==2){
//                    unregisterReceiver(mScanDataReceiver);
//                }else if (App.PDA_Choose == 3){
//                    unregisterReceiver(mScanDataReceiverFor5000);
//                }else if (App. PDA_Choose == 4){
//                    unregisterReceiver(mScanDataReceiverForM60);
//                }
//            }
////        }
//        }catch (Exception e){
//
//        }
//当提示View被动态添加后直接关闭页面会导致该View内存溢出，所以需要在finish时移除
        if (mTipView != null) {
            mWindowManager.removeView(mTipView);
        }
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
        if (isScan()){
            if (null!=mCaptureManager)mCaptureManager.onDestroy();
        }
    }
    //连扫模式时，重新刷新摄像头，并且开启扫描
    public void ReSetScan(CheckBox checkBox){
        if (checkBox.isChecked()){
            if (null!=mCaptureManager)mCaptureManager.onResume();
            if (null!=mCaptureManager)mCaptureManager.decode();
        }
    }

    protected boolean isRegisterEventBus() {
        return false;
    }
    protected boolean isScan() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(ClassEvent event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    protected void receiveEvent(ClassEvent event) {

    }

    public String datePicker(final TextView v) {
        final DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            }
        }, year, month, day);
        datePickerDialog.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int year = datePickerDialog.getDatePicker().getYear();
                int month = datePickerDialog.getDatePicker().getMonth();
                int day = datePickerDialog.getDatePicker().getDayOfMonth();
                date = year + "-" + ((month < 9) ? "0" + (month + 1) : (month + 1)) + "-" + ((day < 10) ? "0" + day : day);
                Toast.showText(mContext, date);
                v.setText(date);
                datePickerDialog.dismiss();

            }
        });
        datePickerDialog.show();
        return date;
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (isScan()){
            if (null!=mCaptureManager)mCaptureManager.onResume();
        }
        //超过10分钟后，自动重新登陆
        if (MathUtil.MoreTime(8)){
            BaseUtilService.reLogin(mContext);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isScan()){
            if (null!=mCaptureManager)mCaptureManager.onPause();
        }
//        unregisterReceiver(mScanDataReceiver);
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void OnReceive(String code);

    // 检查Service是否运行
    private boolean isServiceRunning(String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(100);
        if (!(serviceList.size() > 0)) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
    public final void startNewActivity(Class<? extends Activity> target,
                                       int enterAnim, int exitAnim, boolean isFinish, Bundle mBundle) {
        Intent mIntent = new Intent(this, target);
        if (mBundle != null) {
            mIntent.putExtras(mBundle);
        }
        startActivity(mIntent);
        overridePendingTransition(enterAnim, exitAnim);
        if (isFinish) {
            finish();
        }
    }

    protected final void startNewActivityForResult(Class<? extends Activity> target, int enterAnim, int exitAnim, int requestCode, Bundle mBundle) {
        Intent mIntent = new Intent(this, target);
        if (mBundle != null) {
            mIntent.putExtras(mBundle);
        }
        startActivityForResult(mIntent, requestCode);
        overridePendingTransition(enterAnim, exitAnim);
    }

    public void initDrawer(final DrawerLayout mDrawer) {
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.LEFT);
        mDrawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawer.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float right = 0.8f + (1 - slideOffset) * 0.2f;


                if (drawerView.getTag().equals("LEFT")) {
                    float leftScale = 1 - scale;
                    ViewHelper.setScaleX(mMenu, leftScale);//drawer
                    ViewHelper.setScaleY(mMenu, leftScale);//drawer
                    ViewHelper.setAlpha(mMenu, 0.8f + 0.2f * (leftScale));
                    ViewHelper.setTranslationX(mContent, (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, right);
                    ViewHelper.setScaleY(mContent, right);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.LEFT);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }


    /**
     * 设置全屏滑动
     *
     * @param activity
     * @param drawerLayout
     * @param displayWidthPercentage
     */
    public void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            // 找到 ViewDragHelper 并设置 Accessible 为true
            Field leftDraggerField =
                    drawerLayout.getClass().getDeclaredField("mLeftDragger");//Right
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);

            // 找到 edgeSizeField 并设置 Accessible 为true
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);

            // 设置新的边缘大小
            Point displaySize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(displaySize);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (displaySize.x *
                    displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }

    //获取焦点
    public void setfocus(View v) {
        v.setFocusable(true);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.findFocus();
    }

    public String getTime(boolean b) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(b ? "yyyy-MM-dd" : "yyyyMMdd");
        Date curDate = new Date();
        Log.e("date", curDate.toString());
        String str = format.format(curDate);
        return str;
    }

    public String getTimeLong(boolean b){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat(b?"yyyy-MM-dd HH:mm:ss":"yyyyMMddHHmmss");
        Date curDate = new Date();
        Log.e("date",curDate.toString());
        String str = format.format(curDate);
        return str;
    }


    public String getTimesecond() {
        Date curDate = new Date();
        Long time = curDate.getTime();
        return time + "";
    }

    public String getBaseUrl() {
        return BasicShareUtil.getInstance(mContext).getBaseURL();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.e("base","侧滑");
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }

    //侧滑监听
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }


    //使状态栏透明并沉浸到activity
    protected void initBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            //获取顶级窗口
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN      //全屏标志,布局侵入
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION    //标志布局会侵入到导航栏下
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;            //保持稳定
            decorView.setSystemUiVisibility(option);                //设置系统UI可见属性

            getWindow().setStatusBarColor(Color.TRANSPARENT);       //设置状态栏颜色透明
            getWindow().setNavigationBarColor(Color.TRANSPARENT);   //设置导航栏颜色透明

            //设置状态栏为半透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置导航栏为半透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    localLayoutParams.flags);
        }

    }

    public void backReture(String string){
        if (null == string) return;
        Toast.showText(mContext, string);
        MediaPlayer.getInstance(mContext).error();
        if (isScan()){
            if (null!=mCaptureManager)mCaptureManager.onResume();
            if (null!=mCaptureManager)mCaptureManager.decode();
        }
    }

    public zpBluetoothPrinter getBtPrint(Context context){
        zpBluetoothPrinter zpSDK=new zpBluetoothPrinter(context);
        try {
            BlueToothBean bean = Hawk.get(Config.OBJ_BLUETOOTH, new BlueToothBean("", ""));
            if (bean.address.equals("")) {
                Toast.showText(context,"打印机初始化失败，请检查是否已配置打印机");
                return null;
//                EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
            } else {
                //当打印机已经连上，就不需要再次连接
                if (!zpSDK.connect(bean.address)) {
                    return zpSDK;
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "NOOK"));
                } else {
                    Toast.showText(context,"打印机连接失败,请重试");
                    return null;
//                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.Print_Check, "OK"));
                }
//                }
            }
        } catch (Exception e) {
            return null;
        }
    }



    //防止点击事件短时间多次点击
    public abstract  class NoDoubleClickListener implements View.OnClickListener{
        public static final int MIN_CLICK_DELAY_TIME = 1500;
        private long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                Lg.e("点击OK");
                onNoDoubleClick(v);
            }else{
//                Toast.showText(mContext,"别点太快");
                Lg.e("太快了");
            }
        }
        protected abstract void onNoDoubleClick(View view);
    }

    //为了简化spinner点击事件
    public abstract class ItemListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
            ItemSelected(parent,view,i,id);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
        protected  abstract void ItemSelected(AdapterView<?> parent, View view, int i, long id);
    }

}
