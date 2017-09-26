package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Arrays;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.R;

/**
 * Created by raphael on 8/6/17.
 */

public class FolhasBalancoActivity extends GenericoActivity {
    private int aspectoFezesSelection =-1;
    private TextView menuCurvaGlicemica,curvaGlicemica,evacuacoesTextView,aspectoFezesTextView;
    private View evacuacoesItens,volumeNutricao;
    private MyAnimation myAnimation;
    private CheckBox checkboxEvacuacoes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folha_balanco);
        setToolbar(getString(R.string.FolhasBalanco));
        prepareNavigationButtons();
        myAnimation = new MyAnimation();

        //curvaTermica = (TextView)findViewById(R.id.curvaTermica);
        curvaGlicemica = (TextView)findViewById(R.id.curvaGlicemica);
        evacuacoesTextView = (TextView)findViewById(R.id.evacuacoesTextView);
        evacuacoesItens = findViewById(R.id.evacuacoesItens);
        //aspectoFezesTextView = (TextView)findViewById(R.id.aspectoFezes);
        volumeNutricao = findViewById(R.id.volumeNutricao);
        //nutricaoTextView = (TextView)findViewById(R.id.nutricaoTextView);
        menuCurvaGlicemica = (TextView)findViewById(R.id.menuCurvaGlicemica);
        checkboxEvacuacoes = (CheckBox)findViewById(R.id.checkboxEvacuacoes);
        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), EndocrinoActivity.class);
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

    public void curvaGlicemicaOnClick(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), menuCurvaGlicemica, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_curva_glicemica, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.eventoHipoglicemia:
                        curvaGlicemica.setText(item.getTitle());
                        break;
                    case R.id.normoglicemia:
                        curvaGlicemica.setText(item.getTitle());
                        break;
                    case R.id.eventoHiperglicemia:
                        curvaGlicemica.setText(item.getTitle());
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public void evacuacoesOnClick(View view){
        if(checkboxEvacuacoes.isChecked()) {
            myAnimation.slideUpView(getApplicationContext(), evacuacoesItens);
            checkboxEvacuacoes.setChecked(false);
            //evacuacoesTextView.setText(getString(R.string.Ausente));
        }
        else {
            checkboxEvacuacoes.setChecked(true);
            //evacuacoesTextView.setText(getString(R.string.Presente));
            myAnimation.slideDownView(getApplicationContext(), evacuacoesItens);
        }

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
