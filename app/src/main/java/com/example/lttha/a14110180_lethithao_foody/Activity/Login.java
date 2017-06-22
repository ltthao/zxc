package com.example.lttha.a14110180_lethithao_foody.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lttha.a14110180_lethithao_foody.R;

public class Login extends AppCompatActivity {
    TextView txtSingin, txtSingup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setDisplayHomeAsUpEnabled(true);
        }
        txtSingin=(TextView) findViewById(R.id.txtSinginEmail);
        //Mở activity LoginWithEmail
        txtSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this,LoginWithEmail.class);
                startActivityForResult(intent, 1);

            }
        });

        txtSingup=(TextView) findViewById(R.id.txtSingup);
        //Mở activity Login_Register
        txtSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this,Login_Register.class);
                startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                Intent data1 = new Intent();
                data1.putExtra("email",data.getStringExtra("email"));
                data1.putExtra("passw",data.getStringExtra("passw"));
                setResult(RESULT_OK,data1);
                finish();
            }
        }
        if(requestCode==2){
            if(resultCode==RESULT_OK){
                Intent data1 = new Intent();
                data1.putExtra("email",data.getStringExtra("email"));
                data1.putExtra("passw",data.getStringExtra("passw"));
                setResult(RESULT_OK,data1);
                finish();
            }
        }        super.onActivityResult(requestCode, resultCode, data);
    }
}
