package santauti.app.Activities.Ficha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.Button;

import io.realm.Realm;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 24-May-17.
 */

public abstract class GenericoActivity extends AppCompatActivity {
    public String defaultSpinnerString = "Selecione";
    public Button proxFicha,antFicha;
    public Intent intent;

    @Override
    public void onBackPressed(){
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home) {
            finish();
        }
        return true;
    }

    public void setToolbar(String title){
        Toolbar tbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(title);
        SpannableString s = new SpannableString(toolbar.getTitle());
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFFFF")),0,toolbar.getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public void changeCardColor(){
        FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0)).setColor(1);
        FichaActivity.adapter.notifyDataSetChanged();
    }

    public final void prepareIntent(int position,Intent intent){
        intent.putExtra("Position",position);
    }

    public Ficha getProperFicha(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        Realm realm;
        realm = Realm.getDefaultInstance();
        return realm.where(Ficha.class).equalTo("NroAtendimento",sharedPreferences.getInt("NroAtendimento",0)).findFirst();
    }

    public boolean isTextInpudEditTextEmpty(TextInputEditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public boolean isSpinnerDefault(String string){
        return string.equals(defaultSpinnerString);
    }

    public int getIntegerFromTextInputEditText(TextInputEditText textInputEditText){
        return Integer.parseInt(textInputEditText.getText().toString());
    }

    public void exitActivityToLeft(){
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
    }

    public void exitActivityToRight(){
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);//(int enterAnimation, int exitAnimation)
    }

    public void prepareNavigationButtons(){
        proxFicha = (Button)findViewById(R.id.fichaProxima);
        antFicha = (Button)findViewById(R.id.fichaAnterior);
        proxFicha.setText(FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0)+1).getName()+" >");
        antFicha.setText("< "+FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0)-1).getName());
    }
}
