package santauti.app.Activities.Ficha.PartesMedicas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import santauti.app.Activities.Ficha.Generico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class RespiratorioActivity extends Generico{
    private View invasivoView, naoInvasivoView;
    Spinner respiratorioSpinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respiratorio);
        findViewById(R.id.respiratorio_layout).requestFocus();
        setToolbar(getString(R.string.Respiratorio));

        invasivoView = findViewById(R.id.ventilacao_invasiva);
        naoInvasivoView = findViewById(R.id.ventilacao_nao_invasiva);
        invasivoView.setVisibility(View.GONE);
        naoInvasivoView.setVisibility(View.GONE);

        prepareRespiratorioSpinners();

    }
    @Override
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
