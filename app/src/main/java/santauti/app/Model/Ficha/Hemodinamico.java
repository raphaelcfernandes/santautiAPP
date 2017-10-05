package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import santauti.app.Model.Ficha.RealmObjects.RealmString;

/**
 * Created by Raphael Fernandes on 16-Jun-17.
 */

public class Hemodinamico extends RealmObject implements Serializable{
    @SerializedName("pulso")
    private String pulso;
    @SerializedName("foneseBulhas")
    private String foneseBulhas;
    @SerializedName("tipoSopro")
    private RealmList<RealmString> tipoSopro;
    @SerializedName("intensidadeSopro")
    private int intensidadeSopro;
    @SerializedName("perfusaoCapilar")
    private String perfusaoCapilar;
    @SerializedName("extremidadesColoracao")
    private String extremidadesColoracao;
    @SerializedName("extremidadesTemperatura")
    private String extremidadesTemperatura;

    private boolean soproChecked;

    public boolean checkObject(){
        if(soproChecked)
            return pulso!=null && !tipoSopro.isEmpty() && foneseBulhas!=null && perfusaoCapilar!=null && extremidadesColoracao!=null
                    && intensidadeSopro>0 && extremidadesTemperatura!=null;
        else
            return pulso!=null && foneseBulhas!=null && perfusaoCapilar!=null && extremidadesColoracao!=null
                    && extremidadesTemperatura!=null;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getFoneseBulhas() {
        return foneseBulhas;
    }

    public void setFoneseBulhas(String foneseBulhas) {
        this.foneseBulhas = foneseBulhas;
    }

    public RealmList<RealmString> getTipoSopro() {
        return tipoSopro;
    }

    public void setTipoSopro(RealmList<RealmString> tipoSopro) {
        this.tipoSopro = tipoSopro;
    }

    public int getIntensidadeSopro() {
        return intensidadeSopro;
    }

    public void setIntensidadeSopro(int intensidadeSopro) {
        this.intensidadeSopro = intensidadeSopro;
    }

    public String getPerfusaoCapilar() {
        return perfusaoCapilar;
    }

    public void setPerfusaoCapilar(String perfusaoCapilar) {
        this.perfusaoCapilar = perfusaoCapilar;
    }

    public String getExtremidadesColoracao() {
        return extremidadesColoracao;
    }

    public void setExtremidadesColoracao(String extremidadesColoracao) {
        this.extremidadesColoracao = extremidadesColoracao;
    }

    public String getExtremidadesTemperatura() {
        return extremidadesTemperatura;
    }

    public void setExtremidadesTemperatura(String extremidadesTemperatura) {
        this.extremidadesTemperatura = extremidadesTemperatura;
    }

    public boolean isSoproChecked() {
        return soproChecked;
    }

    public void setSoproChecked(boolean soproChecked) {
        this.soproChecked = soproChecked;
    }
}
