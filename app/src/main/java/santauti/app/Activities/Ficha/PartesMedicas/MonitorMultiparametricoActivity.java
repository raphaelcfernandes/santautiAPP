package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;
import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.R;

/**
 * Created by raphael on 6/20/17.
 */

public class MonitorMultiparametricoActivity extends GenericoActivity {
    Spinner tracadoEletrocardiograficoSpinner;
    ArrayAdapter<String> adapterTracaoEletrocardiografico;
    Realm realm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_multiparametrico);

        setToolbar("Monitor Multiparamétrico");

        prepareSpinner();
        prepareNavigationButtons();

        realm = Realm.getDefaultInstance();

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), NeurologicoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, getIntent().getIntExtra("idFicha",0), intent);
                startActivity(intent);
                exitActivityToRight();
                realm.close();

                finish();
            }
        });
        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), DispositivoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, getIntent().getIntExtra("idFicha",0), intent);
                startActivity(intent);
                exitActivityToLeft();
                finish();
            }
        });
    }


    private void prepareSpinner(){
        String[] tiposTracado={defaultSpinnerString,"Sinusal","Fibrilaćao atrial", "Flutter atrial","Juncional","Idioventricular","Ritmo de marcapasso"};

        tracadoEletrocardiograficoSpinner = (Spinner)findViewById(R.id.pupila_simetria_diferenca_spinner);
        adapterTracaoEletrocardiografico = new ArrayAdapter<String>(MonitorMultiparametricoActivity.this, android.R.layout.simple_dropdown_item_1line, tiposTracado){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        };
        tracadoEletrocardiograficoSpinner.setAdapter(adapterTracaoEletrocardiografico);
    }

}
