package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvRest;
    adapter myAdapter;
    List<Restaurant> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        init();

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddRestaurantActivity when the add button is clicked
                Intent intent = new Intent(MainActivity.this, AddRestaurantActivity.class);
                startActivityForResult(intent, 1);
            }
        });



        rvRest = findViewById(R.id.rvlists);
        rvRest.setHasFixedSize(true);

        myAdapter = new adapter(list, new adapter.OnRestaurantClickListener() {
            @Override
            public void onRestaurantClick(Restaurant restaurant) {
                // Start RestaurantDetailsActivity and pass the selected restaurant
                Intent intent = new Intent(MainActivity.this, RestaurantDetailsActivity.class);

                intent.putExtra("restaurantName", restaurant.getItemName());
                intent.putExtra("restaurantLocation", restaurant.getLocation());
                intent.putExtra("Phone", restaurant.getPhone());
                intent.putExtra("Description", restaurant.getDescription());
                intent.putExtra("rating", restaurant.getRating());
                startActivity(intent);
            }
        });

        rvRest.setLayoutManager(new LinearLayoutManager(this));
        rvRest.setAdapter(myAdapter);

        SearchView simpleSearchView = findViewById(R.id.simpleSearchView);
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String[] parts = query.split("\\s+");
                int rating = 0;
                String location = "";

                if (parts.length > 0 && parts[0].matches("\\d+")) {
                    rating = Integer.parseInt(parts[0]);
                    if (parts.length > 1) {
                        location = parts[1];
                    }
                } else {
                    location = query;
                }

                myAdapter.filterByRatingAndLocation(rating, location);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            Restaurant newRestaurant = (Restaurant) data.getSerializableExtra("newRestaurant");

            list.add(newRestaurant);
            myAdapter.filterByRatingAndLocation(0, ""); // Reset filter to update filtered list

            myAdapter.notifyDataSetChanged();
        }
    }


    private void init() {
        list.add(new Restaurant("Aylanto", "Desi", "MM-alam", "123-456-7890", "Pakistani cuisine.", 2));
        list.add(new Restaurant("Arcadian", "Italian", "Gulberg", "987-654-3210", "Italian restaurant", 5));
        list.add(new Restaurant("Benediction", "Italian", "Fortress", "456-789-0123", "Cozy Italian", 4));
        list.add(new Restaurant("Bundu Khan", "Desi", "Bahria town", "789-012-3456", "traditional Pakistani barbecue.", 3));
        list.add(new Restaurant("Cafe de Como", "Pan Asian", "Liberty", "321-654-9870", "Modern cafe", 4));
        list.add(new Restaurant("Dera", "Desi", "MM-alam", "012-345-6789", "Pakistani dishes.", 5));
        list.add(new Restaurant("Egg Spectation", "Brunch", "MM-alam", "654-987-0123", "Brunch spot", 4));
        list.add(new Restaurant("Fuoco", "Cuisine", "Gulberg", "876-543-2109", "European cuisine.", 3));
        list.add(new Restaurant("Gloria Jeans", "Cafe", "Jail road", "987-012-3456", "Cafe chain", 3));
        list.add(new Restaurant("Haveli", "Cultural", "Emporium Mall", "210-987-6543", "Cultural ambiance.", 2));
        list.add(new Restaurant("Indigo", "European", "Jail Road", "567-890-1234", "European bistro", 5));
        list.add(new Restaurant("Jade", "Chinese", "Bahria town", "543-210-9876", "Popular Chinese restaurant", 3));
        list.add(new Restaurant("Koya", "Italian", "Gulberg", "890-123-4567", "Modern Italian", 4));
    }
}
