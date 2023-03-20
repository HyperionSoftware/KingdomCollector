package cat.udl.hyperion.appmobils.kingdomcollector.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class LoginActivity extends AppCompatActivity {

    protected String myClassTag = this.getClass().getSimpleName();

    EditText etEmail;
    EditText etPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.email_input);
        etPassword = findViewById(R.id.password_input);
        findViewById(R.id.btn_login).setOnClickListener(v -> login());
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        reload();
    }

    private void login() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(myClassTag, "signInWithEmail:success");
                        reload();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(myClassTag, "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        etPassword.setText("");
                    }
                });
    }

    private void reload(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
