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
import santauti.app.Model.Ficha.Hematologico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class HematologicoActivity extends GenericoActivity {
    private Realm realm;
    private RadioGroup tromboprofilaxia;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hematologico);
        prepareNavigationButtons();
        setToolbar(getString(R.string.Hematologico));
        realm = Realm.getDefaultInstance();
        tromboprofilaxia = (RadioGroup)findViewById(R.id.tromboprofilaxiaRadiogroup);

        setHematologicoFromDataBase();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), NutricionalActivity.class);
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
                intent = new Intent(view.getContext(), EndocrinoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
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
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home) {
            verificaCamposENotificaAdapter();
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        verificaCamposENotificaAdapter();
        finish();
    }

    private void verificaCamposENotificaAdapter(){
        realm.beginTransaction();
        Hematologico hematologico = realm.createObject(Hematologico.class);
        String tromboprofilaxiaStr = getStringOfRadioButtonSelectedFromRadioGroup(tromboprofilaxia);
        if(tromboprofilaxiaStr!=null){
            Ficha r = getProperFicha();
            hematologico.setTromboprofilaxia(tromboprofilaxiaStr);
            r.setHematologico(hematologico);
            realm.copyToRealmOrUpdate(r);
            changeCardColorToGreen();
        }
        realm.commitTransaction();
    }

    private void setHematologicoFromDataBase(){
        Ficha ficha = getProperFicha();

        if(ficha.getHematologico()!=null){
            if(ficha.getHematologico().getTromboprofilaxia().equals(getString(R.string.Nao)))
                tromboprofilaxia.check(R.id.naoTromboprofilaxia);
            else if(ficha.getHematologico().getTromboprofilaxia().equals(getString(R.string.HeparinaFracionada)))
                tromboprofilaxia.check(R.id.heparinaFracionada);
            else if(ficha.getHematologico().getTromboprofilaxia().equals(getString(R.string.HeparinaNaoFracionada)))
                tromboprofilaxia.check(R.id.heparinaNaoFracionada);
        }
    }
}
