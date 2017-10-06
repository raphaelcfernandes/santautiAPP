package santauti.app.Activities.Ficha.PartesMedicas;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SwitchCompat;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmList;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.InputFilterMin;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.BombaInfusao.BombaInfusaoItens;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.FolhasBalanco.Evacuacoes;
import santauti.app.Model.Ficha.FolhasBalanco.FolhasBalanco;
import santauti.app.Model.Ficha.FolhasBalanco.Nutricao;
import santauti.app.Model.Ficha.Gastrointestinal;
import santauti.app.Model.Ficha.MonitorMultiparametrico;
import santauti.app.Model.Ficha.RealmObjects.RealmString;
import santauti.app.R;

/**
 * Created by raphael on 8/6/17.
 */

public class FolhasBalancoActivity extends GenericoActivity {
    private View evacuacoesItens,volumeNutricao;
    private MyAnimation myAnimation;
    private CheckBox checkboxEvacuacoes,checkboxGastrica,checkboxEnteral,checkboxOral,checkBoxEndurecidas,checkBoxDiarreicas,checkBoxNormais;
    private TextInputEditText numeroEventos;
    private TextInputLayout enteral,oral,gastrica,endurecidas,normais,diarreicas;
    private Realm realm = Realm.getDefaultInstance();
    private RadioGroup curvaTermicaRadioGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folha_balanco);
        setToolbar(getString(R.string.FolhasBalanco));
        prepareNavigationButtons();
        myAnimation = new MyAnimation();

        curvaTermicaRadioGroup = (RadioGroup)findViewById(R.id.curvaTermicaRadioGroup);

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
        oral = (TextInputLayout)findViewById(R.id.volumeNutricaoOral);
        oral.getEditText().setFilters(new InputFilter[]{ new InputFilterMin(1)});
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
                startActivity(intent);
                exitActivityToLeft();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), MonitorMultiparametricoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        setFolhasBalancoFromDatabase();
    }

    private void setFolhasBalancoFromDatabase() {
        Ficha ficha = getProperFicha();
        if(ficha.getFolhasBalanco()!=null){
            FolhasBalanco folhasBalanco = ficha.getFolhasBalanco();
            if(folhasBalanco.getCurvaTermica()!=null)
                setRadioButtonFromIdAndDatabase(R.id.curvaTermica,folhasBalanco.getCurvaTermica());
            if(folhasBalanco.isEvacuacoes()){
                checkboxEvacuacoes.setChecked(true);
                evacuacoesItens.setVisibility(View.VISIBLE);
                RealmList<Evacuacoes> evacuacoes = ficha.getFolhasBalanco().getEvacuacoesList();
                HashMap<String,Integer> evacuacoesHashMap = new HashMap<>();
                for(Evacuacoes evacuacao : evacuacoes)
                    evacuacoesHashMap.put(evacuacao.getTipoEvacuacao(),evacuacao.getEventos());
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.evacuacoesItens);
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    View v = linearLayout.getChildAt(i);
                    if(v instanceof LinearLayout){
                        for (int k = 0; k < ((LinearLayout) v).getChildCount(); k++) {
                            View view = ((LinearLayout) v).getChildAt(k);
                            if (view instanceof AppCompatCheckBox) {
                                AppCompatCheckBox cb = (AppCompatCheckBox) view;
                                if (evacuacoesHashMap.containsKey(cb.getText().toString())){
                                    k++;
                                    cb.setChecked(true);
                                    view = ((LinearLayout)v).getChildAt(k);
                                    view.setVisibility(View.VISIBLE);
                                    ((TextInputLayout) view).getEditText().
                                            setText(Integer.toString(evacuacoesHashMap.get(cb.getText().toString())));
                                }
                            }
                        }
                    }
                }
            }
            if(!folhasBalanco.getNutricao().isEmpty()){
                RealmList<Nutricao> nutricoes = ficha.getFolhasBalanco().getNutricao();
                HashMap<String,Integer> nutricaoHashmap = new HashMap<>();
                for(Nutricao nutricao : nutricoes)
                    nutricaoHashmap.put(nutricao.getTipoNutricao(),nutricao.getVolume());
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.nutricao);
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    View v = linearLayout.getChildAt(i);
                    if(v instanceof LinearLayout){
                        for (int k = 0; k < ((LinearLayout) v).getChildCount(); k++) {
                            View view = ((LinearLayout) v).getChildAt(k);
                            if (view instanceof AppCompatCheckBox) {
                                AppCompatCheckBox cb = (AppCompatCheckBox) view;
                                if (nutricaoHashmap.containsKey(cb.getText().toString())){
                                    k++;
                                    cb.setChecked(true);
                                    view = ((LinearLayout)v).getChildAt(k);
                                    view.setVisibility(View.VISIBLE);
                                    ((TextInputLayout) view).getEditText().
                                            setText(Integer.toString(nutricaoHashmap.get(cb.getText().toString())));
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onBackPressed() {
        verificaCamposENotificaAdapter();
        finish();
    }

    private void verificaCamposENotificaAdapter() {
        realm.beginTransaction();
        FolhasBalanco folhasBalanco = realm.createObject(FolhasBalanco.class);
        if(curvaTermicaRadioGroup.getCheckedRadioButtonId()!=-1)
            folhasBalanco.setCurvaTermica(getStringOfRadioButtonSelectedFromRadioGroup(curvaTermicaRadioGroup));
        if(checkboxEvacuacoes.isChecked()){
            folhasBalanco.setEvacuacoes(true);
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
                                Evacuacoes evacuacoes = realm.createObject(Evacuacoes.class);
                                evacuacoes.setTipoEvacuacao(cb.getText().toString());
                                evacuacoes.setEventos(Integer.parseInt(((TextInputLayout) view).getEditText().getText().toString()));
                                folhasBalanco.getEvacuacoesList().add(evacuacoes);
                            }
                        }
                    }
                }
            }
        }
        else
            folhasBalanco.setEvacuacoes(false);
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
                            Nutricao nutricao = realm.createObject(Nutricao.class);
                            nutricao.setTipoNutricao(cb.getText().toString());
                            nutricao.setVolume(Integer.parseInt(((TextInputLayout) view).getEditText().getText().toString()));
                            folhasBalanco.getNutricao().add(nutricao);
                        }
                    }
                }
            }
        }
        Ficha r = getProperFicha();
        r.setFolhasBalanco(folhasBalanco);
        realm.copyToRealmOrUpdate(r);
        if (folhasBalanco.checkObject())
            changeCardColorToGreen();
        else
            setCardColorToDefault();
        realm.commitTransaction();
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
