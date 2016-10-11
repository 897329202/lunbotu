package com.baidu.yunxiaoer.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.yunxiaoer.R;

/**
 * Created by Administrator on 2016-10-04.
 */

public class BaseActivity extends FragmentActivity {
    public TextView    title;
    public ImageView   fenxiang;
    public FrameLayout flActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        title = (TextView) findViewById(R.id.title);
        fenxiang = (ImageView) findViewById(R.id.fenxiang);
        flActivity = (FrameLayout) findViewById(R.id.fl_activity);
        initView();
        initData();
    }
    public void initView() {

    }
    public void initData() {

    }


}
