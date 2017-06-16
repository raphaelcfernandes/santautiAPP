package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Adapters.Ficha.Hemodinamico.HemodinamicoAdapter;
import santauti.app.Adapters.Ficha.Hemodinamico.HemodinamicoModel;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Hemodinamico.Hemodinamico;
import santauti.app.Model.Ficha.Hemodinamico.HemodinamicoOpcional;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 10-May-17.
 */

public class HemodinamicoActivity extends GenericoActivity {
    private Spinner ritmoSpinner, bulhasSpinner, drogasVasoativasSpinner;
    private RadioButton hemodinamico_opcional_sim,hemodinamico_opcional_nao;
    private View hemodinamico_opcional_layout,bulhas_layout;
    private boolean bulhasIsShown=false;
    private MyAnimation myAnimation;
    private List<HemodinamicoModel> hemodinamicoModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HemodinamicoAdapter hemodinamicoAdapter;
    private TextInputEditText frequenciaCardiaca,PAM,PVC,swan_ganz;
    private Realm realm;
    private Ficha ficha;
    private int spinnerPosition;
    private ArrayAdapter<String> adapterRitmo,adapterBulhas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hemodinamico);
        findViewById(R.id.hemodinamico_layout).requestFocus();
        setToolbar(getString(R.string.Hemodinamico));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        hemodinamicoAdapter = new HemodinamicoAdapter(this,hemodinamicoModelList);
        recyclerView.setAdapter(hemodinamicoAdapter);

        hemodinamicoAdapter.setOnItemClickListener(onItemClickListener);
        hemodinamico_opcional_nao = (RadioButton)findViewById(R.id.hemodinamico_opcional_nao);
        hemodinamico_opcional_sim = (RadioButton)findViewById(R.id.hemodinamico_opcional_sim);
        hemodinamico_opcional_layout = findViewById(R.id.hemodinamico_opcional_layout);
        hemodinamico_opcional_layout.setVisibility(View.GONE);
        bulhas_layout=findViewById(R.id.bulhas_layout);

        frequenciaCardiaca = (TextInputEditText)findViewById(R.id.hemodinamico_frequencia);
        PAM = (TextInputEditText)findViewById(R.id.pam);
        PVC = (TextInputEditText)findViewById(R.id.pvc);
        swan_ganz = (TextInputEditText)findViewById(R.id.swan_ganz);
        myAnimation = new MyAnimation();
        prepareHemodinamicoSpinners();
        realm=Realm.getDefaultInstance();
        ficha=getProperFicha();

        ritmoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (ritmoSpinner.getSelectedItem().toString().equals("Aritmico")) {
                    /*
                     * Se n houver dado salvo, ou se o dado salvo for diferente do que o usuario quer agora
                     * Caso esteja ritmico previamente salvo e usuario deseja escolher Aritmico
                     */
                    if(ficha.getHemodinamico()==null || !ficha.getHemodinamico().getRitmo().equals("Aritmico"))
                        prepareBulhasAritmicoSpinner();
                    if (!bulhasIsShown) {
                        bulhasIsShown = true;
                        myAnimation.slide_down(HemodinamicoActivity.this, bulhas_layout);
                    }
                } else if (ritmoSpinner.getSelectedItem().toString().equals("Ritmico")) {
                    /*
                     * Se n houver dado salvo, ou se o dado salvo for diferente do que o usuario quer agora
                     * Caso esteja ritmico previamente salvo e usuario deseja escolher Aritmico
                     */
                    if(ficha.getHemodinamico()==null || !ficha.getHemodinamico().getRitmo().equals("Ritmico"))
                        prepareBulhasRitmicoSpinner();
                    if (!bulhasIsShown) {
                        bulhasIsShown = true;
                        myAnimation.slide_down(HemodinamicoActivity.this, bulhas_layout);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if(ficha.getHemodinamico()!=null){
            if(ficha.getHemodinamico().getRitmo().equals("Ritmico")) {
                prepareBulhasRitmicoSpinner();
            }
            if(ficha.getHemodinamico().getRitmo().equals("Aritmico")) {
                prepareBulhasAritmicoSpinner();
            }
            spinnerPosition = adapterRitmo.getPosition(ficha.getHemodinamico().getRitmo());
            ritmoSpinner.setSelection(spinnerPosition);
            spinnerPosition = adapterBulhas.getPosition(ficha.getHemodinamico().getBulhas());
            bulhasSpinner.setSelection(spinnerPosition);
            myAnimation.slide_down(HemodinamicoActivity.this,bulhas_layout);
            frequenciaCardiaca.setText(String.valueOf(ficha.getHemodinamico().getFreqCardiaca()));
            if(ficha.getHemodinamico().getHemodinamicoOpcionals().size()>0){
                for(HemodinamicoOpcional h : ficha.getHemodinamico().getHemodinamicoOpcionals()){
                    HemodinamicoModel hemodinamicoModel = new HemodinamicoModel(h.getDroga(),h.getDose());
                    hemodinamicoModelList.add(hemodinamicoModel);
                    hemodinamicoAdapter.notifyItemInserted(hemodinamicoAdapter.getItemCount()-1);
                }
                hemodinamicoAdapter.notifyDataSetChanged();
            }
            if(ficha.getHemodinamico().isOpcionais()){
                hemodinamico_opcional_sim.setChecked(true);
                PAM.setText(String.valueOf(ficha.getHemodinamico().getPam()));
                swan_ganz.setText(String.valueOf(ficha.getHemodinamico().getSwan_ganz()));
                PVC.setText(String.valueOf(ficha.getHemodinamico().getPvc()));
                myAnimation.slide_down(this,hemodinamico_opcional_layout);
            }
        }
    }

    HemodinamicoAdapter.OnItemClickListener onItemClickListener = new HemodinamicoAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View view, int position) {//Editar Droga Vasoativa
            System.out.println(hemodinamicoModelList.get(position).getDose());
            System.out.println(hemodinamicoModelList.get(position).getDroga());
        }
    };

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
        boolean obrigatorios = false;
        ficha=getProperFicha();
        realm.beginTransaction();
        Hemodinamico hemodinamico = realm.createObject(Hemodinamico.class);
        if(!ritmoSpinner.getSelectedItem().toString().equals(defaultSpinnerString) &&
                !bulhasSpinner.getSelectedItem().toString().equals(defaultSpinnerString) && !isTextInpudEditTextEmpty(frequenciaCardiaca)) {
            obrigatorios = true;
            hemodinamico.setRitmo(ritmoSpinner.getSelectedItem().toString());
            hemodinamico.setBulhas(bulhasSpinner.getSelectedItem().toString());
            hemodinamico.setFreqCardiaca(Integer.parseInt(frequenciaCardiaca.getText().toString()));
        }
        if(hemodinamico_opcional_nao.isChecked())
            hemodinamico.setOpcionais(false);

        if(hemodinamico_opcional_sim.isChecked()) {
            if (!isTextInpudEditTextEmpty(PAM) && !isTextInpudEditTextEmpty(PVC) && !isTextInpudEditTextEmpty(swan_ganz))
            hemodinamico.setOpcionais(true);
            hemodinamico.setPam(getIntegerFromTextInputEditText(PAM));
            hemodinamico.setPvc(getIntegerFromTextInputEditText(PVC));
            hemodinamico.setSwan_ganz(getIntegerFromTextInputEditText(swan_ganz));
        }

        for(HemodinamicoModel h : hemodinamicoModelList){
            HemodinamicoOpcional hemodinamicoOpcional = realm.createObject(HemodinamicoOpcional.class);
            hemodinamicoOpcional.setDose(h.getDose());
            hemodinamicoOpcional.setDroga(h.getDroga());
            hemodinamico.getHemodinamicoOpcionals().add(hemodinamicoOpcional);
        }

        if(obrigatorios) {
            Ficha r = getProperFicha();
            r.setHemodinamico(hemodinamico);
            realm.copyToRealmOrUpdate(r);
            changeCardColor();
        }
        realm.commitTransaction();
    }

    private void prepareBulhasRitmicoSpinner(){
        String[] bulhas = {defaultSpinnerString,"Sinusal","Bradicardico","Taquicardico"};

        bulhasSpinner = (Spinner) findViewById(R.id.hemodinamico_bulhas);
        adapterBulhas = new ArrayAdapter<String>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, bulhas){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        bulhasSpinner.setAdapter(adapterBulhas);
    }

    private void prepareBulhasAritmicoSpinner(){
        String[] bulhas = {defaultSpinnerString,"Fibrilação Atrial","Flutter Atrial"};

        bulhasSpinner = (Spinner) findViewById(R.id.hemodinamico_bulhas);
        adapterBulhas = new ArrayAdapter<String>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, bulhas){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        bulhasSpinner.setAdapter(adapterBulhas);
    }

    private void prepareHemodinamicoSpinners(){
        String[] tiposRitmos = {defaultSpinnerString,"Ritmico","Aritmico"};

        ritmoSpinner = (Spinner) findViewById(R.id.hemodinamico_ritmo);
        adapterRitmo = new ArrayAdapter<String>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, tiposRitmos) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        ritmoSpinner.setAdapter(adapterRitmo);
    }

    public void hemodinamicoOpcionalOnRadioButtonClicked(View view){
        switch(view.getId()) {
            case R.id.hemodinamico_opcional_sim:
                myAnimation.slide_down(HemodinamicoActivity.this,hemodinamico_opcional_layout);
                break;
            case R.id.hemodinamico_opcional_nao:
                myAnimation.slide_up(HemodinamicoActivity.this,hemodinamico_opcional_layout);
                break;
        }
    }

    public void addDrogaVasoativa(View view) {
        String[] drogasVasoativaString = {defaultSpinnerString,"Dobutamina","Dopamina","Nitroprussiato de Sodio","Nitroglicerina","Milrinona","Noradrenalina","Adrenalina"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(HemodinamicoActivity.this);
        builder.setTitle("Adicionar Droga Vasoativa");

        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.hemodinamico_dialog,null);
        dialogView.requestFocus();
        final TextInputEditText doseDroga = (TextInputEditText) dialogView.findViewById(R.id.dose);

        drogasVasoativasSpinner = (Spinner)dialogView.findViewById(R.id.drogaVasoativa);
        ArrayAdapter<String> adapterDrogaVasoativas = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, drogasVasoativaString) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        drogasVasoativasSpinner.setAdapter(adapterDrogaVasoativas);

        builder.setView(dialogView);
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addDataFromDialogIntoAdapter(drogasVasoativasSpinner.getSelectedItem().toString(),Integer.parseInt(doseDroga.getText().toString()));
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
            HemodinamicoModel h = new HemodinamicoModel(droga,dose);
            hemodinamicoModelList.add(h);
            hemodinamicoAdapter.notifyItemInserted(hemodinamicoAdapter.getItemCount()-1);
            hemodinamicoAdapter.notifyDataSetChanged();
        }
    }
}
