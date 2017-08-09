package santauti.app.Adapters.Ficha.BombaInfusao;

import android.widget.ImageView;

/**
 * Created by Raphael Fernandes on 14-Jun-17.
 */

public class BombaInfusaoAdapterModel {
    private ImageView deleteIcon;
    private ImageView editIcon;
    private String droga;
    private int velInfusao;

    public BombaInfusaoAdapterModel(String droga, int velInfusao){
        this.droga=droga;
        this.velInfusao=velInfusao;
    }

    public int getVelInfusao() {
        return velInfusao;
    }

    public void setVelInfusao(int velInfusao) {
        this.velInfusao = velInfusao;
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

}
