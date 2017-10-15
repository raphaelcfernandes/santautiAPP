package santauti.app.Activities.Ficha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import santauti.app.Activities.Ficha.PartesMedicas.BombaInfusaoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.DispositivoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.EndocrinoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.ExamesActivity;
import santauti.app.Activities.Ficha.PartesMedicas.FolhasBalancoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.GastrointestinalActivity;
import santauti.app.Activities.Ficha.PartesMedicas.HematologicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.HemodinamicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.InfecciosoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.MetabolicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.MonitorMultiparametricoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.NeurologicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.NutricionalActivity;
import santauti.app.Activities.Ficha.PartesMedicas.OsteomuscularActivity;
import santauti.app.Activities.Ficha.PartesMedicas.PelesMucosasActivity;
import santauti.app.Activities.Ficha.PartesMedicas.RenalActivity;
import santauti.app.Activities.Ficha.PartesMedicas.RespiradorActivity;
import santauti.app.Activities.Ficha.PartesMedicas.RespiratorioActivity;
import santauti.app.Adapters.Ficha.FichaAdapterModel;
import santauti.app.Adapters.Ficha.FichaSectionAdapter;
import santauti.app.Model.Ficha.Ficha;
import santauti.app.R;

public class FichaActivity extends GenericoActivity{
    private RecyclerView recyclerView;
    public static FichaSectionAdapter adapter;
    public static List<FichaAdapterModel> fichaAdapterModelList;
    private Intent intent;
    private FloatingActionButton floatingActionButton;
    private Ficha ficha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);
        setToolbar(this.getString(R.string.Evolucao));

        /*******************LAYOUT VARIAVEIS*******************************/
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFichaToServer(view);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        /*******************LAYOUT VARIAVEIS*******************************/


        /*******************Inicializacao RecyclerView*******************/
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);//O int representa quantos cards terão por grid
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        /*******************Inicializacao RecyclerView*******************/

        fichaAdapterModelList = new ArrayList<>();
        //createNewFicha();
        prepareFichas();

    }

    /**
     * Método para criar um novo Objeto do tipo Model.Ficha
     */
    private void createNewFicha(){
//        SharedPreferences sp = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//        Ficha ficha = new Ficha(sp.getString("pacienteKey",""),sp.getString("medicoKey",""));
//        String fichaKeyGeradaPeloFirebase = databaseReference.child("Fichas").push().getKey();
//        databaseReference.child("Fichas").child(fichaKeyGeradaPeloFirebase).setValue(ficha);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("FichaKEY",fichaKeyGeradaPeloFirebase);
//        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sp = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int i=0;
        for(FichaAdapterModel fichaAdapterModel : fichaAdapterModelList)
            if(fichaAdapterModel.getColor()==1)
                i++;
//        if(i==fichaAdapterModelList.size())
//            floatingActionButton.setVisibility(View.VISIBLE);

    }

    /**
     * Seta as cards clicáveis para cada activity
     */
    FichaSectionAdapter.OnItemClickListener onItemClickListener = new FichaSectionAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            if(position==0)
                intent = new Intent(v.getContext(), ExamesActivity.class);
            else if(position==1)
                intent = new Intent(v.getContext(), FolhasBalancoActivity.class);
            else if(position==2)
                intent = new Intent(v.getContext(), MonitorMultiparametricoActivity.class);
            else if(position==3)
                intent = new Intent(v.getContext(), BombaInfusaoActivity.class);
            else if(position==4)
                intent = new Intent(v.getContext(), DispositivoActivity.class);
            else if(position==5)
                intent = new Intent(v.getContext(), RespiradorActivity.class);
            else if(position==6)
                intent = new Intent(v.getContext(), NeurologicoActivity.class);
            else if(position==7)
                intent = new Intent(v.getContext(), HemodinamicoActivity.class);
            else if(position==8)
                intent = new Intent(v.getContext(), RespiratorioActivity.class);
            else if(position==9)
                intent = new Intent(v.getContext(), GastrointestinalActivity.class);
            else if(position==10)
                intent = new Intent(v.getContext(), RenalActivity.class);
            else if(position==11)
                intent = new Intent(v.getContext(), MetabolicoActivity.class);
            else if(position==12)
                intent = new Intent(v.getContext(), InfecciosoActivity.class);
            else if(position==13)
                intent = new Intent(v.getContext(), NutricionalActivity.class);
            else if(position==14)
                intent = new Intent(v.getContext(), HematologicoActivity.class);
            else if(position==15)
                intent = new Intent(v.getContext(), EndocrinoActivity.class);
            else if(position==16)
                intent = new Intent(v.getContext(), PelesMucosasActivity.class);
            else if(position==17)
                intent = new Intent(v.getContext(), OsteomuscularActivity.class);
            prepareIntent(position,intent);
            startActivity(intent);
        }
    };

    /**
     * Método responsável por fazer a lista de fichas na activity
     */
    private void prepareFichas() {
        int[] covers = new int[]{
                R.drawable.x_ray,
                R.drawable.folhas_balanco,
                R.drawable.icu_monitor,
                R.drawable.hqdefault,
                R.drawable.sphygmomanometer,
                R.drawable.respirator,
                R.drawable.brain,
                R.drawable.cardiogram,
                R.drawable.lungs,
                R.drawable.intestine,
                R.drawable.kidneys,
                R.drawable.exercise,
                R.drawable.cell,
                R.drawable.nutrition,
                R.drawable.blood_drop,
                R.drawable.thyroid,
                R.drawable.peles_mucosas,
                R.drawable.osteomuscular
        };

        FichaAdapterModel a = new FichaAdapterModel(this.getString(R.string.Exames), covers[0],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.FolhasBalanco), covers[1],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.MonitorMultiparametrico),covers[2],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.BombaInfusao),covers[3],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.Dispositivos),covers[4],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.Respirador),covers[5],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.Neurologico),covers[6],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.Hemodinamico),covers[7],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.Respiratorio), covers[8],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.GastroIntestinal), covers[9],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.Renal), covers[10],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.Metabolico), covers[11],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.Infeccioso), covers[12],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.Nutricional), covers[13],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.Hematologico), covers[14],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.Endocrino), covers[15],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.PelesMucosas), covers[16],0);
        fichaAdapterModelList.add(a);

        a = new FichaAdapterModel(this.getString(R.string.OsteoMuscular), covers[17],0);
        fichaAdapterModelList.add(a);

        adapter = new FichaSectionAdapter(this, fichaAdapterModelList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onItemClickListener);

    }


    /**
     * Envia para o servidor a ficha contendo todas as informacoes pertinentes aos seus campos
     * envia: Ficha (objeto)
     * Recebe: http statusCode
     */
    private void sendFichaToServer(final View view){
//        Ficha fichaToSend = realm.copyFromRealm(realm.where(Ficha.class).equalTo("NroAtendimento",idCriado).findFirst());
//        Call<Ficha> call = apiService.sendFichaFromAppToServer(fichaToSend);
//        call.enqueue(new Callback<Ficha>() {
//            @Override
//            public final void onResponse(@NonNull Call<Ficha> call, @NonNull Response<Ficha> response) {
//                if(response.isSuccessful()) {
//                    //SnackbarCreator.createText(view, "Ficha salva com sucesso!");
//                    Snackbar snackbar = Snackbar.make(view,"Ficha salva com sucesso!",Snackbar.LENGTH_SHORT);
//                    snackbar.addCallback(new Snackbar.Callback() {
//                        @Override
//                        public void onDismissed(Snackbar snackbar, int event) {
//                            finish();
//                        }
//                        @Override
//                        public void onShown(Snackbar snackbar) {
//                        }
//                    });
//                    SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPref.edit();
//                    editor.remove("NroAtendimento");
//                    editor.apply();
//                    snackbar.show();
//                    //finish();
//                }
//                else
//                    SnackbarCreator.createText(view, "Teste");
//            }
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
//                t.printStackTrace();
//                Log.d("ERROR",t.getMessage());
//            }
//        });
    }

}
