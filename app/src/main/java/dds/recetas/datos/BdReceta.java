package dds.recetas.datos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Receta.class, Ingrediente.class, Favorito.class,
        Paso.class}, version = 1)
@TypeConverters({Convertidor.class})
public abstract class BdReceta extends RoomDatabase {

    private static BdReceta INSTANCIA;

    public abstract DatabaseDao databaseDao();

    //Singleton
    public static BdReceta getInstance(final Context context) {
        synchronized (BdReceta.class) {
            if (INSTANCIA == null) {
                //allowMainThreadQueries() se podrá modificar en el futuro
                //p.e usando LiveData (?)
                INSTANCIA = Room.databaseBuilder(context.getApplicationContext(),
                        BdReceta.class,
                        "BdReceta").allowMainThreadQueries().build();
            }
        }
        return INSTANCIA;
    }
}