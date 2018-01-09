package advert.sdk.com.advertlibrary.intf;

import android.graphics.Bitmap;

/**
 */

public interface OnGetBitmapByurlListener {
    /**
     * 加载在成功的回调
     *
     * */
    void onGetBitmapSuccess(Bitmap bitmap);
    /**
     * 加载失败回调
     * */
    void onGetBitmapFailed();
}
