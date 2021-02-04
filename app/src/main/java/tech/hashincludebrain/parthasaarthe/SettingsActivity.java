package tech.hashincludebrain.parthasaarthe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

/**
 * Created by Priyabrata Naskar on 25-01-2021.
 */
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    //Material Buttons
    MaterialButton settingsBackButton;
    MaterialButton creditButton;

    //Material Switches
    SwitchMaterial darkModeSwitch;
    SwitchMaterial musicSwitch;
    SwitchMaterial soundEffectSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Settings Button
        settingsBackButton = findViewById(R.id.settings_back_button);
        settingsBackButton.setOnClickListener(this);

        //Credit Button
        creditButton = findViewById(R.id.settings_credit_button);
        creditButton.setOnClickListener(this);

        //Switches
        darkModeSwitch = findViewById(R.id.dark_mode_switch);
        musicSwitch = findViewById(R.id.music_switch);
        soundEffectSwitch = findViewById(R.id.sound_switch);

        //Adapter for Material Spinner
        ArrayAdapter<String> materialAdapter =
                new ArrayAdapter<>(
                        this, R.layout.drop_down_pop_up_menu_item, getResources().getStringArray(R.array.languages));

        AutoCompleteTextView editTextFilledExposedDropdown =
                findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(materialAdapter);

        //Handle Click on the spinner item
        editTextFilledExposedDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String spinnerText = parent.getItemAtPosition(position).toString();
                Toast.makeText(SettingsActivity.this, spinnerText + " Item selected: " + position, Toast.LENGTH_SHORT).show();
            }

        });

        //Listen Selection on Dak Mode Switch
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //TODO: set Dark Theme
                    Toast.makeText(getApplicationContext(), "Setting Dark Theme", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO: set Light Theme
                    Toast.makeText(getApplicationContext(), "Setting Light Theme", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Listen selection on Music Switch
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //TODO: Handle Music On
                    Toast.makeText(getApplicationContext(), "Music On", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO: Handle Music off
                    Toast.makeText(getApplicationContext(), "Music off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Listen selection on Sound Effect Switch
        soundEffectSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //TODO: Handle Sound Effect On
                    Toast.makeText(getApplicationContext(), "Sound Effect On", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO: Handle Sound Effect off
                    Toast.makeText(getApplicationContext(), "Sound Effect off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Handle click on Material Buttons
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_back_button:
                //Clear back stack & go back to previous activity
                finish();
                break;
            case R.id.settings_credit_button:
                //Open credit activity page
                startActivity(new Intent(this, CreditActivity.class));
        }
    }
}