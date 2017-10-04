package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 21-Jun-17.
 */

public class MonitorMultiparametrico extends RealmObject implements Serializable {
    @SerializedName("ritmo")
    private String ritmo;
    @SerializedName("frequenciaRespiratoria")
    private int frequenciaRespiratoria;
    @SerializedName("frequenciaCardiaca")
    private int frequenciaCardiaca;
    @SerializedName("pam")
    private int PAM;
    @SerializedName("temperatura")
    private float temperatura;
    @SerializedName("pic")
    private int pic;
    @SerializedName("ppc")
    private int ppc;
    @SerializedName("pvc")
    private int pvc;
    @SerializedName("swanGanz")
    private int swanGanz;
    @SerializedName("capnometria")
    private int capnometria;
    @SerializedName("spo2")
    private int spo2=-1;
    @SerializedName("sjo2")
    private int sjo2=-1;

    public boolean checkObject(){
        return ritmo!=null && frequenciaRespiratoria>0 && frequenciaCardiaca>0 &&
                PAM>0 && pvc>0 && ppc>0 && pic>0 && temperatura>0.0 &&
                swanGanz>0 && capnometria>0 && spo2 >=0 && sjo2>=0;
    }

    public int getSpo2() {
        return spo2;
    }

    public void setSpo2(int spo2) {
        this.spo2 = spo2;
    }

    public int getSjo2() {
        return sjo2;
    }

    public void setSjo2(int sjo2) {
        this.sjo2 = sjo2;
    }

    public String getRitmo() {
        return ritmo;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    public int getFrequenciaRespiratoria() {
        return frequenciaRespiratoria;
    }

    public void setFrequenciaRespiratoria(int frequenciaRespiratoria) {
        this.frequenciaRespiratoria = frequenciaRespiratoria;
    }

    public int getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(int frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public int getPAM() {
        return PAM;
    }

    public void setPAM(int PAM) {
        this.PAM = PAM;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getPpc() {
        return ppc;
    }

    public void setPpc(int ppc) {
        this.ppc = ppc;
    }

    public int getPvc() {
        return pvc;
    }

    public void setPvc(int pvc) {
        this.pvc = pvc;
    }

    public int getSwanGanz() {
        return swanGanz;
    }

    public void setSwanGanz(int swanGanz) {
        this.swanGanz = swanGanz;
    }

    public int getCapnometria() {
        return capnometria;
    }

    public void setCapnometria(int capnometria) {
        this.capnometria = capnometria;
    }
}
