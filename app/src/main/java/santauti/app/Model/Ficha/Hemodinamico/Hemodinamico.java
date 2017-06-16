package santauti.app.Model.Ficha.Hemodinamico;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 16-Jun-17.
 */

public class Hemodinamico extends RealmObject {
    private String ritmo;
    private String bulhas;
    private int freqCardiaca;
    private boolean opcionais;
    private int pam;
    private int pvc;
    private int swan_ganz;
    private RealmList<HemodinamicoOpcional> hemodinamicoOpcionals;

    public boolean isOpcionais() {
        return opcionais;
    }

    public void setOpcionais(boolean opcionais) {
        this.opcionais = opcionais;
    }

    public RealmList<HemodinamicoOpcional> getHemodinamicoOpcionals() {
        return hemodinamicoOpcionals;
    }

    public void setHemodinamicoOpcionals(RealmList<HemodinamicoOpcional> hemodinamicoOpcionals) {
        this.hemodinamicoOpcionals = hemodinamicoOpcionals;
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
