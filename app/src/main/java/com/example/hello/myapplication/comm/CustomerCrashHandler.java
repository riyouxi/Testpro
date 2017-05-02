package com.example.hello.myapplication.comm;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/***
 * 自定义Crash捕捉类
 * */
public class CustomerCrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = CustomerCrashHandler.class.getSimpleName();
    private static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().toString();

    private Context mContext;
    private static CustomerCrashHandler instance = new CustomerCrashHandler();

    public CustomerCrashHandler(){
    }

    public static CustomerCrashHandler getInstance(){
        if(instance == null){
            instance = new CustomerCrashHandler();
        }
        return instance;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        //将信息保存到sdcard
        saveExceptionToSD(mContext,ex);

        //提示用户程序即将退出
        showToast(mContext, "很抱歉，程序遭遇异常，即将退出！");
        try {
            thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    /**
     * 显示提示信息，需要在线程中显示Toast
     * @param context
     * @param msg
     */
    private void showToast(final Context context, final String msg){
        new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();
    }




    /**
     * 设置context
     * */
    public void setCustomCrashHandler(Context context){
        this.mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 保存获取的 软件信息，设备信息和出错信息保存在SDcard中
     * @param mContext
     * @param ex
     * @return
     */
    private String saveExceptionToSD(Context mContext, Throwable ex) {
        String fileName = null;
        StringBuffer sb = new StringBuffer();
        for(Map.Entry<String,String> entry : obtainSimpleInfo(mContext).entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append("=").append(value).append("\n");
        }
        sb.append(obtainExceptionInfo(ex));

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File dir = new File(SDCARD_ROOT+File.separator+"crash"+File.separator);
            if(!dir.exists()){
                dir.mkdirs();
            }
            fileName = dir.toString()+File.separator+paserTime(System.currentTimeMillis())+".log";
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(fileName);
                fos.write(sb.toString().getBytes());
                fos.flush();
                fos.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

        }
        return fileName;

    }

    /**
     * 获取一些简单的信息,软件版本，手机版本，型号等信息存放在HashMap中
     * */
    private Map<String,String> obtainSimpleInfo(Context context){
        Map<String,String> map = new HashMap<String, String>();
        PackageManager pm = context.getPackageManager();
        PackageInfo mPackageInfo = null;
        try {
            mPackageInfo = pm.getPackageInfo(mContext.getPackageName(),PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        map.put("versionName",mPackageInfo.versionName);
        map.put("versionCode",mPackageInfo.versionCode+"");
        map.put("MODLE", Build.MODEL);
        map.put("SDK_INT",Build.VERSION.SDK_INT+"");
        map.put("PRODUCT",Build.PRODUCT);
        return map;
    }

    /**
     * 获取系统未捕捉的错误信息
     * */
    private String obtainExceptionInfo(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        pw.close();
        Log.e(TAG,sw.toString());
        return sw.toString();
    }

    /**
     * 将毫秒数转换成yyyy-MM-dd-HH-mm-ss的格式
     * @param milliseconds
     * @return
     */
    private String paserTime(long milliseconds) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String times = format.format(new Date(milliseconds));

        return times;
    }
}
