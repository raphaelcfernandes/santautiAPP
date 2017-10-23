package santauti.app.Model.Ficha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raphael Fernandes on 01-Jun-17.
 */

public class Neurologico implements FichaMetodos{
    private String nivelConsciencia;
    private int ramsay;
    private int rass;
    private int aberturaOcular;
    private int respostaVerbal;
    private int respostaMotora;
    private List<String> orientadoTemporoEspacial;
    private List<String> desorientadoTemporoEspacial;
    private boolean deficitMotor;
    private String mse;
    private String msd;
    private String mie;
    private String mid;
    private String tamanhoPupila;
    private String simetriaPupila;
    private String diferencaPupila;
    private String reatividadeLuzPupila;
    private String deliciumCAMICU;
    private boolean noTempo;
    private boolean noEspaco;

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> itens = new HashMap<>();
        Map<String,Object> finalResult = new HashMap<>();
        if(nivelConsciencia!=null){
            itens.put("nivelConsciencia",nivelConsciencia);
            if(nivelConsciencia.equals("SEDADO")){
                if(ramsay>=0)
                    itens.put("ramsay",ramsay);
            }
        }
        if(rass>-6)
            itens.put("rass",rass);
        if(aberturaOcular>0)
            itens.put("abertaOcular",aberturaOcular);
        if(respostaVerbal>0)
            itens.put("respostaVerbal",respostaVerbal);
        if(respostaMotora>0)
            itens.put("respostaMotora",respostaMotora);
        if(orientadoTemporoEspacial!=null){
            if(!orientadoTemporoEspacial.isEmpty())
                for(String string : orientadoTemporoEspacial)
                    itens.put(string,"orientado");
        }
        if(desorientadoTemporoEspacial!=null){
            if(!desorientadoTemporoEspacial.isEmpty())
                for(String string : desorientadoTemporoEspacial)
                    itens.put(string,"desorientado");
        }
        if(deficitMotor){
            if(mse!=null)
                itens.put("mse",mse);
            if(msd!=null)
                itens.put("msd",msd);
            if(mie!=null)
                itens.put("mie",mie);
            if(mid!=null)
                itens.put("mid",mid);
        }
        if(tamanhoPupila!=null)
            itens.put("tamanhoPupila",tamanhoPupila);
        if(simetriaPupila!=null) {
            itens.put("simetriaPupila",simetriaPupila);
            if (simetriaPupila.equals("Anisocóricas") && diferencaPupila!=null)
                itens.put("diferencaPupila",diferencaPupila);
        }
        if(reatividadeLuzPupila!=null)
            itens.put("reatividadeLuzPupila",reatividadeLuzPupila);
        if(deliciumCAMICU!=null)
            itens.put("deliriumCAMICU",deliciumCAMICU);
        finalResult.put("Neurologico",itens);
        return finalResult;
    }

    public void initializeLists(){
        if(desorientadoTemporoEspacial==null)
            desorientadoTemporoEspacial = new ArrayList<>();
        if(orientadoTemporoEspacial==null)
            orientadoTemporoEspacial = new ArrayList<>();
    }

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

    public Neurologico(String nivelConsciencia, int ramsay, int rass, int aberturaOcular, int respostaVerbal, int respostaMotora, List<String> orientadoTemporoEspacial, List<String> desorientadoTemporoEspacial, boolean deficitMotor, String mse, String msd, String mie, String mid, String tamanhoPupila, String simetriaPupila, String diferencaPupila, String reatividadeLuzPupila, String deliciumCAMICU, boolean noTempo, boolean noEspaco) {
        this.nivelConsciencia = nivelConsciencia;
        this.ramsay = ramsay;
        this.rass = rass;
        this.aberturaOcular = aberturaOcular;
        this.respostaVerbal = respostaVerbal;
        this.respostaMotora = respostaMotora;
        this.orientadoTemporoEspacial = orientadoTemporoEspacial;
        this.desorientadoTemporoEspacial = desorientadoTemporoEspacial;
        this.deficitMotor = deficitMotor;
        this.mse = mse;
        this.msd = msd;
        this.mie = mie;
        this.mid = mid;
        this.tamanhoPupila = tamanhoPupila;
        this.simetriaPupila = simetriaPupila;
        this.diferencaPupila = diferencaPupila;
        this.reatividadeLuzPupila = reatividadeLuzPupila;
        this.deliciumCAMICU = deliciumCAMICU;
        this.noTempo = noTempo;
        this.noEspaco = noEspaco;
    }

    public Neurologico() {
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

    public List<String> getOrientadoTemporoEspacial() {
        return orientadoTemporoEspacial;
    }

    public void setOrientadoTemporoEspacial(List<String> orientadoTemporoEspacial) {
        this.orientadoTemporoEspacial = orientadoTemporoEspacial;
    }

    public List<String> getDesorientadoTemporoEspacial() {
        return desorientadoTemporoEspacial;
    }

    public void setDesorientadoTemporoEspacial(List<String> desorientadoTemporoEspacial) {
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

    public String getDeliciumCAMICU() {
        return deliciumCAMICU;
    }

    public void setDeliciumCAMICU(String deliciumCAMICU) {
        this.deliciumCAMICU = deliciumCAMICU;
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
}
