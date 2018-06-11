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
import java.util.List;

import dds.recetas.datos.Receta;
import dds.recetas.datos.BdRecetaAPI;
import dds.recetas.datos.Regimen;
import dds.recetas.datos.Tipo;

public class BuscarReceta extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button botonBuscar;
    EditText editReceta;
    EditText editIngrediente;
    Spinner spinnerTipo;
    Spinner spinnerRegimen;
    Tipo tipo;
    Regimen regimen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_receta);

        spinnerTipo = findViewById(R.id.spinnerTipo);
        spinnerTipo.setOnItemSelectedListener(this);
        ArrayAdapter<String> adaptadorTipo = new ArrayAdapter<String>(BuscarReceta.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tipos));
        adaptadorTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adaptadorTipo);

        spinnerRegimen = findViewById(R.id.spinnerRegimen);
        spinnerRegimen.setOnItemSelectedListener(this);
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


                        String nombre = editReceta.getText().toString();
                        String ingrediente = editIngrediente.getText().toString();

                        Intent intent = new Intent(getApplicationContext(), Resultado.class);

                        intent.putExtra("RECETA", nombre);
                        intent.putExtra("INGREDIENTE", ingrediente);
                        intent.putExtra("TIPO", tipo);
                        intent.putExtra("REGIMEN", regimen);

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
        if(parent == spinnerTipo) {
            switch (position) {
                case 0:
                    tipo = Tipo.INDIFERENTE;
                    break;
                case 1:
                    tipo = Tipo.ENTRANTE;
                    break;
                case 2:
                    tipo = Tipo.APERITIVO;
                    break;
                case 3:
                    tipo = Tipo.POSTRE;
                    break;
                case 4:
                    tipo = Tipo.PRINCIPAL;
                    break;
                default:
                    tipo = Tipo.INDIFERENTE;
                    break;
            }
        }
        else if (parent == spinnerRegimen) {
            switch (position) {
                case 0:
                    regimen= Regimen.OMNI;
                    break;
                case 1:
                    regimen = Regimen.VEGANO;
                    break;
                case 2:
                    regimen = Regimen.VEGETARIANO;
                    break;
                default:
                    regimen= Regimen.OMNI;
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(parent == spinnerTipo) {
            tipo = Tipo.INDIFERENTE;
        }
        if(parent == spinnerRegimen) {
            regimen = Regimen.OMNI;
        }
    }
}
