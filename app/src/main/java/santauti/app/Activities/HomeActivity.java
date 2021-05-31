package santauti.app.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import santauti.app.APIServices.FireBaseUtils;
import santauti.app.Activities.Ficha.FichaActivity;
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
    private Hospital hospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tbar = findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        updateUI();

        progress = findViewById(R.id.progressbar_recycler);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        homeModelList = new ArrayList<>();
        homeAdapter = new HomeAdapter(homeModelList,this);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(onItemClickListener);
//        requestPacienteList();
    }

    private void updateUI(){
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(false);
            SpannableString s = new SpannableString(toolbar.getTitle());
            s.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFFFF")),0,toolbar.getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            toolbar.setTitle(s);
        }
//        initNavigationDrawer();
        intent = new Intent(getBaseContext(), FichaActivity.class);
        sp = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("pacienteKey", "blabla");
        editor.putString("hospitalKey","Santa Clara");
        editor.apply();
        intent.putExtra("tipoFicha", "Diurna");
        startActivity(intent);
    }

    private void initNavigationDrawer() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            switch (id) {
                case R.id.home:
                    if(FirebaseAuth.getInstance().getCurrentUser() != null)
                        FirebaseAuth.getInstance().signOut();
                    Intent it = new Intent(HomeActivity.this, MainActivity.class);
                    drawerLayout.closeDrawers();
                    finish();
                    startActivity(it);
                    break;
            }
            return true;
        });
        View header = navigationView.getHeaderView(0);
        final TextView tv_email = header.findViewById(R.id.tv_email);
        FireBaseUtils.getDatabaseReference().child("Pessoa").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot documentSnapshot : dataSnapshot.getChildren()) {
                    if(documentSnapshot.child("email").getValue().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        tv_email.setText("Médico: "+documentSnapshot.child("nome").getValue()+" "+documentSnapshot.child("sobrenome").getValue());
                        SharedPreferences sp = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("userKey", documentSnapshot.getKey());
                        editor.apply();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        drawerLayout = findViewById(R.id.drawer_layout);

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

    private void requestPacienteList() {
        FireBaseUtils.getDatabaseReference().child("Hospital").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshotItem : dataSnapshot.getChildren()){
                    if(dataSnapshotItem.child("nome").getValue().equals("Santa Clara")){//Deve ser substituido para o nome do hospital escolhido pelo usuario E verificar se usuario trabalha lá
                        hospital = dataSnapshotItem.getValue(Hospital.class);
                        hospital.setHospitalDocumentKey(dataSnapshotItem.getKey());
                        break;
                    }
                }
                if(hospital != null) {
                    homeModelList.clear();
                    int[] covers = new int[]{
                            R.drawable.ic_person_black};
                    for (Map.Entry<String, Paciente> entry : hospital.getPacientes().entrySet()) {
                        String medicoResponsavel = entry.getValue().getProfissionalResponsavel();
                        for(Map.Entry<String,Profissional> entryProfissional : hospital.getProfissionais().entrySet()){
                            if(entryProfissional.getKey().equals(medicoResponsavel)){
                                HomeModel p = new HomeModel(entry.getValue().getNome()+" "+entry.getValue().getSobrenome(),entry.getValue().getBox(),entry.getValue().getLeito(),
                                        covers[0],entryProfissional.getValue().getNome()+ " "+entryProfissional.getValue().getSobrenome(),entry.getKey());
                                homeModelList.add(p);
                                homeAdapter.notifyDataSetChanged();
                                prepareListaPacientes();
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sp = getSharedPreferences(getString(R.string.sharedPrefecences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
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
                    editor.putString("pacienteKey",homeModelList.get(position).getPacienteKey());
                    editor.putString("hospitalKey",hospital.getHospitalDocumentKey());
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

