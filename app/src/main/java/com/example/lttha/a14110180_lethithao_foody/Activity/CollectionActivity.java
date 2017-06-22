package com.example.lttha.a14110180_lethithao_foody.Activity;

import android.support.v7.app.AppCompatActivity;

public class CollectionActivity extends AppCompatActivity {
    //Chưa cần thiết kế

    //This is our tablayout
//    private TabLayout tabLayout;
//
//    //This is our viewPager
//    private ViewPager viewPager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.collection_layout);
//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//
//        //Adding the tabs using addTab() method
//        tabLayout.addTab(tabLayout.newTab().setText("Ở đâu"));
//        tabLayout.addTab(tabLayout.newTab().setText("Ăn gì"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
//
//        //Initializing viewPager
//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//
//        //Creating our pager adapter
//        CollectionActivity.Pager adapter = new CollectionActivity.Pager(getSupportFragmentManager(), tabLayout.getTabCount());
//
//        //Adding adapter to pager
//        viewPager.setAdapter(adapter);
//
//        tabLayout.setupWithViewPager(viewPager);
//
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            onBackPressed();
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
//
//    public void onBackPressed() {
//        Intent myIntent = new Intent(this, MainActivity.class);
//        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);//clear top of stack.
//        startActivity(myIntent);
//        finish();
//        return;
//    }
//
//    //Extending FragmentStatePagerAdapter
//    public class Pager extends FragmentStatePagerAdapter {
//        //Constructor to the class
//        public Pager(FragmentManager fm, int tabCount) {
//            super(fm);
//        }
//
//        //Overriding method getItem
//        @Override
//        public Fragment getItem(int position) {
//            //Returning the current tabs
//            switch (position) {
//                case 0:
//                    LocationCollectionFragment location_collection_fragment = new LocationCollectionFragment();
//                    return location_collection_fragment;
//                case 1:
//                    AlbumFragment album_fragment = new AlbumFragment();
//                    return album_fragment;
//                default:
//                    return null;
//            }
//        }
//
//        //Overriden method getCount to get the number of tabs
//        @Override
//        public int getCount() {
//            return 2;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "Bộ sưu tập địa điểm";
//                default:
//                    return "Bộ sưu tập ảnh";
//            }
//        }
//    }
}
