package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.Model.Ficha.Neurologico.Neurologico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 13-May-17.
 */

public class NeurologicoActivity extends GenericoActivity {
    private Spinner nivelConscienciaSpinner,ramsaySpinner,rassSpinner,deficitMotorSpinner;
    private Spinner aberturaOcularSpinner,respostaVerbalSpinner,respostaMotoraSpinner,pupilaReatividadeLuzSpinner;
    private Spinner pupilaSimetriaSpinner,pupilaTamanhoSpinner;
    private RadioButton sedado_sim,sedado_nao;
    private View sedado_sim_layout,sedado_nao_layout,neurologico_opcional_layout,avaliacaoPupilarLayout;
    private Realm realm;
    private ImageView avaliacaoPupilarToggleButton,sedadoToggleButton;
    private MyAnimation myAnimation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neurologico);
        setToolbar(getString(R.string.Neurologico));
        sedado_sim = (RadioButton)findViewById(R.id.sedado_sim);
        sedado_nao = (RadioButton)findViewById(R.id.sedado_nao);
        sedado_sim_layout = findViewById(R.id.sedado_sim_layout);
        sedado_nao_layout = findViewById(R.id.sedado_nao_layout);
        neurologico_opcional_layout = findViewById(R.id.neurologico_opcional_layout);
        avaliacaoPupilarLayout = findViewById(R.id.avaliacaoPupilar);
        avaliacaoPupilarToggleButton = (ImageView)findViewById(R.id.avaliacaoToggleButton);
        sedadoToggleButton = (ImageView)findViewById(R.id.sedadoToggleButton);

        avaliacaoPupilarLayout.setVisibility(View.GONE);
        sedado_sim_layout.setVisibility(View.GONE);
        sedado_nao_layout.setVisibility(View.GONE);
        neurologico_opcional_layout.setVisibility(View.GONE);

        myAnimation = new MyAnimation();

        avaliacaoPupilarToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAnimation.rotateImageView180(avaliacaoPupilarToggleButton);

                if(avaliacaoPupilarLayout.isShown()){
                    myAnimation.slide_up(NeurologicoActivity.this,avaliacaoPupilarLayout);
                    avaliacaoPupilarLayout.setVisibility(View.GONE);

                }
                else{
                    myAnimation.slide_down(NeurologicoActivity.this, avaliacaoPupilarLayout);
                    avaliacaoPupilarLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        sedadoToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        prepareNeurologicoSpinners();
        realm = Realm.getDefaultInstance();
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

    public void neuroligicoSedadoOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.sedado_sim:
                if (checked) {
                    sedado_nao_layout.setVisibility(View.GONE);
                    sedado_sim_layout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.sedado_nao:
                if (checked) {
                    sedado_sim_layout.setVisibility(View.GONE);
                    sedado_nao_layout.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    public void neurologicoOpcionalOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.neurologico_opcional_sim:
                if (checked) {
                    neurologico_opcional_layout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.neurologico_opcional_nao:
                if (checked) {
                    neurologico_opcional_layout.setVisibility(View.GONE);
                }
                break;
        }
    }
    private void prepareNeurologicoSpinners(){
        String[] nivelConsciencia = {defaultSpinnerString,"Alerta","Sonolência","Obnubilação","Torpor","Coma"};
        String[] ramsay = {defaultSpinnerString,"Grau 1","Grau 2","Grau 3","Grau 4","Grau 5","Grau 6"};
        String[] rass = {defaultSpinnerString,"+4","+3","+2","+1","0","-1","-2","-3","-4","-5"};
        String[] deficitMotor = {defaultSpinnerString,"Presente","Ausente"};
        String[] aberturaOcular = {defaultSpinnerString,"4 - Espontânea","3 - À voz","2 - À dor","1 - Nenhuma"};
        String[] respostaVerbal = {defaultSpinnerString,"5 - Orientada","4 - Confusa","3 - Palavras inapropriadas","2 - Palavras incompreensiveis","1 - Nenhuma"};
        String[] respostaMotora = {defaultSpinnerString,"6 - Obedece comandos","5 - Localiza dor","4 - Movimento de retirada","3 - Flexão anormal","2 - Extensão anormal","1 - Nenhuma"};
        String[] pupilaReatividadeLuz = {defaultSpinnerString,"RFM+","RFM-"};
        String[] pupilaSimetria = {defaultSpinnerString,"Isocóricas","Anisocóricas"};
        String[] pupilaTamanho = {defaultSpinnerString,"Miose","Midríase"};

        nivelConscienciaSpinner = (Spinner) findViewById(R.id.material_spinner1);
        ArrayAdapter<String> adapterNivelConsciencia = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, nivelConsciencia){
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
        nivelConscienciaSpinner.setAdapter(adapterNivelConsciencia);

        ramsaySpinner = (Spinner)findViewById(R.id.ramsay_spinner);
        ArrayAdapter<String> adapterRamsay = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, ramsay){
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
        ramsaySpinner.setAdapter(adapterRamsay);

        rassSpinner = (Spinner)findViewById(R.id.rass_spinner);
        ArrayAdapter<String> adapterRass = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, rass){
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
        rassSpinner.setAdapter(adapterRass);

        deficitMotorSpinner = (Spinner)findViewById(R.id.deficitMotor_spinner);
        ArrayAdapter<String> adapterDeficitMotor = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, deficitMotor){
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
        deficitMotorSpinner.setAdapter(adapterDeficitMotor);

        aberturaOcularSpinner = (Spinner)findViewById(R.id.aberturaOcular_spinner);
        ArrayAdapter<String> adapterAberturaOcular = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, aberturaOcular){
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
        aberturaOcularSpinner.setAdapter(adapterAberturaOcular);

        respostaVerbalSpinner = (Spinner)findViewById(R.id.respostaVerbal_spinner);
        ArrayAdapter<String> adapterRespostaVerbal = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, respostaVerbal){
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
        respostaVerbalSpinner.setAdapter(adapterRespostaVerbal);

        respostaMotoraSpinner = (Spinner)findViewById(R.id.respostaMotora_spinner);
        ArrayAdapter<String> adapterRespostaMotora = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, respostaMotora){
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
        respostaMotoraSpinner.setAdapter(adapterRespostaMotora);

        pupilaReatividadeLuzSpinner = (Spinner)findViewById(R.id.pupila_reatividade_spinner);
        ArrayAdapter<String> adapterPupilaReatividadeLuz = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, pupilaReatividadeLuz){
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
        pupilaReatividadeLuzSpinner.setAdapter(adapterPupilaReatividadeLuz);

        pupilaSimetriaSpinner = (Spinner)findViewById(R.id.pupila_simetria_spinner);
        ArrayAdapter<String> adapterPupilaSimetria = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, pupilaSimetria){
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
        pupilaSimetriaSpinner.setAdapter(adapterPupilaSimetria);

        pupilaTamanhoSpinner = (Spinner)findViewById(R.id.pupila_tamanho_spinner);
        ArrayAdapter<String> adapterPupilaTamanho = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, pupilaTamanho){
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
        pupilaTamanhoSpinner.setAdapter(adapterPupilaTamanho);


    }

    private void verificaCamposENotificaAdapter(){
//        if(gasometrialArterial.getText().toString().length()>0) {
//            realm.beginTransaction();
//            Metabolico metabolico = realm.createObject(Metabolico.class);
//            metabolico.setGasometriaArterial(gasometriaArterialInput);
//            Ficha r = getProperFicha();
//            r.setMetabolico(metabolico);
//            realm.copyToRealmOrUpdate(r);
//            realm.commitTransaction();
//            changeCardColor();
//        }
    }
}
