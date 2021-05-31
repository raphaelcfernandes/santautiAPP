package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Renal;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class RenalActivity extends GenericoActivity {
    private TextInputEditText volumeDialise, peso, tempoDialise;
    private View dialiseItensLayout, emDialise;
    private CheckBox checkboxEmDialise,ufCheckBox;
    private RadioGroup urinaRadioGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renal);
        findViewById(R.id.activity_renal).requestFocus();
        setToolbar(getString(R.string.Renal));
        setupUI(findViewById(R.id.activity_renal));
        prepareNavigationButtons();

        /**************************TEXTINPUTEDITTEXT*******************/
        peso = (TextInputEditText)findViewById(R.id.peso);
        volumeDialise = (TextInputEditText)findViewById(R.id.volumeDialise);
        tempoDialise = (TextInputEditText)findViewById(R.id.tempoDialise);
        /**************************TEXTINPUTEDITTEXT*******************/

        /**************************CHECKBOX*****************************/
        ufCheckBox = (CheckBox)findViewById(R.id.ufCheckbox);
        checkboxEmDialise = (CheckBox)findViewById(R.id.checkboxEmDialise);
        checkboxEmDialise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkboxEmDialise.isChecked())
                    dialiseItensLayout.setVisibility(View.GONE);
                else
                    dialiseItensLayout.setVisibility(View.VISIBLE);
            }
        });
        /**************************CHECKBOX*****************************/

        /**************************VIEW*********************************/
        dialiseItensLayout = findViewById(R.id.dialiseItensLayout);
        emDialise = findViewById(R.id.emDialise);
        emDialise.setOnClickListener(v -> {
            if(checkboxEmDialise.isChecked()) {
                if(dialiseItensLayout.isShown())
                    dialiseItensLayout.setVisibility(View.GONE);
                else
                    dialiseItensLayout.setVisibility(View.VISIBLE);
            }
        });
        /**************************VIEW*********************************/

        /**************************RADIOGROUP***************************/
        urinaRadioGroup = (RadioGroup)findViewById(R.id.urinaRadioGroup);
        /**************************RADIOGROUP***************************/

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), GastrointestinalActivity.class);
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
                intent = new Intent(view.getContext(), MetabolicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToRight();
            }
        });

        setRenalFromDatabase();
    }


    private void setRenalFromDatabase(){
//        Ficha ficha = getProperFicha();
//        if(ficha.getRenal()!=null){
//            Renal renal = ficha.getRenal();
//            if(renal.getPeso()>0)
//                peso.setText(Integer.toString(renal.getPeso()));
//            if(renal.getUrina()!=null){
//                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.urinaRadioGroup);
//                for(int i=0;i<radioGroup.getChildCount();i++) {
//                    View v = radioGroup.getChildAt(i);
//                    AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton)v;
//                    if(appCompatRadioButton.getText().toString().equals(renal.getUrina())) {
//                        appCompatRadioButton.setChecked(true);
//                        break;
//                    }
//                }
//            }
//            if(renal.isDialise()){
//                checkboxEmDialise.setChecked(true);
//                dialiseItensLayout.setVisibility(View.VISIBLE);
//                if(renal.isUF())
//                    ufCheckBox.setChecked(true);
//                if(renal.getVolume()>0)
//                    volumeDialise.setText(Integer.toString(renal.getVolume()));
//                if(renal.getTempo()>0)
//                    tempoDialise.setText(Integer.toString(renal.getTempo()));
//            }
//        }
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

    private void verificaCamposENotificaAdapter() {
        final Renal renal = new Renal();
        if(!isTextInputEditTextEmpty(peso))
            renal.setPeso(Integer.parseInt(peso.getText().toString()));
        if(urinaRadioGroup.getCheckedRadioButtonId()!=-1)
            renal.setUrina(getStringOfRadioButtonSelectedFromRadioGroup(urinaRadioGroup));
        if(checkboxEmDialise.isChecked()){
            renal.setDialise(true);
            if(ufCheckBox.isChecked())
                renal.setUF(true);
            if(!isTextInputEditTextEmpty(volumeDialise))
                renal.setVolume(Integer.parseInt(volumeDialise.getText().toString()));
            if(!isTextInputEditTextEmpty(tempoDialise))
                renal.setTempo(Integer.parseInt(tempoDialise.getText().toString()));
        }
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).updateChildren(renal.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(renal.checkObject())
                    changeCardColorToGreen();
                else
                    setCardColorToDefault();
            }
        });
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
