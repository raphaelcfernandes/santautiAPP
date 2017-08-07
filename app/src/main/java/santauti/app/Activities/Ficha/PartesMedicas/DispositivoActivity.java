package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import santauti.app.Activities.Ficha.FichaActivity;
import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Adapters.Ficha.BombaInfusao.BombaInfusaoAdapter;
import santauti.app.Adapters.Ficha.BombaInfusao.BombaInfusaoAdapterModel;
import santauti.app.Adapters.Ficha.Dispositivos.DispositivoAdapter;
import santauti.app.Adapters.Ficha.Dispositivos.DispositivoAdapterModel;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 21-Jun-17.
 */

public class DispositivoActivity extends GenericoActivity {
    private RecyclerView recyclerView;
    private DispositivoAdapter dispositivoAdapter;
    private List<DispositivoAdapterModel> dispositivoAdapterModelList = new ArrayList<>();
    private Spinner dispositivoSpinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivo);
        setToolbar("Dispositivos");
        prepareNavigationButtons();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        dispositivoAdapter = new DispositivoAdapter(this, dispositivoAdapterModelList);
        recyclerView.setAdapter(dispositivoAdapter);


        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), BombaInfusaoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0) - 1, intent);
                startActivity(intent);
                exitActivityToLeft();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), RespiradorActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0) + 1, intent);
                startActivity(intent);
                exitActivityToRight();
                //verificaCamposENotificaAdapter();
                finish();
            }
        });
    }
    public void addDispositivo(View view){
        final String[] drogasVasoativa = new String[]{"Cateter Venoso Central", "Cateter de Hemodialise", "Cateter de PAI",
                "Dreno de Torax", "Dreno Abdominal", "Sonda Vesical", "Tubo Orotraquial","Traqueostomia",
                "Gastrostomia","Venoclise","Sonda Enteral","Sonda Oroenteral",
                "Sonda Nasoenteral","Sonda Orogastrica","Sonda Nasogastrica","Cateter de PIC","Balão Intra-aórtico"};

        ordenaStringSpinner(drogasVasoativa);

        final AlertDialog.Builder builder = new AlertDialog.Builder(DispositivoActivity.this);
        builder.setTitle("Adicionar Dispositivo");

        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.dispositivo_dialog,null);
        dialogView.requestFocus();
        dispositivoSpinner = (Spinner)dialogView.findViewById(R.id.dispositivoSpinner);
        ArrayAdapter<String> adapterDispositivo = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, drogasVasoativa);
        dispositivoSpinner.setAdapter(adapterDispositivo);

        builder.setView(dialogView);
        builder.setPositiveButton(getString(R.string.Selecionar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addDataFromDialogIntoAdapter(dispositivoSpinner.getSelectedItem().toString());
            }
        });

        builder.setNegativeButton(getString(R.string.Cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        final AlertDialog dialog = builder.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

    }

    private void addDataFromDialogIntoAdapter(String dispositivo){
        DispositivoAdapterModel h = new DispositivoAdapterModel(dispositivo);
        dispositivoAdapterModelList.add(h);
        dispositivoAdapter.notifyItemInserted(dispositivoAdapter.getItemCount()-1);
        dispositivoAdapter.notifyDataSetChanged();
    }
}