package dds.recetas.datos;

import android.app.Application;
import android.content.Context;

import java.util.List;

//Patrón adaptador
public class BdRecetaAPI {

    private static BdRecetaAPI INSTANCE;
    private DatabaseDao mDatabaseDao;

    //Antiguo método
    /*
    private void buildReceta(Receta r) {
        r.setIngredientes(mDatabaseDao.getIngredientes(r.getId()));
        r.setPasos(mDatabaseDao.getPasos(r.getId()));
    }
    */

    //Refactoring 1 : build recetaS en vez de hacer la iteración cada vez
    //  Retornar el parámetro en vez de void para que el código sea mas conciso
    private List<Receta> buildRecetas(List<Receta> recetas) {
        for(Receta r : recetas) {
            r.setIngredientes(mDatabaseDao.getIngredientes(r.getId()));
            r.setPasos(mDatabaseDao.getPasos(r.getId()));
        }
        return recetas;
    }

    public List<Receta> favoritos() {
        List<Receta> resultado = mDatabaseDao.verFavoritos();
        return buildRecetas(resultado);
    }

    public void agregarFavorito(Receta r) {
        mDatabaseDao.nuevoFavorito(new Favorito(r));
    }

    public void borrarFavorito(Receta r) {
        mDatabaseDao.borrarFavorito(new Favorito(r));
    }

    public List<Receta> buscarReceta(String titulo, String ingrediente,
                                     Tipo tipo, Regimen regimen) {
        if(titulo == null)
            titulo = "";
        if(ingrediente == null)
            ingrediente = "";
        if(tipo == null)
            tipo = Tipo.INDIFERENTE;
        if(regimen == null)
            regimen = Regimen.OMNI;

        List<Receta> resultado = mDatabaseDao.buscarReceta(titulo, ingrediente, regimen, tipo);
        return buildRecetas(resultado);
    }

    public void crearReceta(Receta r) {
        mDatabaseDao.nuevaReceta(r);

        //Obtener el id de la receta que acabamos de crear
        int id = buscarRecetaPorTitulo(r.getTitulo()).get(0).getId();

        for(Ingrediente i : r.getIngredientes()) {
            i.setReceta(id);
        }

        for(Paso p : r.getPasos()) {
            p.setReceta(id);
        }

        mDatabaseDao.addIngredientes(r.getIngredientes());
        mDatabaseDao.addPasos(r.getPasos());
    }

    public List<Receta> buscarRecetaPorTitulo(String titulo) {
        if(titulo == null)
            titulo = "";

        return buildRecetas(mDatabaseDao.buscarRecetaPorTitulo(titulo));
    }

    //Singleton
    public static BdRecetaAPI getInstance(Application app) {
        if (INSTANCE == null)
            INSTANCE = new BdRecetaAPI(app);
        return INSTANCE;
    }

    private BdRecetaAPI(Application app) {
        BdReceta bd = BdReceta.getInstance(app);
        mDatabaseDao = bd.databaseDao();
    }
}
