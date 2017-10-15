package santauti.app.Model.Ficha;

/**
 * Created by Raphael Fernandes on 16-Jun-17.
 */

public class Hemodinamico {

    private String pulso;
    private String foneseBulhas;
    //private RealmList<RealmString> tipoSopro;
    private int intensidadeSopro;
    private String perfusaoCapilar;
    private String extremidadesColoracao;
    private String extremidadesTemperatura;
    private boolean soproChecked;

    public boolean checkObject(){
        if(soproChecked)
            return pulso!=null && /*!tipoSopro.isEmpty() &&*/ foneseBulhas!=null && perfusaoCapilar!=null && extremidadesColoracao!=null
                    && intensidadeSopro>0 && extremidadesTemperatura!=null;
        else
            return pulso!=null && foneseBulhas!=null && perfusaoCapilar!=null && extremidadesColoracao!=null
                    && extremidadesTemperatura!=null;
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
