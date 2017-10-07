package santauti.app.Model.Ficha.FolhasBalanco;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by rapha on 06-Oct-17.
 */

public class FolhasBalanco extends RealmObject implements Serializable {
    @SerializedName("curvaTermica")
    private String curvaTermica;
    @SerializedName("evacuacoesFlag")
    private boolean evacuacoesFlag;
    @SerializedName("evacuacoesList")
    private RealmList<Evacuacoes> evacuacoesList;
    @SerializedName("nutricao")
    private RealmList<Nutricao> nutricao;

    public boolean checkObject(){
        if(evacuacoesFlag){
            return !evacuacoesList.isEmpty() && curvaTermica!=null && !nutricao.isEmpty();
        }
        else{
            return curvaTermica!=null && !nutricao.isEmpty();
        }
    }

    public String getCurvaTermica() {
        return curvaTermica;
    }

    public void setCurvaTermica(String curvaTermica) {
        this.curvaTermica = curvaTermica;
    }

    public boolean isEvacuacoesFlag() {
        return evacuacoesFlag;
    }

    public void setEvacuacoesFlag(boolean evacuacoesFlag) {
        this.evacuacoesFlag = evacuacoesFlag;
    }

    public RealmList<Evacuacoes> getEvacuacoesList() {
        return evacuacoesList;
    }

    public void setEvacuacoesList(RealmList<Evacuacoes> evacuacoesList) {
        this.evacuacoesList = evacuacoesList;
    }

    public RealmList<Nutricao> getNutricao() {
        return nutricao;
    }

    public void setNutricao(RealmList<Nutricao> nutricao) {
        this.nutricao = nutricao;
    }
}
