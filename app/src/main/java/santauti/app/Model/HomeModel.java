package santauti.app.Model;

/**
 * Created by rapha on 27-Apr-17.
 */

public class HomeModel {
    private String nomePaciente;
    private int boxPaciente;
    private int leitoPaciente;
    private int thumbnail;

    public HomeModel(String nome, int box, int leito, int thumbnail) {
        this.nomePaciente = nome;
        this.boxPaciente = box;
        this.leitoPaciente = leito;
        this.thumbnail = thumbnail;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public int getBoxPaciente() {
        return boxPaciente;
    }

    public void setBoxPaciente(int boxPaciente) {
        this.boxPaciente = boxPaciente;
    }

    public int getLeitoPaciente() {
        return leitoPaciente;
    }

    public void setLeitoPaciente(int leitoPaciente) {
        this.leitoPaciente = leitoPaciente;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
