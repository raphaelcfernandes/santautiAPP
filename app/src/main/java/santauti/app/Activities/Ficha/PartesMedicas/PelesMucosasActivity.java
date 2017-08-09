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
import android.widget.TextView;

import java.util.Arrays;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 09-Aug-17.
 */

public class PelesMucosasActivity extends GenericoActivity{
    private SwitchCompat ulceraSwitch;
    private TextView ulceraPressaoTextView,peleTextView,mucosasTextView;
    private View peleLayout;
    private int mucosasSelection=-1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peles_mucosas);
        setToolbar(getString(R.string.PelesMucosas));
        prepareNavigationButtons();

        ulceraPressaoTextView = (TextView)findViewById(R.id.ulceraTextView);
        ulceraSwitch = (SwitchCompat)findViewById(R.id.ulceraPressaoSwitch);
        peleTextView = (TextView)findViewById(R.id.peleTextView);
        peleLayout = findViewById(R.id.peleLayout);
        mucosasTextView = (TextView)findViewById(R.id.mucosasTextView);

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), NutricionalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), OsteomuscularActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                finish();
            }
        });
    }

    public void ulceraPressaoOnClick(View view){
        if(ulceraSwitch.isChecked()) {
            ulceraSwitch.setChecked(false);
            ulceraPressaoTextView.setText(getString(R.string.Ausente));
        }
        else {
            ulceraPressaoTextView.setText(getString(R.string.Presente));
            ulceraSwitch.setChecked(true);
        }
    }

    public void peleOnClick(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), peleLayout, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pele, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.MnuOpc1:
                        peleTextView.setText(item.getTitle());
                        break;
                    case R.id.MnuOpc2:
                        peleTextView.setText(item.getTitle());
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    public void mucosasOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Mucosas);

        //list of items
        final String[] items = getResources().getStringArray(R.array.mucosas);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, mucosasSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mucosasTextView.setText(items[which]);
                        mucosasTextView.setVisibility(View.VISIBLE);
                        mucosasSelection=which;
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
