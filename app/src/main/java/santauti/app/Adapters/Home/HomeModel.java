package santauti.app.Adapters.Home;

/**
 * Created by Raphael Fernandes on 27-Apr-17.
 */

public class HomeModel {
    private String nomePaciente;
    private int boxPaciente;
    private int leitoPaciente;
    private int thumbnail;
    private String medicoResponsavel;
    private int idPaciente;
    private int responsavelRegistro;

    public HomeModel(String nome, int box, int leito, int thumbnail, String medicoResponsavel, int idPaciente, int responsavelRegistro) {
        this.nomePaciente = nome;
        this.boxPaciente = box;
        this.leitoPaciente = leito;
        this.thumbnail = thumbnail;
        this.medicoResponsavel = medicoResponsavel;
        this.responsavelRegistro = responsavelRegistro;
        this.idPaciente = idPaciente;

    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public int getResponsavelRegistro() {
        return responsavelRegistro;
    }

    public String getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public void setMedicoResponsavel(String medicoResponsavel) {
        this.medicoResponsavel = medicoResponsavel;
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
