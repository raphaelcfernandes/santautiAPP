package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 21-Jun-17.
 */

public class MonitorMultiparametrico extends RealmObject implements Serializable {
    @SerializedName("ritmo")
    private String ritmo;
    @SerializedName("freqCardiaca")
    private int freqCardiaca;
    @SerializedName("pressaoArterial")
    private int pressaoArterial;
    @SerializedName("saturacaoo2")
    private int saturacaoO2;
    @SerializedName("freqRespiratoria")
    private int freqRespiratoria;
    @SerializedName("temperatura")
    private int temperatura;

    public String getRitmo() {
        return ritmo;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    public int getFreqCardiaca() {
        return freqCardiaca;
    }

    public void setFreqCardiaca(int freqCardiaca) {
        this.freqCardiaca = freqCardiaca;
    }

    public int getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(int pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public int getSaturacaoO2() {
        return saturacaoO2;
    }

    public void setSaturacaoO2(int saturacaoO2) {
        this.saturacaoO2 = saturacaoO2;
    }

    public int getFreqRespiratoria() {
        return freqRespiratoria;
    }

    public void setFreqRespiratoria(int freqRespiratoria) {
        this.freqRespiratoria = freqRespiratoria;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
}
