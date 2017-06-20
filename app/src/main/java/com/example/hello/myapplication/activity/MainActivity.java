package com.example.hello.myapplication.activity;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hello.myapplication.R;
import com.example.hello.myapplication.db.Dao;
import com.example.hello.myapplication.db.DaoImpl;
import com.example.hello.myapplication.db.DbHelper;
import com.example.hello.myapplication.db.Person;
import com.example.hello.myapplication.db.User;
import com.example.hello.myapplication.http.DataBean;
import com.example.hello.myapplication.http.OpsRequest;
import com.example.hello.myapplication.http.RequestManager;
import com.example.hello.myapplication.http.TestRequest;
import com.example.hello.myapplication.http.exception.OkHttpException;
import com.example.hello.myapplication.http.listener.DisposeDataListener;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private boolean stop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;

        DaoImpl<Person,String> daoImpl = new DaoImpl<>(Person.class);
        Person person = new Person();
        person.setName("刘狗");
        daoImpl.insert(person);
         List<Person> data =  daoImpl.loadAll();
        Log.e("test",data.get(0).getName());


        /*try {
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

        }*/

        /*RequestManager.requestHomeData(DataBean.class, new DisposeDataListener<DataBean>() {
            @Override
            public void onSuccess(DataBean user) {
                user.ads.toString();

            }

            @Override
            public void onFailure(OkHttpException e) {

            }
        });*/
        String url =  "https://github.com/hongyangAndroid";
        OpsRequest<TestRequest,DataBean> excutor = OpsRequest.create(url);
        TestRequest test = new TestRequest();
        test.setAge(18);
        test.setName("test");
        excutor.requestValue(test)
                .setResponseValue(DataBean.class)
                .excute(new DisposeDataListener<DataBean>() {
            @Override
            public void onSuccess(DataBean dataBean) {

            }

            @Override
            public void onFailure(OkHttpException e) {

            }
        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop = true;
    }
}
