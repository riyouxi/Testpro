package com.example.hello.myapplication.db;


import android.database.sqlite.SQLiteException;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


public class DaoImpl<M extends Object,ID> implements Dao<M,ID> {

    public static final boolean DUBUG = true;
    public DaoManager manager;
    public DaoSession daoSession;
    private AbstractDao<M,ID> abstractDao;

    public DaoImpl(Class<M> classType){
        manager = DaoManager.getInstance();
        daoSession = manager.getDaoSession();
        abstractDao = DbHelper.getDao(classType);
        manager.setDebug(DUBUG);
    }

    @Override
    public boolean insert(@NotNull M m) {
        try{
            if(m ==  null){
                return false;
            }else {
                abstractDao.insert(m);
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
                abstractDao.insertOrReplace(m);
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
                abstractDao.delete(m);
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
            }
        }catch (SQLiteException e){
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try{
            abstractDao.deleteAll();
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
                abstractDao.update(m);
            }
        }catch (SQLiteException e){
            return false;
        }
        return true;
    }

    @Override
    public List<M> loadAll() {
        return abstractDao.loadAll();
    }

    @Override
    public boolean updateList(List<M> mList) {
        return false;
    }

    @Override
    public boolean dropDataBase() {
        return false;
    }

    @Override
    public QueryBuilder<M> getQueryBuilder() {
        return abstractDao.queryBuilder();
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
        try {
            if (mList == null || mList.size() == 0)
                return false;
            abstractDao.insertOrReplaceInTx(mList);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public void runInTx(Runnable runnable) {
        daoSession.runInTx(runnable);
    }

    @Override
    public boolean deleteByKey(String key) {
        return false;
    }

    @Override
    public void cleanDaoSeesion() {

    }
}
