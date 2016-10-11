package com.baidu.yunxiaoer.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baidu.yunxiaoer.R;
import com.baidu.yunxiaoer.fragment.HomePageFragment;
import com.baidu.yunxiaoer.fragment.MySelfFragment;
import com.baidu.yunxiaoer.fragment.WaiterFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private View        view;
    private ImageButton idTabWeixinImg;
    private ImageButton idTabFrdImg;
    private ImageButton idTabAddressImg;
    private TextView    weixinTv, frdTv, addressTv;
    private FragmentTransaction transaction;
    private HomePageFragment    homePageFragment;
    private WaiterFragment      waiterFragment;
    private MySelfFragment      mySelfFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.id_tab_weixin).setOnClickListener(this);
        idTabWeixinImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        findViewById(R.id.id_tab_frd).setOnClickListener(this);
        idTabFrdImg = (ImageButton) findViewById(R.id.id_tab_frd_img);
        findViewById(R.id.id_tab_address).setOnClickListener(this);
        idTabAddressImg = (ImageButton)findViewById(R.id.id_tab_address_img);
        weixinTv = (TextView) findViewById(R.id.weixin_tv);
        frdTv = (TextView) findViewById(R.id.frd_tv);
        addressTv = (TextView) findViewById(R.id.address_tv);
        fragments.add(new HomePageFragment());
        fragments.add(new WaiterFragment());
        fragments.add(new MySelfFragment());
        select(0);
    }


    private void select(int i) {
        /**
         * 把图片和文字都改为暗色
         */
        selectImage();
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        hidiFragment();
        switch (i) {
            case 0:
                idTabWeixinImg.setImageResource(R.mipmap.tab_weixin_pressed);
                weixinTv.setTextColor(Color.parseColor("#1B9CFF"));
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    transaction.add(R.id.fl_main, homePageFragment);
                } else {
                    transaction.show(homePageFragment);
                }
                break;
            case 1:
                idTabFrdImg.setImageResource(R.mipmap.tab_find_frd_pressed);
                frdTv.setTextColor(Color.parseColor("#1B9CFF"));
                if (waiterFragment == null) {
                    waiterFragment = new WaiterFragment();
                    transaction.add(R.id.fl_main, waiterFragment);
                } else {
                    transaction.show(waiterFragment);
                }
                break;
            case 2:
                idTabAddressImg.setImageResource(R.mipmap.tab_address_pressed);
                addressTv.setTextColor(Color.parseColor("#1B9CFF"));
                if (mySelfFragment == null) {
                    mySelfFragment = new MySelfFragment();
                    transaction.add(R.id.fl_main, mySelfFragment);
                } else {
                    transaction.show(mySelfFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hidiFragment() {
        if (homePageFragment != null) {
            transaction.hide(homePageFragment);
        }
        if (mySelfFragment != null) {
            transaction.hide(mySelfFragment);
        }
        if (waiterFragment != null) {
            transaction.hide(waiterFragment);
        }

    }

    private void selectImage() {
        idTabAddressImg.setImageResource(R.mipmap.tab_address_normal);
        idTabFrdImg.setImageResource(R.mipmap.tab_find_frd_normal);
        idTabWeixinImg.setImageResource(R.mipmap.tab_weixin_normal);
        weixinTv.setTextColor(Color.parseColor("#000000"));
        frdTv.setTextColor(Color.parseColor("#000000"));
        addressTv.setTextColor(Color.parseColor("#000000"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_weixin:
                select(0);
                break;
            case R.id.id_tab_frd:
                select(1);
                break;
            case R.id.id_tab_address:
                select(2);
                break;
        }
    }
}
