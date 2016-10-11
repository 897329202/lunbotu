package com.baidu.yunxiaoer.utils;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.baidu.yunxiaoer.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ui操作处理的工具类
 */

public class UiUtils {

    public static PopupWindow parentpopupWindow;
    public static PopupWindow popupWindow;
    public static int         year;
    public static int         month;
    public static int         day;
    public static String      firstday, lastday;
    public static int         daysCountOfMonth;
    public static int dayOfWeek;

    //获取context对象
    public static Context getContext() {
        return MyApplication.getContext();
    }

    //获取handler对象
    public static Handler getMainThreadHandler() {
        return MyApplication.getHandler();
    }

    //获取主线程的线程id
    public static int getMainThreadId() {
        return MyApplication.getMainThreadId();
    }

    //获取字符串资源
    public static String getString(int resId) {
        return getContext().getResources().getString(resId);
    }

    //获取字符串数组资源
    public static String[] getStringArray(int resId) {
        return getContext().getResources().getStringArray(resId);
    }

    //获取drawable
    public static Drawable getDrawable(int resId) {
        return getContext().getResources().getDrawable(resId);
    }

    //获取color
    public static int getColor(int resId) {
        return getContext().getResources().getColor(resId);
    }

    //获取颜色的状态选择器
    public static ColorStateList getColorStateList(int resId) {
        return getContext().getResources().getColorStateList(resId);
    }

    //获取dimen下的值
    public static int getDimen(int resId) {
        return getContext().getResources().getDimensionPixelSize(resId);
    }

    // dp--px
    public static int dip2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;//获取屏幕密度
        return (int) (dp * density + 0.5);
    }

    //px--dp
    public static int px2dip(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;//获取屏幕密度
        return (int) (px / density + 0.5);
    }


    //判断当前线程是否是主线程
    public static boolean isRunOnUiThread() {
        //获取主线程的线程id
        int mainThreadId = getMainThreadId();
        //获取当前线程的id
        int currentThreadId = android.os.Process.myTid();

        return mainThreadId == currentThreadId;
    }

    //保证一定运行在主线程中
    public static void runOnUiThread(Runnable r) {
        if (isRunOnUiThread()) {
            //new Thread(r).start();
            r.run();
        } else {
            getMainThreadHandler().post(r);//将r丢到主线程的消息队列里面
        }
    }

    //保证一定运行在子线程中
    public static void runOnThread(Runnable r) {
        if (isRunOnUiThread()) {
            new Thread(r).start();
            r.run();
        } else {
            //getMainThreadHandler().post(r);//将r丢到主线程的消息队列里面
        }
    }

    public static View inflateView(int resId) {

        return View.inflate(getContext(), resId, null);
    }

    /**
     * PopupWindown
     */
    public static View getPopuwindow(int resId, View obj, int x, int y) {
        View view = UiUtils.inflateView(resId);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        //给VIewPager设置一个背景，从而达到点击控件以外可以让PopupWindown自动消失
        popupWindow.showAsDropDown(obj, x, y);
        return view;
    }


    /**
     * PopupWindown
     */
    public static View getParentPopuwindow(Activity mActivity, int resId) {
        View view = UiUtils.inflateView(resId);
        parentpopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true);
        parentpopupWindow.setBackgroundDrawable(new ColorDrawable());
        parentpopupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        //给PopupWindown设置一个背景，从而达到点击控件以外可以让PopupWindown自动消失
        parentpopupWindow.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        return view;
    }

    /**
     * Dialog;
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static AlertDialog getDialog(Context context, int resId) {
        AlertDialog dialog = new AlertDialog.Builder(context, AlertDialog
                .THEME_TRADITIONAL).create();     //AlertDialog.THEME_TRADITIONAL表示默认的背景为透明
        dialog.setView(new EditText(getContext()));
        dialog.show();
        dialog.setContentView(resId);
        return dialog;
    }

    public static void ToastUtils(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 时间格式化为毫秒值
     */
    public static Long timeToMill(String format, String time) {
        try {
            return new SimpleDateFormat(format).parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间选择Dialog
     */
    public static DatePickerDialog showTimeDialog(Context context) {
        Calendar d = Calendar.getInstance(Locale.CHINA);
        //创建一个日历引用d，通过静态方法getInstance() 从指定时区 Locale.CHINA 获得一个日期实例
        Date myDate = new Date();
        //创建一个Date实例
        d.setTime(myDate);
        //设置日历的时间，把一个新建Date实例myDate传入
        year = d.get(Calendar.YEAR);
        month = d.get(Calendar.MONTH);
        day = d.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog
                .OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
            }
        }, year, month, day);
        dialog.show();
        return dialog;
    }



    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 判断是否是手机号
     */
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    /**
     * 当前时间格式化
     */
    public static String refFormatNowDate(String format) {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat(format);
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        return retStrFormatNowDate;
    }

    /**
     * 时间格式化
     */
    public static String refFormatDate(Date date, String format) {
        SimpleDateFormat sdFormatter = new SimpleDateFormat(format);
        String time = sdFormatter.format(date);
        return time;
    }

    /**
     *  前面日期或后面日期
     */
    public static String getDateTime(int i, int j, String format){
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(new Date()); //设置时间为当前时间
        ca.add(i, j); //前一天
        Date date= ca.getTime();//结果
        String frontDay = UiUtils.refFormatDate(date, format);
        return frontDay;
    }

    /**
     * 某个月的第一天日期和最后一天的日期
     * @param i
     */
    public static void  getFontBehand(int i){
        // 获取当前年份、月份、日期
        Calendar cale = null;
        cale = Calendar.getInstance();
        // 获取当月第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, i);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        // 获取前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, i + 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
    }

    public static void getWeek(int year1, int month1, int day1){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year1);//先指定年份
        calendar.set(Calendar.MONTH, month1 - 1);//再指定月份 Java月份从0开始算
        //获取指定年份中指定月份有几天
        daysCountOfMonth = calendar.getActualMaximum(Calendar.DATE);
        //获取指定年份月份中指定某天是星期几
        calendar.set(Calendar.DAY_OF_MONTH, day1);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    }
}
