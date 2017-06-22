package com.example.lttha.a14110180_lethithao_foody.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lttha.a14110180_lethithao_foody.DataBase.DBUserServer;
import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.SessionManager;
import com.example.lttha.a14110180_lethithao_foody.Utils.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements DBUserServer.getUserListener {
    LinearLayout lnLogin, lnLogout,lnInfo, lnSetting;
    TextView txtUseName;
    public static User userCurrent=null;
    public static Boolean isLogin = false;
    SessionManager session;
    CircleImageView imgUserAvatar;
    DBUserServer dbUserServer;
    private static final int SECOND_ACTIVITY_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        lnLogin=(LinearLayout) findViewById(R.id.lnLogin);
        txtUseName=(TextView) findViewById(R.id.txtUserName);
        lnLogout=(LinearLayout) findViewById(R.id.lnLogout);
        lnInfo=(LinearLayout) findViewById(R.id.lnInformation);
        lnSetting=(LinearLayout) findViewById(R.id.lnSetting);
        imgUserAvatar=(CircleImageView) findViewById(R.id.imgUserAvatar);

        session = new SessionManager(getBaseContext());
        dbUserServer = new DBUserServer(this);
        //Ẩn lnLoout khi chưa login
        lnLogout.setVisibility(View.GONE);
        //Mở activity login
        lnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ProfileActivity.this,Login.class);
                startActivityForResult(intent, 1);// Yêu cầu start Activity và sẽ có phản hồi từ Login
            }
        });
        //Mở activity Profile_Information
        lnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ProfileActivity.this, Profile_Information.class);
                ProfileActivity.this.startActivity(intent);
            }
        });
        //Mở activity ProfileSetting
        lnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ProfileActivity.this, ProfileSetting.class);
                ProfileActivity.this.startActivity(intent);
            }
        });
        lnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
                txtUseName.setText("Đăng nhập");
                imgUserAvatar.setImageResource(R.drawable.tn_ic_not_login_profile);
                lnLogout.setVisibility(View.GONE);
            }
        });
        CheckLogin();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onBackPressed() {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);//clear top of stack.
        startActivity(myIntent);
        finish();
        return;
    }
    public void CheckLogin()
    {
        if(session.isLoggedIn())
        {
            lnLogout.setVisibility(View.VISIBLE);
            HashMap<String, String> userss = session.getUserDetails();
            dbUserServer.Login(userss.get(SessionManager.KEY_EMAIL),userss.get(SessionManager.KEY_PASS));
        }
    }

// Function dùng để đọc kết quả trả về từ một Activity mới được tạo

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String email ="";
        String passw = "";
        if(resultCode==RESULT_OK){
            email= data.getStringExtra("email");// Nhận dữ liệu
            passw= data.getStringExtra("passw");
            session.createLoginSession(email,passw);
            CheckLogin();
            if(requestCode==1){   // nhận request khi click vào đăng nhập
                Toast.makeText(ProfileActivity.this,email, Toast.LENGTH_SHORT).show();

            }

//            else if(requestCode==2){   // nhận request khi click vào đăng nhập
//                Toast.makeText(ProfileActivity.this,email+" vào thông tin tài khoản", Toast.LENGTH_LONG).show();
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    public void run() {   // ko loi
//                        Intent intent = new Intent(ProfileActivity.this, Profile_Information.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
//                        startActivity(intent);
//                    }
//                }, 1500);
//
//            }
//            else if(requestCode==3)   // nhận request khi click vào thông tin tài khoản
//            {
//                Toast.makeText(ProfileActivity.this,email+" vào thiết lập tài khoản", Toast.LENGTH_LONG).show();
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    public void run() {
//                        Intent intent = new Intent(ProfileActivity.this, ProfileSetting.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
//                        startActivity(intent);
//                    }
//                }, 1500);
//
//            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void getUser(ArrayList<User> listUser) {
        userCurrent = listUser.get(0);
            String url_imgitem="https://foody-v2.herokuapp.com/getimg?nameimg="; // doi lai
            Picasso.with(this).load(url_imgitem+userCurrent.getImg()+".png")
                    .placeholder(R.drawable.fdi1)
                    .error(R.drawable.fdi1)
                    .into(imgUserAvatar);
            txtUseName.setText(userCurrent.getUsername());
    }
}
