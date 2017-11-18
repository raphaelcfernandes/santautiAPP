package santauti.app.Model.Ficha;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raphael Fernandes on 30-May-17.
 */

public class Respiratorio implements FichaMetodos{
    private String viasAereas;
    private int pressaoCuff;
    private String localizacaoCanula;
    private String murmurioVesicular;
    private List<String> localizacoesMurmurioVesicular;
    private String usoOxigenio;
    private int usoOxigenioFluxo;
    private int mascaraVenturi;
    private List<String> sibilos;
    private List<String> roncos;
    private List<String> crepitacoes;

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> itens = new HashMap<>();
        Map<String,Object> viasAereasMap = new HashMap<>();
        Map<String,Object> murmurioVesicularMap = new HashMap<>();
        Map<String,Object> localizacoesMurmurioVesicularMap = new HashMap<>();
        Map<String,Object> usoOxigenioMap = new HashMap<>();
        if(viasAereas!=null){
            if(!viasAereas.equals("Natural")){
                viasAereasMap.put("pressaoCuff",pressaoCuff);
                if(viasAereas.equals("Tubo Traqueal"))
                    viasAereasMap.put("localizacaoCanula",localizacaoCanula);
                viasAereasMap.put(viasAereas,true);
                itens.put("ViasAereas",viasAereasMap);
            }
            else
                itens.put("viasAereas",viasAereas);
        }
        if(murmurioVesicular!=null){
            if(!murmurioVesicular.equals("Fisiológico") && !localizacoesMurmurioVesicular.isEmpty()){
                for(String string : localizacoesMurmurioVesicular)
                    localizacoesMurmurioVesicularMap.put(string,true);
                murmurioVesicularMap.put(murmurioVesicular,localizacoesMurmurioVesicularMap);
                itens.put("MurmurioVesicular",murmurioVesicularMap);
            }
            else
                itens.put("MurmurioVesicular",murmurioVesicular);
        }
        if(roncos!=null)
            if(!roncos.isEmpty())
                itens.put("Roncos",preencheMapStringComTrueByList(roncos));
        else
            itens.put("Roncos",false);
        if(sibilos!=null)
            if(!sibilos.isEmpty())
                itens.put("Sibilos",preencheMapStringComTrueByList(sibilos));
        else
            itens.put("Sibilos",false);
        if(crepitacoes!=null)
            if(!crepitacoes.isEmpty())
                itens.put("Crepitacoes",preencheMapStringComTrueByList(crepitacoes));
        else
            itens.put("Crepitacoes",false);
        if(usoOxigenio!=null){
            switch (usoOxigenio) {
                case "Não":
                    itens.put("UsoOxigenio", usoOxigenio);
                    break;
                case "Em Máscara de Venturi":
                    usoOxigenioMap.put(usoOxigenio, mascaraVenturi);
                    itens.put("UsoOxigenio",usoOxigenioMap);
                    break;
                default:
                    usoOxigenioMap.put(usoOxigenio, usoOxigenioFluxo);
                    itens.put("UsoOxigenio",usoOxigenioMap);
                    break;
            }

        }
        Map<String,Object> finalResult = new HashMap<>();
        finalResult.put("Respiratorio",itens);
        return finalResult;
    }

    private Map<String,Object> preencheMapStringComTrueByList(List<String> list){
        Map<String,Object> mapToReturn = new HashMap<>();
        for(String string : list)
            mapToReturn.put(string,true);
        return mapToReturn;
    }

    public boolean checkObject(){
        return true;
//        boolean viasAereas=false;
//        boolean murmurioVesicular=false;
//        boolean usoOxigenio=false;
//        if(this.viasAereas!=null){
//            if(this.viasAereas.equals("Natural"))
//                viasAereas=true;
//            else{
//                if(localizacaoCanula!=null && pressaoCuff >0)
//                    viasAereas=true;
//            }
//        }
//        if(this.murmurioVesicular!=null){
//            if(this.murmurioVesicular.equals("Fisiológico"))
//                murmurioVesicular=true;
//            else{
//                //if(!localizacaoMurmurioVesicular.isEmpty())
//                    murmurioVesicular=true;
//            }
//        }
//        if(this.usoOxigenio!=null){
//            if(this.usoOxigenio.equals("Não"))
//                usoOxigenio=true;
//            else{
//                if (mascaraVenturi>0 || fluxo>0)
//                    usoOxigenio=true;
//            }
//        }
//        return viasAereas && murmurioVesicular && usoOxigenio;
    }


    public String getViasAereas() {
        return viasAereas;
    }

    public void setViasAereas(String viasAereas) {
        this.viasAereas = viasAereas;
    }

    public int getPressaoCuff() {
        return pressaoCuff;
    }

    public void setPressaoCuff(int pressaoCuff) {
        this.pressaoCuff = pressaoCuff;
    }

    public String getLocalizacaoCanula() {
        return localizacaoCanula;
    }

    public void setLocalizacaoCanula(String localizacaoCanula) {
        this.localizacaoCanula = localizacaoCanula;
    }

    public String getMurmurioVesicular() {
        return murmurioVesicular;
    }

    public void setMurmurioVesicular(String murmurioVesicular) {
        this.murmurioVesicular = murmurioVesicular;
    }

    public List<String> getLocalizacoesMurmurioVesicular() {
        return localizacoesMurmurioVesicular;
    }

    public void setLocalizacoesMurmurioVesicular(List<String> localizacoesMurmurioVesicular) {
        this.localizacoesMurmurioVesicular = localizacoesMurmurioVesicular;
    }

    public String getUsoOxigenio() {
        return usoOxigenio;
    }

    public void setUsoOxigenio(String usoOxigenio) {
        this.usoOxigenio = usoOxigenio;
    }

    public int getUsoOxigenioFluxo() {
        return usoOxigenioFluxo;
    }

    public void setUsoOxigenioFluxo(int usoOxigenioFluxo) {
        this.usoOxigenioFluxo = usoOxigenioFluxo;
    }

    public List<String> getSibilos() {
        return sibilos;
    }

    public void setSibilos(List<String> sibilos) {
        this.sibilos = sibilos;
    }

    public List<String> getRoncos() {
        return roncos;
    }

    public void setRoncos(List<String> roncos) {
        this.roncos = roncos;
    }

    public List<String> getCrepitacoes() {
        return crepitacoes;
    }

    public void setCrepitacoes(List<String> crepitacoes) {
        this.crepitacoes = crepitacoes;
    }

    public Respiratorio() {
    }

    public Respiratorio(String viasAereas, int pressaoCuff, String localizacaoCanula, String murmurioVesicular, List<String> localizacoesMurmurioVesicular, String usoOxigenio, int usoOxigenioFluxo, int mascaraVenturi, List<String> sibilos, List<String> roncos, List<String> crepitacoes) {
        this.viasAereas = viasAereas;
        this.pressaoCuff = pressaoCuff;
        this.localizacaoCanula = localizacaoCanula;
        this.murmurioVesicular = murmurioVesicular;
        this.localizacoesMurmurioVesicular = localizacoesMurmurioVesicular;
        this.usoOxigenio = usoOxigenio;
        this.usoOxigenioFluxo = usoOxigenioFluxo;
        this.mascaraVenturi = mascaraVenturi;
        this.sibilos = sibilos;
        this.roncos = roncos;
        this.crepitacoes = crepitacoes;
    }

    public int getMascaraVenturi() {
        return mascaraVenturi;
    }

    public void setMascaraVenturi(int mascaraVenturi) {
        this.mascaraVenturi = mascaraVenturi;
    }
}

