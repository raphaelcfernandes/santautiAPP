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
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.R;

/**
 * Created by raphael on 8/6/17.
 */

public class ExamesActivity extends GenericoActivity{
    private int disturbioEletroliticoSelection=-1;
    private int acidoseMetabolicaSelection=-1;
    private int raioxToraxSelection =-1;
    private int leucogramaSelection=-1;
    private TextView disturbioEletrolitico,acidoseMetabolica;
    private TextView raioxToraxTextView;
    private TextView leucogramaTextView;
    private TextView funcaoHepaticaTextView;
    private TextView gasometriaArterialTextView;
    private View eletrolitoItens;
    private MyAnimation myAnimation;
    private boolean[] gasometriaArterial = new boolean[8],funcaoHepatica = new boolean[6];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames);
        setToolbar(getString(R.string.Exames));
        prepareNavigationButtons();
        
        acidoseMetabolica = (TextView)findViewById(R.id.acidoseMetabolica);
        eletrolitoItens = findViewById(R.id.eletrolitosItens);
        raioxToraxTextView = (TextView)findViewById(R.id.raioxToraxTextView);
        leucogramaTextView = (TextView)findViewById(R.id.leucogramaTextView);
        gasometriaArterialTextView = (TextView)findViewById(R.id.gasometriaArterialTextView);
        funcaoHepaticaTextView = (TextView)findViewById(R.id.funcaoHepaticaTextView);

        myAnimation = new MyAnimation();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), FolhasBalancoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                finish();
            }
        });

    }

    @Override
    public void prepareNavigationButtons() {
        findViewById(R.id.fichaProxima).setVisibility(View.GONE);
        antFicha = (Button)findViewById(R.id.fichaAnterior);
        antFicha.setText("< "+FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0)-1).getName());
    }

    public void disturbioEletroliticoOnCLick(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.DisturbioEletrolitico);

        //list of items
        final String[] items = getResources().getStringArray(R.array.disturbioEletrolitico);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, disturbioEletroliticoSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        disturbioEletrolitico.setText(items[which]);
                        disturbioEletrolitico.setVisibility(View.VISIBLE);
                        disturbioEletroliticoSelection=which;
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

    public void acidoseMetabolicaOnCLick(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.AcidoseMetabolica);

        //list of items
        final String[] items = getResources().getStringArray(R.array.acidoseMetabolica);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, acidoseMetabolicaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        acidoseMetabolica.setText(items[which]);
                        acidoseMetabolica.setVisibility(View.VISIBLE);
                        acidoseMetabolicaSelection=which;
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

    public void raioxToraxOnCLick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.RaioxTorax);

        //list of items
        final String[] items = getResources().getStringArray(R.array.raioxTorax);
        builder.setSingleChoiceItems(items, raioxToraxSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        raioxToraxTextView.setText(items[which]);
                        raioxToraxTextView.setVisibility(View.VISIBLE);
                        raioxToraxSelection=which;
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

    public void leucogramaOnCLick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Leucograma);

        //list of items
        final String[] items = getResources().getStringArray(R.array.leucograma);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, leucogramaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        leucogramaTextView.setText(items[which]);
                        leucogramaTextView.setVisibility(View.VISIBLE);
                        leucogramaSelection=which;
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

    public void eletrolitosOnCLick(View view){
        if(eletrolitoItens.isShown())
            myAnimation.slideUpView(getApplicationContext(),eletrolitoItens);
        else
            myAnimation.slideDownView(getApplicationContext(),eletrolitoItens);
    }

    public void gasometriaArterialOnClick(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.GasometriaArterial);

        //list of items
        final String[] items = getResources().getStringArray(R.array.gasometriaArterial);
        Arrays.sort(items);
        builder.setMultiChoiceItems(items, gasometriaArterial,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        gasometriaArterial[which]=isChecked;
                    }
                })
                // Set the action buttons
                .setPositiveButton(R.string.Selecionar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(setTextViewFromDialogMultipleText(gasometriaArterial,gasometriaArterialTextView,items)==0){
                            gasometriaArterialTextView.setText(getString(R.string.Normal));
                        }
                    }
                })
                .setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void funcaoHepaticaOnClick(View view){
        funcaoHepaticaDialog();
    }

    private void funcaoHepaticaDialog(){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.FuncaoHepatica);

        //list of items
        final String[] items = getResources().getStringArray(R.array.funcaoHepatica);
        Arrays.sort(items);
        builder.setMultiChoiceItems(items, funcaoHepatica,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        funcaoHepatica[which]=isChecked;
                    }
                })
                // Set the action buttons
                .setPositiveButton(R.string.Selecionar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(setTextViewFromDialogMultipleText(funcaoHepatica,funcaoHepaticaTextView,items)==0){
                            funcaoHepaticaTextView.setText(getString(R.string.Normal));
                        }
                    }
                })
                .setNegativeButton(R.string.Cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

}
