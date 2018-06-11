package dds.recetas.datos;

public class FiltroQueryId implements FiltroQuery {

    private String criterio;

    public FiltroQueryId(String criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean filtro(Receta r) {
        return r.getId().equals(criterio);
    }
}
