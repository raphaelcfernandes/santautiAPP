package santauti.app.Model.Ficha.Respiratorio;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 30-May-17.
 */

public class RespiratorioNaoInvasiva extends RealmObject {
    private int ipap;
    private int epap;
    private int saturacao;

    public int getIpap() {
        return ipap;
    }

    public void setIpap(int ipap) {
        this.ipap = ipap;
    }

    public int getEpap() {
        return epap;
    }

    public void setEpap(int epap) {
        this.epap = epap;
    }

    public int getSaturacao() {
        return saturacao;
    }

    public void setSaturacao(int saturacao) {
        this.saturacao = saturacao;
    }
}
