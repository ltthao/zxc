package com.example.lttha.a14110180_lethithao_foody.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.Adapter.ItemFoodAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ListTypeAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.TypeFoodAdapter;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemFoodsServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.Model.TypeModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemFoods;

import java.util.ArrayList;

/**
 * Created by lttha on 4/19/2017.
 */

public class TypeFood extends Fragment implements DBItemFoodsServer.getItemFoodListener {
    DataBase db;
    ArrayList<TypeModel> array= new ArrayList<>();
    public static ImageView imgHeader;
    public static TextView txtHeader;

    DBItemFoodsServer dbServer;
    FoodFragment x =new FoodFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.type_layout, container, false);
        Button btnHuy=(Button) v.findViewById(R.id.btnCancel);
        db= new DataBase(getContext());
        db.openDataBase();
        array=db.getType();

        dbServer = new DBItemFoodsServer(this);

        // Locate the ListView in activity_danh_muc.xml
        ListView list = (ListView) v.findViewById(R.id.lvDM);
        View vhead=inflater.inflate(R.layout.header_list_type,null);
        list.addHeaderView(vhead);
        imgHeader=(ImageView) v.findViewById(R.id.imgHeaderType);
        txtHeader=(TextView) v.findViewById(R.id.headerTitle);
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
        TypeFoodAdapter adapter = new TypeFoodAdapter(getActivity(),array);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        // Capture clicks on ListView items

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TypeFoodAdapter.isHeader=false;
                TypeFoodAdapter.vt=position-1;
                TypeModel typeModel = (TypeModel) parent.getItemAtPosition(position);
                Log.e("it_type:::",typeModel.getId()+"'");
                x.currentID_Type = typeModel.getId();
                dbServer.getList_ItemFoods(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City);
//                Log.e("it_type:::",typeModel.getId()+"'");
//                x.arrayTtem = db.getListItem2(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City);
//                ItemFoodAdapter adapter = new ItemFoodAdapter(getActivity(), x.arrayTtem);
//                x.gvItem.setAdapter(adapter);
                x.btnDM.setText(typeModel.getName());
                x.setBackgroundButton(getResources().getColor(R.color.white));
                x.btnDM.setTextColor(getResources().getColor(R.color.colorFoody));
                x.fr2.setVisibility(View.GONE);
                x.frMain.setVisibility(View.VISIBLE);
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);

            }

        });
        //Khi click vào button hủy
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TypeFoodAdapter.isHeader=true;
                FoodFragment pmF=new FoodFragment();
                pmF.fr2.setVisibility(View.GONE);
                pmF.frMain.setVisibility(View.VISIBLE);
                pmF.setBackgroundButton(getResources().getColor(R.color.white));
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
    public void getItemFoods(ArrayList<ItemFoods> listItemFoods) {
        x.pAdapter = new ItemFoodAdapter(getActivity(),listItemFoods);
        x.gvItem.setAdapter(x.pAdapter);
    }
}
