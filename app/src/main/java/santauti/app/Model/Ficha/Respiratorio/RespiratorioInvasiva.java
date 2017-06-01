package santauti.app.Model.Ficha.Respiratorio;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 30-May-17.
 */

public class RespiratorioInvasiva extends RealmObject {
    private int fio2;
    private int frequenciaRespiratoria;
    private int peep;
    private int pressaoCuff;
    private String modoVentilatorio;

    public int getFio2() {
        return fio2;
    }

    public void setFio2(int fio2) {
        this.fio2 = fio2;
    }

    public int getFrequenciaRespiratoria() {
        return frequenciaRespiratoria;
    }

    public void setFrequenciaRespiratoria(int frequenciaRespiratoria) {
        this.frequenciaRespiratoria = frequenciaRespiratoria;
    }

    public int getPeep() {
        return peep;
    }

    public void setPeep(int peep) {
        this.peep = peep;
    }

    public int getPressaoCuff() {
        return pressaoCuff;
    }

    public void setPressaoCuff(int pressaoCuff) {
        this.pressaoCuff = pressaoCuff;
    }

    public String getModoVentilatorio() {
        return modoVentilatorio;
    }

    public void setModoVentilatorio(String modoVentilatorio) {
        this.modoVentilatorio = modoVentilatorio;
    }
}
