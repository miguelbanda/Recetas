package dds.recetas;

import dds.recetas.datos.Ingrediente;
import dds.recetas.datos.Receta;

public class FiltroQueryIngredientes implements FiltroQuery {
    private String criterio;

    public FiltroQueryIngredientes(String criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean filtro(Receta r) {
        for(Ingrediente i : r.ingredientes) {
            if(i.nombre.contains(criterio)) {
                return true;
            }
        }
        return false;
    }
}
