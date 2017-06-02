package santauti.app.Model.Ficha.Neurologico;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 02-Jun-17.
 */

public class Opcionais extends RealmObject {
    private int pic;
    private int ppc;
    private int sjo2;

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getPpc() {
        return ppc;
    }

    public void setPpc(int ppc) {
        this.ppc = ppc;
    }

    public int getSjo2() {
        return sjo2;
    }

    public void setSjo2(int sjo2) {
        this.sjo2 = sjo2;
    }
}
