package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 09-Aug-17.
 */

public class OsteomuscularActivity extends GenericoActivity {
    private boolean[] musculatura = new boolean[6];
    private TextView musculaturaTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osteomuscular);
        setToolbar(getString(R.string.OsteoMuscular));
        prepareNavigationButtons();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), PelesMucosasActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), InfecciosoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                finish();
            }
        });

        musculaturaTextView = (TextView)findViewById(R.id.musculaturaTextView);
        createPopupMusculatura();
    }

    private void createPopupMusculatura(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        final ArrayList mSelectedItems = new ArrayList();

        // Set the dialog title
        builder.setTitle(R.string.Paresia)
                .setMultiChoiceItems(R.array.musculatura, musculatura,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked)
                                    musculatura[which]=true;
                                else
                                    musculatura[which]=false;
                            }
                        })
                // Set the action buttons
                .setPositiveButton(R.string.Selecionar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setTextViewFromDialogMusculatura(musculatura,musculaturaTextView);
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

    public void musculaturaOnClick (View view){
        createPopupMusculatura();
    }

    private void setTextViewFromDialogMusculatura(boolean[] array, TextView textView){
        StringBuilder stringBuilder = new StringBuilder();
        int i,total=0;
        for(i=0;i<array.length;i++)
            if(array[i])
                total++;
        if(total==0)
            textView.setVisibility(View.INVISIBLE);
        else{
            for (i = 0; i < array.length; i++) {
                if (array[i]) {
                    total--;
                    if (total == 0) {
                        stringBuilder.append(getResources().getStringArray(R.array.musculatura)[i]);
                    }
                    else {
                        stringBuilder.append(getResources().getStringArray(R.array.musculatura)[i]).append(", ");
                    }
                }
            }
            textView.setText(stringBuilder);
            if(!textView.isShown())
                textView.setVisibility(View.VISIBLE);
        }
    }
}
