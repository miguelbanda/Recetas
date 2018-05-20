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
    public static int fromRegimen(Regimen regimen) {
        if(regimen == null)
            return 2;
        switch (regimen) {
            case VEGANO:
                return 0;
            case VEGETARIANO:
                return 1;
            default:
                return 2;
        }
    }

    @TypeConverter
    public static Regimen toRegimen(int regimen) {
        switch (regimen) {
            case 0:
                return Regimen.VEGANO;
            case 1:
                return Regimen.VEGETARIANO;
            default:
                return Regimen.OMNI;
        }

    }
}
