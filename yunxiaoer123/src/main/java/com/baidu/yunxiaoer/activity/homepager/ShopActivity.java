package com.baidu.yunxiaoer.activity.homepager;

import android.os.Bundle;
import android.view.View;

import com.baidu.yunxiaoer.R;
import com.baidu.yunxiaoer.activity.BaseActivity;
import com.baidu.yunxiaoer.utils.UiUtils;

/**
 * 首页→商品订单
 */
public class ShopActivity extends BaseActivity {

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = UiUtils.inflateView(R.layout.activity_shop);

        flActivity.addView(view);
    }
}
