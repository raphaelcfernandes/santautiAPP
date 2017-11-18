package santauti.app.Model.Ficha;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rapha on 06-Oct-17.
 */

public class FolhasBalanco implements FichaMetodos{

    private float curvaTermica;
    private int picosFebris;
    private boolean evacuacoesFlag;
    private int diurese;
    private int balancoHidrico;
    private Map<String,Integer> evacuacoes;
    private Map<String,Integer> nutricao;
    private String hemodinamicamente;

    public FolhasBalanco() {
    }

    public FolhasBalanco(float curvaTermica, int picosFebris, boolean evacuacoesFlag, int diurese, int balancoHidrico, Map<String, Integer> evacuacoes, Map<String, Integer> nutricao, String hemodinamicamente) {
        this.curvaTermica = curvaTermica;
        this.picosFebris = picosFebris;
        this.evacuacoesFlag = evacuacoesFlag;
        this.diurese = diurese;
        this.balancoHidrico = balancoHidrico;
        this.evacuacoes = evacuacoes;
        this.nutricao = nutricao;
        this.hemodinamicamente = hemodinamicamente;
    }

    public int getDiurese() {
        return diurese;
    }

    public void setDiurese(int diurese) {
        this.diurese = diurese;
    }

    public int getBalancoHidrico() {
        return balancoHidrico;
    }

    public void setBalancoHidrico(int balancoHidrico) {
        this.balancoHidrico = balancoHidrico;
    }

    public float getCurvaTermica() {
        return curvaTermica;
    }

    public void setCurvaTermica(float curvaTermica) {
        this.curvaTermica = curvaTermica;
    }

    public int getPicosFebris() {
        return picosFebris;
    }

    public void setPicosFebris(int picosFebris) {
        this.picosFebris = picosFebris;
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
        if(curvaTermica>0)
            itens.put("curvaTermica",curvaTermica);
        if(picosFebris!=0)
            itens.put("picosFebris",picosFebris);
        if(balancoHidrico>0)
            itens.put("balancoHidrico",balancoHidrico);
        if(diurese>0)
            itens.put("diurese",diurese);
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
            return /*!evacuacoes.isEmpty() &&*/ curvaTermica>0 && /*!nutricao.isEmpty() &&*/ hemodinamicamente!=null;
        }
        else{
            return curvaTermica>0 /*&& !nutricao.isEmpty()*/ && hemodinamicamente!=null;
        }
    }

}
