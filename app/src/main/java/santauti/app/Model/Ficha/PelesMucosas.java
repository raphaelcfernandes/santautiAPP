package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import santauti.app.Model.Ficha.RealmObjects.RealmString;

/**
 * Created by rapha on 04-Oct-17.
 */

public class PelesMucosas extends RealmObject implements Serializable {
    @SerializedName("pele")
    private String pele;
    @SerializedName("ulceraPressao")
    private RealmList<RealmString> ulceraPressao;
    @SerializedName("mucosasColoracao")
    private String mucosasColoracao;
    @SerializedName("mucosasColoracao2")
    private String mucosasColoracao2;
    @SerializedName("mucosasUmidade")
    private String mucosasUmidade;
    @SerializedName("ictericia")
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
            return pele!=null && !ulceraPressao.isEmpty() && mucosasColoracao!=null && mucosasColoracao2!=null && mucosasUmidade!=null
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

    public RealmList<RealmString> getUlceraPressao() {
        return ulceraPressao;
    }

    public void setUlceraPressao(RealmList<RealmString> ulceraPressao) {
        this.ulceraPressao = ulceraPressao;
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
