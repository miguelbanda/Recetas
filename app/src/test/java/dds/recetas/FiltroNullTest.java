package dds.recetas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.FiltroQuery;
import dds.recetas.datos.FiltroQueryFactory;
import dds.recetas.datos.Receta;

import static junit.framework.Assert.assertEquals;

public class FiltroNullTest {
    @Test
    public void noSeFiltraNada() {
        int n = 3;
        List<Receta> recetas = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            recetas.add(new Receta());
        }

        FiltroQueryFactory fab = FiltroQueryFactory.getInstance();
        FiltroQuery filtro = fab.build();

        List<Receta> filtradas = Receta.filtrar(recetas, filtro);

        assertEquals(n, filtradas.size());
    }
}
