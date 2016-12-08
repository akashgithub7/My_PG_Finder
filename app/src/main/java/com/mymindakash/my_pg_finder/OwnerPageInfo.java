package com.mymindakash.my_pg_finder;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mymindakash.my_pg_finder.fragments.PropertyBasicInfo;
import com.mymindakash.my_pg_finder.fragments.PropertyMoreInfo;
import com.mymindakash.my_pg_finder.fragments.User_Login_Form;


/**
 * Created by Akruti on 12/6/2016.
 */

public class OwnerPageInfo extends AppCompatActivity implements ActionBar.TabListener {
    private ActionBar actionBar;
    private Toolbar toolbar ;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    // Tab titles
    private String[] tabs = {"Register", "Login"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_page_info);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Property Basic Information"));
        tabLayout.addTab(tabLayout.newTab().setText("Property More Information"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);

        actionBar = getSupportActionBar();
        final OwnerAdapter ownerAdapter = new OwnerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());


        viewPager.setAdapter(ownerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });


    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}




