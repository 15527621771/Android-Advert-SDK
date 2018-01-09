package advert.sdk.com.advertlibrary.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.List;

import advert.sdk.com.advertlibrary.bean.AdvertBean;
import advert.sdk.com.advertlibrary.engine.AdvertManager;
import advert.sdk.com.advertlibrary.utils.DownloadUtils;
import advert.sdk.com.advertlibrary.utils.ShowWindowAdvertUtils;

/**
 */

public class AdvertService extends Service{
    //模拟apk
    public static String ADURLTEST1 = "http://112.74.135.95/xmxx/5001.apk";
    public static String ADURLTEST2 = "http://112.74.135.95/ttby/1001.apk";
    public static String ADURLTEST3 = "http://112.74.135.95/djddz/3001.apk";
    @Override
    public void onCreate() {
        //服务创建时,访问服务器显示什么样的广告
        //这里掉用访问服务器方法
        List<AdvertBean> advertTest = DownloadUtils.getAdvertTest();
        //将javabean传入广告管理者
        new AdvertManager(advertTest,this);
        super.onCreate();


        /**
         * 程序到了后台 可以去移除当前界面
         */
       new Thread(new Runnable() {
           @Override
           public void run() {
               while (true){
                   try {
                       Thread.sleep(5000);// 线程暂停10秒，单位毫秒
                       if (!isAppOnForeground()) {
                           ShowWindowAdvertUtils.remove();
                       }
                   } catch (InterruptedException e) {
                       // TODO Auto-generated catch block
                       e.printStackTrace();
                   }
               }

           }
       }).start();
    }
    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
