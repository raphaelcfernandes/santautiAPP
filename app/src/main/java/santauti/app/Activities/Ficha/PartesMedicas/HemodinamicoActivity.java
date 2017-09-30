package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Hemodinamico.Hemodinamico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 10-May-17.
 */

public class HemodinamicoActivity extends GenericoActivity {
    private View tipoSoproLayout,intensidadeSoproLayout;
    private CheckBox checkboxSopro;
    private MyAnimation myAnimation;
    private Realm realm;
    private Ficha ficha;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hemodinamico);
        findViewById(R.id.hemodinamico_layout).requestFocus();
        setToolbar(getString(R.string.Hemodinamico));

        /********************VIEWS*******************************/
        tipoSoproLayout = findViewById(R.id.tipoSopro_layout);
        intensidadeSoproLayout = findViewById(R.id.intensidade_sopro_layout);
        checkboxSopro = (CheckBox)findViewById(R.id.checkboxSopro);
        checkboxSopro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkboxSopro.isChecked()){
                    myAnimation.slideUpView(HemodinamicoActivity.this,tipoSoproLayout);
                    myAnimation.slideUpView(HemodinamicoActivity.this,intensidadeSoproLayout);
                }
                else{
                    if(!intensidadeSoproLayout.isShown() && !tipoSoproLayout.isShown()) {
                        myAnimation.slideDownView(HemodinamicoActivity.this,intensidadeSoproLayout);
                        myAnimation.slideDownView(HemodinamicoActivity.this,tipoSoproLayout);
                    }
                }
            }
        });
        /********************VIEWS*******************************/



        myAnimation = new MyAnimation();

        prepareNavigationButtons();
        realm=Realm.getDefaultInstance();
        ficha=getProperFicha();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), NeurologicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RespiratorioActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });

    }

    public void soproOnClick(View view){
        if(tipoSoproLayout.isShown() && intensidadeSoproLayout.isShown()){
            myAnimation.slideUpView(HemodinamicoActivity.this,tipoSoproLayout);
            myAnimation.slideUpView(HemodinamicoActivity.this,intensidadeSoproLayout);
        }
        else{
            if(checkboxSopro.isChecked()) {
                myAnimation.slideDownView(HemodinamicoActivity.this, intensidadeSoproLayout);
                myAnimation.slideDownView(HemodinamicoActivity.this, tipoSoproLayout);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onBackPressed(){
        //verificaCamposENotificaAdapter();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home) {
            //verificaCamposENotificaAdapter();
            finish();
        }
        return true;
    }

    public void verificaCamposENotificaAdapter(){
        boolean obrigatorios = false;
        ficha=getProperFicha();
        realm.beginTransaction();
        Hemodinamico hemodinamico = realm.createObject(Hemodinamico.class);


        if(obrigatorios) {
            Ficha r = getProperFicha();
            r.setHemodinamico(hemodinamico);
            realm.copyToRealmOrUpdate(r);
            changeCardColor();
        }
        realm.commitTransaction();
    }
}
