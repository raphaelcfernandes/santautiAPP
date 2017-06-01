package santauti.app.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import santauti.app.Activities.Home.HomeActivity;
import santauti.app.R;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sp;
    String access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp = getSharedPreferences(getString(R.string.sharedPrefecences), 0);


        access_token = sp.getString(getString(R.string.acess_token), "");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               startNextScreen();
            }
        }, 1000);


    }

    public void startNextScreen(){
        if(!access_token.equals("")){
            Intent it = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(it);

        } else{

            Intent it = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(it);

        }

        finish();
    }
}
