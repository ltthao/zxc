package com.example.lttha.a14110180_lethithao_foody.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.R;

public class ScreenFoody extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_foody);

            progressBar = (ProgressBar) findViewById(R.id.pg_flash_screen);
            //tạo 1 luồng thread
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(4000); //chạy loading 4 giây
                    }catch (Exception e){

                    }finally{
                        Intent intent=new Intent(ScreenFoody.this,MainActivity.class);
                        startActivity(intent); finish();
                    }
                }
            });
            thread.start();
    }
}
