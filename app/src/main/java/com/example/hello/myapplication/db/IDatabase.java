package com.example.hello.myapplication.db;


import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public interface IDatabase<M,K> {

    boolean insert(M m);

    boolean delete(M m);

    boolean deleteByKey(K key);

    boolean deleteList(List<M> mList);

    //boolean deleteByKeyInTx(K...key);

    boolean deleteAll();

    boolean insertOrReplace(@NotNull M m);

    boolean update(M m);

   // boolean updateInInTx(M...m);

    boolean updateList(List<M> mList);

    M selectByPrimarKey(K key);

    List<M> loadAll();

    boolean refresh(M m);

    void cleanDaoSeesion();

    boolean dropDataBase();

    /**
     * 事务
     *
     */
    void runInTx(Runnable runnable);


    boolean insertList(List<M> mList);

    boolean insertOrReplaceList(List<M> mList);

    QueryBuilder<M> getQueryBuilder();

   // List<M> queryRaw(String where,String...slectArgs);

}
