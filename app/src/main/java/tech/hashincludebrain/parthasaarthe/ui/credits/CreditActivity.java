package tech.hashincludebrain.parthasaarthe.ui.credits;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import tech.hashincludebrain.parthasaarthe.R;

/**
 * Created by Priyabrata Naskar on 25-01-2021.
 */
public class CreditActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        backButton = findViewById(R.id.credit_back_button);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.credit_back_button:
                //Clear back stack & go back to previous activity
                finish();
                break;
        }
    }
}