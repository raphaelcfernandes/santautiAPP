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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Hemodinamico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 10-May-17.
 */

public class HemodinamicoActivity extends GenericoActivity {
    private View tipoSoproLayout,intensidadeSoproLayout;
    private CheckBox checkboxSopro;
    private MyAnimation myAnimation;
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

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), NeurologicoActivity.class);
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
                intent = new Intent(view.getContext(), RespiratorioActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToRight();
            }
        });
        setHemodinamicoFromDatabase();
    }

    private void setHemodinamicoFromDatabase(){
//        Ficha ficha = getProperFicha();
//        if(ficha.getHemodinamico()!=null){
//            Hemodinamico hemodinamico = ficha.getHemodinamico();
//            if(hemodinamico.getPulso()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.pulso,hemodinamico.getPulso());
//            if(hemodinamico.getFoneseBulhas()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.foneseBulhas,hemodinamico.getFoneseBulhas());
//            if(hemodinamico.getPerfusaoCapilar()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.perfusaoCapilarLinearLayout,hemodinamico.getPerfusaoCapilar());
//            if(hemodinamico.getExtremidadesColoracao()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.extremidades,hemodinamico.getExtremidadesColoracao());
//            if(hemodinamico.getExtremidadesTemperatura()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.extremidades,hemodinamico.getExtremidadesTemperatura());
//            if(hemodinamico.isSoproChecked()){
//                checkboxSopro.setChecked(true);
//                myAnimation.slideDownView(getApplicationContext(),intensidadeSoproLayout);
//                myAnimation.slideDownView(getApplicationContext(),tipoSoproLayout);
//                if(hemodinamico.getIntensidadeSopro()>0) {
//                    StringBuilder sb = new StringBuilder(Integer.toString(hemodinamico.getIntensidadeSopro()));
//                    sb.insert(0, '+');
//                    setRadioButtonFromIdAndDatabase(R.id.intensidade_sopro_layout, sb.toString());
//                }
//                if(!hemodinamico.getTipoSopro().isEmpty())
//                    preencheCheckboxes(R.id.tipoSopro_layout,hemodinamico.getTipoSopro());
//            }
//        }
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
        Hemodinamico hemodinamico = new Hemodinamico();
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
            List<String> string = getCheckBoxesPreenchidos(R.id.tipoSopro_layout);
            hemodinamico.setTipoSopro(string);
            if(intensidadeSopro.getCheckedRadioButtonId()!=-1) {
                StringBuilder sb = new StringBuilder(getStringOfRadioButtonSelectedFromRadioGroup(intensidadeSopro));
                sb.deleteCharAt(0);
                hemodinamico.setIntensidadeSopro(Integer.parseInt(sb.toString()));
            }
        }
        else if(!checkboxSopro.isChecked())
            hemodinamico.setSoproChecked(false);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).updateChildren(hemodinamico.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                changeCardColorToGreen();
            }
        });
    }
}
