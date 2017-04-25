package santauti.app;

/**
 * Created by rapha on 25-Apr-17.
 */

public class FichaSection {
    private String name;
    private int thumbnail;

    public FichaSection() {
    }

    public FichaSection(String name, int thumbnail) {
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
