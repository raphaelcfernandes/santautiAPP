package santauti.app.Model;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by rapha on 12-Oct-17.
 */
@IgnoreExtraProperties
public class Hospital {
    private String cidade;
    private String endereco;
    private String nome;
//    private ArrayList<Paciente> pacientes;
//    private ArrayList<Profissional> profissionais;
    private String hospitalDocumentKey;

    public String getHospitalDocumentKey() {
        return hospitalDocumentKey;
    }

    public void setHospitalDocumentKey(String hospitalDocumentKey) {
        this.hospitalDocumentKey = hospitalDocumentKey;
    }

    public Hospital() {
    }

    public Hospital(String cidade, String endereco, String nome) {
        this.cidade = cidade;
        this.endereco = endereco;
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
