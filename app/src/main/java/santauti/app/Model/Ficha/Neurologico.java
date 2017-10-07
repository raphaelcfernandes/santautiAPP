package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import santauti.app.Model.Ficha.RealmObjects.RealmString;

/**
 * Created by Raphael Fernandes on 01-Jun-17.
 */

public class Neurologico extends RealmObject implements Serializable{
    @SerializedName("nivelConsciencia")
    private String nivelConsciencia;
    @SerializedName("sedado")
    private boolean sedado;
    @SerializedName("ramsay")
    private int ramsay;
    @SerializedName("rass")
    private String rass;
    @SerializedName("aberturaOcular")
    private int aberturaOcular;
    @SerializedName("repostaVerbal")
    private int respostaVerbal;
    @SerializedName("respostaMotora")
    private int respostaMotora;
    @SerializedName("orientacaoTemporoEspacial")
    private String orientacaoTemporoEspacial;
    @SerializedName("tipoDesorientacao")
    private RealmList<RealmString> tipoDesorientacao;
    @SerializedName("deficitMotor")
    private boolean deficitMotor;
    @SerializedName("mse")
    private String mse;
    @SerializedName("msd")
    private String msd;
    @SerializedName("mie")
    private String mie;
    @SerializedName("mid")
    private String mid;
    @SerializedName("tamanhoPupila")
    private String tamanhoPupila;
    @SerializedName("simetriaPupila")
    private String simetriaPupila;
    @SerializedName("diferencaPupila")
    private String diferencaPupila;
    @SerializedName("reatividadeLuzPupila")
    private String reatividadeLuzPupila;
    @SerializedName("flutuacaoEstadoMental")
    private boolean flutuacaoEstadoMental;
    @SerializedName("inatencao")
    private boolean inatencao;
    @SerializedName("pensamentoDesorganizado")
    private boolean pensamentoDesorganizado;

//    public boolean checkObject(){
//        boolean sedadoFlag=false;
//        boolean deficitMotorFlag=false;
//        boolean orientacaoTemporoEspacialFlag=false;
//        if(sedado && ramsay>0 && rass!=null)
//            sedadoFlag=true;
//        if(orientacaoTemporoEspacial!=null)
//            if(orientacaoTemporoEspacial.equals("Desorientado") && !tipoDesorientacao.isEmpty())
//                orientacaoTemporoEspacialFlag=true;
//        if(simetriaPupila!=null) {
//            if (simetriaPupila.equals("Anisocóricas"))
//                return (sedadoFlag || nivelConsciencia != null) && aberturaOcular > 0 && respostaVerbal > 0 && respostaMotora > 0
//                        && (orientacaoTemporoEspacialFlag || orientacaoTemporoEspacial != null) && tamanhoPupila != null && diferencaPupila != null
//                        && reatividadeLuzPupila != null;
//        }
//        else
//            return (sedadoFlag || nivelConsciencia!=null) && aberturaOcular>0 && respostaVerbal>0 && respostaMotora>0
//                    && (orientacaoTemporoEspacialFlag || orientacaoTemporoEspacial!=null) && tamanhoPupila!=null && simetriaPupila!=null
//                    && reatividadeLuzPupila!=null;
//    }


    public String getNivelConsciencia() {
        return nivelConsciencia;
    }

    public void setNivelConsciencia(String nivelConsciencia) {
        this.nivelConsciencia = nivelConsciencia;
    }

    public boolean isSedado() {
        return sedado;
    }

    public void setSedado(boolean sedado) {
        this.sedado = sedado;
    }

    public int getRamsay() {
        return ramsay;
    }

    public void setRamsay(int ramsay) {
        this.ramsay = ramsay;
    }

    public String getRass() {
        return rass;
    }

    public void setRass(String rass) {
        this.rass = rass;
    }

    public int getAberturaOcular() {
        return aberturaOcular;
    }

    public void setAberturaOcular(int aberturaOcular) {
        this.aberturaOcular = aberturaOcular;
    }

    public int getRespostaVerbal() {
        return respostaVerbal;
    }

    public void setRespostaVerbal(int respostaVerbal) {
        this.respostaVerbal = respostaVerbal;
    }

    public int getRespostaMotora() {
        return respostaMotora;
    }

    public void setRespostaMotora(int respostaMotora) {
        this.respostaMotora = respostaMotora;
    }

    public String getOrientacaoTemporoEspacial() {
        return orientacaoTemporoEspacial;
    }

    public void setOrientacaoTemporoEspacial(String orientacaoTemporoEspacial) {
        this.orientacaoTemporoEspacial = orientacaoTemporoEspacial;
    }

    public RealmList<RealmString> getTipoDesorientacao() {
        return tipoDesorientacao;
    }

    public void setTipoDesorientacao(RealmList<RealmString> tipoDesorientacao) {
        this.tipoDesorientacao = tipoDesorientacao;
    }

    public boolean isDeficitMotor() {
        return deficitMotor;
    }

    public void setDeficitMotor(boolean deficitMotor) {
        this.deficitMotor = deficitMotor;
    }

    public String getMse() {
        return mse;
    }

    public void setMse(String mse) {
        this.mse = mse;
    }

    public String getMsd() {
        return msd;
    }

    public void setMsd(String msd) {
        this.msd = msd;
    }

    public String getMie() {
        return mie;
    }

    public void setMie(String mie) {
        this.mie = mie;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public String getDiferencaPupila() {
        return diferencaPupila;
    }

    public void setDiferencaPupila(String diferencaPupila) {
        this.diferencaPupila = diferencaPupila;
    }

    public String getReatividadeLuzPupila() {
        return reatividadeLuzPupila;
    }

    public void setReatividadeLuzPupila(String reatividadeLuzPupila) {
        this.reatividadeLuzPupila = reatividadeLuzPupila;
    }

    public boolean isFlutuacaoEstadoMental() {
        return flutuacaoEstadoMental;
    }

    public void setFlutuacaoEstadoMental(boolean flutuacaoEstadoMental) {
        this.flutuacaoEstadoMental = flutuacaoEstadoMental;
    }

    public boolean isInatencao() {
        return inatencao;
    }

    public void setInatencao(boolean inatencao) {
        this.inatencao = inatencao;
    }

    public boolean isPensamentoDesorganizado() {
        return pensamentoDesorganizado;
    }

    public void setPensamentoDesorganizado(boolean pensamentoDesorganizado) {
        this.pensamentoDesorganizado = pensamentoDesorganizado;
    }
}
