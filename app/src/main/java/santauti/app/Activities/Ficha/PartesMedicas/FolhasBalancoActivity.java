package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.R;

/**
 * Created by raphael on 8/6/17.
 */

public class FolhasBalancoActivity extends GenericoActivity {
    private int curvaTermicaSelection=-1,curvaGlicemicaSelection=-1, aspectoFezesSelection =-1,nutricaoSelection=-1;
    private TextView curvaTermica,curvaGlicemica,evacuacoesTextView,aspectoFezesTextView,nutricaoTextView;
    private SwitchCompat evacuacoesSwitch;
    private View evacuacoesItens,volumeNutricao;
    private MyAnimation myAnimation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folha_balanco);
        setToolbar(getString(R.string.FolhasBalanco));
        prepareNavigationButtons();
        myAnimation = new MyAnimation();

        curvaTermica = (TextView)findViewById(R.id.curvaTermica);
        curvaGlicemica = (TextView)findViewById(R.id.curvaGlicemica);
        evacuacoesTextView = (TextView)findViewById(R.id.evacuacoesTextView);
        evacuacoesSwitch = (SwitchCompat)findViewById(R.id.evacuacoesSwitch);
        evacuacoesItens = findViewById(R.id.evacuacoesItens);
        aspectoFezesTextView = (TextView)findViewById(R.id.aspectoFezes);
        volumeNutricao = findViewById(R.id.volumeNutricao);
        nutricaoTextView = (TextView)findViewById(R.id.nutricaoTextView);

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

    public void curvaTermicaOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.CurvaTermica);

        //list of items
        final String[] items = getResources().getStringArray(R.array.curvaTermica);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, curvaTermicaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        curvaTermica.setText(items[which]);
                        curvaTermica.setVisibility(View.VISIBLE);
                        curvaTermicaSelection=which;
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void curvaGlicemicaOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.CurvaGlicemica);

        //list of items
        final String[] items = getResources().getStringArray(R.array.curvaGlicemica);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, curvaGlicemicaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        curvaGlicemica.setText(items[which]);
                        curvaGlicemica.setVisibility(View.VISIBLE);
                        curvaGlicemicaSelection=which;
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(R.string.Cancelar);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void evacuacoesOnClick(View view){
        if(evacuacoesSwitch.isChecked()) {
            if (evacuacoesItens.isShown())
                myAnimation.slideUpView(getApplicationContext(), evacuacoesItens);
            else
                myAnimation.slideDownView(getApplicationContext(),evacuacoesItens);
        }
    }

    public void evacuacoesSwitchOnClick(View view){
        if(evacuacoesSwitch.isChecked()) {
            evacuacoesTextView.setText(R.string.Presente);
            if(!evacuacoesItens.isShown())
                myAnimation.slideDownView(getApplicationContext(),evacuacoesItens);
        }
        else {
            evacuacoesTextView.setText(R.string.Ausente);
            if(evacuacoesItens.isShown())
                myAnimation.slideUpView(getApplicationContext(),evacuacoesItens);
        }
    }

    public void aspectoFezesOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.AspectoFezes);

        //list of items
        final String[] items = getResources().getStringArray(R.array.aspectoFezes);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, aspectoFezesSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        aspectoFezesTextView.setText(items[which]);
                        aspectoFezesTextView.setVisibility(View.VISIBLE);
                        aspectoFezesSelection =which;
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(R.string.Cancelar);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void nutricaoOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Nutricao);

        //list of items
        final String[] items = getResources().getStringArray(R.array.nutricao);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, nutricaoSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nutricaoTextView.setText(items[which]);
                        nutricaoTextView.setVisibility(View.VISIBLE);
                        nutricaoSelection=which;
                        if(nutricaoSelection!=-1 && !volumeNutricao.isShown()){
                            myAnimation.slideDownView(getApplicationContext(),volumeNutricao);
                        }
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(R.string.Cancelar);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }
}
