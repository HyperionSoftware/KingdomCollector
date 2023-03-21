package cat.udl.hyperion.appmobils.kingdomcollector.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cat.udl.hyperion.appmobils.kingdomcollector.R;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button Settings.
        findViewById(R.id.btn_settings).setOnClickListener(view -> setBtn_aboutus());

        //Button AboutUs.
        findViewById(R.id.btn_terms).setOnClickListener(view -> setBtn_settings());

        //Button Start.
        findViewById(R.id.btn_start).setOnClickListener(view -> setBtn_start());

        //Music
        MediaPlayer mp = MediaPlayer.create(this,R.raw.check_it_out_now);
        mp.start();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        Toast.makeText(this, "¡WELCOME!", Toast.LENGTH_SHORT).show(); // + user.getUid()

        findViewById(R.id.btn_logout).setOnClickListener(v -> logout());
    }

    private void logout() {
        mAuth.signOut();
        finish();

    }

    public void setBtn_aboutus(){
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }

    public void setBtn_settings(){
        Intent intent = new Intent(this, TermsAndConditions.class);
        startActivity(intent);
    }

    public void setBtn_start(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

}