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

        place.add(new Item(getString(R.string.Fairmont_title),
                R.drawable.lodge_fairmont,
                getString(R.string.Fairmont_location),
                getResources().getStringArray(R.array.Fairmont_highlights),
                getString(R.string.Fairmont_provider)));

        place.add(new Item(getString(R.string.Ibis_title),
                R.drawable.lodge_ibis,
                getString(R.string.Ibis_location),
                getResources().getStringArray(R.array.Ibis_highlights),
                getString(R.string.Ibis_provider)));

        place.add(new Item(getString(R.string.Radission_title),
                R.drawable.lodge_radisson,
                getString(R.string.Radission_location),
                getResources().getStringArray(R.array.Radission_highlights),
                getString(R.string.Radission_provider)));

        place.add(new Item(getString(R.string.Meridian_title),
                R.drawable.lodge_meridian,
                getString(R.string.Meridian__location),
                getResources().getStringArray(R.array.Meridian__highlights),
                getString(R.string.Meridian__provider)));

        place.add(new Item(getString(R.string.Oberoi_Palace_title),
                R.drawable.lodge_oberoi,
                getString(R.string.Oberoi_Palace_location),
                getResources().getStringArray(R.array.Oberoi_Palace_highlights),
                getString(R.string.Oberoi_Palace_provider)));

        place.add(new Item(getString(R.string.Rajasthan_Palace_title),
                R.drawable.lodge_rajasthan,
                getString(R.string.Rajasthan_Palace_location),
                getResources().getStringArray(R.array.Rajasthan_Palace_highlights),
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
