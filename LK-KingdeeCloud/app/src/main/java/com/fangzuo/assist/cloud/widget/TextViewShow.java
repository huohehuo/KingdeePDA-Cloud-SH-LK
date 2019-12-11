package com.fangzuo.assist.cloud.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangzuo.assist.cloud.R;


public class TextViewShow extends RelativeLayout {
    // 返回按钮控件
    // 标题Tv
    private TextView titleone;
    private TextView titletwo;
    private ImageView line;
    public TextViewShow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_textview_show, this);
        // 获取控件
        titleone = (TextView) findViewById(R.id.tv_one);
        titletwo = (TextView) findViewById(R.id.tv_two);
//        line = (ImageView) findViewById(R.id.linecenter);
        TypedArray attrArray = context.obtainStyledAttributes(attributeSet, R.styleable.Style_TextView_Show);
        int count = attrArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attrName = attrArray.getIndex(i);
            switch (attrName) {
                case R.styleable.Style_TextView_Show_TextView_Show_one:
                    titleone.setText(attrArray.getString(R.styleable.Style_TextView_Show_TextView_Show_one));
                    break;
                case R.styleable.Style_TextView_Show_TextView_Show_two:
                    titletwo.setText(attrArray.getString(R.styleable.Style_TextView_Show_TextView_Show_two));
                    break;
                case R.styleable.Style_TextView_Show_TextView_Show_one_size:
                    titleone.setTextSize(attrArray.getDimension(R.styleable.Style_TextView_Show_TextView_Show_one_size, 10));
                    break;
                case R.styleable.Style_TextView_Show_TextView_Show_two_size:
                    titletwo.setTextSize(attrArray.getDimension(R.styleable.Style_TextView_Show_TextView_Show_two_size, 10));
                    break;
            }
        }
        attrArray.recycle();
    }

}
