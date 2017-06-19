package santauti.app.Adapters.Ficha.Hemodinamico;

import android.content.Context;
import android.media.Image;
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
 * Created by Raphael Fernandes on 14-Jun-17.
 */

public class HemodinamicoAdapter extends RecyclerView.Adapter<HemodinamicoAdapter.ViewHolder>{
    private Context context;
    private List<HemodinamicoAdapterModel> hemodinamicoAdapterModelList;
    private int lastPosition=-1;
    private MyAnimation myAnimation = new MyAnimation();
    private OnItemClickListener mItemClickListener;
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView droga;
        public final TextView dose;
        public final ImageView deleteIcon;
        public final ImageView editIcon;
        public ViewHolder(View itemView) {
            super(itemView);
            droga = (TextView) itemView.findViewById(R.id.droga);
            dose = (TextView)itemView.findViewById(R.id.doseDroga);
            deleteIcon = (ImageView)itemView.findViewById(R.id.deleteIcon);
            editIcon = (ImageView)itemView.findViewById(R.id.editIcon);
            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(view,getAdapterPosition());
                }
            });
            editIcon.setOnClickListener(this);
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
    public void setOnItemClickListener(final HemodinamicoAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    public HemodinamicoAdapter(Context context, List<HemodinamicoAdapterModel> hemodinamicoAdapterModelList){
        this.context=context;
        this.hemodinamicoAdapterModelList = hemodinamicoAdapterModelList;
    }


    @Override
    public HemodinamicoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hemodinamico_drogavasoativa_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HemodinamicoAdapter.ViewHolder holder, final int position) {
        HemodinamicoAdapterModel hemodinamicoModel = hemodinamicoAdapterModelList.get(position);
        holder.dose.setText(String.valueOf(hemodinamicoModel.getDose()));
        holder.droga.setText(hemodinamicoModel.getDroga());
        setAnimationNewItem(holder.itemView, position);
    }

    private void deleteItem(View view,int position){
        hemodinamicoAdapterModelList.remove(position);
        setAnimationExcludeItem(view);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,hemodinamicoAdapterModelList.size());
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
        return hemodinamicoAdapterModelList.size();
    }
}
