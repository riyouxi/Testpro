package com.example.hello.myapplication.db;


import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

public class DaoManager {

    private static final String DB_NAME ="luow.db";
    private volatile static DaoManager mInstance;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static DevOpenHelper mHelper;
    private static  Context mContext;

    /**
     * 使用单例模式获得操作数据库的对象
     * @return
     */
    public static DaoManager getInstance(){
        if(mInstance == null){
            synchronized (DaoManager.class){
                if(mInstance == null){
                    mInstance = new DaoManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化Context对象
     * @param context
     */
    public void init(Context context){
        mContext = context;
    }

    /**
     * 判断数据库是否存在，如果不存在则创建
     * @return
     */
    public DaoMaster getDaoMaster(){
        if(mDaoMaster == null){
            mHelper = new DevOpenHelper(mContext,DB_NAME,null);
            mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        }
        return mDaoMaster;
    }

    /**
     * 完成对数据库的增删查找
     * @return
     */
    public DaoSession getDaoSession(){
        if(mDaoSession == null){
            if(mDaoMaster == null){
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    /**
     * 设置debug模式开启或关闭，默认关闭
     * @param flag
     */
    public void setDebug(boolean flag){
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }

    /**
     * 关闭数据库
     * */
    public void closeDataBase(){
        closeHelper();
        closeDaoSeesion();
    }

    private void closeHelper() {
        if(mHelper !=null){
            mHelper.close();
            mHelper = null;
        }
    }

    private void closeDaoSeesion(){
        if(mDaoSession !=null){
            mDaoSession.clear();
            mDaoSession = null;
        }
    }


}
