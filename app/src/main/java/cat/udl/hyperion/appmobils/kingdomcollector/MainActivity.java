package cat.udl.hyperion.appmobils.kingdomcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button Settings.
        findViewById(R.id.btn_settings).setOnClickListener(view -> setBtn_aboutus());

        //Button AboutUs.
        findViewById(R.id.btn_aboutUs).setOnClickListener(view -> setBtn_settings());

        //Button Start.
        findViewById(R.id.btn_start).setOnClickListener(view -> setBtn_start());

        //Music
        MediaPlayer mp = MediaPlayer.create(this,R.raw.check_it_out_now);
        mp.start();

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
        Intent intent = new Intent(this, activity_tablero.class);
        startActivity(intent);
    }

}