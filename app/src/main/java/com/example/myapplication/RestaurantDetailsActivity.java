package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String restaurantName = getIntent().getStringExtra("restaurantName");
        String restaurantLocation = getIntent().getStringExtra("restaurantLocation");
        String Phone = getIntent().getStringExtra("Phone");
        String Description = getIntent().getStringExtra("Description");
        int rating=getIntent().getIntExtra("rating",0);

        TextView tvName = findViewById(R.id.textViewName);
        TextView tvLocation = findViewById(R.id.textViewLocation);
        TextView tvPhone = findViewById(R.id.textViewPhone);
        TextView tvDescription = findViewById(R.id.textViewDescription);
        TextView tvRating=findViewById(R.id.textViewRating);


        tvName.setText(restaurantName);
        tvLocation.setText(restaurantLocation);
        tvPhone.setText(Phone);
        tvDescription.setText(Description);
        tvRating.setText(String.valueOf(rating));


    }
}
