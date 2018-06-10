package dds.recetas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

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


        LinearLayout linearFavoritos =(LinearLayout) inflater.inflate(R.layout.fragment_favoritos, container, false);

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
        for (int i = 1; i <= 10; i++ ) {
            Receta receta = new Receta();
            receta.nombre = "Receta" + i;
            //receta.foto = "http://foodandtravel.mx/home/wp-content/uploads/2017/08/tacoslapastorFT.jpg";
            listaRecetasFavoritas.add(receta);
        }
    }
}
