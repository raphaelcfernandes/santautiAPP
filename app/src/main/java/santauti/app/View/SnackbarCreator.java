package santauti.app.View;

import android.support.design.widget.Snackbar;
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
}
