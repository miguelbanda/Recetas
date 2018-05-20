package dds.recetas.datos;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

@Entity(tableName = "ing_rec", foreignKeys =
        {
            @ForeignKey(entity = Ingrediente.class,
            parentColumns = "id",
            childColumns = "ingrediente"),

            @ForeignKey(entity = Receta.class,
                    parentColumns = "id",
                    childColumns = "receta"
            )
        }, indices = {@Index(value = {"receta"}), @Index(value = {"ingrediente"})})
public class AsociacionIngredienteReceta {
    private int ingrediente;
    private int receta;
    private double cantidad;
    private String unidad;

    public int getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(int ingrediente) {
        this.ingrediente = ingrediente;
    }

    public int getReceta() {
        return receta;
    }

    public void setReceta(int receta) {
        this.receta = receta;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public AsociacionIngredienteReceta(Receta r, Ingrediente i) {
        ingrediente = i.getId();
        receta = r.getId();
    }
}
