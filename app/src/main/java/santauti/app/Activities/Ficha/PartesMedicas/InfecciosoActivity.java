package santauti.app.Activities.Ficha.PartesMedicas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import io.realm.Realm;
import santauti.app.Activities.Ficha.Generico;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Infeccioso;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class InfecciosoActivity extends Generico {
    private Realm realm;
    private int idFicha;
    private int infeccioso;
    RadioButton infecciosoSim,infecciosoNao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infeccioso);
        setToolbar(getString(R.string.Infeccioso));

        idFicha=getIntent().getIntExtra("idFicha",0);
        realm = Realm.getDefaultInstance();

        infecciosoSim = (RadioButton)findViewById(R.id.infeccioso_sim);
        infecciosoNao = (RadioButton)findViewById(R.id.infeccioso_nao);

        if(getInfecciosoSelected()!=-1){
            if(getInfecciosoSelected()==1){
                infecciosoSim.setChecked(true);
            }
            else
                infecciosoNao.setChecked(true);
        }

    }

    private int getInfecciosoSelected(){
        Ficha f = getProperFicha();
        if(f.getInfeccioso()!=null)
            return f.getInfeccioso().getPacienteComInfeccao();
        else
            return -1;
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

    public void verificaCamposENotificaAdapter(){
        if(infecciosoSim.isChecked() || infecciosoNao.isChecked()) {
            realm.beginTransaction();
            Infeccioso infecc = realm.createObject(Infeccioso.class);
            infecc.setPacienteComInfeccao(infeccioso);
            Ficha r = getProperFicha();
            r.setInfeccioso(infecc);
            realm.copyToRealmOrUpdate(r);
            realm.commitTransaction();
            changeCardColor();
        }
    }

    public void infecciosoOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.infeccioso_sim:
                if (checked) {
                    Snackbar.make(view, "Você terá campos a preencher na webpage.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    infeccioso=1;
                }
                break;
            case R.id.infeccioso_nao:
                if (checked) {
                    Snackbar.make(view, "Avaliação gerada automaticamente.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    infeccioso=0;
                }
                break;
        }
    }
}
