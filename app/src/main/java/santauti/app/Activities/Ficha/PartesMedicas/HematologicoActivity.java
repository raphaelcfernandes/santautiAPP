package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Activities.SnackbarCreator;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Hematologico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class HematologicoActivity extends GenericoActivity {
    private Spinner tromboprofilaxiaSpinner;
    private View tromboprofilaxia;
    private RadioButton hemogramaS,hemogramaN,tromboprofilaxiaS,tromboprofilaxiaN;
    private Realm realm;
    private ArrayAdapter<String> adapterProfilaxia;
    private MyAnimation myAnimation = new MyAnimation();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hematologico);
        tromboprofilaxia = findViewById(R.id.tromboprofilaxia_layout);
        prepareNavigationButtons();
        hemogramaS = (RadioButton)findViewById(R.id.hemograma_sim);
        hemogramaN = (RadioButton)findViewById(R.id.hemograma_nao);
        tromboprofilaxiaS = (RadioButton)findViewById(R.id.tromboprofilaxia_sim);
        tromboprofilaxiaN = (RadioButton)findViewById(R.id.tromboprofilaxia_nao);

        setToolbar(getString(R.string.Hematologico));

        prepareHematologicoSpinners();

        realm = Realm.getDefaultInstance();

        Ficha ficha = getProperFicha();
        if(ficha.getHematologico()!=null){
            if(ficha.getHematologico().getTromboprofilaxia()==1){
                tromboprofilaxia.setVisibility(View.VISIBLE);
                tromboprofilaxiaS.setChecked(true);
                int spinnerPosition = adapterProfilaxia.getPosition(ficha.getHematologico().getTipoMedicamento());
                tromboprofilaxiaSpinner.setSelection(spinnerPosition);
            }
            else {
                tromboprofilaxiaN.setChecked(true);
                tromboprofilaxia.setVisibility(View.GONE);
            }
            if(ficha.getHematologico().getHemograma()==1)
                hemogramaS.setChecked(true);
            else
                hemogramaN.setChecked(true);
        }
        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), EndocrinoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, getIntent().getIntExtra("idFicha",0), intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RenalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, getIntent().getIntExtra("idFicha",0), intent);
                startActivity(intent);
                exitActivityToLeft();
                verificaCamposENotificaAdapter();
                finish();
            }
        });
    }

    private void prepareHematologicoSpinners(){
        String[] nivelConsciencia = {defaultSpinnerString,"Heparina Fracionada","Heparina não Fracionada"};

        tromboprofilaxiaSpinner = (Spinner) findViewById(R.id.tromboprofilaxia_spinner);
        adapterProfilaxia = new ArrayAdapter<String>(HematologicoActivity.this, android.R.layout.simple_dropdown_item_1line, nivelConsciencia){
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
        tromboprofilaxiaSpinner.setAdapter(adapterProfilaxia);
    }

    public void hematologicoProfilaxiaOnRadioButtonClicked(View view){
        switch(view.getId()) {
            case R.id.tromboprofilaxia_sim:
                SnackbarCreator.camposAPreencher(view);
                if(!tromboprofilaxia.isShown())
                    myAnimation.slideDownView(this,tromboprofilaxia);
                break;
            case R.id.tromboprofilaxia_nao:
                SnackbarCreator.camposAPreencher(view);
                if(tromboprofilaxia.isShown()) {
                    myAnimation.slideDownView(this, tromboprofilaxia);
                }
                break;
        }
    }

    public void hematologicoOnRadioButtonClicked(View view){
        switch(view.getId()) {
            case R.id.hemograma_sim:
                SnackbarCreator.camposAPreencher(view);
                break;
            case R.id.hemograma_nao:
                SnackbarCreator.avaliacaoGeradaAutomaticamente(view);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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

    @Override
    public void onBackPressed(){
        verificaCamposENotificaAdapter();
        finish();
    }

    private void verificaCamposENotificaAdapter(){
        realm.beginTransaction();
        int i=0;
        Hematologico hematologico = realm.createObject(Hematologico.class);
        if(tromboprofilaxiaS.isChecked()){
            hematologico.setTromboprofilaxia(1);
            if(!tromboprofilaxiaSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
                hematologico.setTipoMedicamento(tromboprofilaxiaSpinner.getSelectedItem().toString());
                i++;
            }
        }
        if(tromboprofilaxiaN.isChecked()){
            hematologico.setTromboprofilaxia(0);
            hematologico.setTipoMedicamento(null);
            i++;
        }
        if(hemogramaS.isChecked()) {
            hematologico.setHemograma(1);
            i++;
        }
        if(hemogramaN.isChecked()) {
            hematologico.setHemograma(0);
            i++;
        }

        if(i==2) {
            Ficha r = getProperFicha();
            r.setHematologico(hematologico);
            realm.copyToRealmOrUpdate(r);
            changeCardColor();
        }
        realm.commitTransaction();
    }
}
