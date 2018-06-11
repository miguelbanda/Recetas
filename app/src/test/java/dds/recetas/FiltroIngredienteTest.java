package dds.recetas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.FiltroQuery;
import dds.recetas.datos.FiltroQueryFactory;
import dds.recetas.datos.Ingrediente;
import dds.recetas.datos.Receta;

import static junit.framework.Assert.assertEquals;

public class FiltroIngredienteTest {
    @Test
    public void soloRecetasConTomate() {
        int n = 3;
        List<Receta> recetas = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            recetas.add(new Receta());
            recetas.get(i).ingredientes = new ArrayList<>();
        }

        recetas.get(0).ingredientes.add(new Ingrediente("Albahaca"));
        recetas.get(1).ingredientes.add(new Ingrediente("Fresa"));
        recetas.get(2).ingredientes.add(new Ingrediente("Tomate"));


        FiltroQueryFactory fab = FiltroQueryFactory.getInstance();
        FiltroQuery filtro = fab.build("tomate", FiltroQueryFactory.TipoFiltro.INGREDIENTE);

        List<Receta> filtradas = Receta.filtrar(recetas, filtro);

        assertEquals(1, filtradas.size());
        assertEquals(recetas.get(2).ingredientes.get(0), filtradas.get(0).ingredientes.get(0));
    }
}
