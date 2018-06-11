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

    public FiltroQuery build() {
        return new FiltroQueryNull();
    }

    public FiltroQuery build(boolean favorito) {
        return new FiltroQueryFavorito(favorito);
    }

    public FiltroQuery build(String id, String nombre, Tipo tipo, Regimen regimen, String ingrediente) {

        if(!isEmpty(id) && isEmpty(nombre) && isEmpty(tipo)
                && isEmpty(regimen) && isEmpty(ingrediente)) {
            return new FiltroQueryId(id);
        } else if(!isEmpty(nombre) && isEmpty(id) && isEmpty(tipo)
                && isEmpty(regimen) && isEmpty(ingrediente)) {
            return new FiltroQueryNombre(nombre);
        } else if(!isEmpty(tipo) && isEmpty(id) && isEmpty(nombre)
                && isEmpty(regimen) && isEmpty(ingrediente)) {
            return new FiltroQueryTipo(tipo);
        } else if(!isEmpty(regimen) && isEmpty(id) && isEmpty(nombre)
                && isEmpty(tipo) && isEmpty(ingrediente)) {
            return new FiltroQueryRegimen(regimen);
        } else if(!isEmpty(ingrediente) && isEmpty(id) && isEmpty(nombre)
                && isEmpty(tipo) && isEmpty(regimen)) {
            return new FiltroQueryIngredientes(ingrediente);
        } else if(isEmpty(nombre) && !isEmpty(id) && isEmpty(tipo)
                && isEmpty(regimen) && isEmpty(ingrediente)) {
            return new FiltroQueryNull();
        } else {//Búsqueda avanzada
            List<FiltroQuery> filtros = new ArrayList<>();

            if(!isEmpty(nombre))
                filtros.add(new FiltroQueryNombre(nombre));
            if(!isEmpty(tipo))
                filtros.add(new FiltroQueryTipo(tipo));
            if(!isEmpty(regimen))
                filtros.add(new FiltroQueryRegimen(regimen));
            if(!isEmpty(ingrediente))
                filtros.add(new FiltroQueryIngredientes(ingrediente));

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
