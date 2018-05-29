package dds.recetas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorFavs extends RecyclerView.Adapter<AdaptadorFavs.ViewHolderDatos>{

    ArrayList<String> listFavoritos;

    public AdaptadorFavs(ArrayList<String> listFavoritos) {
        this.listFavoritos = listFavoritos;
    }

    @NonNull
    @Override
    public AdaptadorFavs.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_list, null, false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorFavs.ViewHolderDatos holder, int position) {
        holder.asignarDatos(listFavoritos.get(position));
    }

    @Override
    public int getItemCount() {
        return listFavoritos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            dato = (TextView) itemView.findViewById(R.id.idFav);
        }

        public void asignarDatos(String datos) {
            dato.setText(datos);
        }
    }
}
