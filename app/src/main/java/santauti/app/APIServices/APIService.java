package santauti.app.APIServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.Model.Paciente;
import santauti.app.Model.User;

/**
 * Created by Raphael Fernandes on 19-May-17.
 */

public interface APIService {
    @POST("/login")
    Call<User> login(@Body User user);

    @GET("/getPacientes")
    Call<List<Paciente>> getPacientes(@Header("access_token") String token);

    @POST("/sendFichaFromAppToServer")
    Call<Ficha> sendFichaFromAppToServer(@Header("acess_token") String token,@Body Ficha ficha);
}