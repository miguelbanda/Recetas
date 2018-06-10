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

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.BdRecetaAPI;
import dds.recetas.datos.Ingrediente;
import dds.recetas.datos.Paso;
import dds.recetas.datos.Receta;
import dds.recetas.datos.Regimen;
import dds.recetas.datos.Tipo;

public class FavoritosFragment extends Fragment {

    List<Receta> listaRecetasFavoritas;
    RecyclerView recyclerRecetasFavoritas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        CoordinatorLayout linearFavoritos = (CoordinatorLayout) inflater.inflate(R.layout.fragment_favoritos, container, false);

        listaRecetasFavoritas = new ArrayList<>();
        recyclerRecetasFavoritas = linearFavoritos.findViewById(R.id.recyclerFav);
        recyclerRecetasFavoritas.setLayoutManager(new LinearLayoutManager(linearFavoritos.getContext(),
                LinearLayoutManager.VERTICAL, false));

        llenarRecetasFavoritas();

        AdaptadorRecetasFavoritas adaptador = new AdaptadorRecetasFavoritas(this.getContext(), listaRecetasFavoritas);
        recyclerRecetasFavoritas.setAdapter(adaptador);

        return linearFavoritos;
    }

    private void llenarRecetasFavoritas() {
        BdRecetaAPI apiReceta = BdRecetaAPI.getInstance();
        listaRecetasFavoritas = apiReceta.favoritos();
    }
}
