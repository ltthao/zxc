package com.example.lttha.a14110180_lethithao_foody.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.Model.DistrictModel;
import com.example.lttha.a14110180_lethithao_foody.Model.StreetModel;
import com.example.lttha.a14110180_lethithao_foody.R;

import java.util.ArrayList;

/**
 * Created by lttha on 4/19/2017.
 */

public class DistrictFoodAdapter extends BaseAdapter {
    Context context;
    public static int vt=-1;//chua click vao lítview
    private ArrayList<DistrictModel> list=new ArrayList<>();
    ArrayList<StreetModel> arrayStreet=new ArrayList<>();
    DataBase db;
    StreetAdapter streetAdapter;

    private static LayoutInflater inflater=null;
    public DistrictFoodAdapter(Context context,ArrayList<DistrictModel> list) {
        this.context=context;
        this.list=list;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }
    public class ViewHolder
    {
        TextView tv;
        Button btn;
        ListView listView;
    }
    private static void justifyListViewHeightBasedOnChildren (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final DistrictFoodAdapter.ViewHolder holder=new DistrictFoodAdapter.ViewHolder();
        view=inflater.inflate(R.layout.list_district,null);


        holder.tv=(TextView) view.findViewById(R.id.tvquan);
        holder.btn=(Button) view.findViewById(R.id.btnD);
        holder.listView=(ListView)view.findViewById(R.id.lvD);

        db=new DataBase(context);
        db.openDataBase();
        arrayStreet=db.getListStreet(list.get(position).getId());
        streetAdapter=new StreetAdapter(context,arrayStreet);
        holder.listView.setAdapter(streetAdapter);
        Log.e("street ",arrayStreet.get(position).getName());

        //
        holder.tv.setText(list.get(position).getName());
        holder.btn.setText(arrayStreet.size()+" đường \t >");

        holder.listView.setVisibility(View.GONE);
        justifyListViewHeightBasedOnChildren(holder.listView);
        if(holder.listView.isShown())
            holder.listView.setVisibility(View.VISIBLE);
        //Sự kiện click button để show ra listview con
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.listView.setVisibility(holder.listView.isShown() ? View.GONE : View.VISIBLE);

            }
        });
        if(vt==position) {
            //Thay đổi màu của textview khi click
            holder.tv.setTextColor(view.getResources().getColor(R.color.colorPrimary));


        }

        return view;
    }
}
