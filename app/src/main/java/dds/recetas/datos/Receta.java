package dds.recetas.datos;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class Receta {
    public String id;
    public String nombre;
    public String foto;
    public boolean favorito;
    public String regimen;
    public String tipo;
    public List<Ingrediente> ingredientes;
    public List<Paso> pasos;
    public int porciones;

    public Receta(String nombre, String foto, Regimen regimen, Tipo tipo, List<Ingrediente> ingredientes, List<Paso> pasos, int porciones) {
        this.nombre = nombre;
        this.foto = foto;
        this.favorito = true;
        this.regimen = regimen.toString();
        this.tipo = tipo.toString();
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.porciones = porciones;
    }

    public Receta() {
        //Default constructor necesario para Firebase
    }

    public static List<Receta> filtrar(List<Receta> recetas, FiltroQuery query) {
        List<Receta> resultado = new ArrayList<>();
        for(Receta r : recetas) {
            if(query.filtro(r)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public static List<Receta> filtrar(List<Receta> recetas, FiltroQuery... queries) {
        List<Receta> resultado = recetas;

        for(int i = 0; i < queries.length; i++) {
            resultado = filtrar(resultado, queries[i]);
        }

        return resultado;
    }
}
