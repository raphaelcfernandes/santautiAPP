package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Activities.SnackbarCreator;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Renal;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class RenalActivity extends GenericoActivity {
    TextInputEditText diureseTxt, pesoTxt, balancoHidricoTxt;
    RadioButton renalS,renalN;
    private int renalChecked;
    private Realm realm;
    private Ficha ficha;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_renal);
        findViewById(R.id.activity_renal).requestFocus();
        renalS = (RadioButton)findViewById(R.id.renal_sim);
        renalN = (RadioButton)findViewById(R.id.renal_nao);
        setToolbar(getString(R.string.Renal));

        diureseTxt = (TextInputEditText)findViewById(R.id.diurese);
        pesoTxt = (TextInputEditText)findViewById(R.id.peso);
        balancoHidricoTxt = (TextInputEditText)findViewById(R.id.balanco_hidrico);
        prepareNavigationButtons();
        realm = Realm.getDefaultInstance();
        ficha=getProperFicha();
        if(ficha.getRenal()!=null){
            if(ficha.getRenal().getDiurese()>=0)
                diureseTxt.setText(String.valueOf(ficha.getRenal().getDiurese()));
            if(ficha.getRenal().getBalancoHidrico()>=0)
                balancoHidricoTxt.setText(String.valueOf(ficha.getRenal().getBalancoHidrico()));
            if(ficha.getRenal().getPeso()>=0)
                pesoTxt.setText(String.valueOf(ficha.getRenal().getPeso()));
            if(ficha.getRenal().getDialise()==0)
                renalN.setChecked(true);
            else if(ficha.getRenal().getDialise()==1)
                renalS.setChecked(true);
        }
        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HematologicoActivity.class);
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
                intent = new Intent(view.getContext(), GastrointestinalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, getIntent().getIntExtra("idFicha",0), intent);
                startActivity(intent);
                exitActivityToLeft();
                verificaCamposENotificaAdapter();
                finish();
            }
        });
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
        Renal renal = realm.createObject(Renal.class);
        if(!isTextInpudEditTextEmpty(diureseTxt)) {
            renal.setDiurese(Integer.parseInt(diureseTxt.getText().toString()));
            i++;
        }
        if(!isTextInpudEditTextEmpty(pesoTxt)) {
            renal.setPeso(Integer.parseInt(pesoTxt.getText().toString()));
            i++;
        }
        if(!isTextInpudEditTextEmpty(balancoHidricoTxt)) {
            renal.setBalancoHidrico(Integer.parseInt(balancoHidricoTxt.getText().toString()));
            i++;
        }
        if(renalS.isChecked()) {
            renal.setDialise(1);
            i++;
        }
        if(renalN.isChecked()) {
            renal.setDialise(0);
            i++;
        }
        if(i==4) {
            Ficha r = getProperFicha();
            r.setRenal(renal);
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

    public void renalOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.renal_sim:
                SnackbarCreator.camposAPreencher(view);
                break;
            case R.id.renal_nao:
                SnackbarCreator.avaliacaoGeradaAutomaticamente(view);
                break;
        }
    }

}
