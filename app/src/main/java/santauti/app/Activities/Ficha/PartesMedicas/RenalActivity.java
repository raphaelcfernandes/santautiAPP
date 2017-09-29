package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import io.realm.Realm;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Activities.SnackbarCreator;
import santauti.app.Animation.MyAnimation;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Renal;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class RenalActivity extends GenericoActivity {
    TextInputEditText diureseTxt, pesoTxt, balancoHidricoTxt;
    RadioButton renalS,renalN;
    private int renalChecked;
    private View dialiseItensLayout;
    private Realm realm;
    private Ficha ficha;
    private MyAnimation myAnimation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renal);
        findViewById(R.id.activity_renal).requestFocus();
        setToolbar(getString(R.string.Renal));
        setupUI(findViewById(R.id.activity_renal));
        dialiseItensLayout = findViewById(R.id.dialiseItensLayout);
        pesoTxt = (TextInputEditText)findViewById(R.id.peso);
        prepareNavigationButtons();
        realm = Realm.getDefaultInstance();
        ficha=getProperFicha();
        myAnimation = new MyAnimation();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), GastrointestinalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                // verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), MetabolicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                // verificaCamposENotificaAdapter();
                finish();
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
        // verificaCamposENotificaAdapter();
        finish();
    }

    private void verificaCamposENotificaAdapter() {
        realm.beginTransaction();
        int i=0;
        Renal renal = realm.createObject(Renal.class);
        if(!isTextInputEditTextEmpty(diureseTxt)) {
            renal.setDiurese(Integer.parseInt(diureseTxt.getText().toString()));
            i++;
        }
        if(!isTextInputEditTextEmpty(pesoTxt)) {
            renal.setPeso(Integer.parseInt(pesoTxt.getText().toString()));
            i++;
        }
        if(!isTextInputEditTextEmpty(balancoHidricoTxt)) {
            renal.setBalancoHidrico(Integer.parseInt(balancoHidricoTxt.getText().toString()));
            i++;
        }
        if(renalS.isChecked()) {
            renal.setDialise(1);
            i++;
        }
        if(renalN.isChecked()) {
            renal.setDialise(0);
            i++;
        }
        if(i==4) {
            Ficha r = getProperFicha();
            r.setRenal(renal);
            realm.copyToRealmOrUpdate(r);
            changeCardColor();
        }
        realm.commitTransaction();
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

    public void dialiseRadioButtonClicked (View view){
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.simDialise:
                if (checked)
                    if(!dialiseItensLayout.isShown())
                        myAnimation.slideDownView(getApplicationContext(),dialiseItensLayout);
                break;
            case R.id.naoDialise:
                if (checked)
                    if(dialiseItensLayout.isShown())
                        myAnimation.slideUpView(getApplicationContext(),dialiseItensLayout);
                break;
        }
    }
}
