package com.example.lttha.a14110180_lethithao_foody.Activity;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lttha.a14110180_lethithao_foody.Activity.Fragment.FoodFragment;
import com.example.lttha.a14110180_lethithao_foody.Adapter.AddLocationAdapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.AddLocation_District_Adapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.AddLocation_Type_Adapter;
import com.example.lttha.a14110180_lethithao_foody.Adapter.ItemFoodAdapter;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemFoodsServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DBItemPlacesServer;
import com.example.lttha.a14110180_lethithao_foody.DataBase.DataBase;
import com.example.lttha.a14110180_lethithao_foody.Model.CityModel;
import com.example.lttha.a14110180_lethithao_foody.Model.DistrictModel;
import com.example.lttha.a14110180_lethithao_foody.Model.TypeModel;
import com.example.lttha.a14110180_lethithao_foody.R;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemFoods;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemPlaces;
import com.example.lttha.a14110180_lethithao_foody.Utils.User;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;

public class AddLocation extends AppCompatActivity implements DBItemFoodsServer.getItemFoodListener,View.OnClickListener {
    private static final int PICK_PICTURE = 1;
    public static Button btnt1, btnt2, btncity, btnIgnore, btnDistrict,btnHuy,btnXong,btnGui;
    TextView txtT,txtNo;
    LinearLayout lnType;
    ImageView imgInsert,imgChoose;
    EditText edNameLocation,edAddress,edDescribe;
    int districtid, cityid,typeid;
    String fileURI, encodedString;

    AlertDialog.Builder dialog;

    DataBase db;
    DBItemFoodsServer dbServer;

