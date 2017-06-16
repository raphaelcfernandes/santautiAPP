package santauti.app.Adapters.Ficha.Neurologico;

import android.widget.ImageView;

/**
 * Created by Raphael Fernandes on 16-Jun-17.
 */

public class NeurologicoAdapterModel {
    private ImageView deleteIcon;
    private ImageView editIcon;
    private String tipoSedativo;
    private int doseSedativo;

    public NeurologicoAdapterModel(String tipoSedativo, int doseSedativo) {
        this.tipoSedativo = tipoSedativo;
        this.doseSedativo = doseSedativo;
    }

    public ImageView getDeleteIcon() {
        return deleteIcon;
    }

    public void setDeleteIcon(ImageView deleteIcon) {
        this.deleteIcon = deleteIcon;
    }

    public ImageView getEditIcon() {
        return editIcon;
    }

    public void setEditIcon(ImageView editIcon) {
        this.editIcon = editIcon;
    }

    public String getTipoSedativo() {
        return tipoSedativo;
    }

    public void setTipoSedativo(String tipoSedativo) {
        this.tipoSedativo = tipoSedativo;
    }

    public int getDoseSedativo() {
        return doseSedativo;
    }

    public void setDoseSedativo(int doseSedativo) {
        this.doseSedativo = doseSedativo;
    }
}
