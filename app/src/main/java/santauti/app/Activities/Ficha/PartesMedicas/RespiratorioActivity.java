package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Activities.SnackbarCreator;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class RespiratorioActivity extends GenericoActivity {
    private int viasAereasSelection=-1,localizacaoCanulaSelection=-1,murmurioVesicularSelection=-1,baseMurmurioVesicularSelection=-1,tercoMedioMurmurioVesicularSelection=-1;
    private View pressaoCuff_layout, localizacaoCanula_layout;
    private TextView viasAerea,localizacaoCanula,murmurioVesicular,baseMurmurioVesicular,tercoMedioMurmurioVesicular,apiceMurmurioVesicular;
    private SwitchCompat apiceSwitchCompat;
    private TextInputEditText pressaoCuff;
    private Realm realm;
    private Ficha ficha;
    private MyAnimation myAnimation;
    private Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respiratorio);
        findViewById(R.id.respiratorio_layout).requestFocus();
        setToolbar(getString(R.string.Respiratorio));

        /**************************VIEWS**************************/
        pressaoCuff = (TextInputEditText)findViewById(R.id.pressaoCuff);
        pressaoCuff_layout = findViewById(R.id.pressaoCuff_layout);
        localizacaoCanula_layout = findViewById(R.id.localizacaoCanula_layout);
        apiceSwitchCompat = (SwitchCompat)findViewById(R.id.apiceMurmurioVesicularSwitch);
        viasAerea = (TextView)findViewById(R.id.viaAerea);
        localizacaoCanula = (TextView)findViewById(R.id.localizacaoCanula);
        murmurioVesicular = (TextView)findViewById(R.id.murmurioVesicular);
        baseMurmurioVesicular = (TextView)findViewById(R.id.baseMurmurioVesicular);
        tercoMedioMurmurioVesicular = (TextView)findViewById(R.id.tercoMedioMurmurioVesicular);
        apiceMurmurioVesicular = (TextView)findViewById(R.id.apiceMurmurioVesicular);
        /**************************VIEWS**************************/


        prepareNavigationButtons();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HemodinamicoActivity.class);
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
                intent = new Intent(view.getContext(), GastrointestinalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });


        myAnimation = new MyAnimation();
        realm = Realm.getDefaultInstance();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home) {
            //verificaCamposENotificaAdapter();
            finish();
        }
        return true;
    }

    private void verificaCamposENotificaAdapter() {
//        realm.beginTransaction();
//        int i;
//        Respiratorio respiratorio = realm.createObject(Respiratorio.class);
//        if(invasivo.isChecked()){
//            i=0;
//            respiratorio.setInvasiva(1);
//            RespiratorioInvasiva respiratorioInvasiva = realm.createObject(RespiratorioInvasiva.class);
//            if(!isTextInpudEditTextEmpty(volume)){
//                respiratorio.setVolume(Integer.parseInt(volume.getText().toString()));
//                i++;
//            }
//            if(!isTextInpudEditTextEmpty(fio2)){
//                respiratorioInvasiva.setFio2(Integer.parseInt(fio2.getText().toString()));
//                i++;
//            }
//            if(!isTextInpudEditTextEmpty(freqRespiratoria)){
//                respiratorioInvasiva.setFrequenciaRespiratoria(Integer.parseInt(freqRespiratoria.getText().toString()));
//                i++;
//            }
//            if(!isTextInpudEditTextEmpty(peep)){
//                respiratorioInvasiva.setPeep(Integer.parseInt(peep.getText().toString()));
//                i++;
//            }
//            if(!isTextInpudEditTextEmpty(pressaoCuff)){
//                respiratorioInvasiva.setPressaoCuff(Integer.parseInt(pressaoCuff.getText().toString()));
//                i++;
//            }
//            if(!respiratorioSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
//                respiratorioInvasiva.setModoVentilatorio(respiratorioSpinner.getSelectedItem().toString());
//                i++;
//            }
//            if(i==6){
//                respiratorio.setRespiratorioInvasiva(respiratorioInvasiva);
//                Ficha r = getProperFicha();
//                r.setRespiratorio(respiratorio);
//                realm.copyToRealmOrUpdate(r);
//                changeCardColor();
//            }
//        }
//        else if(naoInvasivo.isChecked()){
//            i=0;
//            respiratorio.setInvasiva(0);
//            RespiratorioNaoInvasiva respiratorioNaoInvasiva = realm.createObject(RespiratorioNaoInvasiva.class);
//            if(!isTextInpudEditTextEmpty(volumeNaoInvasivo)){
//                respiratorio.setVolume(Integer.parseInt(volumeNaoInvasivo.getText().toString()));
//                i++;
//            }
//            if(!isTextInpudEditTextEmpty(ipap)){
//                respiratorioNaoInvasiva.setIpap(Integer.parseInt(ipap.getText().toString()));
//                i++;
//            }
//            if(!isTextInpudEditTextEmpty(epap)){
//                respiratorioNaoInvasiva.setEpap(Integer.parseInt(epap.getText().toString()));
//                i++;
//            }
//            if(!isTextInpudEditTextEmpty(saturacao)){
//                respiratorioNaoInvasiva.setSaturacao(Integer.parseInt(saturacao.getText().toString()));
//                i++;
//            }
//            if(i==4){
//                respiratorio.setRespiratorioNaoInvasiva(respiratorioNaoInvasiva);
//                Ficha r = getProperFicha();
//                r.setRespiratorio(respiratorio);
//                realm.copyToRealmOrUpdate(r);
//                changeCardColor();
//            }
//        }
//        realm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onBackPressed(){
        verificaCamposENotificaAdapter();
        finish();
    }

    public void viasAereasOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.ViasAereas);

        //list of items
        final String[] items = getResources().getStringArray(R.array.viasAereas);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, viasAereasSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viasAerea.setText(items[which]);
                        viasAerea.setVisibility(View.VISIBLE);
                        viasAereasSelection=which;
                        if(!items[which].equals(getResources().getStringArray(R.array.viasAereas)[0])){
                            if(!localizacaoCanula_layout.isShown() && !pressaoCuff_layout.isShown()) {
                                myAnimation.slideDownView(getApplicationContext(),localizacaoCanula_layout);
                                myAnimation.slideDownView(getApplicationContext(),pressaoCuff_layout);
                            }
                        }
                        else{
                            if(localizacaoCanula_layout.isShown() && pressaoCuff_layout.isShown()) {
                                myAnimation.slideUpView(getApplicationContext(),pressaoCuff_layout);
                                myAnimation.slideUpView(getApplicationContext(),localizacaoCanula_layout);
                            }
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


    public void localizacaoCanulaOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.LocalizacaoCanula);

        //list of items
        final String[] items = getResources().getStringArray(R.array.localizacaoCanula);
        builder.setSingleChoiceItems(items, localizacaoCanulaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        localizacaoCanula.setText(items[which]);
                        localizacaoCanula.setVisibility(View.VISIBLE);
                        localizacaoCanulaSelection=which;
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

    public void murmurioVesicularOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.MurmurioVesicular);

        //list of items
        final String[] items = getResources().getStringArray(R.array.murmurioVesicular);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, murmurioVesicularSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        murmurioVesicular.setText(items[which]);
                        murmurioVesicular.setVisibility(View.VISIBLE);
                        murmurioVesicularSelection=which;
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

    public void baseMurmurioVesicularOnclick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Base);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, baseMurmurioVesicularSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseMurmurioVesicular.setText(items[which]);
                        baseMurmurioVesicular.setVisibility(View.VISIBLE);
                        baseMurmurioVesicularSelection=which;
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

    public void tercoMedioVesicularOnclick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.TercoMedio);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, tercoMedioMurmurioVesicularSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tercoMedioMurmurioVesicular.setText(items[which]);
                        tercoMedioMurmurioVesicular.setVisibility(View.VISIBLE);
                        tercoMedioMurmurioVesicularSelection=which;
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

    public void apiceMurmurioVesicularOnClick(View view){
        if(apiceSwitchCompat.isChecked()){
            apiceSwitchCompat.setChecked(false);
            apiceMurmurioVesicular.setText(getString(R.string.Nao));
        }
        else{
            apiceSwitchCompat.setChecked(true);
            apiceMurmurioVesicular.setText(getString(R.string.Sim));
        }
    }


}
