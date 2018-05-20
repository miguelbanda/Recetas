package dds.recetas.datos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Receta.class, Ingrediente.class, IngredienteCuantificado.class,
        Paso.class, AsociacionIngredienteReceta.class}, version = 1)
public abstract class BdReceta extends RoomDatabase {

    private static BdReceta INSTANCE;

    public abstract DatabaseDao databaseDao();

    //Singleton
    public static BdReceta getInstance(final Context context) {
        synchronized (BdReceta.class) {
            if (INSTANCE == null) {
                //allowMainThreadQueries() se podrá midificar en el futuro
                //p.e usando LiveData (?)
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        BdReceta.class,
                        "BdReceta").allowMainThreadQueries().build();
            }
        }
        return INSTANCE;
    }
}