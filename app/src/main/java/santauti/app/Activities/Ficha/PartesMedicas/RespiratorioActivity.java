package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class RespiratorioActivity extends GenericoActivity {
    private boolean[] roncos=new boolean[5],sibilos=new boolean[5],crepitacoes=new boolean[5],murmurioVesicular = new boolean[8];
    private int mascaraVenturiSelection=-1,usoDeOxigenioSelection=-1;
    private View pressaoCuff_layout;
    private View localizacaoCanula_layout;
    private View mascaraDeVenturi_layout, fluxoOxigenio_layout;
    private TextView viasAereasTextView, localizacaoCanulaTextView, murmurioVesicularTextView,mascaraVenturiSelected,roncosTextView,sibilosTextView,crepitacoesTextView;
    private TextView usoDeOxigenioTextView;
    private TextView menuViasAereas,menuLocalizacaoCanula;
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
        mascaraVenturiSelected = (TextView)findViewById(R.id.mascaraVenturiSelected);
        viasAereasTextView = (TextView)findViewById(R.id.visaAereasTextView);
        localizacaoCanulaTextView = (TextView)findViewById(R.id.localizacaoCanulaTextView);
        murmurioVesicularTextView = (TextView)findViewById(R.id.murmurioVesicular);
        usoDeOxigenioTextView = (TextView)findViewById(R.id.usoDeOxigenioSelected);
        roncosTextView = (TextView)findViewById(R.id.roncosTextView);
        sibilosTextView = (TextView)findViewById(R.id.sibilosTextView);
        crepitacoesTextView = (TextView)findViewById(R.id.crepitacoesTextView);
        menuViasAereas = (TextView)findViewById(R.id.menuViasAereas);
        menuLocalizacaoCanula = (TextView)findViewById(R.id.menuLocalizacaoCanula);
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
        PopupMenu popupMenu = new PopupMenu(view.getContext(), menuViasAereas, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_vias_aereas, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.natural:
                        viasAereasTextView.setText(item.getTitle());
                        if(localizacaoCanula_layout.isShown() && pressaoCuff_layout.isShown()){
                            myAnimation.slideUpView(getApplicationContext(),localizacaoCanula_layout);
                            myAnimation.slideUpView(getApplicationContext(),pressaoCuff_layout);
                        }
                        break;
                    case R.id.tuboTraquial:
                        viasAereasTextView.setText(item.getTitle());
                        if(!localizacaoCanula_layout.isShown() && !pressaoCuff_layout.isShown()){
                            myAnimation.slideDownView(getApplicationContext(),localizacaoCanula_layout);
                            myAnimation.slideDownView(getApplicationContext(),pressaoCuff_layout);
                        }
                        break;
                    case R.id.traqueoostomia:
                        viasAereasTextView.setText(item.getTitle());
                        if(!localizacaoCanula_layout.isShown() && !pressaoCuff_layout.isShown()){
                            myAnimation.slideDownView(getApplicationContext(),localizacaoCanula_layout);
                            myAnimation.slideDownView(getApplicationContext(),pressaoCuff_layout);
                        }
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public void localizacaoCanulaOnClick(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), menuLocalizacaoCanula, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_localizacao_canula, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.alta:
                        localizacaoCanulaTextView.setText(item.getTitle());
                        break;
                    case R.id.media:
                        localizacaoCanulaTextView.setText(item.getTitle());
                        break;
                    case R.id.baixa:
                        localizacaoCanulaTextView.setText(item.getTitle());
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public void murmurioVesicularOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        final ArrayList mSelectedItems = new ArrayList();

        // Set the dialog title
        builder.setTitle(R.string.MurmurioVesicular);
        final String[] items = getResources().getStringArray(R.array.murmurioVesicular);
        builder.setMultiChoiceItems(items, murmurioVesicular,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        murmurioVesicular[which]=isChecked;
                    }
                })
                // Set the action buttons
                .setPositiveButton(R.string.Selecionar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setTextViewFromDialogMultipleText(murmurioVesicular,murmurioVesicularTextView,items);
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

    public void roncosOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        final ArrayList mSelectedItems = new ArrayList();

        // Set the dialog title
        builder.setTitle(R.string.Roncos);
        final String[] items = getResources().getStringArray(R.array.crepitacoes);
        builder.setMultiChoiceItems(items, roncos,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        roncos[which]=isChecked;
                    }
                })
                // Set the action buttons
                .setPositiveButton(R.string.Selecionar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setTextViewFromDialogMultipleText(roncos,roncosTextView,items);
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

    public void sibilosOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        final ArrayList mSelectedItems = new ArrayList();

        // Set the dialog title
        builder.setTitle(R.string.Sibilos);
        final String[] items = getResources().getStringArray(R.array.crepitacoes);
        builder.setMultiChoiceItems(items, sibilos,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        sibilos[which]=isChecked;
                    }
                })
                // Set the action buttons
                .setPositiveButton(R.string.Selecionar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setTextViewFromDialogMultipleText(sibilos,sibilosTextView,items);
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

    public void crepitacoesOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        final ArrayList mSelectedItems = new ArrayList();

        // Set the dialog title
        builder.setTitle(R.string.Crepitacoes);
        final String[] items = getResources().getStringArray(R.array.crepitacoes);
        builder.setMultiChoiceItems(items, crepitacoes,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        crepitacoes[which]=isChecked;
                    }
                })
                // Set the action buttons
                .setPositiveButton(R.string.Selecionar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setTextViewFromDialogMultipleText(crepitacoes,crepitacoesTextView,items);
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
