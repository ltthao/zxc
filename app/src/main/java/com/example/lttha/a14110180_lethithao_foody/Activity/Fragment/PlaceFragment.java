package com.example.lttha.a14110180_lethithao_foody.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.Adapter.GridMenuAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ItemPlaceAdapter;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemPlacesServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemPlaces;
import com.example.lttha.a14110180_lethithao_foody.Utils.Service;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.lttha.a14110180_lethithao_foody.MainActivity.tabhost;

/**
 * Created by lttha on 3/30/2017.
 */

public class PlaceFragment extends Fragment implements DBItemPlacesServer.getItemPlaceListener{
    DataBase db;
    private Service mService;
    public static Button btnMN, btnDM, btnT;
    public static FrameLayout fr1, fr2, fr3, frMain;
    public static int currentID_Category=1, currentID_Type=-1,currentID_City=1, currentID_District=-1, currenntId_Street=-1;
    ArrayList<ItemPlaces> arrayTtem;
    public static ItemPlaceAdapter pAdapter;
    public static RecyclerView lv, gv;
    public static TextView CurrentCity_Name;

    ViewPager mViewPager;
    CustomPagerAdapter mCustomPagerAdapter;
    Timer timer;

    public static DBItemPlacesServer dbServer;

    String[] names = new String[]{
            "Gần tôi",
            "Đặt chỗ ưu đãi",
            "E-Card",
            "Bình luận",
            "Top thành viên",
            "Coupon",
            "Đặt giao hàng",
            "Game & Fun",
            "Blogs",
            "Video"
    };

    int[] imgs = new int[]{
            R.drawable.ic_nearby,
            R.drawable.ic_sttnotification_tablenow,
            R.drawable.ic_ecard,
            R.drawable.ic_icon_binhluanmoi,
            R.drawable.ic_icon_topthanhvien,
            R.drawable.ic_ecoupon,
            R.drawable.ic_sttnotification_deli,
            R.drawable.ic_game,
            R.drawable.ic_icon_chuyende,
            R.drawable.ic_video
    };

