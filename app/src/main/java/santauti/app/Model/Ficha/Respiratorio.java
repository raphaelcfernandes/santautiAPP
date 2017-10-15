package santauti.app.Model.Ficha;

/**
 * Created by Raphael Fernandes on 30-May-17.
 */

public class Respiratorio {
    private String viasAereas;
    private int pressaoCuff;
    private String localizacaoCanula;
    private String murmurioVesicular;
    //private RealmList<RealmString> localizacaoMurmurioVesicular;
    private String usoOxigenio;
    private int mascaraVenturi;
    private int fluxo;
//    private RealmList<RealmString> roncos;
//    private RealmList<RealmString> sibilos;
//    private RealmList<RealmString> crepitacoes;

    public boolean checkObject(){
        boolean viasAereas=false;
        boolean murmurioVesicular=false;
        boolean usoOxigenio=false;
        if(this.viasAereas!=null){
            if(this.viasAereas.equals("Natural"))
                viasAereas=true;
            else{
                if(localizacaoCanula!=null && pressaoCuff >0)
                    viasAereas=true;
            }
        }
        if(this.murmurioVesicular!=null){
            if(this.murmurioVesicular.equals("Fisiológico"))
                murmurioVesicular=true;
            else{
                //if(!localizacaoMurmurioVesicular.isEmpty())
                    murmurioVesicular=true;
            }
        }
        if(this.usoOxigenio!=null){
            if(this.usoOxigenio.equals("Não"))
                usoOxigenio=true;
            else{
                if (mascaraVenturi>0 || fluxo>0)
                    usoOxigenio=true;
            }
        }
        return viasAereas && murmurioVesicular && usoOxigenio;
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

    public String getUsoOxigenio() {
        return usoOxigenio;
    }

    public void setUsoOxigenio(String usoOxigenio) {
        this.usoOxigenio = usoOxigenio;
    }

    public int getMascaraVenturi() {
        return mascaraVenturi;
    }

    public void setMascaraVenturi(int mascaraVenturi) {
        this.mascaraVenturi = mascaraVenturi;
    }

    public int getFluxo() {
        return fluxo;
    }

    public void setFluxo(int fluxo) {
        this.fluxo = fluxo;
    }

}

