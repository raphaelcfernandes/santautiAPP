package santauti.app.Model.Ficha;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raphael Fernandes on 26-May-17.
 */

public class Endocrino {
    private String curvaGlicemica;

    public String getCurvaGlicemica() {
        return curvaGlicemica;
    }

    public void setCurvaGlicemica(String curvaGlicemica) {
        this.curvaGlicemica = curvaGlicemica;
    }

    public Endocrino(String curvaGlicemia) {
        this.curvaGlicemica = curvaGlicemia;
    }

    public Endocrino() {
    }

    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("curvaGlicemica", curvaGlicemica);
        HashMap<String,Object> finalResult = new HashMap<>();
        finalResult.put("Endocrino",result);
        return finalResult;
    }
}

