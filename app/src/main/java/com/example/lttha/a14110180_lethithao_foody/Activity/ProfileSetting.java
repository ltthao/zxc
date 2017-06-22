package com.example.lttha.a14110180_lethithao_foody.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lttha.a14110180_lethithao_foody.R;

public class ProfileSetting extends AppCompatActivity {
    LinearLayout lnChangePass,lnChangeAva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        lnChangePass=(LinearLayout) findViewById(R.id.lnChangePass);
        lnChangeAva=(LinearLayout) findViewById(R.id.lnChangeAva);
        //Mở activity ChangePass
        lnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ProfileSetting.this,ChangePass.class);
                ProfileSetting.this.startActivity(intent);
            }
        });
        //Mở activity ChangeAvatar
        lnChangeAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ProfileSetting.this,ChangeAvatar.class);
                ProfileSetting.this.startActivity(intent);

            }
        });

    }
}
