package santauti.app.Activities.Ficha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmResults;
import io.realm.RealmSchema;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santauti.app.APIServices.APIService;
import santauti.app.APIServices.RestClient;
import santauti.app.Activities.Ficha.PartesMedicas.EndocrinoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.GastrointestinalActivity;
import santauti.app.Activities.Ficha.PartesMedicas.HematologicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.HemodinamicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.InfecciosoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.MetabolicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.NeurologicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.RenalActivity;
import santauti.app.Activities.Ficha.PartesMedicas.RespiratorioActivity;
import santauti.app.Activities.MainActivity;
import santauti.app.Activities.SnackbarCreator;
import santauti.app.Adapters.Ficha.FichaAdapterModel;
import santauti.app.Adapters.Ficha.FichaSectionAdapter;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.Model.Paciente;
import santauti.app.Model.User;
import santauti.app.R;

public class FichaActivity extends GenericoActivity {
    private RecyclerView recyclerView;
    public static FichaSectionAdapter adapter;
    public static List<FichaAdapterModel> fichaAdapterModelList;
    private Intent intent;
    private int idCriado;
    private Realm realm;
    private FloatingActionButton floatingActionButton;
    APIService apiService;
    private Ficha ficha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);
        setToolbar(this.getString(R.string.Evolucao));
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);

        apiService = RestClient.getClient(FichaActivity.this).create(APIService.class);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        fichaAdapterModelList = new ArrayList<>();
        prepareFichas();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);//O int represnta quantos cards terão por grid
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adapter.setOnItemClickListener(onItemClickListener);
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getDefaultInstance();

        createNewFicha();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFichaToServer(view);
            }
        });
    }

    private void createNewFicha(){
        int idPaciente = getIntent().getIntExtra("idPaciente",0);
        realm.beginTransaction();

        ficha = new Ficha();
        Number currentIdNum = realm.where(Ficha.class).max("NroAtendimento");
        idCriado = currentIdNum == null? 1 : currentIdNum.intValue()+1;
        ficha.setNroAtendimento(idCriado);
        Calendar c = Calendar.getInstance();
        ficha.setDataCriado(c.getTime());

        User user = realm.createObject(User.class);
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        user.setRegistro(sharedPref.getInt(getString(R.string.registroMedico),0));
        user.setToken(sharedPref.getString("acess_token",""));
        user.setTipoProfissional(sharedPref.getInt("tipoProfissional",0));

        Paciente paciente = realm.createObject(Paciente.class);
        paciente.setInternado(idPaciente);
        ficha.setPaciente(paciente);
        ficha.setUser(user);

        realm.insert(ficha);
        realm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Realm realm = Realm.getDefaultInstance();
        final Ficha query = realm.where(Ficha.class).equalTo("NroAtendimento",ficha.getNroAtendimento()).findFirst();
        int i=0;
        for(FichaAdapterModel fichaAdapterModel : fichaAdapterModelList)
            if(fichaAdapterModel.getColor()==1)
                i++;
        //if(i==fichaAdapterModelList.size())
        floatingActionButton.setVisibility(View.VISIBLE);

//        realm.executeTransaction(new Realm.Transaction(){
//            @Override
//            public void execute(Realm realm) {
//                query.deleteAllFromRealm();
//            }
//        });
    }

    FichaSectionAdapter.OnItemClickListener onItemClickListener = new FichaSectionAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            if(position==0)
                intent = new Intent(v.getContext(), NeurologicoActivity.class);
            else if(position==1)
                intent = new Intent(v.getContext(), HemodinamicoActivity.class);
            else if(position==2)
                intent = new Intent(v.getContext(), RespiratorioActivity.class);
            else if(position==3)
                intent = new Intent(v.getContext(), GastrointestinalActivity.class);
            else if(position==4)
                intent = new Intent(v.getContext(), RenalActivity.class);
            else if(position==5)
                intent = new Intent(v.getContext(), HematologicoActivity.class);
            else if(position==6)
                intent = new Intent(v.getContext(), EndocrinoActivity.class);
            else if(position==7)
                intent = new Intent(v.getContext(), InfecciosoActivity.class);
            else if(position==8)
                intent = new Intent(v.getContext(), MetabolicoActivity.class);
            intent.putExtra("Position",position);
            intent.putExtra("idFicha",idCriado);
            startActivityForResult(intent,position);
        }
    };

    private void prepareFichas() {
        int[] covers = new int[]{
                R.drawable.brain,
                R.drawable.cardiogram,
                R.drawable.lungs,
                R.drawable.intestine,
                R.drawable.kidneys,
                R.drawable.blood_drop,
                R.drawable.thyroid,
                R.drawable.cell,
                R.drawable.exercise};

        FichaAdapterModel a = new FichaAdapterModel("Neurologico",covers[0],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel("Hemodinamico",covers[1],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel("Respiratorio", covers[2],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel("Gastrointestinal", covers[3],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel("Renal", covers[4],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel("Hematologico", covers[5],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel("Endocrino", covers[6],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel("Infeccioso", covers[7],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel("Metabolico", covers[8],0);
        fichaAdapterModelList.add(a);

        adapter = new FichaSectionAdapter(this, fichaAdapterModelList);
        recyclerView.setAdapter(adapter);

    }

    private void sendFichaToServer(final View view){
        System.out.println(ficha.getNroAtendimento());
        Ficha fichaToSend = realm.where(Ficha.class).equalTo("NroAtendimento",ficha.getNroAtendimento()).findFirst();
        Call<Ficha> call = apiService.sendFichaFromAppToServer(fichaToSend.getUser().getToken(),fichaToSend);
        call.enqueue(new Callback<Ficha>() {
            @Override
            public final void onResponse(@NonNull Call<Ficha> call, @NonNull Response<Ficha> response) {
                if(response.isSuccessful())
                    SnackbarCreator.createText(view, "Perfil sem acesso a esta área");

                else
                    SnackbarCreator.createText(view, "Usuário e/ou senha incorretos");
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                t.printStackTrace();
                Log.d("ERROR",t.getMessage());
            }

        });
    }
}
