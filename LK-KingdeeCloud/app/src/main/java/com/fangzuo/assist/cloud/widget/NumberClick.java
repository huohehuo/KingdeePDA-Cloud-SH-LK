package com.fangzuo.assist.cloud.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangzuo.assist.cloud.Dao.Unit;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.GreedDaoUtil.GreenDaoManager;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.fangzuo.greendao.gen.DaoSession;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;


public class NumberClick extends RelativeLayout {
    // 返回按钮控件
    // 标题Tv
    private TextView title;
    private TextView num;
    private Button add;
    private Button del;
    private static BasicShareUtil share;
    private String autoKey="";
    private String autoString;
    private ArrayList<Unit> list;
    private DaoSession daoSession;
    private String unitId = "";
    private String unitName = "";
    public static final String Name = "name";
    public static final String Id = "id";
    public static final String Number = "number";
    public static final String TGP = "Unit+";

    public NumberClick(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater.from(context).inflate(R.layout.view_number_click, this);
        daoSession = GreenDaoManager.getmInstance(context).getDaoSession();
//        list = new ArrayList<>();
//        share = BasicShareUtil.getInstance(context);
        // 获取控件
        title = (TextView) findViewById(R.id.tv_cardtitle);
        num = (TextView) findViewById(R.id.tv_num);
        add = (Button) findViewById(R.id.btn_add);
        del = (Button) findViewById(R.id.btn_delete);

        TypedArray attrArray = context.obtainStyledAttributes(attributeSet, R.styleable.Style_TextView);
        int count = attrArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attrName = attrArray.getIndex(i);
            switch (attrName) {
                case R.styleable.Style_TextView_textcardtext:
                    title.setText(attrArray.getString(R.styleable.Style_TextView_textcardtext));
//                    world.setText(attrArray.getString(R.styleable.Style_TextView_textcardtext));
                    break;
//                case R.styleable.Style_Spinner_Unit_Uspinner_focusable:
//                    mSp.setEnabled(attrArray.getBoolean(R.styleable.Style_Spinner_Unit_Uspinner_focusable, true));
//                    break;
                case R.styleable.Style_TextView_textcardsize:
                    title.setTextSize(attrArray.getDimension(R.styleable.Style_TextView_textcardsize, 10));
//                    world.setTextSize(attrArray.getDimension(R.styleable.Style_TextView_textcardsize, 10));
                    break;
            }
        }
        attrArray.recycle();

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Lg.e("数量：",num.getText().toString());
                if (Integer.parseInt(num.getText().toString())<10){
                    num.setText((Integer.parseInt(num.getText().toString())+1)+"");
                }else{
                    Toast.showText(context,getContext().getString(R.string.tip_print_is_max));
                }
                Hawk.put(autoKey,num.getText().toString());
            }
        });
        del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(num.getText().toString())==0){
                    Toast.showText(context,getContext().getString(R.string.tip_print_is_mix));
                }else{
                    num.setText((Integer.parseInt(num.getText().toString())-1)+"");
                }
                Hawk.put(autoKey,num.getText().toString());
            }
        });
    }

    public void setSaveKey(String key){
        autoKey = key;
        num.setText(Hawk.get(autoKey,2)+"");
    }
    public String getNum(){
        return num.getText().toString();
    }


}
