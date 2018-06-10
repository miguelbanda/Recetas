package dds.recetas;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.BdRecetaAPI;
import dds.recetas.datos.Receta;

public class InicioFragment extends Fragment {

    public List<Receta> listaRecetasInicio;
    RecyclerView recyclerRecetasInicio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((Main)getActivity()).showFAB();
        CoordinatorLayout fragment = (CoordinatorLayout) inflater.inflate(R.layout.fragment_inicio, container, false);

        listaRecetasInicio = new ArrayList<>();
        recyclerRecetasInicio = fragment.findViewById(R.id.recyclerInicio);
        recyclerRecetasInicio.setLayoutManager(new LinearLayoutManager(fragment.getContext(),
                LinearLayoutManager.VERTICAL, false));

        llenarInicio();

        AdaptadorRecetasInicio adaptador = new AdaptadorRecetasInicio(this.getContext(), listaRecetasInicio);
        recyclerRecetasInicio.setAdapter(adaptador);

        return fragment;
    }

    private void llenarInicio() {

        BdRecetaAPI apiBD = BdRecetaAPI.getInstance();
        listaRecetasInicio = apiBD.buscarRecetaPorTitulo("");
    }
}
