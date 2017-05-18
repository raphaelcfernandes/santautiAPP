package santauti.app.View.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import santauti.app.Controller.Home.HomeAdapter;
import santauti.app.Model.Home.HomeModel;
import santauti.app.R;

public class HomeActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private List<HomeModel> homeModelList;
    private Toolbar tbar;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        initNavigationDrawer();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        homeModelList = new ArrayList<>();
        prepareListaPacientes();

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
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
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
    private void prepareListaPacientes() {
        int[] covers = new int[]{
                R.drawable.ic_person_black};

        HomeModel a = new HomeModel("João Roberto Silva",1,1,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Raphael Cardoso Fernandes",2,2,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Antonio Silva Sauro",3,3,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Barbara Costa Preta",4,4,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Julio Cesar de Roma",5,5,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("César Augusto",6,6,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Napoleao Bonaparte",7,7,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Adolf Hitler",8,8,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Obama",9,9,covers[0]);
        homeModelList.add(a);
        a = new HomeModel("Lula da Silva",10,10,covers[0]);
        homeModelList.add(a);
        a = new HomeModel("Dilma DuCheff",11,11,covers[0]);
        homeModelList.add(a);
        a = new HomeModel("Lucas Borges",12,12,covers[0]);
        homeModelList.add(a);
        a = new HomeModel("Laura Borges",13,13,covers[0]);
        homeModelList.add(a);
        a = new HomeModel("Zenaide Gomes",14,14,covers[0]);

        homeModelList.add(a);

        Collections.sort(homeModelList, new Comparator<HomeModel>() {
            @Override
            public int compare(final HomeModel object1, final HomeModel object2) {
                return object1.getNomePaciente().compareTo(object2.getNomePaciente());
            }
        });

        homeAdapter = new HomeAdapter(homeModelList,this);
        recyclerView.setAdapter(homeAdapter);
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
}

