package advert.sdk.com.advertlibrary.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.File;

import advert.sdk.com.advertlibrary.intf.OnDownloadListener;
import advert.sdk.com.advertlibrary.intf.UIProgressResponseListener;
import advert.sdk.com.advertlibrary.utils.DownloadUtils;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 */

public class NotificationAdvertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d("NotificationAdvertRecei", "点击了");
        String apkUrl = intent.getStringExtra("apkUrl");
        DownloadUtils.download(apkUrl, new OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File destFile) {
                installApk(destFile, context);
            }

            @Override
            public void onDownloadFailed() {

            }
        }, new UIProgressResponseListener() {
            @Override
            public void onUIResponseProgress(long bytesRead, long contentLength, boolean done) {
                if (contentLength != -1) {
                    //长度未知的情况下回返回-1
                    long ratio = (100 * bytesRead) / contentLength;
                    if (ratio % 5 == 0) {
                        DownloadUtils.showProgressnotifivation(context,(int) ratio);
                    }
                }
            }
        });
    }

    // 安装方法
    public void installApk(File destFile, Context context) {
        Intent intent = new Intent();
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(destFile), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
