package cat.udl.hyperion.appmobils.kingdomcollector.other;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class Config extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        // Button go back to main:
        findViewById(R.id.button_back_from_config).setOnClickListener(view -> goToMainActivity());

        // Button go about us
        findViewById(R.id.button_about_us).setOnClickListener(view -> goToAboutus());

        // Button terms and conditions
        findViewById(R.id.button_terms_and_conditions).setOnClickListener(view -> goToTermsAndConditions());

    }

    private void goToTermsAndConditions() {
        Intent intent = new Intent(this, TermsAndConditions.class);
        startActivity(intent);
    }

    private void goToAboutus() {
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}