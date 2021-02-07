package tech.hashincludebrain.parthasaarthe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import tech.hashincludebrain.parthasaarthe.R;
import tech.hashincludebrain.parthasaarthe.ui.game.ChapterListActivity;
import tech.hashincludebrain.parthasaarthe.ui.quiz.QuizCategoryActivity;
import tech.hashincludebrain.parthasaarthe.ui.settings.SettingsActivity;

/**
 * Created by Priyabrata Naskar on 22-01-2021.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton newGameButton;
    MaterialButton continueGameButton;
    MaterialButton exploreButton;
    CheckBox settingsCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        newGameButton = findViewById(R.id.new_game);
        newGameButton.setOnClickListener(this);

        continueGameButton = findViewById(R.id.continue_game);
        continueGameButton.setOnClickListener(this);

        exploreButton = findViewById(R.id.explore_game);
        exploreButton.setOnClickListener(this);

        settingsCheckBox = findViewById(R.id.game_settings_icon);
        settingsCheckBox.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_game:
                resetChapters();
                startActivity(new Intent(getApplicationContext(), ChapterListActivity.class));
                break;
            case R.id.continue_game:
                startActivity(new Intent(getApplicationContext(), ChapterListActivity.class));
                break;
            case R.id.explore_game:
                startActivity(new Intent(getApplicationContext(), QuizCategoryActivity.class));
                break;
            case R.id.game_settings_icon:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
        }
    }

    private void resetChapters() {
        //TODO: Implement progress reset
    }
}