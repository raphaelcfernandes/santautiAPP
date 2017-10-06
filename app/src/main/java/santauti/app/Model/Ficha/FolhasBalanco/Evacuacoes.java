package santauti.app.Model.Ficha.FolhasBalanco;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by rapha on 06-Oct-17.
 */

public class Evacuacoes extends RealmObject {
    private String tipoEvacuacao;
    private int eventos;

    public int getEventos() {
        return eventos;
    }

    public void setEventos(int eventos) {
        this.eventos = eventos;
    }

    public String getTipoEvacuacao() {
        return tipoEvacuacao;
    }

    public void setTipoEvacuacao(String tipoEvacuacao) {
        this.tipoEvacuacao = tipoEvacuacao;
    }
}
