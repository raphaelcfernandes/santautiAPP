package santauti.app.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santauti.app.APIServices.APIService;
import santauti.app.APIServices.RestClient;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Activities.Home.HomeActivity;
import santauti.app.Model.User;
import santauti.app.R;

public class MainActivity extends GenericoActivity {
    private User user = new User();
    APIService apiService;
    ProgressDialog progressDialog;
    View mainView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SCREEN ELEMENTS ------------------------------------------------------------------------
        final EditText username = (EditText)findViewById(R.id.input_usuario);
        final EditText password = (EditText)findViewById(R.id.input_password);
        final Button login = (Button)findViewById(R.id.btn_login);
        findViewById(R.id.login).requestFocus();
        mainView = findViewById(android.R.id.content);
        setupUI(findViewById(R.id.main_activity));
        // -----------------------------------------------------------------------------------------
//        mAuth = FirebaseAuth.getInstance();
        apiService = RestClient.getClient(MainActivity.this).create(APIService.class);
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Log.d("ey", "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    // User is signed out
//                    Log.d("ey", "onAuthStateChanged:signed_out");
//                }
//                // ...
//            }
//        };
//        mAuth.addAuthStateListener(mAuthListener);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.Theme_AppCompat_DayNight_Dialog);

                user.setPassword(password.getText().toString().trim());
                user.setUser(username.getText().toString().trim());

                login();

                progressDialog.setIndeterminate(true);
                progressDialog.setMessage(getString(R.string.autenticando));
                progressDialog.show();
            }
        });
    }

    public void login(){
//        mAuth.signInWithEmailAndPassword("raphael.cardoso.f@gmail.com", "123456")
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d("logun", "signInWithEmail:onComplete:" + task.isSuccessful());
//                        progressDialog.dismiss();
//                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                        String name = user.getDisplayName();
//                        String email = user.getEmail();
//
//                        // The user's ID, unique to the Firebase project. Do NOT use this value to
//                        // authenticate with your backend server, if you have one. Use
//                        // FirebaseUser.getToken() instead.
//                        String uid = user.getUid();
//                        System.out.println("uid: "+uid+" name: "+name+" email: "+email+" "+user.getToken(true));
//                        // If sign in fails, display a message to the user. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in user can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            Log.w("login", "signInWithEmail:failed", task.getException());
//                            Toast.makeText(MainActivity.this, "Fail",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // ...
//                    }
//                });
        Call<User> call = apiService.login(user);
        call.enqueue(new Callback<User>() {
            @Override
            public final void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {

                if(response.code()==200 && response.body().getTipoProfissional()>1){

                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);

                    SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPrefecences),Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.acess_token),response.body().getToken());
                    editor.putInt(getString(R.string.registroMedico),response.body().getRegistro());
                    editor.putInt(getString(R.string.tipoProfissional),response.body().getTipoProfissional());
                    editor.apply();

                    startActivity(intent);
                    finish();
                }
                else if(response.code()==200 && response.body().getTipoProfissional()==1)
                    SnackbarCreator.createText(mainView,getString(R.string.perfilSemAcesso));

                else if(response.code()==400)
                    SnackbarCreator.createText(mainView, getString(R.string.usuarioSenhaIncorreto));

                progressDialog.dismiss();
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Log.d("ERROR",t.getMessage());
            }
        });
    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
}