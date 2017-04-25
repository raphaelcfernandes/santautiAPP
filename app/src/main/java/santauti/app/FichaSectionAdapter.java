package santauti.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by rapha on 25-Apr-17.
 */

public class FichaSectionAdapter extends RecyclerView.Adapter<FichaSectionAdapter.MyViewHolder>{
    private Context mContext;
    private List<FichaSection> fichaSectionList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public FichaSectionAdapter(Context mContext, List<FichaSection> fichaSectionList) {
        this.mContext = mContext;
        this.fichaSectionList = fichaSectionList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ficha_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        FichaSection fichaSection = fichaSectionList.get(position);
        holder.title.setText(fichaSection.getName());

        // loading album cover using Glide library
        Glide.with(mContext).load(fichaSection.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return fichaSectionList.size();
    }
}
