package com.example.dungencrawler.viewmodels;
import android.content.Intent;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.RadioGroup;

import com.example.dungencrawler.R;
import com.example.dungencrawler.model.Difficulty;
import com.example.dungencrawler.model.Player;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        EditText userName = findViewById(R.id.userName);
        ImageButton nextButton = findViewById(R.id.configNextButton);
        ImageButton lastButton = findViewById(R.id.configLastButton);

        nextButton.setEnabled(false);

        //verify text
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean isValid = Player.validateName(charSequence.toString());
                nextButton.setEnabled(isValid);

                if (!isValid) {
                    userName.setError("The name cannot be empty or just spaces");
                }
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });

        //Return to start
        lastButton.setOnClickListener(v -> {
            MainActivity.this.finish();
        });

        //Go to Game
        nextButton.setOnClickListener(v -> {
            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
            RadioGroup characterRadioGroup = findViewById(R.id.characterRadioGroup);

            Difficulty difficulty;
            int character;
            String username;

            int difficultyCheckedId = difficultyRadioGroup.getCheckedRadioButtonId();
            int characterCheckedId = characterRadioGroup.getCheckedRadioButtonId();

            // Set difficulty
            if (difficultyCheckedId == R.id.easyButton) {
                difficulty = Difficulty.easy;
            } else if (difficultyCheckedId == R.id.mediumButton) {
                difficulty = Difficulty.medium;
            } else if (difficultyCheckedId == R.id.hardButton) {
                difficulty = Difficulty.hard;
            } else {
                difficulty = Difficulty.easy; // Default to easy
            }

            // Set character
            if (characterCheckedId == R.id.character1Button) {
                character = 1;
            } else if (characterCheckedId == R.id.character2Button) {
                character = 2;
            } else if (characterCheckedId == R.id.character3Button) {
                character = 3;
            } else {
                character = 1; // Default to first character
            }

            //get username
            EditText ign = (EditText) findViewById(R.id.userName);
            String editTextIgn = ign.getText().toString();


            Intent game = new Intent(MainActivity.this, GameActivity.class);
            game.putExtra("difficulty", difficulty);
            game.putExtra("character", character);
            game.putExtra("username", editTextIgn);
            startActivity(game);
            finish();
        });
    }

    // Validate name input
    //public boolean validateName(String name) {
        //return name != null && !name.trim().isEmpty();
    //}
}