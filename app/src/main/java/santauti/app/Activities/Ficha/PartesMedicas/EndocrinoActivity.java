package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import io.realm.Realm;
import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Model.Ficha.Endocrino;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Infeccioso;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 15-May-17.
 */

public class EndocrinoActivity extends GenericoActivity {
    private Realm realm;
    private TextView menuCurvaGlicemica,curvaGlicemica;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endocrino);
        setToolbar(getString(R.string.Endocrino));

        prepareNavigationButtons();
        realm = Realm.getDefaultInstance();

        menuCurvaGlicemica = (TextView)findViewById(R.id.menuCurvaGlicemica);
        curvaGlicemica = (TextView)findViewById(R.id.curvaGlicemica);
        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), HematologicoActivity.class);
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
                intent = new Intent(view.getContext(), PelesMucosasActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });

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
//        if(endocrinoS.isChecked() || endocrinoN.isChecked()) {
//            realm.beginTransaction();
//            Endocrino endocrino = realm.createObject(Endocrino.class);
//            endocrino.setUsoDeInsulinaBombaInfusao(usoInsulinaBombaInfusao);
//            Ficha r = getProperFicha();
//            r.setEndocrino(endocrino);
//            realm.copyToRealmOrUpdate(r);
//            realm.commitTransaction();
//            changeCardColor();
//        }
    }
    public void curvaGlicemicaOnClick(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), menuCurvaGlicemica, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_curva_glicemica, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.eventoHipoglicemia:
                        curvaGlicemica.setText(item.getTitle());
                        break;
                    case R.id.normoglicemia:
                        curvaGlicemica.setText(item.getTitle());
                        break;
                    case R.id.eventoHiperglicemia:
                        curvaGlicemica.setText(item.getTitle());
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        popupMenu.show();
    }
}