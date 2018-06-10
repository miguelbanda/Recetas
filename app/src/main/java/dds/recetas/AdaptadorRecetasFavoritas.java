package dds.recetas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.Receta;

public class AdaptadorRecetasFavoritas extends RecyclerView.Adapter<AdaptadorRecetasFavoritas.ViewHolderPersonajes> implements View.OnClickListener{



    List<Receta> listaRecetasFavoritas;
    Context context;

    public AdaptadorRecetasFavoritas(Context context, List<Receta> listaRecetasFavoritas) {
        this.listaRecetasFavoritas = listaRecetasFavoritas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderPersonajes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_list, null,false);
        return new ViewHolderPersonajes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPersonajes holder, final int position) {
        Picasso.get().load(listaRecetasFavoritas.get(position).foto).into(holder.fotoReceta);
        holder.nombreReceta.setText(listaRecetasFavoritas.get(position).nombre);

        holder.layoutReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaActividad = new Intent(context, MostrarReceta.class);
                String nombre = listaRecetasFavoritas.get(position).nombre;
                nuevaActividad.putExtra("TituloReceta", nombre);

                Toast.makeText(context, "Click on " + nombre, Toast.LENGTH_SHORT).show();

                context.startActivity(nuevaActividad);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listaRecetasFavoritas.isEmpty())
        {
            return 0;
        }
        return listaRecetasFavoritas.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {
        TextView nombreReceta;
        ImageView fotoReceta;
        public LinearLayout layoutReceta;
        public ViewHolderPersonajes(View itemView) {
            super(itemView);
            nombreReceta = itemView.findViewById(R.id.idNombreFav);
            fotoReceta = itemView.findViewById(R.id.idImagenFav);
            layoutReceta = itemView.findViewById(R.id.layoutFav);
        }
    }
}
