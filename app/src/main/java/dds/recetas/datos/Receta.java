package dds.recetas.datos;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Receta {
    public String id;
    public String nombre;
    public String foto;
    public boolean favorito;
    public String regimen;
    public String tipo;
    public List<Ingrediente> ingredientes;
    public List<Paso> pasos;

    public Receta(String nombre, String foto, Regimen regimen, Tipo tipo, List<Ingrediente> ingredientes, List<Paso> pasos) {
        this.nombre = nombre;
        this.foto = foto;
        this.favorito = false;
        this.regimen = regimen.toString();
        this.tipo = tipo.toString();
        this.ingredientes = ingredientes;
        this.pasos = pasos;
    }

    public Receta() {
        //Default constructor necesario para Firebase
    }
}
