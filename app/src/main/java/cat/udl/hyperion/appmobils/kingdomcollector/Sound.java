package cat.udl.hyperion.appmobils.kingdomcollector;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Sound extends AppCompatActivity {

    // Audio para reproducción corta.
    /*public void AudioSoundPool(View view){
        sp.play(sonido_reproduccion,1,1,1,0,0);

    }*/


    // Audio para reproducción larga.
    /*public void AudioMediaPlayer(){
        MediaPlayer mp = MediaPlayer.create(this,R.raw.check_it_out_now);
        mp.start();
    }*/


    /*public void setSonido_reproduccion(){
        Intent intent = new Intent(this, activity_tablero.class);
        startActivity(intent);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC,1);
        sonido_reproduccion = sp.load(this, R.raw.check_it_out_now,1);
        // sonido_reproduccion = sp.load("res/raw/check_it_out_now.mp3",1);

    }*/
}
