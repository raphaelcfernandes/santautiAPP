package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.R;

/**
 * Created by raphael on 6/23/17.
 */

public class RespiradorActivity extends GenericoActivity{
    private int ventilacaoSelection=-1,modoVentilatorioSelection=-1;
    private TextView ventilacao,modoVentilatorio;
    private View bipap,mecanico,naoInvasivo;
    private MyAnimation myAnimation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respirador);
        setToolbar(getString(R.string.Respirador));
        prepareNavigationButtons();


        /****************************VIEWS*****************************/
        bipap = findViewById(R.id.bipap);
        ventilacao = (TextView)findViewById(R.id.ventilacao);
        mecanico = findViewById(R.id.ventilacao_mecanica);
        modoVentilatorio = (TextView)findViewById(R.id.modoVentilatorio);
        naoInvasivo = findViewById(R.id.ventilacao_naoInvasiva);
        /****************************VIEWS*****************************/

        myAnimation = new MyAnimation();
        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), DispositivoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), NeurologicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });
    }

    public void ventilacaoOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Ventilacao);

        //list of items
        final String[] items = getResources().getStringArray(R.array.ventilacao);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, ventilacaoSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ventilacao.setText(items[which]);
                        ventilacao.setVisibility(View.VISIBLE);
                        ventilacaoSelection=which;
                        if(items[which].equals(getResources().getStringArray(R.array.ventilacao)[1]) && !bipap.isShown()) {
                            if(mecanico.isShown())
                                mecanico.setVisibility(View.GONE);
                            if(naoInvasivo.isShown())
                                naoInvasivo.setVisibility(View.GONE);
                            bipap.setVisibility(View.VISIBLE);
                        }
                        if(items[which].equals(getResources().getStringArray(R.array.ventilacao)[2]) && !mecanico.isShown()){
                            if(bipap.isShown())
                                bipap.setVisibility(View.GONE);
                            if(naoInvasivo.isShown())
                                naoInvasivo.setVisibility(View.GONE);
                            mecanico.setVisibility(View.VISIBLE);
                        }
                        if(items[which].equals(getResources().getStringArray(R.array.ventilacao)[3]) && !naoInvasivo.isShown()){
                            if(bipap.isShown())
                                bipap.setVisibility(View.GONE);
                            if(mecanico.isShown())
                                mecanico.setVisibility(View.GONE);
                            naoInvasivo.setVisibility(View.VISIBLE);
                        }
                        if(items[which].equals(getResources().getStringArray(R.array.ventilacao)[0])){
                            if(bipap.isShown())
                                myAnimation.slideUpView(getApplicationContext(),bipap);
                            if(mecanico.isShown())
                                myAnimation.slideUpView(getApplicationContext(),mecanico);
                            if(naoInvasivo.isShown())
                                myAnimation.slideUpView(getApplicationContext(),naoInvasivo);
                        }
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

    public void modoVentilatorioOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.ModoVentilatorio);

        //list of items
        final String[] items = getResources().getStringArray(R.array.modoVentilatorio);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, modoVentilatorioSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        modoVentilatorio.setText(items[which]);
                        modoVentilatorio.setVisibility(View.VISIBLE);
                        modoVentilatorioSelection=which;
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

}
