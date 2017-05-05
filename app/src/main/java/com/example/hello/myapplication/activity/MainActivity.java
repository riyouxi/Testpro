package com.example.hello.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hello.myapplication.R;
import com.example.hello.myapplication.db.User;
import com.example.hello.myapplication.db.UserManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;

        User user = new User();
        user.setAge(11);
        user.setIsBoy(true);
        user.setName("test");
        new UserManager().insert(user);

        List<User> data  =  new UserManager().loadAll();
        if(data!=null && data.size()!=0){
            Toast.makeText(MainActivity.this,data.get(0).getName(),Toast.LENGTH_SHORT).show();
        }
    }
}
