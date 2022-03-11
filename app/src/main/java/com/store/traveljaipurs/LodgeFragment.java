package com.store.traveljaipurs;

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

import com.store.traveljaipurs.R;

import java.util.ArrayList;

//Create LodgeFragment
public class LodgeFragment extends Fragment {
    //Create LodgeFragment constructor
    public LodgeFragment() {
        //empty constructor
    }

    //Override onStart method
    @Override
    public void onStart() {
        super.onStart();
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
        items.add(new Item(getString(R.string.thanh_pho_ho_chi_minh),
                R.drawable.thanh_pho_ho_chi_minh,
                getString(R.string.description_thanh_pho_ho_chi_minh)));

        items.add(new Item(getString(R.string.tinh_ba_ria_vung_tau),
                R.drawable.tinh_ba_ria_vung_tau,
                getString(R.string.description_tinh_ba_ria_vung_tau)));

        items.add(new Item(getString(R.string.tinh_binh_duong),
                R.drawable.tinh_binh_duong,
                getString(R.string.description_tinh_binh_duong)));

        items.add(new Item(getString(R.string.tinh_binh_phuoc),
                R.drawable.tinh_binh_phuoc,
                getString(R.string.description_tinh_binh_phuoc)));

        items.add(new Item(getString(R.string.tinh_dong_nai),
                R.drawable.tinh_dong_nai,
                getString(R.string.description_tinh_dong_nai)));

        items.add(new Item(getString(R.string.tinh_tay_ninh),
                R.drawable.tinh_tay_ninh,
                getString(R.string.description_tinh_tay_ninh)));

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
                itemDetailIntent.putExtra(getString(R.string.intent_extra_item_title), currentItem.getTitle());

                //Start the intent
                startActivity(itemDetailIntent);
            }
        });

        //Return the view
        return rootView;
    }
}