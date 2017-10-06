package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by rapha on 06-Oct-17.
 */

public class Exames extends RealmObject implements Serializable {
    @SerializedName("hematocrito")
    private int hematocrito;
    @SerializedName("hemoglobina")
    private int hemoglobina;
    @SerializedName("plaquetas")
    private int plaquetas;
    @SerializedName("leucograma")
    private String leucograma;
    @SerializedName("pcr")
    private String pcr;
    @SerializedName("ureia")
    private int ureia;
    @SerializedName("creatinina")
    private int creatinina;
    @SerializedName("potassio")
    private String potassio;
    @SerializedName("magnesio")
    private String magnesio;
    @SerializedName("fosforo")
    private String fosforo;
    @SerializedName("calcio")
    private String calcio;
    @SerializedName("gasometriaArterialAcidoseAlcalose")
    private String gasometriaArterialAcidoseAlcalose;
    @SerializedName("gasometriaArterialMetabolicaRespiratoria")
    private String gasometrialArterialMetabolicaRespiratoria;
    @SerializedName("gasometriaArterialCompensadaDescompensada")
    private String gasometriaArterialCompensadaDescompensada;
    @SerializedName("funcaoHepaticaBilirrubinas")
    private String funcaoHepaticaBilirrubinas;
    @SerializedName("funcaoHepaticaFAGGT")
    private String funcaoHepaticaFAGGT;
    @SerializedName("funcaoHepaticaTransaminases")
    private String funcaoHepaticaTransaminases;
    @SerializedName("lactato")
    private String lactato;
    private boolean amilaseChecked;
    @SerializedName("amilase")
    private String amilase;
    @SerializedName("marcadoresInfeccao")
    private String marcadoresInfeccao;
    @SerializedName("albumina")
    private String albumina;
    @SerializedName("raioxTorax")
    private String raioxTorax;

    public boolean checkObject(){
        boolean gasometria=false;
        if(gasometrialArterialMetabolicaRespiratoria!=null){
            if(!gasometrialArterialMetabolicaRespiratoria.equals("Mista") && gasometriaArterialCompensadaDescompensada!=null)
                gasometria=true;
            if(gasometrialArterialMetabolicaRespiratoria.equals("Mista") && gasometriaArterialCompensadaDescompensada==null)
                gasometria=true;
        }
        if(amilaseChecked){
            return hematocrito>0 && hemoglobina>0 && plaquetas>0 && leucograma!=null && pcr!=null
                    && ureia>0 && creatinina>0 && potassio!=null && magnesio!=null && fosforo!=null && calcio!=null
                    && gasometriaArterialAcidoseAlcalose!=null && gasometria
                    && funcaoHepaticaBilirrubinas!=null && funcaoHepaticaFAGGT!=null && funcaoHepaticaTransaminases!=null && lactato!=null
                    && marcadoresInfeccao!=null && albumina!=null && raioxTorax!=null && amilase!=null;
        }
        else
            return hematocrito>0 && hemoglobina>0 && plaquetas>0 && leucograma!=null && pcr!=null
            && ureia>0 && creatinina>0 && potassio!=null && magnesio!=null && fosforo!=null && calcio!=null
            && gasometriaArterialAcidoseAlcalose!=null && gasometria && funcaoHepaticaBilirrubinas!=null
            && funcaoHepaticaFAGGT!=null && funcaoHepaticaTransaminases!=null && lactato!=null
            && marcadoresInfeccao!=null && albumina!=null && raioxTorax!=null;
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

    public String getRaioxTorax() {
        return raioxTorax;
    }

    public void setRaioxTorax(String raioxTorax) {
        this.raioxTorax = raioxTorax;
    }
}
