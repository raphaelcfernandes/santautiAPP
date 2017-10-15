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
    private String pacienteKey;

    public HomeModel(String nomePaciente, int boxPaciente, int leitoPaciente, int thumbnail, String medicoResponsavel, String pacienteKey) {
        this.nomePaciente = nomePaciente;
        this.boxPaciente = boxPaciente;
        this.leitoPaciente = leitoPaciente;
        this.thumbnail = thumbnail;
        this.medicoResponsavel = medicoResponsavel;
        this.pacienteKey = pacienteKey;
    }

    public String getPacienteKey() {
        return pacienteKey;
    }

    public void setPacienteKey(String pacienteKey) {
        this.pacienteKey = pacienteKey;
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
