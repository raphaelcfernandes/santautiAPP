package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 09-Aug-17.
 */

public class NutricionalActivity extends GenericoActivity {
    private int ingestaoSelection=-1;
    private TextView ingestaoTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricional);
        setToolbar(getString(R.string.Nutricional));

        prepareNavigationButtons();
        //createPopupIngestao();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), MetabolicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), PelesMucosasActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                finish();
            }
        });

    }
    public void ingestaoOnClick(View view){
        createPopupIngestao();
    }

    private void createPopupIngestao(){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Ingestao);

        //list of items
        final String[] items = getResources().getStringArray(R.array.ingestao);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, ingestaoSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ingestaoTextView.setText(items[which]);
                        ingestaoTextView.setVisibility(View.VISIBLE);
                        ingestaoSelection=which;
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
}
