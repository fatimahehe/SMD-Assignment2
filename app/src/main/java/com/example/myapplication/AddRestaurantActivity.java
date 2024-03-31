package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddRestaurantActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editsubitem;
    private EditText editLocation;
    private EditText editPhone;
    private EditText editDescription;
    private EditText editRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        editTextName = findViewById(R.id.editTextName);
        editsubitem = findViewById(R.id.editsubitem);
        editLocation = findViewById(R.id.editlocation);
        editPhone = findViewById(R.id.editPhone);
        editDescription = findViewById(R.id.editDescription);
        editRating = findViewById(R.id.editRating);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString();
                String subItem = editsubitem.getText().toString();
                String location = editLocation.getText().toString();
                String phone = editPhone.getText().toString();
                String description = editDescription.getText().toString();
                int rating = Integer.parseInt(editRating.getText().toString());


                Restaurant restaurant = new Restaurant(name, subItem, location, phone, description, rating);


                Intent resultIntent = new Intent();
                resultIntent.putExtra("newRestaurant", restaurant);
                setResult(RESULT_OK, resultIntent);
                Toast.makeText(AddRestaurantActivity.this, "Restaurant added successfully", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
      Button btnBack = findViewById(R.id.btnBack2);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}