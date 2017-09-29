package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 29-May-17.
 */

public class Hematologico extends RealmObject implements Serializable{
    @SerializedName("tromboprofilaxia")
    private String tromboprofilaxia;

    public String getTromboprofilaxia() {
        return tromboprofilaxia;
    }

    public void setTromboprofilaxia(String tromboprofilaxia) {
        this.tromboprofilaxia = tromboprofilaxia;
    }
}
