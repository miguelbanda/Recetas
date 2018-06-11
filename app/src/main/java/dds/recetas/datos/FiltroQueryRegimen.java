package dds.recetas.datos;

public class FiltroQueryRegimen implements FiltroQuery {

    private Regimen criterio;

    public FiltroQueryRegimen(Regimen criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean filtro(Receta r) {
        return comparar(r.getRegimen(), criterio);
    }

    private boolean comparar(Regimen regimen1, Regimen regimen2) {
        if(regimen1 == regimen2)
            return true;
        else if(regimen2 == Regimen.OMNI)
            return true;
        else if (regimen1 == Regimen.OMNI)
            return false;
        else {
            if(regimen1 == Regimen.VEGANO && regimen2 == Regimen.VEGETARIANO)
                return true;
            else
                return false;
        }
    }
}
