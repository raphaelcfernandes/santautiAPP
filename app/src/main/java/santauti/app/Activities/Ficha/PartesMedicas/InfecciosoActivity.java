package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Arrays;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Infeccioso;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class InfecciosoActivity extends GenericoActivity {
    private Realm realm;
    private int marcadoresInfeccaoSelection=-1;
    private TextView marcadoresInfeccao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infeccioso);
        setToolbar(getString(R.string.Infeccioso));

        /*********************VIEWS***********************/
        marcadoresInfeccao = (TextView)findViewById(R.id.marcadoresInfeccao);
        /*********************VIEWS***********************/


        realm = Realm.getDefaultInstance();
        prepareNavigationButtons();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), MetabolicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1,intent);
                startActivity(intent);
                exitActivityToLeft();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HematologicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });
    }


    private int getInfecciosoSelected(){
        Ficha f = getProperFicha();
        if(f.getInfeccioso()!=null)
            return f.getInfeccioso().getPacienteComInfeccao();
        else
            return -1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onBackPressed(){
        //verificaCamposENotificaAdapter();
        finish();
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

    public void verificaCamposENotificaAdapter(){
//        if(infecciosoSim.isChecked() || infecciosoNao.isChecked()) {
//            realm.beginTransaction();
//            Infeccioso infecc = realm.createObject(Infeccioso.class);
//            infecc.setPacienteComInfeccao(infeccioso);
//            Ficha r = getProperFicha();
//            r.setInfeccioso(infecc);
//            realm.copyToRealmOrUpdate(r);
//            realm.commitTransaction();
//            changeCardColor();
//        }
    }



    public void marcadoresInfeccaoOnClick(View view){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.MarcadoresInfeccao);

        //list of items
        final String[] items = getResources().getStringArray(R.array.marcadoresInfeccao);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, marcadoresInfeccaoSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        marcadoresInfeccao.setText(items[which]);
                        marcadoresInfeccao.setVisibility(View.VISIBLE);
                        marcadoresInfeccaoSelection=which;
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
