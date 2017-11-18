package santauti.app.Model.Ficha;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raphael Fernandes on 21-Jun-17.
 */

public class MonitorMultiparametrico implements FichaMetodos{
    private String ritmo;
    private int frequenciaRespiratoria;
    private int frequenciaCardiaca;
    private int pam;
    private float temperatura;
    private int pic;
    private int ppc;
    private int pvc;
    private int swanGanz;
    private int capnometria;
    private int spo2=-1;
    private int sjo2=-1;

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> itens = new HashMap<>();
        Map<String,Object> finalResult = new HashMap<>();
        if(ritmo!=null)
            itens.put("ritmo",ritmo);
        if(frequenciaRespiratoria>0)
            itens.put("frequenciaRespiratoria",frequenciaRespiratoria);
        if(frequenciaCardiaca>0)
            itens.put("frequenciaCardiaca",frequenciaCardiaca);
        if(pam >0)
            itens.put("pam", pam);
        if(temperatura>0)
            itens.put("temperatura",temperatura);
        if(pic>0)
            itens.put("pic",pic);
        if(ppc>0)
            itens.put("ppc",ppc);
        if(pvc>0)
            itens.put("pvc",pvc);
        if(swanGanz>0)
            itens.put("swanGanz",swanGanz);
        if(capnometria>0)
            itens.put("capnometria",capnometria);
        if(spo2>0)
            itens.put("spo2",spo2);
        if(sjo2>0)
            itens.put("sjo2",sjo2);
        finalResult.put("MonitorMultiparametrico",itens);
        return finalResult;
    }

    public boolean checkObject(){
        return ritmo!=null && frequenciaRespiratoria>0 && frequenciaCardiaca>0 &&
                pam >0 && pvc>0 && ppc>0 && pic>0 && temperatura>0.0 &&
                swanGanz>0 && capnometria>0 && spo2 >=0 && sjo2>=0;
    }

    public MonitorMultiparametrico(String ritmo, int frequenciaRespiratoria, int frequenciaCardiaca, int PAM, float temperatura, int pic, int ppc, int pvc, int swanGanz, int capnometria, int spo2, int sjo2) {
        this.ritmo = ritmo;
        this.frequenciaRespiratoria = frequenciaRespiratoria;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.pam = PAM;
        this.temperatura = temperatura;
        this.pic = pic;
        this.ppc = ppc;
        this.pvc = pvc;
        this.swanGanz = swanGanz;
        this.capnometria = capnometria;
        this.spo2 = spo2;
        this.sjo2 = sjo2;
    }

    public MonitorMultiparametrico() {
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

    public int getPam() {
        return pam;
    }

    public void setPam(int pam) {
        this.pam = pam;
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
}
