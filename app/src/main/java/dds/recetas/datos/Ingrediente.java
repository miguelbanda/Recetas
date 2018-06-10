package dds.recetas.datos;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Ingrediente {
    public int receta;
    public String nombre;
    public double cantidad;
    public String unidad;

    public Ingrediente(int receta, String nombre, double cantidad, String unidad) {
        this.receta = receta;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }

    public Ingrediente() {
        //Default constructor necesario para Firebase
    }
}
