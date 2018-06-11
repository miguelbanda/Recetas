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

public class FiltroAplicarVariosTest {
    @Test
    public void aplicarVariosFiltros() {
        int n = 3;
        List<Receta> recetas = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            recetas.add(new Receta());
            recetas.get(i).setRegimen(Regimen.OMNI);
            recetas.get(i).setTipo(Tipo.POSTRE);
        }
        recetas.get(1).setRegimen(Regimen.VEGANO);
        recetas.get(1).setNombre("Pastel vegano con fresas");

        FiltroQueryFactory fab = FiltroQueryFactory.getInstance();
        FiltroQuery filtro1 = fab.build(null, null, Tipo.POSTRE, Regimen.VEGANO, null);
        FiltroQuery filtro2 = fab.build("Fresa", FiltroQueryFactory.TipoFiltro.NOMBRE);
        List<FiltroQuery> filtros = new ArrayList<>();
        filtros.add(filtro1);
        filtros.add(filtro2);
        List<Receta> filtradas = Receta.filtrar(recetas, filtros);

        assertEquals(1, filtradas.size());
    }
}
