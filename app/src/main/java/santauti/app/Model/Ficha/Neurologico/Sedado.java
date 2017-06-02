package santauti.app.Model.Ficha.Neurologico;

import java.util.HashMap;
import java.util.Map;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 02-Jun-17.
 */

public class Sedado extends RealmObject {
    private String ramsay;
    private String rass;
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
