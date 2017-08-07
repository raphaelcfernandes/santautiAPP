package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Hematologico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class HematologicoActivity extends GenericoActivity {
    private TextView tromboprofilaxiaTextView;
    private SwitchCompat switchTromboprofilaxia,switchHemograma;
    private int tromboprofilaSelection=-1;
    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hematologico);

        tromboprofilaxiaTextView = (TextView)findViewById(R.id.tromboprofilaxia_selected);
        switchTromboprofilaxia = (SwitchCompat)findViewById(R.id.tromprofilaxiaSwitch);

        prepareNavigationButtons();

        setToolbar(getString(R.string.Hematologico));


        realm = Realm.getDefaultInstance();

//        Ficha ficha = getProperFicha();
//        if(ficha.getHematologico()!=null){
//            if(ficha.getHematologico().getTromboprofilaxia()==1){
//                tromboprofilaxia.setVisibility(View.VISIBLE);
//                tromboprofilaxiaS.setChecked(true);
//                int spinnerPosition = adapterProfilaxia.getPosition(ficha.getHematologico().getTipoMedicamento());
//                tromboprofilaxiaSpinner.setSelection(spinnerPosition);
//            }
//            else {
//                tromboprofilaxiaN.setChecked(true);
//                tromboprofilaxia.setVisibility(View.GONE);
//            }
//            if(ficha.getHematologico().getHemograma()==1)
//                hemogramaS.setChecked(true);
//            else
//                hemogramaN.setChecked(true);
//        }
        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), InfecciosoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                //verificaCamposENotificaAdapter();
                finish();
           }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), EndocrinoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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

    @Override
    public void onBackPressed(){
        //verificaCamposENotificaAdapter();
        finish();
    }

    private void verificaCamposENotificaAdapter(){
        realm.beginTransaction();
        int i=0;
        Hematologico hematologico = realm.createObject(Hematologico.class);

        if(i==2) {
            Ficha r = getProperFicha();
            r.setHematologico(hematologico);
            realm.copyToRealmOrUpdate(r);
            changeCardColor();
        }
        realm.commitTransaction();
    }

    public void tromboprofilaxiaOnClick(View view){
        if(switchTromboprofilaxia.isChecked()){
            tromboprofilaxiaTextView.setText(getString(R.string.Nao));
            switchTromboprofilaxia.setChecked(false);
        }
        else
            showDialog();
    }

    private void showDialog(){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Tromboprofilaxia);

        //list of items
        final String[] items = getResources().getStringArray(R.array.tromboprofilaxia);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, tromboprofilaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tromboprofilaxiaTextView.setText(items[which]);
                        tromboprofilaxiaTextView.setVisibility(View.VISIBLE);
                        tromboprofilaSelection=which;
                        switchTromboprofilaxia.setChecked(true);
                        dialog.dismiss();
                    }
                });
        String positiveText = getString(R.string.Selecionar);
        builder.setPositiveButton(positiveText,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(tromboprofilaSelection!=-1) {
                    tromboprofilaxiaTextView.setText(items[tromboprofilaSelection]);
                    tromboprofilaxiaTextView.setVisibility(View.VISIBLE);
                    switchTromboprofilaxia.setChecked(true);
                }
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
