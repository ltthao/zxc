package com.example.lttha.a14110180_lethithao_foody.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.Model.ItemModel;
import com.example.lttha.a14110180_lethithao_foody.Model.ReviewModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemFoods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lttha on 4/10/2017.
 */

public class ItemFoodAdapter extends RecyclerView.Adapter<ItemFoodAdapter.ViewHolder> {
    DataBase db;
    private Activity activity;
    ArrayList<ItemFoods> items=new ArrayList<>();


    public ItemFoodAdapter(Activity activity, ArrayList<ItemFoods> items) {
        this.activity = activity;
        this.items = items;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtType, txtName,txtAddress,txtNameR,txtDateR;
        ImageView imgItem;
        CircleImageView circleImg;

        public ViewHolder(View itemView) {
            super(itemView);
            txtType=(TextView) itemView.findViewById(R.id.txtTypeItemF);
            txtName=(TextView) itemView.findViewById(R.id.txtNameItemF);
            txtAddress=(TextView) itemView.findViewById(R.id.txtAddressItemF);
            imgItem=(ImageView) itemView.findViewById(R.id.imgFoodItem);
            circleImg=(CircleImageView) itemView.findViewById(R.id.imgAvatarItemF);
            txtNameR=(TextView) itemView.findViewById(R.id.txtNameReviewF);
            txtDateR=(TextView) itemView.findViewById(R.id.txtDateReviewF);
        }
    }

    @Override
    public ItemFoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_food_content, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemFoodAdapter.ViewHolder holder, int position) {

        //Tạo biến để lấy hình ảnh trên server
        //Set hình ảng cho img

        String url = items.get(position).getImg();
        if(url.contains("upload"))
        {
            String urllink ="https://foody-v2.herokuapp.com/getimg?nameimg=fdi";
            Glide.with(activity).load(urllink+items.get(position).getImg()+".png").into(holder.imgItem);
        }
        else
             Glide.with(activity).load(items.get(position).getImg()).into(holder.imgItem);


        Glide.with(activity).load(items.get(position).getUserAvatar()).into(holder.circleImg);
        holder.txtNameR.setText(items.get(position).getUserName());
        //Log.e("imgitemFood",resid+items.get(position).getUserAvatar()+".png");

        holder.txtType.setText(items.get(position).getFoodName());
        holder.txtName.setText(items.get(position).getName());
        holder.txtAddress.setText(items.get(position).getAddress());



    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
