package dds.recetas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.BdRecetaAPI;
import dds.recetas.datos.Ingrediente;
import dds.recetas.datos.Paso;
import dds.recetas.datos.Receta;

public class MostrarReceta extends AppCompatActivity {

    boolean favorito;
    Receta recetaMostrada;
    List<Receta> recetasFavoritas;
    String idReceta, nombreReceta, porcionesReceta, tipoReceta, regimenReceta, urlImagenReceta;
    List<String> pasosReceta, ingredientesReceta;
    List<Ingrediente> ingredientes;
    List<Paso> pasos;
    TextView tv_titulo, tv_porciones, tv_tipo, tv_regimen;
    List<TextView> listaPasos, listaIngredientes;
    ImageView imagenReceta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_receta);

        recetasFavoritas = new ArrayList<>();
        recetaMostrada = new Receta();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMostrar);
        setSupportActionBar(toolbar);

        obtenerIdReceta();
        cargarDatosReceta();


    }

    private void obtenerIdReceta() {
        if(getIntent().hasExtra("id")) {
            idReceta = getIntent().getStringExtra("id");
        }
    }

    private void cargarDatosReceta() {
        imagenReceta = findViewById(R.id.imageViewRecetaMostrar);
        tv_titulo = findViewById(R.id.textViewNombreReceta);
        tv_porciones = findViewById(R.id.textViewPorcionesReceta);
        tv_tipo = findViewById(R.id.textViewTipoReceta);
        tv_regimen = findViewById(R.id.textViewRegimenReceta);

        nombreReceta = recetaMostrada.nombre;
        urlImagenReceta = recetaMostrada.foto;
        porcionesReceta = String.valueOf(recetaMostrada.porciones);
        tipoReceta = recetaMostrada.tipo;
        regimenReceta = recetaMostrada.regimen;
        favorito = recetaMostrada.favorito;
        ingredientes = recetaMostrada.ingredientes;
        pasos = recetaMostrada.pasos;

        crearListaPasos();
        crearListaIngredientes();
    }

    private void crearListaPasos() {
        for(int i = 0; i < pasos.size(); i++){

            LinearLayout layoutPadre = findViewById(R.id.pasosAMostrar);

            TextView pasoN = new TextView(MostrarReceta.this);
            pasoN.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            pasoN.setText(pasos.get(i).paso);

            layoutPadre.addView(pasoN);
        }
    }

    private  void crearListaIngredientes() {
        for(int i = 0; i < ingredientes.size(); i++){

            LinearLayout layoutPadre = findViewById(R.id.ingredientesAMostrar);

            TextView ingredienteN = new TextView(MostrarReceta.this);
            ingredienteN.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            ingredienteN.setText(pasos.get(i).paso);

            layoutPadre.addView(ingredienteN);
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
