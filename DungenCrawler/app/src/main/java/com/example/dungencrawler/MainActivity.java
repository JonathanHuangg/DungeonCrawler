package com.example.dungencrawler;
import android.content.Intent;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText userName = findViewById(R.id.userName);
        ImageButton nextButton = findViewById(R.id.configNextButton);
        ImageButton lastButton = findViewById(R.id.configLastButton);

        nextButton.setEnabled(false);
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean isValid = validateName(charSequence.toString());
                nextButton.setEnabled(isValid);

                if (!isValid) {
                    userName.setError("The name cannot be empty or just spaces");
                }
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });
        lastButton.setOnClickListener(v -> {
            Intent startPage = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(startPage);
        });

        nextButton.setOnClickListener(v -> {
            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
            RadioGroup characterRadioGroup = findViewById(R.id.characterRadioGroup);
            double difficulty;
            int character;

            int difficultyCheckedId = difficultyRadioGroup.getCheckedRadioButtonId();
            int characterCheckedId = characterRadioGroup.getCheckedRadioButtonId();

            // Set difficulty
            if (difficultyCheckedId == R.id.easyButton) {
                difficulty = 0.5;
            } else if (difficultyCheckedId == R.id.mediumButton) {
                difficulty = 0.75;
            } else if (difficultyCheckedId == R.id.hardButton) {
                difficulty = 1;
            } else {
                difficulty = 0.5; // Default to easy
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

            Intent game = new Intent(MainActivity.this, GameActivity.class);
            game.putExtra("difficulty", difficulty);
            game.putExtra("character", character);
            startActivity(game);
            finish();
        });
    }

    // Validate name input
    private boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}