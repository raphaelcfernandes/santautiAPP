package santauti.app.View.Ficha;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import santauti.app.Controller.Ficha.FichaSectionAdapter;
import santauti.app.R;

public class Ficha extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FichaSectionAdapter adapter;
    private List<santauti.app.Model.Ficha.Ficha> fichaList; //albumList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        fichaList = new ArrayList<>();
        adapter = new FichaSectionAdapter(this, fichaList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);//O int represnta quantos cards terão por grid
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.setOnItemClickListener(onItemClickListener);
        prepareFichas();
    }
    FichaSectionAdapter.OnItemClickListener onItemClickListener = new FichaSectionAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Toast.makeText(getApplicationContext(),"Voce clicou na ficha de posição: "+position, Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * Adding few albums for testing
     */
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

        santauti.app.Model.Ficha.Ficha a = new santauti.app.Model.Ficha.Ficha("Neurologico",covers[0]);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Hemodinamico",covers[1]);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Respiratorio", covers[2]);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Gastrointestinal", covers[3]);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Renal", covers[4]);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Hematologico", covers[5]);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Endocrino", covers[6]);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Infeccioso", covers[7]);
        fichaList.add(a);

        a = new santauti.app.Model.Ficha.Ficha("Metabolico", covers[8]);
        fichaList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
