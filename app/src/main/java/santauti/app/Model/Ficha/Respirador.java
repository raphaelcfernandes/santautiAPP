package santauti.app.Model.Ficha;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rapha on 05-Oct-17.
 */

public class Respirador implements FichaMetodos{
    private boolean emVentilacaoMecanica;
    private String modoVentilatorio;
    private int volume;
    private int peep;
    private int fio2;
    private int freqRespiratoriaPaciente;
    private int freqRespiratoriaRespirador;
    private int ipap;
    private int epap;
    private int saturacao;
    private int oxigenio;
    private String parametros;

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> finalResult = new HashMap<>();
        Map<String,Object> itens = new HashMap<>();
        if(emVentilacaoMecanica){
            if(modoVentilatorio!=null){
                if(volume>0)
                    itens.put("volume",volume);
                itens.put("modoVentilatorio",modoVentilatorio);
                if(modoVentilatorio.equals("BIPAP")){
                    if(ipap>0)
                        itens.put("ipap",ipap);
                    if(epap>0)
                        itens.put("epap",epap);
                    if(saturacao>0)
                        itens.put("saturacao",saturacao);
                    if(oxigenio>0)
                        itens.put("oxigenio",oxigenio);
                }
                else if(modoVentilatorio.equals("Mecânica") && parametros!=null){
                    if(parametros!=null)
                        itens.put("parametros",parametros);
                    if(fio2>0)
                        itens.put("fio2",fio2);
                    if(peep>0)
                        itens.put("peep",peep);
                    if(freqRespiratoriaPaciente>0)
                        itens.put("freqRespiratoriaPaciente",freqRespiratoriaPaciente);
                    if(freqRespiratoriaRespirador>0)
                        itens.put("freqRespiratoriaRespirador",freqRespiratoriaRespirador);
                }
                else{
                    if(fio2>0)
                        itens.put("fio2",fio2);
                    if(peep>0)
                        itens.put("peep",peep);
                    if(freqRespiratoriaPaciente>0)
                        itens.put("freqRespiratoriaPaciente",freqRespiratoriaPaciente);
                    if(freqRespiratoriaRespirador>0)
                        itens.put("freqRespiratoriaRespirador",freqRespiratoriaRespirador);
                }
            }
        }
        else
            itens.put("ventilacaoMecanica",false);
        finalResult.put("Respirador",itens);
        return finalResult;
    }

    public boolean checkObject(){
        if(emVentilacaoMecanica){
            if(modoVentilatorio!=null){
                switch (modoVentilatorio) {
                    case "Não-invasiva":
                        return volume > 0 && peep > 0 && fio2 > 0 && freqRespiratoriaRespirador > 0 && freqRespiratoriaPaciente > 0;
                    case "BIPAP":
                        return volume > 0 && ipap > 0 && epap > 0 && saturacao > 0 && oxigenio > 0;
                    case "Mecânica":
                        return parametros != null && fio2 > 0 && peep > 0 && volume > 0 && freqRespiratoriaPaciente > 0 && freqRespiratoriaRespirador > 0;
                }
            }
            return false;
        }
        return true;
    }

    public boolean isEmVentilacaoMecanica() {
        return emVentilacaoMecanica;
    }

    public void setEmVentilacaoMecanica(boolean emVentilacaoMecanica) {
        this.emVentilacaoMecanica = emVentilacaoMecanica;
    }

    public String getModoVentilatorio() {
        return modoVentilatorio;
    }

    public void setModoVentilatorio(String modoVentilatorio) {
        this.modoVentilatorio = modoVentilatorio;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPeep() {
        return peep;
    }

    public void setPeep(int peep) {
        this.peep = peep;
    }

    public int getFio2() {
        return fio2;
    }

    public void setFio2(int fio2) {
        this.fio2 = fio2;
    }

    public int getFreqRespiratoriaPaciente() {
        return freqRespiratoriaPaciente;
    }

    public void setFreqRespiratoriaPaciente(int freqRespiratoriaPaciente) {
        this.freqRespiratoriaPaciente = freqRespiratoriaPaciente;
    }

    public int getFreqRespiratoriaRespirador() {
        return freqRespiratoriaRespirador;
    }

    public void setFreqRespiratoriaRespirador(int freqRespiratoriaRespirador) {
        this.freqRespiratoriaRespirador = freqRespiratoriaRespirador;
    }

    public int getIpap() {
        return ipap;
    }

    public void setIpap(int ipap) {
        this.ipap = ipap;
    }

    public int getEpap() {
        return epap;
    }

    public void setEpap(int epap) {
        this.epap = epap;
    }

    public int getSaturacao() {
        return saturacao;
    }

    public void setSaturacao(int saturacao) {
        this.saturacao = saturacao;
    }

    public int getOxigenio() {
        return oxigenio;
    }

    public void setOxigenio(int oxigenio) {
        this.oxigenio = oxigenio;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public Respirador(boolean emVentilacaoMecanica, String modoVentilatorio, int volume, int peep, int fio2, int freqRespiratoriaPaciente, int freqRespiratoriaRespirador, int ipap, int epap, int saturacao, int oxigenio, String parametros) {
        this.emVentilacaoMecanica = emVentilacaoMecanica;
        this.modoVentilatorio = modoVentilatorio;
        this.volume = volume;
        this.peep = peep;
        this.fio2 = fio2;
        this.freqRespiratoriaPaciente = freqRespiratoriaPaciente;
        this.freqRespiratoriaRespirador = freqRespiratoriaRespirador;
        this.ipap = ipap;
        this.epap = epap;
        this.saturacao = saturacao;
        this.oxigenio = oxigenio;
        this.parametros = parametros;
    }

    public Respirador() {
    }
}
