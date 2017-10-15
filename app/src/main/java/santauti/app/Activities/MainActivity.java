package santauti.app.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Activities.Home.HomeActivity;
import santauti.app.Model.User;
import santauti.app.R;

public class MainActivity extends GenericoActivity {
    private User user = new User();
    ProgressDialog progressDialog;
    View mainView;
    private FirebaseAuth mAuth;
    private TextInputEditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SCREEN ELEMENTS ------------------------------------------------------------------------
        email = (TextInputEditText) findViewById(R.id.input_usuario);
        password = (TextInputEditText)findViewById(R.id.input_password);
        final Button login = (Button)findViewById(R.id.btn_login);
        findViewById(R.id.login).requestFocus();
        mainView = findViewById(android.R.id.content);
        //setupUI(findViewById(R.id.main_activity));
        // -----------------------------------------------------------------------------------------
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.Theme_AppCompat_DayNight_Dialog);

                user.setPassword(password.getText().toString().trim());
                user.setEmail(email.getText().toString().trim());

                login();

                progressDialog.setIndeterminate(true);
                progressDialog.setMessage(getString(R.string.autenticando));
                progressDialog.show();
            }
        });
    }

    public void login() {
        mAuth.signInWithEmailAndPassword("udiacf@gmail.com","123456")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            progressDialog.dismiss();
                            startActivity(intent);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            SnackbarCreator.createText(mainView, getString(R.string.usuarioSenhaIncorreto));
                        }

                    }
                });
    }
}