package santauti.app.Controller.Home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import santauti.app.Model.HomeModel;
import santauti.app.R;

/**
 * Created by rapha on 27-Apr-17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private Context mContext;
    private List<HomeModel> homeModelList;

    public class HomeViewHolder extends RecyclerView.ViewHolder{
        public TextView nomePaciente;
        public TextView leito;
        public TextView box;
        public ImageView contactImage;

        public HomeViewHolder(View itemView) {
            super(itemView);
            nomePaciente = (TextView) itemView.findViewById(R.id.pacienteNome);
            leito = (TextView)itemView.findViewById(R.id.pacienteLeito);
            box = (TextView)itemView.findViewById(R.id.pacienteBox);
            contactImage = (ImageView)itemView.findViewById(R.id.pacienteImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Print","Cliquei");
                }
            });
        }
    }
    public HomeAdapter(Context mContext,List<HomeModel> homeModelList){
        this.mContext = mContext;
        this.homeModelList = homeModelList;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pacientes_view,parent,false);
        return new HomeViewHolder(itemView);
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
