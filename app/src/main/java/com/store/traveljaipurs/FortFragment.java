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

public class FortFragment extends Fragment {
    public FortFragment() {
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_listview, container, false);
        final ArrayList<Item> inner_Details = new ArrayList<>();

        inner_Details.add(new Item(getString(R.string.tinh_kon_tum),
                R.drawable.tinh_kon_tum,
                getString(R.string.description_tinh_kon_tum)));

        inner_Details.add(new Item(getString(R.string.tinh_gia_lai),
                R.drawable.tinh_gia_lai,
                getString(R.string.description_tinh_gia_lai)));

        inner_Details.add(new Item(getString(R.string.tinh_dak_lak),
                R.drawable.tinh_dak_lak,
                getString(R.string.description_tinh_dak_lak)));

        inner_Details.add(new Item(getString(R.string.tinh_dak_nong),
                R.drawable.tinh_dak_nong,
                getString(R.string.description_tinh_dak_nong)));

        inner_Details.add(new Item(getString(R.string.tinh_lam_dong),
                R.drawable.tinh_lam_dong,
                getString(R.string.description_tinh_lam_dong)));

        final ItemAdapter adapter = new ItemAdapter(getActivity(), inner_Details);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item Current_list = inner_Details.get(i);

                Intent itemDetailIntent = new Intent(getContext(), PlaceDetailActivity.class);

                itemDetailIntent.putExtra(getString(R.string.intent_extra_item_title),
                        Current_list.getTitle());

                startActivity(itemDetailIntent);
            }
        });
        return rootView;
    }
}