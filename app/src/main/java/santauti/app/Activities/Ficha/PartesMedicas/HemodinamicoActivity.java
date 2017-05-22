package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 10-May-17.
 */

public class HemodinamicoActivity extends AppCompatActivity{
    Spinner ritmo,bulhas;
    RadioButton hemodinamico_opcional_sim,hemodinamico_opcional_nao;
    private View hemodinamico_opcional_layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hemodinamico);
        findViewById(R.id.hemodinamico_layout).requestFocus();
        Toolbar tbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(R.string.Hemodinamico);
        toolbar.setDisplayHomeAsUpEnabled(true);
        hemodinamico_opcional_nao = (RadioButton)findViewById(R.id.hemodinamico_opcional_nao);
        hemodinamico_opcional_sim = (RadioButton)findViewById(R.id.hemodinamico_opcional_sim);
        this.hemodinamico_opcional_layout = findViewById(R.id.hemodinamico_opcional_layout);
        this.hemodinamico_opcional_layout.setVisibility(View.GONE);
        prepareHemodinamicoSpinners();
        buildIntent();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
        return true;
    }
    private Intent buildIntent(){
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        return resultIntent;
    }

    private void prepareHemodinamicoSpinners(){
        String[] ritmo = {"Ritmico","Aritmico"};
        String[] bulhas = {"Grau 1","Grau 2","Grau 3","Grau 4","Grau 5","Grau 6"};

        this.ritmo = (Spinner) findViewById(R.id.hemodinamico_ritmo);
        ArrayAdapter<String> adapterRitmo = new ArrayAdapter<>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, ritmo);
        this.ritmo.setAdapter(adapterRitmo);

        this.bulhas = (Spinner) findViewById(R.id.hemodinamico_bulhas);
        ArrayAdapter<String> adapterBulhas = new ArrayAdapter<>(HemodinamicoActivity.this, android.R.layout.simple_dropdown_item_1line, bulhas);
        this.bulhas.setAdapter(adapterBulhas);
    }

    public void hemodinamicoOpcionalOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.hemodinamico_opcional_sim:
                if (checked) {
                    hemodinamico_opcional_layout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.hemodinamico_opcional_nao:
                if (checked) {
                    hemodinamico_opcional_layout.setVisibility(View.GONE);
                }
                break;
        }
    }
}
