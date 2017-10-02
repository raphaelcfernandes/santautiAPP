package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.ListPopupWindow;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Animation.InputFilterMin;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.MonitorMultiparametrico;
import santauti.app.R;

/**
 * Created by raphael on 6/20/17.
 */

public class MonitorMultiparametricoActivity extends GenericoActivity{
    Realm realm;
    private TextView ritmoTextView,ritmoMenu;
    private LinearLayout mainLayout;
    private TextInputEditText freqRespiratoria,freqCardiaca,PAM,temperatura,PIC,PPC,PVC,swan_ganz;
    private TextInputEditText capnometria,spo2,sjo2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_multiparametrico);
        mainLayout = (LinearLayout)findViewById(R.id.monitor_multiparametrico_layout);
        mainLayout.requestFocus();
        setupUI(mainLayout);
        setToolbar(getString(R.string.MonitorMultiparametrico));

        /******************************TEXTVIEW*************************************/
        ritmoTextView = (TextView)findViewById(R.id.ritmo);
        ritmoMenu = (TextView)findViewById(R.id.ritmoMenu);
        /******************************TEXTVIEW*************************************/

        /*****************************TEXTINPUTEDITTEXT****************************/
        freqRespiratoria = (TextInputEditText)findViewById(R.id.freqRespiratoria);
        freqRespiratoria.setFilters(new InputFilter[]{ new InputFilterMin(1)});
        freqCardiaca = (TextInputEditText)findViewById(R.id.freqCardiaca);
        freqCardiaca.setFilters(new InputFilter[]{ new InputFilterMin(1)});
        PAM = (TextInputEditText)findViewById(R.id.pam);
        PAM.setFilters(new InputFilter[]{ new InputFilterMin(1)});
        temperatura = (TextInputEditText)findViewById(R.id.temperatura);
        PIC = (TextInputEditText)findViewById(R.id.PIC);
        PIC.setFilters(new InputFilter[]{ new InputFilterMin(1)});
        PPC = (TextInputEditText)findViewById(R.id.PPC);
        PPC.setFilters(new InputFilter[]{ new InputFilterMin(1)});
        PVC = (TextInputEditText)findViewById(R.id.PVC);
        PVC.setFilters(new InputFilter[]{ new InputFilterMin(1)});
        swan_ganz = (TextInputEditText)findViewById(R.id.swan_ganz);
        swan_ganz.setFilters(new InputFilter[]{ new InputFilterMin(1)});
        capnometria = (TextInputEditText)findViewById(R.id.capnometria);
        capnometria.setFilters(new InputFilter[]{ new InputFilterMin(1)});
        spo2 = (TextInputEditText)findViewById(R.id.spo2);
        spo2.setFilters(new InputFilter[]{ new InputFilterMin(0)});
        sjo2 = (TextInputEditText)findViewById(R.id.sjo2);
        sjo2.setFilters(new InputFilter[]{ new InputFilterMin(0)});
        /*****************************TEXTINPUTEDITTEXT****************************/

        prepareNavigationButtons();

        realm = Realm.getDefaultInstance();

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), BombaInfusaoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                realm.close();
                verificaCamposENotificaAdapter();
                finish();
            }
        });
        setMonitorMultiparametricoFromDatabase();
    }

    @Override
    public void prepareNavigationButtons() {
        proxFicha = (Button)findViewById(R.id.fichaProxima);
        antFicha = (Button)findViewById(R.id.fichaAnterior);
        antFicha.setVisibility(View.GONE);
        proxFicha.setText(FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0)+1).getName()+" >");
    }

    private void setMonitorMultiparametricoFromDatabase(){
        Ficha ficha = getProperFicha();
        if(ficha.getMonitorMultiparametrico()!=null) {
            MonitorMultiparametrico monitorMultiparametrico = ficha.getMonitorMultiparametrico();
            if(monitorMultiparametrico.getRitmo()!=null)
                ritmoTextView.setText(monitorMultiparametrico.getRitmo());
            if(monitorMultiparametrico.getFrequenciaRespiratoria()>0)
                freqRespiratoria.setText(Integer.toString(monitorMultiparametrico.getFrequenciaRespiratoria()));
            if(monitorMultiparametrico.getFrequenciaCardiaca()>0)
                freqCardiaca.setText(Integer.toString(monitorMultiparametrico.getFrequenciaCardiaca()));
            if(monitorMultiparametrico.getPAM()>0)
                PAM.setText(Integer.toString(monitorMultiparametrico.getPAM()));
            if(monitorMultiparametrico.getTemperatura()>0)
                temperatura.setText(Float.toString(monitorMultiparametrico.getTemperatura()));
            if(monitorMultiparametrico.getPic()>0)
                PIC.setText(Integer.toString(monitorMultiparametrico.getPic()));
            if(monitorMultiparametrico.getPpc()>0)
                PPC.setText(Integer.toString(monitorMultiparametrico.getPpc()));
            if(monitorMultiparametrico.getPvc()>0)
                PVC.setText(Integer.toString(monitorMultiparametrico.getPvc()));
            if(monitorMultiparametrico.getSwanGanz()>0)
                swan_ganz.setText(Integer.toString(monitorMultiparametrico.getSwanGanz()));
            if(monitorMultiparametrico.getCapnometria()>0)
                capnometria.setText(Integer.toString(monitorMultiparametrico.getCapnometria()));
            if(monitorMultiparametrico.getSpo2()>=0)
                spo2.setText(Integer.toString(monitorMultiparametrico.getSpo2()));
            if(monitorMultiparametrico.getSjo2()>=0)
                sjo2.setText(Integer.toString(monitorMultiparametrico.getSjo2()));
        }
    }

    public void ritmoOnClick(View view) {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(
                MonitorMultiparametricoActivity.this,null,R.attr.actionOverflowMenuStyle,0);
        listPopupWindow.setAdapter(new ArrayAdapter<>(
                MonitorMultiparametricoActivity.this,
                R.layout.list_item, getResources().getStringArray(R.array.tiposTracado)));
        listPopupWindow.setAnchorView(ritmoMenu);
        listPopupWindow.setWidth(700);
        listPopupWindow.setHeight(1050);
        listPopupWindow.setModal(true);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ritmoTextView.setText(getResources().getStringArray(R.array.tiposTracado)[i]);
                listPopupWindow.dismiss();
            }
        });
        listPopupWindow.show();
    }

    private void verificaCamposENotificaAdapter(){
        realm.beginTransaction();
        Ficha r = getProperFicha();
        MonitorMultiparametrico monitorMultiparametrico = realm.createObject(MonitorMultiparametrico.class);
        if(ritmoTextView.getText().length()>0)
            monitorMultiparametrico.setRitmo(ritmoTextView.getText().toString());
        if(!isTextInputEditTextEmpty(freqRespiratoria))
            monitorMultiparametrico.setFrequenciaRespiratoria(getIntegerFromTextInputEditText(freqRespiratoria));
        if(!isTextInputEditTextEmpty(freqCardiaca))
            monitorMultiparametrico.setFrequenciaCardiaca(getIntegerFromTextInputEditText(freqCardiaca));
        if(!isTextInputEditTextEmpty(PAM))
            monitorMultiparametrico.setPAM(getIntegerFromTextInputEditText(PAM));
        if(!isTextInputEditTextEmpty(temperatura))
            monitorMultiparametrico.setTemperatura(Float.parseFloat(temperatura.getText().toString()));
        if(!isTextInputEditTextEmpty(PIC))
            monitorMultiparametrico.setPic(getIntegerFromTextInputEditText(PIC));
        if(!isTextInputEditTextEmpty(PPC))
            monitorMultiparametrico.setPpc(getIntegerFromTextInputEditText(PPC));
        if(!isTextInputEditTextEmpty(PVC))
            monitorMultiparametrico.setPvc(getIntegerFromTextInputEditText(PVC));
        if(!isTextInputEditTextEmpty(swan_ganz))
            monitorMultiparametrico.setSwanGanz(getIntegerFromTextInputEditText(swan_ganz));
        if(!isTextInputEditTextEmpty(capnometria))
            monitorMultiparametrico.setCapnometria(getIntegerFromTextInputEditText(capnometria));
        if(!isTextInputEditTextEmpty(spo2))
            monitorMultiparametrico.setSpo2(getIntegerFromTextInputEditText(spo2));
        if(!isTextInputEditTextEmpty(sjo2))
            monitorMultiparametrico.setSjo2(getIntegerFromTextInputEditText(sjo2));
        r.setMonitorMultiparametrico(monitorMultiparametrico);
        realm.copyToRealmOrUpdate(r);
        if(monitorMultiparametrico.checkObject())
            changeCardColor();
        realm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
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

    @Override
    public void onBackPressed(){
        verificaCamposENotificaAdapter();
        finish();
    }
}
