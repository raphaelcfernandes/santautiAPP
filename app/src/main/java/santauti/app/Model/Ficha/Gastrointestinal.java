package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

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
    private boolean massasPalpaveis=false;
    @SerializedName("viscerasPalpaveis")
    private boolean viscerasPalpaveis=false;

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

    public boolean isMassasPalpaveis() {
        return massasPalpaveis;
    }

    public void setMassasPalpaveis(boolean massasPalpaveis) {
        this.massasPalpaveis = massasPalpaveis;
    }

    public boolean isViscerasPalpaveis() {
        return viscerasPalpaveis;
    }

    public void setViscerasPalpaveis(boolean viscerasPalpaveis) {
        this.viscerasPalpaveis = viscerasPalpaveis;
    }
}
