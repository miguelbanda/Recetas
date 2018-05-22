package dds.recetas.datos;

import android.arch.persistence.room.TypeConverter;

public class ConvertidorReceta {

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
