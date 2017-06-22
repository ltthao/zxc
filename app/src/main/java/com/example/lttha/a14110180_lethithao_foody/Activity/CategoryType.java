package com.example.lttha.a14110180_lethithao_foody.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.example.lttha.a14110180_lethithao_foody.Adapter.GridDiscoveryAdapter;
import com.example.lttha.a14110180_lethithao_foody.R;
//Activity của layout khi click vào button F
public class CategoryType extends AppCompatActivity {
    String[] names = new String[]{
            "Ăn uống",
            "Du lịch",
            "Cưới hỏi",
            "Đẹp khỏe",
            "Giải trí",
            "Mua sắm",
            "Giáo dục",
            "Dịch vụ"
    };
    int[] imgs = new int[]{
            R.drawable.ic_h_angi,
            R.drawable.ic_h_dulich,
            R.drawable.ic_h_cuoihoi,
            R.drawable.ic_h_depkhoe,
            R.drawable.ic_h_giaitri,
            R.drawable.ic_h_muasam,
            R.drawable.ic_h_giaoduc,
            R.drawable.ic_h_dichvu
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_type);

        //Tham chiếu tới toolbar trong layout activity_category_type.xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_category_type);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setDisplayHomeAsUpEnabled(true);
        }

        //Khởi tạo gridDiscovery
        GridDiscoveryAdapter adapter = new GridDiscoveryAdapter(CategoryType.this, names, imgs);
        GridView grid=(GridView)findViewById(R.id.gridDicovery);
        grid.setAdapter(adapter);


    }
}
