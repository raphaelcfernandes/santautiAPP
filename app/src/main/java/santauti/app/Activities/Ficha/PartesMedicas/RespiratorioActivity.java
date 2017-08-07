package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
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
    private int viasAereasSelection=-1,localizacaoCanulaSelection=-1,baseRoncosSelection=-1,tercoMedioRoncosSelection=-1,apiceRoncosSelection=-1;
    private int murmurioVesicularSelection=-1,baseMurmurioVesicularSelection=-1,baseSibilosSelection=-1;
    private int mascaraVenturiSelection=-1,tercoMedioMurmurioVesicularSelection=-1,usoDeOxigenioSelection=-1,apiceSelection=-1,roncoSelection=-1,sibiloSelection=-1,crepitacoesSelection=-1;
    private int baseCrepitacoesSelection=-1,tercoMedioCrepitacoesSelection=-1,tercoMedioSibilosSelection=-1,apiceSibilosSelection=-1,apiceCrepitacoesSelection=-1;
    private View pressaoCuff_layout,murmurioVesicularItensLayout,roncosItensLayout,sibilosItensLayout,crepitacoesItensLayout;
    private View localizacaoCanula_layout;
    private View mascaraDeVenturi_layout, fluxoOxigenio_layout;
    private TextView viasAerea,localizacaoCanula,murmurioVesicular,mascaraVenturiSelected,baseRoncos,tercoMedioRoncos,apiceRoncos,roncosTextView,sibilosTextView,crepitacoesTextView;
    private TextView baseMurmurioVesicular,tercoMedioMurmurioVesicular,apiceMurmurioVesicular,usoDeOxigenioTextView,baseSibilos,baseCrepitacoes;
    private TextView tercoMedioSibilos,tercoMedioCrepitacoes,apiceSibilos,apiceCrepitacoes;
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
        pressaoCuff_layout = findViewById(R.id.pressaoCuff_layout);
        localizacaoCanula_layout = findViewById(R.id.localizacaoCanula_layout);
        mascaraDeVenturi_layout = findViewById(R.id.mascaVenturi);
        fluxoOxigenio_layout = findViewById(R.id.fluxoOxigenioLayout);
        roncosItensLayout = findViewById(R.id.roncosItensLayout);
        sibilosItensLayout = findViewById(R.id.sibilosItensLayout);
        crepitacoesItensLayout = findViewById(R.id.crepitacoesItensLayout);
        murmurioVesicularItensLayout = findViewById(R.id.murmurioVesicularItensLayout);
        mascaraVenturiSelected = (TextView)findViewById(R.id.mascaraVenturiSelected);
        viasAerea = (TextView)findViewById(R.id.viaAerea);
        localizacaoCanula = (TextView)findViewById(R.id.localizacaoCanula);
        murmurioVesicular = (TextView)findViewById(R.id.murmurioVesicular);
        baseMurmurioVesicular = (TextView)findViewById(R.id.baseMurmurioVesicular);
        tercoMedioMurmurioVesicular = (TextView)findViewById(R.id.tercoMedioMurmurioVesicular);
        apiceMurmurioVesicular = (TextView)findViewById(R.id.apiceMurmurioVesicular);
        usoDeOxigenioTextView = (TextView)findViewById(R.id.usoDeOxigenioSelected);
        baseRoncos = (TextView)findViewById(R.id.baseRoncos);
        tercoMedioRoncos = (TextView)findViewById(R.id.tercoMedioRoncos);
        apiceRoncos = (TextView)findViewById(R.id.apiceRoncos);
        baseSibilos = (TextView)findViewById(R.id.baseSibilos);
        baseCrepitacoes = (TextView)findViewById(R.id.baseCrepitacoes);;
        tercoMedioSibilos = (TextView)findViewById(R.id.tercoMedioSibilos);
        tercoMedioCrepitacoes = (TextView)findViewById(R.id.tercoMedioCrepitacoes);
        apiceSibilos = (TextView)findViewById(R.id.apiceSibilos);
        apiceCrepitacoes = (TextView)findViewById(R.id.apiceCrepitacoes);
        roncosTextView = (TextView)findViewById(R.id.roncosTextView);
        sibilosTextView = (TextView)findViewById(R.id.sibilosTextView);
        crepitacoesTextView = (TextView)findViewById(R.id.crepitacoesTextView);
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
        final String[] items = getResources().getStringArray(R.array.respiratorioIndices);
        builder.setSingleChoiceItems(items, murmurioVesicularSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        murmurioVesicular.setText(items[which]);
                        murmurioVesicular.setVisibility(View.VISIBLE);
                        murmurioVesicularSelection=which;
                        if(!murmurioVesicularItensLayout.isShown())
                            myAnimation.slideDownView(getApplicationContext(),murmurioVesicularItensLayout);
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

    public void roncosOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Roncos);

        //list of items
        final String[] items = getResources().getStringArray(R.array.respiratorioIndices);
        builder.setSingleChoiceItems(items, roncoSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        roncosTextView.setText(items[which]);
                        roncosTextView.setVisibility(View.VISIBLE);
                        roncoSelection=which;
                        if(!roncosItensLayout.isShown())
                            myAnimation.slideDownView(getApplicationContext(),roncosItensLayout);
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

    public void sibilosOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Sibilos);

        //list of items
        final String[] items = getResources().getStringArray(R.array.respiratorioIndices);
        builder.setSingleChoiceItems(items, sibiloSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sibilosTextView.setText(items[which]);
                        sibilosTextView.setVisibility(View.VISIBLE);
                        sibiloSelection=which;
                        if(!sibilosItensLayout.isShown())
                            myAnimation.slideDownView(getApplicationContext(),sibilosItensLayout);
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

    public void crepitacoesOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Crepitacoes);

        //list of items
        final String[] items = getResources().getStringArray(R.array.respiratorioIndices);
        builder.setSingleChoiceItems(items, crepitacoesSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        crepitacoesTextView.setText(items[which]);
                        crepitacoesTextView.setVisibility(View.VISIBLE);
                        crepitacoesSelection=which;
                        if(!crepitacoesItensLayout.isShown())
                            myAnimation.slideDownView(getApplicationContext(),crepitacoesItensLayout);
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

        builder.setTitle(R.string.BaseMurmurio);

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

    public void baseRoncosOnclick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.BaseRoncos);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, baseRoncosSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseRoncos.setText(items[which]);
                        baseRoncos.setVisibility(View.VISIBLE);
                        baseRoncosSelection=which;
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

    public void baseSibilosOnclick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.BaseSibilos);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, baseSibilosSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseSibilos.setText(items[which]);
                        baseSibilos.setVisibility(View.VISIBLE);
                        baseSibilosSelection=which;
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

    public void baseCrepitacoesOnclick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.BaseCrepitacoes);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, baseCrepitacoesSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        baseCrepitacoes.setText(items[which]);
                        baseCrepitacoes.setVisibility(View.VISIBLE);
                        baseCrepitacoesSelection=which;
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

        builder.setTitle(R.string.TercoMedioMurmurioVesicular);

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

    public void tercoMedioRoncosOnclick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.TercoMedioRoncos);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, tercoMedioRoncosSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tercoMedioRoncos.setText(items[which]);
                        tercoMedioRoncos.setVisibility(View.VISIBLE);
                        tercoMedioRoncosSelection=which;
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

    public void tercoMedioSibilosOnclick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.TercoMedioSibilos);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, tercoMedioSibilosSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tercoMedioSibilos.setText(items[which]);
                        tercoMedioSibilos.setVisibility(View.VISIBLE);
                        tercoMedioSibilosSelection=which;
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

    public void tercoMedioCrepitacoesOnclick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.TercoMedioCrepitacoes);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, tercoMedioCrepitacoesSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tercoMedioCrepitacoes.setText(items[which]);
                        tercoMedioCrepitacoes.setVisibility(View.VISIBLE);
                        tercoMedioCrepitacoesSelection=which;
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

        builder.setTitle(R.string.ApiceMurmurioVesicular);

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

    public void apiceRoncosOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.ApiceRoncos);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, apiceRoncosSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        apiceRoncos.setText(items[which]);
                        apiceRoncos.setVisibility(View.VISIBLE);
                        apiceRoncosSelection=which;
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

    public void apiceSibilosOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.ApiceSibilos);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, apiceSibilosSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        apiceSibilos.setText(items[which]);
                        apiceSibilos.setVisibility(View.VISIBLE);
                        apiceSibilosSelection=which;
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

    public void apiceCrepitacoesOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.ApiceCrepitacoes);

        //list of items
        final String[] items = getResources().getStringArray(R.array.direito_esquerdo_ambos);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, apiceCrepitacoesSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        apiceCrepitacoes.setText(items[which]);
                        apiceCrepitacoes.setVisibility(View.VISIBLE);
                        apiceCrepitacoesSelection=which;
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

    public void usoDeOxigenioOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.UsoDeOxigenio);

        //list of items
        final String[] items = getResources().getStringArray(R.array.usoDeOxigenio);
        builder.setSingleChoiceItems(items, usoDeOxigenioSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        usoDeOxigenioTextView.setText(items[which]);
                        usoDeOxigenioTextView.setVisibility(View.VISIBLE);
                        usoDeOxigenioSelection=which;
                        //Caso seja mascara de venturi
                        if(items[which].equals(getResources().getStringArray(R.array.usoDeOxigenio)[0])){
                            if(fluxoOxigenio_layout.isShown())
                                myAnimation.slideUpView(getApplicationContext(),fluxoOxigenio_layout);
                            if(!mascaraDeVenturi_layout.isShown())
                                myAnimation.slideDownView(getApplicationContext(),mascaraDeVenturi_layout);
                        }
                        //Mascara de inalaçao ou cateter nasal
                        else if(items[which].equals(getResources().getStringArray(R.array.usoDeOxigenio)[1]) ||
                                items[which].equals(getResources().getStringArray(R.array.usoDeOxigenio)[2])){
                            if(mascaraDeVenturi_layout.isShown())
                                myAnimation.slideUpView(getApplicationContext(),mascaraDeVenturi_layout);
                            if(!fluxoOxigenio_layout.isShown())
                                myAnimation.slideDownView(getApplicationContext(),fluxoOxigenio_layout);
                        }
                        //Nao
                        else{
                            if(mascaraDeVenturi_layout.isShown() || fluxoOxigenio_layout.isShown()){
                                myAnimation.slideUpView(getApplication(),mascaraDeVenturi_layout);
                                myAnimation.slideUpView(getApplication(),fluxoOxigenio_layout);
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
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void mascaraVenturiOnClick(final View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(RespiratorioActivity.this);
        builder.setTitle(getString(R.string.MascaraVenturi));
        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.mascara_venturi_dialog,null);

        final RadioGroup rg = (RadioGroup)dialogView.findViewById(R.id.mascaraVenturiRadioGroup);

        rg.check(mascaraVenturiSelection);

        builder.setView(dialogView);
        builder.setPositiveButton(getString(R.string.Selecionar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int selectedRadioButtonID = rg.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = (RadioButton)rg.findViewById(selectedRadioButtonID);
                String selectedRadioButtonText = selectedRadioButton.getText().toString();
                mascaraVenturiSelection = selectedRadioButtonID;
                mascaraVenturiSelected.setText(selectedRadioButtonText);
                mascaraVenturiSelected.setVisibility(View.VISIBLE);
            }
        });

        builder.setNegativeButton(getString(R.string.Cancelar), new DialogInterface.OnClickListener() {
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
