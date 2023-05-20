package cat.udl.hyperion.appmobils.kingdomcollector.other;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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

import java.util.Iterator;
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
    DatabaseReference gameDataRef = database.getReference("winner_count");

    String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    DatabaseReference userGameDataRef = gameDataRef.child(userId);

    // Nombre y puntuación máxima del usuario con más puntos.
    private String highestScoringUserID;
    private long highestScore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadHighestScore();

        //Button Settings.
        //findViewById(R.id.btn_settings).setOnClickListener(view -> setBtn_aboutus());

        //Button AboutUs.
        //findViewById(R.id.btn_terms).setOnClickListener(view -> setBtn_settings());

        //Button Config.
        findViewById(R.id.button_config).setOnClickListener(view-> goToSettings());

        //Button Start.
        findViewById(R.id.btn_start).setOnClickListener(view -> setBtn_start());

        //Button Collection.
        findViewById(R.id.btn_collection).setOnClickListener(view -> setBtn_collection());


        // Button Logout.
        findViewById(R.id.btn_logout).setOnClickListener(v -> logout());

        //TODO: Borrar cuándo estén todas las cartas ya incluidas en firebase storage.
        //Button actualizar.
        findViewById(R.id.actualizar).setOnClickListener(view -> setBtn_actualizar());


        //Music
        mp = MediaPlayer.create(this,R.raw.check_it_out_now);
        mp.start();


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;

        userGameDataRef.addValueEventListener(gameDataListener);

        getPenultimateLogin(new PenultimateLoginCallback() {
            @Override
            public void onFailure(String usuario_no_autenticado) {
                //TODO: DA1. Funcionar amb valors de strings.
                Toast.makeText(MainActivity.this, "Error: " + usuario_no_autenticado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String secondLastLogin) {
                if (secondLastLogin != null) {
                    Log.d(myClassTag,"LastLogin:" + secondLastLogin);
                    //TODO: DA1. Funcionar amb valors de strings.
                    Toast.makeText(MainActivity.this, "Bienvenido " + user.getDisplayName() + " tu último inicio de sesión fue: " + secondLastLogin, Toast.LENGTH_SHORT).show();
                } else {
                    //TODO: DA1. Funcionar amb valors de strings.
                    Toast.makeText(MainActivity.this, "Bienvenido " + user.getDisplayName() + ", no se encontraron registros de inicio de sesión anteriores.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void goToSettings() {
        Intent intent = new Intent(this, Config.class);
        startActivity(intent);

    }

    private void setBtn_actualizar() {
        Intent intent = new Intent(this, addingcards.class);
        startActivity(intent);
    }

    // Metodo para obtener las veces que el usuario ha ganado
    @Override
    protected void onStart() {
        super.onStart();
        userGameDataRef.addValueEventListener(gameDataListener);

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


    public void startNewActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
    public void setBtn_aboutus(){
        startNewActivity(AboutUs.class);
    }

    public void setBtn_settings(){
        startNewActivity(TermsAndConditions.class);
    }

    public void setBtn_start(){
        startNewActivity(GameActivity.class);
    }

    public void setBtn_collection(){
        startNewActivity(CollectionActivity.class);
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
                Long count = dataSnapshot.getValue(Long.class);
                // Actualizar el valor del TextView con el valor de "count"
                TextView winnerCountText = findViewById(R.id.winner_count_value);
                if (count != null) {
                    winnerCountText.setText(String.valueOf(count));
                } else {
                    winnerCountText.setText("0");
                }
            } else {
                // El nodo no existe o el usuario no ha ganado aún, puedes manejarlo de la forma que prefieras.
                TextView winnerCountText = findViewById(R.id.winner_count_value);
                winnerCountText.setText("0");
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.w("MainActivity", "Error al leer los datos.", databaseError.toException());
        }
    };

    // Método para obtener el score más alto
    private void loadHighestScore() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("winner_count");

        TextView highestScoreText = findViewById(R.id.highestScoreText);

        reference.orderByValue().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    highestScoringUserID = snapshot.getKey();
                    highestScore = snapshot.getValue(Long.class);
                }

                highestScoreText.setText(String.valueOf(highestScore));
                Log.w(TAG, "El score más alto es: " + highestScore);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejo del error.
                Log.w(TAG, "Error al cargar el score más alto.", databaseError.toException());
            }
        });
    }


}