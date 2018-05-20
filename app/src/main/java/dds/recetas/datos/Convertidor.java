package dds.recetas.datos;

import android.arch.persistence.room.TypeConverter;

public class Convertidor {

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
}
