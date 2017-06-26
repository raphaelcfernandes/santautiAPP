package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Adapters.Ficha.Neurologico.NeurologicoAdapter;
import santauti.app.Adapters.Ficha.Neurologico.NeurologicoAdapterModel;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Neurologico.NaoSedado;
import santauti.app.Model.Ficha.Neurologico.Neurologico;
import santauti.app.Model.Ficha.Neurologico.Opcionais;
import santauti.app.Model.Ficha.Neurologico.Sedado;
import santauti.app.Model.Ficha.Neurologico.Sedativo;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 13-May-17.
 */

public class NeurologicoActivity extends GenericoActivity {
    private Spinner nivelConscienciaSpinner,ramsaySpinner,rassSpinner,deficitMotorSpinner;
    private Spinner aberturaOcularSpinner,respostaVerbalSpinner,respostaMotoraSpinner,pupilaReatividadeLuzSpinner;
    private Spinner pupilaSimetriaSpinner,pupilaTamanhoSpinner,tipoDecifitSpinner,ladoDeficitSpinner,diferencaPupilarSpinner;
    private RadioButton sedado_sim,sedado_nao;
    private View sedado_sim_layout;
    private View sedado_nao_layout;
    private View avaliacaoPupilarLayout;
    private View deficitMotorLado;
    private View deficitMotorTipo, nivelConsciencia, flutuacaoLayout,inatencaoLayout,pensamentoDesorganizadoLayout,flutuacaoQuestion,inatencaoQuestion,pensamentoQuestion;
    private Realm realm;
    private ImageView avaliacaoPupilarToggleButton;
    private ImageView sedadoToggleButton, flutuacaoToggleButton, inatencaoToggleButton, pensamentoDesorganizadoToggleButton,deliriumToggleButton;
    private MyAnimation myAnimation;
    private List<NeurologicoAdapterModel> neurologicoAdapterModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NeurologicoAdapter neurologicoAdapter;
    private int pupilaRotationAngle=0,sedadoRotationAngle=0,deliriumRotationAngle=0,flutuacaoRotationAngle=0,inatencaoRotationAngle=0,pensamentoRotationAngle=0;
    private TextView diferencaPupilar;
    private ArrayAdapter<String> adapterPupilaDiferenca,adapterPupilaTamanho,adapterPupilaSimetria,adapterPupilaReatividadeLuz,adapterDecifitTipo,adapterDeficitMotor,adapterRass,adapterDecifitLado,adapterNivelConsciencia,adapterAberturaOcular,adapterRespostaVerbal,adapterRespostaMotora,adapterRamsay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neurologico);
        setToolbar(getString(R.string.Neurologico));

        /******************************VARIAVEIS LAYOUTS*************************************/
        sedado_sim_layout = findViewById(R.id.sedado_sim_layout);
        sedado_nao_layout = findViewById(R.id.sedado_nao_layout);
        avaliacaoPupilarLayout = findViewById(R.id.avaliacaoPupilar);

        deficitMotorLado = findViewById(R.id.ladoDecificitLayout);
        deficitMotorTipo = findViewById(R.id.tipoDecificitLayout);
        nivelConsciencia = findViewById(R.id.nivel_consciencia);

        flutuacaoLayout = findViewById(R.id.flutuacao_layout);
        inatencaoLayout = findViewById(R.id.inatencao_layout);
        pensamentoDesorganizadoLayout = findViewById(R.id.pensamento_layout);

        /******************************VARIAVEIS LAYOUTS*************************************/

        /******************************VARIAVEIS RADIOBUTTON*********************************/
        sedado_sim = (RadioButton)findViewById(R.id.sedado_sim);
        sedado_nao = (RadioButton)findViewById(R.id.sedado_nao);
        /******************************VARIAVEIS RADIOBUTTON*********************************/

        /******************************VARIAVEIS TOGGLEBUTTON*******************************/
        sedadoToggleButton = (ImageView)findViewById(R.id.sedadoToggleButton);
        avaliacaoPupilarToggleButton = (ImageView)findViewById(R.id.avaliacaoToggleButton);
        /******************************VARIAVEIS TOGGLEBUTTON*******************************/


        diferencaPupilar = (TextView)findViewById(R.id.diferencaPupilar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        neurologicoAdapter = new NeurologicoAdapter(this,neurologicoAdapterModelList);
        recyclerView.setAdapter(neurologicoAdapter);

        realm = Realm.getDefaultInstance();
        myAnimation = new MyAnimation();
        prepareNavigationButtons();
        prepareNeurologicoSpinners();
        Ficha ficha = getProperFicha();
        if(ficha.getNeurologico()!=null)
            prepareDadosPreviamenteSalvos(ficha);

        avaliacaoPupilarToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pupilaRotationAngle =  myAnimation.rotateImageView180(avaliacaoPupilarToggleButton,pupilaRotationAngle);
                if(avaliacaoPupilarLayout.isShown())
                    myAnimation.slideUpView(NeurologicoActivity.this,avaliacaoPupilarLayout);
                else
                    myAnimation.slideDownView(NeurologicoActivity.this, avaliacaoPupilarLayout);
            }
        });

        sedadoToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sedadoRotationAngle==180) {
                    if (sedado_sim_layout.isShown())
                        myAnimation.slideUpView(NeurologicoActivity.this, sedado_sim_layout);
                    sedadoRotationAngle =  myAnimation.rotateImageView180(sedadoToggleButton,sedadoRotationAngle);
                }
                else{
                    if(sedado_sim.isChecked()) {
                        myAnimation.slideDownView(NeurologicoActivity.this, sedado_sim_layout);
                        sedadoRotationAngle =  myAnimation.rotateImageView180(sedadoToggleButton,sedadoRotationAngle);
                    }
                }
            }
        });

        nivelConscienciaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(nivelConscienciaSpinner.getSelectedItem().toString().equals("Coma")){
                    myAnimation.slideDownView(NeurologicoActivity.this,sedado_nao_layout);
                }
                else
                    myAnimation.slideUpView(NeurologicoActivity.this,sedado_nao_layout);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        deficitMotorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(deficitMotorSpinner.getSelectedItem().toString().equals("Presente")){
//                    deficitMotorToggleButton.setVisibility(View.VISIBLE);
//                    if( getRotationAngle()!=180)
//                         myAnimation.rotateImageView180(deficitMotorToggleButton);
                    myAnimation.slideDownView(NeurologicoActivity.this,deficitMotorTipo);
                    if(tipoDecifitSpinner.getSelectedItem()!=null)
                        myAnimation.slideDownView(NeurologicoActivity.this,deficitMotorTipo);
//                    if(adapterDecifitLado.getPosition(ladoDeficitSpinner.getSelectedItem().toString())!=0)
//                         myAnimation.slideDownView(NeurologicoActivity.this,deficitMotorLado);
                }
                if(deficitMotorSpinner.getSelectedItem().toString().equals("Ausente")){
                    // myAnimation.rotateImageView180(deficitMotorToggleButton);
                    if(deficitMotorTipo.isShown())
                        myAnimation.slideUpView(NeurologicoActivity.this,deficitMotorTipo);
                    if(deficitMotorLado.isShown())
                        myAnimation.slideUpView(NeurologicoActivity.this,deficitMotorLado);
                    // myAnimation.slideUpView(NeurologicoActivity.this,deficitMotorToggleButton);
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
                    myAnimation.slideDownView(NeurologicoActivity.this,deficitMotorLado);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pupilaSimetriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(pupilaSimetriaSpinner.getSelectedItem().equals("Anisocóricas")) {
                    myAnimation.slideDownView(NeurologicoActivity.this,diferencaPupilar);
                    myAnimation.slideDownView(NeurologicoActivity.this,diferencaPupilarSpinner);
                }
                if(diferencaPupilarSpinner.isShown() && diferencaPupilar.isShown() && !pupilaSimetriaSpinner.getSelectedItem().equals("Anisocóricas")){
                    myAnimation.slideUpView(NeurologicoActivity.this,diferencaPupilar);
                    myAnimation.slideUpView(NeurologicoActivity.this,diferencaPupilarSpinner);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RespiradorActivity.class);
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
                intent = new Intent(view.getContext(), HemodinamicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });


        deliriumToggleButton = (ImageView)findViewById(R.id.deliriumToggleButton);

        deliriumToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flutuacaoLayout.isShown() && inatencaoLayout.isShown() && pensamentoDesorganizadoLayout.isShown()){
                    myAnimation.slideUpView(NeurologicoActivity.this, flutuacaoLayout);
                    myAnimation.slideUpView(NeurologicoActivity.this, inatencaoLayout);
                    myAnimation.slideUpView(NeurologicoActivity.this, pensamentoDesorganizadoLayout);
                    myAnimation.rotateImageView180(deliriumToggleButton, deliriumRotationAngle);
                }
                else {
                    myAnimation.slideDownView(NeurologicoActivity.this, flutuacaoLayout);
                    myAnimation.slideDownView(NeurologicoActivity.this, inatencaoLayout);
                    myAnimation.slideDownView(NeurologicoActivity.this, pensamentoDesorganizadoLayout);
                    myAnimation.slideDownView(NeurologicoActivity.this, flutuacaoQuestion);
                    myAnimation.slideDownView(NeurologicoActivity.this, inatencaoQuestion);
                    myAnimation.slideDownView(NeurologicoActivity.this, pensamentoQuestion);

                    myAnimation.rotateImageView180(deliriumToggleButton, deliriumRotationAngle);
                    myAnimation.rotateImageView180(flutuacaoToggleButton, flutuacaoRotationAngle);
                    myAnimation.rotateImageView180(inatencaoToggleButton, inatencaoRotationAngle);
                    myAnimation.rotateImageView180(pensamentoDesorganizadoToggleButton, pensamentoRotationAngle);
                }
            }
        });

        flutuacaoToggleButton = (ImageView)findViewById(R.id.flutuacaoToggleButton);
        flutuacaoQuestion = findViewById(R.id.flutuacao_question);
        flutuacaoToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flutuacaoQuestion.isShown()) {
                    myAnimation.slideUpView(NeurologicoActivity.this,flutuacaoQuestion);
                    myAnimation.rotateImageView180(flutuacaoToggleButton,flutuacaoRotationAngle);
                }
                else {
                    myAnimation.slideDownView(NeurologicoActivity.this, flutuacaoQuestion);
                    myAnimation.rotateImageView180(flutuacaoToggleButton,flutuacaoRotationAngle);
                }
            }
        });

        inatencaoToggleButton = (ImageView)findViewById(R.id.inatencaoToggleButton);
        inatencaoQuestion = findViewById(R.id.inatencao_question);
        inatencaoToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inatencaoQuestion.isShown()) {
                    myAnimation.slideUpView(NeurologicoActivity.this,inatencaoQuestion);
                    myAnimation.rotateImageView180(inatencaoToggleButton,inatencaoRotationAngle);
                }
                else {
                    myAnimation.slideDownView(NeurologicoActivity.this, inatencaoQuestion);
                    myAnimation.rotateImageView180(inatencaoToggleButton,inatencaoRotationAngle);
                }
            }
        });

        pensamentoDesorganizadoToggleButton = (ImageView)findViewById(R.id.pensamentoToggleButton);
        pensamentoQuestion = findViewById(R.id.pensamento_question);
        pensamentoDesorganizadoToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pensamentoQuestion.isShown()){
                    myAnimation.slideUpView(NeurologicoActivity.this,pensamentoQuestion);
                    myAnimation.rotateImageView180(pensamentoDesorganizadoToggleButton,pensamentoRotationAngle);
                }
                else{
                    myAnimation.slideDownView(NeurologicoActivity.this, pensamentoQuestion);
                    myAnimation.rotateImageView180(pensamentoDesorganizadoToggleButton,pensamentoRotationAngle);
                }
            }
        });


    }

    private void prepareDadosPreviamenteSalvos(Ficha ficha) {
        int spinnerPosition;
        //Nivel Consciencia
        spinnerPosition = adapterNivelConsciencia.getPosition(ficha.getNeurologico().getNivelConsciencia());
        nivelConscienciaSpinner.setSelection(spinnerPosition);

        //Avaliacao Pupilar
        spinnerPosition = adapterPupilaTamanho.getPosition(ficha.getNeurologico().getTamanhoPupila());
        pupilaTamanhoSpinner.setSelection(spinnerPosition);
        spinnerPosition = adapterPupilaSimetria.getPosition(ficha.getNeurologico().getSimetriaPupila());
        pupilaSimetriaSpinner.setSelection(spinnerPosition);
        if(ficha.getNeurologico().getSimetriaPupila().equals("Anisocóricas")){
            spinnerPosition = adapterPupilaDiferenca.getPosition(ficha.getNeurologico().getDiferencaPupilar());
            diferencaPupilarSpinner.setSelection(spinnerPosition);
        }
        spinnerPosition = adapterPupilaReatividadeLuz.getPosition(ficha.getNeurologico().getReatividadeLuzPupila());
        pupilaReatividadeLuzSpinner.setSelection(spinnerPosition);

        //Sedado sim
        if(ficha.getNeurologico().getIsSedado()){
            spinnerPosition = adapterRamsay.getPosition(ficha.getNeurologico().getSedado().getRamsay());
            ramsaySpinner.setSelection(spinnerPosition);
            spinnerPosition = adapterRass.getPosition(ficha.getNeurologico().getSedado().getRass());
            rassSpinner.setSelection(spinnerPosition);
            sedado_sim.setChecked(true);
            for(Sedativo sedativo : ficha.getNeurologico().getSedado().getSedativo()){
                NeurologicoAdapterModel neurologicoAdapterModel = new NeurologicoAdapterModel(sedativo.getTipoSedativo(),sedativo.getDoseSedativo());
                neurologicoAdapterModelList.add(neurologicoAdapterModel);
                neurologicoAdapter.notifyItemInserted(neurologicoAdapter.getItemCount()-1);
            }
            neurologicoAdapter.notifyDataSetChanged();
        }

        //Sedado nao
        if(!ficha.getNeurologico().getIsSedado()){
            aberturaOcularSpinner.setSelection(ficha.getNeurologico().getNaoSedado().getAberturaOcular());
            respostaVerbalSpinner.setSelection(ficha.getNeurologico().getNaoSedado().getRespostaVerbal());
            respostaMotoraSpinner.setSelection(ficha.getNeurologico().getNaoSedado().getRespostaMotora());
            sedado_nao.setChecked(true);
        }

        //Deficit Motor Presente
        if(ficha.getNeurologico().getDeficitMotor()){
            spinnerPosition = adapterDeficitMotor.getPosition("Presente");
            deficitMotorSpinner.setSelection(spinnerPosition);
            spinnerPosition = adapterDecifitTipo.getPosition(ficha.getNeurologico().getTipoDecifit());
            tipoDecifitSpinner.setSelection(spinnerPosition);
            spinnerPosition = adapterDecifitLado.getPosition(ficha.getNeurologico().getLadoDeficit());
            ladoDeficitSpinner.setSelection(spinnerPosition);

        }

        //Deficit motor Ausente
        if(!ficha.getNeurologico().getDeficitMotor()){
            spinnerPosition = adapterDeficitMotor.getPosition("Ausente");
            deficitMotorSpinner.setSelection(spinnerPosition);
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

    public void neurologicoSedadoOnRadioButtonClicked(View view){
        switch(view.getId()) {
            case R.id.sedado_sim:
                if(!nivelConsciencia.isShown() || !sedado_sim_layout.isShown()) {//Se layout de nao sedado estiver aparecendo, ele deve ser escondido
                    myAnimation.slideDownView(NeurologicoActivity.this, sedado_sim_layout);
                    myAnimation.slideDownView(NeurologicoActivity.this, nivelConsciencia);
                    /*
                    * Verifica se o toggle button de retrair/exapandir o layout está na posiçao inicial
                    */
                    sedadoRotationAngle =  myAnimation.rotateImageView180(sedadoToggleButton,sedadoRotationAngle);
                }
                break;
            case R.id.sedado_nao:
                if(sedado_sim_layout.isShown()) {
                    myAnimation.slideUpView(NeurologicoActivity.this, sedado_sim_layout);
                    myAnimation.slideUpView(NeurologicoActivity.this,nivelConsciencia);
                    sedadoRotationAngle =  myAnimation.rotateImageView180(sedadoToggleButton,sedadoRotationAngle);
                }
                else if(nivelConsciencia.isShown())
                    myAnimation.slideUpView(NeurologicoActivity.this,nivelConsciencia);
                break;
        }
    }

    private void prepareNeurologicoSpinners(){
        String[] nivelConsciencia = {defaultSpinnerString,"Vigil - hiperaletar",
                "Alerta - Acordado e resposta adequadas às perguntas",
                "Sonolência - Acorda ao chamado, responde às perguntas",
                "Obnubilação - Sonolencia mais profunda, responde as perguntas com voz alta ou após estimulo moderada (balanćar)",
                "Torpor - Sonolencia profunda, responde somente a estimulo doloroso",
                "Coma - Não abre os olhos nem emite sons verbais sob estimulo verbal ou doloroso"};
        String[] ramsay = {defaultSpinnerString,"1 - Combativo","2 - Conduta agressiva","3 - Movimentos despropositados frequentes",
                "4 - Ansioso","5 - Alerta, Calmo","6 - Sem resposta a estímulo verbal ou físico"};
        String[] rass = {defaultSpinnerString,"+4 - Violento, risco para equipe","+3 - Agressivo verbal, arranca tubos e cateteres",
                "+2 - Movimentos sem coordenaćao frequentes","+1 - Ansioso, sem movimentos agressivos",
                "0 - Alerta e calmo","-1 - Despertar c/ estimulo verbal, contato visual > 10 s",
                "-2 - Despertar c/ estimulo verbal, contato visual < 10 s","-3 Ab. ocular ao estimulo verbal, sem contato visual",
                "-4 - Sem resp. ao estimulo verbal, ab. ocular ao toque ","-5 - Sem resposta ao estimulo verbal ou fisico"};
        String[] deficitMotor = {defaultSpinnerString,"Presente","Ausente"};
        String[] deficitTipo = {defaultSpinnerString,"Paresia","Plegia"};
        String[] deficitLado = {defaultSpinnerString,"Esquerdo","Direito","Ambos os lados"};
        String[] aberturaOcular = {defaultSpinnerString,"1 - Nenhuma","2 - À dor","3 - À voz","4 - Espontânea"};
        String[] respostaVerbal = {defaultSpinnerString,"1 - Nenhuma","2 - Palavras incompreensiveis","3 - Palavras inapropriadas","4 - Confusa","5 - Orientada"};
        String[] respostaMotora = {defaultSpinnerString,"1 - Nenhuma","2 - Extensão anormal","3 - Flexão anormal","4 - Movimento de retirada","5 - Localiza dor","6 - Obedece comandos"};
        String[] pupilaReatividadeLuz = {defaultSpinnerString,"RFM+","RFM-"};
        String[] pupilaSimetria = {defaultSpinnerString,"Isocóricas","Anisocóricas"};
        String[] pupilaTamanho = {defaultSpinnerString,"Miose","Normal","Midríase"};
        String[] diferencaPupila = {defaultSpinnerString,"Esquerda > Direita","Direita > Esquerda"};

        diferencaPupilarSpinner = (Spinner)findViewById(R.id.pupila_simetria_diferenca_spinner);
        adapterPupilaDiferenca = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, diferencaPupila){
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
        diferencaPupilarSpinner.setAdapter(adapterPupilaDiferenca);

        nivelConscienciaSpinner = (Spinner) findViewById(R.id.material_spinner1);
        adapterNivelConsciencia = new ArrayAdapter<String>(NeurologicoActivity.this,  R.layout.simple_spinner_item, nivelConsciencia){
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
        adapterRamsay = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, ramsay){
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
        adapterRass = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, rass){
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
        adapterDeficitMotor = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, deficitMotor){
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
        adapterDecifitTipo = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, deficitTipo){
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
        adapterAberturaOcular = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, aberturaOcular){
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
        adapterRespostaVerbal = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, respostaVerbal){
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
        adapterRespostaMotora = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, respostaMotora){
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
        adapterPupilaReatividadeLuz = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, pupilaReatividadeLuz){
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
        adapterPupilaSimetria = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, pupilaSimetria){
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
        adapterPupilaTamanho = new ArrayAdapter<String>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, pupilaTamanho){
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
        boolean nivelConsciencia=false,avaliacaoPupilar=false,sedadoSim=false,sedadoNao=false,deficitMotor=false,opcional=false;
        realm.beginTransaction();
        int i=0;

        Neurologico neurologico = realm.createObject(Neurologico.class);
        if(!nivelConscienciaSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
            neurologico.setNivelConsciencia(nivelConscienciaSpinner.getSelectedItem().toString());
            nivelConsciencia=true;
        }

        /**
         * Avaliaçao Pupilar
         */
        if(!pupilaSimetriaSpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
                !pupilaTamanhoSpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
                !pupilaReatividadeLuzSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
            if(pupilaSimetriaSpinner.getSelectedItem().toString().equals("Anisocóricas") &&
                    !diferencaPupilarSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
                neurologico.setDiferencaPupilar(diferencaPupilarSpinner.getSelectedItem().toString());
            }
            neurologico.setSimetriaPupila(pupilaSimetriaSpinner.getSelectedItem().toString());
            neurologico.setTamanhoPupila(pupilaTamanhoSpinner.getSelectedItem().toString());
            neurologico.setReatividadeLuzPupila(pupilaReatividadeLuzSpinner.getSelectedItem().toString());
            avaliacaoPupilar=true;
        }


        if(sedado_sim.isChecked()){
            if(!ramsaySpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
                    !rassSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
                Sedado sedado = realm.createObject(Sedado.class);
                neurologico.setIsSedado(true);
                sedado.setRamsay(ramsaySpinner.getSelectedItem().toString());
                sedado.setRass(rassSpinner.getSelectedItem().toString());
                sedadoSim=true;
                neurologico.setSedado(sedado);
                //Fzer lista de sedativos
                for(NeurologicoAdapterModel h : neurologicoAdapterModelList){
                    Sedativo sedativo = realm.createObject(Sedativo.class);
                    sedativo.setDoseSedativo(h.getDoseSedativo());
                    sedativo.setTipoSedativo(h.getTipoSedativo());
                    sedado.getSedativo().add(sedativo);
                }
            }

        }
        if(sedado_nao.isChecked()){
            if(!aberturaOcularSpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
                    !respostaMotoraSpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
                    !respostaVerbalSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
                neurologico.setIsSedado(false);
                NaoSedado naoSedado = realm.createObject(NaoSedado.class);
                naoSedado.setAberturaOcular(adapterAberturaOcular.getPosition(aberturaOcularSpinner.getSelectedItem().toString()));
                naoSedado.setRespostaMotora(adapterRespostaMotora.getPosition(respostaMotoraSpinner.getSelectedItem().toString()));
                naoSedado.setRespostaVerbal(adapterRespostaVerbal.getPosition(respostaVerbalSpinner.getSelectedItem().toString()));
                sedadoNao=true;
                neurologico.setNaoSedado(naoSedado);
            }
        }

        if(deficitMotorSpinner.getSelectedItem().toString().equals("Presente")){
            if(!tipoDecifitSpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
                    !ladoDeficitSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
                neurologico.setDeficitMotor(true);
                neurologico.setTipoDecifit(tipoDecifitSpinner.getSelectedItem().toString());
                neurologico.setLadoDeficit(ladoDeficitSpinner.getSelectedItem().toString());
                deficitMotor=true;
            }
        }

        if(deficitMotorSpinner.getSelectedItem().toString().equals("Ausente")) {
            neurologico.setDeficitMotor(false);
            deficitMotor=true;
        }

        if(nivelConsciencia && avaliacaoPupilar && (sedadoSim || sedadoNao) && deficitMotor) {
            Ficha r = getProperFicha();
            r.setNeurologico(neurologico);
            realm.copyToRealmOrUpdate(r);
            changeCardColor();
        }
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
                if(!isTextInpudEditTextEmpty(tipoSedativo) && !isTextInpudEditTextEmpty(doseSedativo))
                    addDataFromDialogIntoAdapter(tipoSedativo.getText().toString(),Integer.parseInt(doseSedativo.getText().toString()));
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

    private void addDataFromDialogIntoAdapter(String droga,int dose){
        if(!droga.equals(defaultSpinnerString)){
            NeurologicoAdapterModel neurologicoAdapterModel = new NeurologicoAdapterModel(droga,dose);
            neurologicoAdapterModelList.add(neurologicoAdapterModel);
            neurologicoAdapter.notifyItemInserted(neurologicoAdapter.getItemCount()-1);
            neurologicoAdapter.notifyDataSetChanged();
        }
    }

}
