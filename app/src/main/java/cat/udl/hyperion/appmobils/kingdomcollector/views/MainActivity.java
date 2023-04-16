package cat.udl.hyperion.appmobils.kingdomcollector.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import cat.udl.hyperion.appmobils.kingdomcollector.Models.CardCollection;
import cat.udl.hyperion.appmobils.kingdomcollector.R;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    protected String myClassTag = this.getClass().getSimpleName();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference gameDataRef = database.getReference("game_data");

    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
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

        //Music
        MediaPlayer mp = MediaPlayer.create(this,R.raw.check_it_out_now);
        mp.start();


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;

        getPenultimateLogin(new PenultimateLoginCallback() {
            @Override
            public void onFailure(String usuario_no_autenticado) {
                Toast.makeText(MainActivity.this, "Error: " + usuario_no_autenticado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String secondLastLogin) {
                if (secondLastLogin != null) {
                    Log.d(myClassTag,"LastLogin:" + secondLastLogin);
                    Toast.makeText(MainActivity.this, "Bienvenido " + user.getDisplayName() + " tu último inicio de sesión fue: " + secondLastLogin, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Bienvenido " + user.getDisplayName() + ", no se encontraron registros de inicio de sesión anteriores.", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gameDataRef = database.getReference("game_data");

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userGameDataRef = gameDataRef.child(userId);

        // Increment the count by 1
        userGameDataRef.child("count").setValue(ServerValue.increment(1));

        startActivity(intent);
    }

    public void setBtn_collection(){
        Intent intent = new Intent(this, CardCollectionActivity.class);
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