package dds.recetas.datos;

//Null object
public class FiltroQueryNull implements FiltroQuery {
    @Override
    public boolean filtro(Receta r) {
        return true;
    }
}
