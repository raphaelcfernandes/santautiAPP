package santauti.app.View.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 08-May-17.
 */

public class Neurologico extends AppCompatActivity{
    MaterialBetterSpinner materialBetterSpinner ;
    private Intent intent;
    String[] SPINNER_DATA = {"ANDROID","PHP","BLOGGER","WORDPRESS"};
    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_neurologico);
        materialBetterSpinner = (MaterialBetterSpinner)findViewById(R.id.material_spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Neurologico.this, android.R.layout.simple_dropdown_item_1line, SPINNER_DATA);
        materialBetterSpinner.setAdapter(adapter);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("Position", 2);
        setResult(RESULT_OK, resultIntent);
    }

}
