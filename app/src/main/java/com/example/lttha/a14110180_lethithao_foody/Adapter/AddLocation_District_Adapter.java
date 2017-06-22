package com.example.lttha.a14110180_lethithao_foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.Model.CityModel;
import com.example.lttha.a14110180_lethithao_foody.Model.DistrictModel;
import com.example.lttha.a14110180_lethithao_foody.R;

import java.util.ArrayList;

/**
 * Created by lttha on 5/7/2017.
 */

public class AddLocation_District_Adapter extends BaseAdapter {
    public static int vt=-1;
    Context context;
    ArrayList<DistrictModel> array;
    LayoutInflater inflater;

    public AddLocation_District_Adapter(Context context, ArrayList<DistrictModel> array) {
        this.context = context;
        this.array = array;
    }
    //Trả về size cỉa mảng District
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


        // Tham chiesu ts cách id trong trang xml
        txtf = (TextView) view.findViewById(R.id.txtCityName);
        imgSelected=(ImageView) view.findViewById(R.id.imgS);
        imgSelected.setVisibility(View.GONE);
        // Get name của từng item theo vị trí
        txtf.setText(array.get(i).getName());
        //Khi click vô item thì sẽ hiển thị imgSelected và đổi màu textview
        if(vt==i) {
            imgSelected.setVisibility(View.VISIBLE);
            txtf.setTextColor(view.getResources().getColor(R.color.colorFoody));

        }
        return view;
    }
}
