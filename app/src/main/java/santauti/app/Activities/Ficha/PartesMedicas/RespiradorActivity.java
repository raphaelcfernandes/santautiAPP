package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.widget.AppCompatCheckBox;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.InputFilterMin;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Respirador;
import santauti.app.R;

/**
 * Created by raphael on 6/23/17.
 */

public class RespiradorActivity extends GenericoActivity{
    private AppCompatCheckBox checkBoxEmVentilacaoMecanica;
    private View bipap, ventilacaoMecanica,naoInvasivo;
    private MyAnimation myAnimation;
    private View respiradorLayout;
    private TextInputEditText volumeBipap,IPAP,EPAP,saturacao,oxigenio, respiratorioVolumeNaoInvasiva, respiratorioPeepNaoInvasiva, respiratorioFio2NaoInvasiva,
            freqRespiratoriaPacienteNaoInvasiva, freqRespiratoriaRespiradorNaoInvasiva,respiratorioFio2Mecanico,respiratorioPeepMecanico,
            respiratorioVolumeMecanico,freqRespiratoriaPacienteMecanico,freqRespiratoriaRespiradorMecanico;
    private RadioGroup modoVentilatorio,parametrosRadioGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respirador);
        setToolbar(getString(R.string.Respirador));
        prepareNavigationButtons();
        /****************************VIEWS*****************************/
        bipap = findViewById(R.id.bipap);
        ventilacaoMecanica = findViewById(R.id.ventilacao_mecanica);
        naoInvasivo = findViewById(R.id.ventilacao_naoInvasiva);
        respiradorLayout = findViewById(R.id.respiradorLayout);
        /****************************VIEWS*****************************/

        /***************************RADIOGROUP*************************/
        modoVentilatorio = (RadioGroup)findViewById(R.id.modoVentilatorio);
        parametrosRadioGroup = (RadioGroup)findViewById(R.id.parametrosRadioGroup);
        /***************************RADIOGROUP*************************/


        /***************************TEXTINPUTEDITTEXT******************/
        volumeBipap = (TextInputEditText)findViewById(R.id.volumeBipap);
        volumeBipap.setFilters(new InputFilter[]{ new InputFilterMin(1)});
        IPAP = (TextInputEditText)findViewById(R.id.IPAP);
        IPAP.setFilters(new InputFilter[]{new InputFilterMin(1)});
        EPAP = (TextInputEditText)findViewById(R.id.EPAP);
        EPAP.setFilters(new InputFilter[]{new InputFilterMin(1)});
        saturacao = (TextInputEditText)findViewById(R.id.saturacao);
        saturacao.setFilters(new InputFilter[]{new InputFilterMin(1)});
        oxigenio = (TextInputEditText)findViewById(R.id.oxigenio);
        oxigenio.setFilters(new InputFilter[]{new InputFilterMin(1)});
        respiratorioVolumeNaoInvasiva = (TextInputEditText)findViewById(R.id.respiratorio_volume);
        respiratorioVolumeNaoInvasiva.setFilters(new InputFilter[]{new InputFilterMin(1)});
        respiratorioPeepNaoInvasiva = (TextInputEditText)findViewById(R.id.respiratorio_peep);
        respiratorioPeepNaoInvasiva.setFilters(new InputFilter[]{new InputFilterMin(1)});
        respiratorioFio2NaoInvasiva = (TextInputEditText)findViewById(R.id.respiratorioFio2NaoInvaviso);
        respiratorioFio2NaoInvasiva.setFilters(new InputFilter[]{new InputFilterMin(1)});
        freqRespiratoriaPacienteNaoInvasiva = (TextInputEditText)findViewById(R.id.freqRespiratoriaPaciente);
        freqRespiratoriaPacienteNaoInvasiva.setFilters(new InputFilter[]{new InputFilterMin(1)});
        freqRespiratoriaRespiradorNaoInvasiva = (TextInputEditText)findViewById(R.id.freqRespiratoriaRespirador);
        freqRespiratoriaRespiradorNaoInvasiva.setFilters(new InputFilter[]{new InputFilterMin(1)});
        respiratorioFio2Mecanico = (TextInputEditText)findViewById(R.id.respiratorioFio2Mecanico);
        respiratorioFio2Mecanico.setFilters(new InputFilter[]{new InputFilterMin(1)});
        respiratorioPeepMecanico = (TextInputEditText)findViewById(R.id.respiratorioPeepMecanico);
        respiratorioPeepMecanico.setFilters(new InputFilter[]{new InputFilterMin(1)});
        respiratorioVolumeMecanico = (TextInputEditText)findViewById(R.id.respiratorioVolumeMecanico);
        respiratorioVolumeMecanico.setFilters(new InputFilter[]{new InputFilterMin(1)});
        freqRespiratoriaPacienteMecanico = (TextInputEditText)findViewById(R.id.freqRespiratoriaPacienteMecanico);
        freqRespiratoriaPacienteMecanico.setFilters(new InputFilter[]{new InputFilterMin(1)});
        freqRespiratoriaRespiradorMecanico = (TextInputEditText)findViewById(R.id.freqRespiratoriaRespiradorMecanico);
        freqRespiratoriaRespiradorMecanico.setFilters(new InputFilter[]{new InputFilterMin(1)});
        /***************************TEXTINPUTEDITTEXT******************/

        /***************************CHECKBOX***************************/
        checkBoxEmVentilacaoMecanica = (AppCompatCheckBox)findViewById(R.id.checkboxEmVentilacaoMecanica);
        checkBoxEmVentilacaoMecanica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBoxEmVentilacaoMecanica.isChecked() && respiradorLayout.isShown())
                    myAnimation.slideUpView(RespiradorActivity.this,respiradorLayout);
                else
                if(!respiradorLayout.isShown() && checkBoxEmVentilacaoMecanica.isChecked())
                    myAnimation.slideDownView(RespiradorActivity.this,respiradorLayout);
            }
        });
        /***************************CHECKBOX***************************/
        setupUI(findViewById(R.id.respirador_activity));
        myAnimation = new MyAnimation();
        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), DispositivoActivity.class);
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
                intent = new Intent(view.getContext(), NeurologicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToRight();
            }
        });

        setRespiradorFromDatabase();

    }

    private void setRespiradorFromDatabase(){
//        Ficha ficha = getProperFicha();
//        if(ficha.getRespirador()!=null){
//            Respirador respirador = ficha.getRespirador();
//            if(respirador.isEmVentilacaoMecanica()){
//                checkBoxEmVentilacaoMecanica.setChecked(true);
//                myAnimation.slideDownView(getApplicationContext(),respiradorLayout);
//                if(respirador.getModoVentilatorio()!=null){
//                    setRadioButtonFromIdAndDatabase(R.id.modoVentilatorioLinearLayout,respirador.getModoVentilatorio());
//                    if(getStringOfRadioButtonSelectedFromRadioGroup(modoVentilatorio).equals(getString(R.string.BIPAP))){
//                        myAnimation.slideDownView(getApplicationContext(),bipap);
//                        volumeBipap.setText(Integer.toString(respirador.getVolume()));
//                        IPAP.setText(Integer.toString(respirador.getIpap()));
//                        EPAP.setText(Integer.toString(respirador.getEpap()));
//                        saturacao.setText(Integer.toString(respirador.getSaturacao()));
//                        oxigenio.setText(Integer.toString(respirador.getOxigenio()));
//                    }
//                    if(getStringOfRadioButtonSelectedFromRadioGroup(modoVentilatorio).equals(getString(R.string.NaoInvasiva))){
//                        myAnimation.slideDownView(getApplicationContext(),naoInvasivo);
//                        respiratorioVolumeNaoInvasiva.setText(Integer.toString(respirador.getVolume()));
//                        respiratorioPeepNaoInvasiva.setText(Integer.toString(respirador.getPeep()));
//                        respiratorioFio2NaoInvasiva.setText(Integer.toString(respirador.getFio2()));
//                        freqRespiratoriaPacienteNaoInvasiva.setText(Integer.toString(respirador.getFreqRespiratoriaPaciente()));
//                        freqRespiratoriaRespiradorNaoInvasiva.setText(Integer.toString(respirador.getFreqRespiratoriaRespirador()));
//                    }
//                    if(getStringOfRadioButtonSelectedFromRadioGroup(modoVentilatorio).equals(getString(R.string.Mecanica))){
//                        myAnimation.slideDownView(getApplicationContext(), ventilacaoMecanica);
//                        setRadioButtonFromIdAndDatabase(R.id.parametros,respirador.getParametros());
//                        respiratorioFio2Mecanico.setText(Integer.toString(respirador.getFio2()));
//                        respiratorioPeepMecanico.setText(Integer.toString(respirador.getPeep()));
//                        respiratorioVolumeMecanico.setText(Integer.toString(respirador.getVolume()));
//                        freqRespiratoriaPacienteMecanico.setText(Integer.toString(respirador.getFreqRespiratoriaPaciente()));
//                        freqRespiratoriaRespiradorMecanico.setText(Integer.toString(respirador.getFreqRespiratoriaRespirador()));
//                    }
//                }
//            }
//        }
    }

    public void emVentilacaoOnClick(View view){
        if(respiradorLayout.isShown())
            myAnimation.slideUpView(RespiradorActivity.this,respiradorLayout);
        else
        if(checkBoxEmVentilacaoMecanica.isChecked())
            myAnimation.slideDownView(RespiradorActivity.this, respiradorLayout);
    }

    public void modoVentilatorioOnClick(View view){
        switch(view.getId()) {
            case R.id.naoInvasivoModoVentilatorio:
                if(bipap.isShown())
                    bipap.setVisibility(View.GONE);
                if(ventilacaoMecanica.isShown())
                    ventilacaoMecanica.setVisibility(View.GONE);
                naoInvasivo.setVisibility(View.VISIBLE);
                break;
            case R.id.bipapModoVentilatorio:
                if(ventilacaoMecanica.isShown())
                    ventilacaoMecanica.setVisibility(View.GONE);
                if(naoInvasivo.isShown())
                    naoInvasivo.setVisibility(View.GONE);
                bipap.setVisibility(View.VISIBLE);
                break;
            case R.id.mecanicaModoVentilatorio:
                if(bipap.isShown())
                    bipap.setVisibility(View.GONE);
                if(naoInvasivo.isShown())
                    naoInvasivo.setVisibility(View.GONE);
                ventilacaoMecanica.setVisibility(View.VISIBLE);
                break;
        }
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
        Respirador respirador = new Respirador();
        if(checkBoxEmVentilacaoMecanica.isChecked()){
            respirador.setEmVentilacaoMecanica(true);
            if(modoVentilatorio.getCheckedRadioButtonId()!=-1){
                respirador.setModoVentilatorio(getStringOfRadioButtonSelectedFromRadioGroup(modoVentilatorio));
                if(respirador.getModoVentilatorio().equals(getString(R.string.NaoInvasiva))){
                    if(!isTextInputEditTextEmpty(respiratorioVolumeNaoInvasiva))
                        respirador.setVolume(getIntegerFromTextInputEditText(respiratorioVolumeNaoInvasiva));
                    if(!isTextInputEditTextEmpty(respiratorioPeepNaoInvasiva))
                        respirador.setPeep(getIntegerFromTextInputEditText(respiratorioPeepNaoInvasiva));
                    if(!isTextInputEditTextEmpty(respiratorioFio2NaoInvasiva))
                        respirador.setFio2(getIntegerFromTextInputEditText(respiratorioFio2NaoInvasiva));
                    if(!isTextInputEditTextEmpty(freqRespiratoriaPacienteNaoInvasiva))
                        respirador.setFreqRespiratoriaPaciente(getIntegerFromTextInputEditText(freqRespiratoriaPacienteNaoInvasiva));
                    if(!isTextInputEditTextEmpty(freqRespiratoriaRespiradorNaoInvasiva))
                        respirador.setFreqRespiratoriaRespirador(getIntegerFromTextInputEditText(freqRespiratoriaRespiradorNaoInvasiva));
                }
                else if(respirador.getModoVentilatorio().equals(getString(R.string.BIPAP))){
                    if(!isTextInputEditTextEmpty(volumeBipap))
                        respirador.setVolume(getIntegerFromTextInputEditText(volumeBipap));
                    if(!isTextInputEditTextEmpty(IPAP))
                        respirador.setIpap(getIntegerFromTextInputEditText(IPAP));
                    if(!isTextInputEditTextEmpty(EPAP))
                        respirador.setEpap(getIntegerFromTextInputEditText(EPAP));
                    if(!isTextInputEditTextEmpty(saturacao))
                        respirador.setSaturacao(getIntegerFromTextInputEditText(saturacao));
                    if(!isTextInputEditTextEmpty(oxigenio))
                        respirador.setOxigenio(getIntegerFromTextInputEditText(oxigenio));
                }
                else{
                    if(parametrosRadioGroup.getCheckedRadioButtonId()!=-1)
                        respirador.setParametros(getStringOfRadioButtonSelectedFromRadioGroup(parametrosRadioGroup));
                    if(!isTextInputEditTextEmpty(respiratorioFio2Mecanico))
                        respirador.setFio2(getIntegerFromTextInputEditText(respiratorioFio2Mecanico));
                    if(!isTextInputEditTextEmpty(respiratorioPeepMecanico))
                        respirador.setPeep(getIntegerFromTextInputEditText(respiratorioPeepMecanico));
                    if(!isTextInputEditTextEmpty(respiratorioVolumeMecanico))
                        respirador.setVolume(getIntegerFromTextInputEditText(respiratorioVolumeMecanico));
                    if(!isTextInputEditTextEmpty(freqRespiratoriaPacienteMecanico))
                        respirador.setFreqRespiratoriaPaciente(getIntegerFromTextInputEditText(freqRespiratoriaPacienteMecanico));
                    if(!isTextInputEditTextEmpty(freqRespiratoriaRespiradorMecanico))
                        respirador.setFreqRespiratoriaRespirador(getIntegerFromTextInputEditText(freqRespiratoriaRespiradorMecanico));
                }
            }
        }
        else
            respirador.setEmVentilacaoMecanica(false);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).updateChildren(respirador.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                changeCardColorToGreen();
            }
        });
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
}
