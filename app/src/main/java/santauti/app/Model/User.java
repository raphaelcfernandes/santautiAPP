package santauti.app.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Raphael Fernandes on 21-May-17.
 */

public class User extends RealmObject implements Serializable{
    @SerializedName("user")
    private String user;
    @SerializedName("passw")
    private String password;
    @SerializedName("token")
    private String token;
    @SerializedName("tipoProfissional")
    private int tipoProfissional;
    @SerializedName("registro")
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}