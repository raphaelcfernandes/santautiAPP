package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

import io.realm.Realm;
import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class MetabolicoActivity extends GenericoActivity {
    private int i=0,hidratacaoSelection=-1;
    private Realm realm;
    private TextView hidratacao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolico);
        findViewById(R.id.activity_metabolico).requestFocus();
        setToolbar(this.getString(R.string.Metabolico));

        /********************VIEWS****************************/
        hidratacao = (TextView)findViewById(R.id.hidratacao);
        /********************VIEWS****************************/


        prepareNavigationButtons();
        realm = Realm.getDefaultInstance();
        hidratacaoPopUpMenu();

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
                intent = new Intent(view.getContext(), NutricionalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });
    }

    private void hidratacaoPopUpMenu(){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Hidratacao);

        //list of items
        final String[] items = getResources().getStringArray(R.array.hidratacao);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, hidratacaoSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hidratacao.setText(items[which]);
                        hidratacao.setVisibility(View.VISIBLE);
                        hidratacaoSelection=which;
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }
    public void hidratacaoOnCLick(View view) {
        hidratacaoPopUpMenu();
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
