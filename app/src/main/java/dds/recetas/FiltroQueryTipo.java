package dds.recetas;

import dds.recetas.datos.Receta;
import dds.recetas.datos.Tipo;

public class FiltroQueryTipo implements FiltroQuery {

    private Tipo criterio;

    public FiltroQueryTipo(Tipo criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean filtro(Receta r) {
        return r.tipo.equals(criterio.toString());
    }
}
