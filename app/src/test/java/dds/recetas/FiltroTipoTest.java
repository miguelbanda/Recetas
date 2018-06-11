package dds.recetas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.FiltroQuery;
import dds.recetas.datos.FiltroQueryFactory;
import dds.recetas.datos.Receta;
import dds.recetas.datos.Tipo;

import static junit.framework.Assert.assertEquals;

public class FiltroTipoTest {
    @Test
    public void soloPostres() {
        int n = 3;
        List<Receta> recetas = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            recetas.add(new Receta());
            recetas.get(i).setTipo(Tipo.PRINCIPAL);
        }

        recetas.get(1).setTipo(Tipo.POSTRE);

        FiltroQueryFactory fab = FiltroQueryFactory.getInstance();
        FiltroQuery filtro = fab.build(Tipo.POSTRE);

        List<Receta> filtradas = Receta.filtrar(recetas, filtro);

        assertEquals(1, filtradas.size());
    }
}
