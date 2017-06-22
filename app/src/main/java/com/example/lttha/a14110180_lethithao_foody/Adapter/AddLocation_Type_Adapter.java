package com.example.lttha.a14110180_lethithao_foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.Model.DistrictModel;
import com.example.lttha.a14110180_lethithao_foody.Model.TypeModel;
import com.example.lttha.a14110180_lethithao_foody.R;

import java.util.ArrayList;

/**
 * Created by lttha on 5/7/2017.
 */

public class AddLocation_Type_Adapter extends BaseAdapter {
    public static int vt=-1;
    Context context;
    ArrayList<TypeModel> array;
    LayoutInflater inflater;

    public AddLocation_Type_Adapter(Context context, ArrayList<TypeModel> array) {
        this.context = context;
        this.array = array;
    }
    //Trả về số phần tử trong mảng Type
    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView txtf;
        ImageView imgSelected;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.item_add_location, viewGroup, false);


        txtf = (TextView) view.findViewById(R.id.txtCityName);
        imgSelected=(ImageView) view.findViewById(R.id.imgS);
        //Ẩn imgSelected
        imgSelected.setVisibility(View.GONE);
        //get name theo vị trí
        txtf.setText(array.get(i).getName());
        //Khi click vào item thì hiện imgSelected và đổi màu textview
        if(vt==i) {
            imgSelected.setVisibility(View.VISIBLE);
             txtf.setTextColor(view.getResources().getColor(R.color.colorFoody));

        }
        return view;
    }
}
