package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.widget.AppCompatCheckBox;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.BombaInfusao;
import santauti.app.R;

/**
 * Created by raphael on 6/23/17.
 */

public class BombaInfusaoActivity extends GenericoActivity {
    private AppCompatCheckBox adrenalina,amiodarona,dobutamina,dopamina,fentanil,hidrocortisona,insulina,
            ketamina,midozalam,milrinona,nitroglicerina,nitroprussiatoDeSodio,noradrenalina,precedex,propofol;
    private MyAnimation myAnimation = new MyAnimation();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomba_infusao);

        setToolbar(getString(R.string.BombaInfusao));
        prepareNavigationButtons();
        setupUI(findViewById(R.id.bombaInfusaoLayout));

        /**************************CHECKBOX**************************/
        adrenalina = (AppCompatCheckBox)findViewById(R.id.checkBoxAdrenalina);
        adrenalina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.adrenalinaTextInputLayout);}
        });
        amiodarona = (AppCompatCheckBox)findViewById(R.id.checkboxAmiodarona);
        amiodarona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.amiodaronaTextInputLayout);}
        });
        dopamina = (AppCompatCheckBox)findViewById(R.id.checkboxDopamina);
        dopamina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.dopaminaTextInputLayout); }
        });
        dobutamina = (AppCompatCheckBox)findViewById(R.id.checkboxDobutamina);
        dobutamina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.dobutaminaTextInputLayout);}
        });
        fentanil = (AppCompatCheckBox)findViewById(R.id.checkboxFentanil);
        fentanil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.fentanilTextInputLayout); }
        });
        hidrocortisona = (AppCompatCheckBox)findViewById(R.id.checkboxHidrocortisona);
        hidrocortisona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.hidrocortisonaTextInputLayout);}
        });
        insulina = (AppCompatCheckBox)findViewById(R.id.checkboxInsulina);
        insulina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.insulinaTextInputLayout);     }
        });
        ketamina = (AppCompatCheckBox)findViewById(R.id.checkboxKetamina);
        ketamina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.ketaminaTextInputLayout);}
        });
        midozalam = (AppCompatCheckBox)findViewById(R.id.checkboxMidazolam);
        midozalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.midozalamTextInputLayout);}
        });
        milrinona = (AppCompatCheckBox)findViewById(R.id.checkboxMilrinona);
        milrinona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.milrinonaTextInputLayout);}
        });
        nitroglicerina = (AppCompatCheckBox)findViewById(R.id.checkboxNitrogelicerina);
        nitroglicerina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.nitroglicerinaTextInputLayout);}
        });
        nitroprussiatoDeSodio = (AppCompatCheckBox)findViewById(R.id.checkboxNitroprussiatoDeSodio);
        nitroprussiatoDeSodio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.nitroprussiatoDeSodioTextInputLayout);}});
        noradrenalina = (AppCompatCheckBox)findViewById(R.id.checkboxNoradrenalina);
        noradrenalina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.noradrenalinaTextInputLayout);}
        });
        precedex = (AppCompatCheckBox)findViewById(R.id.checkboxPrecedex);
        precedex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.precedexTextInputLayout);}
        });
        propofol = (AppCompatCheckBox)findViewById(R.id.checkboxPropofol);
        propofol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTextInputEditText(R.id.propofolTextInputLayout);}
        });
        /**************************CHECKBOX**************************/

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), MonitorMultiparametricoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToLeft();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), DispositivoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToRight();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        setBombaInfusaoFromDatabase();
    }

    private void setBombaInfusaoFromDatabase(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("BombaInfusao")){
                    BombaInfusao bombaInfusao = new BombaInfusao();
                    bombaInfusao.initializeMap();
                    Map<String,Integer> map = new HashMap<>();
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.child("BombaInfusao").getChildren())
                        map.put(dataSnapshot1.getKey(),Integer.parseInt(dataSnapshot1.getValue().toString()));
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.drogas);
                    for (int i = 0; i < linearLayout.getChildCount(); i++) {
                        View v = linearLayout.getChildAt(i);
                        if(v instanceof LinearLayout){
                            for (int k = 0; k < ((LinearLayout) v).getChildCount(); k++) {
                                View view = ((LinearLayout) v).getChildAt(k);
                                if (view instanceof AppCompatCheckBox) {
                                    AppCompatCheckBox cb = (AppCompatCheckBox) view;
                                    String str  = cb.getText().toString();
                                    if (map.containsKey(str)){
                                        k++;
                                        cb.setChecked(true);
                                        view = ((LinearLayout)v).getChildAt(k);
                                        view.setVisibility(View.VISIBLE);
                                        ((TextInputLayout) view).getEditText().
                                                setText(Integer.toString(map.get(str)));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showTextInputEditText(int id){
        if(!findViewById(id).isShown())
            myAnimation.slideDownView(getApplicationContext(),findViewById(id));
        else if(findViewById(id).isShown())
            myAnimation.slideUpView(getApplicationContext(),findViewById(id));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        verificaCamposENotificaAdapter();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home) {
            verificaCamposENotificaAdapter();
            finish();
        }
        return true;
    }

    public void verificaCamposENotificaAdapter(){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.drogas);
        BombaInfusao bombaInfusao = new BombaInfusao();
        bombaInfusao.initializeMap();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View v = linearLayout.getChildAt(i);
            if(v instanceof LinearLayout){
                for (int k = 0; k < ((LinearLayout) v).getChildCount(); k++) {
                    View view = ((LinearLayout) v).getChildAt(k);
                    if (view instanceof AppCompatCheckBox) {
                        AppCompatCheckBox cb = (AppCompatCheckBox) view;
                        if (cb.isChecked()) {
                            k++;
                            view = ((LinearLayout)v).getChildAt(k);
                            if(!(((TextInputLayout) view).getEditText().getText().toString().trim().isEmpty()))
                                bombaInfusao.inserseDispositivo(cb.getText().toString(),Integer.parseInt(((TextInputLayout) view).getEditText().getText().toString()));
                        }
                    }
                }
            }
        }
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).updateChildren(bombaInfusao.toMap());
        changeCardColorToGreen();
    }
}
