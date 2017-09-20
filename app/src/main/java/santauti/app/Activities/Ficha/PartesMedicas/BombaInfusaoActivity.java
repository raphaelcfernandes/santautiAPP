package santauti.app.Activities.Ficha.PartesMedicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import santauti.app.Activities.Ficha.GenericoActivity;
import santauti.app.Adapters.Ficha.BombaInfusao.BombaInfusaoAdapter;
import santauti.app.Adapters.Ficha.BombaInfusao.BombaInfusaoAdapterModel;
import santauti.app.R;

/**
 * Created by raphael on 6/23/17.
 */

public class BombaInfusaoActivity extends GenericoActivity implements View.OnTouchListener {
    private RecyclerView recyclerView;
    private BombaInfusaoAdapter bombaInfusaoAdapter;
    private List<BombaInfusaoAdapterModel> hemodinamicoModelList = new ArrayList<>();
    private TextInputEditText velInfusao;
    private View inserirLayout;
    private TextView drogaVasoativaTextView,menuDrogaVasoativa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomba_infusao);
        findViewById(R.id.activity_bomba_infusao).requestFocus();
        findViewById(R.id.activity_bomba_infusao).setOnTouchListener(this);

        setToolbar(getString(R.string.BombaInfusao));
        prepareNavigationButtons();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        bombaInfusaoAdapter = new BombaInfusaoAdapter(this,hemodinamicoModelList);
        recyclerView.setAdapter(bombaInfusaoAdapter);
        velInfusao = (TextInputEditText)findViewById(R.id.velInfusao);
        bombaInfusaoAdapter.setOnItemClickListener(onItemClickListener);
        inserirLayout = findViewById(R.id.inserirLayout);
        drogaVasoativaTextView = (TextView)findViewById(R.id.drogaVasoativaTextView);
        menuDrogaVasoativa = (TextView)findViewById(R.id.menuDrogaVasoativa);

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

    public void drogaOnClick(View view){
        PopupMenu popupMenu = new PopupMenu(view.getContext(), menuDrogaVasoativa, Gravity.START, R.attr.actionOverflowMenuStyle, 0);
        popupMenu.getMenuInflater().inflate(R.menu.menu_droga, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                drogaVasoativaTextView.setText(item.getTitle());
                if(!inserirLayout.isShown())
                    inserirLayout.setVisibility(View.VISIBLE);
                return false;
            }
        });

        popupMenu.show();
    }

    private void addDataFromDialogIntoAdapter(String droga,int velInfusao){
        if(!droga.equals(defaultSpinnerString)){
            BombaInfusaoAdapterModel h = new BombaInfusaoAdapterModel(droga,velInfusao);
            hemodinamicoModelList.add(h);
            bombaInfusaoAdapter.notifyItemInserted(bombaInfusaoAdapter.getItemCount()-1);
            bombaInfusaoAdapter.notifyDataSetChanged();
        }
    }

    public void addDrogaVasoativa(View view) {
        if(velInfusao.getText().length()>0) {
            addDataFromDialogIntoAdapter(drogaVasoativaTextView.getText().toString(), Integer.parseInt(velInfusao.getText().toString()));
            velInfusao.setText("");
            velInfusao.clearFocus();
            hideSoftKeyboard(BombaInfusaoActivity.this);
            inserirLayout.setVisibility(View.GONE);
            drogaVasoativaTextView.setText("");
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        hideSoftKeyboard(BombaInfusaoActivity.this);
        if(velInfusao.isFocused())
            velInfusao.clearFocus();
        return true;
    }
}
