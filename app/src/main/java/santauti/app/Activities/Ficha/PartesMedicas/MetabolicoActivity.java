package santauti.app.Activities.Ficha.PartesMedicas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class MetabolicoActivity extends GenericoActivity {
    TextInputEditText gasometrialArterial;
    private int i=0;
    private Realm realm;
    private int gasometriaArterialInput;
    private int idFicha;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolico);
        findViewById(R.id.activity_metabolico).requestFocus();
        setToolbar(this.getString(R.string.Metabolico));

        gasometrialArterial = (TextInputEditText)findViewById(R.id.gasometrial_arterial);
        gasometrialArterial.addTextChangedListener(textWatcher);
        idFicha=getIntent().getIntExtra("idFicha",0);

        realm = Realm.getDefaultInstance();

        if(getGasometria()!=-1) {
            gasometrialArterial.setText(String.valueOf(getGasometria()));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home) {
            verificaCamposENotificaAdapter();
            finish();
        }
        return true;
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable editable) {
            if(gasometrialArterial.getText().toString().length()>0)
                gasometriaArterialInput = (Integer.parseInt(gasometrialArterial.getText().toString()));
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };

    /**
     * Verifica se existe registro da Gasometria previamente. Utilizado para setar o editText caso o usuario tenha retornado a esta ficha
     * Caso nao exista registro, editText ficará vazio à espera de new input.
     * @return valor da gasometria preenchido pelo usuario previamente, ou retorna -1 se nao foi preenchido
     */
    private int getGasometria(){
        Ficha ficha = getProperFicha();
        if(ficha.getMetabolico()!=null)
            return ficha.getMetabolico().getGasometriaArterial();
        else
            return -1;
    }

    /**
     * Verifica se o editText foi preenchido, APENAS irá colorir o card se ele tiver sido preenchido
     * Atualiza campo Metabolico ou insere novo objeto em Ficha com idFicha
     */
    private void verificaCamposENotificaAdapter(){
        if(gasometrialArterial.getText().toString().length()>0) {
            realm.beginTransaction();
            Metabolico metabolico = realm.createObject(Metabolico.class);
            metabolico.setGasometriaArterial(gasometriaArterialInput);
            Ficha r = getProperFicha();
            r.setMetabolico(metabolico);
            realm.copyToRealmOrUpdate(r);
            realm.commitTransaction();
            changeCardColor();
        }
    }
}
