package santauti.app.APIServices;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

import santauti.app.Model.Ficha.Ficha;

/**
 * Created by rapha on 17-Oct-17.
 */

public class FireBaseUtils {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }

    public static DatabaseReference getDatabaseReference(){
        return getDatabase().getReference();
    }

    public static String createNewFicha(String pacienteKey,String userKey,String hospitalKey){
        Ficha ficha = new Ficha(pacienteKey,userKey);
        Map<String,Object> newFicha = ficha.toMap();
        String key = getDatabaseReference().child("Hospital").child(hospitalKey).child("Fichas").push().getKey();
        getDatabaseReference().child("Hospital").child(hospitalKey).child("Fichas")
                .child(key).updateChildren(newFicha);
        return key;
    }

    public static DatabaseReference getHospitalReference(String hospitalKey){
        return getDatabaseReference().child("Hospital").child(hospitalKey);
    }

    public static DatabaseReference getFichaHospitalReference(String hospitalKey,String fichaKey){
        return getHospitalReference(hospitalKey).child(fichaKey);
    }

}
