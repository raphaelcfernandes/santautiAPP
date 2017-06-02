package santauti.app.Model.Ficha.Neurologico;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 02-Jun-17.
 */

public class Sedativo extends RealmObject {
    private String tipoSedativo;
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
