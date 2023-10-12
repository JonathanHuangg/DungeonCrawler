package com.example.dungencrawler.viewmodels;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungencrawler.R;
import com.example.dungencrawler.map.MapLayout;
import com.example.dungencrawler.map.Tilemap;

public class Room1Activity extends AppCompatActivity {

    int [] blocks = {
            R.drawable.lavatexture,
            R.drawable.cobblestonetexture,
            R.drawable.leaftexture,
            R.drawable.goldtexture,
            R.drawable.grasstexture
    };

    int widthOfBlock, noOfBlocks = 10, widthOfScreen;
    private Tilemap tilemap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1_view); // Set the layout here
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen = displayMetrics.widthPixels;
        int heightOfScreen = displayMetrics.heightPixels;

        widthOfBlock = widthOfScreen / noOfBlocks;
        createBoard();

        // Initialize the tilemap from the layout
        tilemap = findViewById(R.id.tilemap);

        // If you need to set some properties or listeners on tilemap, you can do it here. For example:
        // tilemap.setSomeProperty(value);

    }

    private void createBoard() {
        GridLayout gridLayout = findViewById(R.id.board);

        gridLayout.setRowCount(noOfBlocks);
        gridLayout.setColumnCount(noOfBlocks);

        gridLayout.getLayoutParams().width = widthOfScreen;
        gridLayout.getLayoutParams().height = widthOfScreen;

        for (int i = 0; i < noOfBlocks * noOfBlocks; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(widthOfBlock,widthOfBlock));
            imageView.setMaxHeight(widthOfBlock);
            imageView.setMaxWidth(widthOfBlock);
            int randomBlock = (int) Math.floor(Math.random() * blocks.length);
            imageView.setImageResource(blocks[randomBlock]);
            gridLayout.addView(imageView);
        }
    }
}