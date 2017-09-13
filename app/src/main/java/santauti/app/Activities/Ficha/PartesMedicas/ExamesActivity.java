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
    private int disturbioEletroliticoSelection=-1,acidoseMetabolicaSelection=-1, raioxToraxSelection =-1,leucogramaSelection=-1,funcaoHepaticaSelection=-1;
    private TextView disturbioEletrolitico,acidoseMetabolica,menuPotassio,potassioTextView,magnesioTextView,menuMagnesio,menuFosforo,fosforoTextView;
    private TextView menuCalcio,calcioTextView,menuAlbumina,albuminaTextView,pcrTextView,gasometriaArterialMetabolicoTextView,
            gasometriaArterialRespiratorioTextView,gasometriaArterialTextView,raioxToraxTextView,leucogramaTextView,funcaoHepaticaTextView,amilaseTextView;
    private View eletrolitoItens, pcrLayout,gasometrialArterialItensLayout,amilaseLayout;
    private MyAnimation myAnimation;
    private boolean[] gasometriaArterialMetabolico = new boolean[4],gasometriaArterialRespiratorio = new boolean[4];
    private SwitchCompat gasometriaArterialSwitch,funcaoHepaticaSwitch,amilaseSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames);
        setToolbar(getString(R.string.Exames));
        prepareNavigationButtons();

        disturbioEletrolitico = (TextView)findViewById(R.id.disturbioEletrolitico);
        acidoseMetabolica = (TextView)findViewById(R.id.acidoseMetabolica);
        menuPotassio = (TextView)findViewById(R.id.menuPotassio);
        menuMagnesio = (TextView)findViewById(R.id.menuMagnesio);
        menuFosforo = (TextView)findViewById(R.id.menuFosforo);
        menuCalcio = (TextView)findViewById(R.id.menuCalcio);
        menuAlbumina = (TextView)findViewById(R.id.menuAlbumina);
        pcrTextView = (TextView)findViewById(R.id.pcrTextView);

        potassioTextView = (TextView)findViewById(R.id.potassioTextView);
        magnesioTextView = (TextView)findViewById(R.id.magnesioTextView);
        fosforoTextView = (TextView)findViewById(R.id.fosforoTextView);
        calcioTextView = (TextView)findViewById(R.id.calcioTextView);
        albuminaTextView = (TextView)findViewById(R.id.albuminaTextView);
        eletrolitoItens = findViewById(R.id.eletrolitosItens);
        pcrLayout = findViewById(R.id.pcrLayout);
        gasometriaArterialSwitch = (SwitchCompat)findViewById(R.id.gasometriaArterialSwitch);
        gasometrialArterialItensLayout = findViewById(R.id.gasometriaArterialItens);
        gasometriaArterialMetabolicoTextView = (TextView)findViewById(R.id.metabolicoGasometriaTextView);
        gasometriaArterialRespiratorioTextView = (TextView)findViewById(R.id.respiratorioGasometriaTextView);
        gasometriaArterialTextView = (TextView)findViewById(R.id.gasometriaArterialTextView);
        raioxToraxTextView = (TextView)findViewById(R.id.raioxToraxTextView);
        leucogramaTextView = (TextView)findViewById(R.id.leucogramaTextView);
        funcaoHepaticaTextView = (TextView)findViewById(R.id.funcaoHepaticaTextView);
        funcaoHepaticaSwitch = (SwitchCompat)findViewById(R.id.funcaoHepaticaSwitch);
        amilaseTextView = (TextView)findViewById(R.id.amilaseTextView);
        amilaseSwitch = (SwitchCompat)findViewById(R.id.amilaseSwitch);
        amilaseLayout = findViewById(R.id.amilaseLayout);
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

    public void potassioOnClick(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), menuPotassio, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_eletrolitos_potassio, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hipercalemia:
                        potassioTextView.setText(item.getTitle());
                        break;
                    case R.id.normal:
                        potassioTextView.setText(item.getTitle());
                        break;
                    case R.id.hipocalemia:
                        potassioTextView.setText(item.getTitle());
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public void magnesioOnClick(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), menuMagnesio, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_eletrolitos_magnesio, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hipermagnesemia:
                        magnesioTextView.setText(item.getTitle());
                        break;
                    case R.id.normal:
                        magnesioTextView.setText(item.getTitle());
                        break;
                    case R.id.hipomagnesemia:
                        magnesioTextView.setText(item.getTitle());
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public void fosforoOnClick(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), menuFosforo, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_eletrolitos_fosforo, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hiperfosfatemia:
                        fosforoTextView.setText(item.getTitle());
                        break;
                    case R.id.normal:
                        fosforoTextView.setText(item.getTitle());
                        break;
                    case R.id.hipofosfatemia:
                        fosforoTextView.setText(item.getTitle());
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public void calcioOnClick(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), menuCalcio, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_eletrolitos_calcio, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hipercalcemia:
                        calcioTextView.setText(item.getTitle());
                        break;
                    case R.id.normal:
                        calcioTextView.setText(item.getTitle());
                        break;
                    case R.id.hipocalcemia:
                        calcioTextView.setText(item.getTitle());
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public void pcrOnCLick (View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), pcrLayout, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pcr, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.elevando:
                        pcrTextView.setText(item.getTitle());
                        break;
                    case R.id.estavel:
                        pcrTextView.setText(item.getTitle());
                        break;
                    case R.id.emQueda:
                        pcrTextView.setText(item.getTitle());
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public void raioxToraxOnCLick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.RaioxTorax);

        //list of items
        final String[] items = getResources().getStringArray(R.array.raioxTorax);
        Arrays.sort(items);
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

    public void albuminaOnClick(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), menuAlbumina, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_eletrolitos_albumina, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hiperalbuminemia:
                        albuminaTextView.setText(item.getTitle());
                        break;
                    case R.id.normal:
                        albuminaTextView.setText(item.getTitle());
                        break;
                    case R.id.hipoalbuminemia:
                        albuminaTextView.setText(item.getTitle());
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public void eletrolitosOnCLick(View view){
        if(eletrolitoItens.isShown())
            myAnimation.slideUpView(getApplicationContext(),eletrolitoItens);
        else
            myAnimation.slideDownView(getApplicationContext(),eletrolitoItens);
    }

    public void gasometriaArterialOnClick(View view) {
        StringBuilder gasometriaArterial = new StringBuilder();
        if(gasometriaArterialSwitch.isChecked()) {
            if (gasometrialArterialItensLayout.isShown()) {
                myAnimation.slideUpView(getApplicationContext(), gasometrialArterialItensLayout);
                for (int i = 0; i < gasometriaArterialMetabolico.length; i++)
                    if (gasometriaArterialMetabolico[i])
                        gasometriaArterial.append(getResources().getStringArray(R.array.gasometriaArterialMetabolico)[i]).append(", ");
                for (int i = 0; i < gasometriaArterialRespiratorio.length; i++)
                    if (gasometriaArterialRespiratorio[i])
                        gasometriaArterial.append(getResources().getStringArray(R.array.gasometriaArterialRespiratorio)[i]).append(", ");
                gasometriaArterialTextView.setText(gasometriaArterial.substring(0,gasometriaArterial.length()-2));
                gasometriaArterialTextView.setVisibility(View.VISIBLE);
            }
            else {
                myAnimation.slideDownView(getApplicationContext(), gasometrialArterialItensLayout);
                gasometriaArterialTextView.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void gasometriaArterialSwitchOnClick(View view){
        if(gasometriaArterialSwitch.isChecked()) {
            gasometriaArterialTextView.setVisibility(View.INVISIBLE);
            if(!gasometrialArterialItensLayout.isShown())
                myAnimation.slideDownView(getApplicationContext(),gasometrialArterialItensLayout);
        }
        else {
            gasometriaArterialTextView.setText(R.string.Normal);
            gasometriaArterialTextView.setVisibility(View.VISIBLE);
            if(gasometrialArterialItensLayout.isShown())
                myAnimation.slideUpView(getApplicationContext(),gasometrialArterialItensLayout);
        }
    }

    public void metabolicoOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        final ArrayList mSelectedItems = new ArrayList();

        // Set the dialog title
        builder.setTitle(R.string.Metabolico);
        final String[] items = getResources().getStringArray(R.array.gasometriaArterialMetabolico);
        builder.setMultiChoiceItems(items, gasometriaArterialMetabolico,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        if (isChecked)
                            gasometriaArterialMetabolico[which]=true;
                        else
                            gasometriaArterialMetabolico[which]=false;
                    }
                })
                // Set the action buttons
                .setPositiveButton(R.string.Selecionar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setTextViewFromDialogMultipleText(gasometriaArterialMetabolico,gasometriaArterialMetabolicoTextView,items);
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

    public void respiratorioOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        final ArrayList mSelectedItems = new ArrayList();

        // Set the dialog title
        builder.setTitle(R.string.Respiratorio);
        final String[] items = getResources().getStringArray(R.array.gasometriaArterialRespiratorio);
        builder.setMultiChoiceItems(items, gasometriaArterialRespiratorio,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        if (isChecked)
                            gasometriaArterialRespiratorio[which]=true;
                        else
                            gasometriaArterialRespiratorio[which]=false;
                    }
                })
                // Set the action buttons
                .setPositiveButton(R.string.Selecionar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setTextViewFromDialogMultipleText(gasometriaArterialRespiratorio,gasometriaArterialRespiratorioTextView,items);
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
        if(!funcaoHepaticaSwitch.isChecked())
            funcaoHepaticaDialog();
        else{
            funcaoHepaticaTextView.setText(getString(R.string.Nao));
            funcaoHepaticaSwitch.setChecked(false);
        }
    }

    private void funcaoHepaticaDialog(){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.FuncaoHepatica);

        //list of items
        final String[] items = getResources().getStringArray(R.array.funcaoHepatica);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, funcaoHepaticaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        funcaoHepaticaTextView.setText(items[which]);
                        funcaoHepaticaTextView.setVisibility(View.VISIBLE);
                        funcaoHepaticaSelection=which;
                        funcaoHepaticaSwitch.setChecked(true);
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

    public void amilaseOnClick(View view){
        if(!amilaseSwitch.isChecked())
            amilaseDialog(view);
        else{
            amilaseTextView.setText(getString(R.string.Nao));
            amilaseSwitch.setChecked(false);
        }
    }

    private void amilaseDialog(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), amilaseLayout, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_amilase, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.normal:
                        amilaseTextView.setText(item.getTitle());
                        amilaseSwitch.setChecked(true);
                        break;
                    case R.id.elevada:
                        amilaseTextView.setText(item.getTitle());
                        amilaseSwitch.setChecked(true);
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }
}
