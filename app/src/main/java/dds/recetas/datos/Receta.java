package dds.recetas.datos;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class Receta {
    private String id;
    private String nombre;
    private String foto;
    private boolean favorito;
    private String regimen;
    private String tipo;
    private List<Ingrediente> ingredientes;
    private List<Paso> pasos;
    private int porciones;

    public Receta(String nombre, String foto, Regimen regimen, Tipo tipo, List<Ingrediente> ingredientes, List<Paso> pasos, int porciones) {
        this.setNombre(nombre);
        this.setFoto(foto);
        this.setFavorito(false);
        this.setRegimen(regimen);
        this.setTipo(tipo);
        this.setIngredientes(ingredientes);
        this.setPasos(pasos);
        this.setPorciones(porciones);
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

    public static List<Receta> filtrar(List<Receta> recetas, List<FiltroQuery> queries) {
        List<Receta> resultado = recetas;

        for(FiltroQuery query : queries) {
            resultado = filtrar(resultado, query);
        }

        return resultado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public Regimen getRegimen() {
        switch (regimen) {
            case "VEGANO":
                return Regimen.VEGANO;
            case "VEGETARIANO":
                return Regimen.VEGETARIANO;
            default:
                return Regimen.OMNI;
        }
    }

    public void setRegimen(Regimen regimen) {
        this.regimen = regimen.toString();
    }

    public Tipo getTipo() {
        switch (tipo) {
            case "ENTRANTE":
                return Tipo.ENTRANTE;
            case "APERITIVO":
                return Tipo.APERITIVO;
            case "PRINCIPAL":
                return Tipo.PRINCIPAL;
            case "POSTRE":
                return Tipo.POSTRE;
            default:
                return Tipo.INDIFERENTE;

        }
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo.toString();
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<Paso> getPasos() {
        return pasos;
    }

    public void setPasos(List<Paso> pasos) {
        this.pasos = pasos;
    }

    public int getPorciones() {
        return porciones;
    }

    public void setPorciones(int porciones) {
        this.porciones = porciones;
    }
}
