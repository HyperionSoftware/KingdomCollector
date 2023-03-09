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

    SoundPool sp;
    private int sonido_reproduccion;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button Settings.
        findViewById(R.id.btn_settings).setOnClickListener(view -> setBtn_aboutus());

        //Button AboutUs.
        findViewById(R.id.btn_aboutUs).setOnClickListener(view -> setBtn_settings());

        //Music
        findViewById(R.id.btn_start_button).setOnClickListener(view -> setSonido_reproduccion());


        //sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        //sonido_reproduccion = sp.load(this, R.raw.check_it_out_now,1);



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

    public void setBtn_aboutus(){
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }

    public void setBtn_settings(){
        Intent intent = new Intent(this, TermsAndConditions.class);
        startActivity(intent);
    }
    public void setSonido_reproduccion(){
        Intent intent = new Intent(this, activity_tablero.class);
        startActivity(intent);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        sonido_reproduccion = sp.load(this, R.raw.check_it_out_now,1);
       // sonido_reproduccion = sp.load("res/raw/check_it_out_now.mp3",1);

    }

}