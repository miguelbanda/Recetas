package dds.recetas.datos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface DatabaseDao {

    /*
    Antiguos métodos:

    @Query("SELECT * FROM ingrediente WHERE nombre LIKE :texto")
    List<Ingrediente> buscarIngrediente(String texto);

    @Query("SELECT receta.* FROM receta, ing_rec, ingrediente " +
            "WHERE receta.id = ing_rec.receta " +
            "AND ing_rec.ingrediente = ingrediente.id " +
            "AND lower(ingrediente.nombre) LIKE '%:nombreIngrediente%'")
    List<Receta> buscarRecetaPorIngrediente(String nombreIngrediente);

    @Query("SELECT * FROM receta " +
            "WHERE tipo = :tipoReceta")
    List<Receta> buscarRecetaPorTipo(String tipoReceta);

    @Query("SELECT * FROM receta " +
            "WHERE regimen = :regimenReceta")
    List<Receta> buscarRecetaPorRegimen(String regimenReceta);
    */

    //Refactoring 2 : dos métodos de búsqueda en vez de cuatro para que la DB se encargue
    //directamente de aplicar los filtros
    @Query("SELECT * FROM receta WHERE instr(lower(titulo), lower(:texto)) > 0")
    List<Receta> buscarRecetaPorTitulo(String texto);

    @Query("SELECT r.* FROM receta r, ingrediente i, ing_rec ir " +
            "WHERE r.id = ir.receta AND i.id = ir.ingrediente " +
            "AND instr(lower(i.nombre), lower(:ingrediente)) > 0 " +
            "AND r.regimen = :regimen " +
            "AND r.tipo = :tipo " +
            "AND instr(lower(r.titulo), lower(:titulo)) > 0")
    List<Receta> buscarReceta(String titulo, String ingrediente, Regimen regimen, Tipo tipo);

    @Query("SELECT i.nombre AS ingrediente, a.cantidad AS cantidad, a.unidad AS unidad " +
            "FROM ingrediente i, ing_rec a " +
            "WHERE a.receta = :idReceta")
    List<IngredienteCuantificado> getIngredientes(int idReceta);

    @Query("SELECT * FROM paso p " +
            "WHERE p.receta = :idReceta " +
            "ORDER BY p.numero")
    List<Paso> getPasos(int idReceta);

    @Query("SELECT r.* FROM favorito f, receta r WHERE r.id = f.idReceta")
    List<Receta> verFavoritos();

    @Query("SELECT * FROM receta")
    List<Receta> verRecetas();

    @Insert(onConflict = IGNORE)
    void nuevoIngrediente(Ingrediente i);

    @Insert(onConflict = IGNORE)
    void nuevaReceta(Receta r);

    @Insert
    void addPasos(List<Paso> pasos);

    @Insert
    void asociarIngrediente(AsociacionIngredienteReceta a);

    @Insert
    void nuevoFavorito(Favorito f);

    @Delete
    void borrarFavorito(Favorito f);

}
