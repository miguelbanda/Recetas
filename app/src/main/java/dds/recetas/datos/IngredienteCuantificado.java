package dds.recetas.datos;

import android.arch.persistence.room.ColumnInfo;

public class IngredienteCuantificado {

    @ColumnInfo(name = "ingrediente")
    private String ingrediente;
    @ColumnInfo(name = "cantidad")
    private double cantidad;
    @ColumnInfo(name = "unidad")
    private String unidad;

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
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

    public IngredienteCuantificado(String ingrediente, double cantidad, String unidad) {
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }
}
