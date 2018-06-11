package dds.recetas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.FiltroQuery;
import dds.recetas.datos.FiltroQueryFactory;
import dds.recetas.datos.Receta;
import dds.recetas.datos.Regimen;
import dds.recetas.datos.Tipo;

import static junit.framework.Assert.assertEquals;

public class FiltroBusquedaAvanzadaTest {
    @Test
    public void postresVeganos() {
        int n = 3;
        List<Receta> recetas = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            recetas.add(new Receta());
            recetas.get(i).setRegimen(Regimen.OMNI);
            recetas.get(i).setTipo(Tipo.POSTRE);
        }
        recetas.get(1).setRegimen(Regimen.VEGANO);

        FiltroQueryFactory fab = FiltroQueryFactory.getInstance();
        FiltroQuery filtro = fab.build(null, null, Tipo.POSTRE, Regimen.VEGANO, null);

        List<Receta> filtradas = Receta.filtrar(recetas, filtro);

        assertEquals(1, filtradas.size());
    }
}
