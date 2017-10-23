package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Nutricional;
import santauti.app.Model.Ficha.PelesMucosas;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 09-Aug-17.
 */

public class NutricionalActivity extends GenericoActivity {
    private CheckBox dietaOral, dietaEnteral, dietaParenteral;
    private RadioGroup aceitacaoRadioGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricional);
        setToolbar(getString(R.string.Nutricional));

        prepareNavigationButtons();

        /*****************************CHECKBOX**************************/
        dietaEnteral = (CheckBox)findViewById(R.id.dietaEnteral);
        dietaOral = (CheckBox)findViewById(R.id.dietaOral);
        dietaParenteral = (CheckBox)findViewById(R.id.dietaParenteral);
        /*****************************CHECKBOX**************************/

        /****************************RADIOGROUP*************************/
        aceitacaoRadioGroup = (RadioGroup)findViewById(R.id.aceitacaoRadioGroup);
        /****************************RADIOGROUP*************************/

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), InfecciosoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToLeft();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HematologicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToRight();
            }
        });

        setNutricionalFromDatabase();
    }

    private void setNutricionalFromDatabase(){
//        Ficha ficha = getProperFicha();
//        if(ficha.getNutricional()!=null){
//            if(!ficha.getNutricional().getDieta().isEmpty()){
//                preencheCheckboxes(R.id.dieta,ficha.getNutricional().getDieta());
//            }
//            if(ficha.getNutricional().getAceitacao()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.aceitacao,ficha.getNutricional().getAceitacao());
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    private void verificaCamposENotificaAdapter(){
        final Nutricional nutricional = new Nutricional();
        List<String> strings;
        strings = getCheckBoxesPreenchidos(R.id.dieta);
        nutricional.setDieta(strings);
        if(aceitacaoRadioGroup.getCheckedRadioButtonId()!=-1)
            nutricional.setAceitacao(getStringOfRadioButtonSelectedFromRadioGroup(aceitacaoRadioGroup));
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).updateChildren(nutricional.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(nutricional.checkObject())
                    changeCardColorToGreen();
                else
                    setCardColorToDefault();
            }
        });
    }
}
