package santauti.app.View.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class GastrointestinalActivity extends AppCompatActivity {
    Spinner exameAbdomeSpinner,formatoSpinner,tensaoSpinner,ruidosSpinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastrointestinal);
        findViewById(R.id.gastrointestinal_layout).requestFocus();
        getSupportActionBar().setTitle(R.string.GastroIntestinal);
        prepareGastrointestinalSpinners();
        buildIntent();
    }

    private Intent buildIntent(){
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        return resultIntent;
    }
    private void prepareGastrointestinalSpinners(){
        String[] ruidos = {"Aumentado","Presente","Normal","Reduzido","Ausente"};
        String[] tensao = {"Hipertenso","Normotenso","Flacido"};

        ruidosSpinner = (Spinner) findViewById(R.id.gastrointestinal_ruidos);
        ArrayAdapter<String> adapterRuido = new ArrayAdapter<>(GastrointestinalActivity.this, android.R.layout.simple_dropdown_item_1line, ruidos);
        ruidosSpinner.setAdapter(adapterRuido);

        tensaoSpinner = (Spinner) findViewById(R.id.gastrointestinal_tensao);
        ArrayAdapter<String> adapterTensao = new ArrayAdapter<>(GastrointestinalActivity.this, android.R.layout.simple_dropdown_item_1line, tensao);
        tensaoSpinner.setAdapter(adapterTensao);
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
