package dds.recetas;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.BdRecetaAPI;
import dds.recetas.datos.Ingrediente;
import dds.recetas.datos.Paso;
import dds.recetas.datos.Receta;
import dds.recetas.datos.Regimen;
import dds.recetas.datos.Tipo;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;

public class AgregarFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Button botonAgregarIngrediente, botonAgregarPaso, botonBuscarImagen, botonGuardarReceta;
    EditText nombreReceta, primerPaso, primerIngrediente;
    ImageView imageViewReceta;
    String tituloReceta;
    List<EditText> editIngredientes = new ArrayList<EditText>();
    List<EditText> editPasos = new ArrayList<EditText>();
    List<Paso> listaPasos = new ArrayList<Paso>();
    List<Ingrediente> listaIngredientes = new ArrayList<Ingrediente>();
    Spinner spinnerPorcionesAgregar, spinnerTipoAgregar, spinnerRegimenAgregar;
    Tipo tipo;
    Regimen regimen;
    Uri urlImagen = null;
    int porciones, pasos = 1, ingredientes = 1;
    public ArrayAdapter<String> adaptadorTipo;
    public ArrayAdapter<String> adaptadorRegimen;
    public ArrayAdapter<String> adaptadorPorciones;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //((Main)getActivity()).hideFAB();

        final Application application = getActivity().getApplication();

        final ScrollView fragmentAgregar = (ScrollView) inflater.inflate(R.layout.fragment_agregar, container, false);

        botonBuscarImagen = fragmentAgregar.findViewById(R.id.botonBuscarImagen);
        botonAgregarIngrediente = fragmentAgregar.findViewById(R.id.botonNuevoIngrediente);
        botonAgregarPaso = fragmentAgregar.findViewById(R.id.botonNuevoPaso);
        botonGuardarReceta = fragmentAgregar.findViewById(R.id.botonAgregarReceta);
        nombreReceta = fragmentAgregar.findViewById(R.id.textoTitulo);
        imageViewReceta = fragmentAgregar.findViewById(R.id.imagenReceta);
        primerIngrediente = fragmentAgregar.findViewById(R.id.ingrediente1);
        primerPaso = fragmentAgregar.findViewById(R.id.paso1);
        spinnerPorcionesAgregar = fragmentAgregar.findViewById(R.id.spinnerPorcionesAgregar);
        spinnerTipoAgregar = fragmentAgregar.findViewById(R.id.spinnerTipoAgregar);
        spinnerRegimenAgregar = fragmentAgregar.findViewById(R.id.spinnerRegimenAgregar);

        spinnerRegimenAgregar.setOnItemSelectedListener(this);
        spinnerTipoAgregar.setOnItemSelectedListener(this);
        spinnerPorcionesAgregar.setOnItemSelectedListener(this);

        setAdaptadoresSpinners();

        botonAgregarIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout layoutIngrediente = fragmentAgregar.findViewById(R.id.layoutIngredientes);
                ingredientes++;

                EditText ingredienteN = new EditText(fragmentAgregar.getContext());
                ingredienteN.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                ingredienteN.setHint("Ingrediente " + String.valueOf(ingredientes));
                ingredienteN.setId(ingredientes);
                editIngredientes.add(ingredienteN);

                layoutIngrediente.addView(ingredienteN);
            }
        });

        botonAgregarPaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout layoutPaso = fragmentAgregar.findViewById(R.id.layoutPasos);
                pasos++;

                EditText pasoN = new EditText(fragmentAgregar.getContext());
                pasoN.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                pasoN.setHint("Paso " + String.valueOf(pasos));
                pasoN.setId(pasos);
                editPasos.add(pasoN);

                layoutPaso.addView(pasoN);
            }
        });

        botonGuardarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValoresDeReceta();

                if(!verificaValoresDeReceta()){
                    Toast.makeText(fragmentAgregar.getContext(),"No se puede agregar receta", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(fragmentAgregar.getContext(),"Receta agregada", Toast.LENGTH_SHORT).show();
                    Receta nuevaReceta = new Receta(tituloReceta, urlImagen, regimen, tipo, listaIngredientes, listaPasos);

                    BdRecetaAPI apiBaseDeDatos = BdRecetaAPI.getInstance(application);
                    apiBaseDeDatos.crearReceta(nuevaReceta);
                }
            }
        });

        botonBuscarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cargarImagen();
                urlImagen = null;
            }
        });

        return fragmentAgregar;
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccionar aplicación"),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            urlImagen = data.getData();
            imageViewReceta.setImageURI(urlImagen);
        }
    }

    public void setAdaptadoresSpinners(){

         adaptadorTipo = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tipos));
        adaptadorTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoAgregar.setAdapter(adaptadorTipo);

        adaptadorRegimen = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.regimenes));
        adaptadorRegimen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegimenAgregar.setAdapter(adaptadorRegimen);

        adaptadorPorciones = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.porciones));
        adaptadorPorciones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPorcionesAgregar.setAdapter(adaptadorPorciones);

    }

    public boolean verificaValoresDeReceta () {
        int contador = 0;
        if(!verificaStringNoVacia(tituloReceta)){
            nombreReceta.setError("Nombre vacio");
            contador++;
        }
        //FALTA verificar que hay imagen
        if (listaPasos.isEmpty()){
            primerPaso.setError("Añadir pasos");
            contador++;
        }
        if (listaIngredientes.isEmpty()){
            primerIngrediente.setError("Añadir ingredientes");
            contador++;
        }
        if (contador > 0) {return  false;}
        else {return true;}
    }

    public void getValoresDeReceta() {

        tituloReceta = nombreReceta.getText().toString();
        //FALTA el getImagen
        //urlImagenReceta = imagenReceta.getText().toString();
        //Refactoring: Se creo un nuevo metodo para obtener los valores de los ingredientes y otro para los pasos
        crearListaIngredientes();
        crearListaPasos();

    }

    public void crearListaPasos() {

        Paso pasoPrimero = new Paso();
        pasoPrimero.numero = 1;
        String pasoUno = primerPaso.getText().toString();
        if (verificaStringNoVacia(pasoUno)) {
            pasoPrimero.paso = pasoUno;
            listaPasos.add(pasoPrimero);
        }

        for(int i = 0; i < pasos-2; i++){
            String pasoN = editPasos.get(i).getText().toString();
            Paso pasoNuevo = new Paso();
            if(verificaStringNoVacia(pasoN)) {
                pasoNuevo.numero = i + 2;
                pasoNuevo.paso = pasoN;
                listaPasos.add(pasoNuevo);
            }
        }
    }

    public void crearListaIngredientes () {

        Ingrediente ingredientePrimero = new Ingrediente();
        String ingredienteUno = primerIngrediente.getText().toString();
        if(verificaStringNoVacia(ingredienteUno))
        {
            ingredientePrimero.nombre = ingredienteUno;
            listaIngredientes.add(ingredientePrimero);
        }

        for(int i = 0; i < ingredientes-2; i++){
            String ingredienteN = editIngredientes.get(i).getText().toString();
            Ingrediente ingredienteNuevo = new Ingrediente();
            if(verificaStringNoVacia(ingredienteN)){
                ingredienteNuevo.nombre = ingredienteN;
                listaIngredientes.add(ingredienteNuevo);
            }
        }
    }

    public boolean verificaStringNoVacia(String stringAVerificar) {
        if (stringAVerificar.equals("") || stringAVerificar.equals(null)){
            return false;
        }
        else {return true;}
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.equals(spinnerTipoAgregar)) {
            switch (position) {
                case 0:
                    this.tipo = Tipo.INDIFERENTE;
                    break;
                case 1:
                    this.tipo = Tipo.ENTRANTE;
                    break;
                case 2:
                    this.tipo = Tipo.APERITIVO;
                    break;
                case 3:
                    this.tipo = Tipo.POSTRE;
                    break;
                case 4:
                    this.tipo = Tipo.PRINCIPAL;
                    break;
                default:
                    this.tipo = Tipo.INDIFERENTE;
                    break;
            }
        }
        else if (parent.equals(spinnerRegimenAgregar)) {
            switch (position) {
                case 0:
                    this.regimen= Regimen.OMNI;
                    break;
                case 1:
                    this.regimen = Regimen.VEGANO;
                    break;
                case 2:
                    this.regimen = Regimen.VEGETARIANO;
                    break;
                default:
                    this.regimen= Regimen.OMNI;
                    break;
            }
        }
        else if (parent.equals(spinnerPorcionesAgregar)){

            //Refactor de un case a valor + 1
            this.porciones = position + 1;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(parent.equals(R.id.spinnerTipoAgregar)) {
            tipo = Tipo.INDIFERENTE;
        }
        if(parent.equals(R.id.spinnerRegimenAgregar)) {
            regimen = Regimen.OMNI;
        }
        if(parent.equals(R.id.spinnerPorcionesAgregar)) {
            porciones = 2;
        }
    }
}

