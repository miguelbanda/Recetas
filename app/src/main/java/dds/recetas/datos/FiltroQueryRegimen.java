package dds.recetas.datos;

public class FiltroQueryRegimen implements FiltroQuery {

    private Regimen criterio;

    public FiltroQueryRegimen(Regimen criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean filtro(Receta r) {
        return r.regimen.equals(criterio.toString());
    }
}
