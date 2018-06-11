package dds.recetas;

import dds.recetas.datos.Receta;

//Patrón Estrategia
public interface FiltroQuery {
    boolean filtro(Receta r);
}
