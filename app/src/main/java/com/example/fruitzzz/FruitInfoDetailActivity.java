package com.example.fruitzzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FruitInfoDetailActivity extends AppCompatActivity {

    TextView FruitName, Description, Mineral, VitC, VitA;
    String id, fruit_name, desc, min, vit_c, vit_a, photo;

    ImageView Photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_info_detail);

        AppCompatButton btn_back = (AppCompatButton) findViewById(R.id.idBTNback);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FruitName = findViewById(R.id.fruitName);
        Description =  findViewById(R.id.description);
        Mineral = findViewById(R.id.mineral);
        VitC = findViewById(R.id.vitc);
        VitA = findViewById(R.id.vita);
        Photo = findViewById(R.id.image_detail);

        id = getIntent().getStringExtra("id");
        fruit_name = getIntent().getStringExtra("fruitName");
        desc = getIntent().getStringExtra("description");
        min = getIntent().getStringExtra("mineral");
        vit_c = getIntent().getStringExtra("vitC");
        vit_a = getIntent().getStringExtra("vitA");
        photo = getIntent().getStringExtra("photo");

        FruitName.setText(fruit_name);
        Description.setText(desc);
        Mineral.setText(min);
        VitC.setText(vit_c);
        VitA.setText(vit_a);

        Picasso.get().load("https://tkjb2019.com/mobile/api_kelompok_3/image/" + photo).into(Photo);
    }
}