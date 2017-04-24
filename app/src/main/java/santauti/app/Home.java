package santauti.app;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;

public class Home extends AppCompatActivity {

    ListView listView ;
    String pacient_choose;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = (ListView)findViewById(R.id.list_item);
        // Get ListView object from xml

        // Defined Array values to show in ListView
        String[] values = new String[30];
        for(int i=0;i<30;i++){
            values[i]="Paciente "+i;
        }


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_2, android.R.id.text1, values);

        this.registerForContextMenu(listView);


        //Assign adapter to ListView
        listView.setAdapter(adapter);

//        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                pacient_choose = (String) parent.getItemAtPosition(position);
                listView.showContextMenuForChild(view);
/*
                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(Home.this,Ficha.class);
                Home.this.startActivity(intent);
*/
            }

        });

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

