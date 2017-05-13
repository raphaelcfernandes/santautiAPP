package santauti.app.View.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 10-May-17.
 */

public class HemodinamicoActivity extends AppCompatActivity{
    private int x=0;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        x=intent.getIntExtra("Position",x);
        setContentView(R.layout.hemodinamico);
        buildIntent(x);
    }


    private Intent buildIntent(int x){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("position",x);
        setResult(RESULT_OK, resultIntent);
        return resultIntent;
    }
}
