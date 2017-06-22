package com.example.lttha.a14110180_lethithao_foody;

import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.example.lttha.a14110180_lethithao_foody.Activity.CollectionActivity;
import com.example.lttha.a14110180_lethithao_foody.Activity.HomeActivity;
import com.example.lttha.a14110180_lethithao_foody.Activity.NotificationActivity;
import com.example.lttha.a14110180_lethithao_foody.Activity.ProfileActivity;
import com.example.lttha.a14110180_lethithao_foody.Activity.SearchActivity;


public class MainActivity extends TabActivity {

    public static TabHost tabhost;

    //Called when the activity is first created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabhost = (TabHost) findViewById(android.R.id.tabhost);
        tabhost.setup();

        //Tạo các tab menu
        TabHost.TabSpec tab_home = tabhost.newTabSpec("Home tab");
        TabHost.TabSpec tab_collection = tabhost.newTabSpec("Collection Tab");
        TabHost.TabSpec tab_search = tabhost.newTabSpec("Search Tab");
        TabHost.TabSpec tab_notification = tabhost.newTabSpec("Notification Tab");
        TabHost.TabSpec tab_profile = tabhost.newTabSpec("Profile Tab");

        //Set tab HomeActivity vào tab_home
        tab_home.setContent(new Intent(this, HomeActivity.class));
        //Set hình ảnh cho tab_home
        tab_home.setIndicator("", getResources().getDrawable(R.drawable.tab_home));

        //Set tab 2 CollectionActivity to tab_collection.
        tab_collection.setContent(new Intent(this, CollectionActivity.class));
        //Set hình ảnh cho tab
        tab_collection.setIndicator("", getResources().getDrawable(R.drawable.tab_collection));

        //Set tab 3 SearchActivity to tab_search.
        tab_search.setContent(new Intent(this, SearchActivity.class));
        //Set hình ảnh cho tab
        tab_search.setIndicator("", getResources().getDrawable(R.drawable.tab_search));

        //Set tab NotificationActivity to tab_notification.
        tab_notification.setContent(new Intent(this, NotificationActivity.class));
        //Set hình ảnh cho tab
        tab_notification.setIndicator("", getResources().getDrawable(R.drawable.tab_notification));

        //Set tab ProfileActivity to tab_search.
        tab_profile.setContent(new Intent(this, ProfileActivity.class));
        //Set hình ảnh cho tab
        tab_profile.setIndicator("", getResources().getDrawable(R.drawable.tab_polife));

        //Adding tab_home, tab_collection, tab_search,tab_notification,tab_profile to tabHost view.
        tabhost.addTab(tab_home);
        tabhost.addTab(tab_collection);
        tabhost.addTab(tab_search);
        tabhost.addTab(tab_notification);
        tabhost.addTab(tab_profile);
    }
    public  static void setStateTab(int viewId){
        tabhost.getTabWidget().setVisibility(viewId);
    }
}
