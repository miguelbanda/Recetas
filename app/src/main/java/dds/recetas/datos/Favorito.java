package dds.recetas.datos;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(foreignKeys = @ForeignKey(entity = Receta.class,
        parentColumns = "id",
        childColumns = "idReceta"))
public class Favorito {
    private int idReceta;

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public Favorito(Receta r) {
        idReceta = r.getId();
    }
}
