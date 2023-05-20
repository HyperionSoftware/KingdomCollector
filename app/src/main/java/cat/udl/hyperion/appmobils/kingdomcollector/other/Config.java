package cat.udl.hyperion.appmobils.kingdomcollector.other;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class Config extends AppCompatActivity {
    private SeekBar volumenSeekBar;
    private AudioManager audioManager;


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

        // Dentro de onCreate()
        volumenSeekBar = findViewById(R.id.ajustador_volumen);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Configura el rango máximo de la SeekBar utilizando el volumen máximo del sistema
        int maxVolumen = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volumenSeekBar.setMax(maxVolumen);

        // Obtiene el volumen actual del sistema y establece el progreso de la SeekBar
        int currentVolumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumenSeekBar.setProgress(currentVolumen);

        // Configura un listener para detectar los cambios en la SeekBar y ajustar el volumen
        volumenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Establece el volumen del sistema según el progreso de la SeekBar
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No se requiere implementación adicional
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No se requiere implementación adicional
            }
        });


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