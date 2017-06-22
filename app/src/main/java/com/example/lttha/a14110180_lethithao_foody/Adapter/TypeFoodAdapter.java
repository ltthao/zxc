package com.example.lttha.a14110180_lethithao_foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.Activity.Fragment.TypeFood;
import com.example.lttha.a14110180_lethithao_foody.Model.TypeModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lttha on 4/19/2017.
 */

public class TypeFoodAdapter extends BaseAdapter {
    public static int vt=-1;//chua click vao lítview
    // Declare Variables
    Context context;
    ArrayList<TypeModel> array;
    LayoutInflater inflater;
    public static boolean isHeader=true;

    public TypeFoodAdapter(Context context, ArrayList<TypeModel> array) {
        this.context = context;
        this.array = array;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return array.get(i);
    }

    @Override
    public long getItemId(int i) {
        return array.get(i).getId();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        TextView txtf;
        final ImageView imgf, imgSelected;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_item, viewGroup, false);


        // Locate the TextViews in listview_item.xml
        txtf = (TextView) itemView.findViewById(R.id.textView);
        imgf = (ImageView) itemView.findViewById(R.id.imageView);
        imgSelected=(ImageView) itemView.findViewById(R.id.imgSelected);
        imgSelected.setVisibility(View.GONE);

        Button btnNew=(Button) itemView.findViewById(R.id.btnNew);
        btnNew.setVisibility(View.GONE);

        // Capture position and set to the TextViews
        txtf.setText(array.get(position).getName());
        //Log.e("img",array.get(position).getImg());
        try {
            int resid = context.getResources().getIdentifier("fd" + array.get(position).getImg(), "drawable", context.getPackageName());
            Picasso.with(context).load(resid).fit().centerInside().into(imgf);
        }
        catch (OutOfMemoryError e){
            e.printStackTrace();
        }
        if(vt==position &&!isHeader) {
            //Thay đổi màu của textview khi click
            txtf.setTextColor(itemView.getResources().getColor(R.color.colorPrimary));
            //Hiện dấu tick khi click
            imgSelected.setVisibility(View.VISIBLE);
            //Thay ddooir img khi click

        }
        if(vt>=0 &&!isHeader)
        {
            //ẩn icon tick và đổi màu txtHeader của listType
            TypeFood.tickVisible(View.INVISIBLE,itemView.getResources().getColor(R.color.black));
        }
        else
        {
            //Hiện icon tick và đổi màu txtHeader của listType
            TypeFood.tickVisible(View.VISIBLE,itemView.getResources().getColor(R.color.colorPrimary));
        }


        return itemView;
    }
}
