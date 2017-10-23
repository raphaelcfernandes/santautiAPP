package santauti.app.Model.Ficha;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Raphael Fernandes on 29-May-17.
 */

public class Gastrointestinal implements FichaMetodos {
    private String formato;
    private String tensao;
    private String ruidos;
    private String ascite;
    private List<String> massasPalpaveis;
    @Exclude
    private boolean massasPalpaveisFlag;
    private List<String> viscerasPalpaveis;
    @Exclude
    private boolean viscerasPalpaveisFlag;
    private Map<String,Object> ostomias;
    @Exclude
    private boolean ostomiasFlag;

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> itens = new HashMap<>();
        Map<String,Object> massasPalpaveisMap = new HashMap<>();
        Map<String,Object> viscerasPalpaveisMap = new HashMap<>();
        Map<String,Object> finalResult = new HashMap<>();
        if(formato!=null)
            itens.put("formato",formato);
        if(tensao!=null)
            itens.put("tensao",tensao);
        if(ruidos!=null)
            itens.put("ruidos",ruidos);
        if(ascite!=null)
            itens.put("ascite",ascite);
        if(massasPalpaveisFlag && !massasPalpaveis.isEmpty()){
            for(String string : massasPalpaveis)
                massasPalpaveisMap.put(string,true);
            itens.put("massasPalpaveis",massasPalpaveisMap);
        }
        else
            itens.put("massasPalpaveis",false);
        if(viscerasPalpaveisFlag && !viscerasPalpaveis.isEmpty()){
            for(String string : viscerasPalpaveis)
                viscerasPalpaveisMap.put(string,true);
            itens.put("viscerasPalpaveis",viscerasPalpaveisMap);
        }
        else
            itens.put("viscerasPalpaveis",false);
        if(ostomiasFlag && !ostomias.isEmpty()) {
            itens.put("ostomias", ostomias);
        }
        else
            itens.put("ostomias",false);
        finalResult.put("Gastrointestinal",itens);
        return finalResult;
    }

    public void insereOstomia(String titulo, List<String> itens){
        Map<String,Object> ostomia = new HashMap<>();
        for (int i=0 ;i < itens.size(); i++){
            if(i==0){
                ostomia.put("funcionamento",itens.get(i));
            }
            else
                ostomia.put("qualidade",itens.get(i));
        }
        this.ostomias.put(titulo,ostomia);
    }

    public void initialiazeMap(){
        if(this.ostomias==null)
            this.ostomias = new HashMap<>();
    }

    public boolean checkObject(){
        boolean flagoostomia=true;
        boolean viscerasFlag=true;
        boolean massasFlag=true;
//        if(ostomiasFlag && ostomias.isEmpty())
//            flagoostomia=false;
//        if(viscerasPalpaveisFlag && viscerasPalpaveis.isEmpty())
//            viscerasFlag=false;
//        if(massasPalpaveisFlag && massasPalpaveis.isEmpty())
//            massasFlag=false;
        return ruidos!=null && formato!=null && tensao!=null && ascite!=null && flagoostomia && viscerasFlag && massasFlag;
    }

    public boolean isMassasPalpaveisFlag() {
        return massasPalpaveisFlag;
    }

    public void setMassasPalpaveisFlag(boolean massasPalpaveisFlag) {
        this.massasPalpaveisFlag = massasPalpaveisFlag;
    }

    public boolean isViscerasPalpaveisFlag() {
        return viscerasPalpaveisFlag;
    }

    public void setViscerasPalpaveisFlag(boolean viscerasPalpaveisFlag) {
        this.viscerasPalpaveisFlag = viscerasPalpaveisFlag;
    }

    public boolean isOstomiasFlag() {
        return ostomiasFlag;
    }

    public void setOstomiasFlag(boolean ostomiasFlag) {
        this.ostomiasFlag = ostomiasFlag;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getTensao() {
        return tensao;
    }

    public void setTensao(String tensao) {
        this.tensao = tensao;
    }

    public String getRuidos() {
        return ruidos;
    }

    public void setRuidos(String ruidos) {
        this.ruidos = ruidos;
    }

    public String getAscite() {
        return ascite;
    }

    public void setAscite(String ascite) {
        this.ascite = ascite;
    }


    public Gastrointestinal() {
    }

    public List<String> getMassasPalpaveis() {
        return massasPalpaveis;
    }

    public void setMassasPalpaveis(List<String> massasPalpaveis) {
        this.massasPalpaveis = massasPalpaveis;
    }

    public List<String> getViscerasPalpaveis() {
        return viscerasPalpaveis;
    }

    public void setViscerasPalpaveis(List<String> viscerasPalpaveis) {
        this.viscerasPalpaveis = viscerasPalpaveis;
    }

    public Map<String, Object> getOstomias() {
        return ostomias;
    }

    public void setOstomias(Map<String, Object> ostomias) {
        this.ostomias = ostomias;
    }

    public Gastrointestinal(String formato, String tensao, String ruidos, String ascite, List<String> massasPalpaveis, boolean massasPalpaveisFlag, List<String> viscerasPalpaveis, boolean viscerasPalpaveisFlag, Map<String, Object> ostomias, boolean ostomiasFlag) {
        this.formato = formato;
        this.tensao = tensao;
        this.ruidos = ruidos;
        this.ascite = ascite;
        this.massasPalpaveis = massasPalpaveis;
        this.massasPalpaveisFlag = massasPalpaveisFlag;
        this.viscerasPalpaveis = viscerasPalpaveis;
        this.viscerasPalpaveisFlag = viscerasPalpaveisFlag;
        this.ostomias = ostomias;
        this.ostomiasFlag = ostomiasFlag;
    }


}
