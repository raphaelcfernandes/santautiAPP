package santauti.app.Adapters.Home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 27-Apr-17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<HomeModel> homeModelList;
    private Context mContext;
    private OnItemClickListener mItemClickListener;

    public HomeAdapter(List<HomeModel> homeModelList, Context context){
        this.homeModelList = homeModelList;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView nomePaciente;
        public final TextView leito;
        public final TextView box;
        public final TextView medicoResponsavel;
        public final ImageView contactThumbnail;

        public ViewHolder(final View itemView) {
            super(itemView);
            nomePaciente = (TextView) itemView.findViewById(R.id.pacienteNome);
            leito = (TextView)itemView.findViewById(R.id.pacienteLeito);
            box = (TextView)itemView.findViewById(R.id.pacienteBox);
            contactThumbnail = (ImageView)itemView.findViewById(R.id.pacienteImage);
            medicoResponsavel = (TextView)itemView.findViewById(R.id.medico_responsavel);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view,getLayoutPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final HomeAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void updateList(List<HomeModel> list){
        homeModelList = new ArrayList<>();
        homeModelList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pacientes_view,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeModel homeModelVO = homeModelList.get(position);
        holder.nomePaciente.setText(homeModelVO.getNomePaciente());
        holder.medicoResponsavel.setText(homeModelVO.getMedicoResponsavel());
        holder.box.setText(Integer.toString(homeModelVO.getBoxPaciente()));
        holder.leito.setText(Integer.toString(homeModelVO.getLeitoPaciente()));
        Glide.with(mContext).load(homeModelVO.getThumbnail()).into(holder.contactThumbnail);
    }

    @Override
    public int getItemCount() {
        return homeModelList.size();
    }
}
