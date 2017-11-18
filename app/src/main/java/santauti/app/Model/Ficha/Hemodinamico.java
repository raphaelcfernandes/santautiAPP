package santauti.app.Model.Ficha;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raphael Fernandes on 16-Jun-17.
 */

public class Hemodinamico implements FichaMetodos{
    private String pulso;
    private String foneseBulhas;
    private List<String> tipoSopro;
    private int intensidadeSopro;
    private String perfusaoCapilar;
    private String extremidadesColoracao;
    private String extremidadesTemperatura;
    private boolean soproChecked;

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> itens = new HashMap<>();
        Map<String,Object> soproMap = new HashMap<>();
        Map<String,Object> finalResult = new HashMap<>();
        if(pulso!=null)
            itens.put("pulso",pulso);
        if(foneseBulhas!=null)
            itens.put("foneseBulhas",foneseBulhas);
        if(perfusaoCapilar!=null)
            itens.put("perfusaoCapilar",perfusaoCapilar);
        if(extremidadesColoracao!=null)
            itens.put("extremidadesColoracao",extremidadesColoracao);
        if(extremidadesTemperatura!=null)
            itens.put("extremidadesTemperatura",extremidadesTemperatura);
        if(soproChecked){
            if(intensidadeSopro>0)
                soproMap.put("intensidadeSopro",intensidadeSopro);
            if(tipoSopro!=null)
                for(String string : tipoSopro)
                    soproMap.put(string,true);
            itens.put("sopro",soproMap);
        }
        else
            itens.put("sopro",false);
        finalResult.put("Hemodinamico",itens);
        return finalResult;
    }

    public boolean checkObject() {
        if (soproChecked)
            return pulso != null && /*!tipoSopro.isEmpty() &&*/ foneseBulhas != null && perfusaoCapilar != null && extremidadesColoracao != null
                    && intensidadeSopro > 0 && extremidadesTemperatura != null;
        else
            return pulso != null && foneseBulhas != null && perfusaoCapilar != null && extremidadesColoracao != null
                    && extremidadesTemperatura != null;
    }

    public Hemodinamico(String pulso, String foneseBulhas, List<String> tipoSopro, int intensidadeSopro, String perfusaoCapilar, String extremidadesColoracao, String extremidadesTemperatura, boolean soproChecked) {
        this.pulso = pulso;
        this.foneseBulhas = foneseBulhas;
        this.tipoSopro = tipoSopro;
        this.intensidadeSopro = intensidadeSopro;
        this.perfusaoCapilar = perfusaoCapilar;
        this.extremidadesColoracao = extremidadesColoracao;
        this.extremidadesTemperatura = extremidadesTemperatura;
        this.soproChecked = soproChecked;
    }

    public Hemodinamico() {
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getFoneseBulhas() {
        return foneseBulhas;
    }

    public void setFoneseBulhas(String foneseBulhas) {
        this.foneseBulhas = foneseBulhas;
    }

    public List<String> getTipoSopro() {
        return tipoSopro;
    }

    public void setTipoSopro(List<String> tipoSopro) {
        this.tipoSopro = tipoSopro;
    }

    public int getIntensidadeSopro() {
        return intensidadeSopro;
    }

    public void setIntensidadeSopro(int intensidadeSopro) {
        this.intensidadeSopro = intensidadeSopro;
    }

    public String getPerfusaoCapilar() {
        return perfusaoCapilar;
    }

    public void setPerfusaoCapilar(String perfusaoCapilar) {
        this.perfusaoCapilar = perfusaoCapilar;
    }

    public String getExtremidadesColoracao() {
        return extremidadesColoracao;
    }

    public void setExtremidadesColoracao(String extremidadesColoracao) {
        this.extremidadesColoracao = extremidadesColoracao;
    }

    public String getExtremidadesTemperatura() {
        return extremidadesTemperatura;
    }

    public void setExtremidadesTemperatura(String extremidadesTemperatura) {
        this.extremidadesTemperatura = extremidadesTemperatura;
    }

    public boolean isSoproChecked() {
        return soproChecked;
    }

    public void setSoproChecked(boolean soproChecked) {
        this.soproChecked = soproChecked;
    }
}
