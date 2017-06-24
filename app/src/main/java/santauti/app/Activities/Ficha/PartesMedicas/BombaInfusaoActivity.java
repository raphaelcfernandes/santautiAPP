package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.R;

/**
 * Created by raphael on 6/23/17.
 */

public class BombaInfusaoActivity extends GenericoActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endocrino);
        setToolbar(getString(R.string.BombaInfusao));
        prepareNavigationButtons();

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), MonitorMultiparametricoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), DispositivoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                finish();
            }
        });

    }
}
