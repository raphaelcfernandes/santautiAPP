package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Activities.SnackbarCreator;
import santauti.app.Adapters.Ficha.Neurologico.NeurologicoAdapter;
import santauti.app.Adapters.Ficha.Neurologico.NeurologicoAdapterModel;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.Model.Ficha.Neurologico.NaoSedado;
import santauti.app.Model.Ficha.Neurologico.Neurologico;
import santauti.app.Model.Ficha.Neurologico.Sedado;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 13-May-17.
 */

public class NeurologicoActivity extends GenericoActivity {
    private Spinner nivelConscienciaSpinner,ramsaySpinner,rassSpinner,deficitMotorSpinner;
    private Spinner aberturaOcularSpinner,respostaVerbalSpinner,respostaMotoraSpinner,pupilaReatividadeLuzSpinner;
    private Spinner pupilaSimetriaSpinner,pupilaTamanhoSpinner,tipoDecifitSpinner,ladoDeficitSpinner;
    private RadioButton sedado_sim,sedado_nao,opcionalSim,opcionalNao;
    private View sedado_sim_layout,sedado_nao_layout,neurologico_opcional_layout,avaliacaoPupilarLayout,deficitMotorLado,deficitMotorTipo;
    private Realm realm;
    private ImageView avaliacaoPupilarToggleButton,sedadoToggleButton,deficitMotorToggleButton;
    private MyAnimation myAnimation;
    private Handler handler = new Handler();
    private ArrayAdapter<String> adapterDecifitLado;
    private List<NeurologicoAdapterModel> neurologicoAdapterModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NeurologicoAdapter neurologicoAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neurologico);
        setToolbar(getString(R.string.Neurologico));

        /******************************VARIAVEIS LAYOUTS*************************************/
        sedado_sim_layout = findViewById(R.id.sedado_sim_layout);
        sedado_nao_layout = findViewById(R.id.sedado_nao_layout);
        neurologico_opcional_layout = findViewById(R.id.neurologico_opcional_layout);
        avaliacaoPupilarLayout = findViewById(R.id.avaliacaoPupilar);

        deficitMotorLado = findViewById(R.id.ladoDecificitLayout);
        deficitMotorTipo = findViewById(R.id.tipoDecificitLayout);

        /******************************VARIAVEIS LAYOUTS*************************************/

        /******************************VARIAVEIS RADIOBUTTON*********************************/
        sedado_sim = (RadioButton)findViewById(R.id.sedado_sim);
        sedado_nao = (RadioButton)findViewById(R.id.sedado_nao);
        opcionalSim = (RadioButton)findViewById(R.id.neurologico_opcional_sim);
        opcionalNao = (RadioButton)findViewById(R.id.neurologico_opcional_nao);
        /******************************VARIAVEIS RADIOBUTTON*********************************/

        /******************************VARIAVEIS TOGGLEBUTTON*******************************/
        sedadoToggleButton = (ImageView)findViewById(R.id.sedadoToggleButton);
        avaliacaoPupilarToggleButton = (ImageView)findViewById(R.id.avaliacaoToggleButton);
        deficitMotorToggleButton = (ImageView)findViewById(R.id.deficitMotorToggle);
        /******************************VARIAVEIS TOGGLEBUTTON*******************************/

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        neurologicoAdapter = new NeurologicoAdapter(this,neurologicoAdapterModelList);
        recyclerView.setAdapter(neurologicoAdapter);

        NeurologicoAdapterModel n = new NeurologicoAdapterModel("teste",1);
        neurologicoAdapterModelList.add(n);
        NeurologicoAdapterModel n1 = new NeurologicoAdapterModel("teste2",2);
        neurologicoAdapterModelList.add(n1);
        neurologicoAdapter.notifyDataSetChanged();

        myAnimation = new MyAnimation();
        prepareNeurologicoSpinners();
        realm = Realm.getDefaultInstance();

        avaliacaoPupilarToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAnimation.rotateImageView180(avaliacaoPupilarToggleButton);

                if(avaliacaoPupilarLayout.isShown()){
                    myAnimation.slide_up(NeurologicoActivity.this,avaliacaoPupilarLayout);
                }
                else{
                    myAnimation.slide_down(NeurologicoActivity.this, avaliacaoPupilarLayout);
                }
            }
        });

        sedadoToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myAnimation.getRotationAngle()==180) {
                    if (sedado_nao_layout.isShown())
                        myAnimation.slide_up(NeurologicoActivity.this, sedado_nao_layout);
                    if (sedado_sim_layout.isShown())
                        myAnimation.slide_up(NeurologicoActivity.this, sedado_sim_layout);
                    myAnimation.rotateImageView180(sedadoToggleButton);
                }
                else{
                    if(sedado_sim.isChecked()) {
                        myAnimation.slide_down(NeurologicoActivity.this, sedado_sim_layout);
                        myAnimation.rotateImageView180(sedadoToggleButton);
                    }
                    if(sedado_nao.isChecked()) {
                        myAnimation.slide_down(NeurologicoActivity.this, sedado_nao_layout);
                        myAnimation.rotateImageView180(sedadoToggleButton);
                    }
                }

            }
        });

        deficitMotorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(deficitMotorSpinner.getSelectedItem().toString().equals("Presente")){
//                    deficitMotorToggleButton.setVisibility(View.VISIBLE);
//                    if(myAnimation.getRotationAngle()!=180)
//                        myAnimation.rotateImageView180(deficitMotorToggleButton);
                    myAnimation.slide_down(NeurologicoActivity.this,deficitMotorTipo);
                    if(tipoDecifitSpinner.getSelectedItem()!=null)
                        myAnimation.slide_down(NeurologicoActivity.this,deficitMotorTipo);
//                    if(adapterDecifitLado.getPosition(ladoDeficitSpinner.getSelectedItem().toString())!=0)
//                        myAnimation.slide_down(NeurologicoActivity.this,deficitMotorLado);
                }
                if(deficitMotorSpinner.getSelectedItem().toString().equals("Ausente")){
                    //myAnimation.rotateImageView180(deficitMotorToggleButton);
                    if(deficitMotorTipo.isShown())
                        myAnimation.slide_up(NeurologicoActivity.this,deficitMotorTipo);
                    if(deficitMotorLado.isShown())
                        myAnimation.slide_up(NeurologicoActivity.this,deficitMotorLado);
                    //myAnimation.slide_up(NeurologicoActivity.this,deficitMotorToggleButton);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tipoDecifitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!tipoDecifitSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
                    myAnimation.slide_down(NeurologicoActivity.this,deficitMotorLado);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                if(sedado_nao_layout.isShown()) {
                    myAnimation.slide_up(NeurologicoActivity.this, sedado_nao_layout);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            myAnimation.slide_down(NeurologicoActivity.this, sedado_sim_layout);
                        }
                    }, 250);
                }
                else if(!sedado_sim_layout.isShown())
                    myAnimation.slide_down(NeurologicoActivity.this,sedado_sim_layout);
                if(myAnimation.getRotationAngle()!=180)
                    myAnimation.rotateImageView180(sedadoToggleButton);
                break;
            case R.id.sedado_nao:
                if(sedado_sim_layout.isShown()) {
                    myAnimation.slide_up(NeurologicoActivity.this, sedado_sim_layout);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            myAnimation.slide_down(NeurologicoActivity.this, sedado_nao_layout);
                        }
                    }, 250);
                }
                else if(!sedado_nao_layout.isShown())
                    myAnimation.slide_down(NeurologicoActivity.this,sedado_nao_layout);
                if(myAnimation.getRotationAngle()!=180)
                    myAnimation.rotateImageView180(sedadoToggleButton);
                break;
        }
    }

    public void neurologicoOpcionalOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.neurologico_opcional_sim:
                if(!neurologico_opcional_layout.isShown())
                    myAnimation.slide_down(NeurologicoActivity.this,neurologico_opcional_layout);
                break;
            case R.id.neurologico_opcional_nao:
                if(neurologico_opcional_layout.isShown())
                    myAnimation.slide_up(NeurologicoActivity.this,neurologico_opcional_layout);
                break;
        }
    }

    private void prepareNeurologicoSpinners(){
        String[] nivelConsciencia = {defaultSpinnerString,"Alerta","Sonolência","Obnubilação","Torpor","Coma"};
        String[] ramsay = {defaultSpinnerString,"Grau 1","Grau 2","Grau 3","Grau 4","Grau 5","Grau 6"};
        String[] rass = {defaultSpinnerString,"+4","+3","+2","+1","0","-1","-2","-3","-4","-5"};
        String[] deficitMotor = {defaultSpinnerString,"Presente","Ausente"};
        String[] deficitTipo = {defaultSpinnerString,"Paresia","Plegia"};
        String[] deficitLado = {defaultSpinnerString,"Esquerdo","Direito","Ambos os lados"};
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

        tipoDecifitSpinner = (Spinner)findViewById(R.id.deficitMotor_tipo);
        ArrayAdapter<String> adapterDecifitTipo = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, deficitTipo){
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
        tipoDecifitSpinner.setAdapter(adapterDecifitTipo);

        ladoDeficitSpinner = (Spinner)findViewById(R.id.deficitMotor_lado);
        adapterDecifitLado = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, deficitLado){
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
        ladoDeficitSpinner.setAdapter(adapterDecifitLado);

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
        realm.beginTransaction();
        int i=0;
        Neurologico neurologico = realm.createObject(Neurologico.class);
        if(!nivelConscienciaSpinner.getSelectedItem().toString().equals(defaultSpinnerString)) {
            neurologico.setNivelConsciencia(nivelConscienciaSpinner.getSelectedItem().toString());
            i++;
        }
        if(!pupilaTamanhoSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
            neurologico.setTamanhoPupila(pupilaTamanhoSpinner.getSelectedItem().toString());
            i++;
        }
        if(!isSpinnerDefault(pupilaSimetriaSpinner.getSelectedItem().toString())){
            neurologico.setSimetriaPupila(pupilaSimetriaSpinner.getSelectedItem().toString());
            i++;
        }
        if(!isSpinnerDefault(pupilaReatividadeLuzSpinner.getSelectedItem().toString())){
            neurologico.setReatividadeLuzPupila(pupilaReatividadeLuzSpinner.getSelectedItem().toString());
            i++;
        }

        if(deficitMotorSpinner.getSelectedItem().toString().equals("Presente")){
            neurologico.setDeficitMotor(1);
            if(!tipoDecifitSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
                neurologico.setTipoDecifit(tipoDecifitSpinner.getSelectedItem().toString());
                i++;
            }
            if(!ladoDeficitSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
                neurologico.setLadoDeficit(ladoDeficitSpinner.getSelectedItem().toString());
                i++;
            }
        }
        else if(deficitMotorSpinner.getSelectedItem().toString().equals("Ausente")){
            neurologico.setDeficitMotor(0);
        }


        if(sedado_sim.isChecked()){
            Sedado sedado = realm.createObject(Sedado.class);
            neurologico.setIsSedado(1); //Posso alterar isso no model do neurologico? Setar NaoSedado e criar objeto Sedado automaticamente
            neurologico.setNaoSedado(null);

            if(!ramsaySpinner.getSelectedItem().toString().equals(defaultSpinnerString)) {
                sedado.setRamsay(ramsaySpinner.getSelectedItem().toString());
                i++;
            }
            if(!rassSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
                sedado.setRass(rassSpinner.getSelectedItem().toString());
                i++;
            }
//            if(!isTextInpudEditTextEmpty(tipoSedativo) && !isTextInpudEditTextEmpty(doseSedativo)){
//                sedado.putIntoSedativo(tipoSedativo.toString(),Integer.parseInt(doseSedativo.toString()));
//                i++;
//            }
        }

        else if(sedado_nao.isChecked()){
            NaoSedado naoSedado = realm.createObject(NaoSedado.class);
            if(!isSpinnerDefault(aberturaOcularSpinner.getSelectedItem().toString())){

            }
            if(!isSpinnerDefault(respostaVerbalSpinner.getSelectedItem().toString())){

            }
            if(!isSpinnerDefault(respostaMotoraSpinner.getSelectedItem().toString())){

            }

        }
        if(opcionalSim.isChecked()){

        }
        else if(opcionalSim.isChecked()){

        }
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
        realm.commitTransaction();
    }

    public void addSedativo(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(NeurologicoActivity.this);
        builder.setTitle("Adicionar Sedativo");

        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.neurologico_dialog_sedativo,null);
        dialogView.requestFocus();
        final TextInputEditText doseSedativo = (TextInputEditText) dialogView.findViewById(R.id.doseSedativo);
        final TextInputEditText tipoSedativo = (TextInputEditText) dialogView.findViewById(R.id.tipoSedativo);


        builder.setView(dialogView);
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        final AlertDialog dialog = builder.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
    }
}
