package santauti.app.Model.Ficha.FolhasBalanco;

/**
 * Created by rapha on 06-Oct-17.
 */

public class FolhasBalanco {

    private String curvaTermica;
    private boolean evacuacoesFlag;
//    private RealmList<Evacuacoes> evacuacoesList;
//    private RealmList<Nutricao> nutricao;
    private String hemodinamicamente;

    public boolean checkObject(){
        if(evacuacoesFlag){
            return /*!evacuacoesList.isEmpty() &&*/ curvaTermica!=null && /*!nutricao.isEmpty() &&*/ hemodinamicamente!=null;
        }
        else{
            return curvaTermica!=null /*&& !nutricao.isEmpty()*/ && hemodinamicamente!=null;
        }
    }

    public String getHemodinamicamente() {
        return hemodinamicamente;
    }

    public void setHemodinamicamente(String hemodinamicamente) {
        this.hemodinamicamente = hemodinamicamente;
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

}
