package advert.sdk.com.advertlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import advert.sdk.com.advertlibrary.R;
import advert.sdk.com.advertlibrary.constant.AdvertConstant;
import advert.sdk.com.advertlibrary.intf.OnDownloadListener;
import advert.sdk.com.advertlibrary.intf.UIProgressResponseListener;

import static android.content.Context.WINDOW_SERVICE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * 管理ad
 */

public class ShowWindowAdvertUtils {

    private static WindowManager windowManager;
    private static View advertView;
    private static Context context;
    private static String apkUrl;
    private static WindowManager.LayoutParams params;
    private static int advertTime;

    public static void init(int advertType, int bannerLocation, String mapkUrl, String picUtils, int madvertTime, Context mcontext) {
        context = mcontext;
        apkUrl = mapkUrl;
        advertTime = madvertTime;
        params = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_TOAST);
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        if (advertType == AdvertConstant.INSERT_ADVERT_TYPE) {
            //params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.width =400;
            params.height = 650;
            params.gravity = Gravity.CENTER;
        } else {
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = 150;
            params.gravity = bannerLocation == 1 ? Gravity.TOP : Gravity.BOTTOM;
        }
        if (windowManager != null && params != null) {
            remove();
        }
        windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        advertView = View.inflate(context, R.layout.dialog_advert_view, null);
        ImageView iv_dialog_advert = ((ImageView) advertView.findViewById(R.id.iv_dialog_advert));
        iv_dialog_advert.setOnClickListener(onDownlodClick);
        Picasso.with(context).load(picUtils).fit().into(iv_dialog_advert);
        advertView.findViewById(R.id.iv_dialog_closed).setOnClickListener(onclosedClick);
    }
    /**
     * 显示广告 传入视图 和布局参数
     */
    public static void show() {
        windowManager.addView(advertView, params);
        //定时关闭
      /*  Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                remove();
            }
        }, advertTime);*/
    }
    /**
     * 关闭当前视图
     */
    public static void remove() {
        try {
            windowManager.removeView(advertView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 点击关闭
     */
    static View.OnClickListener onclosedClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            remove();
        }
    };
    /**
     * 点击下载
     */
    static View.OnClickListener onDownlodClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            remove();
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
                            DownloadUtils.showProgressnotifivation(context, (int) ratio);
                        }
                    }
                }
            });
        }
    };

    // 安装方法
    public static void installApk(File destFile, Context context) {
        Intent intent = new Intent();
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(destFile), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
