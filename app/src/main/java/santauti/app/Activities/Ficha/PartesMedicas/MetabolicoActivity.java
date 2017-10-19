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
import com.google.firebase.firestore.FirebaseFirestore;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class MetabolicoActivity extends GenericoActivity {
    private int i=0;
    private RadioGroup hidratacaoRadioGroup1,hidratacaoRadioGroup2;
    private RadioButton normoHidratado, edemaciado, desidratado;
    SharedPreferences sharedPreferences;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolico);
        findViewById(R.id.activity_metabolico).requestFocus();
        setToolbar(this.getString(R.string.Metabolico));
        sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        /********************RADIOGROUP****************************/
        hidratacaoRadioGroup1 = (RadioGroup)findViewById(R.id.hidratacaoRadioGroup1);
        hidratacaoRadioGroup2 = (RadioGroup)findViewById(R.id.hidratacaoRadioGroup2);
        /********************RADIOGROUP****************************/

        /*******************RADIOBUTTON****************************/
        normoHidratado = (RadioButton)findViewById(R.id.normoHidratado);
        normoHidratado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {hidratacaoRadioGroup2.clearCheck();
            }
        });
        edemaciado = (RadioButton)findViewById(R.id.edemaciado);
        edemaciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {hidratacaoRadioGroup2.clearCheck();
            }
        });
        desidratado = (RadioButton)findViewById(R.id.desidratado);
        desidratado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {hidratacaoRadioGroup1.clearCheck();
            }
        });
        /*******************RADIOBUTTON****************************/



        prepareNavigationButtons();


        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RenalActivity.class);
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
                intent = new Intent(view.getContext(), InfecciosoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
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
        setMetabolicoFromDataBase();
    }

    private void setMetabolicoFromDataBase(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey",""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey","")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Metabolico")){
                    Metabolico metabolico = dataSnapshot.child("Metabolico").getValue(Metabolico.class);
                    if(metabolico.getHidratacao()!=null)
                        setRadioButtonFromIdAndDatabase(R.id.hidratacao,metabolico.getHidratacao());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*FireSTORE*/
        //        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.child("Fichas").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                    setRadioButtonFromIdAndDatabase(R.id.hidratacao,dataSnapshot.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
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

    /**
     * Verifica se algum radio button foi selecionado
     *
     */
    private void verificaCamposENotificaAdapter(){
        String hidratacaoStr = null;
        if(hidratacaoRadioGroup1.getCheckedRadioButtonId()!=-1)
            hidratacaoStr = getStringOfRadioButtonSelectedFromRadioGroup(hidratacaoRadioGroup1);
        else if(hidratacaoRadioGroup2.getCheckedRadioButtonId()!=-1)
            hidratacaoStr = getStringOfRadioButtonSelectedFromRadioGroup(hidratacaoRadioGroup2);
        if(hidratacaoStr!=null) {
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
            Metabolico metabolico = new Metabolico(hidratacaoStr);
            FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                    .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).updateChildren(metabolico.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    changeCardColorToGreen();
                }
            });
        }
        /*FIRESTORE*/
        //        String hidratacaoStr = null;
//        if(hidratacaoRadioGroup1.getCheckedRadioButtonId()!=-1)
//            hidratacaoStr = getStringOfRadioButtonSelectedFromRadioGroup(hidratacaoRadioGroup1);
//        else if(hidratacaoRadioGroup2.getCheckedRadioButtonId()!=-1)
//            hidratacaoStr = getStringOfRadioButtonSelectedFromRadioGroup(hidratacaoRadioGroup2);
//        if(hidratacaoStr!=null){
//            Metabolico metabolico = new Metabolico(hidratacaoStr);
//            db.collection("Hospital").document(sharedPreferences.getString("hospitalKey",""))
//                    .collection("Fichas").document(sharedPreferences.getString("fichaKey",""))
//                    .update(metabolico.toMap()).addOnSuccessListener(MetabolicoActivity.this, new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    changeCardColorToGreen();
//                }
//            });

    }
}
