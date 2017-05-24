package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import santauti.app.Activities.Ficha.Generico;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class InfecciosoActivity extends Generico {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infeccioso);
        setToolbar(getString(R.string.Infeccioso));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
        return true;
    }
    public void infecciosoOnRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.infeccioso_sim:
                if (checked)
                    Snackbar.make(view, "Você terá campos a preencher na webpage.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                break;
            case R.id.infeccioso_nao:
                if (checked)
                    Snackbar.make(view, "Avaliação gerada automaticamente.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                break;
        }
    }
}
