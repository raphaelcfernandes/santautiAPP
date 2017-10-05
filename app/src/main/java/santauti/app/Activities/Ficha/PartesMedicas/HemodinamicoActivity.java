package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import io.realm.Realm;
import io.realm.RealmList;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Hemodinamico;
import santauti.app.Model.Ficha.RealmObjects.RealmString;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 10-May-17.
 */

public class HemodinamicoActivity extends GenericoActivity {
    private View tipoSoproLayout,intensidadeSoproLayout;
    private CheckBox checkboxSopro;
    private MyAnimation myAnimation;
    private Realm realm;
    private RadioButton hiperfoneticas, hipofoneticas,normofoneticas;
    private RadioGroup foneseBulhasRadioGroup1,foneseBulhasRadioGroup2,pulso,perfusaoCapilar,extremidadesCor,extremidadesTemperatura,intensidadeSopro;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hemodinamico);
        findViewById(R.id.hemodinamico_layout).requestFocus();
        setToolbar(getString(R.string.Hemodinamico));

        /********************CHECKBOX****************************/
        checkboxSopro = (CheckBox)findViewById(R.id.checkboxSopro);
        checkboxSopro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkboxSopro.isChecked() && tipoSoproLayout.isShown() && intensidadeSoproLayout.isShown()){
                    myAnimation.slideUpView(HemodinamicoActivity.this,tipoSoproLayout);
                    myAnimation.slideUpView(HemodinamicoActivity.this,intensidadeSoproLayout);
                }
                else{
                    if(!intensidadeSoproLayout.isShown() && !tipoSoproLayout.isShown() && checkboxSopro.isChecked()) {
                        myAnimation.slideDownView(HemodinamicoActivity.this,intensidadeSoproLayout);
                        myAnimation.slideDownView(HemodinamicoActivity.this,tipoSoproLayout);
                    }
                }
            }
        });
        /********************CHECKBOX****************************/

        /********************RADIOGROUP**************************/
        foneseBulhasRadioGroup1 = (RadioGroup)findViewById(R.id.foneseBulhasRadioGroup1);
        foneseBulhasRadioGroup2 = (RadioGroup)findViewById(R.id.foneseBulhasRadioGroup2);
        pulso = (RadioGroup)findViewById(R.id.pulsoRadioGroup);
        perfusaoCapilar = (RadioGroup)findViewById(R.id.perfusaoCapilar);
        extremidadesCor = (RadioGroup)findViewById(R.id.extremidadesCor);
        extremidadesTemperatura = (RadioGroup)findViewById(R.id.extremidadesTemperatura);
        intensidadeSopro = (RadioGroup)findViewById(R.id.intensidadeSopro);
        /********************RADIOGROUP**************************/

        /********************RADIOBUTTON**************************/
        hiperfoneticas = (RadioButton)findViewById(R.id.hiperfoneticas);
        hiperfoneticas.setOnClickListener(foneseBulhasGroup1OnClick);
        hipofoneticas = (RadioButton)findViewById(R.id.hipofoneticas);
        hipofoneticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(foneseBulhasRadioGroup1.getCheckedRadioButtonId()!=-1)
                    foneseBulhasRadioGroup1.clearCheck();
            }
        });
        normofoneticas = (RadioButton)findViewById(R.id.normofoneticas);
        normofoneticas.setOnClickListener(foneseBulhasGroup1OnClick);
        /********************RADIOBUTTON**************************/

        /********************VIEWS*******************************/
        tipoSoproLayout = findViewById(R.id.tipoSopro_layout);
        intensidadeSoproLayout = findViewById(R.id.intensidade_sopro_layout);
        /********************VIEWS*******************************/



        myAnimation = new MyAnimation();

        prepareNavigationButtons();
        realm=Realm.getDefaultInstance();


        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), NeurologicoActivity.class);
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
                intent = new Intent(view.getContext(), RespiratorioActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });
        setHemodinamicoFromDatabase();
    }

    private void setHemodinamicoFromDatabase(){
        Ficha ficha = getProperFicha();
        if(ficha.getHemodinamico()!=null){
            Hemodinamico hemodinamico = ficha.getHemodinamico();
            if(hemodinamico.getPulso()!=null)
                setRadioButtonFromIdAndDatabase(R.id.pulso,hemodinamico.getPulso());
            if(hemodinamico.getFoneseBulhas()!=null)
                setRadioButtonFromIdAndDatabase(R.id.foneseBulhas,hemodinamico.getFoneseBulhas());
            if(hemodinamico.getPerfusaoCapilar()!=null)
                setRadioButtonFromIdAndDatabase(R.id.perfusaoCapilarLinearLayout,hemodinamico.getPerfusaoCapilar());
            if(hemodinamico.getExtremidadesColoracao()!=null)
                setRadioButtonFromIdAndDatabase(R.id.extremidades,hemodinamico.getExtremidadesColoracao());
            if(hemodinamico.getExtremidadesTemperatura()!=null)
                setRadioButtonFromIdAndDatabase(R.id.extremidades,hemodinamico.getExtremidadesTemperatura());
            if(hemodinamico.isSoproChecked()){
                checkboxSopro.setChecked(true);
                myAnimation.slideDownView(getApplicationContext(),intensidadeSoproLayout);
                myAnimation.slideDownView(getApplicationContext(),tipoSoproLayout);
                if(hemodinamico.getIntensidadeSopro()>0) {
                    StringBuilder sb = new StringBuilder(Integer.toString(hemodinamico.getIntensidadeSopro()));
                    sb.insert(0, '+');
                    setRadioButtonFromIdAndDatabase(R.id.intensidade_sopro_layout, sb.toString());
                }
                if(!hemodinamico.getTipoSopro().isEmpty())
                    preencheCheckboxes(R.id.tipoSopro_layout,hemodinamico.getTipoSopro());
            }
        }
    }

    private View.OnClickListener foneseBulhasGroup1OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(foneseBulhasRadioGroup2.getCheckedRadioButtonId()!=-1)
                foneseBulhasRadioGroup2.clearCheck();
        }
    };

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

    private void verificaCamposENotificaAdapter() {
        realm.beginTransaction();
        Hemodinamico hemodinamico = realm.createObject(Hemodinamico.class);
        if(pulso.getCheckedRadioButtonId()!=-1)
            hemodinamico.setPulso(getStringOfRadioButtonSelectedFromRadioGroup(pulso));
        if(foneseBulhasRadioGroup1.getCheckedRadioButtonId()!=-1)
            hemodinamico.setFoneseBulhas(getStringOfRadioButtonSelectedFromRadioGroup(foneseBulhasRadioGroup1));
        else if(foneseBulhasRadioGroup2.getCheckedRadioButtonId()!=-1)
            hemodinamico.setFoneseBulhas(getStringOfRadioButtonSelectedFromRadioGroup(foneseBulhasRadioGroup2));

        if(perfusaoCapilar.getCheckedRadioButtonId()!=-1)
            hemodinamico.setPerfusaoCapilar(getStringOfRadioButtonSelectedFromRadioGroup(perfusaoCapilar));
        if(extremidadesCor.getCheckedRadioButtonId()!=-1)
            hemodinamico.setExtremidadesColoracao(getStringOfRadioButtonSelectedFromRadioGroup(extremidadesCor));
        if(extremidadesTemperatura.getCheckedRadioButtonId()!=-1)
            hemodinamico.setExtremidadesTemperatura(getStringOfRadioButtonSelectedFromRadioGroup(extremidadesTemperatura));

        if(checkboxSopro.isChecked()){
            hemodinamico.setSoproChecked(true);
            RealmList<RealmString> realmStrings = getCheckBoxesPreenchidos(R.id.tipoSopro_layout);
            for(RealmString realmString : realmStrings)
                hemodinamico.getTipoSopro().add(realmString);
            if(intensidadeSopro.getCheckedRadioButtonId()!=-1) {
                StringBuilder sb = new StringBuilder(getStringOfRadioButtonSelectedFromRadioGroup(intensidadeSopro));
                sb.deleteCharAt(0);
                hemodinamico.setIntensidadeSopro(Integer.parseInt(sb.toString()));
            }
        }
        else if(!checkboxSopro.isChecked())
            hemodinamico.setSoproChecked(false);

        Ficha r = getProperFicha();
        r.setHemodinamico(hemodinamico);
        realm.copyToRealmOrUpdate(r);
        realm.commitTransaction();
        if(hemodinamico.checkObject())
            changeCardColorToGreen();
        else
            setCardColorToDefault();
    }
}
