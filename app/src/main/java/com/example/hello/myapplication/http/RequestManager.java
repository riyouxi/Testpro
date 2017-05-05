package com.example.hello.myapplication.http;

import android.os.Environment;

import com.example.hello.myapplication.http.client.CommonOkhttpClient;
import com.example.hello.myapplication.http.listener.DisposeDataHandle;
import com.example.hello.myapplication.http.listener.DisposeDataListener;
import com.example.hello.myapplication.http.listener.DisposeDownloadListener;
import com.example.hello.myapplication.http.request.CommonRequest;
import com.example.hello.myapplication.http.request.RequestParams;
import com.example.hello.myapplication.http.response.CommonJsonCallback;

import java.io.File;

/**
 * Created by gechuanguang on 2017/2/22.
 * 邮箱：1944633835@qq.com
 *
 * @Function 来管理一些网络请求
 */
public class RequestManager {


    //根据参数发送所有get请求
    private static void getRequest(String url, RequestParams params, DisposeDataListener<?> listener, Class<?> clazz) {
        CommonOkhttpClient.sendRequest(CommonRequest.createGetRequest(url, params),
                new CommonJsonCallback(new DisposeDataHandle(listener, clazz)));
    }

    //根据参数发送所有post请求
    private static void postRequest(String url, RequestParams params, DisposeDataListener<?> listener, Class<?> clazz) {
        CommonOkhttpClient.sendRequest(CommonRequest.createPostRequest(url, params),
                new CommonJsonCallback(new DisposeDataHandle(listener, clazz)));
    }


    /**
     * 请求主页数据
     *
     * @param listener
     */
    public static void requestHomeData(Class clazz, DisposeDataListener<?> listener) {
        RequestManager.getRequest(UrlConstant.wxUrl, null, listener, clazz);
    }

    /**
     * 文件下载
     */
    public static void downloadFile(DisposeDownloadListener<File> disposeDownloadListener) {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "okhttp_download.jpg";

        CommonOkhttpClient.downloadFile(CommonRequest.createGetRequest(UrlConstant.DOWNLOAD_URL,null),
                new DisposeDataHandle(disposeDownloadListener,path));
    }


    /**
     *  apk 下载
     */
    public static void downloadApk(DisposeDownloadListener<File> disposeDownloadListener) {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DOWNLOAD_APK";

        CommonOkhttpClient.downloadFile(CommonRequest.createGetRequest(UrlConstant.VIDEO_URL,null),
                new DisposeDataHandle(disposeDownloadListener,path+File.separator+"hello.mp4"));
    }
}
