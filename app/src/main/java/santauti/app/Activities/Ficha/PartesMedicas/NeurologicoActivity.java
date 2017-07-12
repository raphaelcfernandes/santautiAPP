package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Adapters.Ficha.Neurologico.NeurologicoAdapter;
import santauti.app.Adapters.Ficha.Neurologico.NeurologicoAdapterModel;
import santauti.app.Animation.MyAnimation;
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
    private int aberturaOcularSoma=0,nivelConscienciaSelection=-1, rassSelection=-1,
            ramsaySelection=-1,respostaMotoraSelection=-1,respostaVerbalSelection=-1,
            aberturaOcularSelection=-1,tamanhoPupilaSelection=-1,simetriaPupilaSelection=-1,reatividadeLuzSelection=-1,
            diferencaPupilaSelection=-1;
    private TextView nivelConscienciaTextView,rassTextView,ramsayTextView,
            escalaDeGlasgowTextView,aberturaOcularTextView,respostaVerbalTextView,respostaMotoraTextView,
            tamanhoPupilaTextView,simetriaPupilaTextView,reatividadeLuzTextView,diferencaPupilaTextView;
    private View simSedado,comaLayout,diferencaPupilaLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neurologico);
        setToolbar(getString(R.string.Neurologico));

        /******************************VARIAVEIS LAYOUTS*************************************/
        simSedado = findViewById(R.id.sedado_sim_layout);
        comaLayout = findViewById(R.id.coma_layout);
        diferencaPupilaLayout = findViewById(R.id.diferencaPupilaLayout);
        nivelConscienciaTextView = (TextView)findViewById(R.id.nivelCosciencia);
        rassTextView = (TextView)findViewById(R.id.rassTextView);
        ramsayTextView = (TextView)findViewById(R.id.ramsayTextView);
        escalaDeGlasgowTextView = (TextView)findViewById(R.id.escalaDeGlasgow);
        aberturaOcularTextView = (TextView)findViewById(R.id.aberturaOcular);
        respostaVerbalTextView = (TextView)findViewById(R.id.respostaVerbal);
        respostaMotoraTextView = (TextView)findViewById(R.id.respostaMotora);
        tamanhoPupilaTextView = (TextView)findViewById(R.id.tamanhoPupila);
        simetriaPupilaTextView = (TextView)findViewById(R.id.simetriaPupila);
        reatividadeLuzTextView = (TextView)findViewById(R.id.reatividadeLuz);
        diferencaPupilaTextView = (TextView)findViewById(R.id.diferencaPupila);
        /******************************VARIAVEIS LAYOUTS*************************************/

        /******************************VARIAVEIS RADIOBUTTON*********************************/
        /******************************VARIAVEIS RADIOBUTTON*********************************/

        /******************************VARIAVEIS TOGGLEBUTTON*******************************/
        /******************************VARIAVEIS TOGGLEBUTTON*******************************/


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        neurologicoAdapter = new NeurologicoAdapter(this,neurologicoAdapterModelList);
        recyclerView.setAdapter(neurologicoAdapter);

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

    private void prepareNeurologicoSpinners(){
        String[] deficitMotor = {defaultSpinnerString,"Presente","Ausente"};
        String[] deficitTipo = {defaultSpinnerString,"Paresia","Plegia"};
        String[] deficitLado = {defaultSpinnerString,"Esquerdo","Direito","Ambos os lados"};
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
                if(!isTextInpudEditTextEmpty(tipoSedativo) && !isTextInpudEditTextEmpty(doseSedativo))
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
        Arrays.sort(options);
        builder.setSingleChoiceItems(options, nivelConscienciaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nivelConscienciaTextView.setText(options[which]);
                        nivelConscienciaTextView.setVisibility(View.VISIBLE);
                        nivelConscienciaSelection=which;
                        if(options[which].equals(getResources().getStringArray(R.array.nivelConsciencia)[6]) &&
                                !simSedado.isShown()){
                            if(comaLayout.isShown())
                                comaLayout.setVisibility(View.GONE);
                            myAnimation.slideDownView(getApplicationContext(),simSedado);
                        }
                        if(options[which].equals(getResources().getStringArray(R.array.nivelConsciencia)[5]) &&
                                !comaLayout.isShown()){
                            if(simSedado.isShown())
                                simSedado.setVisibility(View.GONE);
                            myAnimation.slideDownView(getApplicationContext(),comaLayout);
                        }
                        else{
                            if(comaLayout.isShown())
                                myAnimation.slideUpView(getApplicationContext(),comaLayout);
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
                        respostaMotoraSelection=which;
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
                        respostaVerbalSelection=which;
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
                        aberturaOcularSelection=which;
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

    public void tamanhoPupilaOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.TamanhoPupila);

        //list of items
        final String[] options = getResources().getStringArray(R.array.tamanhoPupila);
        Arrays.sort(options);
        builder.setSingleChoiceItems(options, tamanhoPupilaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tamanhoPupilaTextView.setText(options[which]);
                        tamanhoPupilaTextView.setVisibility(View.VISIBLE);
                        tamanhoPupilaSelection=which;
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

    public void simetriaPupilaOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.SimetriaPupila);

        //list of items
        final String[] options = getResources().getStringArray(R.array.simetriaPupila);
        Arrays.sort(options);
        builder.setSingleChoiceItems(options, simetriaPupilaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        simetriaPupilaTextView.setText(options[which]);
                        simetriaPupilaTextView.setVisibility(View.VISIBLE);
                        simetriaPupilaSelection=which;
                        if(options[which].equals(getResources().getStringArray(R.array.simetriaPupila)[1]) &&
                                !diferencaPupilaLayout.isShown())
                            myAnimation.slideDownView(getApplicationContext(),diferencaPupilaLayout);
                        else{
                            if(diferencaPupilaLayout.isShown())
                                myAnimation.slideUpView(getApplicationContext(),diferencaPupilaLayout);
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

    public void diferencaPupilaOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.DiferencaPupilar);

        //list of items
        final String[] options = getResources().getStringArray(R.array.diferencaPupila);
        Arrays.sort(options);
        builder.setSingleChoiceItems(options, diferencaPupilaSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        diferencaPupilaTextView.setText(options[which]);
                        diferencaPupilaTextView.setVisibility(View.VISIBLE);
                        diferencaPupilaSelection=which;
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

    public void reatividadeLuzOnClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.ReatividadeALuzPupila);

        //list of items
        final String[] options = getResources().getStringArray(R.array.reatividadeALuz);
        Arrays.sort(options);
        builder.setSingleChoiceItems(options, reatividadeLuzSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reatividadeLuzTextView.setText(options[which]);
                        reatividadeLuzTextView.setVisibility(View.VISIBLE);
                        reatividadeLuzSelection=which;
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


}
