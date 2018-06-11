package dds.recetas.datos;

import java.util.List;

public class FiltroQueryAvanzada implements FiltroQuery {

    private List<FiltroQuery> filtros;

    public FiltroQueryAvanzada(List<FiltroQuery> filtros) {
        this.filtros = filtros;
    }

    @Override
    public boolean filtro(Receta r) {
        for(FiltroQuery f : filtros) {
            if(!f.filtro(r)) {
                return false;
            }
        }

        return true;
    }
}
