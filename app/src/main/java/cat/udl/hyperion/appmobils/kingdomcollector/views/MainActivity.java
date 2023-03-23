package cat.udl.hyperion.appmobils.kingdomcollector.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cat.udl.hyperion.appmobils.kingdomcollector.R;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    protected String myClassTag = this.getClass().getSimpleName();

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

        getLastLogin(new LastLoginCallback() {
            @Override
            public void onSuccess(String lastLogin) {
                if (lastLogin != null) {
                    Log.d(myClassTag,"LastLogin:" + lastLogin);
                    Toast.makeText(MainActivity.this, "Bienvenido " + user.getDisplayName() + " tu último inicio de sesión fue: " + lastLogin, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Bienvenido " + user.getDisplayName() + ", no se encontraron registros de inicio de sesión anteriores.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(MainActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
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
}