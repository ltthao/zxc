package com.example.lttha.a14110180_lethithao_foody.Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.Adapter.ItemPlaceAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ListCityAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ListDistrictAdapter;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemPlacesServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.Model.CityModel;
import com.example.lttha.a14110180_lethithao_foody.Model.DistrictModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemPlaces;

import java.util.ArrayList;

/**
 * Created by lttha on 3/30/2017.
 */

public class City extends Fragment implements DBItemPlacesServer.getItemPlaceListener{
    ListView lv;
    ArrayList<DistrictModel> list=new ArrayList<>();
    ArrayList<CityModel> listCity= new ArrayList<>();
    Button btnDoiTinh, btnHuy;
    private static TextView txtT;
    public static int idCity=1;
    public static ListCityAdapter cityAdapter;
    DataBase db;
    ListDistrictAdapter ad;

    DBItemPlacesServer dbServer;
    PlaceFragment x =new PlaceFragment();

    @Override
    public void onResume() {
        list=db.getListDistrict(idCity);
        ad=new ListDistrictAdapter(getContext(),list);
        lv.setAdapter(ad);
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.city_layout,container,false);
        lv=(ListView)v.findViewById(R.id.lvT);
        btnDoiTinh=(Button) v.findViewById(R.id.btnT);
        txtT=(TextView) v.findViewById(R.id.txtT);
        db=new DataBase(getActivity());
        db.openDataBase();

        dbServer = new DBItemPlacesServer(this);

        cityAdapter=new ListCityAdapter(getContext(),listCity);
        list=db.getListDistrict(idCity);

        ad=new ListDistrictAdapter(v.getContext(),list);
        lv.setAdapter(ad);
        btnHuy=(Button) v.findViewById(R.id.btnCancel);
        final PlaceFragment x=new PlaceFragment();
        //Xử ly sự kiện khi click vào từng item của listDistrict
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ListCityAdapter.vt=position;
                x.setChangeButton(list.get(position).getName(),3); // số 3 là gì đấy
                DistrictModel districtModel = (DistrictModel)adapterView.getAdapter().getItem(position);
                x.currentID_District = districtModel.getId();
                x.currenntId_Street =-1;
                dbServer.getList_ItemPlaces(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City,x.currenntId_Street);
                // Khi click vào button thì ẩn fr1 và hiên frmain

                x.fr3.setVisibility(View.GONE);
                x.frMain.setVisibility(View.VISIBLE);
                //Hiện tabwidget khi click item.
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);
            }
        });
        //Khi click vào button hủy thì fr3 sẽ ẩn frmain sẽ hiện
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlaceFragment pm=new PlaceFragment();
                pm.fr3.setVisibility(View.GONE);
                pm.frMain.setVisibility(View.VISIBLE);
                pm.setBackgroundButton(getResources().getColor(R.color.white));
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);
            }
        });

        btnDoiTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iChangeCity = new Intent(getContext(), ChangeCityActivity.class);
                getContext().startActivity(iChangeCity);
            }
        });

        return v;
    }
    public static void changeText(String text)
    {
        txtT.setText(text);
    }


    @Override
    public void getItemPlaces(ArrayList<ItemPlaces> itemPlaces) {
        x.pAdapter = new ItemPlaceAdapter(getActivity(),itemPlaces);
        x.lv.setAdapter(x.pAdapter);
    }
}
