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


        //Button Config.
        findViewById(R.id.button_config).setOnClickListener(view-> goToSettings());

        //Button Start.
        findViewById(R.id.btn_start).setOnClickListener(view -> setBtn_start());

        //Button Collection.
        findViewById(R.id.btn_collection).setOnClickListener(view -> setBtn_collection());


        // Button Logout.
        findViewById(R.id.btn_logout).setOnClickListener(v -> logout());


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
                Toast.makeText(MainActivity.this, getString(R.string.error_usuario_no_autenticado) + usuario_no_autenticado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String secondLastLogin) {
                if (secondLastLogin != null) {
                    Log.d(myClassTag,"LastLogin:" + secondLastLogin);
                    String welcomeMessage = String.format(getString(R.string.bienvenido), user.getDisplayName(), secondLastLogin);
                    Toast.makeText(MainActivity.this, welcomeMessage, Toast.LENGTH_SHORT).show();
                } else {
                    String welcomeNoRecordMessage = String.format(getString(R.string.bienvenido_sin_registro), user.getDisplayName());
                    Toast.makeText(MainActivity.this, welcomeNoRecordMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



    @Override
    protected void onStart() {
        super.onStart();
        userGameDataRef.addValueEventListener(gameDataListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pausar la música
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

    public void setBtn_start(){
        startNewActivity(PlayModeActivity.class);
    }


    public void setBtn_collection(){
        startNewActivity(CollectionActivity.class);
    }

    private void goToSettings() {startNewActivity(Config.class);}




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
                //Actualizar el valor del TextView con el valor de "count"
                TextView winnerCountText = findViewById(R.id.winner_count_value);
                if (count != null) {
                    winnerCountText.setText(String.valueOf(count));
                } else {
                    winnerCountText.setText("0");
                }
            } else {
                // El nodo no existe o el usuario no ha ganado aún.
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

        reference.orderByValue().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    highestScoringUserID = snapshot.getKey();
                    highestScore = snapshot.getValue(Long.class);
                }

                //highestScoreText.setText(String.valueOf(highestScore));
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