package com.store.journey;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;
import com.store.journey.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewpager);

        ListAdapter adapter = new ListAdapter(this, getSupportFragmentManager());
        ViewPager2Adapter pager2Adapter = new ViewPager2Adapter(getSupportFragmentManager(), this.getLifecycle());

        TownFragment townFragment = new TownFragment();
        CuisineFragment cuisineFragment = new CuisineFragment();
        LodgeFragment lodgeFragment = new LodgeFragment();
        FortFragment fortFragment = new FortFragment();

        pager2Adapter.addFragment(townFragment);
        pager2Adapter.addFragment(cuisineFragment);
        pager2Adapter.addFragment(lodgeFragment);
        pager2Adapter.addFragment(fortFragment);

        viewPager.setAdapter(pager2Adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    String tabTitle;
                    if (position == 0) {
                        tabTitle = getString(R.string.category_name_northern_vietnam);
                    } else if (position == 1) {
                        tabTitle = getString(R.string.category_name_central_vietnam);
                    } else if (position == 2) {
                        tabTitle = getString(R.string.category_name_south_vietnam);
                    } else if (position == 3) {
                        tabTitle = getString(R.string.category_name_central_highland_of_vietnam);
                    } else {
                        tabTitle = "";
                    }
                    tab.setText(tabTitle);
                }
        ).attach();
    }


}
