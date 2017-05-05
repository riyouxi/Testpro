package com.example.hello.myapplication.db;


import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class UserManager extends AbstractDatabaseManager<User,Long> {

    @Override
    AbstractDao<User, Long> getAbstracDao() {
        return daoSession.getUserDao();
    }


}
