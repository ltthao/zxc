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

public class Login_Register extends AppCompatActivity implements DBUserServer.getUserListener{

    TextView txtRegister;
    DBUserServer dbServer;
    EditText edEmail, edPass, edRePass,edUserName;
    public static User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setDisplayHomeAsUpEnabled(true);
        }

        dbServer = new DBUserServer(this);

        txtRegister=(TextView) findViewById(R.id.txt_register);
        edEmail=(EditText) findViewById(R.id.edEmail);
        edPass=(EditText) findViewById(R.id.edPass);
        edRePass=(EditText) findViewById(R.id.edRepass);
        edUserName=(EditText) findViewById(R.id.edUserName);
        //Click vào đăng ký
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //Nếu pass trùng khớp
            if(edPass.getText().toString().equals(edRePass.getText().toString()))
            {
                dbServer.InsertUser(edEmail.getText().toString(),edPass.getText().toString(),edUserName.getText().toString());

                Intent data = new Intent();
                data.putExtra("email",edEmail.getText().toString()); // Gửi các dữ liệu qua ProfileActivity
                data.putExtra("passw",edPass.getText().toString());
                setResult(RESULT_OK,data);

                Log.e("insert",String.valueOf(edEmail.getText())+" "+String.valueOf(edUserName.getText()));
                Toast.makeText(Login_Register.this,"Đăng ký thành công!",Toast.LENGTH_LONG).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        finish();
                    }
                }, 1500);   //
            }
            else
                Toast.makeText(Login_Register.this,"Mật khẩu không khớp!",Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public void getUser(ArrayList<User> listUser) {
//        user = listUser.get(1);
//        Intent data = new Intent();
//        data.putExtra("email",user.getMail()); // Gửi các dữ liệu qua ProfileActivity
//        data.putExtra("passw",user.getPass());
//        setResult(RESULT_OK,data);

    }
}
