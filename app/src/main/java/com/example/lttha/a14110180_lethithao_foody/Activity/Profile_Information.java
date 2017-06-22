package com.example.lttha.a14110180_lethithao_foody.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.DataBase.DBUserServer;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.User;

import java.util.ArrayList;

public class Profile_Information extends AppCompatActivity  {

    User user;
    EditText edNameLogin,edname,edLastname;
    TextView txtEmail,txtDate,txtSex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        edNameLogin=(EditText) findViewById(R.id.ed_name_login_info);
        edname=(EditText) findViewById(R.id.ed_name_info);
        edLastname=(EditText) findViewById(R.id.ed_lastname_info);
        txtEmail=(TextView) findViewById(R.id.txt_email_info);
        txtDate=(TextView) findViewById(R.id.txt_date_info);
        txtSex=(TextView) findViewById(R.id.txt_sex_info);

        user = ProfileActivity.userCurrent;

        edNameLogin.setText(user.getUsername());
        edname.setText(user.getName());
        edLastname.setText(user.getLastname());
        txtEmail.setText(user.getMail());
        txtSex.setText(user.getSex());
        txtDate.setText(user.getBirthday());


    }

}
