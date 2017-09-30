package santauti.app.Adapters.Ficha.BombaInfusao;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import santauti.app.Activities.Ficha.PartesMedicas.ExamesActivity;
import santauti.app.Animation.MyAnimation;
import santauti.app.R;

/**
 * Created by Raphael Fernandes on 14-Jun-17.
 */

public class BombaInfusaoAdapter extends RecyclerView.Adapter<BombaInfusaoAdapter.ViewHolder>{
    private Context context;
    private List<BombaInfusaoAdapterModel> hemodinamicoAdapterModelList;
    private int lastPosition=-1;
    private MyAnimation myAnimation = new MyAnimation();
    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView droga;
        public final TextView velInfusao;
        public final ImageView deleteIcon;
        final ImageView editIcon;
        public ViewHolder(View itemView) {
            super(itemView);
            droga = (TextView) itemView.findViewById(R.id.droga);
            velInfusao = (TextView)itemView.findViewById(R.id.velocidadeInfusao);
            deleteIcon = (ImageView)itemView.findViewById(R.id.deleteIcon);
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

    private void ordenaStringSpinner(String[] stringVec){
        Arrays.sort(stringVec, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareTo(t1);
            }
        });
    }

    private void editItem(View view, final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Editar Droga");

        final String[] drogasVasoativa = new String[]{"Dobutamina", "Dopamina", "Nitroprussiato de Sodio",
                "Nitroglicerina", "Milrinona", "Noradrenalina", "Adrenalina","Fentanil","Propofol","Ketamina","Midazolam","Precedex",
                "Amiodarona","Insulina","Hidrocortisona","Polimixina"};

        ordenaStringSpinner(drogasVasoativa);
        LayoutInflater li = LayoutInflater.from(view.getContext());

        View dialogView = li.inflate(R.layout.bombainfusao_dialog,null);
        dialogView.requestFocus();

        final Spinner drogasVasoativasSpinner;
        drogasVasoativasSpinner = (Spinner)dialogView.findViewById(R.id.drogaVasoativa);
        ArrayAdapter<String> adapterDrogaVasoativas = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, drogasVasoativa);
        drogasVasoativasSpinner.setAdapter(adapterDrogaVasoativas);

        int spinnerPosition = adapterDrogaVasoativas.getPosition(hemodinamicoAdapterModelList.get(position).getDroga());
        drogasVasoativasSpinner.setSelection(spinnerPosition);
        final TextInputEditText velInfusao = (TextInputEditText) dialogView.findViewById(R.id.velInfusaoDialog);
        velInfusao.setText(Integer.toString(hemodinamicoAdapterModelList.get(position).getVelInfusao()));
        builder.setView(dialogView);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(velInfusao.length()>0)
                    addDataFromDialogIntoAdapter(drogasVasoativasSpinner.getSelectedItem().toString(),Integer.parseInt(velInfusao.getText().toString()),position);
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

    private void addDataFromDialogIntoAdapter(String droga,int velInfusao,int position){
        hemodinamicoAdapterModelList.get(position).setDroga(droga);
        hemodinamicoAdapterModelList.get(position).setVelInfusao(velInfusao);
        notifyDataSetChanged();
    }

    public BombaInfusaoAdapter(Context context, List<BombaInfusaoAdapterModel> hemodinamicoAdapterModelList){
        this.context=context;
        this.hemodinamicoAdapterModelList = hemodinamicoAdapterModelList;
    }


    @Override
    public BombaInfusaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bombainfusao_drogavasoativa_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BombaInfusaoAdapter.ViewHolder holder, final int position) {
        BombaInfusaoAdapterModel hemodinamicoModel = hemodinamicoAdapterModelList.get(position);
        holder.droga.setText(hemodinamicoModel.getDroga());
        holder.velInfusao.setText(String.valueOf(hemodinamicoModel.getVelInfusao()));
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
