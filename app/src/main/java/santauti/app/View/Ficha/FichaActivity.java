package santauti.app.View.Ficha;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import santauti.app.Controller.Ficha.FichaSectionAdapter;
import santauti.app.R;
import santauti.app.View.Ficha.PartesMedicas.EndocrinoActivity;
import santauti.app.View.Ficha.PartesMedicas.GastrointestinalActivity;
import santauti.app.View.Ficha.PartesMedicas.HematologicoActivity;
import santauti.app.View.Ficha.PartesMedicas.HemodinamicoActivity;
import santauti.app.View.Ficha.PartesMedicas.InfecciosoActivity;
import santauti.app.View.Ficha.PartesMedicas.MetabolicoActivity;
import santauti.app.View.Ficha.PartesMedicas.NeurologicoActivity;
import santauti.app.View.Ficha.PartesMedicas.RenalActivity;
import santauti.app.View.Ficha.PartesMedicas.RespiratorioActivity;

public class FichaActivity extends AppCompatActivity {
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
        prepareFichas();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);//O int represnta quantos cards terão por grid
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adapter.setOnItemClickListener(onItemClickListener);

        ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);

        SpannableString s = new SpannableString(toolbar.getTitle());
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFFFF")),0,toolbar.getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        toolbar.setTitle(s);

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

        adapter = new FichaSectionAdapter(this, fichaList);
        recyclerView.setAdapter(adapter);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home)
            finish();
        return true;
    }
}
