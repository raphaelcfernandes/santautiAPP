package santauti.app.Model.Ficha.Hemodinamico;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 16-Jun-17.
 */

public class Hemodinamico extends RealmObject implements Serializable{
    @SerializedName("ritmo")
    private String ritmo;
    @SerializedName("bulhas")
    private String bulhas;
    @SerializedName("freqCardiaca")
    private int freqCardiaca;
    @SerializedName("opcionais")
    private boolean opcionais;
    @SerializedName("pam")
    private int pam;
    @SerializedName("pvc")
    private int pvc;
    @SerializedName("swan_ganz")
    private int swan_ganz;
    @SerializedName("hemodinamicoOpcinais")
    private RealmList<HemodinamicoOpcional> hemodinamicoOpcionais;

    public boolean isOpcionais() {
        return opcionais;
    }

    public void setOpcionais(boolean opcionais) {
        this.opcionais = opcionais;
    }

    public RealmList<HemodinamicoOpcional> getHemodinamicoOpcionais() {
        return hemodinamicoOpcionais;
    }

    public void setHemodinamicoOpcionais(RealmList<HemodinamicoOpcional> hemodinamicoOpcionais) {
        this.hemodinamicoOpcionais = hemodinamicoOpcionais;
    }

    public String getRitmo() {
        return ritmo;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    public String getBulhas() {
        return bulhas;
    }

    public void setBulhas(String bulhas) {
        this.bulhas = bulhas;
    }

    public int getFreqCardiaca() {
        return freqCardiaca;
    }

    public void setFreqCardiaca(int freqCardiaca) {
        this.freqCardiaca = freqCardiaca;
    }

    public int getPam() {
        return pam;
    }

    public void setPam(int pam) {
        this.pam = pam;
    }

    public int getPvc() {
        return pvc;
    }

    public void setPvc(int pvc) {
        this.pvc = pvc;
    }

    public int getSwan_ganz() {
        return swan_ganz;
    }

    public void setSwan_ganz(int swan_ganz) {
        this.swan_ganz = swan_ganz;
    }
}
