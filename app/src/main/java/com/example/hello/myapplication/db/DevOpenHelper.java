package com.example.hello.myapplication.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DevOpenHelper extends DaoMaster.OpenHelper {

    public DevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db,UserDao.class,PersonDao.class);//数据版本变更才会执行
    }
}
