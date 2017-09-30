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
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
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
                if(!checkboxEvacuacoes.isChecked())
                    myAnimation.slideUpView(getApplicationContext(), evacuacoesItens);
                else
                    myAnimation.slideDownView(getApplicationContext(), evacuacoesItens);
            }
        });
        enteral = (TextInputLayout)findViewById(R.id.volumeNutricaoEnteral);
        oral = (TextInputLayout)findViewById(R.id.volumeNutricaoOral);
        gastrica = (TextInputLayout)findViewById(R.id.volumeNutricaoGastrica);
        endurecidas = (TextInputLayout)findViewById(R.id.eventosEndurecidas);
        normais = (TextInputLayout)findViewById(R.id.eventosNormais);
        diarreicas = (TextInputLayout)findViewById(R.id.eventosDiarreicas);
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
                intent = new Intent(view.getContext(), OsteomuscularActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), ExamesActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                finish();
            }
        });

    }

    public void aspectoFezesOnClick(View view){
        if(numeroEventos.length()==0) {
            numeroEventos.requestFocus();
            final InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(numeroEventos, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void evacuacoesOnClick(View view){
        if(evacuacoesItens.isShown())
            myAnimation.slideUpView(getApplicationContext(), evacuacoesItens);
        else
        if(checkboxEvacuacoes.isChecked())
            myAnimation.slideDownView(getApplicationContext(), evacuacoesItens);
    }

    public void nutricaoOnClick(View view){
        switch(view.getId()) {
            default:
                if(!volumeNutricao.isShown())
                    myAnimation.slideDownView(getApplicationContext(),volumeNutricao);
                break;
        }
    }

}
