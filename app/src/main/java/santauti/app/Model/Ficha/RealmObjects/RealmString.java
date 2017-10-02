package santauti.app.Model.Ficha.RealmObjects;

import io.realm.RealmObject;

/**
 * Created by rapha on 01-Oct-17.
 */

public class RealmString extends RealmObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
