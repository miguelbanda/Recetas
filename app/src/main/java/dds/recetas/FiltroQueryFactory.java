package dds.recetas;

import dds.recetas.datos.Regimen;
import dds.recetas.datos.Tipo;

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

    //Método fábrica
    public FiltroQuery build(String nombre, Tipo tipo, Regimen regimen, String ingrediente) {
        return new FiltroQueryNull();
    }
}
