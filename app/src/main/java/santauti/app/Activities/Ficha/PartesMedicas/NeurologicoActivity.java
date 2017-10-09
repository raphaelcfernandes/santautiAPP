package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmList;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Neurologico;
import santauti.app.Model.Ficha.RealmObjects.RealmString;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 13-May-17.
 */

public class NeurologicoActivity extends GenericoActivity {
    private Realm realm;
    private MyAnimation myAnimation;
    private int escalaDeGlasgowSoma=0;
    private int nivelConscienciaSelection=-1;
    private int rassSelection=-1;
    private int ramsaySelection=-1;
    private int respostaMotoraSelection=-1;
    private int respostaVerbalSelection=-1;
    private int aberturaOcularSelection=-1;
    private View avaliacaoPupilarItensLayout,caracteristica2CAMICU,caracteristica3CAMICU,caracteristica4CAMICU;
    private TextView nivelConscienciaTextView;
    private TextView rassTextView;
    private TextView ramsayTextView;
    private TextView escalaDeGlasgowTextView,camIcuTextView;
    private TextView aberturaOcularTextView;
    private TextView respostaVerbalTextView;
    private TextView respostaMotoraTextView,rassTextViewCAMICU;
    private View simSedado,diferencaPupilaLayout,escalaGlasgowItensLayout,
            deliriumItensLayout,deficitMotorItensLayout,temporoEspacialLayout,desorientadoLayout,CAMICU;
    private CheckBox checkboxDeficitMotor;
    private AppCompatRadioButton orientado,desorientado,
            caracteristica1CAMICUSim,caracteristica1CAMICUNao,caracteristica2CAMICUMenos3erros,caracteristica2CAMICUMais3erros,
            caracteristica4Mais2Erros,caracteristica4Menos2Erros;
    private RadioGroup caracteristica1CAMICURadioGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neurologico);
        setToolbar(getString(R.string.Neurologico));

        /******************************VARIAVEIS LAYOUTS*************************************/
        simSedado = findViewById(R.id.sedado_sim_layout);
        CAMICU = findViewById(R.id.CAMICU);
        caracteristica2CAMICU = findViewById(R.id.caracteristica2CAMICU);
        caracteristica3CAMICU = findViewById(R.id.caracteristica3CAMICU);
        caracteristica4CAMICU = findViewById(R.id.caracteristica4CAMICU);
        diferencaPupilaLayout = findViewById(R.id.diferencaPupilaLayout);
        escalaGlasgowItensLayout = findViewById(R.id.escalaGlasgowItensLayout);
        deliriumItensLayout = findViewById(R.id.deliriumItens);
        deficitMotorItensLayout = findViewById(R.id.deficitMotorItens);
        nivelConscienciaTextView = (TextView)findViewById(R.id.nivelCosciencia);
        rassTextView = (TextView)findViewById(R.id.rassTextView);
        ramsayTextView = (TextView)findViewById(R.id.ramsayTextView);
        escalaDeGlasgowTextView = (TextView)findViewById(R.id.escalaDeGlasgow);
        aberturaOcularTextView = (TextView)findViewById(R.id.aberturaOcular);
        respostaVerbalTextView = (TextView)findViewById(R.id.respostaVerbal);
        respostaMotoraTextView = (TextView)findViewById(R.id.respostaMotora);
        camIcuTextView = (TextView)findViewById(R.id.camIcuTextView);
        rassTextViewCAMICU = (TextView)findViewById(R.id.rassTextViewCAMICU);
        temporoEspacialLayout = findViewById(R.id.temporoEspacialLayout);
        desorientadoLayout = findViewById(R.id.desorientadoLayout);
        avaliacaoPupilarItensLayout = findViewById(R.id.avaliacaoPupilarItensLayout);
        /******************************VARIAVEIS LAYOUTS*************************************/

        /******************************VARIAVEIS RADIOBUTTON*********************************/
        caracteristica1CAMICUSim = (AppCompatRadioButton)findViewById(R.id.caracteristica1CAMICUSim);
        caracteristica1CAMICUSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!caracteristica2CAMICU.isShown())
                    caracteristica2CAMICU.setVisibility(View.VISIBLE);
                ((RadioGroup)findViewById(R.id.inatencaoRadioGroup)).clearCheck();
                ((RadioGroup)findViewById(R.id.pensamentoRadioGroup)).clearCheck();
            }
        });
        caracteristica1CAMICUNao = (AppCompatRadioButton)findViewById(R.id.caracteristica1CAMICUNao);
        caracteristica1CAMICUNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camIcuTextView.setText("Não há delirium");
                if(caracteristica2CAMICU.isShown())
                    caracteristica2CAMICU.setVisibility(View.GONE);
                if(caracteristica3CAMICU.isShown())
                    caracteristica3CAMICU.setVisibility(View.GONE);
                if(caracteristica4CAMICU.isShown())
                    caracteristica4CAMICU.setVisibility(View.GONE);
            }
        });
        caracteristica2CAMICUMenos3erros = (AppCompatRadioButton)findViewById(R.id.caracteristica2CAMICUMenos3erros);
        caracteristica2CAMICUMenos3erros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(caracteristica4CAMICU.isShown())
                    caracteristica4CAMICU.setVisibility(View.GONE);
                if(caracteristica3CAMICU.isShown())
                    caracteristica3CAMICU.setVisibility(View.GONE);
                camIcuTextView.setText("Não há delirium");
                ((RadioGroup)findViewById(R.id.pensamentoRadioGroup)).clearCheck();
            }
        });
        caracteristica2CAMICUMais3erros = (AppCompatRadioButton)findViewById(R.id.caracteristica2CAMICUMais3erros);
        caracteristica2CAMICUMais3erros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!caracteristica4CAMICU.isShown() && rassSelection==4 && simSedado.isShown())
                    caracteristica4CAMICU.setVisibility(View.VISIBLE);
                if(rassSelection!=4 && simSedado.isShown())
                    camIcuTextView.setText("Há delirium");
                else {
                    camIcuTextView.setText("");
                    if(!simSedado.isShown()) {
                        rassSelection = -1;
                        rassTextViewCAMICU.setText("");
                    }

                }
                if(!simSedado.isShown())
                    caracteristica3CAMICU.setVisibility(View.VISIBLE);

            }
        });
        caracteristica4Mais2Erros = (AppCompatRadioButton)findViewById(R.id.caracteristica4Mais2Erros);
        caracteristica4Mais2Erros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camIcuTextView.setText("Há delirium");
            }
        });
        caracteristica4Menos2Erros = (AppCompatRadioButton)findViewById(R.id.caracteristica4Menos2Erros);
        caracteristica4Menos2Erros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camIcuTextView.setText("Não há delirium");
            }
        });
        caracteristica1CAMICURadioGroup = (RadioGroup)findViewById(R.id.caracteristica1CAMICURadioGroup);

        /******************************VARIAVEIS RADIOBUTTON*********************************/


        /******************************VARIAVEIS CHECKBOX*******************************/
        checkboxDeficitMotor = (CheckBox)findViewById(R.id.checkboxDeficitMotor);
        checkboxDeficitMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkboxDeficitMotor.isChecked())
                    myAnimation.slideUpView(getApplicationContext(), deficitMotorItensLayout);
                else
                    myAnimation.slideDownView(getApplicationContext(), deficitMotorItensLayout);
            }
        });
        /******************************VARIAVEIS CHECKBOX*******************************/


        realm = Realm.getDefaultInstance();
        myAnimation = new MyAnimation();

        prepareNavigationButtons();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RespiradorActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HemodinamicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });
        setNeurologicoFromDatabase();
    }

    private void setNeurologicoFromDatabase(){
//        Ficha ficha = getProperFicha();
//        int j=0;
//        if(ficha.getNeurologico()!=null){
//            Neurologico neurologico = ficha.getNeurologico();
//            if(neurologico.isSedado()){
//                simSedado.setVisibility(View.VISIBLE);
//                if(neurologico.getNivelConsciencia().equals(getString(R.string.Sedado))){
//                    String[] options = getResources().getStringArray(R.array.nivelConsciencia);
//                    for(j=0;j<options.length;j++){
//                        StringBuilder stringBuilder = new StringBuilder(options[j]);
//                        for(int i=0;i<stringBuilder.length();i++){
//                            if(stringBuilder.charAt(i)==' ') {
//                                if (neurologico.getNivelConsciencia().equals(stringBuilder.substring(0, i)))
//                                    nivelConscienciaSelection = j;
//                                break;
//                            }
//                        }
//                    }
//                    nivelConscienciaTextView.setText(getResources().getStringArray(R.array.nivelConsciencia)[nivelConscienciaSelection]);
//                }
//            }
//        }
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

    private void verificaCamposENotificaAdapter(){
        realm.beginTransaction();
        Neurologico neurologico = realm.createObject(Neurologico.class);
        StringBuilder stringBuilder;
        if(nivelConscienciaSelection>-1){
            int i=0;
            stringBuilder = new StringBuilder(nivelConscienciaTextView.getText().toString());
            for(i=0;i<stringBuilder.length();i++)
                if(stringBuilder.charAt(i)==' ')
                    break;
            neurologico.setNivelConsciencia(stringBuilder.substring(0,i));
        }
        if(neurologico.getNivelConsciencia()!=null && neurologico.getNivelConsciencia().equals(getString(R.string.Sedado))){
            neurologico.setSedado(true);
            if(rassSelection>-1){
                stringBuilder = new StringBuilder(rassTextView.getText().toString());
                neurologico.setRass(stringBuilder.substring(0,stringBuilder.indexOf(".")));
            }
            if(ramsaySelection>-1){
                stringBuilder = new StringBuilder(ramsayTextView.getText().toString());
                neurologico.setRamsay(Integer.parseInt(stringBuilder.substring(0,stringBuilder.indexOf(" "))));
            }
        }
        if(aberturaOcularSelection>-1){
            stringBuilder = new StringBuilder(aberturaOcularTextView.getText().toString());
            neurologico.setAberturaOcular(Integer.parseInt(stringBuilder.substring(0,stringBuilder.indexOf(" "))));
        }
        if(respostaVerbalSelection>-1){
            stringBuilder = new StringBuilder(respostaVerbalTextView.getText().toString());
            neurologico.setRespostaVerbal(Integer.parseInt(stringBuilder.substring(0,stringBuilder.indexOf(" "))));
        }
        if(respostaMotoraSelection>-1) {
            stringBuilder = new StringBuilder(respostaMotoraTextView.getText().toString());
            neurologico.setRespostaMotora(Integer.parseInt(stringBuilder.substring(0, stringBuilder.indexOf(" "))));
        }

        if(checkboxDeficitMotor.isChecked()){
            neurologico.setDeficitMotor(true);
            if(((RadioGroup)findViewById(R.id.mse)).getCheckedRadioButtonId()!=-1)
                neurologico.setMse(getStringOfRadioButtonSelectedFromRadioGroup((((RadioGroup)findViewById(R.id.mse)))));
            if(((RadioGroup)findViewById(R.id.msd)).getCheckedRadioButtonId()!=-1)
                neurologico.setMsd(getStringOfRadioButtonSelectedFromRadioGroup((((RadioGroup)findViewById(R.id.msd)))));
            if(((RadioGroup)findViewById(R.id.mie)).getCheckedRadioButtonId()!=-1)
                neurologico.setMie(getStringOfRadioButtonSelectedFromRadioGroup((((RadioGroup)findViewById(R.id.mie)))));
            if(((RadioGroup)findViewById(R.id.mid)).getCheckedRadioButtonId()!=-1)
                neurologico.setMid(getStringOfRadioButtonSelectedFromRadioGroup((((RadioGroup)findViewById(R.id.mid)))));
        }
        if(((RadioGroup)findViewById(R.id.tamanhoPupila)).getCheckedRadioButtonId()!=-1)
            neurologico.setTamanhoPupila(getStringOfRadioButtonSelectedFromRadioGroup((RadioGroup)findViewById(R.id.tamanhoPupila)));
        if(((RadioGroup)findViewById(R.id.simetriaPupila)).getCheckedRadioButtonId()!=-1) {
            neurologico.setSimetriaPupila(getStringOfRadioButtonSelectedFromRadioGroup((RadioGroup) findViewById(R.id.simetriaPupila)));
            if(getStringOfRadioButtonSelectedFromRadioGroup(((RadioGroup)findViewById(R.id.simetriaPupila))).equals(getString(R.string.Anisocoricas))
                    && ((RadioGroup)findViewById(R.id.diferencaPupila)).getCheckedRadioButtonId()!=-1)
                neurologico.setDiferencaPupila(getStringOfRadioButtonSelectedFromRadioGroup((RadioGroup)findViewById(R.id.diferencaPupila)));

        }
        if(((RadioGroup)findViewById(R.id.reatividadeLuz)).getCheckedRadioButtonId()!=-1)
            neurologico.setReatividadeLuzPupila(getStringOfRadioButtonSelectedFromRadioGroup(((RadioGroup)findViewById(R.id.reatividadeLuz))));
        Ficha r = getProperFicha();
        r.setNeurologico(neurologico);
        realm.copyToRealmOrUpdate(r);
        realm.commitTransaction();
//        if(neurologico.checkObject())
        changeCardColorToGreen();
//        else
//            setCardColorToDefault();
    }

    public void nivelConscienciaOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.NivelConsciencia);

        //list of items
        final String[] options = getResources().getStringArray(R.array.nivelConsciencia);
