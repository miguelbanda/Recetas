package dds.recetas.datos;

import java.util.ArrayList;
import java.util.List;

public class FiltroQueryFactory {

    private FiltroQueryFactory INSTANCE;

    private FiltroQueryFactory() {}

    //Singleton
    private FiltroQueryFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FiltroQueryFactory();
        }
        return INSTANCE;
    }

    //Métodos fábrica

    //Refactoring: métodos fábrica con menos parámetros para filtrar con solo 1 criterio

    public FiltroQuery build() {
        return new FiltroQueryNull();
    }

    public FiltroQuery build(boolean favorito) {
        return new FiltroQueryFavorito(favorito);
    }

    public FiltroQuery build(Tipo t) {
        if(t == null || t == Tipo.INDIFERENTE) {
            return new FiltroQueryNull();
        }

        return new FiltroQueryTipo(t);
    }

    public FiltroQuery build(Regimen r) {
        if(r == null || r == Regimen.OMNI) {
            return new FiltroQueryNull();
        }

        return new FiltroQueryRegimen(r);
    }

    public enum TipoFiltro { ID, NOMBRE, INGREDIENTE }

    public FiltroQuery build(String value, TipoFiltro t) {
        switch (t) {
            case ID:
                return new FiltroQueryId(value);
            case NOMBRE:
                return new FiltroQueryNombre(value);
            default:
                return new FiltroQueryIngredientes(value);
        }
    }

    //Refactoring : valor de los booleanos en variables en vez de hacer varias invocaciones
    //a isEmpty
    public FiltroQuery build(String id, String nombre, Tipo tipo, Regimen regimen, String ingrediente) {

        boolean bId = isEmpty(id);
        boolean bNombre = isEmpty(nombre);
        boolean bTipo = isEmpty(tipo);
        boolean bRegimen = isEmpty(regimen);
        boolean bIngrediente = isEmpty(ingrediente);

        if(!bId && bNombre && bTipo && bRegimen && bIngrediente) {
            return new FiltroQueryId(id);
        } else if(bId && !bNombre && bTipo && bRegimen && bIngrediente) {
            return new FiltroQueryNombre(nombre);
        } else if(bId && bNombre && !bTipo && bRegimen && bIngrediente) {
            return new FiltroQueryTipo(tipo);
        } else if(bId && bNombre && bTipo && !bRegimen && bIngrediente) {
            return new FiltroQueryRegimen(regimen);
        } else if(bId && bNombre && bTipo && bRegimen && !bIngrediente) {
            return new FiltroQueryIngredientes(ingrediente);
        } else if(bId && bNombre && bTipo && bRegimen && bIngrediente) {
            return new FiltroQueryNull();
        } else {//Búsqueda avanzada
            List<FiltroQuery> filtros = new ArrayList<>();

            if(!bNombre)
                filtros.add(new FiltroQueryNombre(nombre));
            if(!bRegimen)
                filtros.add(new FiltroQueryTipo(tipo));
            if(!bTipo)
                filtros.add(new FiltroQueryRegimen(regimen));
            if(!bIngrediente)
                filtros.add(new FiltroQueryIngredientes(ingrediente));
            if(!bId)
                filtros.add(new FiltroQueryId(id));

            return new FiltroQueryAvanzada(filtros);
        }
    }

    //Utilidades

    private boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    private boolean isEmpty(Regimen r) {
        return r == null || r == Regimen.OMNI;
    }

    private boolean isEmpty(Tipo t) {
        return t == null || t == Tipo.INDIFERENTE;
    }
}
