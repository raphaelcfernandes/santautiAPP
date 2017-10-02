package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class MetabolicoActivity extends GenericoActivity {
    private int i=0;
    private Realm realm;
    private RadioGroup hidratacao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolico);
        findViewById(R.id.activity_metabolico).requestFocus();
        setToolbar(this.getString(R.string.Metabolico));

        /********************RADIOGROUP****************************/
        hidratacao = (RadioGroup)findViewById(R.id.hidratacaoRadioGroup);
        /********************RADIOGROUP****************************/



        prepareNavigationButtons();
        realm = Realm.getDefaultInstance();
        setMetabolicoFromDataBase();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RenalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), InfecciosoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });
    }


    private void setMetabolicoFromDataBase(){
        Ficha ficha = getProperFicha();
        if(ficha.getMetabolico()!=null){
            if(ficha.getMetabolico().getHidratacao().equals(getString(R.string.NormoHidratado)))
                hidratacao.check(R.id.normoHidratado);
            else if(ficha.getMetabolico().getHidratacao().equals(getString(R.string.Edemaciado)))
                hidratacao.check(R.id.edemaciado);
            else if(ficha.getMetabolico().getHidratacao().equals(getString(R.string.Desidratado)))
                hidratacao.check(R.id.desidratado);
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

    /**
     * Verifica se algum radio button foi selecionado
     *
     */
    private void verificaCamposENotificaAdapter(){
        realm.beginTransaction();
        Metabolico metabolico = realm.createObject(Metabolico.class);
        String hidratacaoStr = getStringOfRadioButtonSelectedFromRadioGroup(hidratacao);
        if(hidratacaoStr!=null) {
            Ficha r = getProperFicha();
            metabolico.setHidratacao(hidratacaoStr);
            r.setMetabolico(metabolico);
            realm.copyToRealmOrUpdate(r);
            changeCardColor();
        }
        realm.commitTransaction();
    }
}
