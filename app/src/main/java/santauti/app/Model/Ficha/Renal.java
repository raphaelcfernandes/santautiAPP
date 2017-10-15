package santauti.app.Model.Ficha;

/**
 * Created by Raphael Fernandes on 26-May-17.
 */

public class Renal{
    private String urina;
    private int peso;
    private boolean dialise;
    private boolean UF;
    private int volume;
    private int tempo;

    public boolean checkObject(){
        if(isDialise()){
            return peso>0 && urina!=null && volume>0 && tempo>0;
        }
        else
            return peso>0 && urina!=null;
    }

    public String getUrina() {
        return urina;
    }

    public void setUrina(String urina) {
        this.urina = urina;
    }

    public boolean isDialise() {
        return dialise;
    }

    public void setDialise(boolean dialise) {
        this.dialise = dialise;
    }

    public boolean isUF() {
        return UF;
    }

    public void setUF(boolean UF) {
        this.UF = UF;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

}
