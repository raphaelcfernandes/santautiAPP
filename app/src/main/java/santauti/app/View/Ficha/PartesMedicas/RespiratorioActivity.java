package santauti.app.View.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class RespiratorioActivity extends AppCompatActivity{
    private View invasivoView, naoInvasivoView;
    Spinner respiratorioSpinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respiratorio);
        findViewById(R.id.respiratorio_layout).requestFocus();
        getSupportActionBar().setTitle(R.string.Respiratorio);
        ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        invasivoView = findViewById(R.id.ventilacao_invasiva);
        naoInvasivoView = findViewById(R.id.ventilacao_nao_invasiva);
        invasivoView.setVisibility(View.GONE);
        naoInvasivoView.setVisibility(View.GONE);
        prepareRespiratorioSpinners();
        buildIntent();
    }
    private Intent buildIntent(){
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        return resultIntent;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
        return true;
    }
    private void prepareRespiratorioSpinners(){
        String[] modoVentilatorio = {"A/C,VCV","A/C,PCV","PSV","SIMV"};

        respiratorioSpinner = (Spinner) findViewById(R.id.ventilacao_invasiva_spinner);
        ArrayAdapter<String> adapterRespiratorio = new ArrayAdapter<>(RespiratorioActivity.this, android.R.layout.simple_dropdown_item_1line, modoVentilatorio);
        respiratorioSpinner.setAdapter(adapterRespiratorio);

    }

    public void respiratorioVentilacaoOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.respiratorio_invasivo:
                if (checked) {
                    naoInvasivoView.setVisibility(View.GONE);
                    invasivoView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.respiratorio_nao_invasivo:
                if (checked) {
                    invasivoView.setVisibility(View.GONE);
                    naoInvasivoView.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
