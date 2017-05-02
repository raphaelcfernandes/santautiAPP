package santauti.app.Model.Ficha;

/**
 * Created by raphael fernandes on 25-Apr-17.
 */

@SuppressWarnings("DefaultFileTemplate")
public class Ficha {
    private String name;
    private int thumbnail;

    public Ficha(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
