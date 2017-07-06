package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class RespiratorioActivity extends GenericoActivity {
    private int viasAereasSelection=-1,localizacaoCanulaSelection=-1;
    private int murmurioVesicularSelection=-1,baseMurmurioVesicularSelection=-1,tercoMedioMurmurioVesicularSelection=-1,usoDeOxigenioSelection=-1,apiceSelection=-1;
    private View pressaoCuff_layout;
    private View localizacaoCanula_layout;
    private View mascaraDeVenturi_layout;
    private TextView viasAerea,localizacaoCanula,murmurioVesicular;
    private TextView baseMurmurioVesicular,tercoMedioMurmurioVesicular,apiceMurmurioVesicular, usoDeOxigenioTextView;
    private SwitchCompat apiceSwitchCompat,usoDeOxigenioSwitchCompat;
    private TextInputEditText pressaoCuff;
    private Realm realm;
    private Ficha ficha;
    private MyAnimation myAnimation;
    private Intent intent;
    private int index;

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
        mascaraDeVenturi_layout = findViewById(R.id.mascaVenturi);
        usoDeOxigenioSwitchCompat = (SwitchCompat)findViewById(R.id.usoDeOxigenioSwitch);
        viasAerea = (TextView)findViewById(R.id.viaAerea);
        localizacaoCanula = (TextView)findViewById(R.id.localizacaoCanula);
        murmurioVesicular = (TextView)findViewById(R.id.murmurioVesicular);
        baseMurmurioVesicular = (TextView)findViewById(R.id.baseMurmurioVesicular);
        tercoMedioMurmurioVesicular = (TextView)findViewById(R.id.tercoMedioMurmurioVesicular);
        apiceMurmurioVesicular = (TextView)findViewById(R.id.apiceMurmurioVesicular);
        usoDeOxigenioTextView = (TextView)findViewById(R.id.usoDeOxigenioSelected);
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
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Apice);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, apiceSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        apiceMurmurioVesicular.setText(items[which]);
                        apiceMurmurioVesicular.setVisibility(View.VISIBLE);
                        apiceSelection=which;
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

    public void usoDeOxigenioMurmurioVesicularOnClick(View view){
        if(usoDeOxigenioSwitchCompat.isChecked()){
            usoDeOxigenioTextView.setText(getString(R.string.Nao));
            usoDeOxigenioSwitchCompat.setChecked(false);
            myAnimation.slideUpView(getApplicationContext(),mascaraDeVenturi_layout);
        }
        else
            showDialog();
    }

    private void showDialog(){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.UsoDeOxigenio);

        //list of items
        final String[] items = getResources().getStringArray(R.array.usoDeOxigenio);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, usoDeOxigenioSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        usoDeOxigenioTextView.setText(items[which]);
                        usoDeOxigenioTextView.setVisibility(View.VISIBLE);
                        usoDeOxigenioSelection=which;
                        usoDeOxigenioSwitchCompat.setChecked(true);
                        myAnimation.slideUpView(getApplicationContext(),mascaraDeVenturi_layout);
                        if(items[which].equals(getResources().getStringArray(R.array.usoDeOxigenio)[1])){
                            if(!mascaraDeVenturi_layout.isShown()) {
                                myAnimation.slideDownView(getApplicationContext(),mascaraDeVenturi_layout);
                            }
                        }
                        else{
                            if(mascaraDeVenturi_layout.isShown()) {
                                myAnimation.slideUpView(getApplicationContext(),mascaraDeVenturi_layout);
                            }
                        }
                        dialog.dismiss();
                    }
                });
        String positiveText = getString(R.string.Selecionar);
        builder.setPositiveButton(positiveText,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(usoDeOxigenioSelection!=-1) {
                    usoDeOxigenioTextView.setText(items[which]);
                    usoDeOxigenioTextView.setVisibility(View.VISIBLE);
                    usoDeOxigenioSelection=which;
                    usoDeOxigenioSwitchCompat.setChecked(true);
                    myAnimation.slideUpView(getApplicationContext(),mascaraDeVenturi_layout);
                    if(items[which].equals(getResources().getStringArray(R.array.usoDeOxigenio)[1])){
                        if(!mascaraDeVenturi_layout.isShown()) {
                            myAnimation.slideDownView(getApplicationContext(),mascaraDeVenturi_layout);
                        }
                    }
                    else{
                        if(mascaraDeVenturi_layout.isShown()) {
                            myAnimation.slideUpView(getApplicationContext(),mascaraDeVenturi_layout);
                        }
                    }
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

    public void mascaraVenturiOnClick(final View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(RespiratorioActivity.this,R.style.MyDialogTheme);
        builder.setTitle(getString(R.string.MascaraVenturi));
        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.mascara_venturi_dialog,null);

        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.mascaraVenturiRadioGroup);
        dialogView.requestFocus();
        final TextInputEditText doseDroga = (TextInputEditText) dialogView.findViewById(R.id.dose);
        final TextInputEditText velInfusao = (TextInputEditText)dialogView.findViewById(R.id.velInfusao);


        builder.setView(dialogView);
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        final AlertDialog dialog = builder.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));


    }

}
