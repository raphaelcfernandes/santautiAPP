package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import io.realm.Realm;
import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Osteomuscular;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 09-Aug-17.
 */

public class OsteomuscularActivity extends GenericoActivity {
    private RadioGroup musculaturaTrofismoRadioGroup, musculaturaTonusRadioGroup;
    Realm realm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osteomuscular);
        setToolbar(getString(R.string.OsteoMuscular));
        realm = Realm.getDefaultInstance();
        prepareNavigationButtons();


        /*********************RADIOGROUP*****************/
        musculaturaTonusRadioGroup = (RadioGroup)findViewById(R.id.musculaturaTonusRadioGroup);
        musculaturaTrofismoRadioGroup = (RadioGroup)findViewById(R.id.musculaturaTrofismoRadioGroup);
        /*********************RADIOGROUP*****************/
        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), PelesMucosasActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        setOsteomuscularFromDatabase();
    }

    @Override
    public void prepareNavigationButtons() {
        proxFicha = (Button)findViewById(R.id.fichaProxima);
        antFicha = (Button)findViewById(R.id.fichaAnterior);
        proxFicha.setVisibility(View.GONE);
        antFicha.setText("< " + FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0) - 1).getName());
    }

    private void setOsteomuscularFromDatabase(){
        Ficha ficha = getProperFicha();
        if(ficha.getOsteomuscular()!=null) {
            if(ficha.getOsteomuscular().getTonusMuscular()!=null)
                setRadioButtonFromIdAndDatabase(R.id.musculatura, ficha.getOsteomuscular().getTonusMuscular());
            if(ficha.getOsteomuscular().getTrofismoMuscular()!=null)
                setRadioButtonFromIdAndDatabase(R.id.musculatura, ficha.getOsteomuscular().getTrofismoMuscular());
        }

    }


    private void verificaCamposENotificaAdapter(){
        realm.beginTransaction();
        Osteomuscular osteomuscular = realm.createObject(Osteomuscular.class);
        if(musculaturaTonusRadioGroup.getCheckedRadioButtonId()!=-1)
            osteomuscular.setTonusMuscular(getStringOfRadioButtonSelectedFromRadioGroup(musculaturaTonusRadioGroup));
        if(musculaturaTrofismoRadioGroup.getCheckedRadioButtonId()!=-1)
            osteomuscular.setTrofismoMuscular(getStringOfRadioButtonSelectedFromRadioGroup(musculaturaTrofismoRadioGroup));

        Ficha r = getProperFicha();
        r.setOsteomuscular(osteomuscular);
        realm.copyToRealmOrUpdate(r);
        realm.commitTransaction();
        if(osteomuscular.checkObject())
            changeCardColorToGreen();
        else
            setCardColorToDefault();
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
}
