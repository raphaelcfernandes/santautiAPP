package santauti.app.Model.Ficha;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rapha on 06-Oct-17.
 */
@IgnoreExtraProperties
public class BombaInfusao {
    private Map<String,Integer> dispositivos;

    public BombaInfusao(Map<String, Integer> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public void initializeMap(){
        if(dispositivos==null)
            dispositivos = new HashMap<>();
    }

    public void inserseDispositivo(String string, int value){
        dispositivos.put(string,value);
    }

    public Map<String, Integer> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(Map<String, Integer> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public BombaInfusao() {
    }

    @Exclude
    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<>();
        if(dispositivos!=null)
            map.put("BombaInfusao",dispositivos);
        return map;
    }
}
