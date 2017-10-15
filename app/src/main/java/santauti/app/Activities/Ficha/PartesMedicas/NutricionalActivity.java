package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Nutricional;
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
                startActivity(intent);
                exitActivityToLeft();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HematologicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
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
//        realm.beginTransaction();
//        Nutricional nutricional = realm.createObject(Nutricional.class);
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dieta);
//        for (int i = 0; i < linearLayout.getChildCount(); i++) {
//            View v = linearLayout.getChildAt(i);
//            if(v instanceof RelativeLayout) {
//                for (int k = 0; k < ((RelativeLayout) v).getChildCount(); k++) {
//                    View view = ((RelativeLayout) v).getChildAt(k);
//                    if (view instanceof AppCompatCheckBox) {
//                        AppCompatCheckBox cb = (AppCompatCheckBox) view;
//                        if (cb.isChecked()) {
//                            RealmString realmString = realm.createObject(RealmString.class);
//                            realmString.setName(cb.getText().toString());
//                            nutricional.getDieta().add(realmString);
//                        }
//                    }
//                }
//            }
//        }
//        if(aceitacaoRadioGroup.getCheckedRadioButtonId()!=-1)
//            nutricional.setAceitacao(getStringOfRadioButtonSelectedFromRadioGroup(aceitacaoRadioGroup));
//        Ficha r = getProperFicha();
//        r.setNutricional(nutricional);
//        realm.copyToRealmOrUpdate(r);
//        realm.commitTransaction();
//        if(nutricional.checkObject())
//            changeCardColorToGreen();
    }
}
