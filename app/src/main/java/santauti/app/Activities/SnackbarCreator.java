package santauti.app.Activities;

import com.google.android.material.snackbar.Snackbar;
import android.view.View;

/**
 * Created by Raphael Fernandes on 16-May-17.
 */

public class SnackbarCreator {
    public static void camposAPreencher(View view){
        Snackbar.make(view, "Você terá campos a preencher na webpage.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public static void avaliacaoGeradaAutomaticamente(View view){
        Snackbar.make(view, "Avaliação gerada automaticamente.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public static void createText(View view,String string){
        Snackbar.make(view,string, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
