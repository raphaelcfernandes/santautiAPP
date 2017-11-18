package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.InputFilterMin;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.FolhasBalanco;
import santauti.app.R;

/**
 * Created by raphael on 8/6/17.
 */

public class FolhasBalancoActivity extends GenericoActivity {
    private View evacuacoesItens;
    private MyAnimation myAnimation;
    private CheckBox checkboxEvacuacoes,checkboxGastrica,checkboxEnteral,checkboxOral,checkBoxEndurecidas,checkBoxDiarreicas,checkBoxNormais;
    private TextInputLayout enteral,oral,gastrica,endurecidas,normais,diarreicas;
    private TextInputEditText curvaTermica, picosFebris,diurese,balancoHidrico;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folha_balanco);
        setToolbar(getString(R.string.FolhasBalanco));
        prepareNavigationButtons();
        myAnimation = new MyAnimation();

        evacuacoesItens = findViewById(R.id.evacuacoesItens);

        checkboxEvacuacoes = (CheckBox)findViewById(R.id.checkboxEvacuacoes);
        checkboxEvacuacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(evacuacoesItens.isShown())
                    myAnimation.slideUpView(getApplicationContext(), evacuacoesItens);
                else
                    if(checkboxEvacuacoes.isChecked())
                        myAnimation.slideDownView(getApplicationContext(), evacuacoesItens);
            }
        });
        enteral = (TextInputLayout)findViewById(R.id.volumeNutricaoEnteral);
        enteral.getEditText().setFilters(new InputFilter[]{ new InputFilterMin(1)});
        balancoHidrico = (TextInputEditText)findViewById(R.id.balancoHidrico);
        balancoHidrico.setFilters(new InputFilter[]{ new InputFilterMin(1)});
        diurese = (TextInputEditText)findViewById(R.id.diurese);
        diurese.setFilters(new InputFilter[]{ new InputFilterMin(1)});
        oral = (TextInputLayout)findViewById(R.id.volumeNutricaoOral);
        oral.getEditText().setFilters(new InputFilter[]{ new InputFilterMin(1)});
        curvaTermica = (TextInputEditText)findViewById(R.id.curvaTermica);
        picosFebris = (TextInputEditText)findViewById(R.id.picosFebris);
        gastrica = (TextInputLayout)findViewById(R.id.volumeNutricaoGastrica);
        gastrica.getEditText().setFilters(new InputFilter[]{ new InputFilterMin(1)});
        endurecidas = (TextInputLayout)findViewById(R.id.eventosEndurecidas);
        endurecidas.getEditText().setFilters(new InputFilter[]{ new InputFilterMin(1)});
        normais = (TextInputLayout)findViewById(R.id.eventosNormais);
        normais.getEditText().setFilters(new InputFilter[]{ new InputFilterMin(1)});
        diarreicas = (TextInputLayout)findViewById(R.id.eventosDiarreicas);
        diarreicas.getEditText().setFilters(new InputFilter[]{ new InputFilterMin(1)});
        checkBoxEndurecidas = (CheckBox)findViewById(R.id.checkboxEndurecidas);
        checkBoxEndurecidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(endurecidas.isShown())
                    endurecidas.setVisibility(View.GONE);
                else
                    endurecidas.setVisibility(View.VISIBLE);
            }
        });
        checkBoxDiarreicas = (CheckBox)findViewById(R.id.checkboxDiarreicas);
        checkBoxDiarreicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(diarreicas.isShown())
                    diarreicas.setVisibility(View.GONE);
                else
                    diarreicas.setVisibility(View.VISIBLE);
            }
        });
        checkBoxNormais = (CheckBox)findViewById(R.id.checkBoxNormais);
        checkBoxNormais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(normais.isShown())
                    normais.setVisibility(View.GONE);
                else
                    normais.setVisibility(View.VISIBLE);
            }
        });
        checkboxGastrica = (CheckBox)findViewById(R.id.checkboxGastrica);
        checkboxGastrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gastrica.isShown())
                    gastrica.setVisibility(View.GONE);
                else
                    gastrica.setVisibility(View.VISIBLE);
            }
        });
        checkboxEnteral = (CheckBox)findViewById(R.id.checkBoxEnteral);
        checkboxEnteral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enteral.isShown())
                    enteral.setVisibility(View.GONE);
                else
                    enteral.setVisibility(View.VISIBLE);
            }
        });
        checkboxOral = (CheckBox)findViewById(R.id.checkBoxOral);
        checkboxOral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(oral.isShown())
                    oral.setVisibility(View.GONE);
                else
                    oral.setVisibility(View.VISIBLE);
            }
        });

        setupUI(findViewById(R.id.activity_folha_balanco));
        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), ExamesActivity.class);
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
                intent = new Intent(view.getContext(), MonitorMultiparametricoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToRight();
            }
        });

        setFolhasBalancoFromDatabase();
    }

    private void setFolhasBalancoFromDatabase() {
//        Ficha ficha = getProperFicha();
//        if(ficha.getFolhasBalanco()!=null){
//            FolhasBalanco folhasBalanco = ficha.getFolhasBalanco();
//            if(folhasBalanco.getCurvaTermica()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.curvaTermica,folhasBalanco.getCurvaTermica());
//            if(folhasBalanco.getHemodinamicamente()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.hemodinamicamente,folhasBalanco.getHemodinamicamente());
//            if(folhasBalanco.isEvacuacoesFlag()){
//                checkboxEvacuacoes.setChecked(true);
//                evacuacoesItens.setVisibility(View.VISIBLE);
//                RealmList<Evacuacoes> evacuacoes = ficha.getFolhasBalanco().getEvacuacoes();
//                HashMap<String,Integer> evacuacoesHashMap = new HashMap<>();
//                for(Evacuacoes evacuacao : evacuacoes)
//                    evacuacoesHashMap.put(evacuacao.getTipoEvacuacao(),evacuacao.getEventos());
//                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.evacuacoesItens);
//                for (int i = 0; i < linearLayout.getChildCount(); i++) {
//                    View v = linearLayout.getChildAt(i);
//                    if(v instanceof LinearLayout){
//                        for (int k = 0; k < ((LinearLayout) v).getChildCount(); k++) {
//                            View view = ((LinearLayout) v).getChildAt(k);
//                            if (view instanceof AppCompatCheckBox) {
//                                AppCompatCheckBox cb = (AppCompatCheckBox) view;
//                                if (evacuacoesHashMap.containsKey(cb.getText().toString())){
//                                    k++;
//                                    cb.setChecked(true);
//                                    view = ((LinearLayout)v).getChildAt(k);
//                                    view.setVisibility(View.VISIBLE);
//                                    ((TextInputLayout) view).getEditText().
//                                            setText(Integer.toString(evacuacoesHashMap.get(cb.getText().toString())));
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            if(!folhasBalanco.getNutricao().isEmpty()){
//                RealmList<Nutricao> nutricoes = ficha.getFolhasBalanco().getNutricao();
//                HashMap<String,Integer> nutricaoHashmap = new HashMap<>();
//                for(Nutricao nutricao : nutricoes)
//                    nutricaoHashmap.put(nutricao.getTipoNutricao(),nutricao.getVolume());
//                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.nutricao);
//                for (int i = 0; i < linearLayout.getChildCount(); i++) {
//                    View v = linearLayout.getChildAt(i);
//                    if(v instanceof LinearLayout){
//                        for (int k = 0; k < ((LinearLayout) v).getChildCount(); k++) {
//                            View view = ((LinearLayout) v).getChildAt(k);
//                            if (view instanceof AppCompatCheckBox) {
//                                AppCompatCheckBox cb = (AppCompatCheckBox) view;
//                                if (nutricaoHashmap.containsKey(cb.getText().toString())){
//                                    k++;
//                                    cb.setChecked(true);
//                                    view = ((LinearLayout)v).getChildAt(k);
//                                    view.setVisibility(View.VISIBLE);
//                                    ((TextInputLayout) view).getEditText().
//                                            setText(Integer.toString(nutricaoHashmap.get(cb.getText().toString())));
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
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
        FolhasBalanco folhasBalanco = new FolhasBalanco();
        folhasBalanco.initializeMaps();
        if(!isTextInputEditTextEmpty(curvaTermica))
            folhasBalanco.setCurvaTermica(Float.parseFloat(curvaTermica.getText().toString()));
        if(!isTextInputEditTextEmpty(picosFebris))
            folhasBalanco.setPicosFebris(Integer.parseInt(picosFebris.getText().toString()));
        if(!isTextInputEditTextEmpty(diurese))
            folhasBalanco.setDiurese(Integer.parseInt(diurese.getText().toString()));
        if(!isTextInputEditTextEmpty(balancoHidrico))
            folhasBalanco.setBalancoHidrico(Integer.parseInt(balancoHidrico.getText().toString()));
        if(((RadioGroup)findViewById(R.id.hemodinamicamenteRadioGroup)).getCheckedRadioButtonId()!=-1)
            folhasBalanco.setHemodinamicamente(getStringOfRadioButtonSelectedFromRadioGroup(((RadioGroup)findViewById(R.id.hemodinamicamenteRadioGroup))));
        if(checkboxEvacuacoes.isChecked()){
            folhasBalanco.setEvacuacoesFlag(true);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.evacuacoesItens);
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                View v = linearLayout.getChildAt(i);
                if(v instanceof LinearLayout){
                    for (int k = 0; k < ((LinearLayout) v).getChildCount(); k++) {
                        View view = ((LinearLayout) v).getChildAt(k);
                        if (view instanceof AppCompatCheckBox) {
                            AppCompatCheckBox cb = (AppCompatCheckBox) view;
                            if (cb.isChecked()) {
                                k++;
                                view = ((LinearLayout)v).getChildAt(k);
                                folhasBalanco.insereEvacuacoes(cb.getText().toString(),Integer.parseInt(((TextInputLayout) view).getEditText().getText().toString()));
                            }
                        }
                    }
                }
            }
        }
        else
            folhasBalanco.setEvacuacoesFlag(false);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.nutricao);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View v = linearLayout.getChildAt(i);
            if(v instanceof LinearLayout){
                for (int k = 0; k < ((LinearLayout) v).getChildCount(); k++) {
                    View view = ((LinearLayout) v).getChildAt(k);
                    if (view instanceof AppCompatCheckBox) {
                        AppCompatCheckBox cb = (AppCompatCheckBox) view;
                        if (cb.isChecked()) {
                            k++;
                            view = ((LinearLayout)v).getChildAt(k);
                            folhasBalanco.insereNutricao(cb.getText().toString(),Integer.parseInt(((TextInputLayout) view).getEditText().getText().toString()));
                        }
                    }
                }
            }
        }
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).updateChildren(folhasBalanco.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                changeCardColorToGreen();
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

    public void evacuacoesOnClick(View view){
        if(evacuacoesItens.isShown())
            myAnimation.slideUpView(getApplicationContext(), evacuacoesItens);
        else
        if(checkboxEvacuacoes.isChecked())
            myAnimation.slideDownView(getApplicationContext(), evacuacoesItens);
    }
}
