package santauti.app.Adapters.Ficha.Dispositivos;

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
 * Created by Raphael Fernandes on 10-Jul-17.
 */

public class DispositivoAdapter extends RecyclerView.Adapter<DispositivoAdapter.ViewHolder>{
    private Context context;
    private List<DispositivoAdapterModel> dispositivoAdapterModelList;
    private int lastPosition=-1;
    private MyAnimation myAnimation = new MyAnimation();
    private DispositivoAdapter.OnItemClickListener mItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView dispositivo;
        public final ImageView deleteIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            dispositivo = (TextView) itemView.findViewById(R.id.dispositivo);
            deleteIcon = (ImageView)itemView.findViewById(R.id.deleteIconDispositivo);

            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(view,getAdapterPosition());
                }
            });
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
    public void setOnItemClickListener(final DispositivoAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    public DispositivoAdapter(Context context, List<DispositivoAdapterModel> dispositivoAdapterModelList){
        this.context=context;
        this.dispositivoAdapterModelList = dispositivoAdapterModelList;
    }


    @Override
    public DispositivoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dispositivo_view,parent,false);
        return new DispositivoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DispositivoAdapter.ViewHolder holder, final int position) {
        DispositivoAdapterModel dispositivoAdapterModel = dispositivoAdapterModelList.get(position);
        holder.dispositivo.setText(dispositivoAdapterModel.getDispositivo());
        setAnimationNewItem(holder.itemView, position);
    }

    private void deleteItem(View view,int position){
        dispositivoAdapterModelList.remove(position);
        setAnimationExcludeItem(view);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,dispositivoAdapterModelList.size());
    }

    private void setAnimationExcludeItem(View view){
        myAnimation.slideOutRight(context,view);
        lastPosition = getItemCount()-1;
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
        return dispositivoAdapterModelList.size();
    }
}
