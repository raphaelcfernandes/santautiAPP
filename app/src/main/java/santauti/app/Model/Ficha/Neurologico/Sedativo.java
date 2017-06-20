package santauti.app.Model.Ficha.Neurologico;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 02-Jun-17.
 */

public class Sedativo extends RealmObject implements Serializable{
    @SerializedName("tipoSedativo")
    private String tipoSedativo;
    @SerializedName("doseSedativo")
    private int doseSedativo;

    public String getTipoSedativo() {
        return tipoSedativo;
    }

    public void setTipoSedativo(String tipoSedativo) {
        this.tipoSedativo = tipoSedativo;
    }

    public int getDoseSedativo() {
        return doseSedativo;
    }

    public void setDoseSedativo(int doseSedativo) {
        this.doseSedativo = doseSedativo;
    }
}
