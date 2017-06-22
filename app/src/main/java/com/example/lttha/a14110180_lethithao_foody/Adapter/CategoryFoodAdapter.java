package com.example.lttha.a14110180_lethithao_foody.Adapter;

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

/**
 * Created by lttha on 4/19/2017.
 */

public class CategoryFoodAdapter extends BaseAdapter {
    public static int vt=-1;//chua click vao listview
    // Declare Variables
    Context context;
    ArrayList<CategoryModel> array;
    LayoutInflater inflater;

    public CategoryFoodAdapter(Context context, ArrayList<CategoryModel> array) {
        this.context = context;
        this.array = array;
    }
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
    public View getView( final int position, View view, ViewGroup viewGroup) {
        // Declare Variables
        TextView txtf;
        ImageView imgf,imgSelected;

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

        }
        return itemView;
    }
}
