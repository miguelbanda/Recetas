package dds.recetas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.FiltroQuery;
import dds.recetas.datos.FiltroQueryFactory;
import dds.recetas.datos.Receta;

import static junit.framework.Assert.assertEquals;

public class FiltroIdTest {
    @Test
    public void soloRecetaConId4() {
        int n = 10;
        List<Receta> recetas = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            recetas.add(new Receta());
            recetas.get(i).id = String.valueOf(i+1);
        }

        FiltroQueryFactory fab = FiltroQueryFactory.getInstance();
        FiltroQuery filtro = fab.build("4", FiltroQueryFactory.TipoFiltro.ID);

        List<Receta> filtradas = Receta.filtrar(recetas, filtro);

        assertEquals(1, filtradas.size());
        assertEquals("4", filtradas.get(0).id);
    }
}
