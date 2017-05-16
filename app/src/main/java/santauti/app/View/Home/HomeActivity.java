package santauti.app.View.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import santauti.app.Controller.Home.HomeAdapter;
import santauti.app.Model.Home.HomeModel;
import santauti.app.R;

public class HomeActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private List<HomeModel> homeModelList; //albumList
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
        return true;
    }
}

