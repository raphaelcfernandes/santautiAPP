package santauti.app.Activities.Ficha;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 24-May-17.
 */

public abstract class GenericoActivity extends AppCompatActivity {
    public Button proxFicha,antFicha;
    public Intent intent;
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

    public void changeCardColorToGreen(){
        FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0)).setColor(1);
        FichaActivity.adapter.notifyDataSetChanged();
    }

    public void setCardColorToDefault(){
        FichaActivity.fichaAdapterModelList.get(getIntent().getIntExtra("Position", 0)).setColor(0);
        FichaActivity.adapter.notifyDataSetChanged();
    }

    public final void prepareIntent(int position,Intent intent){
        intent.putExtra("Position",position);
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

    public void setRadioButtonFromIdAndDatabase(int id,String stringFromDatabase){
        LinearLayout linearLayout = (LinearLayout)findViewById(id);
        for(int i=0;i<linearLayout.getChildCount();i++) {
            View v = linearLayout.getChildAt(i);
            if (v instanceof RadioGroup) {
                for (int k = 0; k < ((RadioGroup) v).getChildCount(); k++) {
                    View view1 = ((RadioGroup)v).getChildAt(k);
                    AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton)view1;
                    if(appCompatRadioButton.getText().toString().equals(stringFromDatabase))
                        appCompatRadioButton.setChecked(true);
                }
            }
        }
    }

    public void setRadioGroup(int id, String stringFromDatabase){
        RadioGroup radioGroup = (RadioGroup)findViewById(id);
        for(int i=0;i<radioGroup.getChildCount();i++){
            View v = radioGroup.getChildAt(i);
            RadioButton radioButton = (RadioButton)v;
            if(radioButton.getText().toString().equals(stringFromDatabase))
                radioButton.setChecked(true);
        }
    }

//    public void preencheCheckboxes(int id, RealmList<RealmString> realmStrings){
//        for(RealmString realmString : realmStrings){
//            LinearLayout linearLayout = (LinearLayout) findViewById(id);
//            for (int i = 0; i < linearLayout.getChildCount(); i++) {
//                View v = linearLayout.getChildAt(i);
//                if (v instanceof RelativeLayout) {
//                    for (int k = 0; k < ((RelativeLayout) v).getChildCount(); k++) {
//                        View view = ((RelativeLayout) v).getChildAt(k);
//                        if (view instanceof AppCompatCheckBox) {
//                            AppCompatCheckBox cb = (AppCompatCheckBox) view;
//                            if (cb.getText().toString().equals(realmString.getName()))
//                                cb.setChecked(true);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public RealmList<RealmString> getCheckBoxesPreenchidos(int id){
//        Realm realm = Realm.getDefaultInstance();
//        RealmList<RealmString> realmStrings = new RealmList<>();
//        LinearLayout linearLayout = (LinearLayout) findViewById(id);
//        for (int i = 0; i < linearLayout.getChildCount(); i++) {
//            View v = linearLayout.getChildAt(i);
//            if(v instanceof RelativeLayout){
//                for (int k = 0; k < ((RelativeLayout) v).getChildCount(); k++) {
//                    View view = ((RelativeLayout) v).getChildAt(k);
//                    if (view instanceof AppCompatCheckBox) {
//                        AppCompatCheckBox cb = (AppCompatCheckBox) view;
//                        if (cb.isChecked()) {
//                            RealmString realmString = realm.createObject(RealmString.class);
//                            realmString.setName(cb.getText().toString());
//                            realmStrings.add(realmString);
//                        }
//                    }
//                }
//            }
//        }
//        return realmStrings;
//    }
//
//    public void abreLayoutMarcaCheckboxEPreenche(CheckBox checkbox, View view,int id,RealmList<RealmString> realmStrings){
//        checkbox.setChecked(true);
//        MyAnimation myAnimation = new MyAnimation();
//        myAnimation.slideDownView(getApplicationContext(),view);
//        preencheCheckboxes(id,realmStrings);
//    }
}
