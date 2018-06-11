package dds.recetas.datos;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Paso {

    private String paso;
    private int numero;

    public Paso(String paso, int numero) {
        this.setPaso(paso);
        this.setNumero(numero);
    }

    public Paso() {
        //Default constructor necesario para Firebase
    }

    public String getPaso() {
        return paso;
    }

    public void setPaso(String paso) {
        this.paso = paso;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
