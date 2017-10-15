package santauti.app.Model;

/**
 * Created by Raphael Fernandes on 21-May-17.
 */

public class User {
    private String email;
    private String password;
    private String token;
    private int tipoProfissional;
    private int registro;

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTipoProfissional() {
        return tipoProfissional;
    }

    public void setTipoProfissional(int tipoProfissional) {
        this.tipoProfissional = tipoProfissional;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}