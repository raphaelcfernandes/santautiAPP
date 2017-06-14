package santauti.app.Activities.Ficha.PartesMedicas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Endocrino;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class EndocrinoActivity extends GenericoActivity {
    private int usoInsulinaBombaInfusao;
    private Realm realm;
    private RadioButton endocrinoS,endocrinoN;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endocrino);
        setToolbar(getString(R.string.Endocrino));

        endocrinoS = (RadioButton)findViewById(R.id.endocrino_sim);
        endocrinoN = (RadioButton)findViewById(R.id.endocrino_nao);

        realm = Realm.getDefaultInstance();

        if(getEndocrinoSelected()!=-1){
            if(getEndocrinoSelected()==1)
                endocrinoS.setChecked(true);
            else
                endocrinoN.setChecked(true);
        }

    }

    public int getEndocrinoSelected(){
        Ficha f = getProperFicha();
        if(f.getEndocrino()!=null)
            return f.getEndocrino().getUsoDeInsulinaBombaInfusao();
        else
            return -1;
    }

    public void endocrinoOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.endocrino_sim:
                Snackbar.make(view, "Você terá campos a preencher na webpage.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                usoInsulinaBombaInfusao=1;
                break;
            case R.id.endocrino_nao:
                usoInsulinaBombaInfusao=0;
                break;
        }
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
        if(endocrinoS.isChecked() || endocrinoN.isChecked()) {
            realm.beginTransaction();
            Endocrino endocrino = realm.createObject(Endocrino.class);
            endocrino.setUsoDeInsulinaBombaInfusao(usoInsulinaBombaInfusao);
            Ficha r = getProperFicha();
            r.setEndocrino(endocrino);
            realm.copyToRealmOrUpdate(r);
            realm.commitTransaction();
            changeCardColor();
        }
    }
}