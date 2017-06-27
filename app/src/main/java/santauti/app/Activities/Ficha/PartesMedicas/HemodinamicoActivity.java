package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Hemodinamico.Hemodinamico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 10-May-17.
 */

public class HemodinamicoActivity extends GenericoActivity {
    private Spinner bulhasSpinner;
    private Spinner drogasVasoativasSpinner,soproSpinner,intensidadeSoproSpinner,tipoSoproSpinner;
    private View tipoSoproLayout;
    private View intensidadeSoproLayout;
    private MyAnimation myAnimation;
    private Realm realm;
    private Ficha ficha;
    private ArrayAdapter<String> adapterBulhas,adapterSopro,adapterIntensidade,adapterTipoSopro;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hemodinamico);
        findViewById(R.id.hemodinamico_layout).requestFocus();
        setToolbar(getString(R.string.Hemodinamico));


        tipoSoproLayout = findViewById(R.id.tipoSopro_layout);
        intensidadeSoproLayout = findViewById(R.id.intensidade_sopro_layout);


        myAnimation = new MyAnimation();
        prepareHemodinamicoSpinners();
        prepareNavigationButtons();
        realm=Realm.getDefaultInstance();
        ficha=getProperFicha();

//        if(ficha.getHemodinamico()!=null){
//            if(ficha.getHemodinamico().getRitmo().equals("Ritmico")) {
//                prepareBulhasRitmicoSpinner();
//            }
//            if(ficha.getHemodinamico().getRitmo().equals("Aritmico")) {
//                prepareBulhasAritmicoSpinner();
//            }
//            spinnerPosition = adapterRitmo.getPosition(ficha.getHemodinamico().getRitmo());
//            spinnerPosition = adapterBulhas.getPosition(ficha.getHemodinamico().getBulhas());
//            bulhasSpinner.setSelection(spinnerPosition);
//            myAnimation.slideDownView(HemodinamicoActivity.this,bulhas_layout);
//            frequenciaCardiaca.setText(String.valueOf(ficha.getHemodinamico().getFreqCardiaca()));
//            if(ficha.getHemodinamico().getHemodinamicoOpcionais().size()>0){//Verifica e percorre lista de drogas vasoativas ativadas
//                for(HemodinamicoOpcional h : ficha.getHemodinamico().getHemodinamicoOpcionais()){
//                    BombaInfusaoAdapterModel hemodinamicoModel = new BombaInfusaoAdapterModel(h.getDroga(),h.getDose());
//                    hemodinamicoModelList.add(hemodinamicoModel);
//                    hemodinamicoAdapter.notifyItemInserted(hemodinamicoAdapter.getItemCount()-1);
//                }
//                hemodinamicoAdapter.notifyDataSetChanged();
//            }
//            if(ficha.getHemodinamico().isOpcionais()){
//                hemodinamico_opcional_sim.setChecked(true);
//                PAM.setText(String.valueOf(ficha.getHemodinamico().getPam()));
//                swan_ganz.setText(String.valueOf(ficha.getHemodinamico().getSwan_ganz()));
//                PVC.setText(String.valueOf(ficha.getHemodinamico().getPvc()));
//                 myAnimation.slideDownView(this,hemodinamico_opcional_layout);
//            }
//            else
//                hemodinamico_opcional_nao.setChecked(true);
//        }
        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), NeurologicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RespiratorioActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });


        soproSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(soproSpinner.getSelectedItem().equals("Sim") && !intensidadeSoproSpinner.isShown() && !tipoSoproSpinner.isShown()){
                    myAnimation.slideDownView(HemodinamicoActivity.this,intensidadeSoproLayout);
                    myAnimation.slideDownView(HemodinamicoActivity.this,tipoSoproLayout);
                }
                else{
                    myAnimation.slideUpView(HemodinamicoActivity.this,tipoSoproLayout);
                    myAnimation.slideUpView(HemodinamicoActivity.this,intensidadeSoproLayout);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
        //verificaCamposENotificaAdapter();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home) {
            //verificaCamposENotificaAdapter();
            finish();
        }
        return true;
    }

    public void verificaCamposENotificaAdapter(){
        boolean obrigatorios = false;
        ficha=getProperFicha();
        realm.beginTransaction();
        Hemodinamico hemodinamico = realm.createObject(Hemodinamico.class);


        if(obrigatorios) {
            Ficha r = getProperFicha();
            r.setHemodinamico(hemodinamico);
            realm.copyToRealmOrUpdate(r);
            changeCardColor();
        }
        realm.commitTransaction();
    }

    private void prepareHemodinamicoSpinners(){
        String[] tipoBulhas = {defaultSpinnerString,"Hiperfonetica","Normofonetica","Hipofonetica"};
        String[] sopro = {defaultSpinnerString,"Sim","Não"};
        String[] tipoSopro = {defaultSpinnerString,"FM","FT","FA","IFT"};
        String[] intensidadeSopro = {defaultSpinnerString,"+1","+2","+3","+4","+5","+6"};

        bulhasSpinner = (Spinner) findViewById(R.id.hemodinamico_bulhas);
        adapterBulhas = new ArrayAdapter<String>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, tipoBulhas) {
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

        soproSpinner = (Spinner) findViewById(R.id.hemodinamico_sopro);
        adapterSopro = new ArrayAdapter<String>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, sopro) {
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
        soproSpinner.setAdapter(adapterSopro);

        tipoSoproSpinner = (Spinner) findViewById(R.id.hemodinamico_tipoSpro);
        adapterTipoSopro = new ArrayAdapter<String>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, tipoSopro) {
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
        tipoSoproSpinner.setAdapter(adapterTipoSopro);

        intensidadeSoproSpinner = (Spinner) findViewById(R.id.hemodinamico_intensidade_sopro);
        adapterIntensidade = new ArrayAdapter<String>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, intensidadeSopro) {
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
        intensidadeSoproSpinner.setAdapter(adapterIntensidade);

    }





}
