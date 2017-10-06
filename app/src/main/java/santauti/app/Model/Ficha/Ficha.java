package santauti.app.Model.Ficha;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import santauti.app.Model.Ficha.BombaInfusao.BombaInfusao;
import santauti.app.Model.Ficha.FolhasBalanco.FolhasBalanco;
import santauti.app.Model.Paciente;
import santauti.app.Model.User;

/**
 * Created by Raphael Fernandes on 25-May-17.
 */

public class Ficha extends RealmObject implements Serializable{

    @PrimaryKey
    @SerializedName("NroAtendimento")
    private int NroAtendimento;
    @SerializedName("dataCriado")
    private Date dataCriado;
    @SerializedName("user")
    private User user;
    @SerializedName("paciente")
    private Paciente paciente;
    @SerializedName("metabolico")
    private Metabolico metabolico;
    @SerializedName("infeccioso")
    private Infeccioso infeccioso;
    @SerializedName("endocrino")
    private Endocrino endocrino;
    @SerializedName("renal")
    private Renal renal;
    @SerializedName("hematologico")
    private Hematologico hematologico;
    @SerializedName("gastrointestinal")
    private Gastrointestinal gastrointestinal;
    @SerializedName("respiratorio")
    private Respiratorio respiratorio;
    @SerializedName("neurologico")
    private Neurologico neurologico;
    @SerializedName("hemodinamico")
    private Hemodinamico hemodinamico;
    @SerializedName("monitorMultiparametrico")
    private MonitorMultiparametrico monitorMultiparametrico;
    @SerializedName("dispositivos")
    private Dispositivos dispositivos;
    @SerializedName("osteomuscular")
    private Osteomuscular osteomuscular;
    @SerializedName("nutricional")
    private Nutricional nutricional;
    @SerializedName("pelesMucosas")
    private PelesMucosas pelesMucosas;
    @SerializedName("respirador")
    private Respirador respirador;
    @SerializedName("bombaInfusao")
    private BombaInfusao bombaInfusao;
    @SerializedName("folhasBalanco")
    private FolhasBalanco folhasBalanco;
    @SerializedName("exames")
    private Exames exames;

    public Exames getExames() {
        return exames;
    }

    public void setExames(Exames exames) {
        this.exames = exames;
    }

    public FolhasBalanco getFolhasBalanco() {
        return folhasBalanco;
    }

    public void setFolhasBalanco(FolhasBalanco folhasBalanco) {
        this.folhasBalanco = folhasBalanco;
    }

    public BombaInfusao getBombaInfusao() {
        return bombaInfusao;
    }

    public void setBombaInfusao(BombaInfusao bombaInfusao) {
        this.bombaInfusao = bombaInfusao;
    }

    public Respirador getRespirador() {
        return respirador;
    }

    public void setRespirador(Respirador respirador) {
        this.respirador = respirador;
    }

    public PelesMucosas getPelesMucosas() {
        return pelesMucosas;
    }

    public void setPelesMucosas(PelesMucosas pelesMucosas) {
        this.pelesMucosas = pelesMucosas;
    }

    public Nutricional getNutricional() {
        return nutricional;
    }

    public void setNutricional(Nutricional nutricional) {
        this.nutricional = nutricional;
    }

    public Osteomuscular getOsteomuscular() {
        return osteomuscular;
    }

    public void setOsteomuscular(Osteomuscular osteomuscular) {
        this.osteomuscular = osteomuscular;
    }

    public Dispositivos getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(Dispositivos dispositivos) {
        this.dispositivos = dispositivos;
    }

    public MonitorMultiparametrico getMonitorMultiparametrico() {
        return monitorMultiparametrico;
    }

    public void setMonitorMultiparametrico(MonitorMultiparametrico monitorMultiparametrico) {
        this.monitorMultiparametrico = monitorMultiparametrico;
    }

    public Hemodinamico getHemodinamico() {
        return hemodinamico;
    }

    public void setHemodinamico(Hemodinamico hemodinamico) {
        this.hemodinamico = hemodinamico;
    }

    public Neurologico getNeurologico() {
        return neurologico;
    }

    public void setNeurologico(Neurologico neurologico) {
        this.neurologico = neurologico;
    }

    public Respiratorio getRespiratorio() {
        return respiratorio;
    }

    public void setRespiratorio(Respiratorio respiratorio) {
        this.respiratorio = respiratorio;
    }

    public Gastrointestinal getGastrointestinal() {
        return gastrointestinal;
    }

    public void setGastrointestinal(Gastrointestinal gastrointestinal) {
        this.gastrointestinal = gastrointestinal;
    }

    public Hematologico getHematologico() {
        return hematologico;
    }

    public void setHematologico(Hematologico hematologico) {
        this.hematologico = hematologico;
    }

    public Renal getRenal() {
        return renal;
    }

    public void setRenal(Renal renal) {
        this.renal = renal;
    }

    public Endocrino getEndocrino() {
        return endocrino;
    }

    public void setEndocrino(Endocrino endocrino) {
        this.endocrino = endocrino;
    }

    public Infeccioso getInfeccioso() {
        return infeccioso;
    }

    public void setInfeccioso(Infeccioso infeccioso) {
        this.infeccioso = infeccioso;
    }

    public Metabolico getMetabolico() {
        return metabolico;
    }

    public void setMetabolico(Metabolico metabolico) {
        this.metabolico = metabolico;
    }

    public int getNroAtendimento() {
        return NroAtendimento;
    }

    public void setNroAtendimento(int nroAtendimento) {
        NroAtendimento = nroAtendimento;
    }

    public Date getDataCriado() {
        return dataCriado;
    }

    public void setDataCriado(Date dataCriado) {
        this.dataCriado = dataCriado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
