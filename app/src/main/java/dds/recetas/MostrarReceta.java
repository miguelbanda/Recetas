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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.BdRecetaAPI;
import dds.recetas.datos.FiltroQuery;
import dds.recetas.datos.FiltroQueryFactory;
import dds.recetas.datos.Ingrediente;
import dds.recetas.datos.Paso;
import dds.recetas.datos.Receta;

public class MostrarReceta extends AppCompatActivity {

    boolean favorito;
    Receta recetaMostrada;
    List<Receta> recetas;
    String idReceta, nombreReceta, porcionesReceta, tipoReceta, regimenReceta, urlImagenReceta;
    List<String> pasosReceta, ingredientesReceta;
    List<Ingrediente> ingredientes;
    List<Paso> pasos;
    TextView tv_titulo, tv_porciones, tv_tipo, tv_regimen;
    ImageView imagenReceta;
    private DatabaseReference databaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_receta);

        recetas = new ArrayList<>();
        recetaMostrada = new Receta();

        databaseRef = FirebaseDatabase.getInstance().getReference("recetas");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Receta receta = postSnapshot.getValue(Receta.class);
                    recetas.add(receta);
                }
                FiltroQueryFactory fab = FiltroQueryFactory.getInstance();
                FiltroQuery filtro = fab.build(idReceta, null, null, null, null);
                recetas = Receta.filtrar(recetas, filtro);
                recetaMostrada = recetas.get(0);
                obtenerIdReceta();
                cargarDatosReceta();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MostrarReceta.this, databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMostrar);
        setSupportActionBar(toolbar);
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

        Picasso.get().load(urlImagenReceta).into(imagenReceta);
        tv_titulo.setText(nombreReceta);
        tv_porciones.setText("Porciones: " + porcionesReceta);
        tv_tipo.setText("Tipo: " + tipoReceta);
        tv_regimen.setText("Régimen: " + regimenReceta);

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
            ingredienteN.setText(ingredientes.get(i).nombre);

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
