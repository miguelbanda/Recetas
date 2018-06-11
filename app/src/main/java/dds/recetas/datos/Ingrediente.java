package dds.recetas.datos;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Ingrediente {
    public String nombre;
    public double cantidad;
    public String unidad;

    public Ingrediente(String nombre) {
        this.nombre = nombre;
    }

    public Ingrediente() {
        //Default constructor necesario para Firebase
    }
}
