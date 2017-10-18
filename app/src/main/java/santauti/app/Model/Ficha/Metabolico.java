package santauti.app.Model.Ficha;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raphael Fernandes on 24-May-17.
 */

public class Metabolico {
    private String hidratacao;

    public String getHidratacao() {
        return hidratacao;
    }

    public void setHidratacao(String hidratacao) {
        this.hidratacao = hidratacao;
    }

    public Metabolico(String hidratacao) {
        this.hidratacao = hidratacao;
    }

    public Metabolico() {
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("hidratacao", hidratacao);
        return result;
    }

}
