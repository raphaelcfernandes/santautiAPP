package santauti.app.Model.Ficha.BombaInfusao;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by rapha on 06-Oct-17.
 */

public class BombaInfusao extends RealmObject implements Serializable{
    @SerializedName("drogasVasoativas")
    private RealmList<BombaInfusaoItens> bombaInfusaoItens;

    public RealmList<BombaInfusaoItens> getBombaInfusaoItens() {
        return bombaInfusaoItens;
    }

    public void setBombaInfusaoItens(RealmList<BombaInfusaoItens> bombaInfusaoItens) {
        this.bombaInfusaoItens = bombaInfusaoItens;
    }
}
