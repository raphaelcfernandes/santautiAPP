package santauti.app.Model.Ficha;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rapha on 03-Oct-17.
 */

public class Nutricional implements FichaMetodos{
    private String enteralTolerancia;
    private String parenteralTolerancia;
    private String oralAceitacao;

    @Exclude
    private boolean enteralCheckBox = false;
    @Exclude
    private boolean parenteralCheckBox = false;
    @Exclude
    private boolean oralCheckBox = false;

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> dietaMap = new HashMap<>();
        Map<String,Object> itens = new HashMap<>();
        if(enteralCheckBox)
            itens.put("Enteral",enteralTolerancia);
        if(parenteralCheckBox)
            itens.put("Parenteral",parenteralTolerancia);
        if(oralCheckBox)
            itens.put("Oral",oralAceitacao);
        dietaMap.put("Nutricional",itens);
        return dietaMap;
    }

    public boolean checkObject(){
        return (enteralCheckBox && enteralTolerancia != null) ||
                (parenteralCheckBox && parenteralTolerancia != null) && (oralCheckBox && oralAceitacao != null);
    }

    public Nutricional(String enteralTolerancia, String parenteralTolerancia, String oralAceitacao) {
        this.enteralTolerancia = enteralTolerancia;
        this.parenteralTolerancia = parenteralTolerancia;
        this.oralAceitacao = oralAceitacao;
    }

    public String getEnteralTolerancia() {
        return enteralTolerancia;
    }

    public void setEnteralTolerancia(String enteralTolerancia) {
        this.enteralTolerancia = enteralTolerancia;
    }

    public String getParenteralTolerancia() {
        return parenteralTolerancia;
    }

    public void setParenteralTolerancia(String parenteralTolerancia) {
        this.parenteralTolerancia = parenteralTolerancia;
    }

    public String getOralAceitacao() {
        return oralAceitacao;
    }

    public void setOralAceitacao(String oralAceitacao) {
        this.oralAceitacao = oralAceitacao;
    }

    public boolean isEnteralCheckBox() {
        return enteralCheckBox;
    }

    public void setEnteralCheckBox(boolean enteralCheckBox) {
        this.enteralCheckBox = enteralCheckBox;
    }

    public boolean isParenteralCheckBox() {
        return parenteralCheckBox;
    }

    public void setParenteralCheckBox(boolean parenteralCheckBox) {
        this.parenteralCheckBox = parenteralCheckBox;
    }

    public boolean isOralCheckBox() {
        return oralCheckBox;
    }

    public void setOralCheckBox(boolean oralCheckBox) {
        this.oralCheckBox = oralCheckBox;
    }

    public Nutricional() {
    }
}
