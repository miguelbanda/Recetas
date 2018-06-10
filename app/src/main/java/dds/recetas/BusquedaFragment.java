package dds.recetas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import dds.recetas.datos.Receta;

public class BusquedaFragment extends Fragment {

    ArrayList<Receta> listaRecetasBusqueda;
    RecyclerView recyclerRecetasBusqueda;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CoordinatorLayout fragment = (CoordinatorLayout) inflater.inflate(R.layout.fragment_busqueda, container, false);

        context = fragment.getContext();
        listaRecetasBusqueda = new ArrayList<>();
        recyclerRecetasBusqueda = fragment.findViewById(R.id.recyclerBusqueda);
        recyclerRecetasBusqueda.setLayoutManager(new LinearLayoutManager(fragment.getContext(),
                LinearLayoutManager.VERTICAL, false));

        AdaptadorRecetasInicio adaptador = new AdaptadorRecetasInicio(context, listaRecetasBusqueda);
        recyclerRecetasBusqueda.setAdapter(adaptador);

        return fragment;
    }
}
