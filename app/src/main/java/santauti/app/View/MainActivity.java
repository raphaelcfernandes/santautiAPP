package santauti.app.View;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santauti.app.Controller.Login.APIService;
import santauti.app.Controller.Login.RestClient;
import santauti.app.Controller.Login.User;
import santauti.app.R;
import santauti.app.View.Home.HomeActivity;
//JSONObject json = new JSONObject(inputStreamAsString);
public class MainActivity extends AppCompatActivity {
    User user = new User();
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
//                user.setPassword(password.getText().toString().trim());
//                user.setUser(username.getText().toString().trim());
                user.setUser("admin");
                user.setPassword("1");
                APIService apiService =
                        RestClient.getClient(v.getContext()).create(APIService.class);

                Call<User> call = apiService.login(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.code()==200) {
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        else
                            SnackbarCreator.createText(v,"Usuario e/ou senha inválidos");
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        SnackbarCreator.createText(v,"Usuario e/ou senha inválidos");
                    }
                });
            }
        });
    }
}