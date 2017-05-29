package santauti.app.Activities.Ficha.PartesMedicas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Gastrointestinal;
import santauti.app.Model.Ficha.Renal;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class GastrointestinalActivity extends GenericoActivity {
    Spinner formatoSpinner,tensaoSpinner,ruidosSpinner;
    private Realm realm;
    RadioButton vcmPresente,vcmAusente;
    private ArrayAdapter<String> adapterRuido,adapterFormato,adapterTensao;
    private int spinnerPosition;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastrointestinal);
        findViewById(R.id.gastrointestinal_layout).requestFocus();
        setToolbar(getString(R.string.GastroIntestinal));

        prepareGastrointestinalSpinners();
        vcmPresente = (RadioButton)findViewById(R.id.vcm_presente);
        vcmAusente = (RadioButton)findViewById(R.id.vcm_ausente);

        realm = Realm.getDefaultInstance();

        Ficha ficha = getProperFicha();
        if(ficha.getGastrointestinal()!=null){
            if(ficha.getGastrointestinal().getVcm()==1)
                vcmPresente.setChecked(true);
            else
                vcmAusente.setChecked(true);
            spinnerPosition = adapterFormato.getPosition(ficha.getGastrointestinal().getFormato());
            formatoSpinner.setSelection(spinnerPosition);

            spinnerPosition = adapterRuido.getPosition(ficha.getGastrointestinal().getRuidos());
            ruidosSpinner.setSelection(spinnerPosition);

            spinnerPosition = adapterTensao.getPosition(ficha.getGastrointestinal().getTensao());
            tensaoSpinner.setSelection(spinnerPosition);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onBackPressed(){
        verificaCamposENotificaAdapter();
        finish();
    }

    private void verificaCamposENotificaAdapter() {
        realm.beginTransaction();
        int i=0;
        Gastrointestinal gastrointestinal = realm.createObject(Gastrointestinal.class);
        if(!formatoSpinner.getSelectedItem().toString().equals(defaultSpinnerString)) {
            gastrointestinal.setFormato(formatoSpinner.getSelectedItem().toString());
            i++;
        }
        if(!tensaoSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
            gastrointestinal.setTensao(tensaoSpinner.getSelectedItem().toString());
            i++;
        }
        if(!ruidosSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
            gastrointestinal.setRuidos(ruidosSpinner.getSelectedItem().toString());
            i++;
        }
        if(vcmPresente.isChecked()){
            gastrointestinal.setVcm(1);
            i++;
        }
        else{
            gastrointestinal.setVcm(0);
            i++;
        }
        if(i==4) {
            Ficha r = getProperFicha();
            r.setGastrointestinal(gastrointestinal);
            realm.copyToRealmOrUpdate(r);
            changeCardColor();
        }
        realm.commitTransaction();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home) {
            verificaCamposENotificaAdapter();
            finish();
        }
        return true;
    }

    private void prepareGastrointestinalSpinners(){
        String[] ruidos = {defaultSpinnerString,"Aumentado","Presente","Normal","Reduzido","Ausente"};
        String[] tensao = {defaultSpinnerString,"Hipertenso","Normotenso","Flacido"};
        String[] formato = {defaultSpinnerString,"Plano","Globoso","Semigloboso","Escavado"};

        ruidosSpinner = (Spinner) findViewById(R.id.gastrointestinal_ruidos);
        adapterRuido = new ArrayAdapter<String>(GastrointestinalActivity.this, android.R.layout.simple_dropdown_item_1line, ruidos){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        ruidosSpinner.setAdapter(adapterRuido);

        tensaoSpinner = (Spinner) findViewById(R.id.gastrointestinal_tensao);
        adapterTensao = new ArrayAdapter<String>(GastrointestinalActivity.this, android.R.layout.simple_dropdown_item_1line, tensao){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        tensaoSpinner.setAdapter(adapterTensao);

        formatoSpinner = (Spinner) findViewById(R.id.formato_abdome);
        adapterFormato = new ArrayAdapter<String>(GastrointestinalActivity.this, android.R.layout.simple_dropdown_item_1line, formato){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        formatoSpinner.setAdapter(adapterFormato);
    }

    public void gastrointestinalOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.vcm_presente:
                break;
            case R.id.vcm_ausente:
                break;
        }
    }
}
