package com.jiebian.adwlf;

import android.app.Activity;
import android.content.Intent;

import com.jiebian.adwlf.ui.activity.personal.AboutActivity;
import com.jiebian.adwlf.ui.activity.personal.AccountActivity;
import com.jiebian.adwlf.ui.activity.personal.FirstGuideActivity;
import com.jiebian.adwlf.ui.activity.personal.ForecastActivity;
import com.jiebian.adwlf.ui.activity.personal.GuideActivity;
import com.jiebian.adwlf.ui.activity.personal.MainTabActivity;
import com.jiebian.adwlf.ui.activity.personal.SetActivity;
import com.jiebian.adwlf.ui.activity.personal.ShareActivity;
import com.jiebian.adwlf.ui.activity.personal.UserDetailInfoActivity;
import com.jiebian.adwlf.wxapi.WXEntryActivity;

/**
 * UI跳转管理
 */
public class UIManager {



    /**
     * 显示第一次引导页面
     */
    public static void showFirstGuideActivity(Activity activity) {
        Intent intent = new Intent(activity, FirstGuideActivity.class);
        activity.startActivity(intent);
    }


    /**
     * 显示使用说明页面
     */
    public static void showGuideActivity(Activity activity) {
        Intent intent = new Intent(activity, GuideActivity.class);
        activity.startActivity(intent);
    }

    /**
     * 显示个人信息界面
     */
    public static void showPersonInfoActivity(Activity activity) {
        Intent intent = new Intent(activity, UserDetailInfoActivity.class);
        activity.startActivity(intent);
    }


    /**
     * 跳转到设置界面
     * @param activity
     */
    public static void showSetActivity(Activity activity) {
        Intent intent = new Intent(activity, SetActivity.class);
        activity.startActivity(intent);
    }


    /**
     * 跳转到预期界面
     * @param activity
     */
    public static void showForecastActivity(Activity activity) {
        Intent intent = new Intent(activity, ForecastActivity.class);
        activity.startActivity(intent);
    }
}
