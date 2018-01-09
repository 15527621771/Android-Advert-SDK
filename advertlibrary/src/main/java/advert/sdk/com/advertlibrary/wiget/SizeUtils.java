package advert.sdk.com.advertlibrary.wiget;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


/**
 * 像素转换
 *      屏幕尺寸相关工具类
 */
public class SizeUtils {

    /**
     * 获取手机屏幕分辨率，
     *
     * @param type     参数1表示获取屏幕宽像素值，2表示获取屏幕高像素值，3表示分辨率
     */
    public static int getDisplay(Activity activity, int type) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        Display localDisplay = activity.getWindowManager().getDefaultDisplay();
        localDisplay.getMetrics(localDisplayMetrics);
        int size = 0;
        switch (type) {
            case 1:
                size = localDisplayMetrics.widthPixels;
                break;
            case 2:
                size = localDisplayMetrics.heightPixels;
                break;
            case 3:
                size = localDisplayMetrics.densityDpi;
                break;
        }
        return size;
    }

    /**
     * 获取手机屏幕的宽度
     *
     */
    public static int getScreenWidth(Activity activity) {
//        WindowManager wm = activity.getWindowManager();
//        int width = wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        Display localDisplay = activity.getWindowManager().getDefaultDisplay();
        localDisplay.getMetrics(localDisplayMetrics);
        return localDisplayMetrics.widthPixels;
    }

    /**
     * 获取手机屏幕的高度
     *
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        Display localDisplay = activity.getWindowManager().getDefaultDisplay();
        localDisplay.getMetrics(localDisplayMetrics);
        return localDisplayMetrics.heightPixels;
    }

    /**
     * 获得屏幕的宽
     */
    public static int getWidths(Activity activity){
        int screenWidth;//宽度
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        return screenWidth;
    }

    /**
     * 获得屏幕的高
     */
    public static int getHeights(Activity activity){
        int screenHeight;//高度
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenHeight = display.getHeight();
        return screenHeight;
    }

    /**
     * 将dip转换为px
     *
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px转换为dip
     *
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f) - 15;
    }

    /**
     * 将sp转换为px
     *
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 将px转换为sp
     *
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

}