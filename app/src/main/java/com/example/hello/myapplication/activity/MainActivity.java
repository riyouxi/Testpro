package com.example.hello.myapplication.activity;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hello.myapplication.R;
import com.example.hello.myapplication.db.DaoImpl;
import com.example.hello.myapplication.db.DbHelper;
import com.example.hello.myapplication.db.User;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean stop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;


        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(!stop){
                        DaoImpl<User,String> daoImpl = new DaoImpl<User,String>(User.class);
//                        final  AbstractDao<User,String> dao = DbHelper.getDao(User.class);
                        User user = new User();
                        user.setAge(11);
                        user.setIsBoy(true);
                        user.setName("test");
                        daoImpl.insert(user);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!stop){
                        final  AbstractDao<User,String> dao = DbHelper.getDao(User.class);
                        List<User> data = dao.loadAll();
                        if(data!=null && data.size()!=0){
                            for(User user : data){
                                Log.e("test",user.getName());
                            }
                        }
                        try {
                            Thread.sleep(15);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();


        }catch (SQLiteException e){

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop = true;
    }
}
