package dds.recetas;

import dds.recetas.datos.Receta;

public class FiltroQueryId implements FiltroQuery {

    private String criterio;

    public FiltroQueryId(String criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean filtro(Receta r) {
        return r.id.equals(criterio);
    }
}
