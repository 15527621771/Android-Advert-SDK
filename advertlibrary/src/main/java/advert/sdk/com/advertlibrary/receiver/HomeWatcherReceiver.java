package advert.sdk.com.advertlibrary.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import advert.sdk.com.advertlibrary.bean.AdvertBean;
import advert.sdk.com.advertlibrary.utils.SPUtils;
import advert.sdk.com.advertlibrary.utils.ShowWindowAdvertUtils;

import static advert.sdk.com.advertlibrary.service.AdvertService.ADURLTEST1;


/**
 */

public class HomeWatcherReceiver extends BroadcastReceiver {
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
            if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) {
                // 按下Home键
                SPUtils.put(context,"HomeHitCount",SPUtils.getInt(context,"HomeHitCount")+1);
                //第一次弹
                if (SPUtils.getInt(context,"HomeHitCount")==1) {
                    AdvertBean insertadvertBean=new AdvertBean(5000,0,0,"http://oqv0h4wnb.bkt.clouddn.com/splash3.png",ADURLTEST1);//插屏
                    ShowWindowAdvertUtils.init(insertadvertBean.getAdvertType(),insertadvertBean.getBannerLocation(),insertadvertBean.getAdvertApkDownloadUrl(),insertadvertBean.getAdvertPicUrl(),insertadvertBean.getAdvertTime(),context);
                    ShowWindowAdvertUtils.show();
                }
            }
        }
    }
}
