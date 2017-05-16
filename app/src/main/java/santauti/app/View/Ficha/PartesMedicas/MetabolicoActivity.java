package santauti.app.View.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class MetabolicoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolico);
        getSupportActionBar().setTitle(R.string.Metabolico);
        findViewById(R.id.activity_metabolico).requestFocus();
        ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        buildIntent();
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
        return true;
    }
    private Intent buildIntent(){
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        return resultIntent;
    }
}
