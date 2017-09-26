package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Gastrointestinal;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class GastrointestinalActivity extends GenericoActivity {
    private Realm realm;
    private TextView massasPalpaveisTextView,viscerasPalpaveisTextView;
    private CheckBox massasPalpaveisCheckBox,viscerasPalpaveisCheckBox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastrointestinal);
        findViewById(R.id.gastrointestinal_layout).requestFocus();
        setToolbar(getString(R.string.GastroIntestinal));

        massasPalpaveisCheckBox = (CheckBox)findViewById(R.id.checkboxMassasPalpaveis);
        viscerasPalpaveisCheckBox = (CheckBox)findViewById(R.id.checkboxViscerasPalpaveis);
        massasPalpaveisTextView = (TextView)findViewById(R.id.massasPalpaveisTextView);
        viscerasPalpaveisTextView = (TextView)findViewById(R.id.viscerasPalpaveisTextView);
        prepareNavigationButtons();

        realm = Realm.getDefaultInstance();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RespiratorioActivity.class);
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
                intent = new Intent(view.getContext(), RenalActivity.class);
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
        //realm.close();
    }

    @Override
    public void onBackPressed(){
       // verificaCamposENotificaAdapter();
        finish();
    }

    private void verificaCamposENotificaAdapter() {
//        realm.beginTransaction();
//        int i=0;
//        Gastrointestinal gastrointestinal = realm.createObject(Gastrointestinal.class);
//        if(!formatoSpinner.getSelectedItem().toString().equals(defaultSpinnerString)) {
//            gastrointestinal.setFormato(formatoSpinner.getSelectedItem().toString());
//            i++;
//        }
//        if(!tensaoSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
//            gastrointestinal.setTensao(tensaoSpinner.getSelectedItem().toString());
//            i++;
//        }
//        if(!ruidosSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
//            gastrointestinal.setRuidos(ruidosSpinner.getSelectedItem().toString());
//            i++;
//        }
//        if(vcmPresente.isChecked()){
//            gastrointestinal.setVcm(1);
//            i++;
//        }
//        else{
//            gastrointestinal.setVcm(0);
//            i++;
//        }
//        if(i==4) {
//            Ficha r = getProperFicha();
//            r.setGastrointestinal(gastrointestinal);
//            realm.copyToRealmOrUpdate(r);
//            changeCardColor();
//        }
//        realm.commitTransaction();
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

    public void massasPalpaveisOnClick(View view){
        if(massasPalpaveisCheckBox.isChecked()){
            //massasPalpaveisTextView.setText(getString(R.string.Nao));
            massasPalpaveisCheckBox.setChecked(false);
        }
        else{
            //massasPalpaveisTextView.setText(getString(R.string.Sim));
            massasPalpaveisCheckBox.setChecked(true);
        }
    }

    public void viscerasPalpaveisOnClick(View view){
        if(viscerasPalpaveisCheckBox.isChecked()){
            //viscerasPalpaveisTextView.setText(getString(R.string.Nao));
            viscerasPalpaveisCheckBox.setChecked(false);
        }
        else{
            //viscerasPalpaveisTextView.setText(getString(R.string.Sim));
            viscerasPalpaveisCheckBox.setChecked(true);
        }
    }

}
