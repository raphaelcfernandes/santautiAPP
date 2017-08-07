package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Adapters.Ficha.BombaInfusao.BombaInfusaoAdapter;
import santauti.app.R;

/**
 * Created by raphael on 8/6/17.
 */

public class ExamesActivity extends GenericoActivity{
    TextInputEditText gasometrialArterial;
    private int gasometriaArterialInput,disturbioEletroliticoSelection=-1,acidoseMetabolicaSelection=-1;
    private TextView disturbioEletrolitico,acidoseMetabolica;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames);
        setToolbar(getString(R.string.Exames));
        prepareNavigationButtons();

        disturbioEletrolitico = (TextView)findViewById(R.id.disturbioEletrolitico);
        acidoseMetabolica = (TextView)findViewById(R.id.acidoseMetabolica);

        gasometrialArterial = (TextInputEditText)findViewById(R.id.gasometrial_arterial);
        gasometrialArterial.addTextChangedListener(textWatcher);

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HematologicoActivity.class);
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
}
