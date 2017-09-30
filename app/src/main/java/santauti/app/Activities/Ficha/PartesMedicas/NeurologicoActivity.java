package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Adapters.Ficha.Neurologico.NeurologicoAdapter;
import santauti.app.Adapters.Ficha.Neurologico.NeurologicoAdapterModel;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Neurologico.Neurologico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 13-May-17.
 */

public class NeurologicoActivity extends GenericoActivity {
    private Realm realm;
    private MyAnimation myAnimation;
    private List<NeurologicoAdapterModel> neurologicoAdapterModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NeurologicoAdapter neurologicoAdapter;
    private int escalaDeGlasgowSoma=0;
    private int nivelConscienciaSelection=-1;
    private int rassSelection=-1;
    private int ramsaySelection=-1;
    private int respostaMotoraSelection=-1;
    private int respostaVerbalSelection=-1;
    private int aberturaOcularSelection=-1;
    private View avaliacaoPupilarItensLayout;
    private TextView nivelConscienciaTextView;
    private TextView rassTextView;
    private TextView ramsayTextView;
    private TextView escalaDeGlasgowTextView;
    private TextView aberturaOcularTextView;
    private TextView respostaVerbalTextView;
    private TextView respostaMotoraTextView;
    private TextView flutuacaoHint1;
    private TextView flutuacaoHint2;
    private TextView pensamentoDesorganizadoHint1;
    private TextView pensamentoDesorganizadoHint2;
    private TextView inatencaoHint1,textoAjudaPensamento,textoAjudaInatencao,textoAjudaFlutuacao;
    private TextView inatencaoHint2;
    private View simSedado,comaLayout,diferencaPupilaLayout,escalaGlasgowItensLayout,
            deliriumItensLayout,deficitMotorItensLayout,temporoEspacialLayout,desorientadoLayout;
    private CheckBox checkboxDeficitMotor,checkboxFlutuacao,checkBoxInatencao,checkBoxPensamento;
    private AppCompatRadioButton paresiaMSE,plegiaMSE,orientado,desorientado;
    private boolean checked=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neurologico);
        setToolbar(getString(R.string.Neurologico));

        /******************************VARIAVEIS LAYOUTS*************************************/
        simSedado = findViewById(R.id.sedado_sim_layout);
        comaLayout = findViewById(R.id.coma_layout);
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
        flutuacaoHint1 = (TextView)findViewById(R.id.flutuacaoHint1);
        flutuacaoHint2 = (TextView)findViewById(R.id.flutuacaoHint2);
        pensamentoDesorganizadoHint1 = (TextView)findViewById(R.id.pensamentoDesorganizadoHint1);
        pensamentoDesorganizadoHint2 = (TextView)findViewById(R.id.pensamentoDesorganizadoHint2);
        inatencaoHint1 = (TextView)findViewById(R.id.inatencaoHint1);
        inatencaoHint2 = (TextView)findViewById(R.id.inatencaoHint2);
        temporoEspacialLayout = findViewById(R.id.temporoEspacialLayout);
        desorientadoLayout = findViewById(R.id.desorientadoLayout);
        avaliacaoPupilarItensLayout = findViewById(R.id.avaliacaoPupilarItensLayout);
        textoAjudaFlutuacao = (TextView)findViewById(R.id.textoAjudaFlutuacao);
        textoAjudaInatencao = (TextView)findViewById(R.id.textoAjudaInatencao);
        textoAjudaPensamento = (TextView)findViewById(R.id.textoAjudaPensamento);
        /******************************VARIAVEIS LAYOUTS*************************************/

        /******************************VARIAVEIS RADIOBUTTON*********************************/
        paresiaMSE = (AppCompatRadioButton)findViewById(R.id.paresiaMSE);
        plegiaMSE = (AppCompatRadioButton)findViewById(R.id.plegiaMSE);
        orientado = (AppCompatRadioButton)findViewById(R.id.orientado);
        desorientado = (AppCompatRadioButton)findViewById(R.id.desorientado);

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
        checkboxFlutuacao = (CheckBox)findViewById(R.id.checkboxFlutuacao);
        /******************************VARIAVEIS CHECKBOX*******************************/


