package com.example.lttha.a14110180_lethithao_foody.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.R;

public class SearchActivity extends AppCompatActivity {
    //Chưa cần thiết kế

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
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
}
