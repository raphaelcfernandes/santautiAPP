package santauti.app.Activities.Ficha.PartesMedicas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import santauti.app.Activities.Ficha.Generico;
import santauti.app.Activities.SnackbarCreator;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class HematologicoActivity extends Generico {
    private Spinner tromboprofilaxiaSpinner;
    private View tromboprofilaxia;
    private boolean visibility=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_hematologico);
        Toolbar tbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        tromboprofilaxia = findViewById(R.id.tromboprofilaxia_layout);
        setToolbar(getString(R.string.Hematologico));

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
        return true;
    }
}
