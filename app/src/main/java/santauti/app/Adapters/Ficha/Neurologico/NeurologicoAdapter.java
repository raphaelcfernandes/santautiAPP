package santauti.app.Adapters.Ficha.Neurologico;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tipoSedativo;
        final TextView doseSedativo;
        final ImageView deleteIcon;
        final ImageView editIcon;
        ViewHolder(View itemView) {
            super(itemView);
            tipoSedativo = (TextView) itemView.findViewById(R.id.tipoSedativo_neurologico_recycler_view);
            doseSedativo = (TextView)itemView.findViewById(R.id.doseSedativo_neurologico_recycler_view);
            deleteIcon = (ImageView)itemView.findViewById(R.id.deleteIcon_neurologico_recycler_view);
            editIcon = (ImageView)itemView.findViewById(R.id.editIcon_neurologico_recycler_view);
            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(view,getAdapterPosition());
                }
            });
            editIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editItem(v,getAdapterPosition());
                }
            });
        }
    }

    private void editItem(View view, final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Editar Sedativo");

        LayoutInflater li = LayoutInflater.from(view.getContext());
        View dialogView = li.inflate(R.layout.neurologico_dialog_sedativo,null);
        dialogView.requestFocus();
        final TextInputEditText doseSedativo = (TextInputEditText) dialogView.findViewById(R.id.doseSedativo);
        final TextInputEditText tipoSedativo = (TextInputEditText) dialogView.findViewById(R.id.tipoSedativo);

        doseSedativo.setText(Integer.toString(neurologicoAdapterModelList.get(position).getDoseSedativo()));
        tipoSedativo.setText(neurologicoAdapterModelList.get(position).getTipoSedativo());
        builder.setView(dialogView);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(tipoSedativo.length()>0 && doseSedativo.length()>0)
                    addDataFromDialogIntoAdapter(tipoSedativo.getText().toString(),Integer.parseInt(doseSedativo.getText().toString()),position);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        final AlertDialog dialog = builder.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(view.getContext(),R.color.colorPrimaryDark));
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(view.getContext(),R.color.colorPrimaryDark));
    }

    private void addDataFromDialogIntoAdapter(String droga,int dose,int position){
        neurologicoAdapterModelList.get(position).setDoseSedativo(dose);
        neurologicoAdapterModelList.get(position).setTipoSedativo(droga);
        notifyDataSetChanged();
    }

    private void setAnimationExcludeItem(View view) {
        myAnimation.slideOutRight(context, view);
        lastPosition = getItemCount() - 1;
    }

    private void deleteItem(View view,int position){
        neurologicoAdapterModelList.remove(position);
        setAnimationExcludeItem(view);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,neurologicoAdapterModelList.size());
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
