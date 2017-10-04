package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by rapha on 03-Oct-17.
 */

public class Osteomuscular extends RealmObject implements Serializable {
    @SerializedName("tonusMuscular")
    private String tonusMuscular;
    @SerializedName("trofismoMuscular")
    private String trofismoMuscular;

    public boolean checkObject(){
        return tonusMuscular!=null && trofismoMuscular!=null;
    }

    public String getTonusMuscular() {
        return tonusMuscular;
    }

    public void setTonusMuscular(String tonusMuscular) {
        this.tonusMuscular = tonusMuscular;
    }

    public String getTrofismoMuscular() {
        return trofismoMuscular;
    }

    public void setTrofismoMuscular(String trofismoMuscular) {
        this.trofismoMuscular = trofismoMuscular;
    }
}
