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
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Arrays;

import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.R;

/**
 * Created by raphael on 8/6/17.
 */

public class ExamesActivity extends GenericoActivity {
    private int acidoseMetabolicaSelection = -1;
    private TextView raioxMenu, acidoseMetabolica, leucogramaMenu;
    private TextView raioxToraxTextView;
    private TextView leucogramaTextView;
    private TextView funcaoHepaticaTextView;
    private TextView gasometriaArterialTextView;
    private View eletrolitoItens, amilaseItens;
    private MyAnimation myAnimation;
    private boolean[] gasometriaArterial = new boolean[8];
    private CheckBox checkboxAmilase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames);
        setToolbar(getString(R.string.Exames));
        prepareNavigationButtons();
        setupUI(findViewById(R.id.exames_activity));
        eletrolitoItens = findViewById(R.id.eletrolitosItens);
        raioxToraxTextView = (TextView) findViewById(R.id.raioxToraxTextView);
        leucogramaTextView = (TextView) findViewById(R.id.leucogramaTextView);
        raioxMenu = (TextView) findViewById(R.id.raioxMenu);
        leucogramaMenu = (TextView) findViewById(R.id.leucogramaMenu);
        amilaseItens = findViewById(R.id.amilaseItens);
        checkboxAmilase = (CheckBox) findViewById(R.id.checkboxAmilase);
        checkboxAmilase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkboxAmilase.isChecked())
                    myAnimation.slideUpView(getApplicationContext(), amilaseItens);
                else
                    myAnimation.slideDownView(getApplicationContext(), amilaseItens);
            }
        });
        myAnimation = new MyAnimation();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), FolhasBalancoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0) - 1, intent);
                startActivity(intent);
                exitActivityToLeft();
                finish();
            }
        });

    }


    public void amilaseOnClick(View view) {
        if (amilaseItens.isShown())
            myAnimation.slideUpView(getApplicationContext(), amilaseItens);
        else {
            if (checkboxAmilase.isChecked())
                myAnimation.slideDownView(getApplicationContext(), amilaseItens);
        }
    }

    @Override
    public void prepareNavigationButtons() {
        findViewById(R.id.fichaProxima).setVisibility(View.GONE);
        antFicha = (Button) findViewById(R.id.fichaAnterior);
        antFicha.setText("< " + FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0) - 1).getName());
    }


    public void raioxToraxOnCLick(View view) {
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

    public void leucogramaOnCLick(View view) {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(
                ExamesActivity.this, null, R.attr.actionOverflowMenuStyle, 0);
        listPopupWindow.setAdapter(new ArrayAdapter<>(
                ExamesActivity.this,
                R.layout.list_item, getResources().getStringArray(R.array.leucograma)));
        listPopupWindow.setAnchorView(leucogramaMenu);
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

    public void eletrolitosOnCLick(View view) {
        if (eletrolitoItens.isShown())
            myAnimation.slideUpView(getApplicationContext(), eletrolitoItens);
        else
            myAnimation.slideDownView(getApplicationContext(), eletrolitoItens);
    }

}
