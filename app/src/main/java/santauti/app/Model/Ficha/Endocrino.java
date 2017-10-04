package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 26-May-17.
 */

public class Endocrino extends RealmObject implements Serializable {
    @SerializedName("curvaGlicemia")
    private String curvaGlicemia;

    public String getCurvaGlicemia() {
        return curvaGlicemia;
    }

    public void setCurvaGlicemia(String curvaGlicemia) {
        this.curvaGlicemia = curvaGlicemia;
    }
}

