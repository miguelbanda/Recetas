package dds.recetas.datos;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Ingrediente {
    public String nombre;
    public double cantidad;
    public String unidad;

    public Ingrediente(String nombre, double cantidad, String unidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public Ingrediente() {
        //Default constructor necesario para Firebase
    }
}
