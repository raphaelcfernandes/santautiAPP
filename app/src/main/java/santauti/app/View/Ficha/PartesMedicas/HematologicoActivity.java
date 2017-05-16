package santauti.app.View.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import santauti.app.R;
import santauti.app.View.SnackbarCreator;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class HematologicoActivity extends AppCompatActivity {
    private Spinner tromboprofilaxiaSpinner;
    private View tromboprofilaxia;
    private boolean visibility=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_hematologico);
        getSupportActionBar().setTitle(R.string.Hematologico);
        tromboprofilaxia = findViewById(R.id.tromboprofilaxia_layout);
        ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        prepareHematologicoSpinners();
        buildIntent();
    }
    private void prepareHematologicoSpinners(){
        String[] nivelConsciencia = {"Heparina Fracionada","Heparina não Fracionada"};

        tromboprofilaxiaSpinner = (Spinner) findViewById(R.id.tromboprofilaxia_spinner);
        ArrayAdapter<String> adapterProfilaxia = new ArrayAdapter<>(HematologicoActivity.this, android.R.layout.simple_dropdown_item_1line, nivelConsciencia);
        tromboprofilaxiaSpinner.setAdapter(adapterProfilaxia);

    }
    public void hematologicoProfilaxiaOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.hematologico_profilaxia_sim:
                if (checked) {
                    SnackbarCreator.camposAPreencher(view);
                    if(!visibility)
                        tromboprofilaxia.setVisibility(View.VISIBLE);
                    visibility=true;
                }
                break;
            case R.id.hematologico_profilaxia_nao:
                if (checked) {
                    SnackbarCreator.camposAPreencher(view);
                    if(visibility)
                        tromboprofilaxia.setVisibility(View.GONE);
                    visibility=false;
                }
                break;
        }
    }
    public void hematologicoOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.hematologico_sim:
                if (checked)
                    SnackbarCreator.camposAPreencher(view);
                break;
            case R.id.hematologico_nao:
                if (checked)
                    SnackbarCreator.avaliacaoGeradaAutomaticamente(view);
                break;
        }
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
