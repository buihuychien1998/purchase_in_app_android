package com.store.traveljaipurs;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.store.traveljaipurs.R;

import java.util.ArrayList;
import java.util.Objects;

public class PlaceDetailActivity extends AppCompatActivity {
    final ArrayList<Item> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        String itemTitle = getIntent().getStringExtra(getString(R.string.intent_extra_item_title));

        setTitle(itemTitle);

        initItems(mItems);

        int currentPlaceIndex = getItemIndexByTitle(itemTitle);

        inflateLayout(currentPlaceIndex);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    private void inflateLayout(int index) {
        ImageView placeImage = findViewById(R.id.item_image);
        placeImage.setImageResource(mItems.get(index).getImageResourceId());

        TextView placeName = findViewById(R.id.item_title);
        placeName.setText(mItems.get(index).getTitle());

        TextView placeLocation = findViewById(R.id.item_location);
        placeLocation.setText(mItems.get(index).getLocation());

        TextView PlaceHighlight = findViewById(R.id.item_hightlights);

        String[] highlights = mItems.get(index).getHighlights();

        String highlights_text = "";

        for (String highlight : highlights) {
            highlights_text += "* " + highlight + "\n\n";
        }
        PlaceHighlight.setText(highlights_text);

    }


    private int getItemIndexByTitle(String title) {
        try {
            for (int i = 0; i < mItems.size(); i++) {
                Item currentItem = mItems.get(i);
                String currentTitle = currentItem.getTitle();
                if (title.equals(currentTitle)) {
                    return i;
                }
            }
        }
        catch (Error error) {
            error.printStackTrace();
        }
        return -1;
    }

