package com.example.lttha.a14110180_lethithao_foody.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lttha.a14110180_lethithao_foody.Activity.Fragment.FoodFragment;
import com.example.lttha.a14110180_lethithao_foody.Activity.Fragment.PlaceFragment;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBUserServer;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.SessionManager;
import com.example.lttha.a14110180_lethithao_foody.Utils.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements DBUserServer.getUserListener{

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    SessionManager session;
    DBUserServer dbUserServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Add tab sử dụng addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Ở đâu"));
        tabLayout.addTab(tabLayout.newTab().setText("Ăn gì"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        //Khởi tạo viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Tạo pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        Button btnF=(Button) findViewById(R.id.btnF);

        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iChangeCity = new Intent(HomeActivity.this, CategoryType.class);
                HomeActivity.this.startActivity(iChangeCity);
            }
        });

        //Them bottomSheetDialog khi click vào button +

        View bottomSheetView = getLayoutInflater().inflate(R.layout.buttom_sheet_layout, null);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HomeActivity.this);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.setCancelable (true);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);

        LinearLayout Ecoupon = (LinearLayout) bottomSheetView.findViewById(R.id.ln1);
        LinearLayout UploadImg = (LinearLayout) bottomSheetView.findViewById(R.id.ln2);
        LinearLayout CheckIn = (LinearLayout) bottomSheetView.findViewById(R.id.ln3);
        LinearLayout Review = (LinearLayout) bottomSheetView.findViewById(R.id.ln4);
        LinearLayout AddNewPlace = (LinearLayout) bottomSheetView.findViewById(R.id.ln5);

        //Khi click vào từng dòng sẽ hiện các text lên
        Ecoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Quét mã coupon", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.hide();
            }
        });

        UploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Đăng ảnh", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.hide();
            }
        });

        CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Check in", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.hide();
            }
        });
        Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Viết bình luận", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.hide();
            }
        });

        AddNewPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(getBaseContext());
                if(sessionManager.isLoggedIn()) // neu da dang nhap
                {
                    Intent iAddLocation = new Intent(HomeActivity.this, AddLocation.class);
                    HomeActivity.this.startActivity(iAddLocation);
                }
                else
                {
                    Intent intent = new Intent(HomeActivity.this, Login.class); // Chuyển tới Login khi click vào button
                        startActivityForResult(intent,1);
                }

            }
        });

        findViewById(R.id.btnPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetDialog.show();
            }
        });

        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

            }
        });

        bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            String email = data.getStringExtra("email");
            String passw = data.getStringExtra("passw");
            session.createLoginSession(email, passw);
            if (requestCode == 1) {   // nhận request khi click vào đăng nhập
                // get then usser theo ham hoi nay
                dbUserServer.Login(email,passw);

            }
        }
    }

    @Override
    public void getUser(ArrayList<User> listUser) {

        // Truyeenf user cho then current Profile
        ProfileActivity.userCurrent=listUser.get(0);

    }

    //Extending FragmentStatePagerAdapter
    public class Pager extends FragmentStatePagerAdapter {
        //Constructor to the class
        public Pager(FragmentManager fm, int tabCount) {
            super(fm);
        }
            //Trả về fragment theo vị trí
        //Overriding method getItem
        @Override
        public Fragment getItem(int position) {
            //Trả về các tab hiejn tại
            switch (position) {
                case 0:
                    PlaceFragment tab1 = new PlaceFragment();
                    return tab1;

                case 1:
                    FoodFragment tab2 = new FoodFragment();
                    return tab2;
                default:
                    return null;
            }
        }

        //Overriden method getCount to get the number of tabs
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Ở đâu";
                default:
                    return "Ăn gì";
            }
        }
    }
    BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

}
