package santauti.app.Model.Ficha;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rapha on 03-Oct-17.
 */
@IgnoreExtraProperties
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        if (tonusMuscular!=null)
            result.put("tonusMuscular",tonusMuscular);
        if(trofismoMuscular!=null)
            result.put("trofismoMuscular",trofismoMuscular);
        return result;
    }
}
