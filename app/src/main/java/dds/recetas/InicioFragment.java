package dds.recetas;

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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.Receta;

public class InicioFragment extends Fragment {

    private List<Receta> listaRecetasInicio;
    RecyclerView recyclerRecetasInicio;
    private DatabaseReference databaseRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final CoordinatorLayout fragment = (CoordinatorLayout) inflater.inflate(R.layout.fragment_inicio, container, false);

        recyclerRecetasInicio = fragment.findViewById(R.id.recyclerInicio);
        recyclerRecetasInicio.setLayoutManager(new LinearLayoutManager(fragment.getContext(),
                LinearLayoutManager.VERTICAL, false));

        listaRecetasInicio = new ArrayList<>();

        databaseRef = FirebaseDatabase.getInstance().getReference("recetas");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaRecetasInicio.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Receta receta = postSnapshot.getValue(Receta.class);
                    listaRecetasInicio.add(receta);
                }

                AdaptadorRecetas adaptador = new AdaptadorRecetas(fragment.getContext(), listaRecetasInicio);
                recyclerRecetasInicio.setAdapter(adaptador);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        return fragment;
    }
}
