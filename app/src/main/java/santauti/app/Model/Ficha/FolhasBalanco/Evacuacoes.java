package santauti.app.Model.Ficha.FolhasBalanco;

/**
 * Created by rapha on 06-Oct-17.
 */

public class Evacuacoes {
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
