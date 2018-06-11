package dds.recetas;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dds.recetas.datos.FiltroQuery;
import dds.recetas.datos.FiltroQueryFactory;
import dds.recetas.datos.Receta;

import static junit.framework.Assert.assertEquals;

public class FiltroNombreTest {
    @Test
    public void recuperarDos() {
        List<Receta> recetas = new ArrayList<>();
        Receta primera = new Receta();
        primera.setNombre("Hamburguesa");
        Receta segunda = new Receta();
        segunda.setNombre("Trio de hamburguesas");
        Receta tercera = new Receta();
        tercera.setNombre("Pollo con champiñones");
        Receta cuarta = new Receta();
        cuarta.setNombre("Torta de cumpleaños");

        recetas.add(primera);
        recetas.add(segunda);
        recetas.add(tercera);
        recetas.add(cuarta);

        FiltroQueryFactory fab = FiltroQueryFactory.getInstance();
        FiltroQuery filtro = fab.build("hamburguesa", FiltroQueryFactory.TipoFiltro.NOMBRE);
        List<Receta> filtradas = Receta.filtrar(recetas, filtro);

        assertEquals(2, filtradas.size());
    }
}
