package santauti.app.Model.Ficha;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raphael Fernandes on 26-May-17.
 */

public class Infeccioso {

    private List<String> infeccioso;

    public Infeccioso(List<String> dispositivos) {
        this.infeccioso = dispositivos;
    }

    public Infeccioso() {
    }

    public void initializeList(){
        if(infeccioso ==null)
            infeccioso = new ArrayList<>();
    }

    public void addString(String string){
        infeccioso.add(string);
    }

    public List<String> getInfeccioso() {
        return infeccioso;
    }

    public void setInfeccioso(List<String> infeccioso) {
        this.infeccioso = infeccioso;
    }

    @Exclude
    public Map<String,Object> toMap(){
        Map<String,Object> stringListMap = new HashMap<>();
        Map<String,Object> dispostivosMap = new  HashMap<>();
        if(infeccioso !=null){
            for(int i = 0; i< infeccioso.size(); i++)
                stringListMap.put(Integer.toString(i), infeccioso.get(i));
            dispostivosMap.put("Infeccioso",stringListMap);
        }
        return dispostivosMap;
    }
}
