package santauti.app.Model.Ficha;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rapha on 06-Oct-17.
 */

public class Exames implements FichaMetodos{
    private int hematocrito;
    private int hemoglobina;
    private int plaquetas;
    private String leucograma;
    private String pcr;
    private int ureia;
    private int creatinina;
    private String potassio;
    private String magnesio;
    private String fosforo;
    private String calcio;
    private String gasometriaArterialAcidoseAlcalose;
    private String gasometrialArterialMetabolicaRespiratoria;
    private String gasometriaArterialCompensadaDescompensada;
    private String funcaoHepaticaBilirrubinas;
    private String funcaoHepaticaFAGGT;
    private String funcaoHepaticaTransaminases;
    private String lactato;
    private boolean amilaseChecked;
    private String amilase;
    private String marcadoresInfeccao;
    private String albumina;
    private String diagnosticoRaioX;
    private List<String> raioxToraxList;

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> itens = new HashMap<>();
        Map<String,Object> gasometriaArterialMap = new HashMap<>();
        Map<String,Object> raioxToraxMap = new HashMap<>();
        Map<String,Object> finalResult = new HashMap<>();
        itens.put("hematocrito",hematocrito);
        itens.put("hemoglobina",hemoglobina);
        itens.put("plaquetas",plaquetas);
        itens.put("leucograma",leucograma);
        itens.put("pcr",pcr);
        itens.put("ureia",ureia);
        itens.put("creatinina",creatinina);
        itens.put("potassio",potassio);
        itens.put("magnesio",magnesio);
        itens.put("fosforo",fosforo);
        itens.put("calcio",calcio);
        if(gasometriaArterialAcidoseAlcalose!=null){
            gasometriaArterialMap.put("tipoGasometriaArterial",gasometriaArterialAcidoseAlcalose);
            if(gasometriaArterialAcidoseAlcalose.equals("Acidose") || gasometriaArterialAcidoseAlcalose.equals("Alcalose")){
                gasometriaArterialMap.put("metabolicaRespiratoria",gasometrialArterialMetabolicaRespiratoria);
                if(!gasometrialArterialMetabolicaRespiratoria.equals("Mista"))
                    gasometriaArterialMap.put("compensadaDescompensada",gasometriaArterialCompensadaDescompensada);
                itens.put("gasometriaArterial",gasometriaArterialMap);
            }
            else
                itens.put("gasometriaArterial",gasometriaArterialAcidoseAlcalose);
        }
        itens.put("funcaoHepaticaBilirrubinas",funcaoHepaticaBilirrubinas);
        itens.put("funcaoHepaticaFAGGT",funcaoHepaticaFAGGT);
        itens.put("funcaoHepaticaTransaminases",funcaoHepaticaTransaminases);
        itens.put("lactato",lactato);
        if(amilaseChecked)
            itens.put("amilase",amilase);
        else
            itens.put("amilase",false);
        itens.put("marcadoresInfeccao",marcadoresInfeccao);
        itens.put("albumina",albumina);
        if(diagnosticoRaioX !=null){
            if(diagnosticoRaioX.equals("Pneumotorax") || diagnosticoRaioX.equals("Actelectasia") || diagnosticoRaioX.equals("Infiltrados Novos")){
                for(String string : raioxToraxList)
                    raioxToraxMap.put(string,true);
                raioxToraxMap.put("diagnosticoRaiox", diagnosticoRaioX);
                itens.put("raioxTorax",raioxToraxMap);
            }
            else
                itens.put("raioxTorax",diagnosticoRaioX);
        }
        finalResult.put("Exames",itens);
        return finalResult;
    }

    public boolean checkObject(){
        boolean gasometria=false;
        boolean raioxFlag=true;
        if(diagnosticoRaioX !=null){
            if((!diagnosticoRaioX.equals("Normal") && !diagnosticoRaioX.equals("Não realizou/sem resultados") && raioxToraxList.isEmpty()))
                raioxFlag=false;
        }
        else
            raioxFlag=false;
        if(gasometriaArterialAcidoseAlcalose!=null){
            if(gasometriaArterialAcidoseAlcalose.equals("Normal") || gasometriaArterialAcidoseAlcalose.equals("Não realizou/sem resultados"))
                gasometria=true;
        }
        if(gasometrialArterialMetabolicaRespiratoria!=null){
            if(!gasometrialArterialMetabolicaRespiratoria.equals("Mista") && gasometriaArterialCompensadaDescompensada!=null)
                gasometria=true;
            if(gasometrialArterialMetabolicaRespiratoria.equals("Mista") && gasometriaArterialCompensadaDescompensada==null)
                gasometria=true;
        }
        if(amilaseChecked){
            return hematocrito>=0 && hemoglobina>=0 && plaquetas>=0 && leucograma!=null && pcr!=null
                    && ureia>=0 && creatinina>=0 && potassio!=null && magnesio!=null && fosforo!=null && calcio!=null
                    && gasometriaArterialAcidoseAlcalose!=null && gasometria
                    && funcaoHepaticaBilirrubinas!=null && funcaoHepaticaFAGGT!=null && funcaoHepaticaTransaminases!=null && lactato!=null
                    && marcadoresInfeccao!=null && albumina!=null && raioxFlag && amilase!=null;
        }
        else
            return hematocrito>=0 && hemoglobina>=0 && plaquetas>=0 && leucograma!=null && pcr!=null
            && ureia>=0 && creatinina>=0 && potassio!=null && magnesio!=null && fosforo!=null && calcio!=null
            && gasometriaArterialAcidoseAlcalose!=null && gasometria && funcaoHepaticaBilirrubinas!=null
            && funcaoHepaticaFAGGT!=null && funcaoHepaticaTransaminases!=null && lactato!=null
            && marcadoresInfeccao!=null && albumina!=null && raioxFlag;
    }

    public Exames(int hematocrito, int hemoglobina, int plaquetas, String leucograma, String pcr, int ureia, int creatinina, String potassio, String magnesio, String fosforo, String calcio, String gasometriaArterialAcidoseAlcalose, String gasometrialArterialMetabolicaRespiratoria, String gasometriaArterialCompensadaDescompensada, String funcaoHepaticaBilirrubinas, String funcaoHepaticaFAGGT, String funcaoHepaticaTransaminases, String lactato, boolean amilaseChecked, String amilase, String marcadoresInfeccao, String albumina) {
        this.hematocrito = hematocrito;
        this.hemoglobina = hemoglobina;
        this.plaquetas = plaquetas;
        this.leucograma = leucograma;
        this.pcr = pcr;
        this.ureia = ureia;
        this.creatinina = creatinina;
        this.potassio = potassio;
        this.magnesio = magnesio;
        this.fosforo = fosforo;
        this.calcio = calcio;
        this.gasometriaArterialAcidoseAlcalose = gasometriaArterialAcidoseAlcalose;
        this.gasometrialArterialMetabolicaRespiratoria = gasometrialArterialMetabolicaRespiratoria;
        this.gasometriaArterialCompensadaDescompensada = gasometriaArterialCompensadaDescompensada;
        this.funcaoHepaticaBilirrubinas = funcaoHepaticaBilirrubinas;
        this.funcaoHepaticaFAGGT = funcaoHepaticaFAGGT;
        this.funcaoHepaticaTransaminases = funcaoHepaticaTransaminases;
        this.lactato = lactato;
        this.amilaseChecked = amilaseChecked;
        this.amilase = amilase;
        this.marcadoresInfeccao = marcadoresInfeccao;
        this.albumina = albumina;
    }

    public Exames() {
    }

    public List<String> getRaioxToraxList() {
        return raioxToraxList;
    }

    public void setRaioxToraxList(List<String> raioxToraxList) {
        this.raioxToraxList = raioxToraxList;
    }

    public int getHematocrito() {
        return hematocrito;
    }

    public void setHematocrito(int hematocrito) {
        this.hematocrito = hematocrito;
    }

    public int getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(int hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public int getPlaquetas() {
        return plaquetas;
    }

    public void setPlaquetas(int plaquetas) {
        this.plaquetas = plaquetas;
    }

    public String getLeucograma() {
        return leucograma;
    }

    public void setLeucograma(String leucograma) {
        this.leucograma = leucograma;
    }

    public String getPcr() {
        return pcr;
    }

    public void setPcr(String pcr) {
        this.pcr = pcr;
    }

    public int getUreia() {
        return ureia;
    }

    public void setUreia(int ureia) {
        this.ureia = ureia;
    }

    public int getCreatinina() {
        return creatinina;
    }

    public void setCreatinina(int creatinina) {
        this.creatinina = creatinina;
    }

    public String getPotassio() {
        return potassio;
    }

    public void setPotassio(String potassio) {
        this.potassio = potassio;
    }

    public String getMagnesio() {
        return magnesio;
    }

    public void setMagnesio(String magnesio) {
        this.magnesio = magnesio;
    }

    public String getFosforo() {
        return fosforo;
    }

    public void setFosforo(String fosforo) {
        this.fosforo = fosforo;
    }

    public String getCalcio() {
        return calcio;
    }

    public void setCalcio(String calcio) {
        this.calcio = calcio;
    }

    public String getGasometriaArterialAcidoseAlcalose() {
        return gasometriaArterialAcidoseAlcalose;
    }

    public void setGasometriaArterialAcidoseAlcalose(String gasometriaArterialAcidoseAlcalose) {
        this.gasometriaArterialAcidoseAlcalose = gasometriaArterialAcidoseAlcalose;
    }

    public String getGasometrialArterialMetabolicaRespiratoria() {
        return gasometrialArterialMetabolicaRespiratoria;
    }

    public void setGasometrialArterialMetabolicaRespiratoria(String gasometrialArterialMetabolicaRespiratoria) {
        this.gasometrialArterialMetabolicaRespiratoria = gasometrialArterialMetabolicaRespiratoria;
    }

    public String getGasometriaArterialCompensadaDescompensada() {
        return gasometriaArterialCompensadaDescompensada;
    }

    public void setGasometriaArterialCompensadaDescompensada(String gasometriaArterialCompensadaDescompensada) {
        this.gasometriaArterialCompensadaDescompensada = gasometriaArterialCompensadaDescompensada;
    }

    public String getFuncaoHepaticaBilirrubinas() {
        return funcaoHepaticaBilirrubinas;
    }

    public void setFuncaoHepaticaBilirrubinas(String funcaoHepaticaBilirrubinas) {
        this.funcaoHepaticaBilirrubinas = funcaoHepaticaBilirrubinas;
    }

    public String getFuncaoHepaticaFAGGT() {
        return funcaoHepaticaFAGGT;
    }

    public void setFuncaoHepaticaFAGGT(String funcaoHepaticaFAGGT) {
        this.funcaoHepaticaFAGGT = funcaoHepaticaFAGGT;
    }

    public String getFuncaoHepaticaTransaminases() {
        return funcaoHepaticaTransaminases;
    }

    public void setFuncaoHepaticaTransaminases(String funcaoHepaticaTransaminases) {
        this.funcaoHepaticaTransaminases = funcaoHepaticaTransaminases;
    }

    public String getLactato() {
        return lactato;
    }

    public void setLactato(String lactato) {
        this.lactato = lactato;
    }

    public boolean isAmilaseChecked() {
        return amilaseChecked;
    }

    public void setAmilaseChecked(boolean amilaseChecked) {
        this.amilaseChecked = amilaseChecked;
    }

    public String getAmilase() {
        return amilase;
    }

    public void setAmilase(String amilase) {
        this.amilase = amilase;
    }

    public String getMarcadoresInfeccao() {
        return marcadoresInfeccao;
    }

    public void setMarcadoresInfeccao(String marcadoresInfeccao) {
        this.marcadoresInfeccao = marcadoresInfeccao;
    }

    public String getAlbumina() {
        return albumina;
    }

    public void setAlbumina(String albumina) {
        this.albumina = albumina;
    }

    public String getDiagnosticoRaioX() {
        return diagnosticoRaioX;
    }

    public void setDiagnosticoRaioX(String diagnosticoRaioX) {
        this.diagnosticoRaioX = diagnosticoRaioX;
    }
}
