package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class MetabolicoActivity extends GenericoActivity {
    private int i=0;
    private Realm realm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolico);
        findViewById(R.id.activity_metabolico).requestFocus();
        setToolbar(this.getString(R.string.Metabolico));

        /********************VIEWS****************************/
        /********************VIEWS****************************/


        prepareNavigationButtons();
        realm = Realm.getDefaultInstance();


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
//        if(gasometrialArterial.getText().toString().length()>0) {
//            realm.beginTransaction();
//            Metabolico metabolico = realm.createObject(Metabolico.class);
//            metabolico.setGasometriaArterial(gasometriaArterialInput);
//            Ficha r = getProperFicha();
//            r.setMetabolico(metabolico);
//            realm.insertOrUpdate(metabolico);
//            realm.commitTransaction();
//            changeCardColor();
//        }
    }
}
