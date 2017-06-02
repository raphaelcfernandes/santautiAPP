package santauti.app.Model.Ficha.Neurologico;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 01-Jun-17.
 */

public class Neurologico extends RealmObject{
    private String nivelConsciencia;
    private String tamanhoPupila;
    private String simetriaPupila;
    private String reatividadeLuzPupila;
    private int isSedado;
    private Sedado sedado;
    private NaoSedado naoSedado;
    private Opcionais opcionais;
    private int deficitMotor;
    private String tipoDecifit;
    private String ladoDeficit;

    public int getDeficitMotor() {
        return deficitMotor;
    }

    public void setDeficitMotor(int deficitMotor) {
        this.deficitMotor = deficitMotor;
    }

    public String getTipoDecifit() {
        return tipoDecifit;
    }

    public void setTipoDecifit(String tipoDecifit) {
        this.tipoDecifit = tipoDecifit;
    }

    public String getLadoDeficit() {
        return ladoDeficit;
    }

    public void setLadoDeficit(String ladoDeficit) {
        this.ladoDeficit = ladoDeficit;
    }

    public Opcionais getOpcionais() {
        return opcionais;
    }

    public void setOpcionais(Opcionais opcionais) {
        this.opcionais = opcionais;
    }

    public String getNivelConsciencia() {
        return nivelConsciencia;
    }

    public void setNivelConsciencia(String nivelConsciencia) {
        this.nivelConsciencia = nivelConsciencia;
    }

    public String getTamanhoPupila() {
        return tamanhoPupila;
    }

    public void setTamanhoPupila(String tamanhoPupila) {
        this.tamanhoPupila = tamanhoPupila;
    }

    public String getSimetriaPupila() {
        return simetriaPupila;
    }

    public void setSimetriaPupila(String simetriaPupila) {
        this.simetriaPupila = simetriaPupila;
    }

    public String getReatividadeLuzPupila() {
        return reatividadeLuzPupila;
    }

    public void setReatividadeLuzPupila(String reatividadeLuzPupila) {
        this.reatividadeLuzPupila = reatividadeLuzPupila;
    }

    public int getIsSedado() {
        return isSedado;
    }

    public void setIsSedado(int isSedado) {
        this.isSedado = isSedado;
    }

    public Sedado getSedado() {
        return sedado;
    }

    public void setSedado(Sedado sedado) {
        this.sedado = sedado;
    }

    public NaoSedado getNaoSedado() {
        return naoSedado;
    }

    public void setNaoSedado(NaoSedado naoSedado) {
        this.naoSedado = naoSedado;
    }
}
