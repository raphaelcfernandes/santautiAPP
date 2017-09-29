package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.R;

/**
 * Created by raphael on 8/6/17.
 */

public class ExamesActivity extends GenericoActivity{
    private int acidoseMetabolicaSelection=-1;
    private TextView raioxMenu,acidoseMetabolica,leucogramaMenu;
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
        setupUI(findViewById(R.id.exames_activity));
        eletrolitoItens = findViewById(R.id.eletrolitosItens);
        raioxToraxTextView = (TextView)findViewById(R.id.raioxToraxTextView);
        leucogramaTextView = (TextView)findViewById(R.id.leucogramaTextView);
        gasometriaArterialTextView = (TextView)findViewById(R.id.gasometriaArterialTextView);
        raioxMenu = (TextView)findViewById(R.id.raioxMenu);
        leucogramaMenu = (TextView)findViewById(R.id.leucogramaMenu);
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


    public void raioxToraxOnCLick(View view){
        final ListPopupWindow listPopupWindow = new ListPopupWindow(
                ExamesActivity.this);
        listPopupWindow.setAdapter(new ArrayAdapter<>(
                ExamesActivity.this,
                R.layout.list_item, getResources().getStringArray(R.array.raioxTorax)));
        listPopupWindow.setAnchorView(raioxToraxTextView);
        listPopupWindow.setWidth(700);
        listPopupWindow.setHeight(700);
        listPopupWindow.setModal(true);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                raioxToraxTextView.setText(getResources().getStringArray(R.array.raioxTorax)[i]);
                listPopupWindow.dismiss();
            }
        });
        listPopupWindow.show();

    }

    public void leucogramaOnCLick(View view){
        final ListPopupWindow listPopupWindow = new ListPopupWindow(
                ExamesActivity.this);
        listPopupWindow.setAdapter(new ArrayAdapter<>(
                ExamesActivity.this,
                R.layout.list_item, getResources().getStringArray(R.array.leucograma)));
        listPopupWindow.setAnchorView(leucogramaTextView);
        listPopupWindow.setWidth(700);
        listPopupWindow.setHeight(700);
        listPopupWindow.setModal(true);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                leucogramaTextView.setText(getResources().getStringArray(R.array.leucograma)[i]);
                listPopupWindow.dismiss();
            }
        });
        listPopupWindow.show();
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

}
