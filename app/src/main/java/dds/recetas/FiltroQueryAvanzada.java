package dds.recetas;

import java.util.Arrays;
import java.util.List;

import dds.recetas.datos.Receta;

public class FiltroQueryAvanzada implements FiltroQuery {

    private List<FiltroQuery> filtros;

    public FiltroQueryAvanzada(FiltroQuery... filtros) {
        this.filtros = Arrays.asList(filtros);
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
