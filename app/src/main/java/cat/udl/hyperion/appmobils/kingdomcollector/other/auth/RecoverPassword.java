package cat.udl.hyperion.appmobils.kingdomcollector.other.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import cat.udl.hyperion.appmobils.kingdomcollector.R;

public class RecoverPassword extends AppCompatActivity {

    protected String myClassTag = this.getClass().getSimpleName();
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
                                    Toast.makeText(RecoverPassword.this, getString(R.string.recover_password_email_sent), Toast.LENGTH_SHORT).show();
                                    Log.d(myClassTag, "Enviado email con exito.");
                                } else {
                                    Toast.makeText(RecoverPassword.this, getString(R.string.recover_password_email_failed), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}