package santauti.app.Model.Ficha;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rapha on 03-Oct-17.
 */

public class Osteomuscular {
    private String tonusMuscular;
    private String trofismoMuscular;

    public boolean checkObject(){
        return tonusMuscular!=null && trofismoMuscular!=null;
    }

    public String getTonusMuscular() {
        return tonusMuscular;
    }

    public void setTonusMuscular(String tonusMuscular) {
        this.tonusMuscular = tonusMuscular;
    }

    public String getTrofismoMuscular() {
        return trofismoMuscular;
    }

    public void setTrofismoMuscular(String trofismoMuscular) {
        this.trofismoMuscular = trofismoMuscular;
    }

    public Osteomuscular() {
    }

    public Osteomuscular(String tonusMuscular, String trofismoMuscular) {
        this.tonusMuscular = tonusMuscular;
        this.trofismoMuscular = trofismoMuscular;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        if (tonusMuscular!=null)
            result.put("tonusMuscular",tonusMuscular);
        if(trofismoMuscular!=null)
            result.put("trofismoMuscular",trofismoMuscular);
        HashMap<String,Object> finalResult = new HashMap<>();
        finalResult.put("Osteomuscular",result);
        return finalResult;
    }
}
