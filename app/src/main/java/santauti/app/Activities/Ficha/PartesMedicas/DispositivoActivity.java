package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 21-Jun-17.
 */

public class DispositivoActivity extends GenericoActivity {
    private boolean[] dispositivos = new boolean[16];
    private TextView dispositivosTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivo);
        setToolbar("Dispositivos");
        prepareNavigationButtons();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), BombaInfusaoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0) - 1, intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToLeft();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RespiradorActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0) + 1, intent);
                verificaCamposENotificaAdapter();
                finish();
                startActivity(intent);
                exitActivityToRight();
            }
        });
        setDispositivosFromDataBase();
    }

    private void setDispositivosFromDataBase() {
//        Ficha ficha = getProperFicha();
//        if (ficha.getDispositivos() != null && !ficha.getDispositivos().getNomeDispositivos().isEmpty())
//            preencheCheckboxes(R.id.dispositivos,ficha.getDispositivos().getNomeDispositivos());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
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
//        realm.beginTransaction();
//        Dispositivos dispositivos = realm.createObject(Dispositivos.class);
//        Ficha r = getProperFicha();
//        RealmList<RealmString> realmStrings = getCheckBoxesPreenchidos(R.id.dispositivos);
//        for(RealmString realmString : realmStrings)
//            dispositivos.getNomeDispositivos().add(realmString);
//        r.setDispositivos(dispositivos);
//        realm.copyToRealmOrUpdate(r);
//        realm.commitTransaction();
//        changeCardColorToGreen();
    }

}