package santauti.app.Activities.Ficha;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import io.realm.Realm;
import santauti.app.Adapters.Home.HomeModel;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 24-May-17.
 */

public abstract class GenericoActivity extends AppCompatActivity {
    public String defaultSpinnerString = "Selecione";
    public Button proxFicha,antFicha;
    public Intent intent;
    public int itemSelected =-1;
    public AlertDialog.Builder builder;
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

    public boolean isTextInputEditTextEmpty(TextInputEditText etText) {
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

    public void ordenaStringSpinner(String[] stringVec){
        Arrays.sort(stringVec, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareTo(t1);
            }
        });
    }

    public void createDialog(final String title, int selection, final TextView textView, final String[] options){
        builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle(title);

        //list of items
        Arrays.sort(options);
        builder.setSingleChoiceItems(options, selection,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(options[which]);
                        textView.setVisibility(View.VISIBLE);
                        itemSelected=which;
                        dialog.dismiss();
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    /**
     *
     * @param array boolean utilizado para marcar itens selecionados
     * @param textView aonde será inserido os itens escolhidos
     * @param items vetor ORDENADO ordem alfabética marcado por itens selecionados por @param array
     */
    public void setTextViewFromDialogMultipleText(boolean[] array, TextView textView,String[] items){
        StringBuilder stringBuilder = new StringBuilder();
        int i,total=0;
        for(i=0;i<array.length;i++)
            if (array[i])
                total++;
        for (i = 0; i < array.length; i++) {
            if (array[i]) {
                total--;
                if (total == 0) {
                    stringBuilder.append(items[i]);
                }
                else {
                    stringBuilder.append(items[i]).append(", ");
                }
            }
        }
        textView.setText(stringBuilder);
        if(!textView.isShown())
            textView.setVisibility(View.VISIBLE);
    }
}
