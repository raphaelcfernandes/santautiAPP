package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by rapha on 05-Oct-17.
 */

public class Respirador extends RealmObject implements Serializable{
    @SerializedName("emVentilacaoMecanica")
    private boolean emVentilacaoMecanica;
    @SerializedName("modoVentilatorio")
    private String modoVentilatorio;
    @SerializedName("volume")
    private int volume;
    @SerializedName("peep")
    private int peep;
    @SerializedName("fio2")
    private int fio2;
    @SerializedName("freqRespiratoriaPaciente")
    private int freqRespiratoriaPaciente;
    @SerializedName("freqRespiratoriaRespirador")
    private int freqRespiratoriaRespirador;
    @SerializedName("ipap")
    private int ipap;
    @SerializedName("epap")
    private int epap;
    @SerializedName("saturacao")
    private int saturacao;
    @SerializedName("oxigenio")
    private int oxigenio;
    @SerializedName("parametros")
    private String parametros;

    public boolean checkObject(){
        if(emVentilacaoMecanica){
            if(modoVentilatorio!=null){
                switch (modoVentilatorio) {
                    case "Não-invasiva":
                        return volume > 0 && peep > 0 && fio2 > 0 && freqRespiratoriaRespirador > 0 && freqRespiratoriaPaciente > 0;
                    case "BIPAP":
                        return volume > 0 && ipap > 0 && epap > 0 && saturacao > 0 && oxigenio > 0;
                    case "Mecânica":
                        return parametros != null && fio2 > 0 && peep > 0 && volume > 0 && freqRespiratoriaPaciente > 0 && freqRespiratoriaRespirador > 0;
                }
            }
            return false;
        }
        return true;
    }

    public boolean isEmVentilacaoMecanica() {
        return emVentilacaoMecanica;
    }

    public void setEmVentilacaoMecanica(boolean emVentilacaoMecanica) {
        this.emVentilacaoMecanica = emVentilacaoMecanica;
    }

    public String getModoVentilatorio() {
        return modoVentilatorio;
    }

    public void setModoVentilatorio(String modoVentilatorio) {
        this.modoVentilatorio = modoVentilatorio;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPeep() {
        return peep;
    }

    public void setPeep(int peep) {
        this.peep = peep;
    }

    public int getFio2() {
        return fio2;
    }

    public void setFio2(int fio2) {
        this.fio2 = fio2;
    }

    public int getFreqRespiratoriaPaciente() {
        return freqRespiratoriaPaciente;
    }

    public void setFreqRespiratoriaPaciente(int freqRespiratoriaPaciente) {
        this.freqRespiratoriaPaciente = freqRespiratoriaPaciente;
    }

    public int getFreqRespiratoriaRespirador() {
        return freqRespiratoriaRespirador;
    }

    public void setFreqRespiratoriaRespirador(int freqRespiratoriaRespirador) {
        this.freqRespiratoriaRespirador = freqRespiratoriaRespirador;
    }

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

    public int getOxigenio() {
        return oxigenio;
    }

    public void setOxigenio(int oxigenio) {
        this.oxigenio = oxigenio;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }
}
