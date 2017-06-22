package com.example.lttha.a14110180_lethithao_foody.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lttha.a14110180_lethithao_foody.Activity.Fragment.City;
import com.example.lttha.a14110180_lethithao_foody.Activity.Fragment.PlaceFragment;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemPlacesServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.MainActivity;
import com.example.lttha.a14110180_lethithao_foody.Model.DistrictModel;
import com.example.lttha.a14110180_lethithao_foody.Model.StreetModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemPlaces;

import java.util.ArrayList;

/**
 * Created by lttha on 3/25/2017.
 */
//List các quận trong tỉnh
public class ListDistrictAdapter extends BaseAdapter implements DBItemPlacesServer.getItemPlaceListener{
    Context context;
    private Activity activity;
    public static int vt=-1;//chua click vao lítview
    private ArrayList<DistrictModel> list=new ArrayList<>();

    ArrayList<StreetModel> arrayStreet=new ArrayList<>();  // loi~ nay moi gap k biet sua? sao. mai qua xem cho ki~
    DataBase db;
    StreetAdapter streetAdapter;
    DBItemPlacesServer dbItemPlacesServer;
    PlaceFragment x = new PlaceFragment();


    private static LayoutInflater inflater=null;
    public ListDistrictAdapter(Context context,ArrayList<DistrictModel> list) {
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

    @Override
    public void getItemPlaces(ArrayList<ItemPlaces> itemPlaces) {
        Log.e("","so item khi chon street la : "+itemPlaces.size());
        x.pAdapter = new ItemPlaceAdapter(x.getActivity(),itemPlaces); // ?? di dau r v
        x.lv.setAdapter(x.pAdapter);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ListDistrictAdapter.ViewHolder holder=new ListDistrictAdapter.ViewHolder();
        View rowView;
        rowView=inflater.inflate(R.layout.list_district,null);
        //
        holder.tv=(TextView) rowView.findViewById(R.id.tvquan);
        holder.btn=(Button) rowView.findViewById(R.id.btnD);
        holder.listView=(ListView)rowView.findViewById(R.id.lvD);
        db=new DataBase(context);
        db.openDataBase();
        dbItemPlacesServer = new DBItemPlacesServer(this);
        arrayStreet=db.getListStreet(list.get(position).getId());
        streetAdapter=new StreetAdapter(context,arrayStreet);
        holder.listView.setAdapter(streetAdapter);
        //
        //int soduong = 20 + (int)(Math.random() * 80);
        holder.tv.setText(list.get(position).getName());
        holder.btn.setText(arrayStreet.size()+" đường \t >");  // tat cai team view qua ben di

        holder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                City ct = new City();
                ct.cityAdapter.vt = -1;
                StreetModel st = (StreetModel)adapterView.getItemAtPosition(i);
                x.currenntId_Street =st.getId();
                x.setChangeButton(st.getName(),3);
                Log.e("","id street la : "+st.getId());
                dbItemPlacesServer.getList_ItemPlaces(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City,x.currenntId_Street);
                x.fr3.setVisibility(View.GONE);
                x.frMain.setVisibility(View.VISIBLE);

                //Hiện tabwidget khi click item.
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);
            }
        }); // de? do coi

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
            holder.tv.setTextColor(rowView.getResources().getColor(R.color.colorPrimary));


        }


        return rowView;
    }
}
