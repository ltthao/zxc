package com.example.lttha.a14110180_lethithao_foody.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.Adapter.ItemPlaceAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ListTypeAdapter;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemPlacesServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.Model.TypeModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemPlaces;

import java.util.ArrayList;

/**
 * Created by lttha on 3/30/2017.
 */

public class Type extends Fragment implements DBItemPlacesServer.getItemPlaceListener {
    DataBase db;
    ArrayList<TypeModel> array= new ArrayList<>();
    public static ImageView imgHeader;
    public static TextView txtHeader;
    DBItemPlacesServer dbServer;
    PlaceFragment x =new PlaceFragment();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.type_layout, container, false);
        Button btnHuy=(Button) v.findViewById(R.id.btnCancel);
        db= new DataBase(getContext());
        db.openDataBase();
        array=db.getType();

        dbServer = new DBItemPlacesServer(this);

        // Locate the ListView in activity_danh_muc.xml
        ListView list = (ListView) v.findViewById(R.id.lvDM);
        View vhead=inflater.inflate(R.layout.header_list_type,null);
            list.addHeaderView(vhead);
        imgHeader=(ImageView) v.findViewById(R.id.imgHeaderType);
        txtHeader=(TextView) v.findViewById(R.id.headerTitle);
        // khi click vào txtHeader
        txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListTypeAdapter.isHeader=true;
                x.setChangeButton("Danh mục",2);
                txtHeader.setTextColor(getResources().getColor(R.color.colorFoody));
                x.fr2.setVisibility(View.GONE);
                x.frMain.setVisibility(View.VISIBLE);
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);
            }
        });

        // Pass results to ListViewAdapter Class
        ListTypeAdapter adapter = new ListTypeAdapter(getActivity(),array);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        // Capture clicks on ListView items

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListTypeAdapter.isHeader=false;
                ListTypeAdapter.vt=position-1;
                //Thay đổi text của button theo vtri chon
                x.setChangeButton(array.get(position-1).getName(),2);
                x.setBackgroundButton(getResources().getColor(R.color.white));
                x.btnDM.setTextColor(getResources().getColor(R.color.colorFoody));
                TypeModel typeModel = (TypeModel)parent.getAdapter().getItem(position);
                x.currentID_Type = typeModel.getId();
//                Log.d("where_city:",typeModel.getId()+"'");
//                x.arrayTtem = db.getListItemPlaces(x.currentID_City,1,x.currentID_Category,1);
//                x.pAdapter = new ItemPlaceAdapter(getActivity(), x.arrayTtem);
//                x.lv.setAdapter(x.pAdapter);

                dbServer.getList_ItemPlaces(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City,x.currenntId_Street);
                x.fr2.setVisibility(View.GONE);
                x.frMain.setVisibility(View.VISIBLE);
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);

                //Thay đổi text của button theo vtri chon và get id theo vị trí
                //x.changeDanhMuc(array.get(position).getId());

            }

        });
        //Khi click vào button hủy
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaceFragment pm=new PlaceFragment();
                pm.fr2.setVisibility(View.GONE);
                pm.frMain.setVisibility(View.VISIBLE);
                pm.setBackgroundButton(getResources().getColor(R.color.white));
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);
            }
        });
        return v;
    }
    //hàm hiển thị tick và đổi màu
    public static void tickVisible(int hien, int color)
    {
        imgHeader.setVisibility(hien);
        txtHeader.setTextColor(color);

    }

    @Override
    public void getItemPlaces(ArrayList<ItemPlaces> itemPlaces) {
        x.pAdapter = new ItemPlaceAdapter(getActivity(),itemPlaces);
        x.lv.setAdapter(x.pAdapter);
    }
}
