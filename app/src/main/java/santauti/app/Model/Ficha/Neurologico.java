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
    @SerializedName("ramsay")
    private int ramsay;
    @SerializedName("rass")
    private int rass;
    @SerializedName("aberturaOcular")
    private int aberturaOcular;
    @SerializedName("repostaVerbal")
    private int respostaVerbal;
    @SerializedName("respostaMotora")
    private int respostaMotora;
    @SerializedName("orientacaoTemporoEspacial")
    private RealmList<RealmString> orientadoTemporoEspacial;
    @SerializedName("desorientadoTemporoEspacial")
    private RealmList<RealmString> desorientadoTemporoEspacial;
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
    @SerializedName("deliriumCAMICU")
    private String deliciumCAMICU;
    private boolean noTempo;
    private boolean noEspaco;

    public boolean checkObject(){
        boolean nivelConscienciaFlag=false;
        boolean deficitMotorFlag=false;
        boolean avaliacaoPupilar=true;
        if(nivelConsciencia!=null){
            if(!nivelConsciencia.equals("SEDADO"))
                nivelConscienciaFlag=true;
            else
                if(ramsay>=0 && rass>-6)
                    nivelConscienciaFlag=true;
        }
        if(this.deficitMotor && (mse!=null || msd!=null || mie!=null ||mid!=null))
            deficitMotorFlag=true;
        if(!deficitMotor)
            deficitMotorFlag=true;
        if(simetriaPupila!=null) {
            if (simetriaPupila.equals("Anisocóricas"))
                if (tamanhoPupila != null && diferencaPupila!=null && reatividadeLuzPupila!=null)
                    avaliacaoPupilar=true;
            else
                if(tamanhoPupila != null && reatividadeLuzPupila!=null)
                    avaliacaoPupilar=true;
        }
        return nivelConscienciaFlag && aberturaOcular>0 && respostaMotora>0 && respostaVerbal>0 && noTempo && noEspaco
               && deficitMotorFlag && avaliacaoPupilar && deliciumCAMICU!=null;
    }


    public boolean isNoTempo() {
        return noTempo;
    }

    public void setNoTempo(boolean noTempo) {
        this.noTempo = noTempo;
    }

    public boolean isNoEspaco() {
        return noEspaco;
    }

    public void setNoEspaco(boolean noEspaco) {
        this.noEspaco = noEspaco;
    }

    public String getDeliciumCAMICU() {
        return deliciumCAMICU;
    }

    public void setDeliciumCAMICU(String deliciumCAMICU) {
        this.deliciumCAMICU = deliciumCAMICU;
    }

    public String getNivelConsciencia() {
        return nivelConsciencia;
    }

    public void setNivelConsciencia(String nivelConsciencia) {
        this.nivelConsciencia = nivelConsciencia;
    }


    public int getRamsay() {
        return ramsay;
    }

    public void setRamsay(int ramsay) {
        this.ramsay = ramsay;
    }

    public int getRass() {
        return rass;
    }

    public void setRass(int rass) {
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

    public RealmList<RealmString> getOrientadoTemporoEspacial() {
        return orientadoTemporoEspacial;
    }

    public void setOrientadoTemporoEspacial(RealmList<RealmString> orientadoTemporoEspacial) {
        this.orientadoTemporoEspacial = orientadoTemporoEspacial;
    }

    public RealmList<RealmString> getDesorientadoTemporoEspacial() {
        return desorientadoTemporoEspacial;
    }

    public void setDesorientadoTemporoEspacial(RealmList<RealmString> desorientadoTemporoEspacial) {
        this.desorientadoTemporoEspacial = desorientadoTemporoEspacial;
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
}
