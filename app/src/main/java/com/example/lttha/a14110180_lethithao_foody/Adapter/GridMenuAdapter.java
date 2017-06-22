package com.example.lttha.a14110180_lethithao_foody.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.R;

public class GridMenuAdapter extends RecyclerView.Adapter<GridMenuAdapter.ViewHolder>{
    @Override
    public int getItemCount() {
        return Name.length;
    }

    private Activity activity;
    //Tạo 2 mảng
    private final String[] Name;
    private final int[] Imageid;

    //Khởi tạo hàm dựng CustomGrid
    public GridMenuAdapter(Activity activity, String[] Name, int[] Imageid ) {
        this.activity = activity;
        this.Imageid = Imageid;
        this.Name = Name;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.grid_single, viewGroup, false);

        return new ViewHolder(view);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            //Trỏ tới các text và image trong grid_single.xml
            textView = (TextView) view.findViewById(R.id.grid_text);
            imageView = (ImageView)view.findViewById(R.id.grid_image);

        }
    }


    @Override
    public void onBindViewHolder(GridMenuAdapter.ViewHolder holder, int position) {
        //set image và text theo position
        holder.imageView.setImageResource(Imageid[position]);
        holder.textView.setText(Name[position]);
    }


}