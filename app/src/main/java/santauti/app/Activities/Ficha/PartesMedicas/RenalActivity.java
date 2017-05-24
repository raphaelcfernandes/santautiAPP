package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import santauti.app.Activities.Ficha.Generico;
import santauti.app.R;
import santauti.app.Activities.SnackbarCreator;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class RenalActivity extends Generico {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_renal);
        findViewById(R.id.activity_renal).requestFocus();
        setToolbar(getString(R.string.Renal));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
        return true;
    }
    public void renalOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.renal_sim:
                if (checked)
                    SnackbarCreator.camposAPreencher(view);
                break;
            case R.id.renal_nao:
                if (checked)
                    SnackbarCreator.avaliacaoGeradaAutomaticamente(view);
                break;
        }
    }
}
