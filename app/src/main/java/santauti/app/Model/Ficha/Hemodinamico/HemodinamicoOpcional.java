package santauti.app.Model.Ficha.Hemodinamico;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 16-Jun-17.
 */

public class HemodinamicoOpcional extends RealmObject {
    private String droga;
    private int dose;

    public String getDroga() {
        return droga;
    }

    public void setDroga(String droga) {
        this.droga = droga;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }
}

