package santauti.app.Model.Ficha.Neurologico;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 02-Jun-17.
 */

public class Sedado extends RealmObject implements Serializable{
    @SerializedName("ramsay")
    private String ramsay;
    @SerializedName("rass")
    private String rass;
    @SerializedName("sedativo")
    private RealmList<Sedativo> sedativo;

    public RealmList<Sedativo> getSedativo() {
        return sedativo;
    }

    public void setSedativo(RealmList<Sedativo> sedativo) {
        this.sedativo = sedativo;
    }

    public String getRamsay() {
        return ramsay;
    }

    public void setRamsay(String ramsay) {
        this.ramsay = ramsay;
    }

    public String getRass() {
        return rass;
    }

    public void setRass(String rass) {
        this.rass = rass;
    }
}
