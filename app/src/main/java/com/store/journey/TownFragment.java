package com.store.journey;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.store.journey.R;

import java.util.ArrayList;

//Create TownFragment
public class TownFragment extends Fragment {
    //Create TownFragment constructor
    public TownFragment() {
        //empty constructor
    }

    //Override onStart method
    @Override
    public void onStop() {
        super.onStop();
    }

    //Override onCreateView method
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //First, create a rootView and inflate layout from item_listview
        View rootView = inflater.inflate(R.layout.item_listview, container, false);

        //Create a ArrayList of Item
        final ArrayList<Item> items = new ArrayList<Item>();

        //Add list data
        items.add(new Item(getString(R.string.tinh_hoa_binh),
                R.drawable.tinh_hoa_binh,
                getString(R.string.description_tinh_hoa_binh)));

        items.add(new Item(getString(R.string.tinh_son_la),
                R.drawable.tinh_son_la,
                getString(R.string.description_tinh_son_la)));

        items.add(new Item(getString(R.string.tinh_dien_bien),
                R.drawable.tinh_dien_bien,
                getString(R.string.description_tinh_dien_bien)));

        items.add(new Item(getString(R.string.tinh_lai_chau),
                R.drawable.tinh_lai_chau,
                getString(R.string.description_tinh_lai_chau)));

        items.add(new Item(getString(R.string.tinh_lao_cai),
                R.drawable.tinh_lao_cai,
                getString(R.string.description_tinh_lao_cai)));

        items.add(new Item(getString(R.string.tinh_yen_bai),
                R.drawable.tinh_yen_bai,
                getString(R.string.description_tinh_yen_bai)));

        //Create an ItemAdapter
        final ItemAdapter adapter = new ItemAdapter(getActivity(), items);

        //Get list view
        ListView listView = rootView.findViewById(R.id.list);

        //Bind with adapter
        listView.setAdapter(adapter);

        //Create a onClickLIstener when listview item clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Get current item index
                Item currentItem = items.get(i);

                //Create an Intent
                Intent itemDetailIntent = new Intent(getContext(), PlaceDetailActivity.class);

                //Add an extra var; ITEM_TITEL so we can reference in the placeDetail activity
                itemDetailIntent.putExtra(getString(R.string.intent_extra_item_title),
                        currentItem.getTitle());

                //Start the intent
                startActivity(itemDetailIntent);
            }
        });

        //Return the view
        return rootView;
    }
}
