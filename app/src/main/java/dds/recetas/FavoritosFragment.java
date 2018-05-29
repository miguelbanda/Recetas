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

public class FavoritosFragment extends Fragment {

    ArrayList<String> listFavoritos;
    RecyclerView recyclerFavoritos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        LinearLayout linearFavoritos =(LinearLayout) inflater.inflate(R.layout.fragment_favoritos, container, false);

        recyclerFavoritos = linearFavoritos.findViewById(R.id.recyclerFav);
        recyclerFavoritos.setLayoutManager(new LinearLayoutManager(linearFavoritos.getContext(),
                LinearLayoutManager.VERTICAL, false));
        listFavoritos = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            listFavoritos.add("Dato #" + i + " ");
        }

        AdaptadorFavs adaptador = new AdaptadorFavs(listFavoritos);
        recyclerFavoritos.setAdapter(adaptador);

        return linearFavoritos;
    }
}
