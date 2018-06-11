package dds.recetas.datos;

public class FiltroQueryIngredientes implements FiltroQuery {
    private String criterio;

    public FiltroQueryIngredientes(String criterio) {
        this.criterio = criterio;
    }

    @Override
    public boolean filtro(Receta r) {
        for(Ingrediente i : r.ingredientes) {
            if(i.nombre.toLowerCase().contains(criterio.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
