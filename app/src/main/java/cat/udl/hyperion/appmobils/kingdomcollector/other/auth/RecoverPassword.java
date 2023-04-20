package cat.udl.hyperion.appmobils.kingdomcollector.other.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class RecoverPassword extends AppCompatActivity {

    private EditText emailInput;
    private Button recoverPasswordButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);
        firebaseAuth = FirebaseAuth.getInstance();

        emailInput = findViewById(R.id.email_input);
        recoverPasswordButton = findViewById(R.id.recover_password_button);

        recoverPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    emailInput.setError("Por favor, introduce tu correo electrónico");
                    return;
                }

                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RecoverPassword.this, "Se ha enviado un correo electrónico para restablecer la contraseña", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RecoverPassword.this, "Ha habido un error al enviar el correo electrónico para restablecer la contraseña", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}