package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class GastrointestinalActivity extends AppCompatActivity {
    Spinner formatoSpinner,tensaoSpinner,ruidosSpinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastrointestinal);
        findViewById(R.id.gastrointestinal_layout).requestFocus();
        Toolbar tbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.GastroIntestinal);
        toolbar.setDisplayHomeAsUpEnabled(true);
        prepareGastrointestinalSpinners();
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
    private void prepareGastrointestinalSpinners(){
        String[] ruidos = {"Aumentado","Presente","Normal","Reduzido","Ausente"};
        String[] tensao = {"Hipertenso","Normotenso","Flacido"};
        String[] formato = {"Plano","Globoso","Semigloboso","Escavado"};

        ruidosSpinner = (Spinner) findViewById(R.id.gastrointestinal_ruidos);
        ArrayAdapter<String> adapterRuido = new ArrayAdapter<>(GastrointestinalActivity.this, android.R.layout.simple_dropdown_item_1line, ruidos);
        ruidosSpinner.setAdapter(adapterRuido);

        tensaoSpinner = (Spinner) findViewById(R.id.gastrointestinal_tensao);
        ArrayAdapter<String> adapterTensao = new ArrayAdapter<>(GastrointestinalActivity.this, android.R.layout.simple_dropdown_item_1line, tensao);
        tensaoSpinner.setAdapter(adapterTensao);

        formatoSpinner = (Spinner) findViewById(R.id.formato_abdome);
        ArrayAdapter<String> adapterFormato = new ArrayAdapter<>(GastrointestinalActivity.this, android.R.layout.simple_dropdown_item_1line, formato);
        formatoSpinner.setAdapter(adapterFormato);
    }

    public void gastrointestinalOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.vcm_presente:
                if (checked) {
                    System.out.println("presente");
                }
                break;
            case R.id.vcm_ausente:
                if (checked) {
                    System.out.println("ausente");
                }
                break;
        }
    }
}
