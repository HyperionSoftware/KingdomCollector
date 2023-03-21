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

    EditText editText_email;
    EditText editText_password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_email = findViewById(R.id.email_input);
        editText_password = findViewById(R.id.password_input);
        findViewById(R.id.btn_login).setOnClickListener(v -> login());
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.register_button1).setOnClickListener(v-> goToRegisterPage());
    }

    private void goToRegisterPage(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        reload();
    }

    private void login() {
        String email = editText_email.getText().toString().trim();
        String password = editText_password.getText().toString().trim();

        if (email.isEmpty()) {
            editText_email.setError("Email is required");
            editText_email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editText_password.setError("Password is required");
            editText_password.requestFocus();
            return;
        }

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
                        editText_password.setText("");
                    }
                });
    }


    private void reload(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            editText_password.setText("");
            editText_email.setText("");
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
