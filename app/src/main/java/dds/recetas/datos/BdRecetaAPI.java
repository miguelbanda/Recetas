package dds.recetas.datos;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//Patrón adaptador
public class BdRecetaAPI {

    private static BdRecetaAPI INSTANCIA;
    private FirebaseDatabase bd;
    private DatabaseReference recetasRef;
    private List<Receta> recetas;

    public List<Receta> favoritos() {
        return null;
    }

    public void agregarFavorito(Receta r) {
        recetasRef.child(r.id).child("favorito").setValue(true);
    }

    public void borrarFavorito(Receta r) {
        recetasRef.child(r.id).child("favorito").setValue(false);
    }

    public List<Receta> buscarReceta(String titulo, String ingrediente,
                                     Tipo tipo, Regimen regimen) {
        List<Receta> resultado = new ArrayList<>();
        for(Receta r : recetas) {
            if(r.nombre.toLowerCase().contains(titulo.toLowerCase())
                    && (tipo.toString() == r.tipo || tipo == Tipo.INDIFERENTE)
                    && (regimen.toString() == r.regimen || regimen == Regimen.OMNI)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    //Refactoring: primary key autogenerada en vez de título: varias recetas pueden
    //compartir título
    public void crearReceta(Receta r) {
        r.id = recetasRef.push().getKey();
        recetasRef.child(r.id).setValue(r);
    }

    public List<Receta> buscarRecetaPorTitulo(String titulo) {
        List<Receta> resultado = new ArrayList<>();
        for(Receta r : recetas) {
            if(r.nombre.toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    //Singleton
    public static BdRecetaAPI getInstance() {
        if (INSTANCIA == null)
            INSTANCIA = new BdRecetaAPI();
        return INSTANCIA;
    }

    private BdRecetaAPI() {
        bd = FirebaseDatabase.getInstance();
        recetasRef = bd.getReference("recetas");
        recetas = new ArrayList<>();

        recetasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recetas.clear();
                for(DataSnapshot rs : dataSnapshot.getChildren()) {
                    Receta r = rs.getValue(Receta.class);
                    recetas.add(r);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
