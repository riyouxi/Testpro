/*
package com.example.hello.myapplication.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public abstract class AbstractDatabaseManager<M,K> implements Dao<M,K> {

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

    */
/**     * Query for writable DB     *//*

    protected static void openWritableDb() throws SQLiteException {
        daoSession = new DaoMaster(getWritableDatabase()).newSession();
    }

    private static SQLiteDatabase getWritableDatabase() {
        return mHelper.getWritableDatabase();
    }

    private static SQLiteDatabase getReadableDatabase(){
        return mHelper.getReadableDatabase();
    }

    */
/**
     * 只关闭helper就好,看源码就知道helper关闭的时候会关闭数据库
     *//*

    public static void closeDbConnections() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }

        if(daoSession !=null){
            daoSession.clear();
            daoSession = null;
        }
    }

    @Override
    public void cleanDaoSeesion() {
        if(daoSession!=null){
            daoSession.clear();
            daoSession = null;
        }
    }

    @Override
    public boolean insert(@NotNull M m) {
        try{
            if(m ==  null){
                return false;
            }else {
                openWritableDb();
                getAbstracDao().insert(m);
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean insertOrReplace(@NotNull M m) {
        try{
            if(m ==  null){
                return false;
            }else {
                openWritableDb();
                getAbstracDao().insertOrReplace(m);
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(M m) {
        try{
            if(m ==  null){
                return false;
            }else {
                openWritableDb();
                getAbstracDao().delete(m);
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteByKey(K key) {
        try{
            if(TextUtils.isEmpty(key.toString())){
                return false;
            }else {
                openWritableDb();
                getAbstracDao().deleteByKey(key);
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteList(List<M> mList) {
        try {
            if (mList == null || mList.size() == 0) {
                return false;
            } else {
                openWritableDb();
                getAbstracDao().deleteInTx(mList);
            }
        }catch (SQLiteException e){
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try{
            openWritableDb();
            getAbstracDao().deleteAll();
        }catch (SQLiteException e){
            return false;
        }
        return  false;
    }

    @Override
    public boolean update(M m) {
        try{
            if(m ==  null){
                return false;
            }else {
                openWritableDb();
                getAbstracDao().update(m);
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    @Override
    public List<M> loadAll() {
        openWritableDb();
        return getAbstracDao().loadAll();
    }

    @Override
    public boolean updateList(List<M> mList) {
        return false;
    }

    @Override
    public M selectByPrimarKey(K key) {
        return null;
    }

    @Override
    public boolean dropDataBase() {
        return false;
    }

    @Override
    public QueryBuilder<M> getQueryBuilder() {
        return null;
    }

    @Override
    public boolean refresh(M m) {
        return false;
    }

    @Override
    public boolean insertList(List<M> mList) {
        return false;
    }

    @Override
    public boolean insertOrReplaceList(List<M> mList) {
        return false;
    }

    @Override
    public void runInTx(Runnable runnable) {

    }

    abstract AbstractDao<M,K> getAbstracDao();
}
*/
