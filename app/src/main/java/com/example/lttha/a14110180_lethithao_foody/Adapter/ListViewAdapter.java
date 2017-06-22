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
import com.example.lttha.a14110180_lethithao_foody.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
//List các hình ảnh và text cửa fragment Danh mục

public class ListViewAdapter extends BaseAdapter {
    public static int vt=-1;//chua click vao listview
    // Declare Variables
    Context context;
    ArrayList<CategoryModel> array;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, ArrayList<CategoryModel> array) {
        this.context = context;
        this.array = array;
    }


    public int getCount() {
        return array.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView txtf;
        ImageView imgf,imgSelected;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_item, parent, false);


        // Locate the TextViews in listview_item.xml
        txtf = (TextView) itemView.findViewById(R.id.textView);
        imgf = (ImageView) itemView.findViewById(R.id.imageView);
        imgSelected=(ImageView) itemView.findViewById(R.id.imgSelected);
        imgSelected.setVisibility(View.GONE);
        Button btnNew=(Button) itemView.findViewById(R.id.btnNew);
        btnNew.setVisibility(View.GONE);
//        if(array.get(position).getIdCategory()==2)
//        {
//            imgf.setImageResource(R.drawable.home_ic_filter_latest_act);
//            txtf.setTextColor(itemView.getResources().getColor(R.color.colorPrimary));
//        }
        if(array.get(position).getIdCategory()==9)
        {
            btnNew.setVisibility(View.VISIBLE);
        }

        // Capture position and set to the TextViews
        txtf.setText(array.get(position).getName());
        try {
            int resid = context.getResources().getIdentifier(array.get(position).getImg(), "drawable", context.getPackageName());
            Picasso.with(context).load(resid).into(imgf);
        }
        catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        }
        if(vt==position) {
            //Thay đổi màu của textview khi click
            txtf.setTextColor(itemView.getResources().getColor(R.color.colorPrimary));
            imgSelected.setVisibility(View.VISIBLE);
            //Thay ddooir img khi click
            int resid2 = context.getResources().getIdentifier(array.get(position).getImgs(), "drawable", context.getPackageName());
            imgf.setImageResource(resid2);
//            //Thay đổi tiêu đề của button khi click

        }

            return itemView;
    }
}
