package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import santauti.app.Model.Ficha.RealmObjects.RealmString;

/**
 * Created by rapha on 03-Oct-17.
 */

public class Nutricional extends RealmObject implements Serializable {
    @SerializedName("dieta")
    private RealmList<RealmString> dieta;
    @SerializedName("aceitacao")
    private String aceitacao;

    public boolean checkObject(){
        return aceitacao!=null && !dieta.isEmpty();
    }

    public RealmList<RealmString> getDieta() {
        return dieta;
    }

    public void setDieta(RealmList<RealmString> dieta) {
        this.dieta = dieta;
    }

    public String getAceitacao() {
        return aceitacao;
    }

    public void setAceitacao(String aceitacao) {
        this.aceitacao = aceitacao;
    }
}
