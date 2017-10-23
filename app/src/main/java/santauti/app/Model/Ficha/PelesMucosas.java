package santauti.app.Model.Ficha;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by rapha on 04-Oct-17.
 */

public class PelesMucosas implements FichaMetodos{
    private String pele;
    private List<String> ulceraPressao;
    private int ictericia;
    private List<String> mucosas;
    @Exclude
    private boolean ulceraP;
    @Exclude
    private boolean ictericiaFlag;

    public boolean isUlceraP() {
        return ulceraP;
    }

    public void setUlceraP(boolean ulceraP) {
        this.ulceraP = ulceraP;
    }

    public boolean isIctericiaFlag() {
        return ictericiaFlag;
    }

    public void setIctericiaFlag(boolean ictericiaFlag) {
        this.ictericiaFlag = ictericiaFlag;
    }

    public String getPele() {
        return pele;
    }

    public void initializeLists(){
        if(ulceraPressao==null)
            ulceraPressao = new ArrayList<>();
        if(mucosas==null)
            mucosas = new ArrayList<>();
    }

    public void addUlceraPressao(String string){
        ulceraPressao.add(string);
    }

    public void addtMucosas(String string){
        mucosas.add(string);
    }

    public void setPele(String pele) {
        this.pele = pele;
    }

    public List<String> getUlceraPressao() {
        return ulceraPressao;
    }

    public void setUlceraPressao(List<String> ulceraPressao) {
        this.ulceraPressao = ulceraPressao;
    }

    public int getIctericia() {
        return ictericia;
    }

    public void setIctericia(int ictericia) {
        this.ictericia = ictericia;
    }

    public List<String> getMucosas() {
        return mucosas;
    }

    public void setMucosas(List<String> mucosas) {
        this.mucosas = mucosas;
    }

    public PelesMucosas(String pele, List<String> ulceraPressao, int ictericia, List<String> mucosas) {
        this.pele = pele;
        this.ulceraPressao = ulceraPressao;
        this.ictericia = ictericia;
        this.mucosas = mucosas;
    }

    public PelesMucosas() {
    }

    @Exclude
    @Override
    public Map<String,Object> toMap(){
        Map<String,Object> itens = new HashMap<>();
        Map<String,Object> mucosasMap = new HashMap<>();
        Map<String,Object> ulceraPMap = new HashMap<>();
        Map<String,Object> finalResult = new HashMap<>();
        if(pele!=null)
            itens.put("pele",pele);
        if(ictericia>0 && ictericiaFlag)
            itens.put("ictericia",ictericia);
        if(mucosas!=null)
            for(String str : mucosas)
                mucosasMap.put(str,true);
        if(ulceraPressao!=null && ulceraP)
            for(String str : ulceraPressao)
                ulceraPMap.put(str,true);
        itens.put("UlceraPressao",ulceraPMap);
        itens.put("Mucosas",mucosasMap);
        finalResult.put("PelesMucosas",itens);
        return finalResult;
    }

    @Override
    public boolean checkObject() {
        if(ulceraP)
            return pele!=null && !ulceraPressao.isEmpty() && mucosas.size()==3;
        if(ictericiaFlag)
            return pele!=null && ictericia>0 && mucosas.size()==3;
        if(ulceraP && ictericiaFlag)
            return pele!=null&& mucosas.size()==3 && ictericia>0 && !ulceraPressao.isEmpty();
        else
            return pele!=null && mucosas.size()==3;
    }
}
