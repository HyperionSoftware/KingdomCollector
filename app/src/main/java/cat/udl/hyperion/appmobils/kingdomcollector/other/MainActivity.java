package cat.udl.hyperion.appmobils.kingdomcollector.other;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.views.CollectionActivity;
import cat.udl.hyperion.appmobils.kingdomcollector.collection.views.addingcards;
import cat.udl.hyperion.appmobils.kingdomcollector.game.views.GameActivity;
import cat.udl.hyperion.appmobils.kingdomcollector.other.auth.LastLoginCallback;


public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    protected String myClassTag = this.getClass().getSimpleName();

    private MediaPlayer mp;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference gameDataRef = database.getReference("game_data");

    String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    DatabaseReference userGameDataRef = gameDataRef.child(userId);

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

        //Button Collection.
        findViewById(R.id.btn_collection).setOnClickListener(view -> setBtn_collection());

        //TODO: Borrar cuándo estén todas las cartas ya incluidas en firebase storage.
        //Button actualizar.
        findViewById(R.id.actualizar).setOnClickListener(view -> setBtn_actualizar());

        //Music
        mp = MediaPlayer.create(this,R.raw.check_it_out_now);
        mp.start();


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;

        getPenultimateLogin(new PenultimateLoginCallback() {
            @Override
            public void onFailure(String usuario_no_autenticado) {
                //TODO: DA1. Funcionar amb valors de strings.
                Toast.makeText(MainActivity.this, getString(R.string.unauthenticated_user_error) + usuario_no_autenticado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String secondLastLogin) {
                if (secondLastLogin != null) {
                    Log.d(myClassTag,"LastLogin:" + secondLastLogin);
                    String welcomeMessage = String.format(getString(R.string.welcome), user.getDisplayName(), secondLastLogin);
                    Toast.makeText(MainActivity.this, welcomeMessage, Toast.LENGTH_SHORT).show();
                } else {
                    String welcomeNoRecordMessage = String.format(getString(R.string.welcome_without_registration), user.getDisplayName());
                    Toast.makeText(MainActivity.this, welcomeNoRecordMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btn_logout).setOnClickListener(v -> logout());

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    private void setBtn_actualizar() {
        Intent intent = new Intent(this, addingcards.class);
        startActivity(intent);
    }

    // Método para parar la música cuando la app está en segundo plano
    @Override
    protected void onPause() {
        super.onPause();
        if (mp != null) {
            mp.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mp != null) {
            mp.start();
        }
    }

    // Liberar recursos Mediaplayer
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.release();
            mp = null;
        }
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

        // Supongamos que la puntuación está almacenada en una variable llamada "puntuacion"
        int puntuacion = 100;
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            // Guardar la puntuación en Firebase Realtime Database
            DatabaseReference puntuacionesRef = FirebaseDatabase.getInstance().getReference("users/" + userId + "/puntuaciones");
            String scoreId = puntuacionesRef.push().getKey();  // Generar un nuevo ID para la puntuación
            if(scoreId != null) {
                puntuacionesRef.child(scoreId).setValue(puntuacion)
                        .addOnSuccessListener(aVoid -> Log.d(myClassTag, "Puntuación guardada en Firebase Realtime Database"))
                        .addOnFailureListener(e -> Log.e(myClassTag, "Error al guardar la puntuación en Firebase Realtime Database", e));
            } else {
                // No se pudo generar un nuevo ID para la puntuación
                Log.e(myClassTag, "Error al generar un nuevo ID para la puntuación");
            }
        } else {
            // El usuario no está autenticado
            Log.e(myClassTag, "Error al guardar la puntuación: Usuario no autenticado");
        }
    }




    public void setBtn_collection(){
        Intent intent = new Intent(this, CollectionActivity.class);
        startActivity(intent);
    }
    private void getLastLogin(LastLoginCallback callback) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            callback.onFailure("Usuario no autenticado");
            return;
        }
        String userId = user.getUid();
        DatabaseReference loginRef = FirebaseDatabase.getInstance().getReference("login_records").child(userId);
        loginRef.orderByKey().limitToLast(2).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() == 2) {
                    String secondLastLogin = null;
                    int count = 0;
                    for (DataSnapshot loginSnapshot : dataSnapshot.getChildren()) {
                        if (++count == 2) {
                            secondLastLogin = loginSnapshot.child("timestamp").getValue(String.class);
                            break;
                        }
                    }
                    callback.onSuccess(secondLastLogin);
                } else {
                    callback.onFailure("No login records found or only one record exists.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailure(databaseError.getMessage());
            }
        });
    }
    private void getPenultimateLogin(PenultimateLoginCallback callback) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            callback.onFailure("Usuario no autenticado");
            return;
        }
        String userId = user.getUid();
        DatabaseReference loginRef = FirebaseDatabase.getInstance().getReference("login_records").child(userId);
        loginRef.orderByChild("timestamp").limitToLast(2).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() == 2) {
                    Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                    DataSnapshot secondLastLoginSnapshot = iterator.next();
                    DataSnapshot lastLoginSnapshot = iterator.next();
                    String secondLastLogin = secondLastLoginSnapshot.child("timestamp").getValue(String.class);
                    callback.onSuccess(secondLastLogin);
                } else {
                    callback.onFailure("No hay suficientes registros de inicio de sesión.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailure(databaseError.getMessage());
            }
        });
    }



    ValueEventListener gameDataListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                long count = dataSnapshot.child("count").getValue(Long.class);
                // Actualizar el valor del TextView con el valor de "count"
                //personal_record_value.setText(String.valueOf(count));
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("MainActivity", "Error al leer los datos.", databaseError.toException());
        }
    };

}