package santauti.app.Adapters.Ficha.Dispositivos;

import android.widget.ImageView;

/**
 * Created by Raphael Fernandes on 10-Jul-17.
 */

public class DispositivoAdapterModel {
    private ImageView deleteIcon;
    private String dispositivo;

    public DispositivoAdapterModel(String dispositivo){
        this.dispositivo=dispositivo;
    }

    public ImageView getDeleteIcon() {
        return deleteIcon;
    }

    public void setDeleteIcon(ImageView deleteIcon) {
        this.deleteIcon = deleteIcon;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }
}
