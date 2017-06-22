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
 * Created by lttha on 5/7/2017.
 */

public class AddLocationAdapter extends BaseAdapter {
    public static int vt=-1;
    Context context;
    ArrayList<CityModel> array;
    LayoutInflater inflater;

    public AddLocationAdapter(Context context, ArrayList<CityModel> array) {
        this.context = context;
        this.array = array;
    }
    //Trả về số phần tử trong mảng City
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


        // Locate the TextViews in listview_item.xml
        txtf = (TextView) view.findViewById(R.id.txtCityName);
        imgSelected=(ImageView) view.findViewById(R.id.imgS);
        imgSelected.setVisibility(View.GONE);

        txtf.setText(array.get(i).getName());
        //mặc định cho chọ tphcm
        if(array.get(i).getId()==1)
        {
            vt=i;
//            imgSelected.setVisibility(View.VISIBLE);
//            txtf.setTextColor(view.getResources().getColor(R.color.colorFoody));
        }
        //nếu click vào vtri khác thì tphcm sẽ bỏ tick và đổi màu chữ
        if(vt>0)
        {
            imgSelected.setVisibility(View.INVISIBLE);
            txtf.setTextColor(view.getResources().getColor(R.color.black));
        }
        //Khi click vào item thì hiện imgSelected và đổi màu textview
        if(vt==i) {
            imgSelected.setVisibility(View.VISIBLE);
            txtf.setTextColor(view.getResources().getColor(R.color.colorFoody));

        }
        return view;
    }
}
