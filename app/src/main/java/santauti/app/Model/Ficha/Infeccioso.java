package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import santauti.app.Model.Ficha.RealmObjects.RealmString;

/**
 * Created by Raphael Fernandes on 26-May-17.
 */

public class Infeccioso extends RealmObject implements Serializable{
    @SerializedName("antibioticos")
    private RealmList<RealmString> antibioticos;

    public RealmList<RealmString> getAntibioticos() {
        return antibioticos;
    }

    public void setAntibioticos(RealmList<RealmString> antibioticos) {
        this.antibioticos = antibioticos;
    }
}
