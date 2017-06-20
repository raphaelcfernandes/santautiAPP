package santauti.app.Model.Ficha.Neurologico;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 02-Jun-17.
 */

public class NaoSedado extends RealmObject implements Serializable {
    @SerializedName("aberturaOcular")
    private int aberturaOcular;
    @SerializedName("respostaVerbal")
    private int respostaVerbal;
    @SerializedName("respostaMotora")
    private int respostaMotora;

    public int getAberturaOcular() {
        return aberturaOcular;
    }

    public void setAberturaOcular(int aberturaOcular) {
        this.aberturaOcular = aberturaOcular;
    }

    public int getRespostaVerbal() {
        return respostaVerbal;
    }

    public void setRespostaVerbal(int respostaVerbal) {
        this.respostaVerbal = respostaVerbal;
    }

    public int getRespostaMotora() {
        return respostaMotora;
    }

    public void setRespostaMotora(int respostaMotora) {
        this.respostaMotora = respostaMotora;
    }
}
