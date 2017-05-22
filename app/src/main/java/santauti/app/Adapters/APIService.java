package santauti.app.Adapters;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import santauti.app.Model.User;

/**
 * Created by Raphael Fernandes on 19-May-17.
 */

public interface APIService {
    @POST("/login")
    Call<User> login(@Body User user);
}