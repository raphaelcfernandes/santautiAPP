package santauti.app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import santauti.app.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               startNextScreen();
            }
        }, 1000);


    }

    public void startNextScreen(){
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent it = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(it);

        } else{
            Intent it = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(it);
        }
        finish();
    }
}
