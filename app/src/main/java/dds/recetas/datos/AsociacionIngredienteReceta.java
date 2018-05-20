package dds.recetas.datos;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "ing_rec", foreignKeys =
        {
            @ForeignKey(entity = Ingrediente.class,
            parentColumns = "id",
            childColumns = "ingrediente"),

            @ForeignKey(entity = Receta.class,
                    parentColumns = "id",
                    childColumns = "receta"
            )
        })
public class AsociacionIngredienteReceta {
    private int ingrediente;
    private int receta;
    private double cantidad;
    private String unidad;

    public AsociacionIngredienteReceta(Receta r, Ingrediente i) {
        ingrediente = i.getId();
        receta = r.getId();
    }
}
