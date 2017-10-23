package santauti.app.Model.Ficha;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raphael Fernandes on 26-May-17.
 */

public class Renal implements FichaMetodos{
    private String urina;
    private int peso;
    @Exclude
    private boolean dialise;
    private boolean UF;
    private int volume;
    private int tempo;

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> itens = new HashMap<>();
        Map<String,Object> emDialise = new HashMap<>();
        Map<String,Object> finalResult = new HashMap<>();
        if(peso>0)
            itens.put("peso",peso);
        if(urina!=null)
            itens.put("urina",urina);
        if(dialise){
            if(UF)
                emDialise.put("UF",UF);
            else
                emDialise.put("UF",UF);
            if(volume>0)
                emDialise.put("volume",volume);
            if(tempo>0)
                emDialise.put("tempo",tempo);
            itens.put("emDialise",emDialise);
        }
        else
            itens.put("emDialise",false);
        finalResult.put("Renal",itens);
        return finalResult;
    }

    public boolean checkObject(){
        if(isDialise()){
            return peso>0 && urina!=null && volume>0 && tempo>0;
        }
        else
            return peso>0 && urina!=null;
    }

    public String getUrina() {
        return urina;
    }

    public void setUrina(String urina) {
        this.urina = urina;
    }

    public boolean isDialise() {
        return dialise;
    }

    public void setDialise(boolean dialise) {
        this.dialise = dialise;
    }

    public boolean isUF() {
        return UF;
    }

    public void setUF(boolean UF) {
        this.UF = UF;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

}
