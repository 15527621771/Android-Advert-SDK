package advert.sdk.com.advertlibrary.receiver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import advert.sdk.com.advertlibrary.service.AdvertService;

/**
 */

public class AdvertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isServiceRunning=false;
        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
            //检查Service状态
            //检查Service状态
            ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
           //当前服务
            for (ActivityManager.RunningServiceInfo service :manager.getRunningServices(Integer.MAX_VALUE)) {
                if("advert.sdk.com.advertlibrary.service.AdvertService".equals(service.service.getClassName())){
                    isServiceRunning = true;
                }
            }
            //开启服务
            if (!isServiceRunning) {
                Intent i = new Intent(context, AdvertService.class);
                context.startService(i);
            }
        }
    }
}
