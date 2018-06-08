package dds.recetas;

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

public class AdaptadorRecetasInicio extends RecyclerView.Adapter<AdaptadorRecetasInicio.ViewHolderRecetas> {

    ArrayList<Receta> listaRecetasInicio;

    public AdaptadorRecetasInicio(ArrayList<Receta> listaRecetasInicio) {
        this.listaRecetasInicio = listaRecetasInicio;
    }

    @NonNull
    @Override
    public ViewHolderRecetas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inicio_list, null,false);
        return new ViewHolderRecetas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecetas holder, int position) {
        String urlReceta = listaRecetasInicio.get(position).getFoto();
        holder.nombreReceta.setText(listaRecetasInicio.get(position).getTitulo());
        Picasso.get().load(urlReceta).into(holder.fotoReceta);
    }

    @Override
    public int getItemCount() {
        return listaRecetasInicio.size();
    }

    public class ViewHolderRecetas extends RecyclerView.ViewHolder {

        TextView nombreReceta;
        ImageView fotoReceta;

        public ViewHolderRecetas(View itemView) {
            super(itemView);
            nombreReceta = itemView.findViewById(R.id.idNombreInicio);
            fotoReceta = itemView.findViewById(R.id.idImagenInicio);
        }
    }
}
