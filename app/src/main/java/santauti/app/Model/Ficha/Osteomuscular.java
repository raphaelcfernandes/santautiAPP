package santauti.app.Model.Ficha;

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
}
