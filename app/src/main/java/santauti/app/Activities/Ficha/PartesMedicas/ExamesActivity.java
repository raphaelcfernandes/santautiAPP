package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import io.realm.Realm;
import io.realm.RealmList;
import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Exames;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Infeccioso;
import santauti.app.Model.Ficha.RealmObjects.RealmString;
import santauti.app.R;

/**
 * Created by raphael on 8/6/17.
 */

public class ExamesActivity extends GenericoActivity {
    private View eletrolitoItens,gasometriaArterialMetabolicaLinearLayout;
    private MyAnimation myAnimation;
    private CheckBox checkboxAmilase;
    private Realm realm = Realm.getDefaultInstance();
    private TextInputEditText hematocrito,hemoglobina,plaquetas,ureia,creatinina;
    private RadioGroup gasometriaArterialCompensadaDescompensada,leucogramaRadioGroup1,
            leucogramaRadioGroup2,albuminaRadioGroup1,albuminaRadioGroup2,
            raioxToraxRadioGroup1,raioxToraxRadioGroup2,pcr,potassio,magnesio,
            fosforo,calcio,gasometriaArterialAcidoseRadioGroup,
            gasometriaArterialMetabolicaRespiratoriaRadioGroup,bilirrubinas,faggt,
            transaminases,lactato,amilaseItens,marcadoresInfeccao;
    private RadioButton leucogramaNormal,leucogramaEstavel,leucogramaEmMelhora,gasometriaAlcalose,gasometriaAcidose,
            leucogramaEmPiora,hiperalbuminemia,albuminaNormal,hipoalbuminemia,
            raioxNormal,pneumotorax,actelectasia,infiltradosNovos,gasometriaMista,gasometriaArterialMetabolica,gasometriaArterialRespiratoria,gasometriaNormal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames);
        setToolbar(getString(R.string.Exames));
        prepareNavigationButtons();
        setupUI(findViewById(R.id.exames_activity));
        eletrolitoItens = findViewById(R.id.eletrolitosItens);
        gasometriaArterialMetabolicaLinearLayout = findViewById(R.id.gasometriaArterialMetabolicaLinearLayout);
        /**********************************TEXTINPUTEDITTEXT*******************************/
        hematocrito = (TextInputEditText)findViewById(R.id.hematocrito);
        hemoglobina = (TextInputEditText)findViewById(R.id.hemoglobina);
        plaquetas = (TextInputEditText)findViewById(R.id.plaquetas);
        ureia = (TextInputEditText)findViewById(R.id.ureia);
        creatinina = (TextInputEditText)findViewById(R.id.creatinina);
        /**********************************TEXTINPUTEDITTEXT*******************************/

        /*********************************RADIOGROUP**************************************/
        leucogramaRadioGroup1 = (RadioGroup)findViewById(R.id.leucogramaRadioGroup1);
        leucogramaRadioGroup2 = (RadioGroup)findViewById(R.id.leucogramaRadioGroup2);
        albuminaRadioGroup1 = (RadioGroup)findViewById(R.id.albuminaRadioGroup1);
        albuminaRadioGroup2 = (RadioGroup)findViewById(R.id.albuminaRadioGroup2);
        raioxToraxRadioGroup1 = (RadioGroup)findViewById(R.id.raioxToraxRadioGroup1);
        raioxToraxRadioGroup2 = (RadioGroup)findViewById(R.id.raioxToraxRadioGroup2);
        gasometriaArterialCompensadaDescompensada = (RadioGroup)findViewById(R.id.gasometriaArterialCompensadaDescompensada);
        pcr = (RadioGroup)findViewById(R.id.pcr);
        potassio = (RadioGroup)findViewById(R.id.potassio);
        magnesio = (RadioGroup)findViewById(R.id.magnesio);
        fosforo = (RadioGroup)findViewById(R.id.fosforo);
        calcio = (RadioGroup)findViewById(R.id.calcio);
        gasometriaArterialAcidoseRadioGroup = (RadioGroup)findViewById(R.id.gasometriaArterialAcidoseRadioGroup);
        gasometriaArterialMetabolicaRespiratoriaRadioGroup = (RadioGroup)findViewById(R.id.gasometriaArterialMetabolicaRespiratoriaRadioGroup);
        bilirrubinas = (RadioGroup)findViewById(R.id.bilirrubinas);
        faggt = (RadioGroup)findViewById(R.id.faggt);
        transaminases = (RadioGroup)findViewById(R.id.transaminases);
        lactato = (RadioGroup)findViewById(R.id.lactato);
        amilaseItens = (RadioGroup)findViewById(R.id.amilaseItens);
        marcadoresInfeccao = (RadioGroup)findViewById(R.id.marcadoresInfeccao);
        /*********************************RADIOGROUP**************************************/

        /*********************************RADIOBUTTON**************************************/
        leucogramaNormal = (RadioButton)findViewById(R.id.leucogramaNormal);
        leucogramaNormal.setOnClickListener(leucogramaRadioGroup1OnClickListener);
        leucogramaEstavel = (RadioButton)findViewById(R.id.leucogramaEstavel);
        leucogramaEstavel.setOnClickListener(leucogramaRadioGroup1OnClickListener);
        leucogramaEmMelhora = (RadioButton)findViewById(R.id.leucogramaEmMelhora);
        leucogramaEmMelhora.setOnClickListener(leucogramaRadioGroup2OnClickListener);
        leucogramaEmPiora = (RadioButton)findViewById(R.id.leucogramaEmPiora);
        leucogramaEmPiora.setOnClickListener(leucogramaRadioGroup2OnClickListener);
        hiperalbuminemia = (RadioButton)findViewById(R.id.hiperalbuminemia);
        hiperalbuminemia.setOnClickListener(albuminaRadioGroup1OnClickListener);
        albuminaNormal = (RadioButton)findViewById(R.id.albuminaNormal);
        albuminaNormal.setOnClickListener(albuminaRadioGroup1OnClickListener);
        hipoalbuminemia = (RadioButton)findViewById(R.id.hipoalbuminemia);
        hipoalbuminemia.setOnClickListener(albuminaRadioGroup2OnClickListener);
        raioxNormal = (RadioButton)findViewById(R.id.raioxNormal);
        raioxNormal.setOnClickListener(raioxToraxRadioGroup1OnClickListener);
        pneumotorax = (RadioButton)findViewById(R.id.pneumotorax);
        pneumotorax.setOnClickListener(raioxToraxRadioGroup1OnClickListener);
        actelectasia = (RadioButton)findViewById(R.id.actelectasia);
        actelectasia.setOnClickListener(raioxToraxRadioGroup1OnClickListener);
        infiltradosNovos = (RadioButton)findViewById(R.id.infiltradosNovos);
        infiltradosNovos.setOnClickListener(raioxToraxRadioGroup2OnClickListener);
        gasometriaMista = (RadioButton)findViewById(R.id.gasometriaMista);
        gasometriaMista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gasometriaArterialCompensadaDescompensada.isShown())
                    gasometriaArterialCompensadaDescompensada.setVisibility(View.GONE);
            }
        });
        gasometriaArterialMetabolica = (RadioButton)findViewById(R.id.gasometriaArterialMetabolica);
        gasometriaArterialMetabolica.setOnClickListener(gasometriaArterialMetabolicaRespiratoriaOnClickListener);
        gasometriaArterialRespiratoria = (RadioButton)findViewById(R.id.gasometriaArterialRespiratoria);
        gasometriaArterialRespiratoria.setOnClickListener(gasometriaArterialMetabolicaRespiratoriaOnClickListener);
        gasometriaNormal = (RadioButton)findViewById(R.id.gasometriaNormal);
        gasometriaAlcalose = (RadioButton)findViewById(R.id.gasometriaAlcalose);
        gasometriaAlcalose.setOnClickListener(gasometriaArterialAcidoseAlcaloseOnClickListener);
        gasometriaAcidose = (RadioButton)findViewById(R.id.gasometriaAcidose);
        gasometriaAcidose.setOnClickListener(gasometriaArterialAcidoseAlcaloseOnClickListener);
        gasometriaNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gasometriaArterialMetabolicaLinearLayout.isShown())
                    gasometriaArterialMetabolicaLinearLayout.setVisibility(View.GONE);
                if(gasometriaArterialCompensadaDescompensada.isShown())
                    gasometriaArterialCompensadaDescompensada.setVisibility(View.GONE);
                if(((RadioGroup)findViewById(R.id.gasometriaArterialMetabolicaRespiratoriaRadioGroup)).getCheckedRadioButtonId()!=-1)
                    ((RadioGroup)findViewById(R.id.gasometriaArterialMetabolicaRespiratoriaRadioGroup)).clearCheck();
                if(((RadioGroup)findViewById(R.id.gasometriaArterialCompensadaDescompensada)).getCheckedRadioButtonId()!=-1)
                    ((RadioGroup)findViewById(R.id.gasometriaArterialCompensadaDescompensada)).clearCheck();
            }
        });
        /*********************************RADIOBUTTON**************************************/


        checkboxAmilase = (CheckBox) findViewById(R.id.checkboxAmilase);
        checkboxAmilase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkboxAmilase.isChecked() && amilaseItens.isShown())
                    myAnimation.slideUpView(getApplicationContext(), amilaseItens);
                if(checkboxAmilase.isChecked() && !amilaseItens.isShown())
                    myAnimation.slideDownView(getApplicationContext(), amilaseItens);
            }
        });
        myAnimation = new MyAnimation();

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), FolhasBalancoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });
        setExamesFromDatabase();
    }

    private View.OnClickListener gasometriaArterialAcidoseAlcaloseOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!gasometriaArterialMetabolicaLinearLayout.isShown())
                gasometriaArterialMetabolicaLinearLayout.setVisibility(View.VISIBLE);
        }
    };

    private void setExamesFromDatabase(){
        Ficha ficha = getProperFicha();
        if(ficha.getExames()!=null){
            Exames exames = ficha.getExames();
            if(exames.getHematocrito()>0)
                hematocrito.setText(Integer.toString(exames.getHematocrito()));
            if(exames.getHemoglobina()>0)
                hemoglobina.setText(Integer.toString(exames.getHemoglobina()));
            if(exames.getPlaquetas()>0)
                plaquetas.setText(Integer.toString(exames.getPlaquetas()));
            if(exames.getLeucograma()!=null)
                setRadioButtonFromIdAndDatabase(R.id.leucograma,exames.getLeucograma());
            if(exames.getPcr()!=null)
                setRadioButtonFromIdAndDatabase(R.id.pcrLinearLayout,exames.getPcr());
            if(exames.getUreia()>0)
                ureia.setText(Integer.toString(exames.getUreia()));
            if(exames.getCreatinina()>0)
                creatinina.setText(Integer.toString(exames.getCreatinina()));
            if(exames.getPotassio()!=null)
                setRadioButtonFromIdAndDatabase(R.id.potassioLinearLayout,exames.getPotassio());
            if(exames.getMagnesio()!=null)
                setRadioButtonFromIdAndDatabase(R.id.magnesioLinearLayout,exames.getMagnesio());
            if(exames.getFosforo()!=null)
                setRadioButtonFromIdAndDatabase(R.id.fosforoLinearLayout,exames.getFosforo());
            if(exames.getCalcio()!=null)
                setRadioButtonFromIdAndDatabase(R.id.calcioLinearLayout,exames.getCalcio());
            if(exames.getGasometriaArterialAcidoseAlcalose()!=null) {
                setRadioButtonFromIdAndDatabase(R.id.gasometriaArterialAcidoseLinearLayout, exames.getGasometriaArterialAcidoseAlcalose());
                if(exames.getGasometriaArterialAcidoseAlcalose().equals(getString(R.string.Normal)))
                    gasometriaArterialMetabolicaLinearLayout.setVisibility(View.GONE);
            }
            if(exames.getGasometrialArterialMetabolicaRespiratoria()!=null) {
                setRadioButtonFromIdAndDatabase(R.id.gasometriaArterialMetabolicaLinearLayout,exames.getGasometrialArterialMetabolicaRespiratoria());
                if (!exames.getGasometrialArterialMetabolicaRespiratoria().equals(getString(R.string.Mista))) {
                    gasometriaArterialCompensadaDescompensada.setVisibility(View.VISIBLE);
                    setRadioButtonFromIdAndDatabase(R.id.gasometriaArterialCompensadaLinearLayout, exames.getGasometriaArterialCompensadaDescompensada());
                }
            }
            if(exames.getFuncaoHepaticaBilirrubinas()!=null)
                setRadioButtonFromIdAndDatabase(R.id.bilirrubinasLinearLayout,exames.getFuncaoHepaticaBilirrubinas());
            if(exames.getFuncaoHepaticaFAGGT()!=null)
                setRadioButtonFromIdAndDatabase(R.id.faggtLinearLayout,exames.getFuncaoHepaticaFAGGT());
            if(exames.getFuncaoHepaticaTransaminases()!=null)
                setRadioButtonFromIdAndDatabase(R.id.transaminasesLinearLayout,exames.getFuncaoHepaticaTransaminases());
            if(exames.getLactato()!=null)
                setRadioButtonFromIdAndDatabase(R.id.lactatoLinearLayout,exames.getLactato());
            if(exames.isAmilaseChecked()){
                checkboxAmilase.setChecked(true);
                amilaseItens.setVisibility(View.VISIBLE);
                if(exames.getAmilase()!=null)
                    setRadioButtonFromIdAndDatabase(R.id.amilaseLinearLayout,exames.getAmilase());
            }
            if(exames.getMarcadoresInfeccao()!=null)
                setRadioButtonFromIdAndDatabase(R.id.marcadoresInfeccaoLinearLayout,exames.getMarcadoresInfeccao());
            if(exames.getAlbumina()!=null)
                setRadioButtonFromIdAndDatabase(R.id.albuminaLinearLayout,exames.getAlbumina());
            if(exames.getRaioxTorax()!=null)
                setRadioButtonFromIdAndDatabase(R.id.raioxToraxLayout,exames.getRaioxTorax());
        }
    }

    private View.OnClickListener gasometriaArterialMetabolicaRespiratoriaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!gasometriaArterialCompensadaDescompensada.isShown())
                gasometriaArterialCompensadaDescompensada.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener leucogramaRadioGroup1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(leucogramaRadioGroup2.getCheckedRadioButtonId()!=-1)
                leucogramaRadioGroup2.clearCheck();
        }
    };

    private View.OnClickListener leucogramaRadioGroup2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(leucogramaRadioGroup1.getCheckedRadioButtonId()!=-1)
                leucogramaRadioGroup1.clearCheck();
        }
    };

    private View.OnClickListener albuminaRadioGroup1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(albuminaRadioGroup2.getCheckedRadioButtonId()!=-1)
                albuminaRadioGroup2.clearCheck();
        }
    };

    private View.OnClickListener albuminaRadioGroup2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(albuminaRadioGroup1.getCheckedRadioButtonId()!=-1)
                albuminaRadioGroup1.clearCheck();
        }
    };

    private View.OnClickListener raioxToraxRadioGroup1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(raioxToraxRadioGroup2.getCheckedRadioButtonId()!=-1)
                raioxToraxRadioGroup2.clearCheck();
        }
    };

    private View.OnClickListener raioxToraxRadioGroup2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(raioxToraxRadioGroup1.getCheckedRadioButtonId()!=-1)
                raioxToraxRadioGroup1.clearCheck();
        }
    };

    public void amilaseOnClick(View view) {
        if (amilaseItens.isShown())
            myAnimation.slideUpView(getApplicationContext(), amilaseItens);
        else {
            if (checkboxAmilase.isChecked())
                myAnimation.slideDownView(getApplicationContext(), amilaseItens);
        }
    }

    @Override
    public void prepareNavigationButtons() {
        proxFicha = (Button)findViewById(R.id.fichaProxima);
        antFicha = (Button)findViewById(R.id.fichaAnterior);
        antFicha.setVisibility(View.GONE);
        proxFicha.setText(FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0)+1).getName()+" >");
    }

    public void eletrolitosOnCLick(View view) {
        if (eletrolitoItens.isShown())
            myAnimation.slideUpView(getApplicationContext(), eletrolitoItens);
        else
            myAnimation.slideDownView(getApplicationContext(), eletrolitoItens);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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
        realm.beginTransaction();
        Ficha ficha = getProperFicha();
        Exames exames = realm.createObject(Exames.class);
        if(!isTextInputEditTextEmpty(hematocrito))
            exames.setHematocrito(getIntegerFromTextInputEditText(hematocrito));
        if(!isTextInputEditTextEmpty(hemoglobina))
            exames.setHemoglobina(getIntegerFromTextInputEditText(hemoglobina));
        if(!isTextInputEditTextEmpty(plaquetas))
            exames.setPlaquetas(getIntegerFromTextInputEditText(plaquetas));
        if(leucogramaRadioGroup1.getCheckedRadioButtonId()!=-1)
            exames.setLeucograma(getStringOfRadioButtonSelectedFromRadioGroup(leucogramaRadioGroup1));
        else if(leucogramaRadioGroup2.getCheckedRadioButtonId()!=-1)
            exames.setLeucograma(getStringOfRadioButtonSelectedFromRadioGroup(leucogramaRadioGroup2));
        if(pcr.getCheckedRadioButtonId()!=-1)
            exames.setPcr(getStringOfRadioButtonSelectedFromRadioGroup(pcr));
        if(!isTextInputEditTextEmpty(ureia))
            exames.setUreia(getIntegerFromTextInputEditText(ureia));
        if(!isTextInputEditTextEmpty(creatinina))
            exames.setCreatinina(getIntegerFromTextInputEditText(creatinina));
        if(potassio.getCheckedRadioButtonId()!=-1)
            exames.setPotassio(getStringOfRadioButtonSelectedFromRadioGroup(potassio));
        if(magnesio.getCheckedRadioButtonId()!=-1)
            exames.setMagnesio(getStringOfRadioButtonSelectedFromRadioGroup(magnesio));
        if(fosforo.getCheckedRadioButtonId()!=-1)
            exames.setFosforo(getStringOfRadioButtonSelectedFromRadioGroup(fosforo));
        if(calcio.getCheckedRadioButtonId()!=-1)
            exames.setCalcio(getStringOfRadioButtonSelectedFromRadioGroup(calcio));
        if(gasometriaArterialAcidoseRadioGroup.getCheckedRadioButtonId()!=-1)
            exames.setGasometriaArterialAcidoseAlcalose(getStringOfRadioButtonSelectedFromRadioGroup(gasometriaArterialAcidoseRadioGroup));
        if(gasometriaArterialMetabolicaRespiratoriaRadioGroup.getCheckedRadioButtonId()!=-1
                && !getStringOfRadioButtonSelectedFromRadioGroup(gasometriaArterialAcidoseRadioGroup).equals(getString(R.string.Normal))){
            exames.setGasometrialArterialMetabolicaRespiratoria(getStringOfRadioButtonSelectedFromRadioGroup(gasometriaArterialMetabolicaRespiratoriaRadioGroup));
            if(!getStringOfRadioButtonSelectedFromRadioGroup(gasometriaArterialMetabolicaRespiratoriaRadioGroup).equals(getString(R.string.Mista))){
                if(gasometriaArterialCompensadaDescompensada.getCheckedRadioButtonId()!=-1)
                    exames.setGasometriaArterialCompensadaDescompensada(getStringOfRadioButtonSelectedFromRadioGroup(gasometriaArterialCompensadaDescompensada));
            }
        }
        if(bilirrubinas.getCheckedRadioButtonId()!=-1)
            exames.setFuncaoHepaticaBilirrubinas(getStringOfRadioButtonSelectedFromRadioGroup(bilirrubinas));
        if(faggt.getCheckedRadioButtonId()!=-1)
            exames.setFuncaoHepaticaFAGGT(getStringOfRadioButtonSelectedFromRadioGroup(faggt));
        if(transaminases.getCheckedRadioButtonId()!=-1)
            exames.setFuncaoHepaticaTransaminases(getStringOfRadioButtonSelectedFromRadioGroup(transaminases));
        if(lactato.getCheckedRadioButtonId()!=-1)
            exames.setLactato(getStringOfRadioButtonSelectedFromRadioGroup(lactato));
        if(checkboxAmilase.isChecked()){
            exames.setAmilaseChecked(true);
            if(amilaseItens.getCheckedRadioButtonId()!=-1)
                exames.setAmilase(getStringOfRadioButtonSelectedFromRadioGroup(amilaseItens));
        }
        if(marcadoresInfeccao.getCheckedRadioButtonId()!=-1)
            exames.setMarcadoresInfeccao(getStringOfRadioButtonSelectedFromRadioGroup(marcadoresInfeccao));
        if(albuminaRadioGroup1.getCheckedRadioButtonId()!=-1)
            exames.setAlbumina(getStringOfRadioButtonSelectedFromRadioGroup(albuminaRadioGroup1));
        else if(albuminaRadioGroup2.getCheckedRadioButtonId()!=-1)
            exames.setAlbumina(getStringOfRadioButtonSelectedFromRadioGroup(albuminaRadioGroup2));
        if(raioxToraxRadioGroup1.getCheckedRadioButtonId()!=-1)
            exames.setRaioxTorax(getStringOfRadioButtonSelectedFromRadioGroup(raioxToraxRadioGroup1));
        else if(raioxToraxRadioGroup2.getCheckedRadioButtonId()!=-1)
            exames.setRaioxTorax(getStringOfRadioButtonSelectedFromRadioGroup(raioxToraxRadioGroup2));
        ficha.setExames(exames);
        realm.copyToRealmOrUpdate(ficha);
        realm.commitTransaction();
        if(exames.checkObject())
            changeCardColorToGreen();
        else
            setCardColorToDefault();
    }

}
