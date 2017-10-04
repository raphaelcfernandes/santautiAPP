package santauti.app.Model.Ficha;

import android.content.res.Resources;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import santauti.app.Model.Ficha.RealmObjects.RealmString;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 30-May-17.
 */

public class Respiratorio extends RealmObject implements Serializable{
    @SerializedName("viasAereas")
    private String viasAereas;
    @SerializedName("pressaoCuff")
    private int pressaoCuff;
    @SerializedName("localizacaoCanula")
    private String localizacaoCanula;
    @SerializedName("murmurioVesicular")
    private String murmurioVesicular;
    @SerializedName("localizacaoMurmurioVesicular")
    private RealmList<RealmString> localizacaoMurmurioVesicular;
    @SerializedName("usoOxigenio")
    private String usoOxigenio;
    @SerializedName("mascaraVenturi")
    private int mascaraVenturi;
    @SerializedName("fluxo")
    private int fluxo;
    @SerializedName("roncos")
    private RealmList<RealmString> roncos;
    @SerializedName("sibilos")
    private RealmList<RealmString> sibilos;
    @SerializedName("crepitacoes")
    private RealmList<RealmString> crepitacoes;

    public boolean checkObject(){
        boolean viasAereas=false;
        boolean murmurioVesicular=false;
        boolean usoOxigenio=false;
        if(this.viasAereas!=null){
            if(this.viasAereas.equals("Natural"))
                viasAereas=true;
            else{
                if(localizacaoCanula!=null && pressaoCuff >0)
                    viasAereas=true;
            }
        }
        if(this.murmurioVesicular!=null){
            if(this.murmurioVesicular.equals("Fisiológico"))
                murmurioVesicular=true;
            else{
                if(!localizacaoMurmurioVesicular.isEmpty())
                    murmurioVesicular=true;
            }
        }
        if(this.usoOxigenio!=null){
            if(this.usoOxigenio.equals("Não"))
                usoOxigenio=true;
            else{
                if (mascaraVenturi>0 || fluxo>0)
                    usoOxigenio=true;
            }
        }
        return viasAereas && murmurioVesicular && usoOxigenio;
    }

    public String getViasAereas() {
        return viasAereas;
    }

    public void setViasAereas(String viasAereas) {
        this.viasAereas = viasAereas;
    }

    public int getPressaoCuff() {
        return pressaoCuff;
    }

    public void setPressaoCuff(int pressaoCuff) {
        this.pressaoCuff = pressaoCuff;
    }

    public String getLocalizacaoCanula() {
        return localizacaoCanula;
    }

    public void setLocalizacaoCanula(String localizacaoCanula) {
        this.localizacaoCanula = localizacaoCanula;
    }

    public String getMurmurioVesicular() {
        return murmurioVesicular;
    }

    public void setMurmurioVesicular(String murmurioVesicular) {
        this.murmurioVesicular = murmurioVesicular;
    }

    public RealmList<RealmString> getLocalizacaoMurmurioVesicular() {
        return localizacaoMurmurioVesicular;
    }

    public void setLocalizacaoMurmurioVesicular(RealmList<RealmString> localizacaoMurmurioVesicular) {
        this.localizacaoMurmurioVesicular = localizacaoMurmurioVesicular;
    }

    public String getUsoOxigenio() {
        return usoOxigenio;
    }

    public void setUsoOxigenio(String usoOxigenio) {
        this.usoOxigenio = usoOxigenio;
    }

    public int getMascaraVenturi() {
        return mascaraVenturi;
    }

    public void setMascaraVenturi(int mascaraVenturi) {
        this.mascaraVenturi = mascaraVenturi;
    }

    public int getFluxo() {
        return fluxo;
    }

    public void setFluxo(int fluxo) {
        this.fluxo = fluxo;
    }

    public RealmList<RealmString> getRoncos() {
        return roncos;
    }

    public void setRoncos(RealmList<RealmString> roncos) {
        this.roncos = roncos;
    }

    public RealmList<RealmString> getSibilos() {
        return sibilos;
    }

    public void setSibilos(RealmList<RealmString> sibilos) {
        this.sibilos = sibilos;
    }

    public RealmList<RealmString> getCrepitacoes() {
        return crepitacoes;
    }

    public void setCrepitacoes(RealmList<RealmString> crepitacoes) {
        this.crepitacoes = crepitacoes;
    }
}

