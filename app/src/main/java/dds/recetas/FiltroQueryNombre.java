package dds.recetas;

import dds.recetas.datos.Receta;

public class FiltroQueryNombre implements FiltroQuery {

    private String criterio;

    public FiltroQueryNombre(String criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean filtro(Receta r) {
        return r.nombre.contains(criterio);
    }
}
