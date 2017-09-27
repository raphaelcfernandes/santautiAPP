package santauti.app.Activities.Ficha.PartesMedicas;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

import io.realm.Realm;
import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.R;

/**
 * Created by raphael on 6/20/17.
 */

public class MonitorMultiparametricoActivity extends GenericoActivity{
    Realm realm;
    private int ritmo=-1;
    private TextView textView;
    private LinearLayout mainLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_multiparametrico);
        mainLayout = (LinearLayout)findViewById(R.id.monitor_multiparametrico_layout);
        mainLayout.requestFocus();
        setupUI(mainLayout);
        setToolbar(getString(R.string.MonitorMultiparametrico));
        textView = (TextView)findViewById(R.id.ritmo);
        //prepareSpinner();
        prepareNavigationButtons();



        realm = Realm.getDefaultInstance();
        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), BombaInfusaoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                realm.close();

                finish();
            }
        });
    }

    @Override
    public void prepareNavigationButtons() {
        proxFicha = (Button)findViewById(R.id.fichaProxima);
        antFicha = (Button)findViewById(R.id.fichaAnterior);
        antFicha.setVisibility(View.GONE);
        proxFicha.setText(FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0)+1).getName()+" >");
    }

    public void ritmoOnClick(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Ritmo);

        //list of items
        final String[] items = getResources().getStringArray(R.array.tiposTracado);
        builder.setSingleChoiceItems(items, ritmo,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(items[which]);
                        textView.setVisibility(View.VISIBLE);
                        ritmo=which;
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
