package santauti.app.Activities.Ficha.PartesMedicas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;

import santauti.app.Activities.Ficha.Generico;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class MetabolicoActivity extends Generico {
    TextInputEditText gasometrialArterial;
    private int i=0;
    private Metabolico metabolico = new Metabolico();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolico);
        findViewById(R.id.activity_metabolico).requestFocus();
        gasometrialArterial = (TextInputEditText)findViewById(R.id.gasometrial_arterial);
        gasometrialArterial.addTextChangedListener(textWatcher);

        setToolbar(this.getString(R.string.Metabolico));

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
            metabolico.setGasometriaArterial(Integer.parseInt(gasometrialArterial.getText().toString()));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    };

    private void verificaCamposENotificaAdapter(){
        if(gasometrialArterial.getText().toString().length()>0) {
            metabolico.setGasometriaArterial(Integer.parseInt(gasometrialArterial.getText().toString()));
            Bundle bundle = new Bundle();
            bundle.putSerializable("MetabolicoObject",metabolico);
            getIntent().putExtras(bundle);
            changeCardColor();
        }
    }
}
