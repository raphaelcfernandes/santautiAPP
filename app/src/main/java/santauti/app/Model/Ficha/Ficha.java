package santauti.app.Model.Ficha;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raphael Fernandes on 25-May-17.
 */
@IgnoreExtraProperties
public class Ficha{
    private String pacienteKey;
    private String medicoKey;
    private Map<String, String> date;

    public Ficha() {
    }

    public Ficha(String pacienteKey, String medicoKey) {
        this.pacienteKey = pacienteKey;
        this.medicoKey = medicoKey;
    }

    public Ficha(String pacienteKey, String medicoKey, Map<String, String> date) {
        this.pacienteKey = pacienteKey;
        this.medicoKey = medicoKey;
        this.date = date;
    }

//    public static java.util.Map<String, String> getCreationDate() {
//        return ServerValue.TIMESTAMP;
//    }

    public String getPacienteKey() {
        return pacienteKey;
    }

    public void setPacienteKey(String pacienteKey) {
        this.pacienteKey = pacienteKey;
    }

    public String getMedicoKey() {
        return medicoKey;
    }

    public void setMedicoKey(String medicoKey) {
        this.medicoKey = medicoKey;
    }

    public Map<String, String> getDate() {
        return date;
    }

    public void setDate(Map<String, String> date) {
        this.date = date;
    }


    @Exclude
    public Map<String,Object> toMap(){
        HashMap<String,Object> result = new HashMap<>();
        result.put("pacienteKey",pacienteKey);
        result.put("medicoKey",medicoKey);
        return result;
    }
}
