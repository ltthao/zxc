package com.example.lttha.a14110180_lethithao_foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.Model.CityModel;
import com.example.lttha.a14110180_lethithao_foody.R;

import java.util.ArrayList;

/**
 * Created by lttha on 4/6/2017.
 */

public class ListCityAdapter extends BaseAdapter {
    Context context;
    ArrayList<CityModel> cities;
    LayoutInflater inflater;
    TextView txtCity,txtSelected;
    ImageView imgSelected;

    //imgv khi item được select

    public static int vt=-1;

    public ListCityAdapter(Context context, ArrayList<CityModel> cities){
        this.context = context;
        this.cities = cities;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cities.get(position).getId();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Tham chiếu tới file list_city.xml và các phần tử trong nó
        view = inflater.inflate(R.layout.list_city, viewGroup, false);
        imgSelected=(ImageView) view.findViewById(R.id.img_select_City);
        txtCity=(TextView) view.findViewById(R.id.txt_city_name);
        txtSelected=(TextView) view.findViewById(R.id.txt_city_select);
        //ban đầu cho ẩn imgSelect và text mặc định
        imgSelected.setVisibility(View.GONE);
        txtSelected.setVisibility(View.GONE);


        final CityModel city = cities.get(position);
        txtCity.setText(city.getName());

        //Xét màu cho txtCity khi id=1
        if(cities.get(position).getId()==1)
        {
            txtCity.setTextColor(context.getResources().getColor(R.color.color_city_select));
        }

        if(vt == position){
            //Hiện imgSelected và txtSelected
            imgSelected.setVisibility(View.VISIBLE);
            txtSelected.setVisibility(View.VISIBLE);

        }

        return view;
    }
}
