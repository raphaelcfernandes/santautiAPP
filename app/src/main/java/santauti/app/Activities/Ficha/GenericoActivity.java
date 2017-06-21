package santauti.app.Activities.Ficha;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import io.realm.Realm;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 24-May-17.
 */

public abstract class GenericoActivity extends AppCompatActivity {
    public String defaultSpinnerString = " ";
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

    public Ficha getProperFicha(){
        Realm realm;
        realm = Realm.getDefaultInstance();
        return realm.where(Ficha.class).equalTo("NroAtendimento",getFichaId()).findFirst();
    }

    public int getFichaId(){
        return getIntent().getIntExtra("idFicha",0);
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

}
