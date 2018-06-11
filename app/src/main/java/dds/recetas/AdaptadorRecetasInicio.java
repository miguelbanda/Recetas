package dds.recetas;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;

import dds.recetas.datos.Receta;

public class AdaptadorRecetasInicio extends RecyclerView.Adapter<AdaptadorRecetasInicio.ViewHolderRecetas> implements View.OnClickListener {

    List<Receta> listaRecetasInicio;
    Context context;

    public AdaptadorRecetasInicio(Context context, List<Receta> listaRecetasInicio) {
        this.listaRecetasInicio = listaRecetasInicio;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolderRecetas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inicio_list, null,false);
        return new ViewHolderRecetas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderRecetas holder, final int position) {
        Picasso.get().load(listaRecetasInicio.get(position).getFoto()).into(holder.fotoReceta);
        holder.nombreReceta.setText(listaRecetasInicio.get(position).getNombre());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaActividad = new Intent(context, MostrarReceta.class);
                String idReceta = listaRecetasInicio.get(position).getId();
                nuevaActividad.putExtra("id", idReceta);
                nuevaActividad.putExtra("fav", listaRecetasInicio.get(position).isFavorito());

                Toast.makeText(context,"Click on: " + holder.nombreReceta.getText(), Toast.LENGTH_SHORT).show();

                context.startActivity(nuevaActividad);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaRecetasInicio.size();
    }

    @Override
    public void onClick(View v) {
    }

    public class ViewHolderRecetas extends RecyclerView.ViewHolder {

        TextView nombreReceta;
        ImageView fotoReceta;
        public LinearLayout layoutItem;

        public ViewHolderRecetas(View itemView) {
            super(itemView);
            nombreReceta = itemView.findViewById(R.id.idNombreInicio);
            fotoReceta = itemView.findViewById(R.id.idImagenInicio);
            layoutItem = itemView.findViewById(R.id.layoutPadre);
        }
    }
}
