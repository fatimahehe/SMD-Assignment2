package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    private List<Restaurant> restaurants;
    private List<Restaurant> filteredRestaurants;
    private OnRestaurantClickListener clickListener;

    public adapter(List<Restaurant> list, OnRestaurantClickListener listener) {
        restaurants = list;
        filteredRestaurants = new ArrayList<>(list);
        clickListener = listener;
    }

    public void filterByRatingAndLocation(int rating, String location) {
        filteredRestaurants.clear();
        for (Restaurant restaurant : restaurants) {
            if ((rating == 0 || restaurant.getRating() == rating) &&
                    (location.isEmpty() || restaurant.getLocation().toLowerCase().contains(location.toLowerCase()))) {
                filteredRestaurants.add(restaurant);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = filteredRestaurants.get(position);
        holder.tvName.setText(restaurant.getItemName());
        holder.tvsubname.setText(restaurant.getSubItemName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onRestaurantClick(restaurant);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredRestaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvsubname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvItem);
            tvsubname = itemView.findViewById(R.id.tvsubitem);
        }
    }

    public interface OnRestaurantClickListener {
        void onRestaurantClick(Restaurant restaurant);
    }
}