    ArrayList<CityModel> array;
    ArrayList<DistrictModel> arrayD;
    ArrayList<TypeModel> arryT;
    FoodFragment x =new FoodFragment();

    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_add_location);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        //Thao chiếu các biến tới id của nó ở file xml
        btnt1 = (Button) findViewById(R.id.btnTimeOpen);
        btnt2 = (Button) findViewById(R.id.btnTimeClose);
        btncity = (Button) findViewById(R.id.btnCity);
        btnDistrict = (Button) findViewById(R.id.btnDistrict);
        btnHuy=(Button) findViewById(R.id.btnHuy);
        btnXong=(Button) findViewById(R.id.btnXong);
        lnType=(LinearLayout) findViewById(R.id.lnType);
        btnGui=(Button) findViewById(R.id.btnGui);
        edNameLocation=(EditText) findViewById(R.id.edLocationName);
        edAddress=(EditText) findViewById(R.id.edAddress);
        txtT=(TextView) findViewById(R.id.txtType_Add);
        edDescribe=(EditText) findViewById(R.id.edDescribe);
        imgInsert=(ImageView) findViewById(R.id.imgInsertItem);
        imgChoose=(ImageView) findViewById(R.id.imgChooseImg);


        dialog = new AlertDialog.Builder(AddLocation.this);
        imgChoose.setOnClickListener(this);
        dialog.setCancelable(true);

        dbServer = new DBItemFoodsServer(this);

        //Khi click vào button giờ mở của
        btnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddLocation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        //Nếu giờ chọn nhỏ hơn 11 thì sẽ hiển thi text của btn là AM
                        if(selectedHour<11){
                            btnt1.setText(selectedHour + ":" + selectedMinute + " AM");
                        }
                        else
                            btnt1.setText(selectedHour + ":" + selectedMinute + " PM");

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Giờ mở cửa");
                mTimePicker.show();

            }
        });
        //Khi click vào button giờ dóng cửa
        btnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddLocation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        //Nếu giờ chọn nhỏ hơn 11 thì sẽ hiển thi text của btn là AM
                        if(selectedHour<11){
                            btnt2.setText(selectedHour + ":" + selectedMinute + " AM");
                        }
                        else
                            btnt2.setText(selectedHour + ":" + selectedMinute + " PM");

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Giờ đóng cửa");
                mTimePicker.show();

            }
        });

        // click vào btn TPHCM
        btncity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // hiển thị dialog

                LayoutInflater layoutInflater = LayoutInflater.from((getBaseContext()));
                view = layoutInflater.inflate(R.layout.layout_city_addlocation, null);

                db = new DataBase(getBaseContext());
                db.openDataBase();
                array = new ArrayList<>();
                array = db.getListCity();

                ListView list = (ListView) view.findViewById(R.id.lv_city_addlocation);
                AddLocationAdapter adapter = new AddLocationAdapter(AddLocation.this, array);

                list.setAdapter(adapter);

                dialog.setView(view);

                final AlertDialog alertDialog = dialog.create();

                btnIgnore = (Button) view.findViewById(R.id.btnIgnore);
                btnIgnore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();

                    }
                });
                // khi click vào item trong list hì sẽ đóng dialog,thay đổi text của btn và set id cho nó
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        AddLocationAdapter.vt = position;
                        alertDialog.dismiss();
                        btncity.setText(array.get(position).getName());
                        cityid=array.get(position).getId();
                    }

                });

                alertDialog.show();
            }
        });

        // click vào btn Quận
        btnDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from((getBaseContext()));
                view = layoutInflater.inflate(R.layout.layout_district_addlocation, null);

                db = new DataBase(getBaseContext());
                db.openDataBase();
                arrayD = new ArrayList<>();
                arrayD = db.getListDistrict(1);

                ListView list = (ListView) view.findViewById(R.id.lv_district_addlocation);
                AddLocation_District_Adapter adapter = new AddLocation_District_Adapter(AddLocation.this, arrayD);

                list.setAdapter(adapter);

                dialog.setView(view);

                final AlertDialog alertDialog = dialog.create();

                btnIgnore = (Button) view.findViewById(R.id.btnIgnore);
                // khi click vào btnIgnore sẽ đóng dialog
                btnIgnore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();

                    }
                });
                // khi click vào item trong list hì sẽ đóng dialog,thay đổi text của btn và set id cho nó
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        AddLocation_District_Adapter.vt = position;
                        alertDialog.dismiss();
                        btnDistrict.setText(arrayD.get(position).getName());
                        districtid=arrayD.get(position).getId();
                    }

                });
                alertDialog.show();

            }
        });
        // click vào lnType
        lnType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater = LayoutInflater.from((getBaseContext()));
                view = layoutInflater.inflate(R.layout.layout_type_addlocation, null);

                db = new DataBase(getBaseContext());
                db.openDataBase();
                arryT = new ArrayList<>();
                arryT = db.getType();

                ListView list = (ListView) view.findViewById(R.id.lv_type_addlocation);
                AddLocation_Type_Adapter adapter = new AddLocation_Type_Adapter(AddLocation.this, arryT);

                list.setAdapter(adapter);

                dialog.setView(view);

                final AlertDialog alertDialog = dialog.create();

                btnHuy = (Button) view.findViewById(R.id.btnHuy);
                btnXong=(Button) view.findViewById(R.id.btnXong);


                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        AddLocation_Type_Adapter.vt = position;
                        txtT.setText(arryT.get(position).getName());
                        Log.e("type name",arryT.get(position).getName());
                        typeid=arryT.get(position).getId();
                        alertDialog.dismiss();

                    }

                });

                btnXong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();

            }
        });

        //Click vào btn gửi để lưu lại tt insert
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = ProfileActivity.userCurrent;

                String url_imgitem="https://foody-v2.herokuapp.com/getimg?nameimg=fdi";
                String userava=url_imgitem+user.getImg()+".png";
                String address,name,Describe;
                address=String.valueOf(edAddress.getText());
                name=String.valueOf(edNameLocation.getText());
                Describe=String.valueOf(edDescribe.getText());
                if (address.trim().length()>0&& name.trim().length()>0&& Describe.trim().length()>0)
                {
                    dbServer.InsertItemFoods(String.valueOf(edAddress.getText()),String.valueOf(edNameLocation.getText())
                            ,encodedString,String.valueOf(edDescribe.getText()),districtid,typeid,cityid,userava,
                            String.valueOf(user.getUsername()));
                    Log.e("insert",String.valueOf(edAddress.getText())+" "+userava);
                    dbServer.getList_ItemFoods(x.currentID_Category,x.currentID_Type,x.currentID_District,x.currentID_City);

                    LayoutInflater layoutInflater = LayoutInflater.from((getBaseContext()));
                    view = layoutInflater.inflate(R.layout.layout_dialog_gui, null);

                    dialog.setView(view);

                    final AlertDialog alertDialog = dialog.create();
                    txtNo=(TextView) view.findViewById(R.id.txtNo);

                    txtNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                            AddLocation.this.finish();
                        }
                    });

                    alertDialog.show();
                }
                else
                    Toast.makeText(AddLocation.this,"Vui lòng nhập đầy đủ thông tin!",Toast.LENGTH_LONG).show();

            }
        });
        //String.valueOf(edAddress.getText());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    @SuppressLint("NewApi")
    private String getPath(Uri uri) {
        if( uri == null ) {
            return null;
        }
        String[] projection = { MediaStore.Images.Media.DATA };

        Cursor cursor;
        if(Build.VERSION.SDK_INT >19)
        {
            // Will return "image:x*"
            String wholeID = DocumentsContract.getDocumentId(uri);
            // Split at colon, use second item in the array
            String id = wholeID.split(":")[1];
            // where id is equal to
            String sel = MediaStore.Images.Media._ID + "=?";
            cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection, sel, new String[]{ id }, null);
        }
        else
        {
            cursor = getContentResolver().query(uri, projection, null, null, null);
        }
        String path = null;
        try
        {
            int column_index = cursor
                    .getColumnIndex(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            path = cursor.getString(column_index).toString();
            cursor.close();
        }
        catch(NullPointerException e) {
            e.printStackTrace();
        }
        return path;
    }

    private void pickCamera(){
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        intent.putExtra("crop", "true");
//        intent.putExtra("scale", true);
//        intent.putExtra("outputX", 256);
//        intent.putExtra("outputY", 256);
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("return-data", true);
        Intent intent = new Intent(Intent.ACTION_PICK/*,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI*/);
        intent.setType("image/*");
        intent.putExtra("return-data", true);
        try {
            startActivityForResult(intent, PICK_PICTURE);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }

    }
    // nhan resultcode de lay ket qua
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            switch (requestCode) {
                case PICK_PICTURE:
                    final Bundle extras = data.getExtras();
                    if (extras != null) {
                        //Get image
                        //Bitmap bitmap = extras.getParcelable("data");
                        try {
                            fileURI = getPath(data.getData());
                            MyBitMap myBitMap = new MyBitMap(this);
                            Bitmap bitmap = myBitMap.decodeUri(data.getData());
                            imgInsert.setImageBitmap(bitmap);
                            encodedString = myBitMap.getStringFromBitmap(bitmap);
                        }
                        catch (OutOfMemoryError | FileNotFoundException e){
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }






    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("AddLocation Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }


    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    @Override
    public void getItemFoods(ArrayList<ItemFoods> listItemFoods) {
        x.pAdapter = new ItemFoodAdapter(AddLocation.this,listItemFoods);
        x.gvItem.setAdapter(x.pAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgChooseImg:
                pickCamera();
                break;
        }
    }
}
