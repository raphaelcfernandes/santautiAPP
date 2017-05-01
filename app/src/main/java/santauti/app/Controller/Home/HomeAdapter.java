package santauti.app.Controller.Home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import santauti.app.Model.HomeModel;
import santauti.app.R;
import santauti.app.View.Ficha;
import santauti.app.View.Home;

/**
 * Created by rapha on 27-Apr-17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private final List<HomeModel> homeModelList;
    private Context context;
    private Intent intent;
    public class HomeViewHolder extends RecyclerView.ViewHolder{
        public final TextView nomePaciente;
        public final TextView leito;
        public final TextView box;
        public final ImageView contactImage;

        public HomeViewHolder(View itemView) {
            super(itemView);
            nomePaciente = (TextView) itemView.findViewById(R.id.pacienteNome);
            leito = (TextView)itemView.findViewById(R.id.pacienteLeito);
            box = (TextView)itemView.findViewById(R.id.pacienteBox);
            contactImage = (ImageView)itemView.findViewById(R.id.pacienteImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    PopupMenu popupMenu = new PopupMenu(view.getContext(), view, Gravity.NO_GRAVITY, R.attr.actionOverflowMenuStyle, 0);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_select_ficha,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.MnuOpc1:
                                    intent = new Intent(view.getContext(),Ficha.class);
                                    intent.putExtra("tipoFicha", "Diurna");
                                    view.getContext().startActivity(intent);
                                case R.id.MnuOpc2:
                                    intent = new Intent(view.getContext(),Ficha.class);
                                    intent.putExtra("tipoFicha", "Noturna");

                                    view.getContext().startActivity(intent);
                                default:
                                    return false;
                            }
                        }
                    });

                    popupMenu.show();
                }
            });
        }
    }
    public HomeAdapter(List<HomeModel> homeModelList){
        this.homeModelList = homeModelList;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pacientes_view,parent,false);
        return new HomeViewHolder(itemView);
    }
    public void removeItem(int position) {
        homeModelList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, homeModelList.size());
    }
    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        HomeModel homeModelVO = homeModelList.get(position);
        holder.nomePaciente.setText(homeModelVO.getNomePaciente());
        holder.box.setText(Integer.toString(homeModelVO.getBoxPaciente()));
        holder.leito.setText(Integer.toString(homeModelVO.getLeitoPaciente()));
        holder.contactImage.setImageResource(homeModelVO.getThumbnail());

    }

    @Override
    public int getItemCount() {
        return homeModelList.size();
    }
}
