package santauti.app.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import santauti.app.Controller.Home.HomeAdapter;

import santauti.app.Model.HomeModel;
import santauti.app.R;

public class Home extends AppCompatActivity {

    String pacient_choose;

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private List<HomeModel> homeModelList; //albumList
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeModelList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        homeAdapter = new HomeAdapter(homeModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(homeAdapter);
        prepareListaPacientes();
    }

    private void prepareListaPacientes() {
        int[] covers = new int[]{
                R.drawable.ic_person_black};

        HomeModel a = new HomeModel("Paciente 1",1,1,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 2",2,2,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 2",3,3,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 3",4,4,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 4",5,5,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 5",6,6,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 6",7,7,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 7",8,8,covers[0]);
        homeModelList.add(a);

        a = new HomeModel("Paciente 8",9,9,covers[0]);
        homeModelList.add(a);

        homeAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_select_ficha, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                Context context = getApplicationContext();
                Intent intent = new Intent(Home.this,Ficha.class);
                intent.putExtra("tipoFicha", "Diurna");
                intent.putExtra("pacienteID", pacient_choose);
                Home.this.startActivity(intent);
                return true;
            case R.id.MnuOpc2:
                Log.v("S1","Menu2 Select");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}

