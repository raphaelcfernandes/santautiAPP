package santauti.app.Adapters.Ficha;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import santauti.app.R;

/**
 * Created by Raphael Fernandes on 25-Apr-17.
 */

public class FichaSectionAdapter extends RecyclerView.Adapter<FichaSectionAdapter.ViewHolder>{
    private Context mContext;
    private List<FichaAdapterModel> fichaAdapterModelList;
    private OnItemClickListener mItemClickListener;

    public FichaSectionAdapter(Context context, List<FichaAdapterModel> fichaAdapterModelList){
        this.mContext = context;
        this.fichaAdapterModelList = fichaAdapterModelList;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView thumbnail;
        CardView cardView;
        ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            cardView = (CardView)view.findViewById(R.id.card_view);
            view.setOnClickListener(this);

            title.setOnClickListener(this);
            thumbnail.setOnClickListener(this);
            cardView.setOnClickListener(this);

            setIsRecyclable(false);
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
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public FichaSectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ficha_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FichaAdapterModel fichaAdapterModel = fichaAdapterModelList.get(position);
        holder.title.setText(fichaAdapterModel.getName());
        Glide.with(mContext).load(fichaAdapterModel.getThumbnail()).into(holder.thumbnail);
        if(fichaAdapterModelList.get(position).getColor()==1)
            holder.cardView.setBackgroundResource(R.drawable.cardview_change_border_color);
    }

    @Override
    public int getItemCount() {
        return fichaAdapterModelList.size();
    }
}
