package com.example.lttha.a14110180_lethithao_foody.DataBase;

/**
 * Created by lttha on 3/30/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.lttha.a14110180_lethithao_foody.Adapter.ItemPlaceAdapter;
import com.example.lttha.a14110180_lethithao_foody.Model.CategoryModel;
import com.example.lttha.a14110180_lethithao_foody.Model.CityModel;
import com.example.lttha.a14110180_lethithao_foody.Model.DistrictModel;
import com.example.lttha.a14110180_lethithao_foody.Model.ItemModel;
import com.example.lttha.a14110180_lethithao_foody.Model.ReviewModel;
import com.example.lttha.a14110180_lethithao_foody.Model.StreetModel;
import com.example.lttha.a14110180_lethithao_foody.Model.TypeModel;
import com.example.lttha.a14110180_lethithao_foody.Utils.ApiUtils;
import com.example.lttha.a14110180_lethithao_foody.Utils.ItemPlaces;
import com.example.lttha.a14110180_lethithao_foody.Utils.Reviews;
import com.example.lttha.a14110180_lethithao_foody.Utils.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataBase extends SQLiteOpenHelper {
    // All Static variables
// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "foody.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context ctx;
    static ArrayList<ItemPlaces> list = new ArrayList<>();
    private Service mService;
    public interface getReviewsListener{
        void getDatarevies(ArrayList<Reviews> reviewses);
    }
   // private getReviewsListener getReviewsListener;
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
        mService = ApiUtils.getService();
    }

    //get list Category
    public ArrayList<CategoryModel> getCategory(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CategoryModel> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM CATEGORY", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            CategoryModel cate = new CategoryModel(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3));
            cursor.moveToNext();
            list.add(cate);

        }
        cursor.close();
        db.close();
        return list;
    }
    //get list category cuar an gi
    public ArrayList<CategoryModel> getCategoryFood(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CategoryModel> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM CATEGORY where IDCATEGORY<6 ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            CategoryModel cate = new CategoryModel(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3));
            cursor.moveToNext();
            list.add(cate);

        }
        cursor.close();
        db.close();
        return list;
    }
    //Get list type
    public ArrayList<TypeModel> getType(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<TypeModel> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM TYPE limit 20", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            TypeModel type = new TypeModel(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getInt(3));
            cursor.moveToNext();
            list.add(type);

        }
        cursor.close();
        db.close();
        return list;
    }
    //get list District theo CityID
    public ArrayList<DistrictModel> getListDistrict(int CITYID){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DistrictModel> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM DISTRICT WHERE CITYID="+CITYID+"", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DistrictModel dt = new DistrictModel(cursor.getInt(0),cursor.getInt(1), cursor.getString(2));
            cursor.moveToNext();
            list.add(dt);

        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<ItemModel> getListItem2(int category_id, int type_id, int district_id, int city_id){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ItemModel> list=new ArrayList<>();
        Cursor cursor,cursor_review;
        // Lúc nào cũng có category_id với city_id
        if(district_id != -1){
            if(type_id == -1)
                cursor = db.rawQuery("SELECT * FROM ITEM WHERE CATEGORYID="+category_id+
                        " and DISTRICTID="+district_id+"  LIMIT 16", null);
            else
                cursor = db.rawQuery("SELECT * FROM ITEM WHERE CATEGORYID="+category_id+
                        " and DISTRICTID="+district_id+" and TYPEID="+type_id+"  LIMIT 16", null);
        }
        else
        {
            if(type_id == -1)
                cursor = db.rawQuery("SELECT * FROM ITEM,DISTRICT,CITY WHERE ITEM.DISTRICTID=DISTRICT.ID AND"
                        +" DISTRICT.CITYID=CITY.ID AND CITY.ID="+city_id+" AND CATEGORYID="+category_id+
                        "  LIMIT 16", null);
            else
                cursor = db.rawQuery("SELECT * FROM ITEM,DISTRICT,CITY WHERE ITEM.DISTRICTID=DISTRICT.ID AND"
                        +" DISTRICT.CITYID=CITY.ID AND CITY.ID="+city_id+" AND CATEGORYID="+category_id+
                        " AND TYPEID="+type_id+" LIMIT 16", null);
        }


        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ItemModel item = new ItemModel();
            item.setId(cursor.getInt(0));
            item.setAddress(cursor.getString(1));
            item.setName(cursor.getString(2));
            item.setTotalReviews(cursor.getInt(3));
            item.setImg(cursor.getString(4));
            item.setDistrictId(cursor.getInt(5));
            item.setAvgRating(cursor.getDouble(6));
            item.setCategoryId(cursor.getInt(7));
            item.setTypeId(cursor.getInt(8));
            item.setTotalPictures(20 + (int)(Math.random() * 80));
            item.setRestaurantId(20 + (int)(Math.random() * 80));

            ArrayList<ReviewModel> reviews=new ArrayList<>();
            cursor_review = db.rawQuery("SELECT * FROM REVIEW WHERE ITEMID="+cursor.getInt(0)+" LIMIT 2", null);
            cursor_review.moveToFirst();
            while (!cursor_review.isAfterLast()){
                ReviewModel review = new ReviewModel();
                review.setReviewID(cursor_review.getInt(0));
                review.setName(cursor_review.getString(1));
                review.setRating(cursor_review.getDouble(2));
                review.setComment(cursor_review.getString(3));
                review.setCommentTrim(cursor_review.getString(4));
                review.setAvatar(cursor_review.getInt(5));
                review.setItemID(cursor_review.getInt(6));
                review.setReviewUrl(cursor_review.getString(7));
                cursor_review.moveToNext();
                reviews.add(review);
            }
            cursor_review.close();
            item.setArrayList(reviews);

            cursor.moveToNext();
            list.add(item);
        }
        cursor.close();
        db.close();
        return list;
    }
    public void getReview(int itemid, final getReviewsListener getReviewsListener){
        mService = ApiUtils.getService();
        Call<ArrayList<Reviews>> call = mService.listReviewByItem(itemid);
        Log.e("url", call.request().url()+"");
        Log.e("ResponseReview",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<Reviews>>() {
            @Override
            public void onResponse(Call<ArrayList<Reviews>> call, Response<ArrayList<Reviews>> response) {
                if(response.isSuccessful() && getReviewsListener!=null) {
                    getReviewsListener.getDatarevies(response.body());

                    Log.e("name", response.body().get(0).getName());
                }
                else
                    Log.e("Response",response.message());
            }
            @Override
            public void onFailure(Call<ArrayList<Reviews>> call, Throwable t) {
                Log.e("ResponseReview",t.getMessage());
            }
        });
    }
//    public ArrayList<ItemPlaces> getItem(Integer categoryid, Integer typeid,Integer districtid,Integer cityid){
//        mService = ApiUtils.getService();
//        Call<ArrayList<ItemPlaces>> call = mService.listItemPlaces(categoryid,typeid,districtid,cityid);
//        Log.e("Response",call.request().url().toString());
//        call.enqueue(new Callback<ArrayList<ItemPlaces>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ItemPlaces>> call, Response<ArrayList<ItemPlaces>> response) {
//                if(response.isSuccessful()) {
//                    list = response.body();
//                    Log.e("nameReview", list.get(0).getName());
//                }
//                else
//                    Log.e("Response",response.message());
//            }
//            @Override
//            public void onFailure(Call<ArrayList<ItemPlaces>> call, Throwable t) {
//                Log.e("Response",t.getMessage());
//            }
//        });
//        return list;
//    }

    //get list review
    public ArrayList<ReviewModel> getListReview(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ReviewModel> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM REVIEW where itemid="+id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ReviewModel review = new ReviewModel(cursor.getInt(0),cursor.getString(1),cursor.getDouble(2),cursor.getString(3),
                    cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getString(7));
            cursor.moveToNext();
            Log.d("review::: ",review.getName());
            list.add(review);

        }
        cursor.close();
        db.close();
        return list;
    }
    //get list city
    public ArrayList<CityModel> getListCity(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CityModel> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM CITY", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            CityModel city = new CityModel(cursor.getInt(0),cursor.getString(1));
            cursor.moveToNext();
            Log.d("item::: ",city.getName());
            list.add(city);

        }
        cursor.close();
        db.close();
        return list;
    }
    public ArrayList<StreetModel> getListStreet(int districtID){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<StreetModel> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM STREET where districtId="+districtID+"", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            StreetModel city = new StreetModel(cursor.getInt(0),cursor.getInt(1),cursor.getString(2));
            cursor.moveToNext();
            list.add(city);

        }
        cursor.close();
        db.close();
        return list;
    }
    public void CopyDataBaseFromAsset() throws IOException {

        InputStream myInput = ctx.getAssets().open(DATABASE_NAME);

// Path to the just created empty db
        String outFileName = getDatabasePath();

// if the path doesn't exist first, create it
        File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

// Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

// transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

// Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    private static String getDatabasePath() {
        return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub

    }
}
