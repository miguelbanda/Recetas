package dds.recetas.datos;

public class FiltroQueryNombre implements FiltroQuery {

    private String criterio;

    public FiltroQueryNombre(String criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean filtro(Receta r) {
        return r.getNombre().toLowerCase().contains(criterio.toLowerCase());
    }
}
