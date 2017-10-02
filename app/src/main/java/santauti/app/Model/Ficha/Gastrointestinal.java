package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import santauti.app.Model.Ficha.RealmObjects.RealmString;

/**
 * Created by Raphael Fernandes on 29-May-17.
 */

public class Gastrointestinal extends RealmObject implements Serializable {
    @SerializedName("formato")
    private String formato;
    @SerializedName("tensao")
    private String tensao;
    @SerializedName("ruidos")
    private String ruidos;
    @SerializedName("ascite")
    private String ascite;
    @SerializedName("massasPalpaveis")
    private RealmList<RealmString> massasPalpaveis;
    @SerializedName("viscerasPalpaveis")
    private RealmList<RealmString> viscerasPalpaveis;


    public boolean checkObject(){
        return getRuidos()!=null && getFormato()!=null && getTensao()!=null && getAscite()!=null;
    }
    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getTensao() {
        return tensao;
    }

    public void setTensao(String tensao) {
        this.tensao = tensao;
    }

    public String getRuidos() {
        return ruidos;
    }

    public void setRuidos(String ruidos) {
        this.ruidos = ruidos;
    }

    public String getAscite() {
        return ascite;
    }

    public void setAscite(String ascite) {
        this.ascite = ascite;
    }

    public RealmList<RealmString> getMassasPalpaveis() {
        return massasPalpaveis;
    }

    public void setMassasPalpaveis(RealmList<RealmString> massasPalpaveis) {
        this.massasPalpaveis = massasPalpaveis;
    }

    public RealmList<RealmString> getViscerasPalpaveis() {
        return viscerasPalpaveis;
    }

    public void setViscerasPalpaveis(RealmList<RealmString> viscerasPalpaveis) {
        this.viscerasPalpaveis = viscerasPalpaveis;
    }
}
