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
import java.util.List;

import santauti.app.Controller.Home.HomeAdapter;
import santauti.app.Model.Home.HomeModel;
import santauti.app.R;

public class Home extends AppCompatActivity {

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

        HomeModel a = new HomeModel("Paciente 1",1,1,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 2",2,2,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 3",3,3,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 4",4,4,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 5",5,5,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 6",6,6,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 7",7,7,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 8",8,8,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 9",9,9,covers[0]);
        homeModelList.add(a);
        a = new HomeModel("Paciente 10",10,10,covers[0]);
        homeModelList.add(a);
        a = new HomeModel("Paciente 11",11,11,covers[0]);
        homeModelList.add(a);
        a = new HomeModel("Paciente 12",12,12,covers[0]);
        homeModelList.add(a);
        a = new HomeModel("Paciente 13",13,13,covers[0]);
        homeModelList.add(a);
        a = new HomeModel("Paciente 14",14,14,covers[0]);

        homeModelList.add(a);

        homeAdapter = new HomeAdapter(homeModelList,this);
        recyclerView.setAdapter(homeAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }
}

