package com.fun_corp.umamappsv10;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fun_corp.umamappsv10.Tab.SlidingTabLayout;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


import static com.fun_corp.umamappsv10.R.layout.fragment_aplikasi;


public class MainActivity extends AppCompatActivity  {

    public Drawer.Result navigationDrawerLeft;
    public AccountHeader.Result headerNavigatorLeft;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    Firebase bacadata;
    String scanid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //Button btn_ya =(Button) findViewById(R.id.button);
        //Button btn_tidak =(Button) findViewById(R.id.button2);

      // TextView mamaa = (TextView) findViewById(R.id.textView_mam);


        //findViewById(R.id.button).setOnClickListener(this);
       // findViewById(R.id.button2).setOnClickListener(this);

//        try{
//            bacadata.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    scanid = dataSnapshot.child("Barcode").getValue().toString();
//                }
//
//                @Override
//                public void onCancelled(FirebaseError firebaseError) {
//
//                }
//            });
//        }catch (Exception e){
//
//        }


       // Toast.makeText(this, "id nyaa adalah"+scanid, Toast.LENGTH_SHORT).show();

        final Toolbar toolbar=(Toolbar) findViewById(R.id.my_awesome_toolbar);
        //toolbar.setLogo(R.mipmap.ic_label_white_48dp);
        toolbar.setTitle("SCMS");
        toolbar.setSubtitle("Safety Car Monitoring System v2.0");
        setSupportActionBar(toolbar);

        //Tab
        mViewPager=(ViewPager)findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),this));

        mSlidingTabLayout=(SlidingTabLayout)findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view,R.id.tv_tab);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
navigationDrawerLeft.setSelection(position);
                switch (position){
                    case   0:
                        toolbar.setTitle("Kecepatan");
                        break;
                    case   1:
                        toolbar.setTitle("Alkohol");
                        break;
                    case   2:
                        toolbar.setTitle("Lokasi");
                        break;
                    case   3:
                        toolbar.setTitle("Statistik");
                        break;
                    case   4:
                        toolbar.setTitle("Tentang Kami");
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //navigation Drawer
        //header
        headerNavigatorLeft =new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(false)
                .withHeaderBackground(R.mipmap.ss)
                .addProfiles(new ProfileDrawerItem().withName("Universitas Teknokrat Indonesia").withEmail("HambaAllah").withIcon(getResources().getDrawable(R.mipmap.icon)))
                .build();
        //left
        navigationDrawerLeft = new Drawer()
        .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(headerNavigatorLeft)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int id, long l, IDrawerItem drawerItem) {

                        //script link nya
                        mViewPager.setCurrentItem(id);
                        toolbar.setTitle(((PrimaryDrawerItem) drawerItem).getName());

                    }
                })
                .build();
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Kecepatan").withIcon(getResources().getDrawable(R.mipmap.speedo1)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Alkohol").withIcon(getResources().getDrawable(R.mipmap.alkoholblack)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Lokasi").withIcon(getResources().getDrawable(R.mipmap.ic_location_on_b_48dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Statistik").withIcon(getResources().getDrawable(R.mipmap.ic_trending_up_black_24dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Tentang Kami").withIcon(getResources().getDrawable(R.mipmap.ic_person_b_48dp)));
//        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Scan Barcode").withIcon(getResources().getDrawable(R.mipmap.ic_crop_free_black_24dp)));


    }



    //Navigator Drawer



}
