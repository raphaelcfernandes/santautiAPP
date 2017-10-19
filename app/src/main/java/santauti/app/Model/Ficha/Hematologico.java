package santauti.app.Model.Ficha;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raphael Fernandes on 29-May-17.
 */

public class Hematologico {
    private String tromboprofilaxia;

    public String getTromboprofilaxia() {
        return tromboprofilaxia;
    }

    public void setTromboprofilaxia(String tromboprofilaxia) {
        this.tromboprofilaxia = tromboprofilaxia;
    }

    public Hematologico(String tromboprofilaxia) {
        this.tromboprofilaxia = tromboprofilaxia;
    }

    public Hematologico() {
    }

    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("tromboprofilaxia",tromboprofilaxia);
        HashMap<String,Object> finalResult = new HashMap<>();
        finalResult.put("Hematologico",result);
        return finalResult;
    }
}
