package dds.recetas;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import dds.recetas.datos.Receta;
import dds.recetas.datos.BdRecetaAPI;

public class BuscarReceta extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button botonBuscar;
    EditText editReceta;
    EditText editIngrediente;
    Spinner spinnerTipo;
    Spinner spinnerRegimen;
    String tipo;
    String regimen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_receta);

        spinnerTipo = findViewById(R.id.spinnerTipo);
        spinnerRegimen = findViewById(R.id.spinnerRegimen);

        ArrayAdapter<String> adaptadorTipo = new ArrayAdapter<String>(BuscarReceta.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tipos));
        adaptadorTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adaptadorTipo);

        ArrayAdapter<String> adaptadorRegimen = new ArrayAdapter<String>(BuscarReceta.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.regimenes));
        adaptadorRegimen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegimen.setAdapter(adaptadorRegimen);

        editReceta = findViewById(R.id.editTextNombre);
        editIngrediente = findViewById(R.id.editTextIngrediente);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSearch);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        botonBuscar = findViewById(R.id.buttonBuscar);
        botonBuscar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonBuscar:

                        ArrayList<Receta> recetasBusqueda = new ArrayList<Receta>();

                        String nombre = editReceta.getText().toString();
                        String ingrediente = editIngrediente.getText().toString();

                        //Hacer busqueda a base de datos

                        //Refactor: Estaba pasando los parametros a la siguiente vista,
                        // pero es mejor hacer la busqueda aqui y pasar la lista de recetas
                        // a la siguiente vista


                        //Aun no se si hacer una nueva actividad o volver a la de inicio ¿?
                        Intent intent = new Intent(getApplicationContext(), Main.class);
                        intent.putExtra("RECETAS", recetasBusqueda);
                        startActivity(intent);
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.equals(R.id.spinnerTipo)) {
            tipo = parent.getItemAtPosition(position).toString();
        }
        else if (parent.equals(R.id.spinnerRegimen)) {
            regimen = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        regimen = "";
        tipo = "";
    }
}
