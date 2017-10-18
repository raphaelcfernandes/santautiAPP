package santauti.app.Activities.Ficha.PartesMedicas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.Model.Ficha.Osteomuscular;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 09-Aug-17.
 */

public class OsteomuscularActivity extends GenericoActivity {
    private RadioGroup musculaturaTrofismoRadioGroup, musculaturaTonusRadioGroup;
    SharedPreferences sharedPreferences;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Osteomuscular osteomuscular;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osteomuscular);
        setToolbar(getString(R.string.OsteoMuscular));
        prepareNavigationButtons();
        sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);

        /*********************RADIOGROUP*****************/
        musculaturaTonusRadioGroup = (RadioGroup)findViewById(R.id.musculaturaTonusRadioGroup);
        musculaturaTrofismoRadioGroup = (RadioGroup)findViewById(R.id.musculaturaTrofismoRadioGroup);
        /*********************RADIOGROUP*****************/
        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), PelesMucosasActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToLeft();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setOsteomuscularFromDatabase();
    }

    @Override
    public void prepareNavigationButtons() {
        proxFicha = (Button)findViewById(R.id.fichaProxima);
        antFicha = (Button)findViewById(R.id.fichaAnterior);
        proxFicha.setVisibility(View.GONE);
        antFicha.setText("< " + FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0) - 1).getName());
    }

    private void setOsteomuscularFromDatabase(){
        db.collection("Hospital").document(sharedPreferences.getString("hospitalKey",""))
                .collection("Fichas").document(sharedPreferences.getString("fichaKey","")).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    osteomuscular = documentSnapshot.toObject(Osteomuscular.class);
                    if (osteomuscular.getTonusMuscular()!=null)
                        setRadioButtonFromIdAndDatabase(R.id.musculatura, osteomuscular.getTonusMuscular());
                    if (osteomuscular.getTrofismoMuscular()!=null)
                        setRadioButtonFromIdAndDatabase(R.id.musculatura, osteomuscular.getTrofismoMuscular());
                }

            }
        });

    }


    private void verificaCamposENotificaAdapter(){
        if(musculaturaTonusRadioGroup.getCheckedRadioButtonId()!=-1)
            osteomuscular.setTonusMuscular(getStringOfRadioButtonSelectedFromRadioGroup(musculaturaTonusRadioGroup));
        if(musculaturaTrofismoRadioGroup.getCheckedRadioButtonId()!=-1)
            osteomuscular.setTrofismoMuscular(getStringOfRadioButtonSelectedFromRadioGroup(musculaturaTrofismoRadioGroup));
        if(osteomuscular.getTonusMuscular()!=null || osteomuscular.getTrofismoMuscular()!=null){
            db.collection("Hospital").document(sharedPreferences.getString("hospitalKey",""))
                    .collection("Fichas").document(sharedPreferences.getString("fichaKey",""))
                    .update(osteomuscular.toMap()).addOnSuccessListener(OsteomuscularActivity.this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    System.out.println("Entrei aqui");
                    updateUI(osteomuscular);
                }
            });

        }
    }

    private void updateUI(Osteomuscular osteomuscular){
        System.out.println("dentroUI");
        if(osteomuscular.checkObject()) {
            System.out.println("if ui");
            changeCardColorToGreen();
        }
        else {
            System.out.println("else");
            setCardColorToDefault();
        }
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
}
