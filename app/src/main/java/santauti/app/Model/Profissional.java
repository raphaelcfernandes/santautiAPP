package santauti.app.Model;


import com.google.firebase.firestore.IgnoreExtraProperties;

/**
 * Created by rapha on 13-Oct-17.
 */
@IgnoreExtraProperties
public class Profissional {
    private boolean profissionalSaude;
    private String registro;
    private String especialidade;
    private String profissao;
    private String nome;
    private String sobrenome;

    public Profissional() {
    }

    public Profissional(boolean profissionalSaude, String registro, String especialidade, String profissao, String nome, String sobrenome) {
        this.profissionalSaude = profissionalSaude;
        this.registro = registro;
        this.especialidade = especialidade;
        this.profissao = profissao;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public boolean isProfissionalSaude() {
        return profissionalSaude;
    }

    public void setProfissionalSaude(boolean profissionalSaude) {
        this.profissionalSaude = profissionalSaude;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
}
