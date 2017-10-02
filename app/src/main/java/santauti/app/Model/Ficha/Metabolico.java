package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 24-May-17.
 */

public class Metabolico extends RealmObject implements Serializable{
    @SerializedName("hidratacao")
    private String hidratacao;

    public String getHidratacao() {
        return hidratacao;
    }

    public void setHidratacao(String hidratacao) {
        this.hidratacao = hidratacao;
    }
}
