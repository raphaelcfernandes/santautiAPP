package santauti.app.Model.Ficha;

/**
 * Created by Raphael Fernandes on 25-Apr-17.
 */

@SuppressWarnings("DefaultFileTemplate")
public class Ficha {
    private String name;
    private int thumbnail;
    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Ficha(String name, int thumbnail, int color) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.color = color;

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