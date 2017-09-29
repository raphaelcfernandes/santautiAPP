package santauti.app.Activities.Ficha;

import android.app.Activity;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import io.realm.Realm;
import santauti.app.Activities.Ficha.PartesMedicas.FolhasBalancoActivity;
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

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(GenericoActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public String getStringOfRadioButtonSelectedFromRadioGroup(RadioGroup radioGroup){
        int idx = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
        RadioButton r = (RadioButton)radioGroup.getChildAt(idx);
        if(r!=null)
            return r.getText().toString();
        else
            return null;
    }

    public int getIndexOfRadioButtonFromRadioGroup(RadioGroup radioGroup){
        return radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
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
    public int setTextViewFromDialogMultipleText(boolean[] array, TextView textView,String[] items){
        StringBuilder stringBuilder = new StringBuilder();
        int i,total=0;
        for (i = 0; i < array.length; i++)
            if (array[i]) {
                total++;
                stringBuilder.append(items[i]).append(", ");
            }
        if(total>0)
            textView.setText(stringBuilder.substring(0,stringBuilder.length()-2));
        if(!textView.isShown())
            textView.setVisibility(View.VISIBLE);
        return total;
    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
