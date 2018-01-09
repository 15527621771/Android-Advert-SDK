package advert.sdk.com.advertlibrary.intf;

import java.io.File;

/**
 * 下载监听器
 * */
public interface OnDownloadListener {
	/**
	 * 下载在成功的回调
	 * 
	 * */
	void onDownloadSuccess(File destFile);
	/**
	 * 下载失败回调
	 * */
	void onDownloadFailed();
}
