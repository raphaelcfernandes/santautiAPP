package santauti.app.Activities.Ficha;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 24-May-17.
 */

public abstract class Generico extends AppCompatActivity {
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
        FichaActivity.fichaList.get(getIntent().getIntExtra("Position", 0)).setColor(1);
        FichaActivity.adapter.notifyDataSetChanged();
    }
}
