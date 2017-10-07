package santauti.app.Model.Ficha.Gastrointestinal;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by rapha on 07-Oct-17.
 */

public class Ostomias extends RealmObject implements Serializable {
    private String tipoOstomia;
    private String qualidadeOstomia;
    private String funcionamentoOstomia;

    public String getTipoOstomia() {
        return tipoOstomia;
    }

    public void setTipoOstomia(String tipoOstomia) {
        this.tipoOstomia = tipoOstomia;
    }

    public String getQualidadeOstomia() {
        return qualidadeOstomia;
    }

    public void setQualidadeOstomia(String qualidadeOstomia) {
        this.qualidadeOstomia = qualidadeOstomia;
    }

    public String getFuncionamentoOstomia() {
        return funcionamentoOstomia;
    }

    public void setFuncionamentoOstomia(String funcionamentoOstomia) {
        this.funcionamentoOstomia = funcionamentoOstomia;
    }
}
