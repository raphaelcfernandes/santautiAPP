package santauti.app.Adapters.Ficha.Neurologico;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import santauti.app.Animation.MyAnimation;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 16-Jun-17.
 */


public class NeurologicoAdapter extends RecyclerView.Adapter<NeurologicoAdapter.ViewHolder> {
    private Context context;
    private List<NeurologicoAdapterModel> neurologicoAdapterModelList;
    private int lastPosition=-1;
    private MyAnimation myAnimation = new MyAnimation();
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tipoSedativo;
        public final TextView doseSedativo;
        public final ImageView deleteIcon;
        public final ImageView editIcon;
        public ViewHolder(View itemView) {
            super(itemView);
            tipoSedativo = (TextView) itemView.findViewById(R.id.tipoSedativo_neurologico_recycler_view);
            doseSedativo = (TextView)itemView.findViewById(R.id.doseSedativo_neurologico_recycler_view);
            deleteIcon = (ImageView)itemView.findViewById(R.id.deleteIcon_neurologico_recycler_view);
            editIcon = (ImageView)itemView.findViewById(R.id.editIcon_neurologico__recycler_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.neurologico_sedativo_view,parent,false);
        return new NeurologicoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NeurologicoAdapterModel neurologicoAdapterModel = neurologicoAdapterModelList.get(position);
        holder.tipoSedativo.setText(neurologicoAdapterModel.getTipoSedativo());
        holder.doseSedativo.setText(String.valueOf(neurologicoAdapterModel.getDoseSedativo()));
        setAnimationNewItem(holder.itemView, position);
    }

    private void setAnimationNewItem(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            myAnimation.slideInLeft(context,viewToAnimate);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return neurologicoAdapterModelList.size();
    }

    public NeurologicoAdapter(Context context, List<NeurologicoAdapterModel> neurologicoAdapterModelList){
        this.context=context;
        this.neurologicoAdapterModelList = neurologicoAdapterModelList;
    }
}
