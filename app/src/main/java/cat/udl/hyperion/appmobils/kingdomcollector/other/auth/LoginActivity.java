package cat.udl.hyperion.appmobils.kingdomcollector.other.auth;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cat.udl.hyperion.appmobils.kingdomcollector.R;
import cat.udl.hyperion.appmobils.kingdomcollector.other.MainActivity;
import cat.udl.hyperion.appmobils.kingdomcollector.viewmodels.RecoverPassword;

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
        findViewById(R.id.forgot_password_text).setOnClickListener(v-> goToRecoverPasswordPage());
    }

    private void goToRecoverPasswordPage() {
        Intent intent = new Intent(this, RecoverPassword.class);
        startActivity(intent);
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
            String emailEmpty = getString(R.string.emailEmpty);
            editText_email.setError(emailEmpty);
            editText_email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            String passwordRequired = getString(R.string.passwordRequired);
            editText_password.setError(passwordRequired);
            editText_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(myClassTag, "signInWithEmail:success");
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if(currentUser.isEmailVerified()){
                            reload();
                        }
                        else{
                            String verifyEmail = getString(R.string.verifyEmail);
                            Toast.makeText(LoginActivity.this, verifyEmail,
                                    Toast.LENGTH_SHORT).show();
                            Log.d(myClassTag, "signInFailed:EmailNotConfirmed");
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(myClassTag, "signInWithEmail:failure", task.getException());
                        String authFailed = getString(R.string.authFailed);
                        Toast.makeText(LoginActivity.this, authFailed,
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
            saveLoginDatabase();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
    private void saveLoginDatabase(){
        //Obtiene el usuario actual de FirebaseAuth.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            //Crea una referencia a la base de datos en tiempo real de Firebase en la ruta "login_records/{userId}".
            DatabaseReference loginRef = FirebaseDatabase.getInstance().getReference("login_records").child(userId);
            //Obtiene la hora actual y la formatea como una cadena de texto.
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            //Crea un Map con la información requerida: correo electrónico del usuario y la hora de inicio de sesión.
            Map<String, Object> loginData = new HashMap<>();
            loginData.put("timestamp", timestamp);
            loginData.put("email", user.getEmail());

            //Guarda la información en la base de datos utilizando la función push()
            //para generar una nueva entrada en la lista de registros de inicio de sesión para ese usuario.
            loginRef.push().setValue(loginData)
                    .addOnSuccessListener(aVoid -> {
                        // Registro de inicio de sesión guardado exitosamente
                        Log.d(myClassTag, "Se ha guardado el inicio de sesión en la database realtime de Firebase");
                    })
                    .addOnFailureListener(e -> {
                        Log.d(myClassTag, "No se ha podido guardar el inicio de sesión en la database realtime de Firebase");
                        // Error al guardar el registro de inicio de sesión
                    });
        }
    }
}