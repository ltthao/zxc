package com.example.lttha.a14110180_lethithao_foody.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.lttha.a14110180_lethithao_foody.Adapter.ItemPlaceAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ListViewAdapter;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemPlacesServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.Model.CategoryModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemPlaces;

import java.util.ArrayList;

/**
 * Created by lttha on 3/30/2017.
 */

public class Category extends Fragment implements DBItemPlacesServer.getItemPlaceListener {
    DataBase db;
    //Khai báo các mảng
    ArrayList<CategoryModel> array;

    DBItemPlacesServer dbServer;
    PlaceFragment x =new PlaceFragment();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.category_layout, container, false);
        Button btnHuy=(Button) v.findViewById(R.id.btnCancel);
        db= new DataBase(getContext());
        db.openDataBase();
        array= new ArrayList<>();
        array=db.getCategory();

        dbServer = new DBItemPlacesServer(this);
        // Locate the ListView in fragmenttab1.xml
        ListView list = (ListView) v.findViewById(R.id.lvPB);

        // Chuyển kết quả sang adapter
        ListViewAdapter adapter = new ListViewAdapter(getActivity(),array);
        // Liên kết adapter với listview
        list.setAdapter(adapter);
        // Capture clicks on ListView items



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListViewAdapter.vt=position;

                //Thay đổi text của button theo vtri chon
                x.setChangeButton(array.get(position).getName(),1);
                //Thay đổi màu của btn
                x.setBackgroundButton(getResources().getColor(R.color.white));

                x.currentID_Category = position+1;
                Log.e("Response","lay duoc "+x.currentID_Category+""+x.currentID_Type+""+x.currentID_District+""+x.currentID_City);
                dbServer.getList_ItemPlaces(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City,x.currenntId_Street);
                //ẩn fragment 1. Hiện fragment main,tabhost khi click vào item
                x.fr1.setVisibility(View.GONE);
                x.frMain.setVisibility(View.VISIBLE);
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);

            }

        });
        //Khi click vào button hủy thì fr1 sẽ ẩn frmain sẽ hiện
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaceFragment pm=new PlaceFragment();
                pm.fr1.setVisibility(View.GONE);
                pm.frMain.setVisibility(View.VISIBLE);
                pm.setBackgroundButton(getResources().getColor(R.color.white));
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);
            }
        });

        return v;
    }


    @Override
    public void getItemPlaces(ArrayList<ItemPlaces> itemPlaces) {
        x.pAdapter = new ItemPlaceAdapter(getActivity(),itemPlaces);
        x.lv.setAdapter(x.pAdapter);
    }
}
