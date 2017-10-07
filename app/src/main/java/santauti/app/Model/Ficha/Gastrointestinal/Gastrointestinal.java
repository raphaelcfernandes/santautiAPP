package santauti.app.Model.Ficha.Gastrointestinal;

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
    private boolean massasPalpaveisFlag;
    @SerializedName("viscerasPalpaveis")
    private RealmList<RealmString> viscerasPalpaveis;
    private boolean viscerasPalpaveisFlag;
    @SerializedName("ostomias")
    private RealmList<Ostomias> ostomias;
    private boolean ostomiasFlag;

    public boolean checkObject(){
        boolean flagoostomia=true;
        boolean viscerasFlag=true;
        boolean massasFlag=true;
        if(ostomiasFlag && ostomias.isEmpty())
            flagoostomia=false;
        if(viscerasPalpaveisFlag && viscerasPalpaveis.isEmpty())
            viscerasFlag=false;
        if(massasPalpaveisFlag && massasPalpaveis.isEmpty())
            massasFlag=false;
        return ruidos!=null && formato!=null && tensao!=null && ascite!=null && flagoostomia && viscerasFlag && massasFlag;
    }

    public boolean isMassasPalpaveisFlag() {
        return massasPalpaveisFlag;
    }

    public void setMassasPalpaveisFlag(boolean massasPalpaveisFlag) {
        this.massasPalpaveisFlag = massasPalpaveisFlag;
    }

    public boolean isViscerasPalpaveisFlag() {
        return viscerasPalpaveisFlag;
    }

    public void setViscerasPalpaveisFlag(boolean viscerasPalpaveisFlag) {
        this.viscerasPalpaveisFlag = viscerasPalpaveisFlag;
    }

    public boolean isOstomiasFlag() {
        return ostomiasFlag;
    }

    public void setOstomiasFlag(boolean ostomiasFlag) {
        this.ostomiasFlag = ostomiasFlag;
    }

    public RealmList<Ostomias> getOstomias() {
        return ostomias;
    }

    public void setOstomias(RealmList<Ostomias> ostomias) {
        this.ostomias = ostomias;
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