    @Override
    public void onResume() {
        super.onResume();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Gắn id vào các thuộc tính khai báo
        View view = inflater.inflate(R.layout.activity_place, container, false);

        btnMN = (Button) view.findViewById(R.id.btn1);
        btnMN.setText("Mới nhất");
        btnDM = (Button) view.findViewById(R.id.btn2);
        btnDM.setText("Danh mục");
        btnT = (Button) view.findViewById(R.id.btn3);
        btnT.setText("TPHCM");
        fr1 = (FrameLayout) view.findViewById(R.id.output1);
        fr2 = (FrameLayout) view.findViewById(R.id.output2);
        fr3 = (FrameLayout) view.findViewById(R.id.output3);
        frMain = (FrameLayout) view.findViewById(R.id.outputMain);
        btnMN.setTextColor(getResources().getColor(R.color.colorFoody));


        dbServer = new DBItemPlacesServer(this);

        CurrentCity_Name = (TextView)view.findViewById(R.id.txtT);  // cái form gọi startActivity dau

        //Ban đầu cho ẩn 3 frame của 3 button và hiện frame main
        fr1.setVisibility(View.GONE);
        fr3.setVisibility(View.GONE);
        fr2.setVisibility(View.GONE);


        // Khi click vào button btnMN thì add vào Fragment
        btnMN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setBackgroundButton(getResources().getColor(R.color.white));
                btnMN.setTextColor(getResources().getColor(R.color.colorFoody));
                if (fr1.isShown() == true) {
                    fr1.setVisibility(View.GONE);
                    frMain.setVisibility(View.VISIBLE);

                } else {
                    //Ẩn tabWidget khi click vào
                    tabhost.getTabWidget().setVisibility(View.INVISIBLE);


                    MainActivity.setStateTab(View.GONE);
                    // ẩn 2 frame 2,3 khi click vào btnMN đồng thười cho hiện frame1
                    frMain.setVisibility(View.GONE);
                    fr3.setVisibility(View.GONE);
                    fr2.setVisibility(View.GONE);
                    fr1.setVisibility(View.VISIBLE);
                    btnMN.setBackgroundColor(getResources().getColor(R.color.backg));
                    //btnMN.setText(td);
                    Category pb = new Category();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.output1, pb);
                    transaction.commit();


                }

            }
        });
        //Khi click vào button Danh mục
        btnDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBackgroundButton(getResources().getColor(R.color.white));
                if (fr2.isShown()) {
                    fr2.setVisibility(View.GONE);
                    frMain.setVisibility(View.VISIBLE);

                } else {

                    //Ẩn tabWidget khi click vào
                    tabhost.getTabWidget().setVisibility(View.GONE);
                    // ẩn 2 frame 1,3 khi click vào btnDM đồng thười cho hiện frame2
                    frMain.setVisibility(View.GONE);
                    fr1.setVisibility(View.GONE);
                    fr3.setVisibility(View.GONE);
                    fr2.setVisibility(View.VISIBLE);

                    btnDM.setBackgroundColor(getResources().getColor(R.color.backg));

                    Type dm = new Type();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.output2, dm);
                    //      transaction.addToBackStack(null);
                    transaction.commit();
                }


            }
        });
        //Khi click vào button TP.HCM
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBackgroundButton(getResources().getColor(R.color.white));
                if (fr3.isShown()) {
                    fr3.setVisibility(View.GONE);
                    frMain.setVisibility(View.VISIBLE);

                } else {

                    //Ẩn tabWidget khi click vào
                    tabhost.getTabWidget().setVisibility(View.GONE);
                    // ẩn 2 frame 2,1 khi click vào btnMN đồng thười cho hiện frame3
                    frMain.setVisibility(View.GONE);
                    fr1.setVisibility(View.GONE);
                    fr2.setVisibility(View.GONE);
                    fr3.setVisibility(View.VISIBLE);

                    btnT.setBackgroundColor(getResources().getColor(R.color.backg));
                    City tinh = new City();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.output3, tinh);
                    transaction.commit();
                }

            }
        });
        final int[] mResources = {
                R.drawable.page2,
                R.drawable.page3

        };
        mCustomPagerAdapter = new CustomPagerAdapter(getActivity(), mResources);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        //Xác định thời gian chạy slide ảnh
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mViewPager.post(new Runnable() {

                    @Override
                    public void run() {
                        mViewPager.setCurrentItem((mViewPager.getCurrentItem() + 1) % mResources.length);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);

        db = new DataBase(getActivity());
        db.openDataBase();


        arrayTtem = new ArrayList<>();

        lv = (RecyclerView) view.findViewById(R.id.lvPlace);
        gv = (RecyclerView) view.findViewById(R.id.gridPlace);

        lv.setHasFixedSize(true);
        gv.setHasFixedSize(true);

        dbServer.getList_ItemPlaces(currentID_Category,currentID_Type,currentID_District,currentID_City,currenntId_Street);
        LinearLayoutManager horizontalManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        lv.setLayoutManager(horizontalManager);

        //set layout manager and adapter for "GridView"
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        gv.setLayoutManager(layoutManager);
        GridMenuAdapter adapter = new GridMenuAdapter(getActivity(), names, imgs);
        gv.setAdapter(adapter);


        return view;
    }




    //Xét text của button
    public void setChangeButton(String text, int vt) {
        if (vt == 1) {
            btnMN.setText(text);

        }
        if (vt == 2) {
            btnDM.setText(text);

        }
        if (vt == 3) {
            btnT.setText(text);

        }


    }
    //Xét màu của các button khi click
    public void setBackgroundButton(int color) {
        btnMN.setBackgroundColor(color);
        btnDM.setBackgroundColor(color);
        btnT.setBackgroundColor(color);

    }


    @Override
    public void getItemPlaces(ArrayList<ItemPlaces> itemPlaces) {
        pAdapter = new ItemPlaceAdapter(getActivity(),itemPlaces);
        lv.setAdapter(pAdapter);
    }
}
