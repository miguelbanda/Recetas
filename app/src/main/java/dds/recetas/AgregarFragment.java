package dds.recetas;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.BdRecetaAPI;
import dds.recetas.datos.Ingrediente;
import dds.recetas.datos.Paso;
import dds.recetas.datos.Receta;
import dds.recetas.datos.Regimen;
import dds.recetas.datos.Tipo;

import static android.app.Activity.RESULT_OK;

public class AgregarFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Button botonAgregarIngrediente, botonAgregarPaso, botonBuscarImagen, botonGuardarReceta;
    EditText nombreReceta, primerPaso, primerIngrediente;
    ImageView imageViewReceta;
    String tituloReceta,
            stringImagen = "https://firebasestorage.googleapis.com/v0/b/recetas-53da2.appspot.com/o/recetas%2Fnotfound.png?alt=media&token=1b9b07ee-d785-4138-8ecf-8665c03acc6b";
    String imageNotFound = "https://firebasestorage.googleapis.com/v0/b/recetas-53da2.appspot.com/o/recetas%2Fnotfound.png?alt=media&token=1b9b07ee-d785-4138-8ecf-8665c03acc6b";
    //Refactoring: No poner cadena vacia porque da error, poner por default imagen de error cargada en la base de datos
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

    private StorageReference recetasStorageRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final Application application = getActivity().getApplication();
        recetasStorageRef = FirebaseStorage.getInstance().getReference("recetas");

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

        vaciarCampos(fragmentAgregar);

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
                    cargarReceta(fragmentAgregar);
                }
            }
        });

        botonBuscarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });
        return fragmentAgregar;
    }

    private void vaciarCampos(ScrollView sv) {
        nombreReceta.setText("");
        primerIngrediente.setText("");
        primerPaso.setText("");
        urlImagen = null;
        stringImagen = "";
        listaIngredientes.clear();
        listaPasos.clear();
        tipo = Tipo.INDIFERENTE;
        regimen = Regimen.OMNI;
        imageViewReceta.setImageResource(R.drawable.error);
        setAdaptadoresSpinners();
        editIngredientes.clear();
        editPasos.clear();

        LinearLayout pasosLayout = sv.findViewById(R.id.layoutPasos);
        LinearLayout ingredientesLayout = sv.findViewById(R.id.layoutIngredientes);

        while(pasos > 1) {
            pasos--;
            pasosLayout.removeViewAt(pasos);
        }

        while (ingredientes > 1) {
            ingredientes--;
            ingredientesLayout.removeViewAt(ingredientes);
        }
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccionar aplicación"),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null && data.getData() != null) {
            urlImagen = data.getData();
            Picasso.get().load(urlImagen).into(imageViewReceta);
        }
    }

    private void cargarReceta(final ScrollView sv) {
        if(urlImagen != null){

            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Cargando Receta...");
            progressDialog.show();

            StorageReference fileReference = recetasStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(urlImagen));
            fileReference.putFile(urlImagen)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.dismiss();

                            stringImagen = taskSnapshot.getDownloadUrl().toString();
                            if (!stringImagen.equals(imageNotFound)) {
                                BdRecetaAPI apiBaseDeDatos = BdRecetaAPI.getInstance();

                                Receta nuevaReceta = new Receta(tituloReceta, stringImagen, regimen, tipo, listaIngredientes, listaPasos, porciones);
                                apiBaseDeDatos.crearReceta(nuevaReceta);
                                vaciarCampos(sv);
                                Toast.makeText(getContext(), "Receta agregada", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getContext(), "Seleccionar imagen", Toast.LENGTH_SHORT).show();
                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Subiendo "+(int)progress+"%");
                        }
                    });

        } else {
            Toast.makeText(getContext(), "Seleccionar  Imagen", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension (Uri uri) {
        ContentResolver cr = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    public void setAdaptadoresSpinners(){

        adaptadorTipo = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tiposAgregar));
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
        crearListaIngredientes();
        crearListaPasos();

    }

    public void crearListaPasos() {

        Paso pasoPrimero = new Paso();
        pasoPrimero.setNumero(1);
        String pasoUno = primerPaso.getText().toString();
        if (verificaStringNoVacia(pasoUno)) {
            pasoPrimero.setPaso(pasoUno);
            listaPasos.add(pasoPrimero);
        }

        for(int i = 0; i < pasos-2; i++){
            String pasoN = editPasos.get(i).getText().toString();
            Paso pasoNuevo = new Paso();
            if(verificaStringNoVacia(pasoN)) {
                pasoNuevo.setNumero(i + 2);
                pasoNuevo.setPaso(pasoN);
                listaPasos.add(pasoNuevo);
            }
        }
    }

    public void crearListaIngredientes () {

        Ingrediente ingredientePrimero = new Ingrediente();
        String ingredienteUno = primerIngrediente.getText().toString();
        if(verificaStringNoVacia(ingredienteUno))
        {
            ingredientePrimero.setNombre(ingredienteUno);
            listaIngredientes.add(ingredientePrimero);
        }

        for(int i = 0; i < ingredientes-2; i++){
            String ingredienteN = editIngredientes.get(i).getText().toString();
            Ingrediente ingredienteNuevo = new Ingrediente();
            if(verificaStringNoVacia(ingredienteN)){
                ingredienteNuevo.setNombre(ingredienteN);
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
                    this.tipo = Tipo.ENTRANTE;
                    break;
                case 1:
                    this.tipo = Tipo.APERITIVO;
                    break;
                case 2:
                    this.tipo = Tipo.POSTRE;
                    break;
                case 3:
                    this.tipo = Tipo.PRINCIPAL;
                    break;
                default:
                    this.tipo = Tipo.PRINCIPAL;
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
            tipo = Tipo.PRINCIPAL;
        }
        if(parent.equals(R.id.spinnerRegimenAgregar)) {
            regimen = Regimen.OMNI;
        }
        if(parent.equals(R.id.spinnerPorcionesAgregar)) {
            porciones = 2;
        }
    }
}

