package com.example.lttha.a14110180_lethithao_foody.Adapter;

/**
 * Created by lttha on 3/23/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.Model.CategoryModel;
import com.example.lttha.a14110180_lethithao_foody.Model.StreetModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StreetAdapter extends BaseAdapter {
    public static int vt=-1;//chua click vao listview
    // Declare Variables
    Context context;

    ArrayList<StreetModel> array;
    LayoutInflater inflater;

    public StreetAdapter(Context context, ArrayList<StreetModel> array) {
        this.context = context;
        this.array = array;
    }


    public int getCount() {
        return array.size();
    }

    public Object getItem(int position) {
        return array.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView txtf;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.list_street, parent, false);


        // Locate the TextViews in listview_item.xml
        txtf = (TextView) convertView.findViewById(R.id.txtStreet);

        txtf.setText(array.get(position).getName());

        return convertView;
    }
}
