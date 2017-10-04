package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Endocrino;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class EndocrinoActivity extends GenericoActivity {
    private Realm realm;
    private RadioButton normoGlicemia,eventosHipoglicemia,eventosHiperglicemia;
    private RadioGroup curvaGlicemicaRadioGroup1,curvaGlicemicaRadioGroup2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endocrino);
        setToolbar(getString(R.string.Endocrino));

        prepareNavigationButtons();
        realm = Realm.getDefaultInstance();

        /***********************RADIOGROUP**********************************/
        curvaGlicemicaRadioGroup1 = (RadioGroup)findViewById(R.id.curvaGlicemicaRadioGroup1);
        curvaGlicemicaRadioGroup2 = (RadioGroup)findViewById(R.id.curvaGlicemicaRadioGroup2);
        /***********************RADIOGROUP**********************************/

        /***********************RADIOBUTTON********************************/
        normoGlicemia = (RadioButton)findViewById(R.id.normoGlicemia);
        normoGlicemia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {curvaGlicemicaRadioGroup2.clearCheck();
            }
        });
        eventosHipoglicemia = (RadioButton)findViewById(R.id.eventosHipoglicemia);
        eventosHipoglicemia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {curvaGlicemicaRadioGroup1.clearCheck();
            }
        });
        eventosHiperglicemia = (RadioButton)findViewById(R.id.eventosHiperglicemia);
        eventosHiperglicemia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {curvaGlicemicaRadioGroup2.clearCheck();
            }
        });
        /***********************RADIOBUTTON********************************/

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HematologicoActivity.class);
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
                intent = new Intent(view.getContext(), PelesMucosasActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        setEndocrinoFromDatabase();
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

    public void verificaCamposENotificaAdapter(){
        String curvaGlicemica = null;
        if(curvaGlicemicaRadioGroup1.getCheckedRadioButtonId()!=-1)
            curvaGlicemica = getStringOfRadioButtonSelectedFromRadioGroup(curvaGlicemicaRadioGroup1);
        else if(curvaGlicemicaRadioGroup2.getCheckedRadioButtonId()!=-1)
            curvaGlicemica = getStringOfRadioButtonSelectedFromRadioGroup(curvaGlicemicaRadioGroup2);

        if(curvaGlicemica!=null) {
            realm.beginTransaction();
            Endocrino endocrino = realm.createObject(Endocrino.class);
            endocrino.setCurvaGlicemia(curvaGlicemica);
            Ficha r = getProperFicha();
            r.setEndocrino(endocrino);
            realm.copyToRealmOrUpdate(r);
            realm.commitTransaction();
            changeCardColorToGreen();
        }
    }

    private void setEndocrinoFromDatabase(){
        Ficha ficha = getProperFicha();
        if(ficha.getEndocrino()!=null)
            setRadioButtonFromIdAndDatabase(R.id.curvaGlicemica,ficha.getEndocrino().getCurvaGlicemia());
    }
}