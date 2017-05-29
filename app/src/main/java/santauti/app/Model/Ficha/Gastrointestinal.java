package santauti.app.Model.Ficha;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 29-May-17.
 */

public class Gastrointestinal extends RealmObject {
    private String formato;
    private String tensao;
    private String ruidos;
    private int vcm;

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getTensao() {
        return tensao;
    }

    public void setTensao(String tensao) {
        this.tensao = tensao;
    }

    public String getRuidos() {
        return ruidos;
    }

    public void setRuidos(String ruidos) {
        this.ruidos = ruidos;
    }

    public int getVcm() {
        return vcm;
    }

    public void setVcm(int vcm) {
        this.vcm = vcm;
    }
}
