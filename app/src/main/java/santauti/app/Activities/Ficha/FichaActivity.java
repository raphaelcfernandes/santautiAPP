package santauti.app.Activities.Ficha;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import santauti.app.Activities.Ficha.PartesMedicas.EndocrinoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.GastrointestinalActivity;
import santauti.app.Activities.Ficha.PartesMedicas.HematologicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.HemodinamicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.InfecciosoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.MetabolicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.NeurologicoActivity;
import santauti.app.Activities.Ficha.PartesMedicas.RenalActivity;
import santauti.app.Activities.Ficha.PartesMedicas.RespiratorioActivity;
import santauti.app.Adapters.Ficha.FichaSectionAdapter;
import santauti.app.Model.Ficha.Metabolico;
import santauti.app.Model.User;
import santauti.app.R;

public class FichaActivity extends Generico {
    private RecyclerView recyclerView;
    public static FichaSectionAdapter adapter;
    public static List<santauti.app.Model.Ficha.Ficha> fichaList;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);

        setToolbar(this.getString(R.string.Evolucao));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        fichaList = new ArrayList<>();
        prepareFichas();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);//O int represnta quantos cards terão por grid
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adapter.setOnItemClickListener(onItemClickListener);
    }

    FichaSectionAdapter.OnItemClickListener onItemClickListener = new FichaSectionAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            if(position==0)
                intent = new Intent(v.getContext(), NeurologicoActivity.class);
            else if(position==1)
                intent = new Intent(v.getContext(), HemodinamicoActivity.class);
            else if(position==2)
                intent = new Intent(v.getContext(), RespiratorioActivity.class);
            else if(position==3)
                intent = new Intent(v.getContext(), GastrointestinalActivity.class);
            else if(position==4)
                intent = new Intent(v.getContext(), RenalActivity.class);
            else if(position==5)
                intent = new Intent(v.getContext(), HematologicoActivity.class);
            else if(position==6)
                intent = new Intent(v.getContext(), EndocrinoActivity.class);
            else if(position==7)
                intent = new Intent(v.getContext(), InfecciosoActivity.class);
            else if(position==8)
                intent = new Intent(v.getContext(), MetabolicoActivity.class);
            intent.putExtra("Position",position);
            startActivityForResult(intent,position);
        }
    };

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

        adapter = new FichaSectionAdapter(this, fichaList);
        recyclerView.setAdapter(adapter);

    }
}
