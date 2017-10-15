package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class MetabolicoActivity extends GenericoActivity {
    private int i=0;
    private RadioGroup hidratacaoRadioGroup1,hidratacaoRadioGroup2;
    private RadioButton normoHidratado, edemaciado, desidratado;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolico);
        findViewById(R.id.activity_metabolico).requestFocus();
        setToolbar(this.getString(R.string.Metabolico));

        /********************RADIOGROUP****************************/
        hidratacaoRadioGroup1 = (RadioGroup)findViewById(R.id.hidratacaoRadioGroup1);
        hidratacaoRadioGroup2 = (RadioGroup)findViewById(R.id.hidratacaoRadioGroup2);
        /********************RADIOGROUP****************************/

        /*******************RADIOBUTTON****************************/
        normoHidratado = (RadioButton)findViewById(R.id.normoHidratado);
        normoHidratado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {hidratacaoRadioGroup2.clearCheck();
            }
        });
        edemaciado = (RadioButton)findViewById(R.id.edemaciado);
        edemaciado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {hidratacaoRadioGroup2.clearCheck();
            }
        });
        desidratado = (RadioButton)findViewById(R.id.desidratado);
        desidratado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {hidratacaoRadioGroup1.clearCheck();
            }
        });
        /*******************RADIOBUTTON****************************/



        prepareNavigationButtons();
        setMetabolicoFromDataBase();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RenalActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), InfecciosoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1,intent);
                startActivity(intent);
                exitActivityToRight();
                verificaCamposENotificaAdapter();
                finish();
            }
        });
    }


    private void setMetabolicoFromDataBase(){
//        Ficha ficha = getProperFicha();
//        if(ficha.getMetabolico()!=null)
//            setRadioButtonFromIdAndDatabase(R.id.hidratacao,ficha.getMetabolico().getHidratacao());
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

    /**
     * Verifica se algum radio button foi selecionado
     *
     */
    private void verificaCamposENotificaAdapter(){
        String hidratacaoStr = null;
        if(hidratacaoRadioGroup1.getCheckedRadioButtonId()!=-1)
            hidratacaoStr = getStringOfRadioButtonSelectedFromRadioGroup(hidratacaoRadioGroup1);
        else if(hidratacaoRadioGroup2.getCheckedRadioButtonId()!=-1)
            hidratacaoStr = getStringOfRadioButtonSelectedFromRadioGroup(hidratacaoRadioGroup2);
        if(hidratacaoStr!=null) {
//            Metabolico metabolico = new Metabolico(hidratacaoStr);
//            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
//            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//            databaseReference.child("Fichas").child(sharedPreferences.getString("FichaKEY","")).setValue(metabolico.getHidratacao());
//            changeCardColorToGreen();
        }
    }
}
