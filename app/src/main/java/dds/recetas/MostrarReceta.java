package dds.recetas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import dds.recetas.datos.BdRecetaAPI;
import dds.recetas.datos.Receta;

public class MostrarReceta extends AppCompatActivity {

    Boolean favorito;
    Receta recetaMostrada = new Receta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_receta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMostrar);
        setSupportActionBar(toolbar);
        favorito = false;

    }

    private void obtenerDatosReceta() {
        if(getIntent().hasExtra("idReceta")) {
            BdRecetaAPI apiBaseDeDatos = BdRecetaAPI.getInstance();
            //FALTA buscar receta por id
            //recetaMostrada = apiBaseDeDatos.buscarReceta();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        if(!favorito){
            inflater.inflate(R.menu.agregar_favorito, menu);
        }
        else {
            inflater.inflate(R.menu.quitar_favorito, menu);
        }

        return true;
    }
}
