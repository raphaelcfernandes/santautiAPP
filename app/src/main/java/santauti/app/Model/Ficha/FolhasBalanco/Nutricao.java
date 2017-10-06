package santauti.app.Model.Ficha.FolhasBalanco;

import io.realm.RealmObject;

/**
 * Created by rapha on 06-Oct-17.
 */

public class Nutricao extends RealmObject {
    private String tipoNutricao;
    private int volume;

    public String getTipoNutricao() {
        return tipoNutricao;
    }

    public void setTipoNutricao(String tipoNutricao) {
        this.tipoNutricao = tipoNutricao;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
