package com.example.lttha.a14110180_lethithao_foody.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.lttha.a14110180_lethithao_foody.Adapter.CategoryFoodAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ItemFoodAdapter;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemFoodsServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.Model.CategoryModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemFoods;

import java.util.ArrayList;

/**
 * Created by lttha on 4/19/2017.
 */

public class CategoryFood extends Fragment implements DBItemFoodsServer.getItemFoodListener {
    DataBase db;

    //Khai báo các mảng
    ArrayList<CategoryModel> array;

    DBItemFoodsServer dbServer;
    FoodFragment x =new FoodFragment();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.category_layout, container, false);
        Button btnHuy = (Button) v.findViewById(R.id.btnCancel);
        db = new DataBase(getContext());
        db.openDataBase();
        array= new ArrayList<>();
        array = db.getCategoryFood();

        dbServer = new DBItemFoodsServer(this);
        // Locate the ListView in fragmenttab1.xml
        ListView list = (ListView) v.findViewById(R.id.lvPB);

        // Pass results to ListViewAdapter Class
        CategoryFoodAdapter adapter = new CategoryFoodAdapter(getActivity(), array);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        // Capture clicks on ListView items

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                CategoryFoodAdapter.vt=position;
                //Thay đổi text của button theo vtri chon
                x.setChangeButton(array.get(position).getName(),1);
                // Khi click vào button thì ẩn fr1 và hiên frmain
                x.currentID_Category = position+1;
                dbServer.getList_ItemFoods(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City);
//                x.arrayTtem = db.getListItem2(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City);
//                ItemFoodAdapter adapter = new ItemFoodAdapter(getActivity(), x.arrayTtem);
//                x.gvItem.setAdapter(adapter);

                x.fr1.setVisibility(View.GONE);
                x.frMain.setVisibility(View.VISIBLE);
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);

            }

        });
        //Khi click vào button hủy thì fr1 sẽ ẩn frmain sẽ hiện
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodFragment pmF=new FoodFragment();

                pmF.fr1.setVisibility(View.GONE);
                pmF.frMain.setVisibility(View.VISIBLE);
                pmF.setBackgroundButton(getResources().getColor(R.color.white));
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);
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
