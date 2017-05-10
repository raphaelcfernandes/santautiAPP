package santauti.app.View.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 10-May-17.
 */

public class PartesMedicas extends AppCompatActivity{
    private int x=0;
    MaterialBetterSpinner materialBetterSpinner ;
    private Intent intent;
    String[] SPINNER_DATA = {"ANDROID","PHP","BLOGGER","WORDPRESS"};
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
        getSupportActionBar().setTitle(R.string.Neurologico);
        materialBetterSpinner = (MaterialBetterSpinner)findViewById(R.id.material_spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PartesMedicas.this, android.R.layout.simple_dropdown_item_1line, SPINNER_DATA);
        materialBetterSpinner.setAdapter(adapter);
        buildIntent(x);
    }

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

    private void eliminarNeurologicoLayout(){
        View v = findViewById(R.id.neurologico_layout);
        v.setVisibility(View.GONE);
    }

    private Intent buildIntent(int x){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("position",x);
        setResult(RESULT_OK, resultIntent);
        return resultIntent;
    }
}
