package com.example.first;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Shop_info> shops;

    ShopAdapter(Context context, List<Shop_info> shops) {
        this.shops = shops;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopAdapter.ViewHolder holder, int position) {
        Shop_info shop_info = shops.get(position);
        holder.dates.setText(shop_info.getDate());
        holder.places.setText(shop_info.getPlace());
        holder.prices.setText(shop_info.getPrice());
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView dates, places, prices;
        ViewHolder(View view){
            super(view);
            dates = (TextView) view.findViewById(R.id.date_item);
            places = (TextView) view.findViewById(R.id.place_item);
            prices = (TextView) view.findViewById(R.id.price_item);
        }
    }
}