package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Respiratorio;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class RespiratorioActivity extends GenericoActivity {
    private View pressaoCuff_layout,murmurioVesicularItensLayout;
    private View roncosItensLayout,sibilosItensLayout,crepitacoesItensLayout;
    private View mascaraDeVenturi_layout, fluxoOxigenio_layout;
    private Ficha ficha;
    private MyAnimation myAnimation;
    private Intent intent;
    private RadioButton viasAereasNatural,viasAereasTubo,viasAereasTraqueostomia,murmurioVesicularReduzido,
            murmurioVesicularFisiologico,murmurioVesicularAumentado,emMascaraVenturi,sobCateterNasal,emMascaraReinalacao,usoOxigenioNao;
    private RadioButton r24,r28,r31,r35,r40,r50;
    private CheckBox checkboxRoncos,checkboxCrepitacoes,checkboxSibilos;
    private RadioGroup usoOxigenioRadioGroup1,usoOxigenioRadioGroup2,mascaraVenturiRadioGroup1,mascaraVenturiRadioGroup2;
    private TextInputEditText pressaoCuff,fluxoOxigenio;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respiratorio);
        findViewById(R.id.respiratorio_activity).requestFocus();
        setToolbar(getString(R.string.Respiratorio));
        setupUI(findViewById(R.id.respiratorio_activity));
        /**************************VIEWS**************************/
        pressaoCuff_layout = findViewById(R.id.pressaoCuff_layout);
        mascaraDeVenturi_layout = findViewById(R.id.mascaVenturi);
        fluxoOxigenio_layout = findViewById(R.id.fluxoOxigenioLayout);
        murmurioVesicularItensLayout = findViewById(R.id.murmurioVesicularItens);
        roncosItensLayout = findViewById(R.id.roncosItensLayout);
        sibilosItensLayout = findViewById(R.id.sibilosItensLayout);
        crepitacoesItensLayout = findViewById(R.id.crepitacoesItensLayout);
        /**************************VIEWS**************************/

        /*************************RADIOGROUP**********************/
        usoOxigenioRadioGroup1 = (RadioGroup)findViewById(R.id.usoOxigenioRadioGroup1);
        usoOxigenioRadioGroup2 = (RadioGroup)findViewById(R.id.usoOxigenioRadioGroup2);
        mascaraVenturiRadioGroup1 = (RadioGroup)findViewById(R.id.mascaraVenturiRadioGroup1);
        mascaraVenturiRadioGroup2 = (RadioGroup)findViewById(R.id.mascaraVenturiRadioGroup2);
        /*************************RADIOGROUP**********************/

        /**************************RADIOBUTTON*********************/
        viasAereasNatural = (RadioButton)findViewById(R.id.viasAereasNatural);
        viasAereasNatural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pressaoCuff_layout.isShown())
                    myAnimation.slideUpView(getApplicationContext(),pressaoCuff_layout);
            }
        });
        viasAereasTubo = (RadioButton)findViewById(R.id.viasAereasTubo);
        viasAereasTubo.setOnClickListener(showPressaoCuffELocalizacaoCanula);
        viasAereasTraqueostomia = (RadioButton)findViewById(R.id.viasAereasTraqueostomia);
        viasAereasTraqueostomia.setOnClickListener(showPressaoCuffELocalizacaoCanula);
        murmurioVesicularReduzido = (RadioButton)findViewById(R.id.murmurioVesicularReduzido);
        murmurioVesicularReduzido.setOnClickListener(showMurmurioVesicular);
        murmurioVesicularFisiologico = (RadioButton)findViewById(R.id.murmurioVesicularFisiologico);
        murmurioVesicularFisiologico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(murmurioVesicularItensLayout.isShown())
                    myAnimation.slideUpView(getApplicationContext(),murmurioVesicularItensLayout);
            }
        });
        murmurioVesicularAumentado = (RadioButton)findViewById(R.id.murmurioVesicularAumentado);
        murmurioVesicularAumentado.setOnClickListener(showMurmurioVesicular);
        emMascaraVenturi = (RadioButton)findViewById(R.id.emMascaraVenturi);
        emMascaraVenturi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fluxoOxigenio_layout.isShown())
                    fluxoOxigenio_layout.setVisibility(View.GONE);
                if(!mascaraDeVenturi_layout.isShown())
                    myAnimation.slideDownView(getApplicationContext(),mascaraDeVenturi_layout);
                if(usoOxigenioRadioGroup2.getCheckedRadioButtonId()!=-1)
                    usoOxigenioRadioGroup2.clearCheck();
            }
        });
        emMascaraReinalacao = (RadioButton)findViewById(R.id.emMascaraReinalacao);
        emMascaraReinalacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mascaraDeVenturi_layout.isShown())
                    mascaraDeVenturi_layout.setVisibility(View.GONE);
                if(!fluxoOxigenio_layout.isShown())
                    myAnimation.slideDownView(getApplicationContext(),fluxoOxigenio_layout);
                if(usoOxigenioRadioGroup1.getCheckedRadioButtonId()!=-1)
                    usoOxigenioRadioGroup1.clearCheck();
            }
        });
        sobCateterNasal = (RadioButton)findViewById(R.id.sobCateterNasal);
        sobCateterNasal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mascaraDeVenturi_layout.isShown())
                    mascaraDeVenturi_layout.setVisibility(View.GONE);
                if(!fluxoOxigenio_layout.isShown())
                    myAnimation.slideDownView(getApplicationContext(),fluxoOxigenio_layout);
                if(usoOxigenioRadioGroup2.getCheckedRadioButtonId()!=-1)
                    usoOxigenioRadioGroup2.clearCheck();
            }
        });
        usoOxigenioNao = (RadioButton)findViewById(R.id.usoOxigenioNao);
        usoOxigenioNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mascaraDeVenturi_layout.isShown())
                    myAnimation.slideUpView(getApplicationContext(),mascaraDeVenturi_layout);
                if(fluxoOxigenio_layout.isShown())
                    myAnimation.slideUpView(getApplicationContext(),fluxoOxigenio_layout);
                if(usoOxigenioRadioGroup1.getCheckedRadioButtonId()!=-1)
                    usoOxigenioRadioGroup1.clearCheck();
            }
        });
        r24 = (RadioButton)findViewById(R.id.r24);
        r24.setOnClickListener(clearCheckMascaraVenturiRadioGroup2);
        r28 = (RadioButton)findViewById(R.id.r28);
        r28.setOnClickListener(clearCheckMascaraVenturiRadioGroup2);
        r31 = (RadioButton)findViewById(R.id.r31);
        r31.setOnClickListener(clearCheckMascaraVenturiRadioGroup2);
        r35 = (RadioButton)findViewById(R.id.r35);
        r35.setOnClickListener(clearCheckMascaraVenturiRadioGroup1);
        r40 = (RadioButton)findViewById(R.id.r40);
        r40.setOnClickListener(clearCheckMascaraVenturiRadioGroup1);
        r50 = (RadioButton)findViewById(R.id.r50);
        r50.setOnClickListener(clearCheckMascaraVenturiRadioGroup1);
        /**************************RADIOBUTTON*********************/

        /**************************TEXTINPUTEDITTEXT***************/
        pressaoCuff = (TextInputEditText)findViewById(R.id.pressaoCuff);
        fluxoOxigenio = (TextInputEditText)findViewById(R.id.fluxoOxigenio);
        /**************************TEXTINPUTEDITTEXT***************/
        /**************************CHECKBOX************************/
        checkboxSibilos = (CheckBox)findViewById(R.id.checkboxSibilos);
        checkboxSibilos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkboxSibilos.isChecked() && sibilosItensLayout.isShown())
                    myAnimation.slideUpView(getApplicationContext(),sibilosItensLayout);
                if(checkboxSibilos.isChecked() && !sibilosItensLayout.isShown())
                    myAnimation.slideDownView(getApplicationContext(), sibilosItensLayout);
            }
        });
        checkboxCrepitacoes = (CheckBox)findViewById(R.id.checkboxCrepitacoes);
        checkboxCrepitacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkboxCrepitacoes.isChecked() && crepitacoesItensLayout.isShown())
                    myAnimation.slideUpView(getApplicationContext(),crepitacoesItensLayout);
                if(checkboxCrepitacoes.isChecked() && !crepitacoesItensLayout.isShown())
                    myAnimation.slideDownView(getApplicationContext(), crepitacoesItensLayout);
            }
        });
        checkboxRoncos = (CheckBox)findViewById(R.id.checkboxRoncos);
        checkboxRoncos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkboxRoncos.isChecked() && roncosItensLayout.isShown())
                    myAnimation.slideUpView(getApplicationContext(), roncosItensLayout);
                if(checkboxRoncos.isChecked() && !roncosItensLayout.isShown())
                    myAnimation.slideDownView(getApplicationContext(), roncosItensLayout);
            }
        });
        /**************************CHECKBOX************************/

        prepareNavigationButtons();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HemodinamicoActivity.class);
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
                intent = new Intent(view.getContext(), GastrointestinalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToRight();
            }
        });


        myAnimation = new MyAnimation();
        setRespiratorioFromDataBase();
    }

    private View.OnClickListener clearCheckMascaraVenturiRadioGroup2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mascaraVenturiRadioGroup2.getCheckedRadioButtonId()!=-1)
                mascaraVenturiRadioGroup2.clearCheck();
        }
    };

    private View.OnClickListener clearCheckMascaraVenturiRadioGroup1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mascaraVenturiRadioGroup1.getCheckedRadioButtonId()!=-1)
                mascaraVenturiRadioGroup1.clearCheck();
        }
    };
    private View.OnClickListener showPressaoCuffELocalizacaoCanula = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!pressaoCuff_layout.isShown())
                myAnimation.slideDownView(getApplicationContext(),pressaoCuff_layout);
        }
    };

    private View.OnClickListener showMurmurioVesicular = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!murmurioVesicularItensLayout.isShown())
                myAnimation.slideDownView(getApplicationContext(),murmurioVesicularItensLayout);
        }
    };

    private void setRespiratorioFromDataBase(){
//        Ficha ficha = getProperFicha();
//        if(ficha.getRespiratorio()!=null){
//            Respiratorio respiratorio = ficha.getRespiratorio();
//            if(respiratorio.getViasAereas()!=null) {
//                setRadioButtonFromIdAndDatabase(R.id.viasAereas, respiratorio.getViasAereas());
//                if(!respiratorio.getViasAereas().equals(getString(R.string.Natural))){
//                    myAnimation.slideDownView(getApplicationContext(),pressaoCuff_layout);
//                    if(respiratorio.getPressaoCuff()>0)
//                        pressaoCuff.setText(Integer.toString(respiratorio.getPressaoCuff()));
//                    if(respiratorio.getLocalizacaoCanula()!=null)
//                        setRadioButtonFromIdAndDatabase(R.id.pressaoCuff_layout,respiratorio.getLocalizacaoCanula());
//
//                }
//            }
//            if(respiratorio.getMurmurioVesicular()!=null){
//                setRadioButtonFromIdAndDatabase(R.id.murmurioVesicular,respiratorio.getMurmurioVesicular());
//                if(!respiratorio.getMurmurioVesicular().equals(getString(R.string.Fisiologico))){
//                    myAnimation.slideDownView(getApplicationContext(),murmurioVesicularItensLayout);
//                    preencheCheckboxes(R.id.murmursioVesicularItens,respiratorio.getLocalizacaoMurmurioVesicular());
//                }
//            }
//            if(respiratorio.getUsoOxigenio()!=null ){
//                setRadioButtonFromIdAndDatabase(R.id.usoOxigenio,respiratorio.getUsoOxigenio());
//                if(!respiratorio.getUsoOxigenio().equals(getString(R.string.Nao))){
//                    if(respiratorio.getUsoOxigenio().equals(getString(R.string.EmMascaraVenturi))){
//                        myAnimation.slideDownView(getApplicationContext(),mascaraDeVenturi_layout);
//                        if(respiratorio.getMascaraVenturi()==24)
//                            r24.setChecked(true);
//                        else if(respiratorio.getMascaraVenturi()==28)
//                            r28.setChecked(true);
//                        else if(respiratorio.getMascaraVenturi()==31)
//                            r31.setChecked(true);
//                        else if(respiratorio.getMascaraVenturi()==35)
//                            r35.setChecked(true);
//                        else if(respiratorio.getMascaraVenturi()==40)
//                            r40.setChecked(true);
//                        else if(respiratorio.getMascaraVenturi()==50)
//                            r50.setChecked(true);
//                    }
//                    else{
//                        myAnimation.slideDownView(getApplicationContext(),fluxoOxigenio_layout);
//                        if(respiratorio.getFluxo()>0)
//                            fluxoOxigenio.setText(Integer.toString(respiratorio.getFluxo()));
//                    }
//                }
//            }
//            if(!respiratorio.getRoncos().isEmpty())
//                abreLayoutMarcaCheckboxEPreenche(checkboxRoncos,roncosItensLayout,R.id.roncosItensLayout,respiratorio.getRoncos());
//            if(!respiratorio.getSibilos().isEmpty())
//                abreLayoutMarcaCheckboxEPreenche(checkboxSibilos,sibilosItensLayout,R.id.sibilosItensLayout,respiratorio.getSibilos());
//            if(!respiratorio.getCrepitacoes().isEmpty())
//                abreLayoutMarcaCheckboxEPreenche(checkboxCrepitacoes,crepitacoesItensLayout,R.id.crepitacoesItensLayout,respiratorio.getCrepitacoes());
//        }
    }

    public void roncosOnClick(View view){
        if(roncosItensLayout.isShown())
            myAnimation.slideUpView(getApplicationContext(),roncosItensLayout);
        else
        if(checkboxRoncos.isChecked())
            myAnimation.slideDownView(getApplicationContext(),roncosItensLayout);
    }

    public void sibilosOnClick(View view){
        if(sibilosItensLayout.isShown())
            myAnimation.slideUpView(getApplicationContext(),sibilosItensLayout);
        else
        if(checkboxSibilos.isChecked())
            myAnimation.slideDownView(getApplicationContext(),sibilosItensLayout);
    }

    public void crepitacoesOnClick(View view){
        if(crepitacoesItensLayout.isShown())
            myAnimation.slideUpView(getApplicationContext(),crepitacoesItensLayout);
        else
        if(checkboxCrepitacoes.isChecked())
            myAnimation.slideDownView(getApplicationContext(),crepitacoesItensLayout);
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
        final Respiratorio respiratorio = new Respiratorio();
        RadioGroup viasAereasRadioGroup = (RadioGroup)findViewById(R.id.viasAereasRadioGroup);
        RadioGroup localizacaoCanula = (RadioGroup)findViewById(R.id.localizacaoCanula);
        RadioGroup murmurioVesicular = (RadioGroup)findViewById(R.id.murmurioVesicularRadioGroup);
        TextInputEditText pressaoCuff = (TextInputEditText)findViewById(R.id.pressaoCuff);
        TextInputEditText fluxoOxigenio = (TextInputEditText)findViewById(R.id.fluxoOxigenio);
        if(viasAereasRadioGroup.getCheckedRadioButtonId()!=-1){
            if(getStringOfRadioButtonSelectedFromRadioGroup(viasAereasRadioGroup).equals(getString(R.string.Natural)))
                respiratorio.setViasAereas(getString(R.string.Natural));
            else {
                respiratorio.setViasAereas(getStringOfRadioButtonSelectedFromRadioGroup(viasAereasRadioGroup));
                if(!isTextInputEditTextEmpty(pressaoCuff))
                    respiratorio.setPressaoCuff(Integer.parseInt(pressaoCuff.getText().toString()));
                if(localizacaoCanula.getCheckedRadioButtonId()!=-1)
                    respiratorio.setLocalizacaoCanula(getStringOfRadioButtonSelectedFromRadioGroup(localizacaoCanula));
            }
        }
        if(murmurioVesicular.getCheckedRadioButtonId()!=-1){
            if(getStringOfRadioButtonSelectedFromRadioGroup(murmurioVesicular).equals(getString(R.string.Fisiologico)))
                respiratorio.setMurmurioVesicular(getString(R.string.Fisiologico));
            else{
                respiratorio.setMurmurioVesicular(getStringOfRadioButtonSelectedFromRadioGroup(murmurioVesicular));
                respiratorio.setLocalizacoesMurmurioVesicular(getCheckBoxesPreenchidos(R.id.murmurioVesicularItens));
            }
        }
        if(usoOxigenioRadioGroup1.getCheckedRadioButtonId()!=-1){
            respiratorio.setUsoOxigenio(getStringOfRadioButtonSelectedFromRadioGroup(usoOxigenioRadioGroup1));
            if(getStringOfRadioButtonSelectedFromRadioGroup(usoOxigenioRadioGroup1).equals(getString(R.string.EmMascaraVenturi))){
                if(mascaraVenturiRadioGroup1.getCheckedRadioButtonId()!=-1){
                    if(getStringOfRadioButtonSelectedFromRadioGroup(mascaraVenturiRadioGroup1).equals(getString(R.string.Percent24)))
                        respiratorio.setMascaraVenturi(24);
                    if(getStringOfRadioButtonSelectedFromRadioGroup(mascaraVenturiRadioGroup1).equals(getString(R.string.Percent28)))
                        respiratorio.setMascaraVenturi(28);
                    if(getStringOfRadioButtonSelectedFromRadioGroup(mascaraVenturiRadioGroup1).equals(getString(R.string.Percent31)))
                        respiratorio.setMascaraVenturi(31);
                }
                else if(mascaraVenturiRadioGroup2.getCheckedRadioButtonId()!=-1){
                    if(getStringOfRadioButtonSelectedFromRadioGroup(mascaraVenturiRadioGroup2).equals(getString(R.string.Percent35)))
                        respiratorio.setMascaraVenturi(35);
                    if(getStringOfRadioButtonSelectedFromRadioGroup(mascaraVenturiRadioGroup2).equals(getString(R.string.Percent40)))
                        respiratorio.setMascaraVenturi(40);
                    if(getStringOfRadioButtonSelectedFromRadioGroup(mascaraVenturiRadioGroup2).equals(getString(R.string.Percent50)))
                        respiratorio.setMascaraVenturi(50);
                }
            }
            else{
                respiratorio.setUsoOxigenio(getStringOfRadioButtonSelectedFromRadioGroup(usoOxigenioRadioGroup1));
                if(!isTextInputEditTextEmpty(fluxoOxigenio))
                    respiratorio.setUsoOxigenioFluxo(Integer.parseInt(fluxoOxigenio.getText().toString()));
            }
        }
        else if(usoOxigenioRadioGroup2.getCheckedRadioButtonId()!=-1){
            respiratorio.setUsoOxigenio(getStringOfRadioButtonSelectedFromRadioGroup(usoOxigenioRadioGroup2));
            if(getStringOfRadioButtonSelectedFromRadioGroup(usoOxigenioRadioGroup2).equals(getString(R.string.EmMascaraReinalacao)) &&
                    !isTextInputEditTextEmpty(fluxoOxigenio))
                respiratorio.setUsoOxigenioFluxo(Integer.parseInt(fluxoOxigenio.getText().toString()));
        }
        if(checkboxRoncos.isChecked()){
            List<String> stringRoncos = getCheckBoxesPreenchidos(R.id.roncosItensLayout);
            respiratorio.setRoncos(stringRoncos);
        }
        if(checkboxSibilos.isChecked()){
            List<String> sibilos = getCheckBoxesPreenchidos(R.id.sibilosItensLayout);
            respiratorio.setSibilos(sibilos);
        }
        if(checkboxCrepitacoes.isChecked()){
            List<String> crepitacoes = getCheckBoxesPreenchidos(R.id.crepitacoesItensLayout);
            respiratorio.setCrepitacoes(crepitacoes);
        }

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        FireBaseUtils.getDatabaseReference().child("Hospital").child(sharedPreferences.getString("hospitalKey", ""))
                .child("Fichas").child(sharedPreferences.getString("fichaKey", "")).updateChildren(respiratorio.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(respiratorio.checkObject())
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
