package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

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
    private View tipoSoproLayout,intensidadeSoproLayout;
    private TextView foneseBulhas,tipoSopro,intensidadeSopro;
    private MyAnimation myAnimation;
    private Realm realm;
    private Ficha ficha;
    private int foneseBulhasSelection=-1,tipoSoproSelection=-1,intensidadeSoproSelection=-1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hemodinamico);
        findViewById(R.id.hemodinamico_layout).requestFocus();
        setToolbar(getString(R.string.Hemodinamico));

        /********************VIEWS*******************************/
        tipoSoproLayout = findViewById(R.id.tipoSopro_layout);
        intensidadeSoproLayout = findViewById(R.id.intensidade_sopro_layout);

        foneseBulhas = (TextView)findViewById(R.id.foneseBulhas);
        tipoSopro = (TextView)findViewById(R.id.tipoSopro);
        intensidadeSopro = (TextView)findViewById(R.id.intensidadeSopro);
        /********************VIEWS*******************************/



        myAnimation = new MyAnimation();

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

    }

    public void soproRadioOnClick(View view){
        switch(view.getId()) {
            case R.id.simSopro:
                if(!intensidadeSoproLayout.isShown() && !tipoSoproLayout.isShown()) {
                    myAnimation.slideDownView(HemodinamicoActivity.this,intensidadeSoproLayout);
                    myAnimation.slideDownView(HemodinamicoActivity.this,tipoSoproLayout);
                }
                break;
            case R.id.naoSopro:
                if(intensidadeSoproLayout.isShown() && tipoSoproLayout.isShown()) {
                    myAnimation.slideUpView(HemodinamicoActivity.this,tipoSoproLayout);
                    myAnimation.slideUpView(HemodinamicoActivity.this,intensidadeSoproLayout);
                }
                break;
        }
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

    public void foneseBulhasOnCLick(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.FoneseBulhas);

        //list of items
        final String[] items = getResources().getStringArray(R.array.foneseBulhas);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, foneseBulhasSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        foneseBulhas.setText(items[which]);
                        foneseBulhas.setVisibility(View.VISIBLE);
                        foneseBulhasSelection=which;
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

    public void tipoSoproOnClick(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.TipoSopro);

        //list of items
        final String[] items = getResources().getStringArray(R.array.tipoSopro);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, tipoSoproSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tipoSopro.setText(items[which]);
                        tipoSopro.setVisibility(View.VISIBLE);
                        tipoSoproSelection=which;
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

    public void intensidadeSoproOnClick(View view) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(R.string.IntensidadeSopro);

        //list of items
        final String[] items = getResources().getStringArray(R.array.intensidadeSopro);
        Arrays.sort(items);
        builder.setSingleChoiceItems(items, intensidadeSoproSelection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intensidadeSopro.setText(items[which]);
                        intensidadeSopro.setVisibility(View.VISIBLE);
                        intensidadeSoproSelection=which;
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
