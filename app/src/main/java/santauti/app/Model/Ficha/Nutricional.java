package santauti.app.Model.Ficha;

/**
 * Created by rapha on 03-Oct-17.
 */

public class Nutricional {
    //private RealmList<RealmString> dieta;
    private String aceitacao;

    public boolean checkObject(){
        return aceitacao!=null /*&& !dieta.isEmpty()*/;
    }

    public String getAceitacao() {
        return aceitacao;
    }

    public void setAceitacao(String aceitacao) {
        this.aceitacao = aceitacao;
    }
}
