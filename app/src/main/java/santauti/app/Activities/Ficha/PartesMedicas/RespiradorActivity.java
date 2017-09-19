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
    private View respiradorLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respirador);
        setToolbar(getString(R.string.Respirador));
        prepareNavigationButtons();
        /****************************VIEWS*****************************/
        bipap = findViewById(R.id.bipap);
        mecanico = findViewById(R.id.ventilacao_mecanica);
        modoVentilatorio = (TextView)findViewById(R.id.modoVentilatorio);
        naoInvasivo = findViewById(R.id.ventilacao_naoInvasiva);
        respiradorLayout = findViewById(R.id.respiradorLayout);
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
        //createPopupModoVentilatorio();
    }

    public void parametrosModoVentilatorioOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.ParametrosRespirador);

        //list of items
        final String[] items = getResources().getStringArray(R.array.modoVentilatorio);
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


    public void emVentilacaoOnClick(View view){
        switch(view.getId()) {
            case R.id.simVentilacao:
                if(!respiradorLayout.isShown())
                    myAnimation.slideDownView(getApplicationContext(),respiradorLayout);
                break;
            case R.id.naoVentilacao:
                if(respiradorLayout.isShown())
                    myAnimation.slideUpView(getApplicationContext(),respiradorLayout);
                break;
        }
    }

    public void modoVentilatorioOnClick(View view){
        switch(view.getId()) {
            case R.id.naoInvasivoModoVentilatorio:
                if(bipap.isShown())
                    bipap.setVisibility(View.GONE);
                if(mecanico.isShown())
                    mecanico.setVisibility(View.GONE);
                naoInvasivo.setVisibility(View.VISIBLE);
                break;
            case R.id.bipapModoVentilatorio:
                if(mecanico.isShown())
                    mecanico.setVisibility(View.GONE);
                if(naoInvasivo.isShown())
                    naoInvasivo.setVisibility(View.GONE);
                bipap.setVisibility(View.VISIBLE);
                break;
            case R.id.mecanicaModoVentilatorio:
                if(bipap.isShown())
                    bipap.setVisibility(View.GONE);
                if(naoInvasivo.isShown())
                    naoInvasivo.setVisibility(View.GONE);
                mecanico.setVisibility(View.VISIBLE);
                break;
        }
    }
}
