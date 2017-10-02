package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Dispositivos;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Gastrointestinal;
import santauti.app.Model.Ficha.RealmObjects.RealmString;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class GastrointestinalActivity extends GenericoActivity {
    private Realm realm;
    private CheckBox massasPalpaveisCheckBox,viscerasPalpaveisCheckBox;
    private RadioButton ruidosAumentados,ruidosReduzidos,ruidosNormais,ruidosAusentes,formatoPlano,formatoGloboso,formatoSemigloboso,formatoEscavado;
    private RadioGroup ruidosGroup1,ruidosGroup2,formatoAbdominalGroup1,formatoAbdominalGroup2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastrointestinal);
        findViewById(R.id.gastrointestinal_layout).requestFocus();
        setToolbar(getString(R.string.GastroIntestinal));

        massasPalpaveisCheckBox = (CheckBox)findViewById(R.id.checkboxMassasPalpaveis);
        viscerasPalpaveisCheckBox = (CheckBox)findViewById(R.id.checkboxViscerasPalpaveis);

        /*********************************RADIOBUTTON/RADIOGROUP*****************************/
        ruidosGroup1 = (RadioGroup)findViewById(R.id.ruidosGroup1);
        ruidosGroup2 = (RadioGroup)findViewById(R.id.ruidosGroup2);
        formatoAbdominalGroup1 = (RadioGroup)findViewById(R.id.formatoAbdominalGroup1);
        formatoAbdominalGroup2 = (RadioGroup)findViewById(R.id.formatoAbdominalGroup2);
        ruidosAumentados = (RadioButton)findViewById(R.id.ruidosAumentados);
        ruidosAumentados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruidosGroup2.clearCheck();
            }
        });
        ruidosReduzidos = (RadioButton)findViewById(R.id.ruidosReduzidos);
        ruidosReduzidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruidosGroup1.clearCheck();
            }
        });
        ruidosNormais = (RadioButton)findViewById(R.id.ruidosNormais);
        ruidosNormais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruidosGroup2.clearCheck();
            }
        });
        ruidosAusentes = (RadioButton)findViewById(R.id.ruidosAusentes);
        ruidosAusentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruidosGroup1.clearCheck();
            }
        });
        formatoPlano = (RadioButton)findViewById(R.id.formatoPlano);
        formatoPlano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {formatoAbdominalGroup2.clearCheck();
            }
        });
        formatoGloboso = (RadioButton)findViewById(R.id.formatoGloboso);
        formatoGloboso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {formatoAbdominalGroup2.clearCheck();
            }
        });
        formatoSemigloboso = (RadioButton)findViewById(R.id.formatoSemigloboso);
        formatoSemigloboso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {formatoAbdominalGroup1.clearCheck();
            }
        });
        formatoEscavado = (RadioButton)findViewById(R.id.formatoEscavado);
        formatoEscavado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {formatoAbdominalGroup1.clearCheck();
            }
        });
        /*********************************RADIOBUTTON/RADIOGROUP*****************************/


        prepareNavigationButtons();

        realm = Realm.getDefaultInstance();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RespiratorioActivity.class);
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
                intent = new Intent(view.getContext(), RenalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        setGastroIntestinalFromDataBase();
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

    private void verificaCamposENotificaAdapter() {
        realm.beginTransaction();
        Gastrointestinal gastrointestinal = realm.createObject(Gastrointestinal.class);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gastroIntestinalItens);
        for(int i=0;i<linearLayout.getChildCount();i++) {
            View v = linearLayout.getChildAt(i);
            if (v instanceof LinearLayout) {
                for (int j = 0; j < ((LinearLayout) v).getChildCount(); j++) {
                    View view = ((LinearLayout) v).getChildAt(j);
                    if (view instanceof RadioGroup) {
                        RadioGroup cb = (RadioGroup) view;
                        //i significa posiçao do filho em relaçao ao id: pai
                        if (cb.getCheckedRadioButtonId() != -1 && i == 0 )//Formato.
                            gastrointestinal.setFormato(getStringOfRadioButtonSelectedFromRadioGroup(cb));
                        if (cb.getCheckedRadioButtonId() != -1 && i == 2)//Tensao
                            gastrointestinal.setTensao(getStringOfRadioButtonSelectedFromRadioGroup(cb));
                        if (cb.getCheckedRadioButtonId() != -1 && i == 4)//Ruidos
                            gastrointestinal.setRuidos(getStringOfRadioButtonSelectedFromRadioGroup(cb));
                        if (cb.getCheckedRadioButtonId() != -1 && i == 6)//Ascite
                            gastrointestinal.setAscite(getStringOfRadioButtonSelectedFromRadioGroup(cb));
                    }
                }
            }
            if (v instanceof RelativeLayout) {
                for (int k = 0; k < ((RelativeLayout) v).getChildCount(); k++) {
                    View view = ((RelativeLayout) v).getChildAt(k);
                    if (view instanceof AppCompatCheckBox) {
                        AppCompatCheckBox cb = (AppCompatCheckBox) view;
                        if (cb.isChecked() && i == 8)//Massas
                            gastrointestinal.setMassasPalpaveis(true);
                        if (cb.isChecked() && i == 10)//Visceras
                            gastrointestinal.setViscerasPalpaveis(true);
                    }
                }
            }
        }

        Ficha r = getProperFicha();
        r.setGastrointestinal(gastrointestinal);
        realm.copyToRealmOrUpdate(r);
        if(gastrointestinal.checkObject())
            changeCardColor();
        realm.commitTransaction();
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

    public void massasPalpaveisOnClick(View view){
        if(massasPalpaveisCheckBox.isChecked())
            massasPalpaveisCheckBox.setChecked(false);
        else
            massasPalpaveisCheckBox.setChecked(true);
    }

    public void viscerasPalpaveisOnClick(View view){
        if(viscerasPalpaveisCheckBox.isChecked())
            viscerasPalpaveisCheckBox.setChecked(false);
        else
            viscerasPalpaveisCheckBox.setChecked(true);
    }

    private void setGastroIntestinalFromDataBase(){
        Ficha ficha = getProperFicha();

        if(ficha.getGastrointestinal()!=null){
            Gastrointestinal gastrointestinal = ficha.getGastrointestinal();
            if(gastrointestinal.isMassasPalpaveis())
                massasPalpaveisCheckBox.setChecked(true);
            if(gastrointestinal.isViscerasPalpaveis())
                viscerasPalpaveisCheckBox.setChecked(true);
            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.gastroIntestinalItens);
            for(int i=0;i<linearLayout.getChildCount();i++) {
                View v = linearLayout.getChildAt(i);
                if (v instanceof LinearLayout) {
                    for (int j = 0; j < ((LinearLayout) v).getChildCount(); j++) {
                        View view = ((LinearLayout) v).getChildAt(j);
                        if (view instanceof RadioGroup) {
                            for (int k = 0; k < ((RadioGroup) view).getChildCount(); k++) {
                                View view1 = ((RadioGroup)view).getChildAt(k);
                                AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton)view1;
                                if(appCompatRadioButton.getText().toString().equals(gastrointestinal.getAscite())
                                        || appCompatRadioButton.getText().toString().equals(gastrointestinal.getFormato())
                                        || appCompatRadioButton.getText().toString().equals(gastrointestinal.getRuidos())
                                        || appCompatRadioButton.getText().toString().equals(gastrointestinal.getTensao()))
                                    appCompatRadioButton.setChecked(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
