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
    private Spinner ritmo,bulhas,drogasVasoativas;
    private RadioButton hemodinamico_opcional_sim,hemodinamico_opcional_nao,drogaVasoativa_sim,drogaVasoativa_nao;
    private View hemodinamico_opcional_layout,bulhas_layout,doseDrogaVasoativa;
    private boolean bulhasIsShown=false;
    private MyAnimation myAnimation;
    private List<HemodinamicoModel> hemodinamicoModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HemodinamicoAdapter hemodinamicoAdapter;
    private TextInputEditText frequenciaCardiaca,PAM,PVC,swan_ganz;
    private Realm realm;
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

        realm=Realm.getDefaultInstance();

        myAnimation = new MyAnimation();

        prepareHemodinamicoSpinners();

        ritmo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(ritmo.getSelectedItem().toString().equals("Aritmico")) {
                    prepareBulhasAritmicoSpinner();
                    if(!bulhasIsShown) {
                        bulhasIsShown = true;
                        myAnimation.slide_down(HemodinamicoActivity.this,bulhas_layout);
                    }
                }
                else if(ritmo.getSelectedItem().toString().equals("Ritmico")) {
                    prepareBulhasRitmicoSpinner();
                    if(!bulhasIsShown) {
                        bulhasIsShown = true;
                        myAnimation.slide_down(HemodinamicoActivity.this,bulhas_layout);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        populate();
    }

    HemodinamicoAdapter.OnItemClickListener onItemClickListener = new HemodinamicoAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View view, int position) {
            System.out.println(hemodinamicoModelList.get(position).getDose());
            System.out.println(hemodinamicoModelList.get(position).getDroga());
        }
    };

    private void populate(){
        HemodinamicoModel h = new HemodinamicoModel("1",1);
        hemodinamicoModelList.add(h);
        HemodinamicoModel h1 = new HemodinamicoModel("2",2);
        hemodinamicoModelList.add(h1);
        HemodinamicoModel h2 = new HemodinamicoModel("3",3);
        hemodinamicoModelList.add(h2);
        hemodinamicoAdapter.notifyDataSetChanged();
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
        boolean opcionais = false;

        if(!ritmo.getSelectedItem().toString().equals(defaultSpinnerString) &&
                !bulhas.getSelectedItem().toString().equals(defaultSpinnerString) && !isTextInpudEditTextEmpty(frequenciaCardiaca))
            obrigatorios=true;
        if(hemodinamico_opcional_sim.isChecked())
            if(!isTextInpudEditTextEmpty(PAM) && !isTextInpudEditTextEmpty(PVC) && !isTextInpudEditTextEmpty(swan_ganz))
                opcionais = true;
        if(obrigatorios) {
            Hemodinamico hemodinamico = realm.createObject(Hemodinamico.class);
            HemodinamicoOpcional hemodinamicoOpcional = realm.createObject(HemodinamicoOpcional.class);
            hemodinamico.setRitmo(ritmo.getSelectedItem().toString());
            hemodinamico.setBulhas(bulhas.getSelectedItem().toString());
            hemodinamico.setFreqCardiaca(Integer.parseInt(frequenciaCardiaca.getText().toString()));
            if(opcionais){
                hemodinamico.setPam(getIntegerFromTextInputEditText(PAM));
                hemodinamico.setPvc(getIntegerFromTextInputEditText(PVC));
                hemodinamico.setSwan_ganz(getIntegerFromTextInputEditText(swan_ganz));
            }
            for(HemodinamicoModel h : hemodinamicoModelList){
                hemodinamicoOpcional.setDose(h.getDose());
                hemodinamicoOpcional.setDroga(h.getDroga());
                hemodinamico.getHemodinamicoOpcionals().add(hemodinamicoOpcional);
            }
            Ficha r = getProperFicha();
            r.setHemodinamico(hemodinamico);
            realm.copyToRealmOrUpdate(r);
            realm.commitTransaction();
            changeCardColor();
        }
    }
    private void prepareBulhasRitmicoSpinner(){
        String[] bulhas = {defaultSpinnerString,"Sinusal","Bradicardico","Taquicardico"};
        this.bulhas = (Spinner) findViewById(R.id.hemodinamico_bulhas);
        ArrayAdapter<String> adapterBulhas = new ArrayAdapter<String>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, bulhas){
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
        this.bulhas.setAdapter(adapterBulhas);
    }

    private void prepareBulhasAritmicoSpinner(){
        String[] bulhas = {defaultSpinnerString,"Fibrilação Atrial","Flutter Atrial"};
        this.bulhas = (Spinner) findViewById(R.id.hemodinamico_bulhas);
        ArrayAdapter<String> adapterBulhas = new ArrayAdapter<String>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, bulhas){
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
        this.bulhas.setAdapter(adapterBulhas);
    }

    private void prepareHemodinamicoSpinners(){
        String[] ritmo = {defaultSpinnerString,"Ritmico","Aritmico"};
        String[] drogasVasoativaString = {defaultSpinnerString,"Dobutamina","Dopamina","Nitroprussiato de Sodio","Nitroglicerina","Milrinona","Noradrenalina","Adrenalina"};

        this.ritmo = (Spinner) findViewById(R.id.hemodinamico_ritmo);
        ArrayAdapter<String> adapterRitmo = new ArrayAdapter<String>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, ritmo) {
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
        this.ritmo.setAdapter(adapterRitmo);

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

        drogasVasoativas = (Spinner)dialogView.findViewById(R.id.drogaVasoativa);
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
        drogasVasoativas.setAdapter(adapterDrogaVasoativas);

        builder.setView(dialogView);
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addDataFromDialogIntoAdapter(drogasVasoativas.getSelectedItem().toString(),Integer.parseInt(doseDroga.getText().toString()));
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
