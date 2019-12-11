package com.fangzuo.assist.cloud.ABase;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Beans.EventBusEvent.ClassEvent;
import com.fangzuo.assist.cloud.Utils.EventBusInfoCode;
import com.fangzuo.assist.cloud.Utils.EventBusUtil;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.assist.cloud.zxing.RGBLuminanceSource;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.SourceData;

import java.util.Calendar;

/**
 * Fragment创建：setUserVisibleHint()->onAttach()->onCreate()->onCreateView()->onActivityCreated()->onStart()->onResume()；
 */

public abstract class BaseFragment extends Fragment {
    private static final String ACTION_DISPLAY_SCAN_RESULT = "techain.intent.action.DISPLAY_SCAN_RESULT";
    private FragmentActivity mContext;
    private String barcodeStr;
    public String FFieldMan;

    public void onCreate(Bundle savedInstanceState) {//初始化Fragment
        FragmentActivity mContext = getActivity();
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        super.onCreate(savedInstanceState);
//        registerBroadCast(mScanDataReceiver);
    }


    //初始化Fragment的布局。加载布局和findViewById的操作通常在此函数内完成
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    //执行该方法时，与Fragment绑定的Activity的onCreate方法已经执行完成并返回，
    // 在该方法内可以进行与Activity交互的UI操作，所以在该方法之前Activity的onCreate方法并未执行完成，
    // 如果提前进行交互操作，会引发空指针异常
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
//        mContext.unregisterReceiver(mScanDataReceiver);
    }
    //onCreateView是创建的时候调用，onViewCreated是在onCreateView后被触发的事件，前后关系
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
//        registerBroadCast(mScanDataReceiver);
        initView();
        initData();
        initListener();
    }
    protected abstract void initView();
    protected abstract void OnReceive(String barCode);
    protected abstract void initData();
    protected abstract void initListener();
    public int year;
    public int month;
    public int day;
    private String date;


    @Override
    public void onDestroy() {
        super.onDestroy();

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

    public final void startNewActivity(Class<? extends Activity> target,
                                       Bundle mBundle) {
        Intent mIntent = new Intent(getActivity(), target);
        if (mBundle != null) {
            mIntent.putExtras(mBundle);
        }
        startActivity(mIntent);
    }
    public final void startNewActivity(Activity activity,Class<? extends Activity> target,
                                       int enterAnim, int exitAnim, boolean isFinish, Bundle mBundle) {
        Intent mIntent = new Intent(activity, target);
        if (mBundle != null) {
            mIntent.putExtras(mBundle);
        }
        startActivity(mIntent);
        activity.overridePendingTransition(enterAnim, exitAnim);
        if (isFinish) {
            activity.finish();
        }
    }

    //获取焦点
    public void setfocus(View v) {
        v.setFocusable(true);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.findFocus();
    }
    protected final void startNewActivityForResult(Activity activity,Class<? extends Activity> target, int enterAnim, int exitAnim, int requestCode, Bundle mBundle) {
        Intent mIntent = new Intent(activity, target);
        if (mBundle != null) {
            mIntent.putExtras(mBundle);
        }
        startActivityForResult(mIntent, requestCode);
        activity.overridePendingTransition(enterAnim, exitAnim);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == Info.Scan_Pic) {//扫码远距时解析返回的图片二维码
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                RGBLuminanceSource source = new RGBLuminanceSource(bitmap);
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
                Reader reader = new QRCodeReader();
                try {
                    Result result = reader.decode(binaryBitmap);
                    Lg.e("解析：",result.getText());
                    BarcodeResult barcodeResult = new BarcodeResult(result,new SourceData(new byte[10],1,1,1,1));
                    EventBusUtil.sendEvent(new ClassEvent(EventBusInfoCode.ScanResult,barcodeResult));
                } catch (NotFoundException | ChecksumException | FormatException e) {
                    Toast.showText(mContext,"解析失败"+e.getMessage());
                    Lg.e("解析失败"+e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    //重载点击事件，避免快速点击
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
    //重载spinner监听，简化代码
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

//----------------------------------------无用----------------------

/*
    public void registerBroadCast(BroadcastReceiver mScanDataReceiver) {
        IntentFilter scanDataIntentFilter = new IntentFilter();
        scanDataIntentFilter.addAction("com.android.scanservice.scancontext");
        getActivity().registerReceiver(mScanDataReceiver, scanDataIntentFilter);

    }*/

//        //UBX
//        initScan();

//        //u8000
//        sm = new ScanDevice();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("scan.rcv.message");
//        mContext.registerReceiver(mScanDataReceiver, filter);

//        //5000
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("scan.rcv.message");
//        filter.addAction("com.android.scanservice.scancontext");
//        mContext.registerReceiver(mScanDataReceiver, filter);


//        //G02A
//        IntentFilter scanDataIntentFilter = new IntentFilter();
//        scanDataIntentFilter.addAction(ACTION_DISPLAY_SCAN_RESULT);
//        getActivity().registerReceiver(mScanDataReceiver, scanDataIntentFilter);

//        try{
//                if (App.PDA_Choose == 1) {
//                    //G02A
//                    IntentFilter scanDataIntentFilter = new IntentFilter();
//                    scanDataIntentFilter.addAction(ACTION_DISPLAY_SCAN_RESULT);
//                    getActivity().registerReceiver(mScanDataReceiverForG02A, scanDataIntentFilter);
//                } else if (App.PDA_Choose==2){
//                    //u8000
//                    sm = new ScanDevice();
//                    IntentFilter filter = new IntentFilter();
//                    filter.addAction("scan.rcv.message");
//                    getActivity().registerReceiver(mScanDataReceiver, filter);
//                }else if (App.PDA_Choose==3){
//                    //5000
//                    IntentFilter filter = new IntentFilter();
//                    filter.addAction("scan.rcv.message");
//                    filter.addAction("com.android.scanservice.scancontext");
//                    getActivity().registerReceiver(mScanDataReceiverFor5000, filter);
//                }else if (App.PDA_Choose==4){
//                    //M60
//                    IntentFilter scanDataIntentFilter = new IntentFilter();
//                    scanDataIntentFilter.addAction(ACTION_M60);
//                    getActivity().registerReceiver(mScanDataReceiverForM60, scanDataIntentFilter);
//                }
//        }catch (Exception e){
//
//        }



//        try{
//            if (App.PDA_Choose!=5){
//                if (mScanDataReceiver != null || mScanDataReceiverForG02A != null|| mScanDataReceiverFor5000 != null) {
//                    if (App.PDA_Choose == 1) {
//                        getActivity().unregisterReceiver(mScanDataReceiverForG02A);
//                    } else if (App.PDA_Choose==2){
//                        getActivity().unregisterReceiver(mScanDataReceiver);
//                    }else if (App.PDA_Choose == 3){
//                        getActivity().unregisterReceiver(mScanDataReceiverFor5000);
//                    }else if (App.PDA_Choose == 4){
//                        getActivity().unregisterReceiver(mScanDataReceiverForM60);
//                    }
//                }
//            }
//        }catch (Exception e){
//
//        }
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

//    //UBX
//    private ScanManager mScanManager;
//    private BroadcastReceiver mScanDataReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            byte[] barcode = intent.getByteArrayExtra(ScanManager.DECODE_DATA_TAG);
//            int barcodelen = intent.getIntExtra(ScanManager.BARCODE_LENGTH_TAG, 0);
//            byte temp = intent.getByteExtra(ScanManager.BARCODE_TYPE_TAG, (byte) 0);
//            Log.i("debug", "----codetype--" + temp);
//            barcodeStr = new String(barcode, 0, barcodelen);
//            OnReceive(barcodeStr);
//
//        }
//    };
//    private void initScan() {
//        try{
//            mScanManager = new ScanManager();
//            mScanManager.openScanner();
//            mScanManager.switchOutputMode(0);
//            SoundPool soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100);
//            int soundid = soundpool.load("/etc/Scan_new. ", 1);
//            Log.e("OnResume","OnResume");
//            IntentFilter filter = new IntentFilter();
//            int[] idbuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG};
//            String[] value_buf = mScanManager.getParameterString(idbuf);
//            if(value_buf != null && value_buf[0] != null && !value_buf[0].equals("")) {
//                filter.addAction(value_buf[0]);
//            } else {
//                filter.addAction(ScanManager.ACTION_DECODE);
//            }
//
//            getActivity().registerReceiver(mScanDataReceiver, filter);
//        }catch (RuntimeException stub){
//            stub.printStackTrace();
//        }
//
//    }


//        //u8000
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
//    //c50002
//    private BroadcastReceiver mScanDataReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (action.equals("com.android.scanservice.scancontext")) {
//                String str = intent.getStringExtra("Scan_context");
//                OnReceive(str);
//            }
//        }
//    };

//    public void registerBroadCast(BroadcastReceiver mScanDataReceiver){
//        IntentFilter scanDataIntentFilter = new IntentFilter();
//        scanDataIntentFilter.addAction(ACTION_DISPLAY_SCAN_RESULT);
//        getActivity().registerReceiver(mScanDataReceiver, scanDataIntentFilter);
//
//    }
}
