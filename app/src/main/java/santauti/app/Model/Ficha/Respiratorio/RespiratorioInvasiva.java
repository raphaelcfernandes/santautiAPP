package santauti.app.Model.Ficha.Respiratorio;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 30-May-17.
 */

public class RespiratorioInvasiva extends RealmObject implements Serializable{
    @SerializedName("fio2")
    private int fio2;
    @SerializedName("frequenciaRespiratoria")
    private int frequenciaRespiratoria;
    @SerializedName("peep")
    private int peep;
    @SerializedName("pressaoCuff")
    private int pressaoCuff;
    @SerializedName("modoVentilatorio")
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
