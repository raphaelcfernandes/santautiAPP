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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santauti.app.APIServices.APIService;
import santauti.app.APIServices.RestClient;
import santauti.app.Activities.Home.HomeActivity;
import santauti.app.Model.User;
import santauti.app.R;
//JSONObject json = new JSONObject(inputStreamAsString);

public class MainActivity extends AppCompatActivity {
    private User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText username = (EditText)findViewById(R.id.input_usuario);
        final EditText password = (EditText)findViewById(R.id.input_password);
        Button login = (Button)findViewById(R.id.btn_login);
        findViewById(R.id.login).requestFocus();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.Theme_AppCompat_DayNight_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Autenticando...");
                progressDialog.show();

                APIService apiService =
                        RestClient.getClient(v.getContext()).create(APIService.class);
                user.setPassword("1");
                user.setUser("udiacf");
//                user.setPassword(password.getText().toString().trim());
//                user.setUser(username.getText().toString().trim());
                Call<User> call = apiService.login(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public final void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if(response.code()==200 && response.body().getTipoProfissional()>1){

                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);

                            SharedPreferences sharedPref = getSharedPreferences(v.getContext().getString(R.string.sharedPrefecences),Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(v.getContext().getString(R.string.acess_token),response.body().getToken());
                            editor.putInt(v.getContext().getString(R.string.registroMedico),response.body().getRegistro());
                            editor.putInt("tipoProfissional",response.body().getTipoProfissional());
                            editor.apply();

                            //intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else if(response.code()==200 && response.body().getTipoProfissional()==1)
                            SnackbarCreator.createText(v, "Perfil sem acesso a esta área");
                        else if(response.code()==400)
                            SnackbarCreator.createText(v, "Usuário e/ou senha incorretos");

                        progressDialog.dismiss();
                    }
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                        Log.d("ERROR",t.getMessage());
                    }
                });
            }
        });
    }

    private void setUser(String token,int tipoProfissional, int registro){
        this.user.setPassword(null);
        this.user.setUser(null);
        this.user.setTipoProfissional(tipoProfissional);
        this.user.setRegistro(registro);
        this.user.setToken(token);
    }
}