//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        neurologicoAdapter = new NeurologicoAdapter(this,neurologicoAdapterModelList);
//        recyclerView.setAdapter(neurologicoAdapter);

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
    }



    private void setTextFromCheckBox(CheckBox checkbox,TextView textview){
        if(checkbox.isChecked())
            textview.setText(getString(R.string.Sim));
        else
            textview.setText(getString(R.string.Nao));
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
//        boolean nivelConscienciaTextView=false,avaliacaoPupilar=false,sedadoSim=false,sedadoNao=false,deficitMotor=false,opcional=false;
//        realm.beginTransaction();
//        int i=0;
//
//        Neurologico neurologico = realm.createObject(Neurologico.class);
//        if(!nivelConscienciaSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
//            neurologico.setNivelConsciencia(nivelConscienciaSpinner.getSelectedItem().toString());
//            nivelConscienciaTextView=true;
//        }
//
//        /**
//         * Avaliaçao Pupilar
//         */
//        if(!pupilaSimetriaSpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
//                !pupilaTamanhoSpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
//                !pupilaReatividadeLuzSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
//            if(pupilaSimetriaSpinner.getSelectedItem().toString().equals("Anisocóricas") &&
//                    !diferencaPupilarSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
//                neurologico.setDiferencaPupilar(diferencaPupilarSpinner.getSelectedItem().toString());
//            }
//            neurologico.setSimetriaPupila(pupilaSimetriaSpinner.getSelectedItem().toString());
//            neurologico.setTamanhoPupila(pupilaTamanhoSpinner.getSelectedItem().toString());
//            neurologico.setReatividadeLuzPupila(pupilaReatividadeLuzSpinner.getSelectedItem().toString());
//            avaliacaoPupilar=true;
//        }
//
//
//        if(sedado_sim.isChecked()){
//            if(!ramsaySpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
//                    !rassSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
//                Sedado sedado = realm.createObject(Sedado.class);
//                neurologico.setIsSedado(true);
//                sedado.setRamsay(ramsaySpinner.getSelectedItem().toString());
//                sedado.setRass(rassSpinner.getSelectedItem().toString());
//                sedadoSim=true;
//                neurologico.setSedado(sedado);
//                //Fzer lista de sedativos
//                for(NeurologicoAdapterModel h : neurologicoAdapterModelList){
//                    Sedativo sedativo = realm.createObject(Sedativo.class);
//                    sedativo.setDoseSedativo(h.getDoseSedativo());
//                    sedativo.setTipoSedativo(h.getTipoSedativo());
//                    sedado.getSedativo().add(sedativo);
//                }
//            }
//
//        }
//        if(sedado_nao.isChecked()){
//            if(!aberturaOcularSpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
//                    !respostaMotoraSpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
//                    !respostaVerbalSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
//                neurologico.setIsSedado(false);
//                NaoSedado naoSedado = realm.createObject(NaoSedado.class);
//                naoSedado.setAberturaOcular(adapterAberturaOcular.getPosition(aberturaOcularSpinner.getSelectedItem().toString()));
//                naoSedado.setRespostaMotora(adapterRespostaMotora.getPosition(respostaMotoraSpinner.getSelectedItem().toString()));
//                naoSedado.setRespostaVerbal(adapterRespostaVerbal.getPosition(respostaVerbalSpinner.getSelectedItem().toString()));
//                sedadoNao=true;
//                neurologico.setNaoSedado(naoSedado);
//            }
//        }
//
//        if(deficitMotorSpinner.getSelectedItem().toString().equals("Presente")){
//            if(!tipoDecifitSpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
//                    !ladoDeficitSpinner.getSelectedItem().toString().equals(defaultSpinnerString)){
//                neurologico.setDeficitMotor(true);
//                neurologico.setTipoDecifit(tipoDecifitSpinner.getSelectedItem().toString());
//                neurologico.setLadoDeficit(ladoDeficitSpinner.getSelectedItem().toString());
//                deficitMotor=true;
//            }
//        }
//
//        if(deficitMotorSpinner.getSelectedItem().toString().equals("Ausente")) {
//            neurologico.setDeficitMotor(false);
//            deficitMotor=true;
//        }
//
//        if(nivelConscienciaTextView && avaliacaoPupilar && (sedadoSim || sedadoNao) && deficitMotor) {
//            Ficha r = getProperFicha();
//            r.setNeurologico(neurologico);
//            realm.copyToRealmOrUpdate(r);
//            changeCardColor();
//        }
//        realm.commitTransaction();
    }

    public void addSedativo(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(NeurologicoActivity.this);
        builder.setTitle("Adicionar Sedativo");

        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.neurologico_dialog_sedativo,null);
        dialogView.requestFocus();
        final TextInputEditText doseSedativo = (TextInputEditText) dialogView.findViewById(R.id.doseSedativo);
        final TextInputEditText tipoSedativo = (TextInputEditText) dialogView.findViewById(R.id.tipoSedativo);


        builder.setView(dialogView);
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!isTextInputEditTextEmpty(tipoSedativo) && !isTextInputEditTextEmpty(doseSedativo))
                    addDataFromDialogIntoAdapter(tipoSedativo.getText().toString(),Integer.parseInt(doseSedativo.getText().toString()));
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        final AlertDialog dialog = builder.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
    }

    private void addDataFromDialogIntoAdapter(String droga,int dose){
        if(!droga.equals(defaultSpinnerString)){
            NeurologicoAdapterModel neurologicoAdapterModel = new NeurologicoAdapterModel(droga,dose);
            neurologicoAdapterModelList.add(neurologicoAdapterModel);
            neurologicoAdapter.notifyItemInserted(neurologicoAdapter.getItemCount()-1);
            neurologicoAdapter.notifyDataSetChanged();
        }
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
//                        if(options[which].equals(getResources().getStringArray(R.array.nivelConsciencia)[5]) &&
//                                !comaLayout.isShown()){
//                            if(simSedado.isShown())
//                                simSedado.setVisibility(View.GONE);
//                            myAnimation.slideDownView(getApplicationContext(),comaLayout);
//                        }
                        else{
//                            if(comaLayout.isShown())
//                                myAnimation.slideUpView(getApplicationContext(),comaLayout);
                            if(simSedado.isShown())
                                myAnimation.slideUpView(getApplicationContext(),simSedado);
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
        Arrays.sort(options);
        builder.setSingleChoiceItems(options, rassSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rassTextView.setText(options[which]);
                        rassTextView.setVisibility(View.VISIBLE);
                        rassSelection =which;
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
//        if(avaliacaoPupilarItensLayout.isShown()) {
//            myAnimation.slideUpView(getApplicationContext(), avaliacaoPupilarItensLayout);
//            if(tamanhoPupilaTextView.getText().toString().length()>0)
//                avaliacaoString.append("Tamanho: ").append(tamanhoPupilaTextView.getText().toString());
//            if(simetriaPupilaTextView.getText().toString().length()>0)
//                avaliacaoString.append(", Simetria: ").append(simetriaPupilaTextView.getText().toString());
//            if(simetriaPupilaTextView.getText().equals(getResources().getStringArray(R.array.simetriaPupila)[1]))
//                avaliacaoString.append(", Diferença: ").append(diferencaPupilaTextView.getText().toString());
//            if(reatividadeLuzTextView.getText().length()>0)
//                avaliacaoString.append(", Reatividade a Luz: ").append(reatividadeLuzTextView.getText().toString());
//            if(avaliacaoString.length()!=0) {
//                avaliacaoPupilarTextView.setText(avaliacaoString);
//                avaliacaoPupilarTextView.setVisibility(View.VISIBLE);
//            }
//        }
//        else {
//            avaliacaoPupilarTextView.setVisibility(View.GONE);
//            myAnimation.slideDownView(getApplicationContext(), avaliacaoPupilarItensLayout);
//        }
    }

    public void escalaGlasgowOnClick(View view){
        if(escalaGlasgowItensLayout.isShown())
            myAnimation.slideUpView(getApplicationContext(),escalaGlasgowItensLayout);
        else
            myAnimation.slideDownView(getApplicationContext(),escalaGlasgowItensLayout);
    }

    public void flutuacaoOnClick(View view){
        if(flutuacaoHint1.isShown() && flutuacaoHint2.isShown()){
            myAnimation.slideUpView(getApplicationContext(),flutuacaoHint1);
            myAnimation.slideUpView(getApplicationContext(),flutuacaoHint2);
            myAnimation.slideUpView(getApplicationContext(),textoAjudaFlutuacao);
        }
        else{
            myAnimation.slideDownView(getApplicationContext(),flutuacaoHint1);
            myAnimation.slideDownView(getApplicationContext(),flutuacaoHint2);
            myAnimation.slideDownView(getApplicationContext(),textoAjudaFlutuacao);
        }
    }

    public void pensamentoDesorganizadoOnClick(View view){
        if(pensamentoDesorganizadoHint1.isShown() && pensamentoDesorganizadoHint2.isShown()){
            myAnimation.slideUpView(getApplicationContext(),pensamentoDesorganizadoHint1);
            myAnimation.slideUpView(getApplicationContext(),pensamentoDesorganizadoHint2);
            myAnimation.slideUpView(getApplicationContext(),textoAjudaPensamento);
        }
        else{
            myAnimation.slideDownView(getApplicationContext(),textoAjudaPensamento);
            myAnimation.slideDownView(getApplicationContext(),pensamentoDesorganizadoHint1);
            myAnimation.slideDownView(getApplicationContext(),pensamentoDesorganizadoHint2);
        }
    }

    public void inatencaoOnClick(View view){
        if(inatencaoHint1.isShown() && inatencaoHint2.isShown()){
            myAnimation.slideUpView(getApplicationContext(),textoAjudaInatencao);
            myAnimation.slideUpView(getApplicationContext(),inatencaoHint1);
            myAnimation.slideUpView(getApplicationContext(),inatencaoHint2);
        }
        else{
            myAnimation.slideDownView(getApplicationContext(),textoAjudaInatencao);
            myAnimation.slideDownView(getApplicationContext(),inatencaoHint1);
            myAnimation.slideDownView(getApplicationContext(),inatencaoHint2);
        }
    }

    public void deliriumOnClick(View view){
        if(deliriumItensLayout.isShown())
            myAnimation.slideUpView(getApplicationContext(),deliriumItensLayout);
        else
            myAnimation.slideDownView(getApplicationContext(),deliriumItensLayout);
    }

    public void deficitMotorOnClick(View view){
        if(deficitMotorItensLayout.isShown())
            myAnimation.slideUpView(getApplicationContext(),deficitMotorItensLayout);
        else {
            if(checkboxDeficitMotor.isChecked())
                myAnimation.slideDownView(getApplicationContext(), deficitMotorItensLayout);
        }
    }

    public void temporoEspacialOnCLick(View view){
        switch(view.getId()) {
            case R.id.desorientado:
                if(!desorientadoLayout.isShown())
                    myAnimation.slideDownView(getApplicationContext(),desorientadoLayout);
                break;
            case R.id.orientado:
                if(desorientadoLayout.isShown())
                    myAnimation.slideUpView(getApplicationContext(),desorientadoLayout);
                break;
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
