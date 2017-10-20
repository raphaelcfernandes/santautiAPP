package santauti.app.Model.Ficha;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rapha on 01-Oct-17.
 */

public class Dispositivos{
    private List<String> dispositivos;

    public Dispositivos(List<String> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public Dispositivos() {
    }

    public void initializeList(){
        if(dispositivos==null)
            dispositivos = new ArrayList<>();
    }

    public void addString(String string){
        dispositivos.add(string);
    }

    public List<String> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<String> dispositivos) {
        this.dispositivos = dispositivos;
    }

    @Exclude
    public Map<String,Object> toMap(){
        Map<String,Object> stringListMap = new HashMap<>();
        Map<String,Object> dispostivosMap = new  HashMap<>();
        if(dispositivos!=null){
            for(int i=0;i<dispositivos.size();i++)
                stringListMap.put(Integer.toString(i),dispositivos.get(i));
            dispostivosMap.put("Dispositivos",stringListMap);
        }
        return dispostivosMap;
    }
}
