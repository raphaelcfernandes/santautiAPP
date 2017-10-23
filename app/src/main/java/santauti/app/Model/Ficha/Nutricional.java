package santauti.app.Model.Ficha;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rapha on 03-Oct-17.
 */

public class Nutricional implements FichaMetodos{
    private List<String> dieta;
    private String aceitacao;

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> dietaMap = new HashMap<>();
        Map<String,Object> itens = new HashMap<>();
        Map<String,Object> finalResult = new HashMap<>();
        if(dieta!=null) {
            for (String string : dieta)
                dietaMap.put(string, true);
            itens.put("dieta",dietaMap);
        }
        if(aceitacao!=null)
            itens.put("aceitacao",aceitacao);
        finalResult.put("Nutricional",itens);
        return finalResult;
    }

    public boolean checkObject(){
        return aceitacao!=null && !dieta.isEmpty();
    }

    public String getAceitacao() {
        return aceitacao;
    }

    public void setAceitacao(String aceitacao) {
        this.aceitacao = aceitacao;
    }

    public List<String> getDieta() {
        return dieta;
    }

    public void setDieta(List<String> dieta) {
        this.dieta = dieta;
    }

    public Nutricional(List<String> dieta, String aceitacao) {
        this.dieta = dieta;
        this.aceitacao = aceitacao;
    }

    public Nutricional() {
    }
}
