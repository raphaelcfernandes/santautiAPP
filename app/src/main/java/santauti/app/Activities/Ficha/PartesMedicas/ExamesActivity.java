package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.R;

/**
 * Created by raphael on 8/6/17.
 */

public class ExamesActivity extends GenericoActivity{
    TextInputEditText gasometrialArterial;
    private int gasometriaArterialInput,disturbioEletroliticoSelection=-1,acidoseMetabolicaSelection=-1;
    private TextView disturbioEletrolitico,acidoseMetabolica,menuPotassio,potassioTextView,magnesioTextView,menuMagnesio,menuFosforo,fosforoTextView;
    private TextView menuCalcio,calcioTextView,menuAlbumina,albuminaTextView;
    private View eletrolitoItens;
    private MyAnimation myAnimation;
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

        potassioTextView = (TextView)findViewById(R.id.potassioTextView);
        magnesioTextView = (TextView)findViewById(R.id.magnesioTextView);
        fosforoTextView = (TextView)findViewById(R.id.fosforoTextView);
        calcioTextView = (TextView)findViewById(R.id.calcioTextView);
        albuminaTextView = (TextView)findViewById(R.id.albuminaTextView);
        eletrolitoItens = findViewById(R.id.eletrolitosItens);


        gasometrialArterial = (TextInputEditText)findViewById(R.id.gasometrial_arterial);
        gasometrialArterial.addTextChangedListener(textWatcher);

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

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable editable) {
            if(gasometrialArterial.getText().toString().length()>0)
                gasometriaArterialInput = (Integer.parseInt(gasometrialArterial.getText().toString()));
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    };

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
}
