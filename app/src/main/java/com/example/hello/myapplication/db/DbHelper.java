package com.example.hello.myapplication.db;


import org.greenrobot.greendao.AbstractDao;


public class DbHelper<E,K>{


    public static <E,K> AbstractDao<E, K> getDao(Class<E> classType) {
        return (AbstractDao<E, K>) DaoManager.getInstance().getDaoSession().getDao(classType);
    }
}
