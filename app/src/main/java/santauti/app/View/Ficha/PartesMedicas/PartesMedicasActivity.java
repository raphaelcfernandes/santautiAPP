package santauti.app.View.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 10-May-17.
 */

public class PartesMedicasActivity extends AppCompatActivity{
    private int x=0;
    private Intent intent;

    /************Variaveis de Neurologico*************/
    Spinner nivelConscienciaSpinner,ramsaySpinner,rassSpinner,deficitMotorSpinner,aberturaOcularSpinner,respostaVerbalSpinner,respostaMotoraSpinner;
    RadioButton sedado_sim,sedado_nao;
    private View sedado_sim_layout,sedado_nao_layout,neurologico_opcional_layout;
    /************Variaveis de Neurologico*************/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        x=intent.getIntExtra("Position",x);
        setContentView(R.layout.activity_ficha_partes_medicas);
        if(x==0) //Neurologico
            criarNeurologico(x);
        else if(x==1)//Hemodinamico
            criarHemodinamico(x);
        else if(x==2)//Respiratorio
            criarRespiratorio(x);
        else if(x==3)//Gastrointestinal
            criarGastrointestinal(x);
        else if(x==4)//Renal
            criarRenal(x);
        else if(x==5)//Hematologico
            criarHematologico(x);
        else if(x==6)//Endocrino
            criarEndocrino(x);
        else if(x==7)//Infeccioso
            criarInfeccioso(x);
        else if(x==8)//Metabolico
            criarMetabolico(x);
    }

    private void criarNeurologico(int x){
        prepareNeurologicoSpinners();
        getSupportActionBar().setTitle(R.string.Neurologico);

        sedado_sim = (RadioButton)findViewById(R.id.sedado_sim);
        sedado_nao = (RadioButton)findViewById(R.id.sedado_nao);
        sedado_sim_layout = findViewById(R.id.sedado_sim_layout);
        sedado_nao_layout = findViewById(R.id.sedado_nao_layout);
        neurologico_opcional_layout = findViewById(R.id.neurologico_opcional_layout);
        sedado_sim_layout.setVisibility(View.GONE);
        sedado_nao_layout.setVisibility(View.GONE);
        neurologico_opcional_layout.setVisibility(View.GONE);
        buildIntent(x);
    }

    /************Métodos de Neurologico*************/
    private void prepareNeurologicoSpinners(){
        String[] nivelConsciencia = {"Alerta","Sonolência","Obnubilação","Torpor","Coma"};
        String[] ramsay = {"Grau 1","Grau 2","Grau 3","Grau 4","Grau 5","Grau 6"};
        String[] rass = {"+4","+3","+2","+1","0","-1","-2","-3","-4","-5"};
        String[] deficitMotor = {"Dado 1","Dado 2","Dado 3"};
        String[] aberturaOcular = {"4 - Espontânea","3 - À voz","2 - À dor","1 - Nenhuma"};
        String[] respostaVerbal = {"5 - Orientada","4 - Confusa","3 - Palavras inapropriadas","2 - Palavras incompreensiveis","1 - Nenhuma"};
        String[] respostaMotora = {"6 - Obedece comandos","5 - Localiza dor","4 - Movimento de retirada","3 - Flexão anormal","2 - Extensão anormal","1 - Nenhuma"};
        nivelConscienciaSpinner = (Spinner) findViewById(R.id.material_spinner1);
        ArrayAdapter<String> adapterNivelConsciencia = new ArrayAdapter<>(PartesMedicasActivity.this, android.R.layout.simple_dropdown_item_1line, nivelConsciencia);
        nivelConscienciaSpinner.setAdapter(adapterNivelConsciencia);

        ramsaySpinner = (Spinner)findViewById(R.id.ramsay_spinner);
        ArrayAdapter<String> adapterRamsay = new ArrayAdapter<>(PartesMedicasActivity.this, android.R.layout.simple_dropdown_item_1line, ramsay);
        ramsaySpinner.setAdapter(adapterRamsay);

        rassSpinner = (Spinner)findViewById(R.id.rass_spinner);
        ArrayAdapter<String> adapterRass = new ArrayAdapter<>(PartesMedicasActivity.this, android.R.layout.simple_dropdown_item_1line, rass);
        rassSpinner.setAdapter(adapterRass);

        deficitMotorSpinner = (Spinner)findViewById(R.id.deficitMotor_spinner);
        ArrayAdapter<String> adapterDeficitMotor = new ArrayAdapter<>(PartesMedicasActivity.this, android.R.layout.simple_dropdown_item_1line, deficitMotor);
        deficitMotorSpinner.setAdapter(adapterDeficitMotor);

        aberturaOcularSpinner = (Spinner)findViewById(R.id.aberturaOcular_spinner);
        ArrayAdapter<String> adapterAberturaOcular = new ArrayAdapter<>(PartesMedicasActivity.this, android.R.layout.simple_dropdown_item_1line, aberturaOcular);
        aberturaOcularSpinner.setAdapter(adapterAberturaOcular);

        respostaVerbalSpinner = (Spinner)findViewById(R.id.respostaVerbal_spinner);
        ArrayAdapter<String> adapterRespostaVerbal = new ArrayAdapter<>(PartesMedicasActivity.this, android.R.layout.simple_dropdown_item_1line, respostaVerbal);
        respostaVerbalSpinner.setAdapter(adapterRespostaVerbal);

        respostaMotoraSpinner = (Spinner)findViewById(R.id.respostaMotora_spinner);
        ArrayAdapter<String> adapterRespostaMotora = new ArrayAdapter<>(PartesMedicasActivity.this, android.R.layout.simple_dropdown_item_1line, respostaMotora);
        respostaMotoraSpinner.setAdapter(adapterRespostaMotora);
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
    private void eliminarNeurologicoLayout(){
        View v = findViewById(R.id.neurologico_layout);
        v.setVisibility(View.GONE);
    }
    /************Métodos de Neurologico*************/


    private void criarHemodinamico(int x){
        getSupportActionBar().setTitle(R.string.Hemodinamico);
        eliminarNeurologicoLayout();
        buildIntent(x);
    }
    private void criarRespiratorio(int x){
        getSupportActionBar().setTitle(R.string.Respiratorio);
        eliminarNeurologicoLayout();
        buildIntent(x);
    }
    private void criarGastrointestinal(int x){
        getSupportActionBar().setTitle(R.string.GastroIntestinal);
        eliminarNeurologicoLayout();
        buildIntent(x);
    }
    private void criarRenal(int x){
        getSupportActionBar().setTitle(R.string.Renal);
        eliminarNeurologicoLayout();
        buildIntent(x);
    }
    private void criarHematologico(int x){
        getSupportActionBar().setTitle(R.string.Hematologico);
        eliminarNeurologicoLayout();
        buildIntent(x);
    }
    private void criarEndocrino(int x){
        getSupportActionBar().setTitle(R.string.Endocrino);
        eliminarNeurologicoLayout();
        buildIntent(x);
    }

    private void criarInfeccioso(int x){
        getSupportActionBar().setTitle(R.string.Infeccioso);
        eliminarNeurologicoLayout();
        buildIntent(x);
    }

    private void criarMetabolico(int x){
        getSupportActionBar().setTitle(R.string.Metabolico);
        eliminarNeurologicoLayout();
        buildIntent(x);
    }

    private Intent buildIntent(int x){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("position",x);
        setResult(RESULT_OK, resultIntent);
        return resultIntent;
    }
}
