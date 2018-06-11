package dds.recetas;

import dds.recetas.datos.Receta;

//Null object
public class FiltroQueryNull implements FiltroQuery {
    @Override
    public boolean filtro(Receta r) {
        return true;
    }
}
