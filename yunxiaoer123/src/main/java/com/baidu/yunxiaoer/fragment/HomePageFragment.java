package com.baidu.yunxiaoer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.yunxiaoer.R;
import com.baidu.yunxiaoer.activity.homepager.AdvancOrderActivity;
import com.baidu.yunxiaoer.activity.homepager.MessagePushActiivty;
import com.baidu.yunxiaoer.activity.homepager.MoreActivity;
import com.baidu.yunxiaoer.activity.homepager.ShopActivity;
import com.baidu.yunxiaoer.activity.homepager.VipManageActivity;
import com.baidu.yunxiaoer.activity.homepager.offlineActivity;
import com.baidu.yunxiaoer.utils.UiUtils;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;

import static com.baidu.yunxiaoer.utils.UiUtils.parentpopupWindow;

/**
 * Created by Administrator on 2016-10-04.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "HomePageFragment";
    private View         view;
    private LinearLayout activityMain;
    private TextView     fangkenum;
    private TextView     liulannum;
    private TextView     jinridingdan;
    private RecyclerView recy;
    private TextView     mTitle;
    private String[] titles  = {"商品订单", "预约订单", "会员管理", "线下核销", "消息推送", "更        多"};
    private int[]    imagmes = {R.mipmap.shangpindingdan, R.mipmap.yuyuedingdan, R.mipmap
            .huiyuanguanli, R.mipmap.xianxiahexiao, R.mipmap.xiaoxituisong, R.mipmap.gengduo};
    private Class[]  clazz   = {ShopActivity.class, AdvancOrderActivity.class, VipManageActivity
            .class, offlineActivity.class, MessagePushActiivty.class, MoreActivity.class};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        ShareSDK.initSDK(UiUtils.getContext());
        view = inflater.inflate(R.layout.home_fragment, null);
        initView();
        initData();
        return this.view;
    }

    private void initView() {
        activityMain = (LinearLayout) view.findViewById(R.id.activity_main);
        fangkenum = (TextView) view.findViewById(R.id.fangkenum);
        liulannum = (TextView) view.findViewById(R.id.liulannum);
        jinridingdan = (TextView) view.findViewById(R.id.jinridingdan);
        recy = (RecyclerView) view.findViewById(R.id.recy);
        mTitle = (TextView) view.findViewById(R.id.title);
        view.findViewById(R.id.fenxiang).setOnClickListener(this);
        ShareSDK.initSDK(UiUtils.getContext());
    }

    private void initData() {
        recy.setLayoutManager(new GridLayoutManager(UiUtils.getContext(), 3));
        MyAdapter myAdapter = new MyAdapter();
        recy.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fenxiang:
                showShare();
                break;
            case R.id.qqhaoyou:
                Platform.ShareParams qqsp = new Platform.ShareParams();
                qqsp.setTitle("测试分享的标题");
                // qqsp.setTitleUrl("http://sharesdk.cn"); // 标题的超链接
                // qqsp.setText("测试分享的文本");
                // qqsp.setImageUrl("http://www.someserver.com/测试图片网络地址.jpg");
                // qqsp.setSite("发布分享的网站名称");
                // qqsp.setSiteUrl("发布分享网站的地址");
                Platform qqqzone = ShareSDK.getPlatform(QQ.NAME);
                // 执行图文分享
                qqqzone.share(qqsp);
                parentpopupWindow.dismiss();
                break;
            case R.id.qqkongjian:
                Platform.ShareParams kongjiansp = new Platform.ShareParams();
                kongjiansp.setTitle("你好啊，工");
                //点击标题跳转的分享网址
                kongjiansp.setTitleUrl("http://sharesdk.cn");

                kongjiansp.setText("我是分享内容");
                //qq.setUrl("http://mob.com"); //空间没有这个
                kongjiansp.setImageUrl("http://cdn6.jinxidao.com/uploads/201607/5791d21f3f84b.jpg");

                // site是分享此内容的网站名称，仅在QQ空间使用
                kongjiansp.setSite("分享到QQ空间");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                kongjiansp.setSiteUrl("http://sharesdk.cn");
                Platform share_qq = ShareSDK.getPlatform(UiUtils.getContext(), QZone.NAME);
                share_qq.share(kongjiansp);
                parentpopupWindow.dismiss();
                break;
            case R.id.weixinhaoyou:
                parentpopupWindow.dismiss();
                break;
            case R.id.quxiao:
                parentpopupWindow.dismiss();
                break;
        }
    }

    private void showShare() {
        OnekeyShare onekeyShare = new OnekeyShare();
        onekeyShare.disableSSOWhenAuthorize();
        onekeyShare.setTitle("云小二");
        onekeyShare.setText("我是云小二，购物类APP");
        onekeyShare.show(UiUtils.getContext());

    /*    View popuView = UiUtils.getParentPopuwindow(getActivity(), R.layout
                .fenxing_layout);
        popuView.findViewById(R.id.qqhaoyou).setOnClickListener(this);
        popuView.findViewById(R.id.qqkongjian).setOnClickListener(this);
        popuView.findViewById(R.id.weixinhaoyou).setOnClickListener(this);
        popuView.findViewById(R.id.pengyouquan).setOnClickListener(this);
        popuView.findViewById(R.id.quxiao).setOnClickListener(this);*/
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(UiUtils.getContext()).inflate(R.layout
                    .home_page_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.iv.setImageResource(imagmes[position]);
            holder.tv.setText(titles[position]);
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView  tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(UiUtils.getContext(), clazz[getAdapterPosition()]));
                }
            });
            iv = (ImageView) itemView.findViewById(R.id.item_main_iv);
            tv = (TextView) itemView.findViewById(R.id.item_main_tv);
        }
    }

}
