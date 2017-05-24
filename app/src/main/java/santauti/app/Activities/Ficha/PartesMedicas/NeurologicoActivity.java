package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import santauti.app.Activities.Ficha.Generico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 13-May-17.
 */

public class NeurologicoActivity extends Generico{
    Spinner nivelConscienciaSpinner,ramsaySpinner,rassSpinner,deficitMotorSpinner;
    Spinner aberturaOcularSpinner,respostaVerbalSpinner,respostaMotoraSpinner,pupilaReatividadeLuzSpinner;
    Spinner pupilaSimetriaSpinner,pupilaTamanhoSpinner;
    RadioButton sedado_sim,sedado_nao;
    private View sedado_sim_layout,sedado_nao_layout,neurologico_opcional_layout;

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
        sedado_sim_layout.setVisibility(View.GONE);
        sedado_nao_layout.setVisibility(View.GONE);
        neurologico_opcional_layout.setVisibility(View.GONE);
        prepareNeurologicoSpinners();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
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
        String[] nivelConsciencia = {"Alerta","Sonolência","Obnubilação","Torpor","Coma"};
        String[] ramsay = {"Grau 1","Grau 2","Grau 3","Grau 4","Grau 5","Grau 6"};
        String[] rass = {"+4","+3","+2","+1","0","-1","-2","-3","-4","-5"};
        String[] deficitMotor = {"Presente","Ausente"};
        String[] aberturaOcular = {"4 - Espontânea","3 - À voz","2 - À dor","1 - Nenhuma"};
        String[] respostaVerbal = {"5 - Orientada","4 - Confusa","3 - Palavras inapropriadas","2 - Palavras incompreensiveis","1 - Nenhuma"};
        String[] respostaMotora = {"6 - Obedece comandos","5 - Localiza dor","4 - Movimento de retirada","3 - Flexão anormal","2 - Extensão anormal","1 - Nenhuma"};
        String[] pupilaReatividadeLuz = {"RFM+","RFM-"};
        String[] pupilaSimetria = {"Isocóricas","Anisocóricas"};
        String[] pupilaTamanho = {"Miose","Midríase"};

        nivelConscienciaSpinner = (Spinner) findViewById(R.id.material_spinner1);
        ArrayAdapter<String> adapterNivelConsciencia = new ArrayAdapter<>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, nivelConsciencia);
        nivelConscienciaSpinner.setAdapter(adapterNivelConsciencia);

        ramsaySpinner = (Spinner)findViewById(R.id.ramsay_spinner);
        ArrayAdapter<String> adapterRamsay = new ArrayAdapter<>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, ramsay);
        ramsaySpinner.setAdapter(adapterRamsay);

        rassSpinner = (Spinner)findViewById(R.id.rass_spinner);
        ArrayAdapter<String> adapterRass = new ArrayAdapter<>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, rass);
        rassSpinner.setAdapter(adapterRass);

        deficitMotorSpinner = (Spinner)findViewById(R.id.deficitMotor_spinner);
        ArrayAdapter<String> adapterDeficitMotor = new ArrayAdapter<>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, deficitMotor);
        deficitMotorSpinner.setAdapter(adapterDeficitMotor);

        aberturaOcularSpinner = (Spinner)findViewById(R.id.aberturaOcular_spinner);
        ArrayAdapter<String> adapterAberturaOcular = new ArrayAdapter<>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, aberturaOcular);
        aberturaOcularSpinner.setAdapter(adapterAberturaOcular);

        respostaVerbalSpinner = (Spinner)findViewById(R.id.respostaVerbal_spinner);
        ArrayAdapter<String> adapterRespostaVerbal = new ArrayAdapter<>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, respostaVerbal);
        respostaVerbalSpinner.setAdapter(adapterRespostaVerbal);

        respostaMotoraSpinner = (Spinner)findViewById(R.id.respostaMotora_spinner);
        ArrayAdapter<String> adapterRespostaMotora = new ArrayAdapter<>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, respostaMotora);
        respostaMotoraSpinner.setAdapter(adapterRespostaMotora);

        pupilaReatividadeLuzSpinner = (Spinner)findViewById(R.id.pupila_reatividade_spinner);
        ArrayAdapter<String> adapterPupilaReatividadeLuz = new ArrayAdapter<>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, pupilaReatividadeLuz);
        pupilaReatividadeLuzSpinner.setAdapter(adapterPupilaReatividadeLuz);

        pupilaSimetriaSpinner = (Spinner)findViewById(R.id.pupila_simetria_spinner);
        ArrayAdapter<String> adapterPupilaSimetria = new ArrayAdapter<>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, pupilaSimetria);
        pupilaSimetriaSpinner.setAdapter(adapterPupilaSimetria);

        pupilaTamanhoSpinner = (Spinner)findViewById(R.id.pupila_tamanho_spinner);
        ArrayAdapter<String> adapterPupilaTamanho = new ArrayAdapter<>(NeurologicoActivity.this, android.R.layout.simple_dropdown_item_1line, pupilaTamanho);
        pupilaTamanhoSpinner.setAdapter(adapterPupilaTamanho);


    }
}
