package dds.recetas.datos;

public class FiltroQueryRegimen implements FiltroQuery {

    private Regimen criterio;

    public FiltroQueryRegimen(Regimen criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean filtro(Receta r) {
        return comparar(r.getRegimen(), criterio.toString());
    }

    private boolean comparar(String regimen1, String regimen2) {
        if(regimen1.equals(regimen2))
            return true;
        else if(regimen2.equals(Regimen.OMNI.toString()))
            return true;
        else if (regimen1.equals(Regimen.OMNI.toString()))
            return false;
        else {
            if(regimen1.equals(Regimen.VEGANO.toString())
                    && regimen2.equals(Regimen.VEGETARIANO.toString()))
                return true;
            else
                return false;
        }
    }
}
