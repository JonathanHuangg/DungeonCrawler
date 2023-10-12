package com.example.dungencrawler.viewmodels;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungencrawler.R;
import com.example.dungencrawler.map.MapLayout;
import com.example.dungencrawler.map.Tilemap;

public class Room1Activity extends AppCompatActivity {

    private Tilemap tilemap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1_view); // Set the layout here

        // Initialize the tilemap from the layout
        tilemap = findViewById(R.id.tilemap);

        // If you need to set some properties or listeners on tilemap, you can do it here. For example:
        // tilemap.setSomeProperty(value);
    }
}