package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class InfecciosoActivity extends GenericoActivity {
    private Realm realm;
    private boolean[] antibioticos = new boolean[24];
    private TextView antibioticoTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infeccioso);
        setToolbar(getString(R.string.Infeccioso));

        /*********************VIEWS***********************/
        //antibioticoTextView = (TextView)findViewById(R.id.antibioticoTextView);
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
                intent = new Intent(view.getContext(), NutricionalActivity.class);
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
//            changeCardColorToGreen();
//        }
    }

    public void antibioticoOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        final ArrayList mSelectedItems = new ArrayList();

        // Set the dialog title
        builder.setTitle(R.string.Antibiotico);
        final String[] items = getResources().getStringArray(R.array.antibioticos);
        Arrays.sort(items);
        builder.setMultiChoiceItems(items, antibioticos,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        if (isChecked)
                            antibioticos[which]=true;
                        else
                            antibioticos[which]=false;
                    }
                })
                // Set the action buttons
                .setPositiveButton(R.string.Selecionar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setTextViewFromDialogMultipleText(antibioticos,antibioticoTextView,items);
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
