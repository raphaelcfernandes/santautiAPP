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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Gastrointestinal;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class GastrointestinalActivity extends GenericoActivity {
    private CheckBox massasPalpaveisCheckBox, viscerasPalpaveisCheckBox,checkBoxOstomias,
            checkBoxColostomia,checkBoxIleostomia,checkBoxJejunostomia;
    private RadioButton ruidosAumentados, ruidosReduzidos, ruidosNormais,
            ruidosAusentes, formatoPlano, formatoGloboso, formatoSemigloboso, formatoEscavado;
    private RadioGroup ruidosGroup1, ruidosGroup2, formatoAbdominalGroup1, formatoAbdominalGroup2, tensaoAbdominal, ascite;
    private View massasPalpaveisItensLayout, viscerasPalpaveisItensLayout,massasPalpaveis,viscerasPalpaveis,
            ostomiasItens,colostomiaItens,ileostomiaItens,jejunostomiaItens;
    private MyAnimation myAnimation = new MyAnimation();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastrointestinal);
        findViewById(R.id.gastrointestinal_layout).requestFocus();
        setToolbar(getString(R.string.GastroIntestinal));

        /****************************CHECKBOX***************************************/
        massasPalpaveisCheckBox = (CheckBox) findViewById(R.id.checkboxMassasPalpaveis);
        massasPalpaveisCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!massasPalpaveisCheckBox.isChecked())
                    massasPalpaveisItensLayout.setVisibility(View.GONE);
                else
                    massasPalpaveisItensLayout.setVisibility(View.VISIBLE);
            }
        });
        viscerasPalpaveisCheckBox = (CheckBox) findViewById(R.id.checkboxEmDialise);
        viscerasPalpaveisCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!viscerasPalpaveisCheckBox.isChecked())
                    viscerasPalpaveisItensLayout.setVisibility(View.GONE);
                else
                    viscerasPalpaveisItensLayout.setVisibility(View.VISIBLE);
            }
        });
        checkBoxOstomias = (CheckBox)findViewById(R.id.checkBoxOstomias);
        checkBoxOstomias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBoxOstomias.isChecked() && ostomiasItens.isShown())
                    myAnimation.slideUpView(getApplicationContext(), ostomiasItens);
                if(checkBoxOstomias.isChecked() && !ostomiasItens.isShown())
                    myAnimation.slideDownView(getApplicationContext(), ostomiasItens);
            }
        });
        checkBoxColostomia = (CheckBox)findViewById(R.id.checkBoxColostomia);
        checkBoxColostomia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBoxColostomia.isChecked() && colostomiaItens.isShown())
                    myAnimation.slideUpView(getApplicationContext(), colostomiaItens);
                if(checkBoxColostomia.isChecked() && !colostomiaItens.isShown())
                    myAnimation.slideDownView(getApplicationContext(), colostomiaItens);
            }
        });
        checkBoxIleostomia = (CheckBox)findViewById(R.id.checkBoxIleostomia);
        checkBoxIleostomia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBoxIleostomia.isChecked() && ileostomiaItens.isShown())
                    myAnimation.slideUpView(getApplicationContext(), ileostomiaItens);
                if(checkBoxIleostomia.isChecked() && !ileostomiaItens.isShown())
                    myAnimation.slideDownView(getApplicationContext(), ileostomiaItens);
            }
        });
        checkBoxJejunostomia = (CheckBox)findViewById(R.id.checkBoxJejunostomia);
        checkBoxJejunostomia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBoxJejunostomia.isChecked() && jejunostomiaItens.isShown())
                    myAnimation.slideUpView(getApplicationContext(), jejunostomiaItens);
                if(checkBoxJejunostomia.isChecked() && !jejunostomiaItens.isShown())
                    myAnimation.slideDownView(getApplicationContext(), jejunostomiaItens);
            }
        });
        /****************************CHECKBOX***************************************/

        /****************************VIEWS*****************************************/
        ostomiasItens = findViewById(R.id.ostomiasItens);
        colostomiaItens = findViewById(R.id.colostomiaItens);
        ileostomiaItens = findViewById(R.id.ileostomiaItens);
        jejunostomiaItens = findViewById(R.id.jejunostomiaItens);
        massasPalpaveis = findViewById(R.id.massasPalpaveis);
        massasPalpaveisItensLayout = findViewById(R.id.massasPalpaveisItensLayout);
        massasPalpaveis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(massasPalpaveisCheckBox.isChecked()) {
                    if(massasPalpaveisItensLayout.isShown())
                        massasPalpaveisItensLayout.setVisibility(View.GONE);
                    else
                        massasPalpaveisItensLayout.setVisibility(View.VISIBLE);
                }

            }
        });
        viscerasPalpaveisItensLayout = findViewById(R.id.viscerasPalpaveisItensLayout);
        viscerasPalpaveis = findViewById(R.id.emDialise);
        viscerasPalpaveis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viscerasPalpaveisCheckBox.isChecked()) {
                    if(viscerasPalpaveisItensLayout.isShown())
                        viscerasPalpaveisItensLayout.setVisibility(View.GONE);
                    else
                        viscerasPalpaveisItensLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        /****************************VIEWS*****************************************/


        /*********************************RADIOBUTTON/RADIOGROUP*****************************/
        ascite = (RadioGroup) findViewById(R.id.asciteRadioGroup);
        tensaoAbdominal = (RadioGroup) findViewById(R.id.tensaoAbdominal);
        ruidosGroup1 = (RadioGroup) findViewById(R.id.ruidosGroup1);
        ruidosGroup2 = (RadioGroup) findViewById(R.id.ruidosGroup2);
        formatoAbdominalGroup1 = (RadioGroup) findViewById(R.id.formatoAbdominalGroup1);
        formatoAbdominalGroup2 = (RadioGroup) findViewById(R.id.formatoAbdominalGroup2);
        ruidosAumentados = (RadioButton) findViewById(R.id.ruidosAumentados);
        ruidosAumentados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ruidosGroup2.clearCheck();
            }
        });
        ruidosReduzidos = (RadioButton) findViewById(R.id.ruidosReduzidos);
        ruidosReduzidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruidosGroup1.clearCheck();
            }
        });
        ruidosNormais = (RadioButton) findViewById(R.id.ruidosNormais);
        ruidosNormais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruidosGroup2.clearCheck();
            }
        });
        ruidosAusentes = (RadioButton) findViewById(R.id.ruidosAusentes);
        ruidosAusentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruidosGroup1.clearCheck();
            }
        });
        formatoPlano = (RadioButton) findViewById(R.id.formatoPlano);
        formatoPlano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {formatoAbdominalGroup2.clearCheck();
            }
        });
        formatoGloboso = (RadioButton) findViewById(R.id.formatoGloboso);
        formatoGloboso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {formatoAbdominalGroup2.clearCheck();
            }
        });
        formatoSemigloboso = (RadioButton) findViewById(R.id.formatoSemigloboso);
        formatoSemigloboso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {formatoAbdominalGroup1.clearCheck();
            }
        });
        formatoEscavado = (RadioButton) findViewById(R.id.formatoEscavado);
        formatoEscavado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {formatoAbdominalGroup1.clearCheck();
            }
        });
        /*********************************RADIOBUTTON/RADIOGROUP*****************************/


        prepareNavigationButtons();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RespiratorioActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0) - 1, intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToLeft();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RenalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0) + 1, intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToRight();
            }
        });

        setGastroIntestinalFromDataBase();
    }

    public void ostomiasOnClick(View view){
        if(ostomiasItens.isShown())
            myAnimation.slideUpView(getApplicationContext(),ostomiasItens);
        else
        if(checkBoxOstomias.isChecked())
            myAnimation.slideDownView(getApplicationContext(),ostomiasItens);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        verificaCamposENotificaAdapter();
        finish();
    }

    private void verificaCamposENotificaAdapter() {
        final Gastrointestinal gastrointestinal = new Gastrointestinal();
        if (formatoAbdominalGroup1.getCheckedRadioButtonId() != -1)
            gastrointestinal.setFormato(getStringOfRadioButtonSelectedFromRadioGroup(formatoAbdominalGroup1));
        else if (formatoAbdominalGroup2.getCheckedRadioButtonId() != -1)
            gastrointestinal.setFormato(getStringOfRadioButtonSelectedFromRadioGroup(formatoAbdominalGroup2));
        if (tensaoAbdominal.getCheckedRadioButtonId() != -1)
            gastrointestinal.setTensao(getStringOfRadioButtonSelectedFromRadioGroup(tensaoAbdominal));
        if (ruidosGroup1.getCheckedRadioButtonId() != -1)
            gastrointestinal.setRuidos(getStringOfRadioButtonSelectedFromRadioGroup(ruidosGroup1));
        else if (ruidosGroup2.getCheckedRadioButtonId() != -1)
            gastrointestinal.setRuidos(getStringOfRadioButtonSelectedFromRadioGroup(ruidosGroup2));
        if (ascite.getCheckedRadioButtonId() != -1)
            gastrointestinal.setAscite(getStringOfRadioButtonSelectedFromRadioGroup(ascite));

        /**
         * Verifica quais Checkbox de massas Palpaveis esta marcado
         */
        if (massasPalpaveisCheckBox.isChecked()) {
            gastrointestinal.setMassasPalpaveisFlag(true);
            List<String> massas = getCheckBoxesPreenchidos(R.id.massasPalpaveisItensLayout);
            gastrointestinal.setMassasPalpaveis(massas);
        }

        if(viscerasPalpaveisCheckBox.isChecked()){
            gastrointestinal.setViscerasPalpaveisFlag(true);
            List<String> visceras = getCheckBoxesPreenchidos(R.id.viscerasPalpaveisItensLayout);
            gastrointestinal.setViscerasPalpaveis(visceras);
        }
        if(checkBoxOstomias.isChecked()){
            gastrointestinal.setOstomiasFlag(true);
            gastrointestinal.initialiazeMap();
            List<String> string = new ArrayList<>();
            if(((RadioGroup)findViewById(R.id.colostomiaQualidadeRadioGroup)).getCheckedRadioButtonId()!=-1
                    && ((RadioGroup)findViewById(R.id.colostomiaFuncionamentoRadioGroup)).getCheckedRadioButtonId()!=-1
                    && checkBoxColostomia.isChecked()){
                string.add(getStringOfRadioButtonSelectedFromRadioGroup(((RadioGroup)findViewById(R.id.colostomiaFuncionamentoRadioGroup))));
                string.add(getStringOfRadioButtonSelectedFromRadioGroup(((RadioGroup)findViewById(R.id.colostomiaQualidadeRadioGroup))));
                gastrointestinal.insereOstomia(checkBoxColostomia.getText().toString(),string);
            }
            if(((RadioGroup)findViewById(R.id.ileostomiaFuncionamentoRadioGroup)).getCheckedRadioButtonId()!=-1
                    && ((RadioGroup)findViewById(R.id.ileostomiaQualidadeRadioGroup)).getCheckedRadioButtonId()!=-1
                    && checkBoxIleostomia.isChecked()){
                string.clear();
                string.add(getStringOfRadioButtonSelectedFromRadioGroup(((RadioGroup)findViewById(R.id.ileostomiaFuncionamentoRadioGroup))));
                string.add(getStringOfRadioButtonSelectedFromRadioGroup(((RadioGroup)findViewById(R.id.ileostomiaQualidadeRadioGroup))));
                gastrointestinal.insereOstomia(checkBoxIleostomia.getText().toString(),string);
            }
            if(((RadioGroup)findViewById(R.id.jejunostomiaFuncionamentoRadioGroup)).getCheckedRadioButtonId()!=-1
                    && ((RadioGroup)findViewById(R.id.jejunostomiaQualidadeRadioGroup)).getCheckedRadioButtonId()!=-1
                    && checkBoxJejunostomia.isChecked()){
                string.clear();
                string.add(getStringOfRadioButtonSelectedFromRadioGroup(((RadioGroup)findViewById(R.id.jejunostomiaFuncionamentoRadioGroup))));
                string.add(getStringOfRadioButtonSelectedFromRadioGroup(((RadioGroup)findViewById(R.id.jejunostomiaQualidadeRadioGroup))));
                gastrointestinal.insereOstomia(checkBoxJejunostomia.getText().toString(),string);
            }
        }
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).updateChildren(gastrointestinal.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(gastrointestinal.checkObject())
                    changeCardColorToGreen();
                else
                    setCardColorToDefault();
            }
        });
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

    private void setGastroIntestinalFromDataBase(){
//        Ficha ficha = getProperFicha();
//        if(ficha.getGastrointestinal()!=null){
//            Gastrointestinal gastrointestinal = ficha.getGastrointestinal();
//            if(gastrointestinal.isMassasPalpaveisFlag()) {
//                massasPalpaveisCheckBox.setChecked(true);
//                massasPalpaveisItensLayout.setVisibility(View.VISIBLE);
//                if(!gastrointestinal.getMassasPalpaveis().isEmpty())
//                    preencheCheckboxes(R.id.massasPalpaveisItensLayout,gastrointestinal.getMassasPalpaveis());
//            }
//            if(gastrointestinal.isViscerasPalpaveisFlag()) {
//                viscerasPalpaveisCheckBox.setChecked(true);
//                viscerasPalpaveisItensLayout.setVisibility(View.VISIBLE);
//                if(!gastrointestinal.getViscerasPalpaveis().isEmpty())
//                    preencheCheckboxes(R.id.viscerasPalpaveisItensLayout,gastrointestinal.getViscerasPalpaveis());
//            }
//            if(gastrointestinal.getFormato()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.formato,gastrointestinal.getFormato());
//            if(gastrointestinal.getRuidos()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.ruidos,gastrointestinal.getRuidos());
//            if(gastrointestinal.getTensao()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.tensao,gastrointestinal.getTensao());
//            if(gastrointestinal.getAscite()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.ascite,gastrointestinal.getAscite());
//            if(gastrointestinal.isOstomiasFlag()){
//                checkBoxOstomias.setChecked(true);
//                ostomiasItens.setVisibility(View.VISIBLE);
//                if(!gastrointestinal.getOstomias().isEmpty()){
//                    RealmList<Ostomias> ostomiasRealmList = gastrointestinal.getOstomias();
//                    for(Ostomias ostomia : ostomiasRealmList){
//                        if(ostomia.getTipoOstomia().equals(getString(R.string.Colostomia))){
//                            checkBoxColostomia.setChecked(true);
//                            colostomiaItens.setVisibility(View.VISIBLE);
//                            setRadioButtonFromIdAndDatabase(R.id.colostomiaItens,ostomia.getFuncionamentoOstomia());
//                            setRadioButtonFromIdAndDatabase(R.id.colostomiaItens,ostomia.getQualidadeOstomia());
//                        }
//                        if(ostomia.getTipoOstomia().equals(getString(R.string.Ileostomia))){
//                            checkBoxIleostomia.setChecked(true);
//                            ileostomiaItens.setVisibility(View.VISIBLE);
//                            setRadioButtonFromIdAndDatabase(R.id.ileostomiaItens,ostomia.getFuncionamentoOstomia());
//                            setRadioButtonFromIdAndDatabase(R.id.ileostomiaItens,ostomia.getQualidadeOstomia());
//                        }
//                        if(ostomia.getTipoOstomia().equals(getString(R.string.Jejunostomia))){
//                            checkBoxJejunostomia.setChecked(true);
//                            jejunostomiaItens.setVisibility(View.VISIBLE);
//                            setRadioButtonFromIdAndDatabase(R.id.jejunostomiaItens,ostomia.getFuncionamentoOstomia());
//                            setRadioButtonFromIdAndDatabase(R.id.jejunostomiaItens,ostomia.getQualidadeOstomia());
//                        }
//                    }
//                }
//            }
//        }
    }

}
