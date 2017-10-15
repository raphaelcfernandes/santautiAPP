package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Infeccioso;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class InfecciosoActivity extends GenericoActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infeccioso);
        setToolbar(getString(R.string.Infeccioso));

        prepareNavigationButtons();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), MetabolicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1,intent);
                startActivity(intent);
                exitActivityToLeft();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), NutricionalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });
        setInfecciosoFromDataBase();
    }

    private void setInfecciosoFromDataBase(){
//        Ficha ficha = getProperFicha();
//        if(ficha.getInfeccioso()!=null && !ficha.getInfeccioso().getAntibioticos().isEmpty())
//            preencheCheckboxes(R.id.antibioticos,ficha.getInfeccioso().getAntibioticos());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
//        realm.beginTransaction();
//        Ficha ficha = getProperFicha();
//        Infeccioso infeccioso = realm.createObject(Infeccioso.class);
//        RealmList<RealmString> realmStrings = getCheckBoxesPreenchidos(R.id.antibioticos);
//        for(RealmString realmString : realmStrings)
//            infeccioso.getAntibioticos().add(realmString);
//        ficha.setInfeccioso(infeccioso);
//        realm.copyToRealmOrUpdate(ficha);
//        changeCardColorToGreen();
//        realm.commitTransaction();
    }
}
