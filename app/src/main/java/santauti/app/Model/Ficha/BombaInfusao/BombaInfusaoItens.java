package santauti.app.Model.Ficha.BombaInfusao;

import io.realm.RealmObject;

/**
 * Created by rapha on 06-Oct-17.
 */

public class BombaInfusaoItens extends RealmObject {
    private String droga;
    private int velocidade;

    public String getDroga() {
        return droga;
    }

    public void setDroga(String droga) {
        this.droga = droga;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
}
