package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Endocrino;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class EndocrinoActivity extends GenericoActivity {
    private RadioButton normoGlicemia,eventosHipoglicemia,eventosHiperglicemia;
    private RadioGroup curvaGlicemicaRadioGroup1,curvaGlicemicaRadioGroup2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endocrino);
        setToolbar(getString(R.string.Endocrino));

        prepareNavigationButtons();


        /***********************RADIOGROUP**********************************/
        curvaGlicemicaRadioGroup1 = (RadioGroup)findViewById(R.id.curvaGlicemicaRadioGroup1);
        curvaGlicemicaRadioGroup2 = (RadioGroup)findViewById(R.id.curvaGlicemicaRadioGroup2);
        /***********************RADIOGROUP**********************************/

        /***********************RADIOBUTTON********************************/
        normoGlicemia = (RadioButton)findViewById(R.id.normoGlicemia);
        normoGlicemia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {curvaGlicemicaRadioGroup2.clearCheck();
            }
        });
        eventosHipoglicemia = (RadioButton)findViewById(R.id.eventosHipoglicemia);
        eventosHipoglicemia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {curvaGlicemicaRadioGroup1.clearCheck();
            }
        });
        eventosHiperglicemia = (RadioButton)findViewById(R.id.eventosHiperglicemia);
        eventosHiperglicemia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {curvaGlicemicaRadioGroup2.clearCheck();
            }
        });
        /***********************RADIOBUTTON********************************/

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HematologicoActivity.class);
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
                intent = new Intent(view.getContext(), PelesMucosasActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToRight();
            }
        });

        setEndocrinoFromDatabase();
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
        String curvaGlicemica = null;
        if(curvaGlicemicaRadioGroup1.getCheckedRadioButtonId()!=-1)
            curvaGlicemica = getStringOfRadioButtonSelectedFromRadioGroup(curvaGlicemicaRadioGroup1);
        else if(curvaGlicemicaRadioGroup2.getCheckedRadioButtonId()!=-1)
            curvaGlicemica = getStringOfRadioButtonSelectedFromRadioGroup(curvaGlicemicaRadioGroup2);
        if(curvaGlicemica!=null) {
            Endocrino endocrino = new Endocrino(curvaGlicemica);
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
            FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                    .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).updateChildren(endocrino.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    changeCardColorToGreen();
                }
            });
        }
    }

    private void setEndocrinoFromDatabase(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Endocrino")){
                    Endocrino endocrino = dataSnapshot.child("Endocrino").getValue(Endocrino.class);
                    if(endocrino.getCurvaGlicemica()!=null)
                        setRadioButtonFromIdAndDatabase(R.id.curvaGlicemica,endocrino.getCurvaGlicemica());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}