package santauti.app.Model.Ficha;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 26-May-17.
 */

public class Renal extends RealmObject {
    private int diurese;
    private int peso;
    private int balancoHidrico;
    private int dialise;

    public int getDiurese() {
        return diurese;
    }

    public void setDiurese(int diurese) {
        this.diurese = diurese;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getBalancoHidrico() {
        return balancoHidrico;
    }

    public void setBalancoHidrico(int balancoHidrico) {
        this.balancoHidrico = balancoHidrico;
    }

    public int getDialise() {
        return dialise;
    }

    public void setDialise(int dialise) {
        this.dialise = dialise;
    }
}
