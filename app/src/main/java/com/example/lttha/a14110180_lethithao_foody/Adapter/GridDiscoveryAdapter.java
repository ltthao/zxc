package com.example.lttha.a14110180_lethithao_foody.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.R;

/**
 * Created by lttha on 4/16/2017.
 */

public class GridDiscoveryAdapter extends BaseAdapter {
    private Context context;
    private final String[] names;
    private final int[] imgs;

    public GridDiscoveryAdapter( Context context, String[] names,int[] imgs) {
        this.context = context;
        this.names=names;
        this.imgs=imgs;
    }


    @Override
    public int getCount() {
        return names.length;
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
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //trỏ tới grid_discovery_item.xml và các thuộc tính trong đó
        grid = inflater.inflate(R.layout.grid_discovery_item, null);
        TextView textView = (TextView) grid.findViewById(R.id.txtDicovery);
        ImageView imageView = (ImageView)grid.findViewById(R.id.imgDicovery);
        //set text và img theo position
        textView.setText(names[i]);
        imageView.setImageResource(imgs[i]);

        return grid;
    }
}