//        Arrays.sort(options);
        builder.setSingleChoiceItems(options, nivelConscienciaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nivelConscienciaTextView.setText(options[which]);
                        nivelConscienciaTextView.setVisibility(View.VISIBLE);
                        nivelConscienciaSelection=which;
                        if(options[which].equals(getResources().getStringArray(R.array.nivelConsciencia)[6]) &&
                                !simSedado.isShown()){
                            myAnimation.slideDownView(getApplicationContext(),simSedado);
                        }
                        else {
                            if (simSedado.isShown())
                                myAnimation.slideUpView(getApplicationContext(), simSedado);
                        }
                        rassSelection=-1;
                        rassTextViewCAMICU.setText("");
                        rassTextView.setText("");
                        caracteristica1CAMICURadioGroup.clearCheck();
                        camIcuTextView.setText("");
                        ((RadioGroup) findViewById(R.id.inatencaoRadioGroup)).clearCheck();
                        ((RadioGroup) findViewById(R.id.pensamentoRadioGroup)).clearCheck();
                        if(caracteristica2CAMICU.isShown())
                            caracteristica2CAMICU.setVisibility(View.GONE);
                        if(caracteristica3CAMICU.isShown())
                            caracteristica3CAMICU.setVisibility(View.GONE);
                        if(caracteristica4CAMICU.isShown())
                            caracteristica4CAMICU.setVisibility(View.GONE);
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void ramsayOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.Ramsay);

        //list of items
        final String[] options = getResources().getStringArray(R.array.ramsay);
        Arrays.sort(options);
        builder.setSingleChoiceItems(options, ramsaySelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ramsayTextView.setText(options[which]);
                        ramsayTextView.setVisibility(View.VISIBLE);
                        ramsaySelection =which;
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void rassOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.RASS);

        //list of items
        final String[] options = getResources().getStringArray(R.array.rass);
        builder.setSingleChoiceItems(options, rassSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rassTextView.setText(options[which]);
                        rassTextViewCAMICU.setText(options[which]);
                        rassTextView.setVisibility(View.VISIBLE);
                        rassTextViewCAMICU.setVisibility(View.VISIBLE);
                        rassSelection=which;
                        if(rassSelection!=4 && !simSedado.isShown() && caracteristica3CAMICU.isShown())
                            camIcuTextView.setText("Há delirium");
                        if(rassSelection==4 && !simSedado.isShown() && caracteristica3CAMICU.isShown())
                            caracteristica4CAMICU.setVisibility(View.VISIBLE);
                        if(rassSelection!=4 && !simSedado.isShown() && caracteristica4CAMICU.isShown())
                            caracteristica4CAMICU.setVisibility(View.GONE);
                        if(((RadioGroup)findViewById(R.id.inatencaoRadioGroup)).getCheckedRadioButtonId()!=-1 && simSedado.isShown()) {
                            ((RadioGroup) findViewById(R.id.inatencaoRadioGroup)).clearCheck();
                            camIcuTextView.setText("");
                        }
                        if(rassSelection!=4 && caracteristica4CAMICU.isShown()) {
                            camIcuTextView.setText("");
                            caracteristica4CAMICU.setVisibility(View.GONE);
                        }
                        if(rassSelection==0 || rassSelection>=8){
                            camIcuTextView.setText("Avaliação não permitida, pois RASS maior que +4 ou menor que -4.");
                            deliriumItensLayout.setVisibility(View.GONE);
                        }
                        else if(camIcuTextView.getText().toString().equals("Avaliação não permitida, pois RASS maior que +4 ou menor que -4.")) {
                            camIcuTextView.setText("");
                            deliriumItensLayout.setVisibility(View.VISIBLE);
                            caracteristica2CAMICU.setVisibility(View.GONE);
                            caracteristica3CAMICU.setVisibility(View.GONE);
                            caracteristica4CAMICU.setVisibility(View.GONE);
                            ((RadioGroup) findViewById(R.id.inatencaoRadioGroup)).clearCheck();
                            ((RadioGroup) findViewById(R.id.pensamentoRadioGroup)).clearCheck();
                            caracteristica1CAMICURadioGroup.clearCheck();


                        }
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void respostaMotoraOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.RespostaMotora);

        //list of items
        final String[] options = getResources().getStringArray(R.array.respostaMotora);
        Arrays.sort(options);
        builder.setSingleChoiceItems(options, respostaMotoraSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        respostaMotoraTextView.setText(options[which]);
                        respostaMotoraTextView.setVisibility(View.VISIBLE);
                        if(respostaMotoraSelection!=-1 && respostaMotoraSelection!=which){
                            escalaDeGlasgowSoma = (escalaDeGlasgowSoma - (respostaMotoraSelection+1)) + which+1;
                        }
                        else
                            escalaDeGlasgowSoma+=which+1;
                        respostaMotoraSelection=which;
                        escalaDeGlasgowTextView.setText(Integer.toString(escalaDeGlasgowSoma));
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void respostaVerbalOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.RespostaVerbal);

        //list of items
        final String[] options = getResources().getStringArray(R.array.respostaVerbal);
        Arrays.sort(options);
        builder.setSingleChoiceItems(options, respostaVerbalSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        respostaVerbalTextView.setText(options[which]);
                        respostaVerbalTextView.setVisibility(View.VISIBLE);
                        if(respostaVerbalSelection!=-1 && respostaVerbalSelection!=which){
                            escalaDeGlasgowSoma = (escalaDeGlasgowSoma - (respostaVerbalSelection+1)) + which+1;
                        }
                        else
                            escalaDeGlasgowSoma+=which+1;
                        respostaVerbalSelection=which;
                        escalaDeGlasgowTextView.setText(Integer.toString(escalaDeGlasgowSoma));
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void aberturaOcularOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.AberturaOcular);

        //list of items
        final String[] options = getResources().getStringArray(R.array.aberturaOcular);
        Arrays.sort(options);
        builder.setSingleChoiceItems(options, aberturaOcularSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        aberturaOcularTextView.setText(options[which]);
                        aberturaOcularTextView.setVisibility(View.VISIBLE);
                        if(aberturaOcularSelection!=-1 && aberturaOcularSelection!=which){
                            escalaDeGlasgowSoma = (escalaDeGlasgowSoma - (aberturaOcularSelection+1)) + which+1;
                        }
                        else
                            escalaDeGlasgowSoma+=which+1;
                        aberturaOcularSelection=which;
                        escalaDeGlasgowTextView.setText(Integer.toString(escalaDeGlasgowSoma));
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void avaliacaoPupilarOnClick(View view){
        StringBuilder avaliacaoString = new StringBuilder();
        if(avaliacaoPupilarItensLayout.isShown()){
            myAnimation.slideUpView(getApplicationContext(), avaliacaoPupilarItensLayout);
        }
        else
            myAnimation.slideDownView(getApplicationContext(), avaliacaoPupilarItensLayout);
    }

    public void escalaGlasgowOnClick(View view){
        if(escalaGlasgowItensLayout.isShown())
            myAnimation.slideUpView(getApplicationContext(),escalaGlasgowItensLayout);
        else
            myAnimation.slideDownView(getApplicationContext(),escalaGlasgowItensLayout);
    }

    public void deliriumOnClick(View view){

        if (deliriumItensLayout.isShown())
            myAnimation.slideUpView(getApplicationContext(), deliriumItensLayout);
        else
            myAnimation.slideDownView(getApplicationContext(), deliriumItensLayout);
    }

    public void deficitMotorOnClick(View view){
        if(deficitMotorItensLayout.isShown())
            myAnimation.slideUpView(getApplicationContext(),deficitMotorItensLayout);
        else {
            if(checkboxDeficitMotor.isChecked())
                myAnimation.slideDownView(getApplicationContext(), deficitMotorItensLayout);
        }
    }

    public void simetriaOnClick(View view){
        switch(view.getId()) {
            case R.id.anisocoricas:
                if(!diferencaPupilaLayout.isShown())
                    myAnimation.slideDownView(NeurologicoActivity.this,diferencaPupilaLayout);
                break;
            case R.id.isocoricas:
                if(diferencaPupilaLayout.isShown())
                    myAnimation.slideUpView(NeurologicoActivity.this,diferencaPupilaLayout);
                break;
        }
    }

}
