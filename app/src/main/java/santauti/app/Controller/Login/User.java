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