package com.store.journey;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.store.journey.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    static class ViewHolder {
        TextView title;
        TextView cost;
        TextView location;
        ImageView image;
    }

    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Item currentItem = getItem(position);
        ViewHolder holder = new ViewHolder();

        holder.title = listItemView.findViewById(R.id.title);
        holder.title.setText(currentItem.getTitle());

        holder.cost = listItemView.findViewById(R.id.cost);
        holder.cost.setText(TextUtils.isEmpty(currentItem.getCost()) ? "FREE" : currentItem.getCost());

        holder.location = listItemView.findViewById(R.id.location);
        holder.location.setText(currentItem.getLocation());


        if(currentItem.getImageResourceId() != 0){
            holder.image.setImageResource(currentItem.getImageResourceId());
        } else {
            Glide.with(holder.image.getContext()).load(currentItem.getImageUrl()).into(holder.image);
        }

        return listItemView;
    }

}
