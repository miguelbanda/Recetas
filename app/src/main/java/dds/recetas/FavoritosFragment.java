package dds.recetas;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.BdRecetaAPI;
import dds.recetas.datos.FiltroQuery;
import dds.recetas.datos.FiltroQueryFactory;
import dds.recetas.datos.Ingrediente;
import dds.recetas.datos.Paso;
import dds.recetas.datos.Receta;
import dds.recetas.datos.Regimen;
import dds.recetas.datos.Tipo;

public class FavoritosFragment extends Fragment {

    public List<Receta> listaRecetasFavoritas;
    RecyclerView recyclerRecetasFavoritas;
    private DatabaseReference databaseRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final CoordinatorLayout linearFavoritos = (CoordinatorLayout) inflater.inflate(R.layout.fragment_favoritos, container, false);

        listaRecetasFavoritas = new ArrayList<>();
        recyclerRecetasFavoritas = linearFavoritos.findViewById(R.id.recyclerFav);
        recyclerRecetasFavoritas.setLayoutManager(new LinearLayoutManager(linearFavoritos.getContext(),
                LinearLayoutManager.VERTICAL, false));

        databaseRef = FirebaseDatabase.getInstance().getReference("recetas");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaRecetasFavoritas.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Receta receta = postSnapshot.getValue(Receta.class);
                    listaRecetasFavoritas.add(receta);
                }

                FiltroQueryFactory fab = FiltroQueryFactory.getInstance();
                FiltroQuery filtro = fab.build(true);
                listaRecetasFavoritas = Receta.filtrar(listaRecetasFavoritas, filtro);

                AdaptadorRecetas adaptador = new AdaptadorRecetas(linearFavoritos.getContext(), listaRecetasFavoritas);
                recyclerRecetasFavoritas.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        return linearFavoritos;
    }
}
