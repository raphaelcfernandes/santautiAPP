package santauti.app.Model.Ficha;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 29-May-17.
 */

public class Hematologico extends RealmObject {
    private String tipoMedicamento;
    private int tromboprofilaxia;
    private int hemograma;

    public String getTipoMedicamento() {
        return tipoMedicamento;
    }

    public void setTipoMedicamento(String tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }

    public int getTromboprofilaxia() {
        return tromboprofilaxia;
    }

    public void setTromboprofilaxia(int tromboprofilaxia) {
        this.tromboprofilaxia = tromboprofilaxia;
    }

    public int getHemograma() {
        return hemograma;
    }

    public void setHemograma(int hemograma) {
        this.hemograma = hemograma;
    }
}
