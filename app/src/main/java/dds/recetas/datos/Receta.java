package dds.recetas.datos;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Receta {
    public String nombre;
    public Uri foto;
    public boolean favorito;
    public Regimen regimen;
    public Tipo tipo;
    public List<Ingrediente> ingredientes;
    public List<Paso> pasos;

    public Receta(String nombre, Uri foto, Regimen regimen, Tipo tipo, List<Ingrediente> ingredientes, List<Paso> pasos) {
        this.nombre = nombre;
        this.foto = foto;
        this.favorito = false;
        this.regimen = regimen;
        this.tipo = tipo;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
    }

    public Receta() {
        //Default constructor necesario para Firebase
    }
}
