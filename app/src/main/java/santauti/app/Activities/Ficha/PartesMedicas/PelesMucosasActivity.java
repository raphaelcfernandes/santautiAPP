package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.Model.Ficha.PelesMucosas;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 09-Aug-17.
 */

public class PelesMucosasActivity extends GenericoActivity{
    private AppCompatCheckBox checkBoxIctericia,checkBoxUlceraPressao;
    private RadioGroup peleRadioGroup,ictericiaItensRadioGroup,mucosasColoracao,mucosasColoracao2,mucosasUmidade;
    private View ictericiaItensLayout,ulceraPressaoItensLayout;
    private MyAnimation myAnimation = new MyAnimation();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peles_mucosas);
        setToolbar(getString(R.string.PelesMucosas));
        prepareNavigationButtons();

        ictericiaItensLayout = findViewById(R.id.ictericiaItensLayout);
        ulceraPressaoItensLayout = findViewById(R.id.ulceraPressaoItensLayout);
        /**************************RADIOGROUP********************/
        peleRadioGroup = (RadioGroup)findViewById(R.id.peleRadioGroup);
        ictericiaItensRadioGroup = (RadioGroup)findViewById(R.id.ictericiaItensRadioGroup);
        mucosasColoracao = (RadioGroup)findViewById(R.id.mucosasColoracao);
        mucosasColoracao2 = (RadioGroup)findViewById(R.id.mucosasColoracao2);
        mucosasUmidade = (RadioGroup)findViewById(R.id.mucosasUmidade);
        /**************************RADIOGROUP********************/

        /**********************CHECKBOX***************************/
        checkBoxUlceraPressao = (AppCompatCheckBox)findViewById(R.id.checkBoxUlceraPressao);
        checkBoxUlceraPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBoxUlceraPressao.isChecked() && ulceraPressaoItensLayout.isShown())
                    myAnimation.slideUpView(getApplicationContext(), ulceraPressaoItensLayout);
                if(checkBoxUlceraPressao.isChecked() && !ulceraPressaoItensLayout.isShown())
                    myAnimation.slideDownView(getApplicationContext(), ulceraPressaoItensLayout);
            }
        });
        checkBoxIctericia = (AppCompatCheckBox) findViewById(R.id.checkBoxIctericia);
        checkBoxIctericia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBoxIctericia.isChecked() && ictericiaItensLayout.isShown())
                    myAnimation.slideUpView(getApplicationContext(), ictericiaItensLayout);
                if(checkBoxIctericia.isChecked() && !ictericiaItensLayout.isShown())
                    myAnimation.slideDownView(getApplicationContext(), ictericiaItensLayout);
            }
        });
        /**********************CHECKBOX***************************/


        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), EndocrinoActivity.class);
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
                intent = new Intent(view.getContext(), OsteomuscularActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToRight();
            }
        });
        setPelesMucosasFromDatabase();
    }

    private void setPelesMucosasFromDatabase(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey",""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey","")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("PelesMucosas")){
                    PelesMucosas pelesMucosas = dataSnapshot.child("PelesMucosas").getValue(PelesMucosas.class);
                    System.out.println(pelesMucosas);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //        Ficha ficha = getProperFicha();
//        if(ficha.getPelesMucosas()!=null){
//            PelesMucosas pelesMucosas = ficha.getPelesMucosas();
//            if(pelesMucosas.getPele()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.peleLayout,pelesMucosas.getPele());
//            if(!pelesMucosas.getUlceraPressao().isEmpty() || pelesMucosas.isUlceraPressaoChecked()) {
//                checkBoxUlceraPressao.setChecked(true);
//                myAnimation.slideDownView(getApplicationContext(),ulceraPressaoItensLayout);
//                abreLayoutMarcaCheckboxEPreenche(checkBoxUlceraPressao,ulceraPressaoItensLayout,R.id.ulceraPressaoItensLayout,pelesMucosas.getUlceraPressao());
//            }
//            if(pelesMucosas.getMucosasColoracao()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.mucosas,pelesMucosas.getMucosasColoracao());
//            if(pelesMucosas.getMucosasColoracao2()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.mucosas,pelesMucosas.getMucosasColoracao2());
//            if(pelesMucosas.getMucosasUmidade()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.mucosas,pelesMucosas.getMucosasUmidade());
//            if(pelesMucosas.getIctericia()>0){
//                checkBoxIctericia.setChecked(true);
//                myAnimation.slideDownView(getApplicationContext(),ictericiaItensLayout);
//                StringBuilder sb = new StringBuilder(Integer.toString(pelesMucosas.getIctericia()));
//                sb.insert(0,'+');
//                setRadioButtonFromIdAndDatabase(R.id.ictericiaItensLayout,sb.toString());
//            }
//        }
    }

    public void ulceraPressaoOnClick(View view){
        if(ulceraPressaoItensLayout.isShown())
            myAnimation.slideUpView(getApplicationContext(),ulceraPressaoItensLayout);
        else
        if(checkBoxUlceraPressao.isChecked())
            myAnimation.slideDownView(getApplicationContext(),ulceraPressaoItensLayout);
    }

    public void ictericiaOnClick(View vew){
        if(ictericiaItensLayout.isShown())
            myAnimation.slideUpView(getApplicationContext(),ictericiaItensLayout);
        else
        if(checkBoxIctericia.isChecked())
            myAnimation.slideDownView(getApplicationContext(),ictericiaItensLayout);
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

    private void verificaCamposENotificaAdapter() {
        final PelesMucosas pelesMucosas = new PelesMucosas();
        pelesMucosas.initializeLists();

        if(peleRadioGroup.getCheckedRadioButtonId()!=-1)
            pelesMucosas.setPele(getStringOfRadioButtonSelectedFromRadioGroup(peleRadioGroup));
        if(checkBoxUlceraPressao.isChecked()){
            pelesMucosas.setUlceraPressao(getCheckBoxesPreenchidos(R.id.ulceraPressaoItensLayout));
            pelesMucosas.setUlceraP(true);
        }
        if(mucosasColoracao.getCheckedRadioButtonId()!=-1)
            pelesMucosas.addtMucosas(getStringOfRadioButtonSelectedFromRadioGroup(mucosasColoracao));
        if(mucosasColoracao2.getCheckedRadioButtonId()!=-1)
            pelesMucosas.addtMucosas(getStringOfRadioButtonSelectedFromRadioGroup(mucosasColoracao2));
        if(mucosasUmidade.getCheckedRadioButtonId()!=-1)
            pelesMucosas.addtMucosas(getStringOfRadioButtonSelectedFromRadioGroup(mucosasUmidade));
        if(checkBoxIctericia.isChecked()) {
            pelesMucosas.setIctericiaFlag(true);
            if (ictericiaItensRadioGroup.getCheckedRadioButtonId() != -1)
                pelesMucosas.setIctericia(Integer.parseInt(getStringOfRadioButtonSelectedFromRadioGroup(ictericiaItensRadioGroup)));
        }
        else if(!checkBoxIctericia.isChecked()) {
            pelesMucosas.setIctericia(0);
            pelesMucosas.setIctericiaFlag(false);
        }

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).updateChildren(pelesMucosas.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(pelesMucosas.checkObject())
                    changeCardColorToGreen();
                else
                    setCardColorToDefault();
            }
        });
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

}
