package com.store.journey;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.store.journey.R;


@SuppressWarnings("ALL")
public class ListAdapter extends FragmentPagerAdapter {
    private Context thisContext;
    private int currentPos = -1;
    TownFragment townFragment = new TownFragment();
    CuisineFragment cuisineFragment = new CuisineFragment();
    LodgeFragment lodgeFragment = new LodgeFragment();
    FortFragment fortFragment = new FortFragment();

    public ListAdapter(Context context, FragmentManager fm) {
        super(fm);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.addToBackStack(null);
        transaction.commit();
        thisContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        currentPos = position;
        if (position == 0) {
            return townFragment;
        } else if (position == 1) {
            return cuisineFragment;
        } else if (position == 2) {
            return lodgeFragment;
        } else
            return fortFragment;

    }


    @Override
    public int getCount() {
        return 4;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return thisContext.getString(R.string.category_name_northern_vietnam);
        } else if (position == 1) {
            return thisContext.getString(R.string.category_name_central_vietnam);
        } else if (position == 2) {
            return thisContext.getString(R.string.category_name_south_vietnam);
        } else if (position == 3) {
            return thisContext.getString(R.string.category_name_central_highland_of_vietnam);
        } else {
            return "";
        }
    }

}
