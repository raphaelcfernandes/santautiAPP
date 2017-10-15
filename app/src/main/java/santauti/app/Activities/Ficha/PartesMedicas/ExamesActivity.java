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

import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Exames;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

/**
 * Created by raphael on 8/6/17.
 */

public class ExamesActivity extends GenericoActivity {
    private View eletrolitoItens,gasometriaArterialMetabolicaLinearLayout,gasometriaArterialCompensadaLinearLayout;
    private MyAnimation myAnimation;
    private CheckBox checkboxAmilase;
    private TextInputEditText hematocrito,hemoglobina,plaquetas,ureia,creatinina;
    private RadioGroup gasometriaArterialAcidoseRadioGroup2,gasometriaArterialAcidoseRadioGroup1,marcadoresInfeccaoRadioGroup1,marcadoresInfeccaoRadioGroup2,gasometriaArterialCompensadaDescompensada,leucogramaRadioGroup1,
            leucogramaRadioGroup2,albuminaRadioGroup1,albuminaRadioGroup2,
            raioxToraxRadioGroup1,raioxToraxRadioGroup2,pcrRadioGroup1,pcrRadioGroup2,potassioRadioGroup1,potassioRadioGroup2,gasometriaArterialMetabolicaRespiratoriaRadioGroup,bilirrubinas,faggt,
            transaminases,lactato,amilaseItens,magnesioRadioGroup1,magnesioRadioGroup2,fosforoRadioGroup1,fosforoRadioGroup2,calcioRadioGroup1,calcioRadioGroup2;
    private RadioButton raioxToraxSemResultados,estaveis,emQueda,emMelhora,naoRealizado,gasometriaArterialSemResultados,hipercalcemia,hipocalcemia,calcioNormal,calcioSemResultados,
            hiperfosfatemia,hipofosfatemia,fosforoNormal,fosforoSemResultados,hipermagnesemia,hipomagnesemia,
            magnesioNormal,magnesioSemResultados,leucogramaNormal,leucogramaEstavel,leucogramaEmMelhora,gasometriaAlcalose,gasometriaAcidose,
            leucogramaEmPiora,leucogramaSemResultados,hiperalbuminemia,albuminaNormal,hipoalbuminemia,
            raioxNormal,pneumotorax,actelectasia,infiltradosNovos,gasometriaMista,
            gasometriaArterialMetabolica,gasometriaArterialRespiratoria,gasometriaNormal,
            pcrEstavel,pcrElevando,pcrEmQueda,pcrSemResultados,potassioNormal,potassioHipocalemia,potassioHipercalemia,potassioSemResultados,albuminaSemResultados;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exames);
        setToolbar(getString(R.string.Exames));
        prepareNavigationButtons();
        setupUI(findViewById(R.id.exames_activity));
        eletrolitoItens = findViewById(R.id.eletrolitosItens);
        gasometriaArterialMetabolicaLinearLayout = findViewById(R.id.gasometriaArterialMetabolicaLinearLayout);
        gasometriaArterialCompensadaLinearLayout = findViewById(R.id.gasometriaArterialCompensadaLinearLayout);
        /**********************************TEXTINPUTEDITTEXT*******************************/
        hematocrito = (TextInputEditText)findViewById(R.id.hematocrito);
        hemoglobina = (TextInputEditText)findViewById(R.id.hemoglobina);
        plaquetas = (TextInputEditText)findViewById(R.id.plaquetas);
        ureia = (TextInputEditText)findViewById(R.id.ureia);
        creatinina = (TextInputEditText)findViewById(R.id.creatinina);
        /**********************************TEXTINPUTEDITTEXT*******************************/

        /*********************************RADIOGROUP**************************************/
        gasometriaArterialAcidoseRadioGroup1 = (RadioGroup)findViewById(R.id.gasometriaArterialAcidoseRadioGroup1);
        gasometriaArterialAcidoseRadioGroup2 = (RadioGroup)findViewById(R.id.gasometriaArterialAcidoseRadioGroup2);
        marcadoresInfeccaoRadioGroup1 = (RadioGroup)findViewById(R.id.marcadoresInfeccaoRadioGroup1);
        marcadoresInfeccaoRadioGroup2 = (RadioGroup)findViewById(R.id.marcadoresInfeccaoRadioGroup2);
        fosforoRadioGroup1 = (RadioGroup)findViewById(R.id.fosforoRadioGroup1);
        fosforoRadioGroup2 = (RadioGroup)findViewById(R.id.fosforoRadioGroup2);
        calcioRadioGroup1 = (RadioGroup)findViewById(R.id.calcioRadioGroup1);
        calcioRadioGroup2 = (RadioGroup)findViewById(R.id.calcioRadioGroup2);
        leucogramaRadioGroup1 = (RadioGroup)findViewById(R.id.leucogramaRadioGroup1);
        leucogramaRadioGroup2 = (RadioGroup)findViewById(R.id.leucogramaRadioGroup2);
        albuminaRadioGroup1 = (RadioGroup)findViewById(R.id.albuminaRadioGroup1);
        albuminaRadioGroup2 = (RadioGroup)findViewById(R.id.albuminaRadioGroup2);
        raioxToraxRadioGroup1 = (RadioGroup)findViewById(R.id.raioxToraxRadioGroup1);
        raioxToraxRadioGroup2 = (RadioGroup)findViewById(R.id.raioxToraxRadioGroup2);
        gasometriaArterialCompensadaDescompensada = (RadioGroup)findViewById(R.id.gasometriaArterialCompensadaDescompensada);
        pcrRadioGroup1 = (RadioGroup)findViewById(R.id.pcrRadioGroup1);
        pcrRadioGroup2 = (RadioGroup)findViewById(R.id.pcrRadioGroup2);
        potassioRadioGroup1 = (RadioGroup)findViewById(R.id.potassioRadioGroup1);
        potassioRadioGroup2 = (RadioGroup)findViewById(R.id.potassioRadioGroup2);
        magnesioRadioGroup1 = (RadioGroup)findViewById(R.id.magnesioRadioGroup1);
        magnesioRadioGroup2 = (RadioGroup)findViewById(R.id.magnesioRadioGroup2);
        gasometriaArterialMetabolicaRespiratoriaRadioGroup = (RadioGroup)findViewById(R.id.gasometriaArterialMetabolicaRespiratoriaRadioGroup);
        bilirrubinas = (RadioGroup)findViewById(R.id.bilirrubinas);
        faggt = (RadioGroup)findViewById(R.id.faggt);
        transaminases = (RadioGroup)findViewById(R.id.transaminases);
        lactato = (RadioGroup)findViewById(R.id.lactato);
        amilaseItens = (RadioGroup)findViewById(R.id.amilaseItens);
        /*********************************RADIOGROUP**************************************/

        /*********************************RADIOBUTTON**************************************/
        estaveis = (RadioButton)findViewById(R.id.estaveis);
        estaveis.setOnClickListener(marcadoresInfeccaoRadioGroup1OnClickListener);
        emMelhora = (RadioButton)findViewById(R.id.emMelhora);
        emMelhora.setOnClickListener(marcadoresInfeccaoRadioGroup1OnClickListener);
        emQueda = (RadioButton)findViewById(R.id.emQueda);
        emQueda.setOnClickListener(marcadoresInfeccaoRadioGroup2OnClickListener);
        naoRealizado = (RadioButton)findViewById(R.id.naoRealizado);
        naoRealizado.setOnClickListener(marcadoresInfeccaoRadioGroup2OnClickListener);
        hipercalcemia = (RadioButton)findViewById(R.id.hipercalcemia);
        hipercalcemia.setOnClickListener(calcioRadioGroup1OnClickListener);
        hipocalcemia = (RadioButton)findViewById(R.id.hipocalcemia);
        hipocalcemia.setOnClickListener(calcioRadioGroup1OnClickListener);
        calcioNormal = (RadioButton)findViewById(R.id.calcioNormal);
        calcioNormal.setOnClickListener(calcioRadioGroup2OnClickListener);
        calcioSemResultados = (RadioButton)findViewById(R.id.calcioSemResultados);
        calcioSemResultados.setOnClickListener(calcioRadioGroup2OnClickListener);
        hiperfosfatemia = (RadioButton)findViewById(R.id.hiperfosfatemia);
        hiperfosfatemia.setOnClickListener(fosforoRadioGroup1OnClickListener);
        hipofosfatemia = (RadioButton)findViewById(R.id.hipofosfatemia);
        hipofosfatemia.setOnClickListener(fosforoRadioGroup1OnClickListener);
        fosforoNormal = (RadioButton)findViewById(R.id.fosforoNormal);
        fosforoNormal.setOnClickListener(fosforoRadioGroup2OnClickListener);
        fosforoSemResultados = (RadioButton)findViewById(R.id.fosforoSemResultados);
        fosforoSemResultados.setOnClickListener(fosforoRadioGroup2OnClickListener);
        hipermagnesemia = (RadioButton)findViewById(R.id.hipermagnesemia);
        hipermagnesemia.setOnClickListener(magnesioRadioGroup1OnClickListener);
        hipomagnesemia = (RadioButton)findViewById(R.id.hipomagnesemia);
        hipomagnesemia.setOnClickListener(magnesioRadioGroup1OnClickListener);
        magnesioNormal = (RadioButton)findViewById(R.id.magnesioNormal);
        magnesioNormal.setOnClickListener(magnesioRadioGroup2OnClickListener);
        magnesioSemResultados = (RadioButton)findViewById(R.id.magnesioSemResultados);
        magnesioSemResultados.setOnClickListener(magnesioRadioGroup2OnClickListener);
        potassioNormal = (RadioButton)findViewById(R.id.potassioNormal);
        potassioNormal.setOnClickListener(potassioRadioGroup1OnClickListener);
        potassioHipocalemia = (RadioButton)findViewById(R.id.potassioHipocalemia);
        potassioHipocalemia.setOnClickListener(potassioRadioGroup1OnClickListener);
        potassioHipercalemia = (RadioButton)findViewById(R.id.potassioHipercalemia);
        potassioHipercalemia.setOnClickListener(potassioRadioGroup2OnClickListener);
        potassioSemResultados = (RadioButton)findViewById(R.id.potassioSemResultados);
        potassioSemResultados.setOnClickListener(potassioRadioGroup2OnClickListener);
        pcrEstavel = (RadioButton)findViewById(R.id.pcrEstavel);
        pcrEstavel.setOnClickListener(pcrRadioGroup1OnClickListener);
        pcrElevando = (RadioButton)findViewById(R.id.pcrElevando);
        pcrElevando.setOnClickListener(pcrRadioGroup1OnClickListener);
        pcrEmQueda = (RadioButton)findViewById(R.id.pcrEmQueda);
        pcrEmQueda.setOnClickListener(pcrRadioGroup2OnClickListener);
        pcrSemResultados = (RadioButton)findViewById(R.id.pcrSemResultados);
        pcrSemResultados.setOnClickListener(pcrRadioGroup2OnClickListener);
        leucogramaNormal = (RadioButton)findViewById(R.id.leucogramaNormal);
        leucogramaNormal.setOnClickListener(leucogramaRadioGroup1OnClickListener);
        leucogramaEstavel = (RadioButton)findViewById(R.id.leucogramaEstavel);
        leucogramaEstavel.setOnClickListener(leucogramaRadioGroup2OnClickListener);
        leucogramaEmMelhora = (RadioButton)findViewById(R.id.leucogramaEmMelhora);
        leucogramaEmMelhora.setOnClickListener(leucogramaRadioGroup2OnClickListener);
        leucogramaEmPiora = (RadioButton)findViewById(R.id.leucogramaEmPiora);
        leucogramaEmPiora.setOnClickListener(leucogramaRadioGroup2OnClickListener);
        leucogramaSemResultados = (RadioButton)findViewById(R.id.leucogramaSemResultados);
        leucogramaSemResultados.setOnClickListener(leucogramaRadioGroup1OnClickListener);
        hiperalbuminemia = (RadioButton)findViewById(R.id.hiperalbuminemia);
        hiperalbuminemia.setOnClickListener(albuminaRadioGroup1OnClickListener);
        albuminaNormal = (RadioButton)findViewById(R.id.albuminaNormal);
        albuminaNormal.setOnClickListener(albuminaRadioGroup2OnClickListener);
        hipoalbuminemia = (RadioButton)findViewById(R.id.hipoalbuminemia);
        hipoalbuminemia.setOnClickListener(albuminaRadioGroup1OnClickListener);
        albuminaSemResultados = (RadioButton)findViewById(R.id.albuminaSemResultados);
        albuminaSemResultados.setOnClickListener(albuminaRadioGroup2OnClickListener);
        raioxNormal = (RadioButton)findViewById(R.id.raioxNormal);
        raioxNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.raioxToraxItensLayout).isShown())
                    findViewById(R.id.raioxToraxItensLayout).setVisibility(View.GONE);
                if(raioxToraxRadioGroup2.getCheckedRadioButtonId()!=-1)
                    raioxToraxRadioGroup2.clearCheck();
            }
        });
        pneumotorax = (RadioButton)findViewById(R.id.pneumotorax);
        pneumotorax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!findViewById(R.id.raioxToraxItensLayout).isShown())
                    findViewById(R.id.raioxToraxItensLayout).setVisibility(View.VISIBLE);
                if(raioxToraxRadioGroup2.getCheckedRadioButtonId()!=-1)
                    raioxToraxRadioGroup2.clearCheck();
            }
        });
        actelectasia = (RadioButton)findViewById(R.id.actelectasia);
        actelectasia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!findViewById(R.id.raioxToraxItensLayout).isShown())
                    findViewById(R.id.raioxToraxItensLayout).setVisibility(View.VISIBLE);
                if(raioxToraxRadioGroup2.getCheckedRadioButtonId()!=-1)
                    raioxToraxRadioGroup2.clearCheck();
            }
        });
        infiltradosNovos = (RadioButton)findViewById(R.id.infiltradosNovos);
        infiltradosNovos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!findViewById(R.id.raioxToraxItensLayout).isShown())
                    findViewById(R.id.raioxToraxItensLayout).setVisibility(View.VISIBLE);
                if(raioxToraxRadioGroup1.getCheckedRadioButtonId()!=-1)
                    raioxToraxRadioGroup1.clearCheck();
            }
        });
        raioxToraxSemResultados = (RadioButton)findViewById(R.id.raioxToraxSemResultados);
        raioxToraxSemResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.raioxToraxItensLayout).isShown())
                    findViewById(R.id.raioxToraxItensLayout).setVisibility(View.GONE);
                if(raioxToraxRadioGroup1.getCheckedRadioButtonId()!=-1)
                    raioxToraxRadioGroup1.clearCheck();
            }
        });
        gasometriaMista = (RadioButton)findViewById(R.id.gasometriaMista);
        gasometriaMista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gasometriaArterialCompensadaDescompensada.isShown()) {
                    if(gasometriaArterialCompensadaDescompensada.getCheckedRadioButtonId()!=-1)
                        gasometriaArterialCompensadaDescompensada.clearCheck();
                    gasometriaArterialCompensadaDescompensada.setVisibility(View.GONE);
                }
            }
        });
        gasometriaArterialMetabolica = (RadioButton)findViewById(R.id.gasometriaArterialMetabolica);
        gasometriaArterialMetabolica.setOnClickListener(gasometriaArterialMetabolicaRespiratoriaOnClickListener);
        gasometriaArterialRespiratoria = (RadioButton)findViewById(R.id.gasometriaArterialRespiratoria);
        gasometriaArterialRespiratoria.setOnClickListener(gasometriaArterialMetabolicaRespiratoriaOnClickListener);

        gasometriaNormal = (RadioButton)findViewById(R.id.gasometriaNormal);
        gasometriaNormal.setOnClickListener(gasometriaArterialAcidoseAlcaloseRadioGroup2OnClickListener);
        gasometriaAlcalose = (RadioButton)findViewById(R.id.gasometriaAlcalose);
        gasometriaAlcalose.setOnClickListener(gasometriaArterialAcidoseAlcaloseRadioGroup1OnClickListener);
        gasometriaAcidose = (RadioButton)findViewById(R.id.gasometriaAcidose);
        gasometriaAcidose.setOnClickListener(gasometriaArterialAcidoseAlcaloseRadioGroup1OnClickListener);
        gasometriaArterialSemResultados = (RadioButton)findViewById(R.id.gasometriaArterialSemResultados);
        gasometriaArterialSemResultados.setOnClickListener(gasometriaArterialAcidoseAlcaloseRadioGroup2OnClickListener);
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

    private void setExamesFromDatabase(){
//        Ficha ficha = getProperFicha();
//        if(ficha.getExames()!=null){
//            Exames exames = ficha.getExames();
//            if(exames.getHematocrito()>=0)
//                hematocrito.setText(Integer.toString(exames.getHematocrito()));
//            if(exames.getHemoglobina()>=0)
//                hemoglobina.setText(Integer.toString(exames.getHemoglobina()));
//            if(exames.getPlaquetas()>=0)
//                plaquetas.setText(Integer.toString(exames.getPlaquetas()));
//            if(exames.getLeucograma()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.leucograma,exames.getLeucograma());
//            if(exames.getPcr()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.pcrLinearLayout,exames.getPcr());
//            if(exames.getUreia()>=0)
//                ureia.setText(Integer.toString(exames.getUreia()));
//            if(exames.getCreatinina()>=0)
//                creatinina.setText(Integer.toString(exames.getCreatinina()));
//            if(exames.getPotassio()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.potassioLinearLayout,exames.getPotassio());
//            if(exames.getMagnesio()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.magnesioLinearLayout,exames.getMagnesio());
//            if(exames.getFosforo()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.fosforoLinearLayout,exames.getFosforo());
//            if(exames.getCalcio()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.calcioLinearLayout,exames.getCalcio());
//            if(exames.getGasometriaArterialAcidoseAlcalose()!=null) {
//                setRadioButtonFromIdAndDatabase(R.id.gasometriaArterialAcidoseLinearLayout, exames.getGasometriaArterialAcidoseAlcalose());
//                if(exames.getGasometriaArterialAcidoseAlcalose().equals(getString(R.string.Normal)))
//                    gasometriaArterialMetabolicaLinearLayout.setVisibility(View.GONE);
//                else
//                    gasometriaArterialMetabolicaLinearLayout.setVisibility(View.VISIBLE);
//            }
//            if(exames.getGasometrialArterialMetabolicaRespiratoria()!=null) {
//                setRadioButtonFromIdAndDatabase(R.id.gasometriaArterialMetabolicaLinearLayout,exames.getGasometrialArterialMetabolicaRespiratoria());
//                if (!exames.getGasometrialArterialMetabolicaRespiratoria().equals(getString(R.string.Mista))) {
//                    gasometriaArterialCompensadaDescompensada.setVisibility(View.VISIBLE);
//                    setRadioButtonFromIdAndDatabase(R.id.gasometriaArterialCompensadaLinearLayout, exames.getGasometriaArterialCompensadaDescompensada());
//                }
//            }
//            if(exames.getFuncaoHepaticaBilirrubinas()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.bilirrubinasLinearLayout,exames.getFuncaoHepaticaBilirrubinas());
//            if(exames.getFuncaoHepaticaFAGGT()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.faggtLinearLayout,exames.getFuncaoHepaticaFAGGT());
//            if(exames.getFuncaoHepaticaTransaminases()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.transaminasesLinearLayout,exames.getFuncaoHepaticaTransaminases());
//            if(exames.getLactato()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.lactatoLinearLayout,exames.getLactato());
//            if(exames.isAmilaseChecked()){
//                checkboxAmilase.setChecked(true);
//                amilaseItens.setVisibility(View.VISIBLE);
//                if(exames.getAmilase()!=null)
//                    setRadioButtonFromIdAndDatabase(R.id.amilaseLinearLayout,exames.getAmilase());
//            }
//            if(exames.getMarcadoresInfeccao()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.marcadoresInfeccaoLinearLayout,exames.getMarcadoresInfeccao());
//            if(exames.getAlbumina()!=null)
//                setRadioButtonFromIdAndDatabase(R.id.albuminaLinearLayout,exames.getAlbumina());
//            if(exames.getRaioxTorax()!=null) {
//                setRadioButtonFromIdAndDatabase(R.id.raioxToraxLayout, exames.getRaioxTorax());
//                if(!exames.getRaioxToraxList().isEmpty()) {
//                    preencheCheckboxes(R.id.raioxToraxItensLayout, exames.getRaioxToraxList());
//                    findViewById(R.id.raioxToraxItensLayout).setVisibility(View.VISIBLE);
//                }
//            }
//        }
    }

    private View.OnClickListener marcadoresInfeccaoRadioGroup1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(marcadoresInfeccaoRadioGroup2.getCheckedRadioButtonId()!=-1)
                marcadoresInfeccaoRadioGroup2.clearCheck();
        }
    };

    private View.OnClickListener marcadoresInfeccaoRadioGroup2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(marcadoresInfeccaoRadioGroup1.getCheckedRadioButtonId()!=-1)
                calcioRadioGroup1.clearCheck();
        }
    };

    private View.OnClickListener calcioRadioGroup1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(calcioRadioGroup2.getCheckedRadioButtonId()!=-1)
                calcioRadioGroup2.clearCheck();
        }
    };

    private View.OnClickListener calcioRadioGroup2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(calcioRadioGroup1.getCheckedRadioButtonId()!=-1)
                calcioRadioGroup1.clearCheck();
        }
    };

    private View.OnClickListener fosforoRadioGroup1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(fosforoRadioGroup2.getCheckedRadioButtonId()!=-1)
                fosforoRadioGroup2.clearCheck();
        }
    };

    private View.OnClickListener fosforoRadioGroup2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(fosforoRadioGroup1.getCheckedRadioButtonId()!=-1)
                fosforoRadioGroup1.clearCheck();
        }
    };

    private View.OnClickListener magnesioRadioGroup1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(magnesioRadioGroup2.getCheckedRadioButtonId()!=-1)
                magnesioRadioGroup2.clearCheck();
        }
    };

    private View.OnClickListener magnesioRadioGroup2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(magnesioRadioGroup1.getCheckedRadioButtonId()!=-1)
                magnesioRadioGroup1.clearCheck();
        }
    };

    private View.OnClickListener potassioRadioGroup1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(potassioRadioGroup2.getCheckedRadioButtonId()!=-1)
                potassioRadioGroup2.clearCheck();
        }
    };

    private View.OnClickListener potassioRadioGroup2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(potassioRadioGroup1.getCheckedRadioButtonId()!=-1)
                potassioRadioGroup1.clearCheck();
        }
    };

    private View.OnClickListener pcrRadioGroup1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(pcrRadioGroup2.getCheckedRadioButtonId()!=-1)
                pcrRadioGroup2.clearCheck();
        }
    };
    private View.OnClickListener pcrRadioGroup2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(pcrRadioGroup1.getCheckedRadioButtonId()!=-1)
                pcrRadioGroup1.clearCheck();
        }
    };


    private View.OnClickListener gasometriaArterialAcidoseAlcaloseRadioGroup1OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!gasometriaArterialMetabolicaLinearLayout.isShown())
                gasometriaArterialMetabolicaLinearLayout.setVisibility(View.VISIBLE);
            if(gasometriaArterialAcidoseRadioGroup2.getCheckedRadioButtonId()!=-1)
                gasometriaArterialAcidoseRadioGroup2.clearCheck();
        }
    };

    private View.OnClickListener gasometriaArterialAcidoseAlcaloseRadioGroup2OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(gasometriaArterialMetabolicaLinearLayout.isShown())
                gasometriaArterialMetabolicaLinearLayout.setVisibility(View.GONE);
            if(gasometriaArterialAcidoseRadioGroup1.getCheckedRadioButtonId()!=-1)
                gasometriaArterialAcidoseRadioGroup1.clearCheck();
            if(gasometriaArterialCompensadaDescompensada.isShown())
                gasometriaArterialCompensadaDescompensada.setVisibility(View.GONE);
            if(gasometriaArterialMetabolicaRespiratoriaRadioGroup.getCheckedRadioButtonId()!=-1)
                gasometriaArterialMetabolicaRespiratoriaRadioGroup.clearCheck();
            if(gasometriaArterialCompensadaDescompensada.getCheckedRadioButtonId()!=-1)
                gasometriaArterialCompensadaDescompensada.clearCheck();
        }
    };


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
//        realm.beginTransaction();
//        Ficha ficha = getProperFicha();
//        Exames exames = realm.createObject(Exames.class);
//        if(!isTextInputEditTextEmpty(hematocrito))
//            exames.setHematocrito(getIntegerFromTextInputEditText(hematocrito));
//        else
//            exames.setHematocrito(0);
//        if(!isTextInputEditTextEmpty(hemoglobina))
//            exames.setHemoglobina(getIntegerFromTextInputEditText(hemoglobina));
//        else
//            exames.setHemoglobina(0);
//        if(!isTextInputEditTextEmpty(plaquetas))
//            exames.setPlaquetas(getIntegerFromTextInputEditText(plaquetas));
//        else
//            exames.setPlaquetas(0);
//        if(!isTextInputEditTextEmpty(ureia))
//            exames.setUreia(getIntegerFromTextInputEditText(ureia));
//        else
//            exames.setUreia(0);
//        if(!isTextInputEditTextEmpty(creatinina))
//            exames.setCreatinina(getIntegerFromTextInputEditText(creatinina));
//        else
//            exames.setCreatinina(0);
//        if(leucogramaRadioGroup1.getCheckedRadioButtonId()!=-1)
//            exames.setLeucograma(getStringOfRadioButtonSelectedFromRadioGroup(leucogramaRadioGroup1));
//        else if(leucogramaRadioGroup2.getCheckedRadioButtonId()!=-1)
//            exames.setLeucograma(getStringOfRadioButtonSelectedFromRadioGroup(leucogramaRadioGroup2));
//        if(pcrRadioGroup1.getCheckedRadioButtonId()!=-1)
//            exames.setPcr(getStringOfRadioButtonSelectedFromRadioGroup(pcrRadioGroup1));
//        else if(pcrRadioGroup2.getCheckedRadioButtonId()!=-1)
//            exames.setPcr(getStringOfRadioButtonSelectedFromRadioGroup(pcrRadioGroup2));
//        if(potassioRadioGroup1.getCheckedRadioButtonId()!=-1)
//            exames.setPotassio(getStringOfRadioButtonSelectedFromRadioGroup(potassioRadioGroup1));
//        else if(potassioRadioGroup2.getCheckedRadioButtonId()!=-1)
//            exames.setPotassio(getStringOfRadioButtonSelectedFromRadioGroup(potassioRadioGroup2));
//        if(magnesioRadioGroup1.getCheckedRadioButtonId()!=-1)
//            exames.setMagnesio(getStringOfRadioButtonSelectedFromRadioGroup(magnesioRadioGroup1));
//        if(magnesioRadioGroup2.getCheckedRadioButtonId()!=-1)
//            exames.setMagnesio(getStringOfRadioButtonSelectedFromRadioGroup(magnesioRadioGroup2));
//        if(fosforoRadioGroup1.getCheckedRadioButtonId()!=-1)
//            exames.setFosforo(getStringOfRadioButtonSelectedFromRadioGroup(fosforoRadioGroup1));
//        if(fosforoRadioGroup2.getCheckedRadioButtonId()!=-1)
//            exames.setFosforo(getStringOfRadioButtonSelectedFromRadioGroup(fosforoRadioGroup2));
//        if(calcioRadioGroup1.getCheckedRadioButtonId()!=-1)
//            exames.setCalcio(getStringOfRadioButtonSelectedFromRadioGroup(calcioRadioGroup1));
//        if(calcioRadioGroup2.getCheckedRadioButtonId()!=-1)
//            exames.setCalcio(getStringOfRadioButtonSelectedFromRadioGroup(calcioRadioGroup2));
//        if(gasometriaArterialAcidoseRadioGroup1.getCheckedRadioButtonId()!=-1)
//            exames.setGasometriaArterialAcidoseAlcalose(getStringOfRadioButtonSelectedFromRadioGroup(gasometriaArterialAcidoseRadioGroup1));
//        else if(gasometriaArterialAcidoseRadioGroup2.getCheckedRadioButtonId()!=-1)
//            exames.setGasometriaArterialAcidoseAlcalose(getStringOfRadioButtonSelectedFromRadioGroup(gasometriaArterialAcidoseRadioGroup2));
//        if(gasometriaArterialMetabolicaRespiratoriaRadioGroup.getCheckedRadioButtonId()!=-1){
//            exames.setGasometrialArterialMetabolicaRespiratoria(getStringOfRadioButtonSelectedFromRadioGroup(gasometriaArterialMetabolicaRespiratoriaRadioGroup));
//            if(!getStringOfRadioButtonSelectedFromRadioGroup(gasometriaArterialMetabolicaRespiratoriaRadioGroup).equals(getString(R.string.Mista))){
//                if(gasometriaArterialCompensadaDescompensada.getCheckedRadioButtonId()!=-1)
//                    exames.setGasometriaArterialCompensadaDescompensada(getStringOfRadioButtonSelectedFromRadioGroup(gasometriaArterialCompensadaDescompensada));
//            }
//        }
//        if(bilirrubinas.getCheckedRadioButtonId()!=-1)
//            exames.setFuncaoHepaticaBilirrubinas(getStringOfRadioButtonSelectedFromRadioGroup(bilirrubinas));
//        if(faggt.getCheckedRadioButtonId()!=-1)
//            exames.setFuncaoHepaticaFAGGT(getStringOfRadioButtonSelectedFromRadioGroup(faggt));
//        if(transaminases.getCheckedRadioButtonId()!=-1)
//            exames.setFuncaoHepaticaTransaminases(getStringOfRadioButtonSelectedFromRadioGroup(transaminases));
//        if(lactato.getCheckedRadioButtonId()!=-1)
//            exames.setLactato(getStringOfRadioButtonSelectedFromRadioGroup(lactato));
//        if(checkboxAmilase.isChecked()){
//            exames.setAmilaseChecked(true);
//            if(amilaseItens.getCheckedRadioButtonId()!=-1)
//                exames.setAmilase(getStringOfRadioButtonSelectedFromRadioGroup(amilaseItens));
//        }
//        if(marcadoresInfeccaoRadioGroup1.getCheckedRadioButtonId()!=-1)
//            exames.setMarcadoresInfeccao(getStringOfRadioButtonSelectedFromRadioGroup(marcadoresInfeccaoRadioGroup1));
//        if(marcadoresInfeccaoRadioGroup2.getCheckedRadioButtonId()!=-1)
//            exames.setMarcadoresInfeccao(getStringOfRadioButtonSelectedFromRadioGroup(marcadoresInfeccaoRadioGroup2));
//        if(albuminaRadioGroup1.getCheckedRadioButtonId()!=-1)
//            exames.setAlbumina(getStringOfRadioButtonSelectedFromRadioGroup(albuminaRadioGroup1));
//        else if(albuminaRadioGroup2.getCheckedRadioButtonId()!=-1)
//            exames.setAlbumina(getStringOfRadioButtonSelectedFromRadioGroup(albuminaRadioGroup2));
//
//        if(raioxToraxRadioGroup1.getCheckedRadioButtonId()!=-1){
//            if(!raioxNormal.isChecked()){
//                RealmList<RealmString> realmStrings = getCheckBoxesPreenchidos(R.id.raioxToraxItensLayout);
//                for(RealmString realmString : realmStrings)
//                    exames.getRaioxToraxList().add(realmString);
//            }
//            exames.setRaioxTorax(getStringOfRadioButtonSelectedFromRadioGroup(raioxToraxRadioGroup1));
//        }
//
//        if(raioxToraxRadioGroup2.getCheckedRadioButtonId()!=-1){
//            if(!raioxToraxSemResultados.isChecked()){
//                RealmList<RealmString> realmStrings = getCheckBoxesPreenchidos(R.id.raioxToraxItensLayout);
//                for(RealmString realmString : realmStrings)
//                    exames.getRaioxToraxList().add(realmString);
//            }
//            exames.setRaioxTorax(getStringOfRadioButtonSelectedFromRadioGroup(raioxToraxRadioGroup2));
//        }
//
//        ficha.setExames(exames);
//        realm.copyToRealmOrUpdate(ficha);
//        realm.commitTransaction();
//        if(exames.checkObject())
//            changeCardColorToGreen();
//        else
//            setCardColorToDefault();
    }

}
