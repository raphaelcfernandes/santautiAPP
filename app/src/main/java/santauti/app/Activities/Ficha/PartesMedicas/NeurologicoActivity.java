package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
    private int nivelConscienciaSelection =-1, rassSelection=-1,ramsaySelection=-1;
    private TextView nivelConscienciaTextView,rassTextView,ramsayTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neurologico);
        setToolbar(getString(R.string.Neurologico));

        /******************************VARIAVEIS LAYOUTS*************************************/
        nivelConscienciaTextView = (TextView)findViewById(R.id.nivelCosciencia);
        rassTextView = (TextView)findViewById(R.id.rassTextView);
        ramsayTextView = (TextView)findViewById(R.id.ramsayTextView);
        /******************************VARIAVEIS LAYOUTS*************************************/

        /******************************VARIAVEIS RADIOBUTTON*********************************/
        /******************************VARIAVEIS RADIOBUTTON*********************************/

        /******************************VARIAVEIS TOGGLEBUTTON*******************************/
        /******************************VARIAVEIS TOGGLEBUTTON*******************************/




//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        neurologicoAdapter = new NeurologicoAdapter(this,neurologicoAdapterModelList);
//        recyclerView.setAdapter(neurologicoAdapter);

        realm = Realm.getDefaultInstance();
        myAnimation = new MyAnimation();

        prepareNavigationButtons();
//        prepareNeurologicoSpinners();


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
        String[] aberturaOcular = {defaultSpinnerString,"1 - Nenhuma","2 - À dor","3 - À voz","4 - Espontânea"};
        String[] respostaVerbal = {defaultSpinnerString,"1 - Nenhuma","2 - Palavras incompreensiveis","3 - Palavras inapropriadas","4 - Confusa","5 - Orientada"};
        String[] respostaMotora = {defaultSpinnerString,"1 - Nenhuma","2 - Extensão anormal","3 - Flexão anormal","4 - Movimento de retirada","5 - Localiza dor","6 - Obedece comandos"};
        String[] pupilaReatividadeLuz = {defaultSpinnerString,"RFM+","RFM-"};
        String[] pupilaSimetria = {defaultSpinnerString,"Isocóricas","Anisocóricas"};
        String[] pupilaTamanho = {defaultSpinnerString,"Miose","Normal","Midríase"};
        String[] diferencaPupila = {defaultSpinnerString,"Esquerda > Direita","Direita > Esquerda"};
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
                        nivelConscienciaSelection =which;
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
}
