package cat.udl.hyperion.appmobils.kingdomcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button btn_aboutus;
    ImageButton play, btn_settings;
    SoundPool sp;
    private int sonido_reproduccion;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Button Settings.
        btn_settings = (ImageButton) findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(MainActivity.this, TermsAndConditions.class)});
            }
        });


        //Button AboutUs.
        btn_aboutus = (Button) findViewById(R.id.btn_aboutUs);
        btn_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(MainActivity.this, AboutUs.class)});
            }
        });

        // Music
        play = (ImageButton) findViewById(R.id.btn_start_button);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        sonido_reproduccion = sp.load(this,R.raw.check_it_out_now,1);

    }
    // Audio para reproducción corta.
    public void AudioSoundPool(View view){
        sp.play(sonido_reproduccion,1,1,1,0,0);

    }

    // Audio para reproducción larga.
    public void AudioMediaPlayer(View view){
        MediaPlayer mp = MediaPlayer.create(this,R.raw.check_it_out_now);
        mp.start();
    }

}