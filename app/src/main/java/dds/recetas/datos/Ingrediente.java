package dds.recetas.datos;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Ingrediente {
    private String nombre;

    public Ingrediente(String nombre) {
        this.setNombre(nombre);
    }

    public Ingrediente() {
        //Default constructor necesario para Firebase
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
