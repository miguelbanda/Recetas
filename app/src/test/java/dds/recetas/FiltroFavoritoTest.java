package dds.recetas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.FiltroQuery;
import dds.recetas.datos.FiltroQueryFactory;
import dds.recetas.datos.Receta;

import static junit.framework.Assert.assertEquals;

public class FiltroFavoritoTest {
    @Test
    public void soloFavoritos() {
        int n = 3;
        List<Receta> recetas = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            recetas.add(new Receta());
        }
        recetas.get(2).setFavorito(true);

        FiltroQueryFactory fab = FiltroQueryFactory.getInstance();
        FiltroQuery filtro = fab.build(true);

        List<Receta> filtradas = Receta.filtrar(recetas, filtro);

        assertEquals(1, filtradas.size());
    }
}
