package dds.recetas.datos;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Paso {

    public String paso;
    public int numero;

    public Paso(String paso, int numero) {
        this.paso = paso;
        this.numero = numero;
    }

    public Paso() {
        //Default constructor necesario para Firebase
    }
}
