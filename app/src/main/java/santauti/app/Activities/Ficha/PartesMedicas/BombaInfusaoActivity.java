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
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Adapters.Ficha.BombaInfusao.BombaInfusaoAdapter;
import santauti.app.Adapters.Ficha.BombaInfusao.BombaInfusaoAdapterModel;
import santauti.app.R;

/**
 * Created by raphael on 6/23/17.
 */

public class BombaInfusaoActivity extends GenericoActivity {
    private RecyclerView recyclerView;
    private BombaInfusaoAdapter bombaInfusaoAdapter;
    private List<BombaInfusaoAdapterModel> hemodinamicoModelList = new ArrayList<>();
    private Spinner drogasVasoativasSpinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomba_infusao);
        setToolbar(getString(R.string.BombaInfusao));
        prepareNavigationButtons();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        bombaInfusaoAdapter = new BombaInfusaoAdapter(this,hemodinamicoModelList);
        recyclerView.setAdapter(bombaInfusaoAdapter);

        bombaInfusaoAdapter.setOnItemClickListener(onItemClickListener);

        antFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), MonitorMultiparametricoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)-1, intent);
                startActivity(intent);
                exitActivityToLeft();
                finish();
            }
        });

        proxFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), DispositivoActivity.class);
                prepareIntent(getIntent().getIntExtra("Position", 0)+1, intent);
                startActivity(intent);
                exitActivityToRight();
                finish();
            }
        });

    }

    BombaInfusaoAdapter.OnItemClickListener onItemClickListener = new BombaInfusaoAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View view, int position) {//Editar Droga Vasoativa
            System.out.println(hemodinamicoModelList.get(position).getDroga());
        }
    };

    private void addDataFromDialogIntoAdapter(String droga,int velInfusao){
        if(!droga.equals(defaultSpinnerString)){
            BombaInfusaoAdapterModel h = new BombaInfusaoAdapterModel(droga,velInfusao);
            hemodinamicoModelList.add(h);
            bombaInfusaoAdapter.notifyItemInserted(bombaInfusaoAdapter.getItemCount()-1);
            bombaInfusaoAdapter.notifyDataSetChanged();
        }
    }

    public void addDrogaVasoativa(View view) {

        final String[] drogasVasoativa = new String[]{"Dobutamina", "Dopamina", "Nitroprussiato de Sodio",
                "Nitroglicerina", "Milrinona", "Noradrenalina", "Adrenalina","Fentanil","Propofol","Ketamina","Midazolam","Precedex",
                "Amiodarona","Insulina","Hidrocortisona","Polimixina"};

        ordenaStringSpinner(drogasVasoativa);

        final AlertDialog.Builder builder = new AlertDialog.Builder(BombaInfusaoActivity.this);

        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.bombainfusao_dialog,null);
        dialogView.requestFocus();
        final TextInputEditText velInfusao = (TextInputEditText)dialogView.findViewById(R.id.velInfusao);
        drogasVasoativasSpinner = (Spinner)dialogView.findViewById(R.id.drogaVasoativa);
        ArrayAdapter<String> adapterDrogaVasoativas = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, drogasVasoativa);
        drogasVasoativasSpinner.setAdapter(adapterDrogaVasoativas);

        builder.setView(dialogView);
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!isTextInputEditTextEmpty(velInfusao))
                    addDataFromDialogIntoAdapter(drogasVasoativasSpinner.getSelectedItem().toString(),Integer.parseInt(velInfusao.getText().toString()));
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        final AlertDialog dialog = builder.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

    }
}
