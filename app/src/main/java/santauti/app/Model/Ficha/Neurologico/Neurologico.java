package santauti.app.Model.Ficha.Neurologico;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 01-Jun-17.
 */

public class Neurologico extends RealmObject implements Serializable{
    @SerializedName("nivelConsciencia")
    private String nivelConsciencia;
    @SerializedName("tamanhoPupila")
    private String tamanhoPupila;
    @SerializedName("simetriaPupila")
    private String simetriaPupila;
    @SerializedName("reatividaeLuzPupila")
    private String reatividadeLuzPupila;
    @SerializedName("diferencaPupilar")
    private String diferencaPupilar;
    @SerializedName("isSedado")
    private boolean isSedado;
    @SerializedName("sedado")
    private Sedado sedado;
    @SerializedName("naoSedado")
    private NaoSedado naoSedado;
    @SerializedName("opcionais")
    private Opcionais opcionais;
    @SerializedName("deficitMotor")
    private boolean deficitMotor;
    @SerializedName("tipoDeficit")
    private String tipoDecifit;
    @SerializedName("ladoDeficit")
    private String ladoDeficit;

    public String getDiferencaPupilar() {
        return diferencaPupilar;
    }

    public void setDiferencaPupilar(String diferencaPupilar) {
        this.diferencaPupilar = diferencaPupilar;
    }

    public boolean getDeficitMotor() {
        return deficitMotor;
    }

    public void setDeficitMotor(boolean deficitMotor) {
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

    public boolean getIsSedado() {
        return isSedado;
    }

    public void setIsSedado(boolean isSedado) {
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
