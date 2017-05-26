package santauti.app.Model.Ficha;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 26-May-17.
 */

public class Endocrino extends RealmObject {
    private int usoDeInsulinaBombaInfusao;

    public int getUsoDeInsulinaBombaInfusao() {
        return usoDeInsulinaBombaInfusao;
    }

    public void setUsoDeInsulinaBombaInfusao(int usoDeInsulinaBombaInfusao) {
        this.usoDeInsulinaBombaInfusao = usoDeInsulinaBombaInfusao;
    }
}
