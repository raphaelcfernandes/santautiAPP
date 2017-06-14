package santauti.app.Adapters.Ficha.Hemodinamico;

import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Created by Raphael Fernandes on 14-Jun-17.
 */

public class HemodinamicoModel {
    private ImageView deleteIcon;
    private ImageView editIcon;
    private String droga;
    private int dose;

    public HemodinamicoModel(String droga, int dose){

        this.droga=droga;
        this.dose=dose;
    }

    public ImageView getEditIcon() {
        return editIcon;
    }

    public void setEditIcon(ImageView editIcon) {
        this.editIcon = editIcon;
    }

    public ImageView getDeleteIcon() {
        return deleteIcon;
    }

    public void setDeleteIcon(ImageView deleteIcon) {
        this.deleteIcon = deleteIcon;
    }

    public String getDroga() {
        return droga;
    }

    public void setDroga(String droga) {
        this.droga = droga;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }
}
