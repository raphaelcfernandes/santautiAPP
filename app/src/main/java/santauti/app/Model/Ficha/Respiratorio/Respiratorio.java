package santauti.app.Model.Ficha.Respiratorio;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 30-May-17.
 */

public class Respiratorio extends RealmObject {
    private int invasiva;
    private int volume;
    private RespiratorioInvasiva respiratorioInvasiva;
    private RespiratorioNaoInvasiva respiratorioNaoInvasiva;

    public int getInvasiva() {
        return invasiva;
    }

    public void setInvasiva(int invasiva) {
        this.invasiva = invasiva;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public RespiratorioInvasiva getRespiratorioInvasiva() {
        return respiratorioInvasiva;
    }

    public void setRespiratorioInvasiva(RespiratorioInvasiva respiratorioInvasiva) {
        this.respiratorioInvasiva = respiratorioInvasiva;
    }

    public RespiratorioNaoInvasiva getRespiratorioNaoInvasiva() {
        return respiratorioNaoInvasiva;
    }

    public void setRespiratorioNaoInvasiva(RespiratorioNaoInvasiva respiratorioNaoInvasiva) {
        this.respiratorioNaoInvasiva = respiratorioNaoInvasiva;
    }
}
