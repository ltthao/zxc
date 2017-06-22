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

import com.example.lttha.a14110180_lethithao_foody.Adapter.DistrictFoodAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ItemFoodAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ListCityAdapter;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemFoodsServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.Model.CityModel;
import com.example.lttha.a14110180_lethithao_foody.Model.DistrictModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemFoods;

import java.util.ArrayList;

/**
 * Created by lttha on 4/19/2017.
 */

public class CityFood extends Fragment implements DBItemFoodsServer.getItemFoodListener {
    ListView lv;
    ArrayList<DistrictModel> list=new ArrayList<>();
    ArrayList<CityModel> listCity= new ArrayList<>();
    Button btnDoiTinh, btnHuy;
    private TextView txtT;
    DataBase db;
    public static int idCity=1;
    ListCityAdapter cityAdapter;
    DistrictFoodAdapter ad;

    DBItemFoodsServer dbServer;
    FoodFragment x =new FoodFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.city_layout,container,false);
        lv=(ListView)v.findViewById(R.id.lvT);
        btnDoiTinh=(Button) v.findViewById(R.id.btnT);
        txtT=(TextView) v.findViewById(R.id.txtT);
        db=new DataBase(getActivity());
        db.openDataBase();

        dbServer = new DBItemFoodsServer(this);

        cityAdapter=new ListCityAdapter(getContext(),listCity);
        list=db.getListDistrict(idCity);

        ad=new DistrictFoodAdapter(v.getContext(),list);
        lv.setAdapter(ad);
        btnHuy=(Button) v.findViewById(R.id.btnCancel);

        //Xử ly sự kiện khi click vào từng item của listDistrict
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DistrictFoodAdapter.vt=position;
                x.setChangeButton(list.get(position).getName(),3);
                //get du lieu theo item khi click
                DistrictModel districtModel = (DistrictModel)adapterView.getAdapter().getItem(position);
                x.currentID_District = districtModel.getId();
                dbServer.getList_ItemFoods(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City);
//                Log.d("where_city:",districtModel.getId()+"'");
//                x.arrayTtem = db.getListItem2(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City);
//                ItemFoodAdapter adapter = new ItemFoodAdapter(getActivity(), x.arrayTtem);
//                x.gvItem.setAdapter(adapter);
                // Khi click vào button thì ẩn fr1 và hiên frmain
                x.fr3.setVisibility(View.GONE);
                x.frMain.setVisibility(View.VISIBLE);
                //Hiện tabwidget khi click item
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);

            }
        });
        //Khi click vào button hủy thì fr3 sẽ ẩn frmain sẽ hiện
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodFragment pmF=new FoodFragment();

                pmF.fr3.setVisibility(View.GONE);
                pmF.frMain.setVisibility(View.VISIBLE);
                pmF.setBackgroundButton(getResources().getColor(R.color.white));
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

    @Override
    public void getItemFoods(ArrayList<ItemFoods> listItemFoods) {
        x.pAdapter = new ItemFoodAdapter(getActivity(),listItemFoods);
        x.gvItem.setAdapter(x.pAdapter);
    }
}
