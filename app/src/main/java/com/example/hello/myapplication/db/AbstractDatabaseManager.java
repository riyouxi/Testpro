package com.example.hello.myapplication.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.Nullable;

import org.greenrobot.greendao.annotation.NotNull;

public abstract class AbstractDatabaseManager<M,K> implements IDatabase<M,K> {

    private static final String DB_NAME ="luow.db";
    private static DaoMaster.DevOpenHelper mHelper = null;
    protected static DaoSession daoSession;

    public static void initOpenHelper(@NotNull Context context){
        mHelper = getOpenHelper(context,DB_NAME);
        openWritableDb();
    }

    private static DaoMaster.DevOpenHelper getOpenHelper(@NotNull Context context, @Nullable String dataBaseName){
        closeDbConnections();
        return new DaoMaster.DevOpenHelper(context, dataBaseName, null);
    }

    /**     * Query for writable DB     */
    protected static void openWritableDb() throws SQLiteException {
        daoSession = new DaoMaster(getWritableDatabase()).newSession();
    }

    private static SQLiteDatabase getWritableDatabase() {
        return mHelper.getWritableDatabase();
    }

    /**
     * 只关闭helper就好,看源码就知道helper关闭的时候会关闭数据库
     */
    public static void closeDbConnections() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }
}
