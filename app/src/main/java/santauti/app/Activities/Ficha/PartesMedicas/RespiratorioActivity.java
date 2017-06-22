package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;
import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Respiratorio.Respiratorio;
import santauti.app.Model.Ficha.Respiratorio.RespiratorioInvasiva;
import santauti.app.Model.Ficha.Respiratorio.RespiratorioNaoInvasiva;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class RespiratorioActivity extends GenericoActivity {
    private TextInputEditText volume,fio2,freqRespiratoria,peep,pressaoCuff,volumeNaoInvasivo,ipap,epap,saturacao;
    private LinearLayout invasivoView,naoInvasivoView;
    private Spinner respiratorioSpinner;
    private RadioButton invasivo,naoInvasivo;
    private Realm realm;
    private ArrayAdapter<String> adapterRespiratorio;
    private Ficha ficha;
    private MyAnimation myAnimation;
    private Handler handler = new Handler();

    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respiratorio);
        findViewById(R.id.respiratorio_layout).requestFocus();
        setToolbar(getString(R.string.Respiratorio));
        invasivoView = (LinearLayout)findViewById(R.id.ventilacao_invasiva);
        naoInvasivoView = (LinearLayout)findViewById(R.id.ventilacao_nao_invasiva);

        invasivo = (RadioButton)findViewById(R.id.respiratorio_invasivo);
        naoInvasivo = (RadioButton)findViewById(R.id.respiratorio_nao_invasivo);

        prepareNavigationButtons();

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), GastrointestinalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, getIntent().getIntExtra("idFicha",0), intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HemodinamicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, getIntent().getIntExtra("idFicha",0), intent);
                startActivity(intent);
                exitActivityToLeft();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        prepareRespiratorioInvasivo();
        prepareRespiratorioNaoInvasivo();


        myAnimation = new MyAnimation();
        realm = Realm.getDefaultInstance();
        ficha = getProperFicha();
        if(ficha.getRespiratorio()!=null){
            if(ficha.getRespiratorio().getInvasiva()==1){//Ventilação invasiva
                preencherInvasivo();
            }
            else{//Ventilação não invasiva
                preencherNaoInvasivo();
            }
        }

    }

    private void preencherNaoInvasivo(){
        naoInvasivo.setChecked(true);
        volumeNaoInvasivo.setText(String.valueOf(ficha.getRespiratorio().getVolume()));
        ipap.setText(String.valueOf(ficha.getRespiratorio().getRespiratorioNaoInvasiva().getIpap()));
        epap.setText(String.valueOf(ficha.getRespiratorio().getRespiratorioNaoInvasiva().getEpap()));
        saturacao.setText(String.valueOf(ficha.getRespiratorio().getRespiratorioNaoInvasiva().getSaturacao()));
        myAnimation.slideDownView(this,naoInvasivoView);
    }

    private void preencherInvasivo(){
        invasivo.setChecked(true);
        volume.setText(String.valueOf(ficha.getRespiratorio().getVolume()));
        fio2.setText(String.valueOf(ficha.getRespiratorio().getRespiratorioInvasiva().getFio2()));
        freqRespiratoria.setText(String.valueOf(ficha.getRespiratorio().getRespiratorioInvasiva().getFrequenciaRespiratoria()));
        peep.setText(String.valueOf(ficha.getRespiratorio().getRespiratorioInvasiva().getPeep()));
        pressaoCuff.setText(String.valueOf(ficha.getRespiratorio().getRespiratorioInvasiva().getPressaoCuff()));
        int spinnerPosition = adapterRespiratorio.getPosition(ficha.getRespiratorio().getRespiratorioInvasiva().getModoVentilatorio());
        respiratorioSpinner.setSelection(spinnerPosition);
        myAnimation.slideDownView(this,invasivoView);
    }

    private void prepareRespiratorioNaoInvasivo() {
        volumeNaoInvasivo = (TextInputEditText)findViewById(R.id.respiratorio_volume_naoInvasivo);
        ipap = (TextInputEditText)findViewById(R.id.respiratorio_IPAP);
        epap = (TextInputEditText)findViewById(R.id.respiratorio_epap);
        saturacao = (TextInputEditText)findViewById(R.id.respiratorio_saturacao);
    }

    private void prepareRespiratorioInvasivo() {
        volume = (TextInputEditText)findViewById(R.id.respiratorio_volume);
        fio2 = (TextInputEditText)findViewById(R.id.respiratorio_fio2);
        freqRespiratoria = (TextInputEditText)findViewById(R.id.freq_respiratoria);
        peep = (TextInputEditText)findViewById(R.id.respiratorio_peep);
        pressaoCuff = (TextInputEditText)findViewById(R.id.respiratorio_pressaoCuff);
        prepareRespiratorioSpinners();
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
        int i;
        Respiratorio respiratorio = realm.createObject(Respiratorio.class);
        if(invasivo.isChecked()){
            i=0;
            respiratorio.setInvasiva(1);
            RespiratorioInvasiva respiratorioInvasiva = realm.createObject(RespiratorioInvasiva.class);
            if(!isTextInpudEditTextEmpty(volume)){
                respiratorio.setVolume(Integer.parseInt(volume.getText().toString()));
                i++;
            }
            if(!isTextInpudEditTextEmpty(fio2)){
                respiratorioInvasiva.setFio2(Integer.parseInt(fio2.getText().toString()));
                i++;
            }
            if(!isTextInpudEditTextEmpty(freqRespiratoria)){
                respiratorioInvasiva.setFrequenciaRespiratoria(Integer.parseInt(freqRespiratoria.getText().toString()));
                i++;
            }
            if(!isTextInpudEditTextEmpty(peep)){
                respiratorioInvasiva.setPeep(Integer.parseInt(peep.getText().toString()));
                i++;
            }
            if(!isTextInpudEditTextEmpty(pressaoCuff)){
                respiratorioInvasiva.setPressaoCuff(Integer.parseInt(pressaoCuff.getText().toString()));
                i++;
            }
            if(!respiratorioSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
                respiratorioInvasiva.setModoVentilatorio(respiratorioSpinner.getSelectedItem().toString());
                i++;
            }
            if(i==6){
                respiratorio.setRespiratorioInvasiva(respiratorioInvasiva);
                Ficha r = getProperFicha();
                r.setRespiratorio(respiratorio);
                realm.copyToRealmOrUpdate(r);
                changeCardColor();
            }
        }
        else if(naoInvasivo.isChecked()){
            i=0;
            respiratorio.setInvasiva(0);
            RespiratorioNaoInvasiva respiratorioNaoInvasiva = realm.createObject(RespiratorioNaoInvasiva.class);
            if(!isTextInpudEditTextEmpty(volumeNaoInvasivo)){
                respiratorio.setVolume(Integer.parseInt(volumeNaoInvasivo.getText().toString()));
                i++;
            }
            if(!isTextInpudEditTextEmpty(ipap)){
                respiratorioNaoInvasiva.setIpap(Integer.parseInt(ipap.getText().toString()));
                i++;
            }
            if(!isTextInpudEditTextEmpty(epap)){
                respiratorioNaoInvasiva.setEpap(Integer.parseInt(epap.getText().toString()));
                i++;
            }
            if(!isTextInpudEditTextEmpty(saturacao)){
                respiratorioNaoInvasiva.setSaturacao(Integer.parseInt(saturacao.getText().toString()));
                i++;
            }
            if(i==4){
                respiratorio.setRespiratorioNaoInvasiva(respiratorioNaoInvasiva);
                Ficha r = getProperFicha();
                r.setRespiratorio(respiratorio);
                realm.copyToRealmOrUpdate(r);
                changeCardColor();
            }
        }
        realm.commitTransaction();
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

    private void prepareRespiratorioSpinners(){
        String[] modoVentilatorio = {defaultSpinnerString,"A/C,VCV","A/C,PCV","PSV","SIMV"};

        respiratorioSpinner = (Spinner) findViewById(R.id.ventilacao_invasiva_spinner);
        adapterRespiratorio = new ArrayAdapter<String>(RespiratorioActivity.this, android.R.layout.simple_dropdown_item_1line, modoVentilatorio) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        respiratorioSpinner.setAdapter(adapterRespiratorio);

    }

    public void respiratorioVentilacaoOnRadioButtonClicked(View view){
        switch(view.getId()) {
            case R.id.respiratorio_invasivo:
                if(ficha.getRespiratorio()!=null && ficha.getRespiratorio().getRespiratorioInvasiva()!=null)
                    preencherInvasivo();
                if(naoInvasivoView.isShown()) {
                    myAnimation.slideUpView(this, naoInvasivoView);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            myAnimation.slideDownView(RespiratorioActivity.this, invasivoView);
                        }
                    }, 250);
                }
                else if(!invasivoView.isShown())
                    myAnimation.slideDownView(RespiratorioActivity.this, invasivoView);
                break;
            case R.id.respiratorio_nao_invasivo:
                if(ficha.getRespiratorio()!=null && ficha.getRespiratorio().getRespiratorioNaoInvasiva()!=null)
                    preencherNaoInvasivo();
                if(invasivoView.isShown()) {
                    myAnimation.slideUpView(this, invasivoView);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            myAnimation.slideDownView(RespiratorioActivity.this, naoInvasivoView);
                        }
                    }, 250);
                }
                else if(!naoInvasivoView.isShown())
                    myAnimation.slideDownView(RespiratorioActivity.this, naoInvasivoView);
                break;
        }
    }
}
