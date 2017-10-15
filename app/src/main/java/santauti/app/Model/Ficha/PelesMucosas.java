package santauti.app.Model.Ficha;

/**
 * Created by rapha on 04-Oct-17.
 */

public class PelesMucosas{
    private String pele;
    //private RealmList<RealmString> ulceraPressao;
    private String mucosasColoracao;
    private String mucosasColoracao2;
    private String mucosasUmidade;
    private int ictericia=-1;

    private boolean ulceraPressaoChecked=false;

    public boolean isUlceraPressaoChecked() {
        return ulceraPressaoChecked;
    }

    public void setUlceraPressaoChecked(boolean ulceraPressaoChecked) {
        this.ulceraPressaoChecked = ulceraPressaoChecked;
    }

    public boolean checkObject(){
        if(ulceraPressaoChecked)
            return pele!=null /*&& !ulceraPressao.isEmpty()*/ && mucosasColoracao!=null && mucosasColoracao2!=null && mucosasUmidade!=null
                    && ictericia>=0;
        else
            return pele!=null && mucosasColoracao!=null && mucosasColoracao2!=null && mucosasUmidade!=null
                && ictericia>=0;
    }

    public String getMucosasColoracao2() {
        return mucosasColoracao2;
    }

    public void setMucosasColoracao2(String mucosasColoracao2) {
        this.mucosasColoracao2 = mucosasColoracao2;
    }

    public String getPele() {
        return pele;
    }

    public void setPele(String pele) {
        this.pele = pele;
    }

    public String getMucosasColoracao() {
        return mucosasColoracao;
    }

    public void setMucosasColoracao(String mucosasColoracao) {
        this.mucosasColoracao = mucosasColoracao;
    }

    public String getMucosasUmidade() {
        return mucosasUmidade;
    }

    public void setMucosasUmidade(String mucosasUmidade) {
        this.mucosasUmidade = mucosasUmidade;
    }

    public int getIctericia() {
        return ictericia;
    }

    public void setIctericia(int ictericia) {
        this.ictericia = ictericia;
    }
}
