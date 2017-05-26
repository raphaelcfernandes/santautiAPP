package santauti.app.Activities.Ficha.PartesMedicas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;

import io.realm.Realm;
import io.realm.RealmResults;
import santauti.app.Activities.Ficha.Generico;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class MetabolicoActivity extends Generico {
    TextInputEditText gasometrialArterial;
    private int i=0;
    private Realm realm;
    private int gasometriaArterialInput;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolico);
        findViewById(R.id.activity_metabolico).requestFocus();
        setToolbar(this.getString(R.string.Metabolico));

        gasometrialArterial = (TextInputEditText)findViewById(R.id.gasometrial_arterial);
        gasometrialArterial.addTextChangedListener(textWatcher);

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
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            gasometriaArterialInput = (Integer.parseInt(gasometrialArterial.getText().toString()));
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    };

    private void verificaCamposENotificaAdapter(){
        if(gasometrialArterial.getText().toString().length()>0) {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            Metabolico metabolico = realm.createObject(Metabolico.class);
            metabolico.setGasometriaArterial(gasometriaArterialInput);
            final Ficha r = realm.where(Ficha.class).equalTo("NroAtendimento",1).findFirst();
            r.setMetabolico(metabolico);
            realm.copyToRealmOrUpdate(r);
            realm.commitTransaction();
            changeCardColor();
        }

    }
}
