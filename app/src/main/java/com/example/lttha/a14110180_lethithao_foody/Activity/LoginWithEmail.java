package com.example.lttha.a14110180_lethithao_foody.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lttha.a14110180_lethithao_foody.DataBase.DBUserServer;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.User;

import java.util.ArrayList;

public class LoginWithEmail extends AppCompatActivity implements DBUserServer.getUserListener {

    EditText edEmail, edPass;
    TextView txtLogin;
    DBUserServer dbServer;
    public static User user = null;
    String email,passw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setDisplayHomeAsUpEnabled(true);
        }
        dbServer=new DBUserServer(this);

        edEmail=(EditText) findViewById(R.id.edLoginEmail);
        edPass=(EditText) findViewById(R.id.edLoginPass);
        txtLogin=(TextView) findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edEmail.getText().toString();
                passw = edPass.getText().toString();
                if(email.trim().length() > 0 && passw.trim().length() > 0)
                {
                    dbServer.Login(email,passw);
                }
                else
                {
                    Toast.makeText(LoginWithEmail.this,"Bạn cần nhập đầy đủ email và pass", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public void getUser(ArrayList<User> listUser) {
        user = listUser.get(0);
        Log.e("","Đang kiểm tra pass "+user.getName());
        if(!user.getMail().equals(email) || !user.getPass().equals(passw))
        {
            Toast.makeText(LoginWithEmail.this,"Password hoặc mật khẩu không đúng !", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent data = new Intent();
            data.putExtra("email",user.getMail());
            data.putExtra("passw",user.getPass());
            setResult(RESULT_OK,data);
            Toast.makeText(LoginWithEmail.this,"Đăng nhập thành công \n đang chuyển hướng !", Toast.LENGTH_LONG).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                }
            }, 1500);   //
        }

    }
}
