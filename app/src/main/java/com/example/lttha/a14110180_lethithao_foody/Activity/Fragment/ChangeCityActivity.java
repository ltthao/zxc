package com.example.lttha.a14110180_lethithao_foody.Activity.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lttha.a14110180_lethithao_foody.Adapter.ItemPlaceAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ListCityAdapter;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemPlacesServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.Model.CityModel;
import com.example.lttha.a14110180_lethithao_foody.Model.DistrictModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemPlaces;

import java.util.ArrayList;

public class ChangeCityActivity extends AppCompatActivity implements DBItemPlacesServer.getItemPlaceListener{
    DataBase db;
    ArrayList<CityModel> array;
    ArrayList<DistrictModel> list=new ArrayList<>();
    private  int idCity=1;
    DBItemPlacesServer dbItemPlacesServer;
     PlaceFragment x=new PlaceFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_city);
        //Tham chiếu toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_change_city);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setDisplayHomeAsUpEnabled(true);
        }

        final ListView lvChangeCity = (ListView) findViewById(R.id.lv_city);
        EditText edt=(EditText) findViewById(R.id.editText);

        //Khởi tạo database
        db= new DataBase(getBaseContext());
        db.openDataBase();
        dbItemPlacesServer = new DBItemPlacesServer(this);
        array= new ArrayList<>();
        //getListCity
        array=db.getListCity();
        ListCityAdapter adapter = new ListCityAdapter(this,array);
        lvChangeCity.setAdapter(adapter);

        //Khi click vào item của city
        lvChangeCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                ListCityAdapter.vt=position;
                idCity=position;
                x.setChangeButton(array.get(position).getName(),3);
            }
        });

        //khi clcik vào btn Xong thì đóng acticicty ChangeCity
        Button btnXong=(Button) findViewById(R.id.btnXong);
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeCityActivity.this.finish();
                City.idCity=idCity+1;
                City.changeText(array.get(idCity).getName());
                x.currentID_City = array.get(idCity).getId();
                // khi click vào city thì set cho id của district vs street =-1
                x.currenntId_Street = -1;
                x.currentID_District = -1;
                dbItemPlacesServer.getList_ItemPlaces(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City,x.currenntId_Street);

            }
        });

    }


    @Override
    public void getItemPlaces(ArrayList<ItemPlaces> itemPlaces) {
        x.pAdapter = new ItemPlaceAdapter(ChangeCityActivity.this,itemPlaces);
        x.lv.setAdapter(x.pAdapter);
    }
}
