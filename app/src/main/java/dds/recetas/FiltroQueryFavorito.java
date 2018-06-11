package dds.recetas;

import dds.recetas.datos.Receta;

public class FiltroQueryFavorito implements FiltroQuery {

    private boolean criterio;

    public FiltroQueryFavorito(boolean criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean filtro(Receta r) {
        return r.favorito == criterio;
    }
}
