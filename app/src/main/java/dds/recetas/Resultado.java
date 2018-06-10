package dds.recetas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.BdRecetaAPI;
import dds.recetas.datos.Ingrediente;
import dds.recetas.datos.Receta;
import dds.recetas.datos.Regimen;
import dds.recetas.datos.Tipo;

public class Resultado extends AppCompatActivity {

    String receta;
    String ingrediente;
    Tipo tipo;
    Regimen regimen;
    Context context;

    RecyclerView recyclerRecetasResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        context = this.getApplicationContext();

        Bundle bundle = getIntent().getExtras();
        receta = bundle.getString("RECETA");
        ingrediente = bundle.getString("INGREDIENTE");
        tipo = (Tipo) bundle.get("TIPO");
        regimen = (Regimen) bundle.get("REGIMEN");

        BdRecetaAPI apiBaseDeDatos = BdRecetaAPI.getInstance(getApplication());



        List<Receta> listaDeResultados = apiBaseDeDatos.buscarReceta(receta,ingrediente,tipo,regimen);

        recyclerRecetasResultado = findViewById(R.id.recyclerResultado);
        recyclerRecetasResultado.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        AdaptadorRecetasInicio adaptador = new AdaptadorRecetasInicio(context, listaDeResultados);
        recyclerRecetasResultado.setAdapter(adaptador);

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
