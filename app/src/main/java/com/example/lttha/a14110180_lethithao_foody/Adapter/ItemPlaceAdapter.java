package com.example.lttha.a14110180_lethithao_foody.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.Model.ItemModel;
import com.example.lttha.a14110180_lethithao_foody.Model.ReviewModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ApiUtils;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemPlaces;
import com.example.lttha.a14110180_lethithao_foody.Utils.Reviews;
import com.example.lttha.a14110180_lethithao_foody.Utils.Service;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by lttha on 4/3/2017.
 */

public class ItemPlaceAdapter extends RecyclerView.Adapter<ItemPlaceAdapter.ViewHolder>  {
    DataBase db;
    private Service mService;
    ArrayList<ReviewModel> arrayRv;
    private Activity activity;
    ArrayList<ItemPlaces> items = new ArrayList<>();

    public ItemPlaceAdapter(Activity activity, ArrayList<ItemPlaces> items) {
        this.activity = activity;
        this.items = items;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtAvgRating, txtName, txtNameR1, txtNameR2, txtAddress, txtState, txtR1, txtR2, txtRating1, txtRating2;
        ImageView imgItem;
        VideoView videoItem;
        RecyclerView recyclerImg;
        Button btnCountComment, btnCountPicture;
        CircleImageView imgAva1, imgAva2;
        View lnOrderNow;


        public ViewHolder(View v) {
            super(v);
            //Tham chiếu tới các phần tủ trong list_content.xml
            txtName = (TextView) v.findViewById(R.id.txtNameItem);
            txtAvgRating = (TextView) v.findViewById(R.id.txtRating);
            txtAddress = (TextView) v.findViewById(R.id.txtAddressItem);
            txtState = (TextView) v.findViewById(R.id.txtState);
            imgItem = (ImageView) v.findViewById(R.id.imgItem);
            videoItem = (VideoView) v.findViewById(R.id.videoV);
            recyclerImg = (RecyclerView) v.findViewById(R.id.recycler_img_item);
            lnOrderNow = v.findViewById(R.id.lnOrder);
            btnCountComment = (Button) v.findViewById(R.id.btnCountC);
            btnCountPicture = (Button) v.findViewById(R.id.btnCountP);

            txtNameR1 = (TextView) v.findViewById(R.id.txtNameReview1);
            txtNameR2 = (TextView) v.findViewById(R.id.txtNameReview2);
            txtR1 = (TextView) v.findViewById(R.id.txtReview1);
            txtR2 = (TextView) v.findViewById(R.id.txtReview2);
            imgAva1 = (CircleImageView) v.findViewById(R.id.imgAvatar1);
            imgAva2 = (CircleImageView) v.findViewById(R.id.imgAvatar2);
            txtRating1 = (TextView) v.findViewById(R.id.txtDiem1);
            txtRating2 = (TextView) v.findViewById(R.id.txtDiem2);

        }
    }

    @Override
    public ItemPlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_content, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        db = new DataBase(activity);
        db.openDataBase();

        //get listReview theo id của itemid
        arrayRv = db.getListReview(items.get(position).getId());
        Log.e("arrayRv",items.get(position).getId()+"");

//        if(arrayRv!=null || !arrayRv.isEmpty())
//        {
//            int resid = activity.getResources().getIdentifier("ava" + arrayRv.get(0).getAvatar(), "drawable", activity.getPackageName());
//            if (resid != 0)
//                Glide.with(activity).load(resid).into(holder.imgAva1);
//            holder.txtNameR1.setText(arrayRv.get(0).getName());
//            holder.txtR1.setText(arrayRv.get(0).getCommentTrim());
//            holder.txtRating1.setText(String.format("%.1f", arrayRv.get(0).getRating()) + "");
//
//            if(items.get(position).getId()!=226) {
//                int resid2 = activity.getResources().getIdentifier("ava" + arrayRv.get(1).getAvatar(), "drawable", activity.getPackageName());
//                if (resid2 != 0)
//                    Glide.with(activity).load(resid2).into(holder.imgAva2);
//                holder.txtNameR2.setText(arrayRv.get(1).getName());
//                holder.txtR2.setText(arrayRv.get(1).getCommentTrim());
//                holder.txtRating2.setText(String.format("%.1f", arrayRv.get(1).getRating()) + "");
//            }
//        }

        //set các thuộc tính của item
        String imgItem="https://foody-v2.herokuapp.com/getimg?nameimg=fdi";
        Glide.with(activity).load(imgItem+items.get(position).getImg()+".png").into(holder.imgItem);
        holder.txtName.setText(items.get(position).getName());
        holder.txtAvgRating.setText(String.valueOf(items.get(position).getAvgRating()));
        holder.txtAddress.setText(items.get(position).getAddress());
        holder.btnCountComment.setText(String.valueOf(items.get(position).getTotalReviews()));
        holder.btnCountPicture.setText(String.valueOf(items.get(position).getTotalPictures()));
        Log.e("name",items.get(position).getImg() );
    }

    public void clearData() {
        items.clear();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
