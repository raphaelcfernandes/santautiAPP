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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.MainActivity;
import santauti.app.Adapters.Home.HomeAdapter;
import santauti.app.Adapters.Home.HomeModel;
import santauti.app.Model.Hospital;
import santauti.app.Model.Paciente;
import santauti.app.Model.Profissional;
import santauti.app.R;

public class HomeActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private List<HomeModel> homeModelList;
    private Toolbar tbar;
    private DrawerLayout drawerLayout;
    private Intent intent;
    SharedPreferences sp;
    ProgressBar progress;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;
    private Hospital hospital;
    private ListenerRegistration registration;
    private Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        updateUI();

        progress = (ProgressBar) findViewById(R.id.progressbar_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        homeModelList = new ArrayList<>();
        homeAdapter = new HomeAdapter(homeModelList,this);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(onItemClickListener);
        //prepareListaPacientes();
        requestPacienteList();
//
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        Pessoa pessoa = new Pessoa("02483575145","09-11-1994","1","MG17817760","Raphael","Fernandes");
//        mDatabase.child("Pessoa").push().setValue(pessoa);
//        pessoa = new Pessoa("12345678910","02-10-1984","1","MG17817750","Fulano","Da Silva Sauro");
//        mDatabase.child("Pessoa").push().setValue(pessoa);
//        pessoa = new Pessoa("02483275145","01-01-1932","1","MG17814260","Bartolomeu","Rocha");
//        mDatabase.child("Pessoa").push().setValue(pessoa);
    }

    private void updateUI(){
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(false);
            SpannableString s = new SpannableString(toolbar.getTitle());
            s.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFFFF")),0,toolbar.getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            toolbar.setTitle(s);
        }
        firebaseUser = firebaseAuth.getCurrentUser();
        initNavigationDrawer();
    }

    private void initNavigationDrawer() {
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.home:
                        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
                            FirebaseAuth.getInstance().signOut();
                        Intent it = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(it);
                        if(registration!=null)
                            registration.remove();
                        finish();
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        final TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Pessoa").whereEqualTo("email",FirebaseAuth.getInstance().getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult()){
                        tv_email.setText("Médico: "+documentSnapshot.get("nome")+" "+documentSnapshot.get("sobrenome"));
                    }
                }
            }
        });
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

    private void requestPacienteList(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        progress.setVisibility(View.VISIBLE);
        query = db.collection("Hospital");
        registration = query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                for(DocumentSnapshot documentSnapshot : documentSnapshots){
                    if(documentSnapshot.get("nome").equals("Santa Clara")){
                        hospital = documentSnapshot.toObject(Hospital.class);
                        hospital.setHospitalDocumentKey(documentSnapshot.getId());
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("Hospital").document(hospital.getHospitalDocumentKey()).collection("Pacientes").addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                                homeModelList.clear();
                                for(DocumentSnapshot documentSnapshot : documentSnapshots){
                                    final Paciente paciente = documentSnapshot.toObject(Paciente.class);
                                    paciente.setPacienteKey(documentSnapshot.getId());
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    db.collection("Pessoa").document(paciente.getProfissionalResponsavel()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                        @Override
                                        public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                                            Profissional profissional = documentSnapshot.toObject(Profissional.class);
                                            int[] covers = new int[]{
                                                    R.drawable.ic_person_black};
                                            HomeModel p = new HomeModel(paciente.getNome()+" "+paciente.getSobrenome(),paciente.getBox(),paciente.getLeito(),
                                                    covers[0],profissional.getNome()+ " "+profissional.getSobrenome(),paciente.getPacienteKey());
                                            homeModelList.add(p);
                                            homeAdapter.notifyDataSetChanged();
                                            prepareListaPacientes();
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void prepareListaPacientes() {
        progress.setVisibility(View.GONE);
        Collections.sort(homeModelList, new Comparator<HomeModel>() {
            @Override
            public int compare(final HomeModel object1, final HomeModel object2) {
                return object1.getNomePaciente().compareTo(object2.getNomePaciente());
            }
        });
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
                    sp = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    //editor.putString("medicoKey",homeModelList.get(position).getMedicoKey());
                    editor.putString("pacienteKey",homeModelList.get(position).getPacienteKey());
                    editor.apply();
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

