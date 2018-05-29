package dds.recetas;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dds.recetas.datos.Receta;

public class AdaptadorRecetasFavoritas extends RecyclerView.Adapter<AdaptadorRecetasFavoritas.ViewHolderPersonajes>{



    ArrayList<Receta> listaRecetasFavoritas;

    public AdaptadorRecetasFavoritas(ArrayList<Receta> listaRecetasFavoritas) {
        this.listaRecetasFavoritas = listaRecetasFavoritas;
    }

    @NonNull
    @Override
    public ViewHolderPersonajes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_list, null,false);
        return new ViewHolderPersonajes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPersonajes holder, int position) {
        holder.nombreReceta.setText(listaRecetasFavoritas.get(position).getTitulo());
        Picasso.get().load("http://foodandtravel.mx/home/wp-content/uploads/2017/08/tacoslapastorFT.jpg").into(holder.fotoReceta);
    }

    @Override
    public int getItemCount() {
        return listaRecetasFavoritas.size();
    }

    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {
        TextView nombreReceta;
        ImageView fotoReceta;
        public ViewHolderPersonajes(View itemView) {
            super(itemView);
            nombreReceta = itemView.findViewById(R.id.idNombreFav);
            fotoReceta = itemView.findViewById(R.id.idImagenFav);
        }
    }
}
