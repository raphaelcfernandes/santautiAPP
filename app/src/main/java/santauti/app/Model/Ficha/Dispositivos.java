package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import santauti.app.Model.Ficha.RealmObjects.RealmString;

/**
 * Created by rapha on 01-Oct-17.
 */

public class Dispositivos extends RealmObject implements Serializable {
    @SerializedName("dispositivos")
    private RealmList<RealmString> nomeDispositivos;

    public RealmList<RealmString> getNomeDispositivos() {
        return nomeDispositivos;
    }

    public void setNomeDispositivos(RealmList<RealmString> nomeDispositivos) {
        this.nomeDispositivos = nomeDispositivos;
    }
}
