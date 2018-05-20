package dds.recetas.datos;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

@Entity(foreignKeys = @ForeignKey(entity = Receta.class,
        parentColumns = "id",
        childColumns = "idReceta"), indices = {@Index(value = {"idReceta"})})
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
