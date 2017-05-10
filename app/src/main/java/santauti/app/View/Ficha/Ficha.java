package santauti.app.View.Ficha;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import santauti.app.Controller.Ficha.FichaSectionAdapter;
import santauti.app.R;
import santauti.app.View.Ficha.PartesMedicas.PartesMedicas;

public class Ficha extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FichaSectionAdapter adapter;
    private List<santauti.app.Model.Ficha.Ficha> fichaList; //albumList
    private Context context;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        fichaList = new ArrayList<>();
        adapter = new FichaSectionAdapter(this, fichaList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);//O int represnta quantos cards terão por grid
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setOnItemClickListener(onItemClickListener);
        prepareFichas();
    }
    FichaSectionAdapter.OnItemClickListener onItemClickListener = new FichaSectionAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            intent = new Intent(v.getContext(),PartesMedicas.class);
            intent.putExtra("Position",position);
            startActivityForResult(intent,position);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            fichaList.get(requestCode).setColor(1);
            adapter.notifyDataSetChanged();
        }
    }

    private void prepareFichas() {
        int[] covers = new int[]{
                R.drawable.brain,
                R.drawable.cardiogram,
                R.drawable.lungs,
                R.drawable.intestine,
                R.drawable.kidneys,
                R.drawable.blood_drop,
                R.drawable.thyroid,
                R.drawable.cell,
                R.drawable.exercise};

        santauti.app.Model.Ficha.Ficha a = new santauti.app.Model.Ficha.Ficha("Neurologico",covers[0],0);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Hemodinamico",covers[1],0);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Respiratorio", covers[2],0);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Gastrointestinal", covers[3],0);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Renal", covers[4],0);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Hematologico", covers[5],0);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Endocrino", covers[6],0);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Infeccioso", covers[7],0);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Metabolico", covers[8],0);
        fichaList.add(a);

        adapter.notifyDataSetChanged();
    }
}
