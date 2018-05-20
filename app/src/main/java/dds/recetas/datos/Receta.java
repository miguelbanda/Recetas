package dds.recetas.datos;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import java.util.List;

@Entity
public class Receta {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titulo;
    private String foto;
    private int porciones;

    //Refactoring: usar enums para Tipo y Regimen en vez de números para garantizar
    //la seguridad de tipos.
    private Regimen regimen;
    private Tipo tipo;

    private @Ignore List<IngredienteCuantificado> ingredientes;
    private @Ignore List<Paso> pasos;

    @TypeConverter
    public static String fromTipo(Tipo tipo) {
        if(tipo == null)
            return "INDIFERENTE";
        switch (tipo) {
            case ENTRANTE:
                return "ENTRANTE";
            case APERITIVO:
                return "APERITIVO";
            case POSTRE:
                return "POSTRE";
            case PRINCIPAL:
                return "PRINCIPAL";
            default:
                return "INDIFERENTE";
        }
    }

    @TypeConverter
    public static Tipo toTipo(String tipo) {
        if(tipo == null)
            return Tipo.INDIFERENTE;

        //Desde Java 7, se puede usar un switch-statement para Strings
        switch (tipo) {
            case "ENTRANTE":
                return Tipo.ENTRANTE;
            case "APERITIVO":
                return Tipo.APERITIVO;
            case "POSTRE":
                return Tipo.POSTRE;
            case "PRINCIPAL":
                return Tipo.PRINCIPAL;
            default:
                return Tipo.INDIFERENTE;
        }

    }

    @TypeConverter
    public static String fromRegimen(Regimen regimen) {
        if(regimen == null)
            return "OMNI";
        switch (regimen) {
            case VEGANO:
                return "VEGANO";
            case VEGETARIANO:
                return "VEGETARIANO";
            default:
                return "OMNI";
        }
    }

    @TypeConverter
    public static Regimen toRegimen(String regimen) {
        if(regimen == null)
            return Regimen.OMNI;

        switch (regimen) {
            case "VEGANO":
                return Regimen.VEGANO;
            case "VEGETARIANO":
                return Regimen.VEGETARIANO;
            default:
                return Regimen.OMNI;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getPorciones() {
        return porciones;
    }

    public void setPorciones(int porciones) {
        this.porciones = porciones;
    }

    public Regimen getRegimen() {
        return regimen;
    }

    public void setRegimen(Regimen regimen) {
        this.regimen = regimen;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<IngredienteCuantificado> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteCuantificado> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<Paso> getPasos() {
        return pasos;
    }

    public void setPasos(List<Paso> pasos) {
        this.pasos = pasos;
    }

    public Receta(String titulo, String foto, int porciones, Regimen regimen,
                  Tipo tipo, List<IngredienteCuantificado> ingredientes, List<Paso> pasos, int id) {
        this.titulo = titulo;
        this.foto = foto;
        this.porciones = porciones;
        this.regimen = regimen;
        this.tipo = tipo;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.id = id;
    }

    public Receta calcularNuevasPorciones(int porciones) {
        double ratio = porciones/this.porciones;
        for(IngredienteCuantificado i : ingredientes)
            i.setCantidad(i.getCantidad() * ratio);
        return new Receta(titulo, foto, porciones, regimen, tipo, ingredientes, pasos, id);
    }
}
