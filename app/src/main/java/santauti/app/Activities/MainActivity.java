package santauti.app.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santauti.app.Activities.Home.HomeActivity;
import santauti.app.Adapters.APIService;
import santauti.app.Adapters.RestClient;
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
                APIService apiService =
                        RestClient.getClient(v.getContext()).create(APIService.class);
                user.setPassword("1");
                user.setUser("udiacf");
                Call<User> call = apiService.login(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public final void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if(response.code()==200 && response.body().getTipoProfissional()>1){
                            setUser(response.body().getToken(),response.body().getTipoProfissional(),
                                    response.body().getRegistro());
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("UserObject",user);
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
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