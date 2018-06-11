package dds.recetas.datos;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarReceta {

    private static AgregarReceta INSTANCIA;
    private FirebaseDatabase bd;
    private DatabaseReference recetasRef;

    //Refactoring: primary key autogenerada en vez de título: varias recetas pueden
    //compartir título
    public void crearReceta(Receta r) {
        r.setId(recetasRef.push().getKey());
        recetasRef.child(r.getId()).setValue(r);
    }

    //Singleton
    public static AgregarReceta getInstance() {
        if (INSTANCIA == null)
            INSTANCIA = new AgregarReceta();
        return INSTANCIA;
    }

    private AgregarReceta() {
        bd = FirebaseDatabase.getInstance();
        recetasRef = bd.getReference("recetas");
    }
}
