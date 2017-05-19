package santauti.app.Controller.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Raphael Fernandes on 19-May-17.
 */

public interface APIService {
    @POST("/login")
    Call<User> login(@Body User user);
}