package santauti.app.Activities.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import santauti.app.APIServices.APIService;
import santauti.app.APIServices.RestClient;
import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.MainActivity;
import santauti.app.Adapters.Home.HomeAdapter;
import santauti.app.Adapters.Home.HomeModel;
import santauti.app.Model.Paciente;
import santauti.app.R;

public class HomeActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private List<HomeModel> homeModelList = null;
    private Toolbar tbar;
    private DrawerLayout drawerLayout;
    ProgressBar progressBar;
    private Intent intent;
    SharedPreferences sp;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        initNavigationDrawer();
//        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.sharedPrefecences),Context.MODE_PRIVATE);
//
//        System.out.println(sharedPref.getString("acess_token",""));
//        System.out.println(sharedPref.getInt(getString(R.string.registroMedico),0));
//        System.out.println(sharedPref.getInt("tipoProfissional",0));

        progress = (ProgressBar) findViewById(R.id.progressbar_recycler);


        sp = getSharedPreferences(getString(R.string.sharedPrefecences), 0);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        progressBar = (ProgressBar)findViewById(R.id.progressbar_recycler);
        if(homeModelList==null) {
            homeModelList = new ArrayList<>();
            requestPacienteList();
        }

        ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(false);
        SpannableString s = new SpannableString(toolbar.getTitle());
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFFFF")),0,toolbar.getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        toolbar.setTitle(s);

    }

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.home:
                        sp.edit().clear().commit();

                        Intent it = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(it);
                        finish();

                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText("Médico: Raphael Cardoso Fernandes");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,tbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void populateListaPacientes(Paciente paciente){
        int[] covers = new int[]{
                R.drawable.ic_person_black};
        HomeModel p = new HomeModel(paciente.getNome()+" "+paciente.getSobrenome(),paciente.getLeito(),paciente.getBox(),
                covers[0],paciente.getNomeMedico()+" "+paciente.getSobrenomeMedico(),paciente.getID(),paciente.getResponsavel());
        homeModelList.add(p);
    }

    private void requestPacienteList(){
        progress.setVisibility(View.VISIBLE);

        APIService apiService =
                RestClient.getClient(this).create(APIService.class);
        Call<List<Paciente>> call = apiService.getPacientes(getSharedPreferences(getString(R.string.sharedPrefecences),
                Context.MODE_PRIVATE).getString("acess_token",""));
        call.enqueue(new Callback<List<Paciente>>() {
            @Override
            public void onResponse(Call<List<Paciente>> call, Response<List<Paciente>> response) {
                progress.setVisibility(View.GONE);

                for(Paciente i : response.body()){
                    populateListaPacientes(i);
                }
                prepareListaPacientes();
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                Log.d("ERROR",t.getMessage());
                progress.setVisibility(View.GONE);
            }
        });
    }

    private void prepareListaPacientes() {
        Collections.sort(homeModelList, new Comparator<HomeModel>() {
            @Override
            public int compare(final HomeModel object1, final HomeModel object2) {
                return object1.getNomePaciente().compareTo(object2.getNomePaciente());
            }
        });

        homeAdapter = new HomeAdapter(homeModelList,this);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(onItemClickListener);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home,menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        homeAdapter.updateList(homeModelList);
                        return true; // Return true to collapse action view
                    }
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true; // Return true to expand action view
                    }
                });
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    public boolean onQueryTextChange(String query) {
        final List<HomeModel> filteredModelList = filter(homeModelList, query);

        homeAdapter.updateList(filteredModelList);
        return false;
    }

    private List<HomeModel> filter(List<HomeModel> models, String query) {
        query = query.toLowerCase();
        final List<HomeModel> filteredModelList = new ArrayList<>();
        for (HomeModel model : models) {
            final String text = model.getNomePaciente().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    HomeAdapter.OnItemClickListener onItemClickListener = new HomeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(final View view, final int position) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view, Gravity.NO_GRAVITY, R.attr.actionOverflowMenuStyle, 0);
            popupMenu.getMenuInflater().inflate(R.menu.menu_select_ficha, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    intent = new Intent(view.getContext(), FichaActivity.class);
                    intent.putExtra("idPaciente", homeModelList.get(position).getIdPaciente());
                    switch (item.getItemId()) {
                        case R.id.MnuOpc1:
                            intent.putExtra("tipoFicha", "Diurna");
                            view.getContext().startActivity(intent);
                            break;
                        case R.id.MnuOpc2:
                            intent.putExtra("tipoFicha", "Noturna");
                            view.getContext().startActivity(intent);
                            break;
                        default:
                            return false;
                    }
                    return false;
                }
            });

            popupMenu.show();

        }
    };

}

