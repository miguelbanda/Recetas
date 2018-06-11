package dds.recetas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import dds.recetas.datos.Receta;
import dds.recetas.datos.Regimen;
import dds.recetas.datos.Tipo;

public class Resultado extends AppCompatActivity {

    String receta;
    String ingrediente;
    Tipo tipo;
    Regimen regimen;
    private DatabaseReference databaseRef;;
    List<Receta> listaRecetasBusqueda;

    RecyclerView recyclerRecetasBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        listaRecetasBusqueda = new ArrayList<>();
        recyclerRecetasBusqueda = findViewById(R.id.recyclerResultado);

        Bundle bundle = getIntent().getExtras();
        receta = bundle.getString("RECETA");
        ingrediente = bundle.getString("INGREDIENTE");
        tipo = (Tipo) bundle.get("TIPO");
        regimen = (Regimen) bundle.get("REGIMEN");

        databaseRef = FirebaseDatabase.getInstance().getReference("recetas");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaRecetasBusqueda.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Receta receta = postSnapshot.getValue(Receta.class);
                    listaRecetasBusqueda.add(receta);
                }

                FiltroQueryFactory fab = FiltroQueryFactory.getInstance();
                FiltroQuery filtro = fab.build(null, receta, tipo, regimen, ingrediente);
                listaRecetasBusqueda = Receta.filtrar(listaRecetasBusqueda, filtro);

                AdaptadorRecetasInicio adaptador = new AdaptadorRecetasInicio(Resultado.this, listaRecetasBusqueda);
                recyclerRecetasBusqueda.setAdapter(adaptador);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Resultado.this, databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarResultado);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
