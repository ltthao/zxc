package com.example.lttha.a14110180_lethithao_foody.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.lttha.a14110180_lethithao_foody.Adapter.GridMenuAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ItemFoodAdapter;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemFoodsServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.Model.ItemModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemFoods;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lttha on 3/30/2017.
 */

public class FoodFragment extends Fragment implements DBItemFoodsServer.getItemFoodListener {
    DataBase db;
    public static Button btnMN,btnDM,btnT;
    public static FrameLayout fr1, fr2,fr3, frMain;
    ArrayList<ItemModel> arrayTtem=new ArrayList<>();
    public static int currentID_Category=1, currentID_Type=-1,currentID_City=1, currentID_District=-1, district_pos = -1;
    ViewPager mViewPager;
    public static ItemFoodAdapter pAdapter;
    CustomPagerAdapter mCustomPagerAdapter;
    Timer timer;
    public static RecyclerView gvItem;

    DBItemFoodsServer dbServer;

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
        View view = inflater.inflate(R.layout.activity_food, container, false);

        btnMN=(Button) view.findViewById(R.id.btnMN);
        btnMN.setText("Mới nhất");
        btnDM=(Button) view.findViewById(R.id.btnDM);
        btnDM.setText("Danh mục");
        btnT=(Button) view.findViewById(R.id.btnT);
        btnT.setText("TPHCM");
        fr1=(FrameLayout) view.findViewById(R.id.outputMN);
        fr2=(FrameLayout) view.findViewById(R.id.outputDM);
        fr3=(FrameLayout) view.findViewById(R.id.outputT);
        frMain=(FrameLayout) view.findViewById(R.id.outputMainFood);

        fr1.setVisibility(View.GONE);
        fr3.setVisibility(View.GONE);
        fr2.setVisibility(View.GONE);
        btnMN.setTextColor(getResources().getColor(R.color.colorFoody));

        dbServer= new DBItemFoodsServer(this);


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
                    MainActivity.tabhost.getTabWidget().setVisibility(View.GONE);
                    // ẩn 2 frame 2,3 khi click vào btnMN đồng thười cho hiện frame1
                    frMain.setVisibility(View.GONE);
                    fr3.setVisibility(View.GONE);
                    fr2.setVisibility(View.GONE);
                    fr1.setVisibility(View.VISIBLE);
                    btnMN.setBackgroundColor(getResources().getColor(R.color.backg));
                    //btnMN.setText(td);
                    CategoryFood pb = new CategoryFood();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.outputMN, pb);
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
                    MainActivity.tabhost.getTabWidget().setVisibility(View.GONE);
                    // ẩn 2 frame 1,3 khi click vào btnDM đồng thười cho hiện frame2
                    frMain.setVisibility(View.GONE);
                    fr1.setVisibility(View.GONE);
                    fr3.setVisibility(View.GONE);
                    fr2.setVisibility(View.VISIBLE);

                    btnDM.setBackgroundColor(getResources().getColor(R.color.backg));

                    TypeFood dm = new TypeFood();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.outputDM, dm);
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
                    MainActivity.tabhost.getTabWidget().setVisibility(View.GONE);
                    // ẩn 2 frame 2,1 khi click vào btnMN đồng thười cho hiện frame3
                    frMain.setVisibility(View.GONE);
                    fr1.setVisibility(View.GONE);
                    fr2.setVisibility(View.GONE);
                    fr3.setVisibility(View.VISIBLE);

                    btnT.setBackgroundColor(getResources().getColor(R.color.backg));
                    CityFood tinh = new CityFood();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.outputT, tinh);
                    transaction.commit();
                }

            }
        });
        final int[] mResources = {
                R.drawable.page2,
                R.drawable.page3

        };
        mCustomPagerAdapter = new CustomPagerAdapter(getActivity(), mResources);
        mViewPager = (ViewPager) view.findViewById(R.id.pagerF);
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
//        arrayTtem = db.getListItem2(currentID_Category,currentID_Type,currentID_District,currentID_City);

         gvItem=(RecyclerView) view.findViewById(R.id.gvFoodItem);
        RecyclerView gv = (RecyclerView) view.findViewById(R.id.gridFood);

        gvItem.setHasFixedSize(true);
        gv.setHasFixedSize(true);

        dbServer.getList_ItemFoods(currentID_Category,currentID_Type,currentID_District,currentID_City);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        gvItem.setLayoutManager(layoutManager);

        //set layout manager and adapter for "GridView"
        GridLayoutManager layoutManager2 = new GridLayoutManager(getActivity(), 2);
        gv.setLayoutManager(layoutManager2);
        GridMenuAdapter adapter2 = new GridMenuAdapter(getActivity(), names, imgs);
        gv.setAdapter(adapter2);

        return view;
    }
    //set color của button
    public void setBackgroundButton(int color)
    {
        btnMN.setBackgroundColor(color);
        btnDM.setBackgroundColor(color);
        btnT.setBackgroundColor(color);
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


    @Override
    public void getItemFoods(ArrayList<ItemFoods> listItemFoods) {
        pAdapter = new ItemFoodAdapter(getActivity(),listItemFoods);
        gvItem.setAdapter(pAdapter);

    }
}
