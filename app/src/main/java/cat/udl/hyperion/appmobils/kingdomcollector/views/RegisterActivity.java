package cat.udl.hyperion.appmobils.kingdomcollector.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class RegisterActivity extends AppCompatActivity {
    protected String myClassTag = this.getClass().getSimpleName();
    private FirebaseAuth mAuth;
    EditText editText_email;
    EditText editText_password;
    EditText editText_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editText_email = findViewById(R.id.email_input);
        editText_password = findViewById(R.id.password_input);
        editText_username = findViewById(R.id.name_input);
        findViewById(R.id.register_button).setOnClickListener(v -> register());


    }
    private void register() {
        String email = editText_email.getText().toString().trim();
        String password = editText_password.getText().toString().trim();
        String username = editText_username.getText().toString().trim();

        // Verificar que se han ingresado valores válidos
        if (email.isEmpty()) {
            editText_email.setError("Email is required");
            Log.d(myClassTag, "Falta introducir el correo electrónico.");
            editText_email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editText_password.setError("Password is required");
            Log.d(myClassTag, "Falta introducir la contraseña.");
            editText_password.requestFocus();
            return;
        }

        if (username.isEmpty()) {
            editText_username.setError("Username is required");
            Log.d(myClassTag, "Falta introducir el nombre de usuario.");
            editText_username.requestFocus();
            return;
        }

        // Registrar al usuario en Firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Registro exitoso, actualizar el nombre de usuario del usuario
                        Log.d(myClassTag, "registerInWithEmail:success");
                        mAuth.getCurrentUser().updateProfile(new UserProfileChangeRequest.Builder()
                                        .setDisplayName(username)
                                        .build())
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Log.d(myClassTag, "actualitzationInWithEmail:success");
                                        // Actualización del perfil exitosa, hacer algo en consecuencia (por ejemplo, ir a otra actividad)
                                        Toast.makeText(RegisterActivity.this, "Registrado correctamente.",
                                                Toast.LENGTH_SHORT).show();
                                        goToLoginPage();

                                    } else {
                                        Log.d(myClassTag, "Error en la actualización del perfil.");
                                        Toast.makeText(RegisterActivity.this, "Actualitzation of profile failed.",
                                                Toast.LENGTH_SHORT).show();
                                        // Error en la actualización del perfil, mostrar un mensaje de error o hacer algo en consecuencia
                                    }
                                });
                    } else {
                        // Error en el registro, mostrar un mensaje de error o hacer algo en consecuencia
                        Log.d(myClassTag, "Error en el registro.");
                        Toast.makeText(RegisterActivity.this, "RegisterActivity failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void goToLoginPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}