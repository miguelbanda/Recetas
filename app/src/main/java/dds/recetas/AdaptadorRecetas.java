package dds.recetas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import dds.recetas.datos.Receta;

public class AdaptadorRecetas extends RecyclerView.Adapter<AdaptadorRecetas.ViewHolderRecetas> implements View.OnClickListener {

    List<Receta> listaRecetasInicio;
    Context context;
    DatabaseReference databaseRef;

    public AdaptadorRecetas(Context context, List<Receta> listaRecetasInicio) {
        this.listaRecetasInicio = listaRecetasInicio;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderRecetas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receta_list, null,false);
        return new ViewHolderRecetas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderRecetas holder, final int position) {
        Picasso.get().load(listaRecetasInicio.get(position).getFoto())
                .resize(72,72)
                .centerCrop()
                .into(holder.fotoReceta);
        holder.nombreReceta.setText(listaRecetasInicio.get(position).getNombre());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaActividad = new Intent(context, MostrarReceta.class);
                String idReceta = listaRecetasInicio.get(position).getId();
                nuevaActividad.putExtra("id", idReceta);
                nuevaActividad.putExtra("fav", listaRecetasInicio.get(position).isFavorito());

                context.startActivity(nuevaActividad);
            }
        });

        holder.layoutItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                borrarReceta(listaRecetasInicio.get(position), position);
                return true;
            }
        });
    }

    private void borrarReceta(final Receta r, final int position) {

        AlertDialog.Builder alertaBorrar = new AlertDialog.Builder(context);
        alertaBorrar.setMessage("¿Eliminar receta?").setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listaRecetasInicio.remove(r);
                        databaseRef = FirebaseDatabase.getInstance().getReference("recetas");
                        databaseRef.child(r.getId()).removeValue();
                        notifyItemRemoved(position);
                        dialog.dismiss();
                        Toast.makeText(context, "Receta eliminada", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alerta = alertaBorrar.create();
        alerta.setTitle("Alerta");
        alerta.show();
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
