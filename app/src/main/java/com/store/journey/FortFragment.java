package com.store.journey;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.store.journey.R;

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
                "https://www.vietnamvisa-easy.com/blog/wp-content/uploads/2016/11/kon-tum.jpg",
                getString(R.string.description_tinh_kon_tum)));

        inner_Details.add(new Item(getString(R.string.tinh_gia_lai),
                "https://upload.wikimedia.org/wikipedia/commons/9/9c/Chi%E1%BB%81u_cao_nguy%C3%AAn_-_Late_afternoon_in_the_Central_High_Plateaux_-_panoramio.jpg"
                ,
                getString(R.string.description_tinh_gia_lai)));

        inner_Details.add(new Item(getString(R.string.tinh_dak_lak),
                "https://baoquocte.vn/stores/news_dataimages/linhnguyen/062020/26/16/4033_94ed99c8a51658480107.jpg?rt=20200626194851"
                ,
                getString(R.string.description_tinh_dak_lak)));

        inner_Details.add(new Item(getString(R.string.tinh_dak_nong),
                "https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2021/12/18/985970/TOAN-CANH-03.jpg",

                getString(R.string.description_tinh_dak_nong)));

        inner_Details.add(new Item(getString(R.string.tinh_lam_dong),
                "https://static1.cafeland.vn/cafelandnew/hinh-anh/2022/01/06/158/da-lat.jpg",
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