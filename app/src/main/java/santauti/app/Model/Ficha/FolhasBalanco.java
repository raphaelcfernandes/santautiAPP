package santauti.app.Model.Ficha;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rapha on 06-Oct-17.
 */

public class FolhasBalanco implements FichaMetodos{

    private String curvaTermica;
    private boolean evacuacoesFlag;
    private Map<String,Integer> evacuacoes;
    private Map<String,Integer> nutricao;
    private String hemodinamicamente;

    public FolhasBalanco() {
    }

    public FolhasBalanco(String curvaTermica, boolean evacuacoesFlag, Map<String, Integer> evacuacoes, Map<String, Integer> nutricao, String hemodinamicamente) {
        this.curvaTermica = curvaTermica;
        this.evacuacoesFlag = evacuacoesFlag;
        this.evacuacoes = evacuacoes;
        this.nutricao = nutricao;
        this.hemodinamicamente = hemodinamicamente;
    }

    public void initializeMaps(){
        if(evacuacoes==null)
            evacuacoes = new HashMap<>();
        if(nutricao==null)
            nutricao = new HashMap<>();
    }

    public void insereEvacuacoes(String string, int value){
        evacuacoes.put(string,value);
    }

    public void insereNutricao(String string, int value){
        nutricao.put(string,value);
    }

    public String getCurvaTermica() {
        return curvaTermica;
    }

    public void setCurvaTermica(String curvaTermica) {
        this.curvaTermica = curvaTermica;
    }

    public boolean isEvacuacoesFlag() {
        return evacuacoesFlag;
    }

    public void setEvacuacoesFlag(boolean evacuacoesFlag) {
        this.evacuacoesFlag = evacuacoesFlag;
    }

    public Map<String, Integer> getEvacuacoes() {
        return evacuacoes;
    }

    public void setEvacuacoes(Map<String, Integer> evacuacoes) {
        this.evacuacoes = evacuacoes;
    }

    public Map<String, Integer> getNutricao() {
        return nutricao;
    }

    public void setNutricao(Map<String, Integer> nutricao) {
        this.nutricao = nutricao;
    }

    public String getHemodinamicamente() {
        return hemodinamicamente;
    }

    public void setHemodinamicamente(String hemodinamicamente) {
        this.hemodinamicamente = hemodinamicamente;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> finalResult = new HashMap<>();
        Map<String,Object> itens = new HashMap<>();
        if(hemodinamicamente!=null)
            itens.put("hemodinamicamente",hemodinamicamente);
        if(curvaTermica!=null)
            itens.put("curvaTermica",curvaTermica);
        if(evacuacoesFlag && !evacuacoes.isEmpty()){
            itens.put("evacuacoes", evacuacoes);
        }
        else
            itens.put("evacuacoes",evacuacoesFlag);
        if(!nutricao.isEmpty()){
            itens.put("nutricao",nutricao);
        }
        finalResult.put("FolhasBalanco",itens);
        return finalResult;
    }

    public boolean checkObject(){
        if(evacuacoesFlag){
            return /*!evacuacoes.isEmpty() &&*/ curvaTermica!=null && /*!nutricao.isEmpty() &&*/ hemodinamicamente!=null;
        }
        else{
            return curvaTermica!=null /*&& !nutricao.isEmpty()*/ && hemodinamicamente!=null;
        }
    }

}