    private void initItems(ArrayList<Item> place) {

        place.add(new Item(getString(R.string.tinh_hoa_binh),
                R.drawable.tinh_hoa_binh,
                getString(R.string.tinh_hoa_binh),
                getResources().getStringArray(R.array.description_detail_tinh_hoa_binh),
                getString(R.string.description_tinh_hoa_binh)));

        place.add(new Item(getString(R.string.tinh_son_la),
                R.drawable.tinh_son_la,
                getString(R.string.tinh_son_la),
                getResources().getStringArray(R.array.description_detail_tinh_son_la),
                getString(R.string.description_tinh_son_la)));

        place.add(new Item(getString(R.string.tinh_dien_bien),
                R.drawable.tinh_dien_bien,
                getString(R.string.tinh_dien_bien),
                getResources().getStringArray(R.array.description_detail_tinh_dien_bien),
                getString(R.string.description_tinh_dien_bien)));

        place.add(new Item(getString(R.string.tinh_lai_chau),
                R.drawable.tinh_lai_chau,
                getString(R.string.tinh_lai_chau),
                getResources().getStringArray(R.array.description_detail_tinh_lai_chau),
                getString(R.string.description_tinh_lai_chau)));

        place.add(new Item(getString(R.string.tinh_lao_cai),
                R.drawable.tinh_lao_cai,
                getString(R.string.tinh_lao_cai),
                getResources().getStringArray(R.array.description_detail_tinh_lao_cai),
                getString(R.string.description_tinh_lao_cai)));

        place.add(new Item(getString(R.string.tinh_yen_bai),
                R.drawable.tinh_yen_bai,
                getString(R.string.tinh_yen_bai),
                getResources().getStringArray(R.array.description_detail_tinh_yen_bai),
                getString(R.string.description_tinh_yen_bai)));

        place.add(new Item(getString(R.string.tinh_thanh_hoa),
                R.drawable.tinh_thanh_hoa,
                getString(R.string.tinh_thanh_hoa),
                getResources().getStringArray(R.array.description_detail_tinh_thanh_hoa),
                getString(R.string.description_tinh_thanh_hoa)));

        place.add(new Item(getString(R.string.tinh_nghe_an),
                R.drawable.tinh_nghe_an,
                getString(R.string.tinh_nghe_an),
                getResources().getStringArray(R.array.description_detail_tinh_nghe_an),
                getString(R.string.description_tinh_nghe_an)));

        place.add(new Item(getString(R.string.tinh_ha_tinh),
                R.drawable.tinh_ha_tinh,
                getString(R.string.tinh_ha_tinh),
                getResources().getStringArray(R.array.description_detail_tinh_ha_tinh),
                getString(R.string.description_tinh_ha_tinh)));

        place.add(new Item(getString(R.string.tinh_quang_binh),
                R.drawable.tinh_quang_binh,
                getString(R.string.tinh_quang_binh),
                getResources().getStringArray(R.array.description_detail_tinh_quang_binh),
                getString(R.string.description_tinh_quang_binh)));

        place.add(new Item(getString(R.string.tinh_quang_tri),
                R.drawable.tinh_quang_tri,
                getString(R.string.tinh_quang_tri),
                getResources().getStringArray(R.array.description_detail_tinh_quang_tri),
                getString(R.string.description_tinh_quang_tri)));

        place.add(new Item(getString(R.string.tinh_thua_thien_hue),
                R.drawable.tinh_thua_thien_hue,
                getString(R.string.tinh_thua_thien_hue),
                getResources().getStringArray(R.array.description_detail_tinh_thua_thien_hue),
                getString(R.string.description_tinh_thua_thien_hue)));

        place.add(new Item(getString(R.string.thanh_pho_ho_chi_minh),
                R.drawable.thanh_pho_ho_chi_minh,
                getString(R.string.thanh_pho_ho_chi_minh),
                getResources().getStringArray(R.array.description_detail_thanh_pho_ho_chi_minh),
                getString(R.string.description_thanh_pho_ho_chi_minh)));

        place.add(new Item(getString(R.string.tinh_ba_ria_vung_tau),
                R.drawable.tinh_ba_ria_vung_tau,
                getString(R.string.tinh_ba_ria_vung_tau),
                getResources().getStringArray(R.array.description_detail_tinh_ba_ria_vung_tau),
                getString(R.string.description_tinh_ba_ria_vung_tau)));

        place.add(new Item(getString(R.string.tinh_binh_duong),
                R.drawable.tinh_binh_duong,
                getString(R.string.tinh_binh_duong),
                getResources().getStringArray(R.array.description_detail_tinh_binh_duong),
                getString(R.string.description_tinh_binh_duong)));

        place.add(new Item(getString(R.string.tinh_binh_phuoc),
                R.drawable.tinh_binh_phuoc,
                getString(R.string.tinh_binh_phuoc),
                getResources().getStringArray(R.array.description_detail_tinh_binh_phuoc),
                getString(R.string.description_tinh_binh_phuoc)));

        place.add(new Item(getString(R.string.tinh_dong_nai),
                R.drawable.tinh_dong_nai,
                getString(R.string.tinh_dong_nai),
                getResources().getStringArray(R.array.description_detail_tinh_dong_nai),
                getString(R.string.description_tinh_dong_nai)));

        place.add(new Item(getString(R.string.tinh_tay_ninh),
                R.drawable.tinh_tay_ninh,
                getString(R.string.tinh_tay_ninh),
                getResources().getStringArray(R.array.description_detail_tinh_tay_ninh),
                getString(R.string.Rajasthan_Palace_provider)));

        place.add(new Item(getString(R.string.Hawamahel_title),
                R.drawable.fort_hawamahel,
                getString(R.string.Hawamahel_location),
                getResources().getStringArray(R.array.Hawamahel_highlights),
                getString(R.string.Hawamahel_provider)));

        place.add(new Item(getString(R.string.Albert_hall_title),
                R.drawable.fort_alberthall,
                getString(R.string.Albert_hall_location),
                getResources().getStringArray(R.array.Albert_hall_highlights),
                getString(R.string.Albert_hall_provider)));

        place.add(new Item(getString(R.string.Amber_Fort_title),
                R.drawable.fort_amber,
                getString(R.string.Amber_Fort_location),
                getResources().getStringArray(R.array.Amber_Fort_highlights),
                getString(R.string.Amber_Fort_provider)));

        place.add(new Item(getString(R.string.Nahargarh_Fort_title),
                R.drawable.fort_nahargarh,
                getString(R.string.Nahargarh_Fort_location),
                getResources().getStringArray(R.array.Nahargarh_Fort_highlights),
                getString(R.string.Nahargarh_Fort_provider)));

        place.add(new Item(getString(R.string.Jantar_Mantar_title),
                R.drawable.fort_jantarmantar,
                getString(R.string.Jantar_Mantar_location),
                getResources().getStringArray(R.array.Jantar_Mantar_highlights),
                getString(R.string.Jantar_Mantar_provider)));

        place.add(new Item(getString(R.string.Jal_Mahel_title),
                R.drawable.fort_jalmahel,
                getString(R.string.Jal_Mahel_location),
                getResources().getStringArray(R.array.Jal_Mahel_highlights),
                getString(R.string.Jal_Mahel_provider)));
    }
}
