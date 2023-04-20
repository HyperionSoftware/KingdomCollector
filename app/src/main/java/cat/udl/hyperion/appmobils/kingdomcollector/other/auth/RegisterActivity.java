package cat.udl.hyperion.appmobils.kingdomcollector.other.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class RegisterActivity extends AppCompatActivity {
    protected String myClassTag = this.getClass().getSimpleName();
    private FirebaseAuth mAuth;

    private FirebaseUser user;
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
                        // El registro fue exitoso, enviar correo de verificación
                        FirebaseUser user = mAuth.getCurrentUser();
                        user.sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "Se ha enviado un correo de verificación a su cuenta de correo electrónico. Por favor, confirme su correo electrónico antes de iniciar sesión.", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                        // Actualizar el nombre de usuario del usuario
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(username)
                                .build();
                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Log.d(myClassTag, "Perfil actualizado correctamente con el username.");
                                    }
                                });
                        // Ir a la pantalla de inicio de sesión
                        goToLoginPage();
                    } else {
                        // El registro falló, mostrar un mensaje de error
                        Toast.makeText(RegisterActivity.this, "No se pudo crear la cuenta. Por favor, inténtelo de nuevo más tarde.", Toast.LENGTH_LONG).show();
                        Log.d(myClassTag, "Error al crear la cuenta.", task.getException());
                    }
                });
    }

    private void goToLoginPage(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}