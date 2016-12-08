package com.mymindakash.my_pg_finder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.mymindakash.my_pg_finder.fragments.PropertyBasicInfo;
import com.mymindakash.my_pg_finder.fragments.PropertyMoreInfo;



/**
 * Created by Akruti on 12/7/2016.
 */

public class OwnerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public OwnerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position == 0) {
            fragment = new PropertyBasicInfo();
        }
        else if(position == 1) {
            fragment = new PropertyMoreInfo();
        }
        return fragment;


    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}
