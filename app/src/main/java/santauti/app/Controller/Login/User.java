package santauti.app.Controller.Login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Raphael Fernandes on 19-May-17.
 */

public class User {
    @SerializedName("user")
    private String user;
    @SerializedName("passw")
    private String password;
    @SerializedName("token")
    private String token;
    @SerializedName("tipoProfissional")
    private String tipoProfissional;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipoProfissional() {
        return tipoProfissional;
    }

    public void setTipoProfissional(String tipoProfissional) {
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