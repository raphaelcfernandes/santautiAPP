package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Dispositivos;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.RealmObjects.RealmString;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 21-Jun-17.
 */

public class DispositivoActivity extends GenericoActivity {
    private boolean[] dispositivos = new boolean[16];
    private TextView dispositivosTextView;
    private Realm realm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivo);
        setToolbar("Dispositivos");
        prepareNavigationButtons();
        realm = Realm.getDefaultInstance();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), BombaInfusaoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0) - 1, intent);
                startActivity(intent);
                exitActivityToLeft();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RespiradorActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0) + 1, intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });
        setDispositivosFromDataBase();
    }

    private void setDispositivosFromDataBase() {
        Ficha ficha = getProperFicha();
        if (ficha.getDispositivos() != null) {
            LinearLayout layout= (LinearLayout)findViewById(R.id.dispositivos);
            for (RealmString realmString : ficha.getDispositivos().getNomeDispositivos()) {
                for(int i=0;i<layout.getChildCount();i++){
                    View v = layout.getChildAt(i);
                    for(int j=0;j<((RelativeLayout) v).getChildCount();j++) {
                        View view = ((RelativeLayout) v).getChildAt(j);
                        CheckBox cb = (CheckBox)view;
                        if(!cb.isChecked() && cb.getText().toString().equals(realmString.getName()))
                            cb.setChecked(true);
                    }
                }
            }
        }
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

    private void verificaCamposENotificaAdapter(){
        realm.beginTransaction();
        Dispositivos dispositivos = realm.createObject(Dispositivos.class);
        Ficha r = getProperFicha();
        r.setDispositivos(dispositivos);
        LinearLayout layout= (LinearLayout)findViewById(R.id.dispositivos);
        for(int i=0;i<layout.getChildCount();i++){
            View v = layout.getChildAt(i);
            if(v instanceof RelativeLayout){
                for(int j=0;j<((RelativeLayout) v).getChildCount();j++) {
                    View view = ((RelativeLayout) v).getChildAt(j);
                    if(view instanceof CheckBox){
                        CheckBox cb = (CheckBox)view;
                        if(cb.isChecked()) {
                            RealmString realmString = realm.createObject(RealmString.class);
                            realmString.setName(cb.getText().toString());
                            dispositivos.getNomeDispositivos().add(realmString);
                        }
                    }
                }
            }
        }
        r.setDispositivos(dispositivos);
        realm.copyToRealmOrUpdate(r);
        changeCardColorToGreen();
        realm.commitTransaction();
    }

}