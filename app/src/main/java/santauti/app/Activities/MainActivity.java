package santauti.app.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import santauti.app.R;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    View mainView;
    private FirebaseAuth mAuth;
    private TextInputEditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SCREEN ELEMENTS ------------------------------------------------------------------------
        email = findViewById(R.id.input_usuario);
        password = findViewById(R.id.input_password);
        final Button login = findViewById(R.id.btn_login);
        findViewById(R.id.login).requestFocus();
        mainView = findViewById(android.R.id.content);
//        setupUI(findViewById(R.id.main_activity));
        // -----------------------------------------------------------------------------------------
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(v -> {
            progressDialog = new ProgressDialog(MainActivity.this,
                    R.style.Theme_AppCompat_DayNight_Dialog);
            login();
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.autenticando));
            progressDialog.show();
        });
    }

    public void login() {
        mAuth.signInWithEmailAndPassword("raphael.cardoso.f@gmail.com", "123456")
                .addOnCompleteListener(this, task -> {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        System.out.println("success");
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        SnackbarCreator.createText(mainView, getString(R.string.usuarioSenhaIncorreto));
                    }
                });
    }
}