package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Nutricional;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 09-Aug-17.
 */

public class NutricionalActivity extends GenericoActivity {
    private CheckBox dietaOral, dietaEnteral, dietaParenteral;
    private RadioGroup aceitacaoRadioGroup;
    private LinearLayout enteralTolerancia, parenteralTolerancia, oralAceitacao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricional);
        setToolbar(getString(R.string.Nutricional));

        prepareNavigationButtons();

        /*****************************CHECKBOX**************************/
        dietaEnteral = (CheckBox)findViewById(R.id.checkBoxEnteral);
        dietaEnteral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enteralTolerancia.isShown())
                    enteralTolerancia.setVisibility(View.GONE);
                else
                    enteralTolerancia.setVisibility(View.VISIBLE);
            }
        });
        dietaOral = (CheckBox)findViewById(R.id.checkboxOral);
        dietaOral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oralAceitacao.isShown())
                    oralAceitacao.setVisibility(View.GONE);
                else
                    oralAceitacao.setVisibility(View.VISIBLE);
            }
        });
        dietaParenteral = (CheckBox)findViewById(R.id.checkboxParenteral);
        dietaParenteral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(parenteralTolerancia.isShown())
                    parenteralTolerancia.setVisibility(View.GONE);
                else
                    parenteralTolerancia.setVisibility(View.VISIBLE);
            }
        });
        /*****************************CHECKBOX**************************/

        /****************************RADIOGROUP*************************/

        /****************************RADIOGROUP*************************/


        /****************************VIEW*******************************/
        enteralTolerancia = (LinearLayout) findViewById(R.id.enteralTolerancia);
        parenteralTolerancia = (LinearLayout) findViewById(R.id.parenteralTolerancia);
        oralAceitacao = (LinearLayout) findViewById(R.id.oralAceitacao);
        /****************************VIEW*******************************/

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
        if(dietaEnteral.isChecked()) {
            nutricional.setEnteralCheckBox(true);
            nutricional.setEnteralTolerancia(getStringOfRadioButtonSelectedFromRadioGroup((RadioGroup)(findViewById(R.id.enteralRadioGroup))));
        }
        if(dietaParenteral.isChecked()){
            nutricional.setParenteralCheckBox(true);
            nutricional.setParenteralTolerancia(getStringOfRadioButtonSelectedFromRadioGroup((RadioGroup)(findViewById(R.id.parenteralRadioGroup))));
        }
        if(dietaOral.isChecked()){
            nutricional.setOralCheckBox(true);
            nutricional.setOralAceitacao(getStringOfRadioButtonSelectedFromRadioGroup((RadioGroup)(findViewById(R.id.oralAceitacaoRadioGroup))));
        }

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getFichaHospitalReference(sharedPreferences.getString("hospitalKey", ""),
                sharedPreferences.getString("fichaKey", "")).updateChildren(nutricional.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
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
